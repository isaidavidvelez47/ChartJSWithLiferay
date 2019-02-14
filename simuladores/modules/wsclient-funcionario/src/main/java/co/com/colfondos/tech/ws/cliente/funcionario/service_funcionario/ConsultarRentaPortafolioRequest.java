
package co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarRentaPortafolioRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarRentaPortafolioRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://funcionario.business.colfondos.com.co/}baseRequest">
 *       &lt;sequence>
 *         &lt;element name="control" type="{http://funcionario.business.colfondos.com.co/}consultarRentaPortafolioControlRequest"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarRentaPortafolioRequest", propOrder = {
    "control"
})
public class ConsultarRentaPortafolioRequest
    extends BaseRequest
{

    @XmlElement(required = true)
    protected ConsultarRentaPortafolioControlRequest control;

    /**
     * Obtiene el valor de la propiedad control.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarRentaPortafolioControlRequest }
     *     
     */
    public ConsultarRentaPortafolioControlRequest getControl() {
        return control;
    }

    /**
     * Define el valor de la propiedad control.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarRentaPortafolioControlRequest }
     *     
     */
    public void setControl(ConsultarRentaPortafolioControlRequest value) {
        this.control = value;
    }

}
