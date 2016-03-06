/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.CertificateDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Certificate;
import DataAccess.Entity.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alejandro
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
        //ContractDAO contrDAO = new ContractDAO();
        //Contract contractObject = contrDAO.getUserContract(new User(user.getPkID()));
        Certificate cert = certificateReturn.get(option);
        String text = "CERTIFICADO " + cert.getType().toUpperCase();
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph(text));
        document.add(new Paragraph(new Paragraph("\n\nDescripcion del certificado : " + cert.getDescripcion())));
        document.add(new Paragraph("\nNombre : " + user.getName() + " " + user.getLastname()));
        document.add(new Paragraph("\nTarjeta de Identificacion : " + user.getIdentifyCard()));
        document.add(new Paragraph("\nDireccion : " + user.getAddress()));
        document.add(new Paragraph("\nTelefono : " + user.getPhone()));
        document.add(new Paragraph("\nCorreo electronico : " + user.getEmail()));
        File f = null;
        if (cert.getType().toLowerCase().equals("laboral")) {
            document.add(new Paragraph("\nNivel de Capacitacion : " + user.getLevelTraining()));
            f = new File("CertificadoLaboral.pdf");
            //document.add(new Paragraph("\nTipo de Contrato : "+contractObject.getType()));
            //document.add(new Paragraph("\nFecha de Inicio del contrato : "+contractObject.getStartDate()));
            //document.add(new Paragraph("\nFecha de finalizacion del contrato : "+contractObject.getEnddate()));
        } else if (cert.getType().toLowerCase().equals("nómina")) {
            f = new File("CertificadoNomina.pdf");
            //document.add(new Paragraph("\nSalario : "+contractObject.getSalary()));
        } else if (cert.getType().toLowerCase().equals("salud")) {
            f = new File("CertificadoSalud.pdf");
            //document.add(new Paragraph("\nSalud Fecha : "+contractObject.getStartHealthDate()));
            //document.add(new Paragraph("\nSalud  : "+contractObject.getHealthEnterprise()));
        } else if (cert.getType().toLowerCase().equals("pension")) {
            f = new File("CertificadoPension.pdf");
            //document.add(new Paragraph("\nInicio de Pension : "+contractObject.getStartPensionDate()));
            //document.add(new Paragraph("\nPension de la empresa: "+contractObject.getPensionEnterprise()));
        }
        document.close();
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/octet-strem");
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
