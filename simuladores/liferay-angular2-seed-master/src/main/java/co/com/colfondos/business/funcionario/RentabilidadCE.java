
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para rentabilidadCE complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="rentabilidadCE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaFinal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colfondosLargoPlazo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="colfondosCortoPlazo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="colfondosRentaEA" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="porvenirRentaEA" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="proteccionRentaEA" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="old_mutualRentaEA" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rentabilidadCE", propOrder = { "fechaFinal", "colfondosLargoPlazo", "colfondosCortoPlazo",
		"colfondosRentaEA", "porvenirRentaEA", "proteccionRentaEA", "oldMutualRentaEA" })
public class RentabilidadCE {

	protected String fechaFinal;
	protected Double colfondosLargoPlazo;
	protected Double colfondosCortoPlazo;
	@XmlElement(name = "colfondos")
	protected Double colfondosRentaEA;
	@XmlElement(name = "porvenir")
	protected Double porvenirRentaEA;
	@XmlElement(name = "proteccion")
	protected Double proteccionRentaEA;
	@XmlElement(name = "old_mutual")
	protected Double oldMutualRentaEA;

	/**
	 * Obtiene el valor de la propiedad fechaFinal.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * Define el valor de la propiedad fechaFinal.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setFechaFinal(String value) {
		this.fechaFinal = value;
	}

	/**
	 * Obtiene el valor de la propiedad colfondosLargoPlazo.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getColfondosLargoPlazo() {
		return colfondosLargoPlazo;
	}

	/**
	 * Define el valor de la propiedad colfondosLargoPlazo.
	 * 
	 * @param value allowed object is {@link Double }
	 * 
	 */
	public void setColfondosLargoPlazo(Double value) {
		this.colfondosLargoPlazo = value;
	}

	/**
	 * Obtiene el valor de la propiedad colfondosCortoPlazo.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getColfondosCortoPlazo() {
		return colfondosCortoPlazo;
	}

	/**
	 * Define el valor de la propiedad colfondosCortoPlazo.
	 * 
	 * @param value allowed object is {@link Double }
	 * 
	 */
	public void setColfondosCortoPlazo(Double value) {
		this.colfondosCortoPlazo = value;
	}

	/**
	 * Obtiene el valor de la propiedad colfondosRentaEA.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getColfondosRentaEA() {
		return colfondosRentaEA;
	}

	/**
	 * Define el valor de la propiedad colfondosRentaEA.
	 * 
	 * @param value allowed object is {@link Double }
	 * 
	 */
	public void setColfondosRentaEA(Double value) {
		this.colfondosRentaEA = value;
	}

	/**
	 * Obtiene el valor de la propiedad porvenirRentaEA.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getPorvenirRentaEA() {
		return porvenirRentaEA;
	}

	/**
	 * Define el valor de la propiedad porvenirRentaEA.
	 * 
	 * @param value allowed object is {@link Double }
	 * 
	 */
	public void setPorvenirRentaEA(Double value) {
		this.porvenirRentaEA = value;
	}

	/**
	 * Obtiene el valor de la propiedad proteccionRentaEA.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getProteccionRentaEA() {
		return proteccionRentaEA;
	}

	/**
	 * Define el valor de la propiedad proteccionRentaEA.
	 * 
	 * @param value allowed object is {@link Double }
	 * 
	 */
	public void setProteccionRentaEA(Double value) {
		this.proteccionRentaEA = value;
	}

	/**
	 * Obtiene el valor de la propiedad oldMutualRentaEA.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getOldMutualRentaEA() {
		return oldMutualRentaEA;
	}

	/**
	 * Define el valor de la propiedad oldMutualRentaEA.
	 * 
	 * @param value allowed object is {@link Double }
	 * 
	 */
	public void setOldMutualRentaEA(Double value) {
		this.oldMutualRentaEA = value;
	}

}
