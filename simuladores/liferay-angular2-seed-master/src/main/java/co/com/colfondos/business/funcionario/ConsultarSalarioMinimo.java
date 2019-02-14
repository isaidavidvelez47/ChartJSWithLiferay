
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarSalarioMinimo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarSalarioMinimo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultarSalarioMinRequest" type="{http://funcionario.business.colfondos.com.co/}consultarSalarioMinimoRequest"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarSalarioMinimo", propOrder = {
    "consultarSalarioMinRequest"
})
public class ConsultarSalarioMinimo {

    @XmlElement(name = "ConsultarSalarioMinRequest", required = true)
    protected ConsultarSalarioMinimoRequest consultarSalarioMinRequest;

    /**
     * Obtiene el valor de la propiedad consultarSalarioMinRequest.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarSalarioMinimoRequest }
     *     
     */
    public ConsultarSalarioMinimoRequest getConsultarSalarioMinRequest() {
        return consultarSalarioMinRequest;
    }

    /**
     * Define el valor de la propiedad consultarSalarioMinRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarSalarioMinimoRequest }
     *     
     */
    public void setConsultarSalarioMinRequest(ConsultarSalarioMinimoRequest value) {
        this.consultarSalarioMinRequest = value;
    }

}
