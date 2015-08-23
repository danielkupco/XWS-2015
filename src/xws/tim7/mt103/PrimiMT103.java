
package xws.tim7.mt103;

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
 *         &lt;element name="RTGS_MT103" type="{http://xws/tim7/mt103}MT103Type"/>
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
    "rtgsmt103"
})
@XmlRootElement(name = "primiMT103")
public class PrimiMT103 {

    @XmlElement(name = "RTGS_MT103", required = true)
    protected MT103Type rtgsmt103;

    /**
     * Gets the value of the rtgsmt103 property.
     * 
     * @return
     *     possible object is
     *     {@link MT103Type }
     *     
     */
    public MT103Type getRTGSMT103() {
        return rtgsmt103;
    }

    /**
     * Sets the value of the rtgsmt103 property.
     * 
     * @param value
     *     allowed object is
     *     {@link MT103Type }
     *     
     */
    public void setRTGSMT103(MT103Type value) {
        this.rtgsmt103 = value;
    }

}
