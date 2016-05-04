
package Presentation.Bean;

import BusinessLogic.Controller.HandleInterestArea;
import java.util.List;
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
        message = ha.addInterestAreaBean(nameInteresArea,username);
    }
    
    public List<String> getInterestAreas(String username){
        HandleInterestArea ha = new HandleInterestArea();
        return ha.getListOfInterestAreas(username);
    }
    
}
