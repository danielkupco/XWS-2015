package sessionbeans.banka;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDao;
import xws.tim7.entities.banka.Banka;

public class BankaDao extends GenericDao<Banka, Long> implements BankaDaoLocal {

	public static final String contextPath = "xws.tim7.entities.banka";
	public static final String schemaName = "banka";
	
	public BankaDao() {
		super(contextPath, schemaName);
	}

	@Override
	public Banka findBankaByObracunskiRacun(String obracunskiRacunBanke)
			throws IOException, JAXBException {
		List<Banka> list = findAll();
		for (Banka item : list) {
			if (item.getObracunskiRacun().equals(obracunskiRacunBanke)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public Banka findBankaByIDBanke(String idBanke) throws IOException, JAXBException {
		List<Banka> banke = findAll();
		for(Banka b : banke) {
			if(b.getIDBanke().equals(idBanke)) {
				return b;
			}
		}
		return null;
	}

}
