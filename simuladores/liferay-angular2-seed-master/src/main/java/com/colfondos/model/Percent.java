/**
 * 
 */
package com.colfondos.model;

import java.io.Serializable;

/**
 * @author silvio.cantillo
 *
 */
public class Percent implements Serializable {

	private Double proteccion;

	private Double porvenir;

	private Double oldmutual;

	private Double colfondos;

	private Double colfondosLargoPlazo;

	private Double colfondosCortoPlazo;

	public Double getProteccion() {
		return proteccion;
	}

	public void setProteccion(Double proteccion) {
		this.proteccion = proteccion;
	}

	public Double getPorvenir() {
		return porvenir;
	}

	public void setPorvenir(Double porvenir) {
		this.porvenir = porvenir;
	}

	public Double getOldmutual() {
		return oldmutual;
	}

	public void setOldmutual(Double oldmutual) {
		this.oldmutual = oldmutual;
	}

	public Double getColfondos() {
		return colfondos;
	}

	public void setColfondos(Double colfondos) {
		this.colfondos = colfondos;
	}

	public Double getColfondosLargoPlazo() {
		return colfondosLargoPlazo;
	}

	public void setColfondosLargoPlazo(Double colfondosLargoPlazo) {
		this.colfondosLargoPlazo = colfondosLargoPlazo;
	}

	public Double getColfondosCortoPlazo() {
		return colfondosCortoPlazo;
	}

	public void setColfondosCortoPlazo(Double colfondosCortoPlazo) {
		this.colfondosCortoPlazo = colfondosCortoPlazo;
	}

}
