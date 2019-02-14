
package co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para internalFault complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="internalFault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="component" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensajeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="servicioCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tecnicalDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "internalFault", propOrder = {
    "component",
    "mensajeId",
    "servicioCode",
    "errorCode",
    "tecnicalDescription",
    "userDescription"
})
public class InternalFault {

    @XmlElement(required = true)
    protected String component;
    @XmlElement(required = true)
    protected String mensajeId;
    @XmlElement(required = true)
    protected String servicioCode;
    @XmlElement(required = true)
    protected String errorCode;
    @XmlElement(required = true)
    protected String tecnicalDescription;
    @XmlElement(required = true)
    protected String userDescription;

    /**
     * Obtiene el valor de la propiedad component.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponent() {
        return component;
    }

    /**
     * Define el valor de la propiedad component.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponent(String value) {
        this.component = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajeId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeId() {
        return mensajeId;
    }

    /**
     * Define el valor de la propiedad mensajeId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeId(String value) {
        this.mensajeId = value;
    }

    /**
     * Obtiene el valor de la propiedad servicioCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicioCode() {
        return servicioCode;
    }

    /**
     * Define el valor de la propiedad servicioCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicioCode(String value) {
        this.servicioCode = value;
    }

    /**
     * Obtiene el valor de la propiedad errorCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Define el valor de la propiedad errorCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Obtiene el valor de la propiedad tecnicalDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTecnicalDescription() {
        return tecnicalDescription;
    }

    /**
     * Define el valor de la propiedad tecnicalDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTecnicalDescription(String value) {
        this.tecnicalDescription = value;
    }

    /**
     * Obtiene el valor de la propiedad userDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserDescription() {
        return userDescription;
    }

    /**
     * Define el valor de la propiedad userDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserDescription(String value) {
        this.userDescription = value;
    }

}
