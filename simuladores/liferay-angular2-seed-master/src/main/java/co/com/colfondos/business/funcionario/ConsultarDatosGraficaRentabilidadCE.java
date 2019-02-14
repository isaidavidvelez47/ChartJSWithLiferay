
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarDatosGraficaRentabilidadCE complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarDatosGraficaRentabilidadCE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsultarDatosGraficaRentabilidadCERequest" type="{http://funcionario.business.colfondos.com.co/}consultarDatosGraficaRentabilidadCERequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarDatosGraficaRentabilidadCE", propOrder = {
    "consultarDatosGraficaRentabilidadCERequest"
})
public class ConsultarDatosGraficaRentabilidadCE {

    @XmlElement(name = "ConsultarDatosGraficaRentabilidadCERequest")
    protected ConsultarDatosGraficaRentabilidadCERequest consultarDatosGraficaRentabilidadCERequest;

    /**
     * Obtiene el valor de la propiedad consultarDatosGraficaRentabilidadCERequest.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarDatosGraficaRentabilidadCERequest }
     *     
     */
    public ConsultarDatosGraficaRentabilidadCERequest getConsultarDatosGraficaRentabilidadCERequest() {
        return consultarDatosGraficaRentabilidadCERequest;
    }

    /**
     * Define el valor de la propiedad consultarDatosGraficaRentabilidadCERequest.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarDatosGraficaRentabilidadCERequest }
     *     
     */
    public void setConsultarDatosGraficaRentabilidadCERequest(ConsultarDatosGraficaRentabilidadCERequest value) {
        this.consultarDatosGraficaRentabilidadCERequest = value;
    }

}
