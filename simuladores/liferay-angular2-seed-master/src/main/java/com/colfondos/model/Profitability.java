/**
 * 
 */
package com.colfondos.model;

import java.io.Serializable;

/**
 * @author silvio.cantillo
 *
 */
public class Profitability implements Serializable {

	private String initialDate;

	private String finalDate;

	private Percent percent;

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

	public Percent getPercent() {
		return percent;
	}

	public void setPercent(Percent percent) {
		this.percent = percent;
	}
}
