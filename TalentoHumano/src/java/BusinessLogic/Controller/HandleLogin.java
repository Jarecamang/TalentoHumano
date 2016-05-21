/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alejandro
 */
public class HandleLogin {
    
    LDAPConnector con = new LDAPConnector();

    public String doLogin(UserDAO userDAO, String user, String password) {
        if (con.validarContrasena(user, password)) {
            try {
                String role = con.searchRole(user);
                if (role.equals("")) {
                    return "Error al obtener Perfil de Usuario.";
                }

                User userObject = userDAO.searchByUsername(user);
                if (userObject != null) {
                    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    ec.getSessionMap().put("user", userObject.getName() + " " + userObject.getLastname());
                    ec.getSessionMap().put("role", role);
                    ec.getSessionMap().put("userId", userObject.getPkID());
                    String url = "";
                    if (ec.getSessionMap().get("role").equals("Administrator")) {
                        ec.getSessionMap().put("role_check", "admin");
                        url = ec.encodeActionURL(
                                FacesContext.getCurrentInstance().getApplication().getViewHandler().getActionURL(FacesContext.getCurrentInstance(), "/administration/adminPanel.xhtml"));
                        ec.redirect(url);
                        return "A";
                    } else {
                        ec.getSessionMap().put("role_check", "employee");
                        url = ec.encodeActionURL(
                                FacesContext.getCurrentInstance().getApplication().getViewHandler().getActionURL(FacesContext.getCurrentInstance(), "/empleado/empleadoPanel.xhtml"));
                        ec.redirect(url);
                        return "Logueando...";
                    }
                } else {
                    return "Error: El usuario no existe";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return "Error al cargar la aplicaciÃ³n.";
            }
        } else {
            return "El usuario y la contraseÃ±a no corresponden";
        }

    }

    public void doLogout() {
        try {
            con.desconectar();
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext extContext = context.getExternalContext();
            extContext.getSessionMap().remove("user");
            extContext.getSessionMap().remove("role");
            extContext.getSessionMap().clear();
            extContext.redirect(extContext.getRequestContextPath());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
