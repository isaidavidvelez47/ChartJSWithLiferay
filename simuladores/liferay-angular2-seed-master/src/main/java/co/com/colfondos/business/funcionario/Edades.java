
package co.com.colfondos.business.funcionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para edades complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="edades">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="edadJubilacionMujer" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="edadJubilacionHombre" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "edades", propOrder = {
    "edadJubilacionMujer",
    "edadJubilacionHombre"
})
public class Edades {

    protected int edadJubilacionMujer;
    protected int edadJubilacionHombre;

    /**
     * Obtiene el valor de la propiedad edadJubilacionMujer.
     * 
     */
    public int getEdadJubilacionMujer() {
        return edadJubilacionMujer;
    }

    /**
     * Define el valor de la propiedad edadJubilacionMujer.
     * 
     */
    public void setEdadJubilacionMujer(int value) {
        this.edadJubilacionMujer = value;
    }

    /**
     * Obtiene el valor de la propiedad edadJubilacionHombre.
     * 
     */
    public int getEdadJubilacionHombre() {
        return edadJubilacionHombre;
    }

    /**
     * Define el valor de la propiedad edadJubilacionHombre.
     * 
     */
    public void setEdadJubilacionHombre(int value) {
        this.edadJubilacionHombre = value;
    }

}
