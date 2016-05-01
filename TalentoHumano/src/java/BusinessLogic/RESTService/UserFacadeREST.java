/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.RESTService;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Edwin
 */
@Stateless
@Path("dataaccess.entity.user")
public class UserFacadeREST extends AbstractFacade<User> {

    public UserFacadeREST() {
        super(User.class);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public String training() {
        ArrayList<String> traininglist = new ArrayList<>();
        UserDAO usDAO = new UserDAO();
        List<User> userlist = usDAO.getAllUsers();
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
        Date today = new Date();
        traininglist.add("Talento Humano S.A");
        traininglist.add("Curso Basico de Excel");
        for (User us : userlist) {
            if (((today.getTime() - us.getContract().getStartDate().getTime()) / MILLSECS_PER_DAY) <= 60) {
                traininglist.add(us.getName() + " " + us.getLastname());
            }
        }
        return traininglist.toString();
    }

    @GET
    @Produces({MediaType.TEXT_XML})
    public String trainingXML() {
        String ret = "<Reporte><Empresa>Talento Humano S.A.</Empresa>";
        ret = ret.concat("<Capacitacion>Curso Basico de Excel</Capacitacion>");
        UserDAO usDAO = new UserDAO();
        List<User> userlist = usDAO.getAllUsers();
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
        Date today = new Date();
        for (User us : userlist) {
            if (((today.getTime() - us.getContract().getStartDate().getTime()) / MILLSECS_PER_DAY) <= 60) {
                ret = ret.concat("<Empleado><Nombre>" + us.getName() + "</Nombre><Apellido>" + us.getLastname() + "</Apellido></Empleado>");
            }
        }
        return ret.concat("</Reporte>");
    }

}
