/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import javax.faces.bean.ManagedBean;
import BusinessLogic.Controller.HandleLogin;
import javax.faces.bean.RequestScoped;

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
        HandleLogin login = new HandleLogin();
        message = login.doLogin(user, password);
        if(message.charAt(0)=='L'){
            DataBean datos = new DataBean();
            datos.populateData();
        }
    }

    public void logout() {
        HandleLogin login = new HandleLogin();
        login.doLogout();
    }
}
