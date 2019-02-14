
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para rentaPortafolioVO complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="rentaPortafolioVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idRentaPortafolio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productoRentaPortafolio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoProyeccionRentaPortafolio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portafolioRentaPortafolio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoValor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="meses" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colfondosRentaPortafolio" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="porvenirRentaPortafolio" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="proteccionRentaPortafolio" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="old_mutualRentaPortafolio" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rentaPortafolioVO", propOrder = { "idRentaPortafolio", "productoRentaPortafolio",
		"colfondosLargoPlazoNominal", "colfondosCortoPlazoNominal", "colfondosLargoPlazoAcumulada",
		"colfondosCortoPlazoAcumulada", "tipoProyeccionRentaPortafolio", "portafolioRentaPortafolio",
		"tipoRentabilidad", "tipoValor", "meses", "colfondosRentaPortafolio", "porvenirRentaPortafolio",
		"proteccionRentaPortafolio", "oldMutualRentaPortafolio" })
public class RentaPortafolioVO {

	protected String idRentaPortafolio;
	protected String productoRentaPortafolio;
	protected String tipoProyeccionRentaPortafolio;
	protected String portafolioRentaPortafolio;
	protected String tipoRentabilidad;
	protected String tipoValor;
	protected String meses;
	protected Double colfondosRentaPortafolio;
	protected Double colfondosLargoPlazoNominal;
	protected Double colfondosCortoPlazoNominal;
	protected Double colfondosLargoPlazoAcumulada;
	protected Double colfondosCortoPlazoAcumulada;
	protected Double porvenirRentaPortafolio;
	protected Double proteccionRentaPortafolio;
	@XmlElement(name = "old_mutualRentaPortafolio")
	protected Double oldMutualRentaPortafolio;

	/**
	 * Obtiene el valor de la propiedad idRentaPortafolio.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdRentaPortafolio() {
		return idRentaPortafolio;
	}

	/**
	 * Define el valor de la propiedad idRentaPortafolio.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setIdRentaPortafolio(String value) {
		this.idRentaPortafolio = value;
	}

	/**
	 * Obtiene el valor de la propiedad productoRentaPortafolio.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductoRentaPortafolio() {
		return productoRentaPortafolio;
	}

	/**
	 * Define el valor de la propiedad productoRentaPortafolio.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setProductoRentaPortafolio(String value) {
		this.productoRentaPortafolio = value;
	}

	/**
	 * Obtiene el valor de la propiedad tipoProyeccionRentaPortafolio.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTipoProyeccionRentaPortafolio() {
		return tipoProyeccionRentaPortafolio;
	}

	/**
	 * Define el valor de la propiedad tipoProyeccionRentaPortafolio.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setTipoProyeccionRentaPortafolio(String value) {
		this.tipoProyeccionRentaPortafolio = value;
	}

	/**
	 * Obtiene el valor de la propiedad portafolioRentaPortafolio.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPortafolioRentaPortafolio() {
		return portafolioRentaPortafolio;
	}

	/**
	 * Define el valor de la propiedad portafolioRentaPortafolio.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setPortafolioRentaPortafolio(String value) {
		this.portafolioRentaPortafolio = value;
	}

	/**
	 * Obtiene el valor de la propiedad tipoValor.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTipoValor() {
		return tipoValor;
	}

	/**
	 * Define el valor de la propiedad tipoValor.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setTipoValor(String value) {
		this.tipoValor = value;
	}

	/**
	 * Obtiene el valor de la propiedad meses.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMeses() {
		return meses;
	}

	/**
	 * @return the tipoRentabilidad
	 */
	public String getTipoRentabilidad() {
		return tipoRentabilidad;
	}

	/**
	 * @param tipoRentabilidad the tipoRentabilidad to set
	 */
	public void setTipoRentabilidad(String tipoRentabilidad) {
		this.tipoRentabilidad = tipoRentabilidad;
	}

