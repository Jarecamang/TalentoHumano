/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.CertificateDAO;
import DataAccess.Entity.Certificate;
import DataAccess.Entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
            certificateObject.add(new Certificate(1, "No tiene certificados por aprovar.", false));
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
    
    public String downloadCertificate(Certificate certificate){
        return "casa";
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
            Logger.getLogger(HandleCertificate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "El certificado se aprobó satisfactoriamente";
    }

}
