
package BusinessLogic.ServiceClient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UsersTraining", targetNamespace = "http://SOAPService.BusinessLogic/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UsersTraining {


    /**
     * 
     * @param monthFilter
     * @param eventName
     * @return
     *     returns BusinessLogic.ServiceClient.Rob
     */
    @WebMethod(operationName = "UsuariosACapacitar")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "UsuariosACapacitar", targetNamespace = "http://SOAPService.BusinessLogic/", className = "BusinessLogic.ServiceClient.UsuariosACapacitar")
    @ResponseWrapper(localName = "UsuariosACapacitarResponse", targetNamespace = "http://SOAPService.BusinessLogic/", className = "BusinessLogic.ServiceClient.UsuariosACapacitarResponse")
    @Action(input = "http://SOAPService.BusinessLogic/UsersTraining/UsuariosACapacitarRequest", output = "http://SOAPService.BusinessLogic/UsersTraining/UsuariosACapacitarResponse")
    public Rob usuariosACapacitar(
        @WebParam(name = "EventName", targetNamespace = "")
        String eventName,
        @WebParam(name = "MonthFilter", targetNamespace = "")
        int monthFilter);

}