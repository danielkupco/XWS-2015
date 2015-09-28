
package xws.tim7.entities.nalogzaplacanje;

import javax.xml.bind.annotation.XmlRegistry;

import xws.tim7.entities.faktura.Faktura;
import xws.tim7.entities.globals.OsnovaNalogaZaPlacanjeType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xws.tim7.nalogzaplacanje package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xws.tim7.nalogzaplacanje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrimiNalogZaPlacanje }
     * 
     */
    public PrimiNalogZaPlacanje createPrimiNalogZaPlacanje() {
        return new PrimiNalogZaPlacanje();
    }

    /**
     * Create an instance of {@link NalogZaPlacanjeType }
     * 
     */
    public NalogZaPlacanjeType createNalogZaPlacanjeType() {
        return new NalogZaPlacanjeType();
    }

    /**
     * Create an instance of {@link NalogZaPlacanjeType }
     * 
     */
    public NalogZaPlacanjeType createNalogZaPlacanjeType(Faktura faktura,String racunKupca) {
        NalogZaPlacanjeType retVal = new NalogZaPlacanjeType();
        retVal.setDatumNaloga(faktura.getZaglavlje().getDatumRacuna());
        retVal.setDatumValute(faktura.getZaglavlje().getDatumValute());
        retVal.setIDPoruke(faktura.getZaglavlje().getIDPoruke());
        retVal.setOznakaValute(faktura.getZaglavlje().getOznakaValute());
                
        xws.tim7.entities.globals.ObjectFactory factory = new xws.tim7.entities.globals.ObjectFactory();
        OsnovaNalogaZaPlacanjeType onzp = factory.createOsnovaNalogaZaPlacanjeType(faktura, racunKupca);
        retVal.setOsnovaNalogaZaPlacanje(onzp);
        
        retVal.setHitno(false);		//nije dato u specifikaciji
        
        return retVal;
    }
}
