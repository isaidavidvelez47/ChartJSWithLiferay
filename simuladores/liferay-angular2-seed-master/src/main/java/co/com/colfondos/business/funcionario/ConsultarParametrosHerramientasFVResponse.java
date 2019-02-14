
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarParametrosHerramientasFVResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarParametrosHerramientasFVResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResponseConsultarParametrosHerramientasFV" type="{http://funcionario.business.colfondos.com.co/}responseConsultarParametrosHerramientasFV" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarParametrosHerramientasFVResponse", propOrder = {
    "responseConsultarParametrosHerramientasFV"
})
public class ConsultarParametrosHerramientasFVResponse {

    @XmlElement(name = "ResponseConsultarParametrosHerramientasFV")
    protected ResponseConsultarParametrosHerramientasFV responseConsultarParametrosHerramientasFV;

    /**
     * Obtiene el valor de la propiedad responseConsultarParametrosHerramientasFV.
     * 
     * @return
     *     possible object is
     *     {@link ResponseConsultarParametrosHerramientasFV }
     *     
     */
    public ResponseConsultarParametrosHerramientasFV getResponseConsultarParametrosHerramientasFV() {
        return responseConsultarParametrosHerramientasFV;
    }

    /**
     * Define el valor de la propiedad responseConsultarParametrosHerramientasFV.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseConsultarParametrosHerramientasFV }
     *     
     */
    public void setResponseConsultarParametrosHerramientasFV(ResponseConsultarParametrosHerramientasFV value) {
        this.responseConsultarParametrosHerramientasFV = value;
    }

}
