//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.19 at 06:12:04 PM CEST 
//

package xws.tim7.entities.faktura;


import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="Redni_broj">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;totalDigits value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Naziv_robe_ili_usluge">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="120"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Kolicina">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="10"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Jedinica_mere">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="6"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Jedinicna_cena">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="10"/>
 *               &lt;fractionDigits value="2"/>
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Vrednost">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="12"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Procenat_rabata">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="5"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Iznos_rabata">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="12"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Umanjeno_za_rabat">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="12"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Ukupan_porez">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="12"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
    "redniBroj",
    "nazivRobeIliUsluge",
    "kolicina",
    "jedinicaMere",
    "jedinicnaCena",
    "vrednost",
    "procenatRabata",
    "iznosRabata",
    "umanjenoZaRabat",
    "ukupanPorez"
})
@XmlRootElement(name = "Stavka")
public class Stavka {

    @XmlElement(name = "Redni_broj", required = true)
    protected BigInteger redniBroj;
    @XmlElement(name = "Naziv_robe_ili_usluge", required = true)
    protected String nazivRobeIliUsluge;
    @XmlElement(name = "Kolicina", required = true)
    protected BigDecimal kolicina;
    @XmlElement(name = "Jedinica_mere", required = true)
    protected String jedinicaMere;
    @XmlElement(name = "Jedinicna_cena", required = true)
    protected BigDecimal jedinicnaCena;
    @XmlElement(name = "Vrednost", required = true)
    protected BigDecimal vrednost;
    @XmlElement(name = "Procenat_rabata", required = true)
    protected BigDecimal procenatRabata;
    @XmlElement(name = "Iznos_rabata", required = true)
    protected BigDecimal iznosRabata;
    @XmlElement(name = "Umanjeno_za_rabat", required = true)
    protected BigDecimal umanjenoZaRabat;
    @XmlElement(name = "Ukupan_porez", required = true)
    protected BigDecimal ukupanPorez;
    @XmlAttribute(name = "id")
    protected Long id;

    /**
     * Gets the value of the redniBroj property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRedniBroj() {
        return redniBroj;
    }

    /**
     * Sets the value of the redniBroj property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRedniBroj(BigInteger value) {
        this.redniBroj = value;
    }

    /**
     * Gets the value of the nazivRobeIliUsluge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivRobeIliUsluge() {
        return nazivRobeIliUsluge;
    }

    /**
     * Sets the value of the nazivRobeIliUsluge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivRobeIliUsluge(String value) {
        this.nazivRobeIliUsluge = value;
    }

    /**
     * Gets the value of the kolicina property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKolicina() {
        return kolicina;
    }

    /**
     * Sets the value of the kolicina property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKolicina(BigDecimal value) {
        this.kolicina = value;
    }

    /**
     * Gets the value of the jedinicaMere property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJedinicaMere() {
        return jedinicaMere;
    }

    /**
     * Sets the value of the jedinicaMere property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJedinicaMere(String value) {
        this.jedinicaMere = value;
    }

    /**
     * Gets the value of the jedinicnaCena property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getJedinicnaCena() {
        return jedinicnaCena;
    }

    /**
     * Sets the value of the jedinicnaCena property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setJedinicnaCena(BigDecimal value) {
        this.jedinicnaCena = value;
    }

    /**
     * Gets the value of the vrednost property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVrednost() {
        return vrednost;
    }

    /**
     * Sets the value of the vrednost property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVrednost(BigDecimal value) {
        this.vrednost = value;
    }

    /**
     * Gets the value of the procenatRabata property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getProcenatRabata() {
        return procenatRabata;
    }

    /**
     * Sets the value of the procenatRabata property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setProcenatRabata(BigDecimal value) {
        this.procenatRabata = value;
    }

    /**
     * Gets the value of the iznosRabata property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIznosRabata() {
        return iznosRabata;
    }

    /**
     * Sets the value of the iznosRabata property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIznosRabata(BigDecimal value) {
        this.iznosRabata = value;
    }

    /**
     * Gets the value of the umanjenoZaRabat property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUmanjenoZaRabat() {
        return umanjenoZaRabat;
    }

    /**
     * Sets the value of the umanjenoZaRabat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUmanjenoZaRabat(BigDecimal value) {
        this.umanjenoZaRabat = value;
    }

    /**
     * Gets the value of the ukupanPorez property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUkupanPorez() {
        return ukupanPorez;
    }

    /**
     * Sets the value of the ukupanPorez property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUkupanPorez(BigDecimal value) {
        this.ukupanPorez = value;
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
