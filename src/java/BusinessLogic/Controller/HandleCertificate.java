/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.CertificateDAO;
import DataAccess.DAO.ContractDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Certificate;
import DataAccess.Entity.Contract;
import DataAccess.Entity.Position;
import DataAccess.Entity.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Edwin
 */
public class HandleCertificate {

    public String doPetition(String type, String description) {
        CertificateDAO certificateDAO = new CertificateDAO();
        Certificate certificate = new Certificate();

        certificate.setType(type);
        certificate.setDescripcion(description);
        certificate.setAproved(false);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        User user = new User((Integer) ec.getSessionMap().get("userId"));
        certificate.setFkuserID(user);
        Certificate certificateObject = certificateDAO.persist(certificate);

        if (certificateObject != null) {
            return "El certificado se ha solicitado. Usted será informado cuando éste sea aprobado.";
        } else {
            return "Error: El certificado no pudo ser solicitado.";
        }
    }

    public void getUnaprovedCertificates() {
        CertificateDAO certificateDAO = new CertificateDAO();
        List<Certificate> certificateObject = certificateDAO.searchUnaproved();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (certificateObject != null) {
            ec.getSessionMap().put("notificationList", certificateObject);
        } else {
            certificateObject.add(new Certificate(1, "No tiene certificados por aprobar.", false));
            ec.getSessionMap().put("notificationList", certificateObject);
        }
        String url = "";
        url = ec.encodeActionURL(
                FacesContext.getCurrentInstance().getApplication().getViewHandler().getActionURL(FacesContext.getCurrentInstance(), "/administration/aproveCertificate.xhtml"));
        try {
            ec.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(HandleCertificate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getUserCertificates() {
        CertificateDAO certificateDAO = new CertificateDAO();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        User user = new User((Integer) ec.getSessionMap().get("userId"));
        List<Certificate> certificateObject = certificateDAO.searchUserAproved();
        List<Certificate> certificateReturn = new ArrayList<Certificate>();
        if (certificateObject != null) {
            certificateReturn.clear();
            for (int i = 0; i < certificateObject.size(); i++) {
                if (certificateObject.get(i).getFkuserID().getPkID().equals(user.getPkID())) {
                    certificateReturn.add(certificateObject.get(i));
                }
            }
            ec.getSessionMap().put("notificationUserList", certificateReturn);
        } else {
            certificateObject.add(new Certificate(1, "No tiene certificados aprobados", false));
            ec.getSessionMap().put("notificationUserList", certificateObject);
        }
        String url = "";
        url = ec.encodeActionURL(
                FacesContext.getCurrentInstance().getApplication().getViewHandler().getActionURL(FacesContext.getCurrentInstance(), "/empleado/certificateNotification.xhtml"));
        try {
            ec.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(HandleCertificate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void downloadCertificate(HttpServletResponse response, int idUser, int option) throws DocumentException, IOException {
        UserDAO usDAO = new UserDAO();
        User user = usDAO.searchByPkID(idUser);
        CertificateDAO certificateDAO = new CertificateDAO();
        List<Certificate> certificateObject = certificateDAO.searchUserAproved();
        List<Certificate> certificateReturn = new ArrayList<Certificate>();
        if (certificateObject != null) {
            certificateReturn.clear();
            for (int i = 0; i < certificateObject.size(); i++) {
                if (certificateObject.get(i).getFkuserID().getPkID().equals(user.getPkID())) {
                    certificateReturn.add(certificateObject.get(i));
                }
            }
        }
        ContractDAO contrDAO = new ContractDAO();
        Contract contractObject = contrDAO.getUserContract(new User(user.getPkID()));
        Certificate cert = certificateReturn.get(option);
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open(); 
        Font fuente = new Font();
        fuente.setStyle(Font.BOLD);    
        fuente.setColor(BaseColor.BLACK);
        fuente.setFamily(Font.FontFamily.TIMES_ROMAN.toString());
        fuente.setSize(15);
        Paragraph header = new Paragraph("\n\n\n\n\nEL DEPARTAMENTO DE GESTION HUMANA\n"
                + "DE TALENTO-HUMANO LTDA\n\n\n\n\n"
                + "CERTIFICA QUE:\n\n\n\n\n",fuente);
        header.setAlignment(Element.ALIGN_CENTER);
        Calendar fecha = new GregorianCalendar();
        int annio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH)+1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        Paragraph endtext = new Paragraph("\n\nSe expide la presente certificacion a solicitud"
            + " del interesado a la fecha de : "+dia+"/"+mes+"/"+annio 
            +"\n\n\nCordialmente\n\n\nEdwin Alexander Bohorquez\nGERENTE GENERAL DE TALENTO-HUMANO LTDA");
        endtext.setAlignment(Element.ALIGN_LEFT);
        String typeCertificate = "";
        if (cert.getType().toLowerCase().equals("laboral")) {
            typeCertificate = "Laboral";
            document.add(header);
            List<String> positionlist = new ArrayList<String>();
            for( Position cargo : contractObject.getPositionSet()){
                 positionlist.add(cargo.getName());
            }
            Paragraph body = new Paragraph(user.getName().toUpperCase()+" "+user.getLastname().toUpperCase()
                    +" con cedula de ciudadania No."+user.getIdentifyCard()+", trabaja en esta empresa"
                    +" desde "+contractObject.getStartDate()+", desempeñandose actualmente como "
                    +positionlist+" con una asignacion mensual de $"
                    +contractObject.getSalary()+".Su contrato de trabajo es a termino "
                    +contractObject.getType()+"."
            );
            body.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            document.add(body);
            document.add(endtext);
        } else if (cert.getType().toLowerCase().equals("nómina")) {
            typeCertificate = "Nomina";
            Paragraph Payroll_header = new Paragraph("DEPARTAMENTO DE CONTABILIDAD\n"
                + "TALENTO-HUMANO LTDA\n\n"
                + "LIQUIDACION DE NOMINA:\n"
                + "Nombre del empleado : "+user.getName()+" "+user.getLastname()+"\n"
                + "Cedula : "+user.getIdentifyCard()+"\n\n",fuente);
            Payroll_header.setAlignment(Element.ALIGN_CENTER);
            document.add(Payroll_header);
            double commissions=10000; 
            double extra_hours=50000;
            double transportation_aid=63600;
            double totalAccrued = contractObject.getSalary()+commissions+extra_hours+transportation_aid;
              
            PdfPTable basetable = new PdfPTable(2);       
            PdfPCell headertable = new PdfPCell(new Paragraph("Tabla Base",fuente));
            headertable.setColspan(2);
            basetable.addCell(headertable);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Salario Basico : "));
            PdfPCell cell2 = new PdfPCell(new Paragraph(String.valueOf(contractObject.getSalary())));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Comisiones : "));
            PdfPCell cell4 = new PdfPCell(new Paragraph(String.valueOf(commissions)));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Horas Extras : "));
            PdfPCell cell6 = new PdfPCell(new Paragraph(String.valueOf(extra_hours)));
            PdfPCell cell7 = new PdfPCell(new Paragraph("Auxilio de transporte : "));
            PdfPCell cell8 = new PdfPCell(new Paragraph(String.valueOf(transportation_aid)));
            PdfPCell ce9 = new PdfPCell(new Paragraph("Total Devengado : "));
            PdfPCell ce10 = new PdfPCell(new Paragraph(" $ "+String.valueOf(totalAccrued)));
            basetable.setWidthPercentage(100F);
            basetable.setHorizontalAlignment(Element.ALIGN_CENTER);
            basetable.addCell(cell1);
            basetable.addCell(cell2);
            basetable.addCell(cell3);
            basetable.addCell(cell4);
            basetable.addCell(cell5);
            basetable.addCell(cell6);
            basetable.addCell(cell7);
            basetable.addCell(cell8);
            basetable.addCell(ce9);
            basetable.addCell(ce10);
            document.add(basetable);
            
            PdfPTable tableliquidation = new PdfPTable(2);       
            PdfPCell title = new PdfPCell(new Paragraph("Liquidacion. Deducciones de nomina(Conceptos a cargo del empleado) ",fuente));
            title.setColspan(2);
            tableliquidation.addCell(title);
            PdfPCell cell9 = new PdfPCell(new Paragraph("Salud (4%) : "));
            PdfPCell cell10 = new PdfPCell(new Paragraph(String.valueOf(contractObject.getSalary()*0.04)));
            PdfPCell cell11 = new PdfPCell(new Paragraph("Pension (4%) : "));
            PdfPCell cell12 = new PdfPCell(new Paragraph(String.valueOf(contractObject.getSalary()*0.04)));
            
            tableliquidation.setWidthPercentage(100F);
            tableliquidation.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableliquidation.addCell(cell9);
            tableliquidation.addCell(cell10);
            tableliquidation.addCell(cell11);
            tableliquidation.addCell(cell12);
            document.add(tableliquidation);
            
            PdfPTable tablesocialSecurity = new PdfPTable(2);       
            PdfPCell title2 = new PdfPCell(new Paragraph("Seguridad Social a cargo del empleador",fuente));
            title2.setColspan(2);
            tablesocialSecurity.addCell(title2);
            PdfPCell cell13 = new PdfPCell(new Paragraph("Salud (8.5%) : "));
            PdfPCell cell14 = new PdfPCell(new Paragraph(String.valueOf(contractObject.getSalary()*0.085)));
            PdfPCell cell15 = new PdfPCell(new Paragraph("Pension (12%) : "));
            PdfPCell cell16 = new PdfPCell(new Paragraph(String.valueOf(contractObject.getSalary()*0.12)));
            PdfPCell cell17 = new PdfPCell(new Paragraph("A.R.P : "));
            PdfPCell cell18 = new PdfPCell(new Paragraph(String.valueOf(contractObject.getSalary()*0.00522)));
            
            tablesocialSecurity.setWidthPercentage(100F);
            tablesocialSecurity.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablesocialSecurity.addCell(cell13);
            tablesocialSecurity.addCell(cell14);
            tablesocialSecurity.addCell(cell15);
            tablesocialSecurity.addCell(cell16);
            tablesocialSecurity.addCell(cell17);
            tablesocialSecurity.addCell(cell18);
            document.add(tablesocialSecurity);
            
            PdfPTable social_benefits = new PdfPTable(2);       
            PdfPCell title3 = new PdfPCell(new Paragraph("Prestaciones Sociales",fuente));
            title3.setColspan(2);
            social_benefits.addCell(title3);
            PdfPCell cell19 = new PdfPCell(new Paragraph("Prima de servicios : "));   
            PdfPCell cell20 = new PdfPCell(new Paragraph(String.valueOf(totalAccrued*0.0833)));
            PdfPCell cell21 = new PdfPCell(new Paragraph("Cesantias : "));
            PdfPCell cell22 = new PdfPCell(new Paragraph(String.valueOf(totalAccrued*0.0833)));
            PdfPCell cell23 = new PdfPCell(new Paragraph("Intereses sobre las cesantias : "));
            PdfPCell cell24 = new PdfPCell(new Paragraph(String.valueOf(totalAccrued*0.0833*0.12)));
            PdfPCell cell25 = new PdfPCell(new Paragraph("Vacaciones : "));
            PdfPCell cell26 = new PdfPCell(new Paragraph(String.valueOf(contractObject.getSalary()*0.0417)));
            social_benefits.setWidthPercentage(100F);
            social_benefits.setHorizontalAlignment(Element.ALIGN_CENTER);
            social_benefits.addCell(cell19);
            social_benefits.addCell(cell20);
            social_benefits.addCell(cell21);
            social_benefits.addCell(cell22);
            social_benefits.addCell(cell23);
            social_benefits.addCell(cell24);
            social_benefits.addCell(cell25);
            social_benefits.addCell(cell26);    
            document.add(social_benefits);
            
            PdfPTable fiscal_contributions = new PdfPTable(2);       
            PdfPCell title4 = new PdfPCell(new Paragraph("Aportes Parafiscales",fuente));
            title4.setColspan(2);
            fiscal_contributions.addCell(title4);
            PdfPCell cell27 = new PdfPCell(new Paragraph("Cajas de compensacion familiar (4%) : "));   
            PdfPCell cell28 = new PdfPCell(new Paragraph(String.valueOf((contractObject.getSalary()+extra_hours+commissions)*0.04)));
            PdfPCell cell29 = new PdfPCell(new Paragraph("I.CB.F (3%) : "));
            PdfPCell cell30 = new PdfPCell(new Paragraph(String.valueOf((contractObject.getSalary()+extra_hours+commissions)*0.03)));
            PdfPCell cell31 = new PdfPCell(new Paragraph("Sena (2%) : "));
            PdfPCell cell32 = new PdfPCell(new Paragraph(String.valueOf((contractObject.getSalary()+extra_hours+commissions)*0.02)));
            fiscal_contributions.setWidthPercentage(100F);
            fiscal_contributions.setHorizontalAlignment(Element.ALIGN_CENTER);
            fiscal_contributions.addCell(cell27);
            fiscal_contributions.addCell(cell28);
            fiscal_contributions.addCell(cell29);
            fiscal_contributions.addCell(cell30);
            fiscal_contributions.addCell(cell31);
            fiscal_contributions.addCell(cell32);
            document.add(fiscal_contributions);
            
            PdfPTable totalValue = new PdfPTable(2);       
            PdfPCell title5 = new PdfPCell(new Paragraph("Neto a pagar al empleado",fuente));
            title5.setColspan(2);
            totalValue.addCell(title5);
            PdfPCell cell33 = new PdfPCell(new Paragraph(" Total devengado - salud - pension"));   
            cell33.setColspan(2);
            PdfPCell cell34 = new PdfPCell(new Paragraph(" $ "+String.valueOf(totalAccrued-contractObject.getSalary()*0.08),fuente));
            cell34.setColspan(2);
            totalValue.setWidthPercentage(100F);
            totalValue.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalValue.addCell(cell33);
            totalValue.addCell(cell34);
            document.add(totalValue);
             
            document.add(endtext);
        } else if (cert.getType().toLowerCase().equals("salud")) {
            typeCertificate = "Salud";
            document.add(header);
            Paragraph body = new Paragraph(user.getName().toUpperCase()+" "+user.getLastname().toUpperCase()
                    +" con cedula de ciudadania No."+user.getIdentifyCard()+", esta afiliado desde "
                    +contractObject.getStartHealthDate()+" a la empresa de salud "
                    +contractObject.getHealthEnterprise()+"."
            );
            body.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            document.add(body);
            document.add(endtext);
        } else if (cert.getType().toLowerCase().equals("pensión")) {
            typeCertificate = "Pension";
            document.add(header);
            Paragraph body = new Paragraph(user.getName().toUpperCase()+" "+user.getLastname().toUpperCase()
                    +" con cedula de ciudadania No."+user.getIdentifyCard()+", esta afiliado a la empresa de pension "
                    +contractObject.getPensionEnterprise()+".La fecha de inicio de pension es "
                    +contractObject.getStartPensionDate()+"."
            );
            body.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            document.add(body);
            document.add(endtext);
        } 
        document.close(); 
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/octet-strem");
        File f = new File("Certificado"+typeCertificate+".pdf");
        response.setHeader("Content-Disposition", "attachment;filename=" + f.getName());
        response.setContentLength(baos.size());
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
    }

    public String aproveCertificate(Certificate certificate) {
        CertificateDAO certificateDAO = new CertificateDAO();
        certificateDAO.editAproved(certificate);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String url = "";
        url = ec.encodeActionURL(
                FacesContext.getCurrentInstance().getApplication().getViewHandler().getActionURL(FacesContext.getCurrentInstance(), "/administration/adminPanel.xhtml"));
        try {
            ec.redirect(url);

        } catch (IOException ex) {
            Logger.getLogger(HandleCertificate.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return "El certificado se aprobó satisfactoriamente";
    }

}
