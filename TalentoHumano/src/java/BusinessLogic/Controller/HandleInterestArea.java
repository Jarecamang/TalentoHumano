package BusinessLogic.Controller;

import DataAccess.DAO.InterestAreaDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Areaofinterest;
import DataAccess.Entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edwin
 */
public class HandleInterestArea {

    public String addInterestAreaBean(UserDAO userDAO, InterestAreaDAO areaDAO, String nameArea, String username) {
        User user = userDAO.searchByUsername(username);
        List<String> personalAreas = getListOfInterestAreas(userDAO,areaDAO,username);   
        if ( personalAreas.contains(nameArea.toLowerCase()) ){
            return "Usted ya tiene esta area";
        }else{ 
            List<Areaofinterest> systemAreas = getAllInterestAreas(areaDAO);
            Areaofinterest interestArea = areaDAO.searchByName(nameArea.toLowerCase());
            Areaofinterest areaObject = null;
            if ( systemAreas.contains(interestArea) ){
                interestArea.getUserSet().add(user);
                areaObject = areaDAO.update(interestArea);
                if (areaObject != null) {
                    return "El Area de interes ha sido creada con nombre " + areaObject.getName();
                } else {
                    return "El Area no pudo ser creada.";
                }
            }else{
                Areaofinterest area = new Areaofinterest();
                area.setName(nameArea.toLowerCase());
                area.getUserSet().add(user);
                areaObject = areaDAO.persist(area);
                if (areaObject != null) {
                    return "El Area de interes ha sido creada con nombre " + areaObject.getName();
                } else {
                    return "El Area no pudo ser creada.";
                }
            }
        }
    }

    public List<String> getListOfInterestAreas(UserDAO userDAO, InterestAreaDAO areaDAO, String username) {
        List<Areaofinterest> areaslist = areaDAO.getAllAreasOfInterest();
        List<String> userAreas = new ArrayList<>();
        User user = userDAO.searchByUsername(username);
        for (Areaofinterest area : areaslist) {
            if (area.getUserSet().contains(user)) {
                userAreas.add(area.getName());
            } 
        }
        return userAreas;
    } 

    public List<Areaofinterest> getAllInterestAreas(InterestAreaDAO areaDAO){
        List<Areaofinterest> areaslist = areaDAO.getAllAreasOfInterest();
        return areaslist;
    }
    
}
