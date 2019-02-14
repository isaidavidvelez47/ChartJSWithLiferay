
package co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarEdadPension complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarEdadPension">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultarEdadPensionSimpleRequest" type="{http://funcionario.business.colfondos.com.co/}consultarEdadPensionSimpleRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarEdadPension", propOrder = {
    "consultarEdadPensionSimpleRequest"
})
public class ConsultarEdadPension {

    @XmlElement(name = "ConsultarEdadPensionSimpleRequest")
    protected ConsultarEdadPensionSimpleRequest consultarEdadPensionSimpleRequest;

    /**
     * Obtiene el valor de la propiedad consultarEdadPensionSimpleRequest.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarEdadPensionSimpleRequest }
     *     
     */
    public ConsultarEdadPensionSimpleRequest getConsultarEdadPensionSimpleRequest() {
        return consultarEdadPensionSimpleRequest;
    }

    /**
     * Define el valor de la propiedad consultarEdadPensionSimpleRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarEdadPensionSimpleRequest }
     *     
     */
    public void setConsultarEdadPensionSimpleRequest(ConsultarEdadPensionSimpleRequest value) {
        this.consultarEdadPensionSimpleRequest = value;
    }

}
