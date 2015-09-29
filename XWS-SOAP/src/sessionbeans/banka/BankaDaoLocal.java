package sessionbeans.banka;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDaoLocal;
import soap.tim7.entities.banka.Banka;
import soap.tim7.entities.globals.MT9XXType;
import soap.tim7.entities.globals.RacunType;

public interface BankaDaoLocal extends GenericDaoLocal<Banka, Long> {

	public Banka findBankaByObracunskiRacun(String obracunskiRacunBankePoverioca) throws IOException, JAXBException;
	public Banka findBankaByIDBanke(String idBanke) throws IOException, JAXBException;
}
