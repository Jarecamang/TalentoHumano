/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleCertification;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edwin
 */
@ManagedBean
@ViewScoped
public class CreateCertificationBean {
    
    private String name;
    private String message;

    public CreateCertificationBean() {
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }    
    
    public void createCertification(){
        HandleCertification hc = new HandleCertification();
        message = hc.createCertification(name);
    }
    
}
