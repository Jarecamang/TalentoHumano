package Presentation.Bean;

import BusinessLogic.Controller.HandleUser;
import DataAccess.DAO.UserDAO;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author Edwin
 */
@ManagedBean
@RequestScoped
public class UserLevelTrainingBean {

    private int technical;
    private int technologist;
    private int undergraduate;
    private int specialist;
    private int magister;
    private int doctor;
    private int phd;
    @EJB
    private UserDAO userDAO;

    public UserLevelTrainingBean() {

    }

    public UserLevelTrainingBean(int technical, int technologist, int undergraduate, int specialist, int magister, int doctor, int phd
    ) {
        this.technical = technical;
        this.technologist = technologist;
        this.undergraduate = undergraduate;
        this.specialist = specialist;
        this.magister = magister;
        this.doctor = doctor;
        this.phd = phd;
    }

    public int getTechnical() {
        return technical;
    }

    public int getTechnologist() {
        return technologist;
    }

    public int getUndergraduate() {
        return undergraduate;
    }

    public int getSpecialist() {
        return specialist;
    }

    public int getMagister() {
        return magister;
    }

    public int getDoctor() {
        return doctor;
    }

    public int getPhd() {
        return phd;
    }

    public UserLevelTrainingBean get_training_levels() {
        HandleUser obj = new HandleUser();
        return obj.get_training_levels(userDAO);
    }

}
