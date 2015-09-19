
package xws.tim7.entities.globals;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status_kod" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="poruka" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusType", propOrder = {
    "statusKod",
    "poruka"
})
public class StatusType {

    @XmlElement(name = "status_kod", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger statusKod;
    @XmlElement(required = true)
    protected String poruka;

    /**
     * Gets the value of the statusKod property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStatusKod() {
        return statusKod;
    }

    /**
     * Sets the value of the statusKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStatusKod(BigInteger value) {
        this.statusKod = value;
    }

    /**
     * Gets the value of the poruka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoruka() {
        return poruka;
    }

    /**
     * Sets the value of the poruka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoruka(String value) {
        this.poruka = value;
    }

}
