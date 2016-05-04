
package BusinessLogic.ESBServiceClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the BusinessLogic.ESBServiceClient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateEvent_QNAME = new QName("http://SOAPService.BusinessLogic/", "createEvent");
    private final static QName _CreateEventResponse_QNAME = new QName("http://SOAPService.BusinessLogic/", "createEventResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: BusinessLogic.ESBServiceClient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateEvent }
     * 
     */
    public CreateEvent createCreateEvent() {
        return new CreateEvent();
    }

    /**
     * Create an instance of {@link CreateEventResponse }
     * 
     */
    public CreateEventResponse createCreateEventResponse() {
        return new CreateEventResponse();
    }

    /**
     * Create an instance of {@link RobESB }
     * 
     */
    public RobESB createRobESB() {
        return new RobESB();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAPService.BusinessLogic/", name = "createEvent")
    public JAXBElement<CreateEvent> createCreateEvent(CreateEvent value) {
        return new JAXBElement<CreateEvent>(_CreateEvent_QNAME, CreateEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateEventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAPService.BusinessLogic/", name = "createEventResponse")
    public JAXBElement<CreateEventResponse> createCreateEventResponse(CreateEventResponse value) {
        return new JAXBElement<CreateEventResponse>(_CreateEventResponse_QNAME, CreateEventResponse.class, null, value);
    }

}
