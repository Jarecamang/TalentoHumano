package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.DAO.ContractDAO;
import DataAccess.Entity.Contract;
import DataAccess.Entity.User;
import DataAccess.Entity.Role;
import Presentation.Bean.UserLevelTrainingBean;
import Presentation.Bean.UserSalaryBean;
import java.io.IOException;
import java.util.Date;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alejandro
 */
public class HandleUser {

    public String doCreate(UserDAO userDAO, String name, String lastname, Date dateBorn, String address, String trainingLevel, String phone, String email, String username, String password1, String password2, String role, String identifyCard) {
        System.out.println(new Date());
        User user = new User();
        Role roleObject = new Role(Integer.parseInt(role));
        user.setName(name);
        user.setLastname(lastname);
        user.setAddress(address);
        user.setLevelTraining(trainingLevel);
        user.setPhone(phone);
        user.setDateBorn(dateBorn);
        user.setEmail(email);
        user.setUsername(username);
        user.setFkroleID(roleObject);
        user.setIdentifyCard(identifyCard);
        if (!password1.equals(password2)) {
            return "Las contraseñas no coinciden";
        } else {
            user.setPassword(password1);
        }

        if (userDAO.searchByDoccument(Long.parseLong(user.getIdentifyCard())) != null) {
            return "El documento de identidad ya existe";
        }
        if (userDAO.searchByUsername(user.getUsername()) != null) {
            return "El username ya existe";
        }

        User userObject = userDAO.persist(user);
        if (userObject != null) {
            return "Usuario creado con username " + userObject.getUsername() + "." + "/" + userObject.getPkID();
        } else {
            return "El usuario no pudo ser creado.";
        }
    }

    public UserLevelTrainingBean get_training_levels(UserDAO userDAO) {

        UserLevelTrainingBean ult = new UserLevelTrainingBean(
                userDAO.getAmountOf("Tecnico"),
                userDAO.getAmountOf("Tecnologo"),
                userDAO.getAmountOf("Pregrado"),
                userDAO.getAmountOf("Especialista"),
                userDAO.getAmountOf("Magister"),
                userDAO.getAmountOf("Doctor"),
                userDAO.getAmountOf("Phd")
        );
        return ult;
    }

    public UserSalaryBean getSalaries(ContractDAO cDAO) {
        UserSalaryBean us = new UserSalaryBean(
                cDAO.getAmountOfSalariesSmallerThan(1000000),
                cDAO.getAmountOfSalariesBetween(1000000, 2000000),
                cDAO.getAmountOfSalariesBetween(2000000, 3000000),
                cDAO.getAmountOfSalariesBetween(3000000, 4000000),
                cDAO.getAmountOfSalariesBetween(4000000, 5000000),
                cDAO.getAmountOfSalariesBiggerThan(5000000)
        );
        return us;
    }

    public void uploadPersonalData(ContractDAO contrDAO, UserDAO userDAO, String username) {
        User userObject = userDAO.searchByUsername(username);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getSessionMap().put("userData", userObject);
        Contract contractObject = contrDAO.getUserContract(new User(userObject.getPkID()));
        ec.getSessionMap().put("userContract", contractObject);
    }

    public void back(UserDAO userDAO, String username) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String url;
        User userObject = userDAO.searchByUsername(username);
        if (userObject.getFkroleID().getName().equals("Administrator")) {
            url = ec.encodeActionURL(
                    FacesContext.getCurrentInstance().getApplication().getViewHandler().getActionURL(FacesContext.getCurrentInstance(), "/administration/adminPanel.xhtml"));
        } else {
            url = ec.encodeActionURL(
                    FacesContext.getCurrentInstance().getApplication().getViewHandler().getActionURL(FacesContext.getCurrentInstance(), "/empleado/empleadoPanel.xhtml"));
        }
        try {
            ec.redirect(url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
