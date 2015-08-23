
package xws.tim7.mt102;

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
 *         &lt;element name="nalog_za_grupna_placanja" type="{http://xws/tim7/mt102}MT102Type"/>
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
    "nalogZaGrupnaPlacanja"
})
@XmlRootElement(name = "primiMT102")
public class PrimiMT102 {

    @XmlElement(name = "nalog_za_grupna_placanja", required = true)
    protected MT102Type nalogZaGrupnaPlacanja;

    /**
     * Gets the value of the nalogZaGrupnaPlacanja property.
     * 
     * @return
     *     possible object is
     *     {@link MT102Type }
     *     
     */
    public MT102Type getNalogZaGrupnaPlacanja() {
        return nalogZaGrupnaPlacanja;
    }

    /**
     * Sets the value of the nalogZaGrupnaPlacanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link MT102Type }
     *     
     */
    public void setNalogZaGrupnaPlacanja(MT102Type value) {
        this.nalogZaGrupnaPlacanja = value;
    }

}
