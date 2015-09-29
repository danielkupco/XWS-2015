package sessionbeans.racun_firme;

import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDaoLocal;
import soap.tim7.entities.globals.RacunType;
import soap.tim7.entities.racun_firme.RacunFirme;

public interface RacunFirmeDaoLocal extends GenericDaoLocal<RacunFirme, Long> {

	public void reserveFunds(String racunKupca, BigDecimal iznos) throws IOException, JAXBException;

	public void transferFunds(String racunKupca, String racunDobavljaca,
			BigDecimal iznos) throws IOException, JAXBException;

	public void skiniSaRacuna(String racunDuznika, BigDecimal iznos) throws IOException, JAXBException;

	public void uplatiNovac(String racunDobavljaca, BigDecimal iznos) throws IOException, JAXBException;

	public RacunFirme findByRacun(String brojRacuna) throws IOException, JAXBException;
	
}
