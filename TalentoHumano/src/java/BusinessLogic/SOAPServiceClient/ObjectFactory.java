
package BusinessLogic.SOAPServiceClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the BusinessLogic.SOAPServiceClient package. 
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

    private final static QName _UsuariosACapacitar_QNAME = new QName("http://SOAPService.BusinessLogic/", "UsuariosACapacitar");
    private final static QName _UsuariosACapacitarResponse_QNAME = new QName("http://SOAPService.BusinessLogic/", "UsuariosACapacitarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: BusinessLogic.SOAPServiceClient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UsuariosACapacitar }
     * 
     */
    public UsuariosACapacitar createUsuariosACapacitar() {
        return new UsuariosACapacitar();
    }

    /**
     * Create an instance of {@link UsuariosACapacitarResponse }
     * 
     */
    public UsuariosACapacitarResponse createUsuariosACapacitarResponse() {
        return new UsuariosACapacitarResponse();
    }

    /**
     * Create an instance of {@link RobWS }
     * 
     */
    public RobWS createRobWS() {
        return new RobWS();
    }

    /**
     * Create an instance of {@link Course }
     * 
     */
    public Course createCourse() {
        return new Course();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuariosACapacitar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAPService.BusinessLogic/", name = "UsuariosACapacitar")
    public JAXBElement<UsuariosACapacitar> createUsuariosACapacitar(UsuariosACapacitar value) {
        return new JAXBElement<UsuariosACapacitar>(_UsuariosACapacitar_QNAME, UsuariosACapacitar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuariosACapacitarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAPService.BusinessLogic/", name = "UsuariosACapacitarResponse")
    public JAXBElement<UsuariosACapacitarResponse> createUsuariosACapacitarResponse(UsuariosACapacitarResponse value) {
        return new JAXBElement<UsuariosACapacitarResponse>(_UsuariosACapacitarResponse_QNAME, UsuariosACapacitarResponse.class, null, value);
    }

}
