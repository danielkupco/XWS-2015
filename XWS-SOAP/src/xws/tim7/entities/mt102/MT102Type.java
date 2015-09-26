
package xws.tim7.entities.mt102;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import xws.tim7.entities.Identifiable;
import xws.tim7.entities.globals.RacunType;
import xws.tim7.entities.nalogzaplacanje.NalogZaPlacanjeType;


/**
 * <p>Java class for MT102Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MT102Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SWIFT_kod_banke_duznika" type="{http://xws/tim7/globals}TSWIFTKod"/>
 *         &lt;element name="obracunski_racun_banke_duznika" type="{http://xws/tim7/globals}RacunType"/>
 *         &lt;element name="SWIFT_kod_banke_poverioca" type="{http://xws/tim7/globals}TSWIFTKod"/>
 *         &lt;element name="obracunski_racun_banke_poverioca" type="{http://xws/tim7/globals}RacunType"/>
 *         &lt;element name="ukupan_iznos" type="{http://xws/tim7/globals}TDecimal15_2"/>
 *         &lt;element name="sifra_valute" type="{http://xws/tim7/globals}TOznakaValute"/>
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="datum_valute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;sequence maxOccurs="unbounded">
 *           &lt;element name="nalog_za_placanje" type="{http://xws/tim7/nalogZaPlacanje}NalogZaPlacanjeType"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MT102Type", propOrder = {
    "swiftKodBankeDuznika",
    "obracunskiRacunBankeDuznika",
    "swiftKodBankePoverioca",
    "obracunskiRacunBankePoverioca",
    "ukupanIznos",
    "sifraValute",
    "datum",
    "datumValute",
    "nalogZaPlacanje"
})
public class MT102Type extends Identifiable {

    @XmlElement(name = "SWIFT_kod_banke_duznika", required = true)
    protected String swiftKodBankeDuznika;
    @XmlElement(name = "obracunski_racun_banke_duznika", required = true)
    protected RacunType obracunskiRacunBankeDuznika;
    @XmlElement(name = "SWIFT_kod_banke_poverioca", required = true)
    protected String swiftKodBankePoverioca;
    @XmlElement(name = "obracunski_racun_banke_poverioca", required = true)
    protected RacunType obracunskiRacunBankePoverioca;
    @XmlElement(name = "ukupan_iznos", required = true)
    protected BigDecimal ukupanIznos;
    @XmlElement(name = "sifra_valute", required = true)
    protected String sifraValute;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlElement(name = "datum_valute", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(name = "nalog_za_placanje", required = true)
    protected List<NalogZaPlacanjeType> nalogZaPlacanje;

    /**
     * Gets the value of the swiftKodBankeDuznika property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSWIFTKodBankeDuznika() {
        return swiftKodBankeDuznika;
    }

    /**
     * Sets the value of the swiftKodBankeDuznika property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSWIFTKodBankeDuznika(String value) {
        this.swiftKodBankeDuznika = value;
    }

    /**
     * Gets the value of the obracunskiRacunBankeDuznika property.
     * 
     * @return
     *     possible object is
     *     {@link RacunType }
     *     
     */
    public RacunType getObracunskiRacunBankeDuznika() {
        return obracunskiRacunBankeDuznika;
    }

    /**
     * Sets the value of the obracunskiRacunBankeDuznika property.
     * 
     * @param value
     *     allowed object is
     *     {@link RacunType }
     *     
     */
    public void setObracunskiRacunBankeDuznika(RacunType value) {
        this.obracunskiRacunBankeDuznika = value;
    }

    /**
     * Gets the value of the swiftKodBankePoverioca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSWIFTKodBankePoverioca() {
        return swiftKodBankePoverioca;
    }

    /**
     * Sets the value of the swiftKodBankePoverioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSWIFTKodBankePoverioca(String value) {
        this.swiftKodBankePoverioca = value;
    }

    /**
     * Gets the value of the obracunskiRacunBankePoverioca property.
     * 
     * @return
     *     possible object is
     *     {@link RacunType }
     *     
     */
    public RacunType getObracunskiRacunBankePoverioca() {
        return obracunskiRacunBankePoverioca;
    }

    /**
     * Sets the value of the obracunskiRacunBankePoverioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link RacunType }
     *     
     */
    public void setObracunskiRacunBankePoverioca(RacunType value) {
        this.obracunskiRacunBankePoverioca = value;
    }

    /**
     * Gets the value of the ukupanIznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUkupanIznos() {
        return ukupanIznos;
    }

    /**
     * Sets the value of the ukupanIznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUkupanIznos(BigDecimal value) {
        this.ukupanIznos = value;
    }

    /**
     * Gets the value of the sifraValute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraValute() {
        return sifraValute;
    }

    /**
     * Sets the value of the sifraValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraValute(String value) {
        this.sifraValute = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
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
     * Gets the value of the nalogZaPlacanje property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nalogZaPlacanje property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNalogZaPlacanje().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NalogZaPlacanjeType }
     * 
     * 
     */
    public List<NalogZaPlacanjeType> getNalogZaPlacanje() {
        if (nalogZaPlacanje == null) {
            nalogZaPlacanje = new ArrayList<NalogZaPlacanjeType>();
        }
        return this.nalogZaPlacanje;
    }
    
    
	private Long Id;

	@Override
	public Long getId() {
		return Id;
	}

	@Override
	public void setId(Long value) {
		this.Id = value;
	}

}
