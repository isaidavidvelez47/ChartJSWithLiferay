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
public class GraficoRentabilidadRs implements Serializable {

	private List<String> labels = null;

	private List<Profitability> profitability = null;

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<Profitability> getProfitability() {
		return profitability;
	}

	public void setProfitability(List<Profitability> profitability) {
		this.profitability = profitability;
	}
}
