/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleCertificate;
import DataAccess.Entity.Certificate;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
/**
 *
 * @author Alejandro
 */
@ManagedBean
@ViewScoped
public class CertificateNotificationBean {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void loadUserCertificates(){
        HandleCertificate certificateNotifications = new HandleCertificate();
        certificateNotifications.getUserCertificates();
    }
    
    public void downloadCertificate(Certificate certificate){
        HandleCertificate certificateDownload = new HandleCertificate();
    }
    
}
