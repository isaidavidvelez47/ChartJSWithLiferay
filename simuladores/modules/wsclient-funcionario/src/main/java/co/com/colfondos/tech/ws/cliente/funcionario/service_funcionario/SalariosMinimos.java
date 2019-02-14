
package co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para salariosMinimos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="salariosMinimos">
 *   &lt;complexContent>
 *     &lt;extension base="{http://funcionario.business.colfondos.com.co/}consultarSalarioMinimoRequest">
 *       &lt;sequence>
 *         &lt;element name="salarios" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="salario" type="{http://funcionario.business.colfondos.com.co/}salarioMinimo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salariosMinimos", propOrder = {
    "salarios",
    "codigoError",
    "descripcionError"
})
public class SalariosMinimos
    extends ConsultarSalarioMinimoRequest
{

    protected SalariosMinimos.Salarios salarios;
    protected String codigoError;
    protected String descripcionError;

    /**
     * Obtiene el valor de la propiedad salarios.
     * 
     * @return
     *     possible object is
     *     {@link SalariosMinimos.Salarios }
     *     
     */
    public SalariosMinimos.Salarios getSalarios() {
        return salarios;
    }

    /**
     * Define el valor de la propiedad salarios.
     * 
     * @param value
     *     allowed object is
     *     {@link SalariosMinimos.Salarios }
     *     
     */
    public void setSalarios(SalariosMinimos.Salarios value) {
        this.salarios = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Define el valor de la propiedad codigoError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionError() {
        return descripcionError;
    }

    /**
     * Define el valor de la propiedad descripcionError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionError(String value) {
        this.descripcionError = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="salario" type="{http://funcionario.business.colfondos.com.co/}salarioMinimo" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "salario"
    })
    public static class Salarios {

        protected List<SalarioMinimo> salario;

        /**
         * Gets the value of the salario property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the salario property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSalario().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SalarioMinimo }
         * 
         * 
         */
        public List<SalarioMinimo> getSalario() {
            if (salario == null) {
                salario = new ArrayList<SalarioMinimo>();
            }
            return this.salario;
        }

    }

}
