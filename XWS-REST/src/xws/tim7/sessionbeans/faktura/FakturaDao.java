package xws.tim7.sessionbeans.faktura;

import java.io.IOException;
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
		
		if (faktura instanceof Faktura) {
			for (Stavka item : faktura.getStavka()) {
				if(stavkaId.equals(item.getId())) {
					return item;
				}
			}
		}
		
		return null;
	}

	@Override
	public Faktura removeItemFromFaktura(Long FakturaId, Long itemId) throws IOException, JAXBException {
		Faktura faktura = findById(FakturaId);
		
		if (faktura instanceof Faktura) {
			for (Iterator<Stavka> iter = faktura.getStavka().iterator(); iter.hasNext(); ) {
			    Stavka item = iter.next();
			    if (item.getId().equals(itemId)) {
			        iter.remove();
			    }
			}
		}
		
		return merge(faktura, FakturaId);
	}

	@Override
	public Faktura createStavka(Long FakturaId, Stavka item) throws IOException, JAXBException {
		Faktura faktura = findById(FakturaId);
		
		if(faktura instanceof Faktura) {
			item.setId(em.getIdentity());
			faktura.getStavka().add(item);
		}
		
		return merge(faktura, FakturaId);
	}

	@Override
	public Faktura updateStavka(Long FakturaId, Stavka item) throws IOException, JAXBException {
		Faktura faktura = findById(FakturaId);
		
		if(faktura instanceof Faktura) {
			for (Iterator<Stavka> iter = faktura.getStavka().iterator(); iter.hasNext(); ) {
			    Stavka stavka = iter.next();
			    if (stavka.getId().equals(item.getId())) {
			        iter.remove();
			    }
			}
			faktura.getStavka().add(item);
		}
		
		return merge(faktura, FakturaId);
	}

	@Override
	public List<Faktura> getFaktureByBuyerAndSeller(String buyerPIB, String sellerPIB) throws IOException, JAXBException {
		// TODO Auto-generated method stub
		// pokupis fakturu, proveris u zaglavlju kupca/dobavljaca pa dodas u retList
		List<Faktura> retVal = new ArrayList<Faktura>();
		List<Faktura> fakture = findAll();
		log.info("sve fakture: " + fakture.size());
		for(Faktura f : fakture) {
			log.info("buyerPIB: " + buyerPIB);
			log.info("f kupac PIB: " + f.getZaglavlje().getKupac().getPIB());
			log.info("sellerPIB: " + sellerPIB);
			log.info("f dobavljac PIB: " + f.getZaglavlje().getDobavljac().getPIB());
			if(f.getZaglavlje().getKupac().getPIB().equals(buyerPIB) && f.getZaglavlje().getDobavljac().getPIB().equals(sellerPIB))
				retVal.add(f);
		}
		return retVal;
	}

}
