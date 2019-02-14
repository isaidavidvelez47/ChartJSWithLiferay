
package co.com.colfondos.business.funcionario;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseConsultarParametrosHerramientasFV complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseConsultarParametrosHerramientasFV">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codMensajeResp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desMensajeResp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechas" type="{http://funcionario.business.colfondos.com.co/}fechasDatosPortafolios" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseConsultarParametrosHerramientasFV", propOrder = {
    "codMensajeResp",
    "desMensajeResp",
    "fechas"
})
public class ResponseConsultarParametrosHerramientasFV {

    protected String codMensajeResp;
    protected String desMensajeResp;
    @XmlElement(required = true)
    protected List<FechasDatosPortafolios> fechas;

    /**
     * Obtiene el valor de la propiedad codMensajeResp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodMensajeResp() {
        return codMensajeResp;
    }

    /**
     * Define el valor de la propiedad codMensajeResp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodMensajeResp(String value) {
        this.codMensajeResp = value;
    }

    /**
     * Obtiene el valor de la propiedad desMensajeResp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesMensajeResp() {
        return desMensajeResp;
    }

    /**
     * Define el valor de la propiedad desMensajeResp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesMensajeResp(String value) {
        this.desMensajeResp = value;
    }

    /**
     * Gets the value of the fechas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fechas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFechas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FechasDatosPortafolios }
     * 
     * 
     */
    public List<FechasDatosPortafolios> getFechas() {
        if (fechas == null) {
            fechas = new ArrayList<FechasDatosPortafolios>();
        }
        return this.fechas;
    }

}
