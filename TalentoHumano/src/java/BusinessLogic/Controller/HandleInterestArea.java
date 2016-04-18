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

    public String addInterestAreaBean(String name, String username) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchByUsername(username);
        Areaofinterest area = new Areaofinterest();
        InterestAreaDAO areaDAO = new InterestAreaDAO();
        area.setName(name);
        area.getUserSet().add(user);
        Areaofinterest areaObject = areaDAO.persist(area);
        if (areaObject != null) {
            return "El Area de interes ha sido creada con nombre " + areaObject.getName();
        } else {
            return "El Area no pudo ser creada.";
        }
    }

    public List<String> getListOfInterestAreas(String username) {
        InterestAreaDAO areaDAO = new InterestAreaDAO();
        List<Areaofinterest> areaslist = areaDAO.getAllAreasOfInterest();
        List<String> userAreas = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchByUsername(username);
        for (Areaofinterest area : areaslist) {
            if (area.getUserSet().contains(user)) {
                userAreas.add(area.getName());
            }
        }
        return userAreas;
    }

}
