/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alejandro
 */
public class HandleLogin {
    
    LDAPConnector con=new LDAPConnector();

    public String doLogin(String user, String password) {
        if (con.validarContrasena(user, password)) {
            try {
                String role = con.searchRole(user);
                if (role.equals("")) {
                    return "Error al obtener Perfil de Usuario.";
                }
                UserDAO userDAO = new UserDAO();
                User userObject = userDAO.searchByUsername(user);
                if (userObject != null) {
                    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    ec.getSessionMap().put("user", userObject.getName() + " " + userObject.getLastname());
                    ec.getSessionMap().put("role", role);
                    ec.getSessionMap().put("userId", userObject.getPkID());
                    String url;
                    if (role.equals("Administrator")) {
                        url = ec.encodeActionURL(
                                FacesContext.getCurrentInstance().getApplication().getViewHandler().getActionURL(FacesContext.getCurrentInstance(), "/administration/adminPanel.xhtml"));
                        ec.redirect(url);
                        return "A";
                    } else {
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
                return "Error al cargar la aplicación.";
            }
        } else {
            return "El usuario y la contraseña no corresponden";
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
