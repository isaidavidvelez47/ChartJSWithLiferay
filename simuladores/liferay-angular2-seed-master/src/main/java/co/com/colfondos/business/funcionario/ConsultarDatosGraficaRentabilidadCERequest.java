
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarDatosGraficaRentabilidadCERequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarDatosGraficaRentabilidadCERequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://funcionario.business.colfondos.com.co/}baseRequest">
 *       &lt;sequence>
 *         &lt;element name="control" type="{http://funcionario.business.colfondos.com.co/}consultarDatosGraficaRentabilidadCEControlRequest"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarDatosGraficaRentabilidadCERequest", propOrder = {
    "control"
})
public class ConsultarDatosGraficaRentabilidadCERequest
    extends BaseRequest
{

    @XmlElement(required = true)
    protected ConsultarDatosGraficaRentabilidadCEControlRequest control;

    /**
     * Obtiene el valor de la propiedad control.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarDatosGraficaRentabilidadCEControlRequest }
     *     
     */
    public ConsultarDatosGraficaRentabilidadCEControlRequest getControl() {
        return control;
    }

    /**
     * Define el valor de la propiedad control.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarDatosGraficaRentabilidadCEControlRequest }
     *     
     */
    public void setControl(ConsultarDatosGraficaRentabilidadCEControlRequest value) {
        this.control = value;
    }

}
