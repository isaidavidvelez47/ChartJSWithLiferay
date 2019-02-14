
package co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para baseControlRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="baseControlRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="canal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="identificadorDeTransaccion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseControlRequest", propOrder = {
    "canal",
    "identificadorDeTransaccion"
})
@XmlSeeAlso({
    ConsultarParametrosHerramientasFVControlRequest.class,
    ConsultarRentaPortafolioControlRequest.class,
    ConsultarSalarioMinimoControlRequest.class,
    ConsultarEdadPensionControlRequest.class
})
public class BaseControlRequest {

    @XmlElement(required = true)
    protected String canal;
    @XmlElement(required = true)
    protected String identificadorDeTransaccion;

    /**
     * Obtiene el valor de la propiedad canal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Define el valor de la propiedad canal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanal(String value) {
        this.canal = value;
    }

    /**
     * Obtiene el valor de la propiedad identificadorDeTransaccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadorDeTransaccion() {
        return identificadorDeTransaccion;
    }

    /**
     * Define el valor de la propiedad identificadorDeTransaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorDeTransaccion(String value) {
        this.identificadorDeTransaccion = value;
    }

}
