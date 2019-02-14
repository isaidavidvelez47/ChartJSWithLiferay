/**
 * 
 */
package com.colfondos.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author silvio.cantillo
 *
 */

@XmlAccessorType(XmlAccessType.NONE)
public class Projection implements Serializable {
	@XmlElement
	public String idProjection;
	@XmlElement
	public String nameProjection;

	public String getIdProjection() {
		return idProjection;
	}

	public void setIdProjection(String idProjection) {
		this.idProjection = idProjection;
	}

	public String getNameProjection() {
		return nameProjection;
	}

	public void setNameProjection(String nameProjection) {
		this.nameProjection = nameProjection;
	}

}
