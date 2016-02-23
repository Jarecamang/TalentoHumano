/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alejandro
 */
public class HandleLogin {
    
    public String doLogin(String user, String password){
        
        UserDAO userDAO = new UserDAO();
        User userObject = userDAO.searchByUsername(user);
        
        if (userObject != null) {
            if(!userObject.getPassword().equals(password)){
                return "La contraseña digitada NO es la de el usuario " + userObject.getName();
            }else{
                //Saltar 
                String rol = userObject.getFkroleID().getName();
                String userSession = rol + "-" + userObject.getName() + "-" + userObject.getLastname();
                //return new ModelAndView("redirect:/home");
                try {
                    FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "home.xhtml");
                } catch (Exception e) {
                    System.out.println("Error en redirect");
                }
                return "Logueando...";
            }
        } else {
            return "El usuario no existe";
        }        
    }
}
