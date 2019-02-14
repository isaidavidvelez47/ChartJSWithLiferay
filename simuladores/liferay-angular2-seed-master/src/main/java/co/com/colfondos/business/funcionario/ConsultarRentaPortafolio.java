
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarRentaPortafolio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarRentaPortafolio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultarRentaPortafolioRequest" type="{http://funcionario.business.colfondos.com.co/}consultarRentaPortafolioRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarRentaPortafolio", propOrder = {
    "consultarRentaPortafolioRequest"
})
public class ConsultarRentaPortafolio {

    @XmlElement(name = "ConsultarRentaPortafolioRequest")
    protected ConsultarRentaPortafolioRequest consultarRentaPortafolioRequest;

    /**
     * Obtiene el valor de la propiedad consultarRentaPortafolioRequest.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarRentaPortafolioRequest }
     *     
     */
    public ConsultarRentaPortafolioRequest getConsultarRentaPortafolioRequest() {
        return consultarRentaPortafolioRequest;
    }

    /**
     * Define el valor de la propiedad consultarRentaPortafolioRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarRentaPortafolioRequest }
     *     
     */
    public void setConsultarRentaPortafolioRequest(ConsultarRentaPortafolioRequest value) {
        this.consultarRentaPortafolioRequest = value;
    }

}
