package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.DAO.ContractDAO;
import DataAccess.Entity.Contract;
import DataAccess.Entity.User;
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

    LDAPConnector con = new LDAPConnector();

    public String doCreate(String name, String lastname, Date dateBorn, String address, String trainingLevel, String phone, String email, String password1, String password2, String role, String identifyCard) {
        try {
            System.out.println(new Date());
            User user = new User();
            user.setName(name);
            user.setLastname(lastname);
            user.setAddress(address);
            user.setLevelTraining(trainingLevel);
            user.setPhone(phone);
            user.setDateBorn(dateBorn);
            user.setEmail(email);
            user.setUsername(name + " " + lastname);
            user.setIdentifyCard(identifyCard);
            if (!password1.equals(password2)) {
                return "Las contraseñas no coinciden";
            }
            UserDAO userDAO = new UserDAO();

            if (userDAO.searchByDoccument(Long.parseLong(identifyCard)) != null) {
                return "El documento de identidad ya existe";
            }
            if (userDAO.searchByUsername(user.getUsername()) != null) {
                return "El username ya existe";
            }
            if (con.registrar(identifyCard, name, lastname, password2, role.equals("1") ? "601" : "600")) {
                User userObject = userDAO.persist(user);
                if (userObject != null) {
                    return "Usuario creado con username " + userObject.getUsername() + "." + "/" + userObject.getPkID();
                } else {
                    return "El usuario no pudo ser creado.";
                }
            } else {
                return "Error: LDAP";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear Usuario.";
        }
    }

    public UserLevelTrainingBean get_training_levels() {

        UserDAO userDAO = new UserDAO();
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

    public UserSalaryBean getSalaries() {
        ContractDAO cDAO = new ContractDAO();
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

    public void uploadPersonalData(String username) {
        UserDAO userDAO = new UserDAO();
        User userObject = userDAO.searchByUsername(username);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getSessionMap().put("userData", userObject);
        ContractDAO contrDAO = new ContractDAO();
        Contract contractObject = contrDAO.getUserContract(new User(userObject.getPkID()));
        ec.getSessionMap().put("userContract", contractObject);
    }

    public void back(String username) {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String url = "";
            if (con.searchRole(username).equals("Administrator")) {
                url = ec.encodeActionURL(
                        FacesContext.getCurrentInstance().getApplication().getViewHandler().getActionURL(FacesContext.getCurrentInstance(), "/administration/adminPanel.xhtml"));
            } else {
                url = ec.encodeActionURL(
                        FacesContext.getCurrentInstance().getApplication().getViewHandler().getActionURL(FacesContext.getCurrentInstance(), "/empleado/empleadoPanel.xhtml"));
            }
            ec.redirect(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
