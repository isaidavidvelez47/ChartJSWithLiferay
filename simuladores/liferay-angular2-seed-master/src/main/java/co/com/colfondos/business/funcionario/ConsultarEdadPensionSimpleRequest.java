
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarEdadPensionSimpleRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarEdadPensionSimpleRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://funcionario.business.colfondos.com.co/}baseRequest">
 *       &lt;sequence>
 *         &lt;element name="control" type="{http://funcionario.business.colfondos.com.co/}consultarEdadPensionControlRequest"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarEdadPensionSimpleRequest", propOrder = {
    "control"
})
@XmlSeeAlso({
    ConsultarEdadPensionSimpleResponse.class
})
public class ConsultarEdadPensionSimpleRequest
    extends BaseRequest
{

    @XmlElement(required = true)
    protected ConsultarEdadPensionControlRequest control;

    /**
     * Obtiene el valor de la propiedad control.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarEdadPensionControlRequest }
     *     
     */
    public ConsultarEdadPensionControlRequest getControl() {
        return control;
    }

    /**
     * Define el valor de la propiedad control.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarEdadPensionControlRequest }
     *     
     */
    public void setControl(ConsultarEdadPensionControlRequest value) {
        this.control = value;
    }

}
