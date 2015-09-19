
package xws.tim7.nalogzaplacanje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nalog_za_placanje" type="{http://xws/tim7/nalogZaPlacanje}NalogZaPlacanjeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nalogZaPlacanje"
})
@XmlRootElement(name = "primiNalogZaPlacanje")
public class PrimiNalogZaPlacanje {

    @XmlElement(name = "nalog_za_placanje", required = true)
    protected NalogZaPlacanjeType nalogZaPlacanje;

    /**
     * Gets the value of the nalogZaPlacanje property.
     * 
     * @return
     *     possible object is
     *     {@link NalogZaPlacanjeType }
     *     
     */
    public NalogZaPlacanjeType getNalogZaPlacanje() {
        return nalogZaPlacanje;
    }

    /**
     * Sets the value of the nalogZaPlacanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link NalogZaPlacanjeType }
     *     
     */
    public void setNalogZaPlacanje(NalogZaPlacanjeType value) {
        this.nalogZaPlacanje = value;
    }

}
