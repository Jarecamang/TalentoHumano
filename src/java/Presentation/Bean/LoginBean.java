/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import BusinessLogic.Controller.HandleLogin;
/**
 *
 * @author Alejandro
 */
@ManagedBean
@ViewScoped
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
    
    public void login(){   
        HandleLogin login = new HandleLogin();
        message = login.doLogin(user, password);
    }
    
}
