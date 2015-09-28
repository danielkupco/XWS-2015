package sessionbeans.mt102;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.xml.bind.JAXBException;

import sessionbeans.banka.BankaDaoLocal;
import sessionbeans.common.GenericDao;
import xws.tim7.entities.banka.Banka;
import xws.tim7.entities.mt102.MT102Type;
import xws.tim7.entities.nalogzaplacanje.NalogZaPlacanjeType;

public class MT102Dao extends GenericDao<MT102Type, Long> implements MT102DaoLocal {

	public static final String contextPath = "xws.tim7.entities.mt102";
	public static final String schemaName = "nalog_za_grupna_placanja_mt102";
	
	public MT102Dao() {
		super(contextPath, schemaName);
	}

	@Override
	public void addNalog(NalogZaPlacanjeType nalogZaPlacanje, Banka bankaDuznika, Banka bankaPoverioca)
			throws IOException, JAXBException {
		List<MT102Type> mt102list = findAll();

		String bankaPoveriocaIzNaloga = nalogZaPlacanje.getOsnovaNalogaZaPlacanje().getRacunPoverioca().getBrojRacuna().substring(0, 3);
		String bankaDuznikaIzNaloga = nalogZaPlacanje.getOsnovaNalogaZaPlacanje().getRacunDuznika().getBrojRacuna().substring(0, 3);
		for (MT102Type item : mt102list) {
			String idBankePoverioca = item.getObracunskiRacunBankePoverioca().substring(0, 3);
			String idBankeDuznika = item.getObracunskiRacunBankeDuznika().substring(0, 3);
			if (idBankePoverioca.equals(bankaPoveriocaIzNaloga) && idBankeDuznika.equals(bankaDuznikaIzNaloga)) {
				// ako je nasao, dodaj stavku, update bazu, kraj !
				item.getNalogZaPlacanje().add(nalogZaPlacanje);
				// update-uj ukupan iznos
				item.getUkupanIznos().add(nalogZaPlacanje.getOsnovaNalogaZaPlacanje().getIznos());
				this.merge(item, item.getId());
				return;
			}
		}
		
		// ako nije nasao, stici ce dovde
		xws.tim7.entities.mt102.ObjectFactory factory = new xws.tim7.entities.mt102.ObjectFactory();
		// potrebne informacije: SWIFT banke duznika/poverioca, Obracunski racun banke duznika/poverioca
		MT102Type mt102 = factory.createMT102Type(nalogZaPlacanje);	
		// entityManager.findSWIFTBankeDuznika/Poverioca + ObracunskiRacunBankeDuznika/Poverioca + set na mt102
		
		mt102.setSWIFTKodBankeDuznika(bankaDuznika.getSWIFT());
		mt102.setObracunskiRacunBankeDuznika(bankaDuznika.getObracunskiRacun());
		mt102.setSWIFTKodBankePoverioca(bankaPoverioca.getSWIFT());
		mt102.setObracunskiRacunBankePoverioca(bankaPoverioca.getObracunskiRacun());
		
		//mt102.setId(em.getIdentity()); ovo se radi u GenericDao
		this.persist(mt102);
	}

	@Override
	public MT102Type findByMT910Id(String idPoruke) throws IOException,
			JAXBException {
		List<MT102Type> list = findAll();
		for (MT102Type item : list) {
			if (item.getIDPoruke().equals(idPoruke)) {
				return item;
			}
		}
		return null;
	}
	
}
