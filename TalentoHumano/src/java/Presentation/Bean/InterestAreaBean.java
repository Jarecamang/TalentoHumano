
package Presentation.Bean;

import BusinessLogic.Controller.HandleInterestArea;
import DataAccess.DAO.InterestAreaDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Areaofinterest;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edwin
 */
@ManagedBean
@ViewScoped
public class InterestAreaBean {

    private String nameInteresArea;
    private String message;
    @EJB
    private UserDAO userDAO;
    @EJB
    private InterestAreaDAO interestAreaDAO;

    public InterestAreaBean() {
    }

    public String getNameInteresArea() {
        return nameInteresArea;
    }

    public String getMessage() {
        return message;
    }

    public void setNameInteresArea(String nameInteresArea) {
        this.nameInteresArea = nameInteresArea;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void addInterestArea(String username){
        HandleInterestArea ha = new HandleInterestArea();
        message = ha.addInterestAreaBean(userDAO,interestAreaDAO,nameInteresArea,username);
    }
    
    public List<String> getInterestAreas(String username){
        HandleInterestArea ha = new HandleInterestArea();
        return ha.getListOfInterestAreas(userDAO,interestAreaDAO,username);
    }
    
    public List<Areaofinterest> getAllInterestAreas(){
        HandleInterestArea ha = new HandleInterestArea();
        return ha.getAllInterestAreas(interestAreaDAO);
    }
    
}
