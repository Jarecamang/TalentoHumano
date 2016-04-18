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

/**
 *
 * @author Edwin
 */
@WebService(serviceName = "UsersTraining")
public class UsersTraining {

    /**
     * This is a sample web service operation
     * @return 
     */  
    @WebMethod(operationName = "UsuariosACapacitar")
    public ArrayList<String> UsuariosACapacitar() {
        ArrayList<String> training = new ArrayList<>();
        UserDAO usDAO = new UserDAO();
        List<User> userlist = usDAO.getAllUsers();
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
        Date today = new Date();
        training.add("Talento Humano S.A");
        training.add("Curso Basico de Excel");
        for (User us : userlist){
            if( ((today.getTime()-us.getContract().getStartDate().getTime())/MILLSECS_PER_DAY) <= 60 )
                training.add(us.getName()+" "+us.getLastname());
        }
        return training;
    }
}
