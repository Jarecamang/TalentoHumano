/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

//import BusinessLogic.ESBServiceClient.RobESB;
/**
 *
 * @author Edwin
 */
public class HandleEvent {

    public String doEvent(String name, String filter) {
        try {
            try {
                Integer.parseInt(filter);
            } catch (Exception e) {
                return "Error: Filtro Inválido";
            }
            try {
                if ( name.equals("") ){
                    return "El nombre del evento no puede quedar vacio";
                }
                else{
                    Integer.parseInt(name);
                    return "Error: Nombre Inválido";
                }
            } catch (Exception e) {
                //TODO: ESB Service Call
                //Testing Part:
                //RobESB robESB = createEvent(name, Integer.parseInt(filter));
                return "Test";//robESB.getErrMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error General: Servicio";
        }
    }
}
