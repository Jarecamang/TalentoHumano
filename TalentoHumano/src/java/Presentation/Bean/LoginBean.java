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
import DataAccess.DAO.ContractDAO;
import DataAccess.DAO.NotificationDAO;
import DataAccess.DAO.PositionDAO;
import DataAccess.DAO.UserDAO;
import javax.ejb.EJB;
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
    @EJB
    private UserDAO userDAO;
    @EJB
    private ContractDAO contractDAO;
    @EJB
    private PositionDAO positionDAO;
    @EJB
    private NotificationDAO notiDAO;

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
        message = login.doLogin(userDAO, user, password);
        if (message.charAt(0) == 'A') {//solo admin
            HandlePosition positionObject = new HandlePosition();
            positionObject.getPositions(positionDAO);
        }
        if (message.charAt(0) != 'E') {//ambos
            HandlePosition hanp = new HandlePosition();
            hanp.getPositions(positionDAO);
            HandleUser hu = new HandleUser();
            hu.uploadPersonalData(contractDAO, userDAO, user);
        }
        HandleNotifications hn = new HandleNotifications();
        hn.getNotifications(notiDAO); 
    }

    public void logout() {
        HandleLogin login = new HandleLogin();
        login.doLogout();
    }

    public void back(String username) {
        HandleLogin hl = new HandleLogin();
        HandleUser hu = new HandleUser();
        hu.back(userDAO, username);
    }
}
