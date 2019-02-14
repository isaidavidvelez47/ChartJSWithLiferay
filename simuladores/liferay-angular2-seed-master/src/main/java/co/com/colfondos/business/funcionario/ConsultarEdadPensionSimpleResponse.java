
package co.com.colfondos.business.funcionario;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarEdadPensionSimpleResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarEdadPensionSimpleResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://funcionario.business.colfondos.com.co/}consultarEdadPensionSimpleRequest">
 *       &lt;sequence>
 *         &lt;element name="controlResponse" type="{http://funcionario.business.colfondos.com.co/}consultarEdadPensionControlResponse"/>
 *         &lt;element name="edades" type="{http://funcionario.business.colfondos.com.co/}edades" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarEdadPensionSimpleResponse", propOrder = {
    "controlResponse",
    "edades"
})
public class ConsultarEdadPensionSimpleResponse
    extends ConsultarEdadPensionSimpleRequest
{

    @XmlElement(required = true)
    protected ConsultarEdadPensionControlResponse controlResponse;
    protected List<Edades> edades;

    /**
     * Obtiene el valor de la propiedad controlResponse.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarEdadPensionControlResponse }
     *     
     */
    public ConsultarEdadPensionControlResponse getControlResponse() {
        return controlResponse;
    }

    /**
     * Define el valor de la propiedad controlResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarEdadPensionControlResponse }
     *     
     */
    public void setControlResponse(ConsultarEdadPensionControlResponse value) {
        this.controlResponse = value;
    }

    /**
     * Gets the value of the edades property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the edades property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEdades().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Edades }
     * 
     * 
     */
    public List<Edades> getEdades() {
        if (edades == null) {
            edades = new ArrayList<Edades>();
        }
        return this.edades;
    }

}
