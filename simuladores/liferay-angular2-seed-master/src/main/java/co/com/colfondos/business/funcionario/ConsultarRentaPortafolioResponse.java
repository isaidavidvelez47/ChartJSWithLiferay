
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarRentaPortafolioResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarRentaPortafolioResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResponseConsultarRentaPortafolio" type="{http://funcionario.business.colfondos.com.co/}responseConsultarRentaPortafolio" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarRentaPortafolioResponse", propOrder = {
    "responseConsultarRentaPortafolio"
})
public class ConsultarRentaPortafolioResponse {

    @XmlElement(name = "ResponseConsultarRentaPortafolio")
    protected ResponseConsultarRentaPortafolio responseConsultarRentaPortafolio;

    /**
     * Obtiene el valor de la propiedad responseConsultarRentaPortafolio.
     * 
     * @return
     *     possible object is
     *     {@link ResponseConsultarRentaPortafolio }
     *     
     */
    public ResponseConsultarRentaPortafolio getResponseConsultarRentaPortafolio() {
        return responseConsultarRentaPortafolio;
    }

    /**
     * Define el valor de la propiedad responseConsultarRentaPortafolio.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseConsultarRentaPortafolio }
     *     
     */
    public void setResponseConsultarRentaPortafolio(ResponseConsultarRentaPortafolio value) {
        this.responseConsultarRentaPortafolio = value;
    }

}
