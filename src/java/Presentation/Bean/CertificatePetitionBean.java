/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import BusinessLogic.Controller.HandleCertificate;
import DataAccess.Entity.Certificate;
/**
 *
 * @author Alejandro
 */
@ManagedBean
@ViewScoped
public class CertificatePetitionBean {
    
    String certificateType;
    String message;
    String certificateDescription;

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCertificateDescription() {
        return certificateDescription;
    }

    public void setCertificateDescription(String certificateDescription) {
        this.certificateDescription = certificateDescription;
    }
    
    public void createPetition(){
        
        HandleCertificate certificatePetition = new HandleCertificate();
        message = certificatePetition.doPetition(certificateType,certificateDescription);
        
    }
    
}
