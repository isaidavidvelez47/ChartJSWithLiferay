
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarParametrosHerramientasFVSimpleRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarParametrosHerramientasFVSimpleRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://funcionario.business.colfondos.com.co/}baseRequest">
 *       &lt;sequence>
 *         &lt;element name="control" type="{http://funcionario.business.colfondos.com.co/}consultarParametrosHerramientasFVControlRequest"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarParametrosHerramientasFVSimpleRequest", propOrder = {
    "control"
})
public class ConsultarParametrosHerramientasFVSimpleRequest
    extends BaseRequest
{

    @XmlElement(required = true)
    protected ConsultarParametrosHerramientasFVControlRequest control;

    /**
     * Obtiene el valor de la propiedad control.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarParametrosHerramientasFVControlRequest }
     *     
     */
    public ConsultarParametrosHerramientasFVControlRequest getControl() {
        return control;
    }

    /**
     * Define el valor de la propiedad control.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarParametrosHerramientasFVControlRequest }
     *     
     */
    public void setControl(ConsultarParametrosHerramientasFVControlRequest value) {
        this.control = value;
    }

}
