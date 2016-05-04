/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.SOAPService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author PCMiguel
 */
@WebService(serviceName = "StartESBService")
public class StartESBService {

    @WebMethod(operationName = "createEvent")
    public ROB_ESB CreateEvent(@WebParam(name = "EventName") String evento, @WebParam(name = "MonthFilter") int month) {
        try {
            return new ROB_ESB(true, "Prueba Exitosa", "test");
        } catch (Exception e) {
            return new ROB_ESB(false, "Error: ESB Service - Recursos Humanos", null);
        }
    }
}
