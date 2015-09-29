package xws.tim7.sessionbeans.bank;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDao;
import soap.tim7.entities.banka.Banka;

@Stateless
@Local(BankaDaoLocal2.class)
public class BankaDao2 extends GenericDao<Banka, Long> implements BankaDaoLocal2 {

	public static final String contextPath = "soap.tim7.entities.banka";
	public static final String schemaName = "banka";
	
	public BankaDao2() {
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
