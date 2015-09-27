package xws.tim7.sessionbeans.faktura;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.apache.openjpa.lib.log.Log;

import xws.tim7.entities.faktura.Faktura;
import xws.tim7.entities.faktura.Stavka;
import xws.tim7.services.FirmaService;
import xws.tim7.sessionbeans.common.GenericDao;

@Stateless
@Local(FakturaDaoLocal.class)
public class FakturaDao extends GenericDao<Faktura, Long> implements FakturaDaoLocal {

	private static Logger log = Logger.getLogger(FakturaDaoLocal.class);
	
	public static final String contextPath = "xws.tim7.entities.faktura";
	public static final String schemaName = "faktura";
	
	public FakturaDao() {
		super(contextPath, schemaName);
	}

	@Override
	public Stavka findItemInFaktura(Long fakturaId, Long stavkaId) throws IOException, JAXBException {
		Faktura faktura = findById(fakturaId);
		
			for (Stavka item : faktura.getStavka()) {
				if(stavkaId.equals(item.getRedniBroj().longValue())) {
					return item;
				}
			}
		
		return null;
	}

	@Override
	public Faktura removeItemFromFaktura(Long FakturaId, BigInteger redBroj) throws IOException, JAXBException {
		Faktura faktura = findById(FakturaId);
		
		if (faktura instanceof Faktura) {
			for (Iterator<Stavka> iter = faktura.getStavka().iterator(); iter.hasNext(); ) {
			    Stavka item = iter.next();
			    if (item.getRedniBroj().equals(redBroj)) {
			        iter.remove();
			    }
			}
		}
		
		for(int i = 0; i < faktura.getStavka().size(); ++i){
    		Stavka s = faktura.getStavka().get(i);
    		s.setRedniBroj(BigInteger.valueOf(i+1));
    	}
		
		return merge(faktura, FakturaId);
	}

	@Override
	public Faktura createStavka(Long FakturaId, Stavka item) throws IOException, JAXBException {
		Faktura faktura = findById(FakturaId);
		
		if(faktura instanceof Faktura) {
			faktura.getStavka().add(item);
		}
		
		return merge(faktura, FakturaId);
	}

	@Override
	public Faktura updateStavka(Long FakturaId, Stavka item) throws IOException, JAXBException {
		Faktura faktura = findById(FakturaId);
		
		for (Iterator<Stavka> iter = faktura.getStavka().iterator(); iter.hasNext(); ) {
		    Stavka stavka = iter.next();
		    if (stavka.getRedniBroj().equals(item.getRedniBroj())) {
		        iter.remove();
		        break;
		    }
		}
		faktura.getStavka().add(item);

		return merge(faktura, FakturaId);
	}

	@Override
	public List<Faktura> getFaktureByBuyerAndSeller(String buyerPIB, String sellerPIB) throws IOException, JAXBException {
		// TODO Auto-generated method stub
		// pokupis fakturu, proveris u zaglavlju kupca/dobavljaca pa dodas u retList
		List<Faktura> retVal = new ArrayList<Faktura>();
		List<Faktura> fakture = findAll();
		for(Faktura f : fakture) {
			if(f.getZaglavlje().getKupac().getPIB().equals(buyerPIB) && f.getZaglavlje().getDobavljac().getPIB().equals(sellerPIB))
				retVal.add(f);
		}
		return retVal;
	}

}
