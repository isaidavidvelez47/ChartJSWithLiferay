
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarDatosGraficaRentabilidadCEResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarDatosGraficaRentabilidadCEResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResponseConsultarDatosGraficaRentabilidadCE" type="{http://funcionario.business.colfondos.com.co/}responseConsultarDatosGraficaRentabilidadCE" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarDatosGraficaRentabilidadCEResponse", propOrder = {
    "responseConsultarDatosGraficaRentabilidadCE"
})
public class ConsultarDatosGraficaRentabilidadCEResponse {

    @XmlElement(name = "ResponseConsultarDatosGraficaRentabilidadCE")
    protected ResponseConsultarDatosGraficaRentabilidadCE responseConsultarDatosGraficaRentabilidadCE;

    /**
     * Obtiene el valor de la propiedad responseConsultarDatosGraficaRentabilidadCE.
     * 
     * @return
     *     possible object is
     *     {@link ResponseConsultarDatosGraficaRentabilidadCE }
     *     
     */
    public ResponseConsultarDatosGraficaRentabilidadCE getResponseConsultarDatosGraficaRentabilidadCE() {
        return responseConsultarDatosGraficaRentabilidadCE;
    }

    /**
     * Define el valor de la propiedad responseConsultarDatosGraficaRentabilidadCE.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseConsultarDatosGraficaRentabilidadCE }
     *     
     */
    public void setResponseConsultarDatosGraficaRentabilidadCE(ResponseConsultarDatosGraficaRentabilidadCE value) {
        this.responseConsultarDatosGraficaRentabilidadCE = value;
    }

}
