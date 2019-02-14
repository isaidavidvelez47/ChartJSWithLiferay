
package co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarParametrosHerramientasFV complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarParametrosHerramientasFV">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultarParametrosHerramientasFVSimpleRequest" type="{http://funcionario.business.colfondos.com.co/}consultarParametrosHerramientasFVSimpleRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarParametrosHerramientasFV", propOrder = {
    "consultarParametrosHerramientasFVSimpleRequest"
})
public class ConsultarParametrosHerramientasFV {

    @XmlElement(name = "ConsultarParametrosHerramientasFVSimpleRequest")
    protected ConsultarParametrosHerramientasFVSimpleRequest consultarParametrosHerramientasFVSimpleRequest;

    /**
     * Obtiene el valor de la propiedad consultarParametrosHerramientasFVSimpleRequest.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarParametrosHerramientasFVSimpleRequest }
     *     
     */
    public ConsultarParametrosHerramientasFVSimpleRequest getConsultarParametrosHerramientasFVSimpleRequest() {
        return consultarParametrosHerramientasFVSimpleRequest;
    }

    /**
     * Define el valor de la propiedad consultarParametrosHerramientasFVSimpleRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarParametrosHerramientasFVSimpleRequest }
     *     
     */
    public void setConsultarParametrosHerramientasFVSimpleRequest(ConsultarParametrosHerramientasFVSimpleRequest value) {
        this.consultarParametrosHerramientasFVSimpleRequest = value;
    }

}
