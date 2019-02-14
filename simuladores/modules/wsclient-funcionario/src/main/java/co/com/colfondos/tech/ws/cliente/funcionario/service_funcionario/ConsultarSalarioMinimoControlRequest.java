
package co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarSalarioMinimoControlRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarSalarioMinimoControlRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://funcionario.business.colfondos.com.co/}baseControlRequest">
 *       &lt;sequence>
 *         &lt;element name="fecSig" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="regRet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarSalarioMinimoControlRequest", propOrder = {
    "fecSig",
    "regRet"
})
public class ConsultarSalarioMinimoControlRequest
    extends BaseControlRequest
{

    @XmlElement(required = true)
    protected String fecSig;
    protected String regRet;

    /**
     * Obtiene el valor de la propiedad fecSig.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecSig() {
        return fecSig;
    }

    /**
     * Define el valor de la propiedad fecSig.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecSig(String value) {
        this.fecSig = value;
    }

    /**
     * Obtiene el valor de la propiedad regRet.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegRet() {
        return regRet;
    }

    /**
     * Define el valor de la propiedad regRet.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegRet(String value) {
        this.regRet = value;
    }

}
