/**
 * 
 */
package com.colfondos.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author silvio.cantillo
 *
 */
@XmlRootElement
public class RentabilidadFondo implements Serializable {

	private String fondo;
	private Double rentabilidad;

	/**
	 * @return the fondo
	 */
	public String getFondo() {
		return fondo;
	}

	/**
	 * @param fondo the fondo to set
	 */
	public void setFondo(String fondo) {
		this.fondo = fondo;
	}

	/**
	 * @return the rentabilidad
	 */
	public Double getRentabilidad() {
		return rentabilidad;
	}

	/**
	 * @param rentabilidad the rentabilidad to set
	 */
	public void setRentabilidad(Double rentabilidad) {
		this.rentabilidad = rentabilidad;
	}

}
