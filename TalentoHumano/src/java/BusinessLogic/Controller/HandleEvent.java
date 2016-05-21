/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import BusinessLogic.ESBServiceClient.RobESB;

/**
 *
 * @author Edwin
 */
public class HandleEvent {

    public String doEvent(String name, String filter) {
        try {
            //TODO: ESB Service Call
            //Testing Part:
            RobESB robESB = createEvent(name, Integer.parseInt(filter));
            return robESB.getErrMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error General: Servicio";
        }
    }

    private static RobESB createEvent(java.lang.String eventName, int monthFilter) {
        BusinessLogic.ESBServiceClient.StartESBService_Service service = new BusinessLogic.ESBServiceClient.StartESBService_Service();
        BusinessLogic.ESBServiceClient.StartESBService port = service.getStartESBServicePort();
        return port.createEvent(eventName, monthFilter);
    }
}
