package xws.tim7.sessionbeans.faktura;

import java.io.IOException;
import java.util.Iterator;
import javax.xml.bind.JAXBException;
import xws.tim7.entities.faktura.Faktura;
import xws.tim7.entities.faktura.Stavka;
import xws.tim7.sessionbeans.common.GenericDao;

public class FakturaDao extends GenericDao<Faktura, Long> implements FakturaDaoLocal {

	public static final String contextPath = "xws.tim7.entities.faktura.faktura";
	public static final String schemaName = "Faktura";
	
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

}
