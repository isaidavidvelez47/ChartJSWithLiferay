/**
 * 
 */
package com.colfondos.model;

import java.io.Serializable;

/**
 * @author silvio.cantillo
 *
 */
public class Period implements Serializable {

	private String initialDate;

	private String finalDate;

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initial) {
		this.initialDate = initial;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}
}
