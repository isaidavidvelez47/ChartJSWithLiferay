/**
 * 
 */
package com.colfondos.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author silvio.cantillo
 *
 */
@XmlRootElement
public class OpcionInversionRs implements Serializable {

	private Projection options;

	private List<String> tittle = null;

	private List<Period> periods = null;

	public Projection getOptions() {
		return options;
	}

	public void setOptions(Projection options) {
		this.options = options;
	}

	public List<String> getTittle() {
		return tittle;
	}

	public void setTittle(List<String> tittle) {
		this.tittle = tittle;
	}

	public List<Period> getPeriods() {
		return periods;
	}

	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}

}
