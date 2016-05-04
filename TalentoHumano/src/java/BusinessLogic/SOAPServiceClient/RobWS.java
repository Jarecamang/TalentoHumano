
package BusinessLogic.SOAPServiceClient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para robWS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="robWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="data" type="{http://SOAPService.BusinessLogic/}course" minOccurs="0"/&gt;
 *         &lt;element name="err_message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "robWS", propOrder = {
    "data",
    "errMessage",
    "success"
})
public class RobWS {

    protected Course data;
    @XmlElement(name = "err_message")
    protected String errMessage;
    protected boolean success;

    /**
     * Obtiene el valor de la propiedad data.
     * 
     * @return
     *     possible object is
     *     {@link Course }
     *     
     */
    public Course getData() {
        return data;
    }

    /**
     * Define el valor de la propiedad data.
     * 
     * @param value
     *     allowed object is
     *     {@link Course }
     *     
     */
    public void setData(Course value) {
        this.data = value;
    }

    /**
     * Obtiene el valor de la propiedad errMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrMessage() {
        return errMessage;
    }

    /**
     * Define el valor de la propiedad errMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrMessage(String value) {
        this.errMessage = value;
    }

    /**
     * Obtiene el valor de la propiedad success.
     * 
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Define el valor de la propiedad success.
     * 
     */
    public void setSuccess(boolean value) {
        this.success = value;
    }

}
