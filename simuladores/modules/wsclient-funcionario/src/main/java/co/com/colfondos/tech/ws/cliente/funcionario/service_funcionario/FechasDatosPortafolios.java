
package co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para fechasDatosPortafolios complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="fechasDatosPortafolios">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ultimaFecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portafolio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fechasDatosPortafolios", propOrder = {
    "ultimaFecha",
    "portafolio"
})
public class FechasDatosPortafolios {

    protected String ultimaFecha;
    protected String portafolio;

    /**
     * Obtiene el valor de la propiedad ultimaFecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUltimaFecha() {
        return ultimaFecha;
    }

    /**
     * Define el valor de la propiedad ultimaFecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUltimaFecha(String value) {
        this.ultimaFecha = value;
    }

    /**
     * Obtiene el valor de la propiedad portafolio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortafolio() {
        return portafolio;
    }

    /**
     * Define el valor de la propiedad portafolio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortafolio(String value) {
        this.portafolio = value;
    }

}
