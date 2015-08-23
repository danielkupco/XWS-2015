
package xws.tim7.presek;

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
 *         &lt;element name="presek" type="{http://xws/tim7/presek}PresekType"/>
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
    "presek"
})
@XmlRootElement(name = "return_presek")
public class ReturnPresek {

    @XmlElement(required = true)
    protected PresekType presek;

    /**
     * Gets the value of the presek property.
     * 
     * @return
     *     possible object is
     *     {@link PresekType }
     *     
     */
    public PresekType getPresek() {
        return presek;
    }

    /**
     * Sets the value of the presek property.
     * 
     * @param value
     *     allowed object is
     *     {@link PresekType }
     *     
     */
    public void setPresek(PresekType value) {
        this.presek = value;
    }

}
