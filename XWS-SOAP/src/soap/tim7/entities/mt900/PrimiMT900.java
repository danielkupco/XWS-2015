
package soap.tim7.entities.mt900;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import soap.tim7.entities.globals.MT9XXType;


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
 *         &lt;element name="poruka_o_zaduzenju_MT900" type="{http://xws/tim7/globals}MT9XXType"/>
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
    "porukaOZaduzenjuMT900"
})
@XmlRootElement(name = "primiMT900")
public class PrimiMT900 {

    @XmlElement(name = "poruka_o_zaduzenju_MT900", required = true)
    protected MT9XXType porukaOZaduzenjuMT900;

    /**
     * Gets the value of the porukaOZaduzenjuMT900 property.
     * 
     * @return
     *     possible object is
     *     {@link MT9XXType }
     *     
     */
    public MT9XXType getPorukaOZaduzenjuMT900() {
        return porukaOZaduzenjuMT900;
    }

    /**
     * Sets the value of the porukaOZaduzenjuMT900 property.
     * 
     * @param value
     *     allowed object is
     *     {@link MT9XXType }
     *     
     */
    public void setPorukaOZaduzenjuMT900(MT9XXType value) {
        this.porukaOZaduzenjuMT900 = value;
    }

}
