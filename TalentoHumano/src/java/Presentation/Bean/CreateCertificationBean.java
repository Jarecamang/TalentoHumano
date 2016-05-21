/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleCertification;
import DataAccess.DAO.CertificationDAO;
import DataAccess.DAO.InterestAreaDAO;
import DataAccess.DAO.NotificationDAO;
import DataAccess.DAO.UserDAO;
import javax.ejb.EJB;
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
    @EJB
    private UserDAO userDAO;
    @EJB
    private CertificationDAO ceDAO;
    @EJB
    private InterestAreaDAO areaDAO;
    @EJB
    private NotificationDAO notiDAO; 
    
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
        message = hc.createCertification(ceDAO,userDAO,areaDAO,notiDAO,name);
    }
    
}
