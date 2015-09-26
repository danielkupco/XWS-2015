
package xws.tim7.mt102;

import javax.xml.bind.annotation.XmlRegistry;

import xws.tim7.nalogzaplacanje.NalogZaPlacanjeType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xws.tim7.mt102 package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xws.tim7.mt102
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrimiMT102 }
     * 
     */
    public PrimiMT102 createPrimiMT102() {
        return new PrimiMT102();
    }

    /**
     * Create an instance of {@link MT102Type }
     * 
     */
    public MT102Type createMT102Type() {
        return new MT102Type();
    }

	public MT102Type createMT102Type(NalogZaPlacanjeType nzp) {
		MT102Type retVal = new MT102Type();
		retVal.setDatum(nzp.getDatumNaloga());
		retVal.setDatumValute(nzp.getDatumValute());
		
		//TODO !!!
		retVal.setObracunskiRacunBankeDuznika(null);
		retVal.setObracunskiRacunBankePoverioca(null);
		
		retVal.setSifraValute(nzp.getOznakaValute());
		
		retVal.setSWIFTKodBankeDuznika("");
		retVal.setSWIFTKodBankePoverioca("");
		
		retVal.getNalogZaPlacanje().add(nzp);
		
		retVal.setUkupanIznos(nzp.getOsnovaNalogaZaPlacanje().getIznos());
		return retVal;
	}

}
