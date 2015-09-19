
package xws.tim7.mt910;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import xws.tim7.globals.MT9XXType;


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
 *         &lt;element name="poruka_o_odobrenju_MT910" type="{http://xws/tim7/globals}MT9XXType"/>
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
    "porukaOOdobrenjuMT910"
})
@XmlRootElement(name = "primiMT910")
public class PrimiMT910 {

    @XmlElement(name = "poruka_o_odobrenju_MT910", required = true)
    protected MT9XXType porukaOOdobrenjuMT910;

    /**
     * Gets the value of the porukaOOdobrenjuMT910 property.
     * 
     * @return
     *     possible object is
     *     {@link MT9XXType }
     *     
     */
    public MT9XXType getPorukaOOdobrenjuMT910() {
        return porukaOOdobrenjuMT910;
    }

    /**
     * Sets the value of the porukaOOdobrenjuMT910 property.
     * 
     * @param value
     *     allowed object is
     *     {@link MT9XXType }
     *     
     */
    public void setPorukaOOdobrenjuMT910(MT9XXType value) {
        this.porukaOOdobrenjuMT910 = value;
    }

}
