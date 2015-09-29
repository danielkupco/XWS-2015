
package soap.tim7.entities.zahtevzaizvod;

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
 *         &lt;element name="zahtev_za_izvod" type="{http://xws/tim7/zahtevZaIzvod}ZahtevZaIzvodType"/>
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
    "zahtevZaIzvod"
})
@XmlRootElement(name = "primiZahtevZaIzvod")
public class PrimiZahtevZaIzvod {

    @XmlElement(name = "zahtev_za_izvod", required = true)
    protected ZahtevZaIzvodType zahtevZaIzvod;

    /**
     * Gets the value of the zahtevZaIzvod property.
     * 
     * @return
     *     possible object is
     *     {@link ZahtevZaIzvodType }
     *     
     */
    public ZahtevZaIzvodType getZahtevZaIzvod() {
        return zahtevZaIzvod;
    }

    /**
     * Sets the value of the zahtevZaIzvod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZahtevZaIzvodType }
     *     
     */
    public void setZahtevZaIzvod(ZahtevZaIzvodType value) {
        this.zahtevZaIzvod = value;
    }

}
