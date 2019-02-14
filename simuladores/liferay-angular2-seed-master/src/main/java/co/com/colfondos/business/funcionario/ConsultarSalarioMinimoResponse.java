
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarSalarioMinimoResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarSalarioMinimoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultarSalarioMinResponse" type="{http://funcionario.business.colfondos.com.co/}salariosMinimos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarSalarioMinimoResponse", propOrder = {
    "consultarSalarioMinResponse"
})
public class ConsultarSalarioMinimoResponse {

    @XmlElement(name = "ConsultarSalarioMinResponse")
    protected SalariosMinimos consultarSalarioMinResponse;

    /**
     * Obtiene el valor de la propiedad consultarSalarioMinResponse.
     * 
     * @return
     *     possible object is
     *     {@link SalariosMinimos }
     *     
     */
    public SalariosMinimos getConsultarSalarioMinResponse() {
        return consultarSalarioMinResponse;
    }

    /**
     * Define el valor de la propiedad consultarSalarioMinResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link SalariosMinimos }
     *     
     */
    public void setConsultarSalarioMinResponse(SalariosMinimos value) {
        this.consultarSalarioMinResponse = value;
    }

}
