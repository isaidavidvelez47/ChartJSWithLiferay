
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarDatosGraficaRentabilidadCEControlRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarDatosGraficaRentabilidadCEControlRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://funcionario.business.colfondos.com.co/}baseControlRequest">
 *       &lt;sequence>
 *         &lt;element name="proyeccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarDatosGraficaRentabilidadCEControlRequest", propOrder = {
    "proyeccion"
})
public class ConsultarDatosGraficaRentabilidadCEControlRequest
    extends BaseControlRequest
{

    protected String proyeccion;

    /**
     * Obtiene el valor de la propiedad proyeccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyeccion() {
        return proyeccion;
    }

    /**
     * Define el valor de la propiedad proyeccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyeccion(String value) {
        this.proyeccion = value;
    }

}
