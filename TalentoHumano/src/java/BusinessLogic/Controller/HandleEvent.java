/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import BusinessLogic.ServiceClient.Rob;

/**
 *
 * @author Edwin
 */
public class HandleEvent {

    public String doEvent(String name, String filter) {
        try {
            Rob rob = usuariosACapacitar(name, Integer.parseInt(filter));
            return rob.getData().toString();
        } catch (Exception e) {
            return "Error General: Servicio";
        }
    }

    private static Rob usuariosACapacitar(java.lang.String eventName, int monthFilter) {
        BusinessLogic.ServiceClient.UsersTraining_Service service = new BusinessLogic.ServiceClient.UsersTraining_Service();
        BusinessLogic.ServiceClient.UsersTraining port = service.getUsersTrainingPort();
        return port.usuariosACapacitar(eventName, monthFilter);
    }
}
