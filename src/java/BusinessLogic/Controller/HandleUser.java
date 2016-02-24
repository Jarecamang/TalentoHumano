/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import DataAccess.Entity.Role;
import javax.faces.context.FacesContext;
/**
 *
 * @author Alejandro
 */
public class HandleUser {

    public String doCreate(String name, String lastname, int age, String address, long phone, String email, String username, String password1, String password2, String role) {
        User user = new User();
        Role roleObject = new Role(Integer.parseInt(role));
        user.setName(name);
        user.setLastname(lastname);
        user.setAddress(address);
        user.setPhone(phone);
        user.setEmail(email);
        user.setUsername(username);
        user.setFkroleID(roleObject);
        if(!password1.equals(password2)){
            return "Las contraseñas no coinciden";
        }else{
            user.setPassword(password1);
        }     
        UserDAO userDAO = new UserDAO();
        User userObject = userDAO.persist(user);
        if (userObject != null) {
            return "El usuario ha sido creado con username " + userObject.getUsername()+ ".";
        } else {
            return "El usuario no pudo ser creado.";
        }        
    }
    
}
