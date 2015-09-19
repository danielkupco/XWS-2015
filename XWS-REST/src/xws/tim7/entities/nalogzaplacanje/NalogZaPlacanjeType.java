
package xws.tim7.entities.nalogzaplacanje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import xws.tim7.entities.globals.OsnovaNalogaZaPlacanjeType;


/**
 * <p>Java class for NalogZaPlacanjeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NalogZaPlacanjeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID_poruke" type="{http://xws/tim7/globals}TIDPoruke"/>
 *         &lt;element name="osnova_naloga_za_placanje" type="{http://xws/tim7/globals}OsnovaNalogaZaPlacanjeType"/>
 *         &lt;element name="oznaka_valute" type="{http://xws/tim7/globals}TOznakaValute"/>
 *         &lt;element name="datum_naloga" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="datum_valute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="hitno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NalogZaPlacanjeType", propOrder = {
    "idPoruke",
    "osnovaNalogaZaPlacanje",
    "oznakaValute",
    "datumNaloga",
    "datumValute",
    "hitno"
})
public class NalogZaPlacanjeType {

    @XmlElement(name = "ID_poruke", required = true)
    protected String idPoruke;
    @XmlElement(name = "osnova_naloga_za_placanje", required = true)
    protected OsnovaNalogaZaPlacanjeType osnovaNalogaZaPlacanje;
    @XmlElement(name = "oznaka_valute", required = true)
    protected String oznakaValute;
    @XmlElement(name = "datum_naloga", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumNaloga;
    @XmlElement(name = "datum_valute", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    protected boolean hitno;

    /**
     * Gets the value of the idPoruke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDPoruke() {
        return idPoruke;
    }

    /**
     * Sets the value of the idPoruke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPoruke(String value) {
        this.idPoruke = value;
    }

    /**
     * Gets the value of the osnovaNalogaZaPlacanje property.
     * 
     * @return
     *     possible object is
     *     {@link OsnovaNalogaZaPlacanjeType }
     *     
     */
    public OsnovaNalogaZaPlacanjeType getOsnovaNalogaZaPlacanje() {
        return osnovaNalogaZaPlacanje;
    }

    /**
     * Sets the value of the osnovaNalogaZaPlacanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link OsnovaNalogaZaPlacanjeType }
     *     
     */
    public void setOsnovaNalogaZaPlacanje(OsnovaNalogaZaPlacanjeType value) {
        this.osnovaNalogaZaPlacanje = value;
    }

    /**
     * Gets the value of the oznakaValute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOznakaValute() {
        return oznakaValute;
    }

    /**
     * Sets the value of the oznakaValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOznakaValute(String value) {
        this.oznakaValute = value;
    }

    /**
     * Gets the value of the datumNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumNaloga() {
        return datumNaloga;
    }

    /**
     * Sets the value of the datumNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumNaloga(XMLGregorianCalendar value) {
        this.datumNaloga = value;
    }

    /**
     * Gets the value of the datumValute property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumValute() {
        return datumValute;
    }

    /**
     * Sets the value of the datumValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumValute(XMLGregorianCalendar value) {
        this.datumValute = value;
    }

    /**
     * Gets the value of the hitno property.
     * 
     */
    public boolean isHitno() {
        return hitno;
    }

    /**
     * Sets the value of the hitno property.
     * 
     */
    public void setHitno(boolean value) {
        this.hitno = value;
    }

}