	/**
	 * Define el valor de la propiedad meses.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setMeses(String value) {
		this.meses = value;
	}

	/**
	 * Obtiene el valor de la propiedad colfondosRentaPortafolio.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getColfondosRentaPortafolio() {
		return colfondosRentaPortafolio;
	}

	/**
	 * Define el valor de la propiedad colfondosRentaPortafolio.
	 * 
	 * @param value allowed object is {@link Double }
	 * 
	 */
	public void setColfondosRentaPortafolio(Double value) {
		this.colfondosRentaPortafolio = value;
	}

	/**
	 * Obtiene el valor de la propiedad porvenirRentaPortafolio.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getPorvenirRentaPortafolio() {
		return porvenirRentaPortafolio;
	}

	/**
	 * Define el valor de la propiedad porvenirRentaPortafolio.
	 * 
	 * @param value allowed object is {@link Double }
	 * 
	 */
	public void setPorvenirRentaPortafolio(Double value) {
		this.porvenirRentaPortafolio = value;
	}

	/**
	 * Obtiene el valor de la propiedad proteccionRentaPortafolio.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getProteccionRentaPortafolio() {
		return proteccionRentaPortafolio;
	}

	/**
	 * Define el valor de la propiedad proteccionRentaPortafolio.
	 * 
	 * @param value allowed object is {@link Double }
	 * 
	 */
	public void setProteccionRentaPortafolio(Double value) {
		this.proteccionRentaPortafolio = value;
	}

	/**
	 * Obtiene el valor de la propiedad oldMutualRentaPortafolio.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getOldMutualRentaPortafolio() {
		return oldMutualRentaPortafolio;
	}

	/**
	 * Define el valor de la propiedad oldMutualRentaPortafolio.
	 * 
	 * @param value allowed object is {@link Double }
	 * 
	 */
	public void setOldMutualRentaPortafolio(Double value) {
		this.oldMutualRentaPortafolio = value;
	}

	/**
	 * @return the colfondosLargoPlazoNominal
	 */
	public Double getColfondosLargoPlazoNominal() {
		return colfondosLargoPlazoNominal;
	}

	/**
	 * @param colfondosLargoPlazoNominal the colfondosLargoPlazoNominal to set
	 */
	public void setColfondosLargoPlazoNominal(Double colfondosLargoPlazoNominal) {
		this.colfondosLargoPlazoNominal = colfondosLargoPlazoNominal;
	}

	/**
	 * @return the colfondosCortoPlazoNominal
	 */
	public Double getColfondosCortoPlazoNominal() {
		return colfondosCortoPlazoNominal;
	}

	/**
	 * @param colfondosCortoPlazoNominal the colfondosCortoPlazoNominal to set
	 */
	public void setColfondosCortoPlazoNominal(Double colfondosCortoPlazoNominal) {
		this.colfondosCortoPlazoNominal = colfondosCortoPlazoNominal;
	}

	/**
	 * @return the colfondosLargoPlazoAcumulada
	 */
	public Double getColfondosLargoPlazoAcumulada() {
		return colfondosLargoPlazoAcumulada;
	}

	/**
	 * @param colfondosLargoPlazoAcumulada the colfondosLargoPlazoAcumulada to set
	 */
	public void setColfondosLargoPlazoAcumulada(Double colfondosLargoPlazoAcumulada) {
		this.colfondosLargoPlazoAcumulada = colfondosLargoPlazoAcumulada;
	}

	/**
	 * @return the colfondosCortoPlazoAcumulada
	 */
	public Double getColfondosCortoPlazoAcumulada() {
		return colfondosCortoPlazoAcumulada;
	}

	/**
	 * @param colfondosCortoPlazoAcumulada the colfondosCortoPlazoAcumulada to set
	 */
	public void setColfondosCortoPlazoAcumulada(Double colfondosCortoPlazoAcumulada) {
		this.colfondosCortoPlazoAcumulada = colfondosCortoPlazoAcumulada;
	}

}
