//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 


package xws.tim7.entities.firma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import xws.tim7.entities.Identifiable;


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
 *         &lt;element name="ID_firme" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Naziv_firme" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Partneri" type="{http://xws/tim7/firma}TPartneri"/>
 *         &lt;element name="PIB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Racuni" type="{http://xws/tim7/firma}TRacuni"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idFirme",
    "nazivFirme",
    "url",
    "partneri",
    "pib",
    "racuni"
})
@XmlRootElement(name = "Firma")
public class Firma extends Identifiable {

    @XmlElement(name = "ID_firme")
    protected long idFirme;
    @XmlElement(name = "Naziv_firme", required = true)
    protected String nazivFirme;
    @XmlElement(name = "Url", required = true)
    protected String url;
    @XmlElement(name = "Partneri", required = true)
    protected TPartneri partneri;
<<<<<<< HEAD
    @XmlElement(name = "PIB", required = true)
    protected String pib;
    @XmlElement(name = "Racuni", required = true)
    protected TRacuni racuni;
=======
>>>>>>> 457db5011f81b6fc72082c1bcece84874dd11a62
    @XmlAttribute(name = "id")
    protected Long id;

    /**
     * Gets the value of the idFirme property.
     * 
     */
    public long getIDFirme() {
        return idFirme;
    }

    /**
     * Sets the value of the idFirme property.
     * 
     */
    public void setIDFirme(long value) {
        this.idFirme = value;
    }

    /**
     * Gets the value of the nazivFirme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivFirme() {
        return nazivFirme;
    }

    /**
     * Sets the value of the nazivFirme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivFirme(String value) {
        this.nazivFirme = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the partneri property.
     * 
     * @return
     *     possible object is
     *     {@link TPartneri }
     *     
     */
    public TPartneri getPartneri() {
        return partneri;
    }

    /**
     * Sets the value of the partneri property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPartneri }
     *     
     */
    public void setPartneri(TPartneri value) {
        this.partneri = value;
    }

    /**
     * Gets the value of the pib property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIB() {
        return pib;
    }

    /**
     * Sets the value of the pib property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPIB(String value) {
        this.pib = value;
    }

    /**
     * Gets the value of the racuni property.
     * 
     * @return
     *     possible object is
     *     {@link TRacuni }
     *     
     */
    public TRacuni getRacuni() {
        return racuni;
    }

    /**
     * Sets the value of the racuni property.
     * 
     * @param value
     *     allowed object is
     *     {@link TRacuni }
     *     
     */
    public void setRacuni(TRacuni value) {
        this.racuni = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

}
