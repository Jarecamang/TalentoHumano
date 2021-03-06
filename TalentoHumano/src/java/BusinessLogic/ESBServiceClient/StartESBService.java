
package BusinessLogic.ESBServiceClient;

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
@WebService(name = "StartESBService", targetNamespace = "http://SOAPService.BusinessLogic/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface StartESBService {


    /**
     * 
     * @param monthFilter
     * @param eventName
     * @return
     *     returns BusinessLogic.ESBServiceClient.RobESB
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createEvent", targetNamespace = "http://SOAPService.BusinessLogic/", className = "BusinessLogic.ESBServiceClient.CreateEvent")
    @ResponseWrapper(localName = "createEventResponse", targetNamespace = "http://SOAPService.BusinessLogic/", className = "BusinessLogic.ESBServiceClient.CreateEventResponse")
    @Action(input = "http://SOAPService.BusinessLogic/StartESBService/createEventRequest", output = "http://SOAPService.BusinessLogic/StartESBService/createEventResponse")
    public RobESB createEvent(
        @WebParam(name = "EventName", targetNamespace = "")
        String eventName,
        @WebParam(name = "MonthFilter", targetNamespace = "")
        int monthFilter);

}
