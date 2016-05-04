/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.SOAPService;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Edwin
 */
@WebService(serviceName = "UsersTraining")
public class UsersTraining {

    /**
     * This is a sample web service operation
     *
     * @return
     */
    @WebMethod(operationName = "UsuariosACapacitar")
    public ROB_WS UsuariosACapacitar(@WebParam(name = "EventName") String evento, @WebParam(name = "MonthFilter") int month) {
        try {
            List<BusinessLogic.SOAPService.User> training = new ArrayList<>();
            UserDAO usDAO = new UserDAO();
            List<DataAccess.Entity.User> userlist = usDAO.getAllUsers();
            final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
            Date today = new Date();
            for (DataAccess.Entity.User us : userlist) {
                if (((today.getTime() - us.getContract().getStartDate().getTime()) / MILLSECS_PER_DAY) <= month * 30) {
                    training.add(new BusinessLogic.SOAPService.User(Integer.parseInt(us.getIdentifyCard()), us.getName(), us.getLastname()));
                }
            }
            return new ROB_WS(true, "Transaccion Exitosa", new Course(training, 20162016, evento));
        } catch (Exception e) {
            e.printStackTrace();
            return new ROB_WS(false, "Error En Servicio", null);
        }
    }
}
