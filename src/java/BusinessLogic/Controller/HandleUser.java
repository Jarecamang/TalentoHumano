package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import DataAccess.Entity.Role;
import Presentation.Bean.UserLevelTrainingBean;

/**
 *
 * @author Alejandro
 */


public class HandleUser {

    public String doCreate(String name, String lastname, int age, String address, String trainingLevel, String phone, String email, String username, String password1, String password2, String role, String identifyCard) {

        User user = new User();
        Role roleObject = new Role(Integer.parseInt(role));
        user.setName(name);
        user.setLastname(lastname);
        user.setAddress(address);
        user.setLevelTraining(trainingLevel);
        user.setPhone(phone);
        user.setAge(age);
        user.setEmail(email);
        user.setUsername(username);
        user.setFkroleID(roleObject);
        user.setIdentifyCard(identifyCard);
        if (!password1.equals(password2)) {
            return "Las contraseñas no coinciden";
        } else {
            user.setPassword(password1);
        }
        UserDAO userDAO = new UserDAO();
        User userObject = userDAO.persist(user);
        if (userObject != null) {
            return "El usuario ha sido creado con username " + userObject.getUsername() + "." + "/" + userObject.getPkID();
        } else {
            return "El usuario no pudo ser creado.";
        }
    }

    public UserLevelTrainingBean get_training_levels() {

        UserDAO userDAO = new UserDAO();
        UserLevelTrainingBean ult = new UserLevelTrainingBean(
                userDAO.getAmountOf("Tecnico"),
                userDAO.getAmountOf("Tecnologo"),
                userDAO.getAmountOf("Especialista"),
                userDAO.getAmountOf("Magister"),
                userDAO.getAmountOf("Doctor"),
                userDAO.getAmountOf("Phd")
        );
        return ult;
    }
}
