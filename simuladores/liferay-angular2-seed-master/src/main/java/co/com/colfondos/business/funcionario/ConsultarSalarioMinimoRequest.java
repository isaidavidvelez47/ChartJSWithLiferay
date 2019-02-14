
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarSalarioMinimoRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarSalarioMinimoRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://funcionario.business.colfondos.com.co/}baseRequest">
 *       &lt;sequence>
 *         &lt;element name="control" type="{http://funcionario.business.colfondos.com.co/}consultarSalarioMinimoControlRequest"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarSalarioMinimoRequest", propOrder = {
    "control"
})
@XmlSeeAlso({
    SalariosMinimos.class
})
public class ConsultarSalarioMinimoRequest
    extends BaseRequest
{

    @XmlElement(required = true)
    protected ConsultarSalarioMinimoControlRequest control;

    /**
     * Obtiene el valor de la propiedad control.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarSalarioMinimoControlRequest }
     *     
     */
    public ConsultarSalarioMinimoControlRequest getControl() {
        return control;
    }

    /**
     * Define el valor de la propiedad control.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarSalarioMinimoControlRequest }
     *     
     */
    public void setControl(ConsultarSalarioMinimoControlRequest value) {
        this.control = value;
    }

}
