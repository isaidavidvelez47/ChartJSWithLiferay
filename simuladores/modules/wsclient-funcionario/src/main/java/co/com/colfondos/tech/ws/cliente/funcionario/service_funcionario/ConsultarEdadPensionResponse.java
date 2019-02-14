
package co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarEdadPensionResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarEdadPensionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultarEdadPensionSimpleResponse" type="{http://funcionario.business.colfondos.com.co/}consultarEdadPensionSimpleResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarEdadPensionResponse", propOrder = {
    "consultarEdadPensionSimpleResponse"
})
public class ConsultarEdadPensionResponse {

    @XmlElement(name = "ConsultarEdadPensionSimpleResponse")
    protected ConsultarEdadPensionSimpleResponse consultarEdadPensionSimpleResponse;

    /**
     * Obtiene el valor de la propiedad consultarEdadPensionSimpleResponse.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarEdadPensionSimpleResponse }
     *     
     */
    public ConsultarEdadPensionSimpleResponse getConsultarEdadPensionSimpleResponse() {
        return consultarEdadPensionSimpleResponse;
    }

    /**
     * Define el valor de la propiedad consultarEdadPensionSimpleResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarEdadPensionSimpleResponse }
     *     
     */
    public void setConsultarEdadPensionSimpleResponse(ConsultarEdadPensionSimpleResponse value) {
        this.consultarEdadPensionSimpleResponse = value;
    }

}
