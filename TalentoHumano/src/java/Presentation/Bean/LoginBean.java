/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import javax.faces.bean.ManagedBean;
import BusinessLogic.Controller.HandleLogin;
import BusinessLogic.Controller.HandleNotifications;
import BusinessLogic.Controller.HandlePosition;
import BusinessLogic.Controller.HandleUser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alejandro
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    String user;
    String password;
    String message;

    public LoginBean() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String userName) {
        this.user = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordUser) {
        this.password = passwordUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void login() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HandleLogin login = new HandleLogin();  
        message = login.doLogin(user, password);        
        if (message.charAt(0) == 'A') {//solo admin
            HandlePosition positionObject = new HandlePosition();
            positionObject.getPositions();                        
        }
        if (message.charAt(0) != 'E') {//ambos
            HandlePosition hanp = new HandlePosition();
            hanp.getPositions();
            HandleLogin hl = new HandleLogin();
            HandleUser hu = new HandleUser();
            hu.uploadPersonalData(user);            
        }
        HandleNotifications hn = new HandleNotifications();
        hn.getNotifications();
        ec.getSessionMap().put("state", true);
    }
    
    public void redirect_if_logged() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();        
        Boolean test = (Boolean)ec.getSessionMap().get("state");
        if(test != null && test) {
            String role = (String) ec.getSessionMap().get("role_check");
            try {
            if(role.equals("admin"))
                ec.redirect("/TalentoHumano/faces/administration/adminPanel.xhtml");
            else
                ec.redirect("/TalentoHumano/faces/empleado/empleadoPanel.xhtml");
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                ec.redirect("/TalentoHumano/faces/index.xhtml");
                System.out.println("redirected to index");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void verify_role(String supposed_role) {
        String[] page = {"administration/adminPanel.xhtml", "empleado/empleadoPanel.xhtml"};        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        boolean admin = supposed_role.equals("admin")? true:false;
        String role = (String)ec.getSessionMap().get("role_check");
        System.out.printf("role %s\n", role);    
        if(role == null)
            return;
        if(!role.equals(supposed_role)) {
            try {
                ec.redirect("/TalentoHumano/faces/"+(admin?page[1]:page[0]));                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }                
    }   
    
    public void verify() {
        System.out.println("in verify");
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();        
        Boolean test = (Boolean)ec.getSessionMap().get("state");
        if(test == null || !test) {
            redirect_if_logged();            
        }
                   
    }
    
    public void logout() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getSessionMap().put("state", false);
        ec.getSessionMap().put("role_check", null);
        HandleLogin login = new HandleLogin();
        login.doLogout();
        System.out.println("logged out");
    }

    public void back(String username) {
        HandleLogin hl = new HandleLogin();
        HandleUser hu = new HandleUser();
        hu.back(username);
    }
}
