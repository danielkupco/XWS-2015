package sessionbeans.racun_firme;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDao;
import xws.tim7.entities.racun_firme.RacunFirme;

public class RacunFirmeDao extends GenericDao<RacunFirme, Long> implements RacunFirmeDaoLocal {

	public static final String contextPath = "xws.tim7.entities.racun_firme";
	public static final String schemaName = "racun_firme";
	
	public RacunFirmeDao() {
		super(contextPath, schemaName);
	}

	@Override
	public void reserveFunds(String racunKupca, BigDecimal iznos) throws IOException, JAXBException {
		RacunFirme racun = findByRacun(racunKupca);
		racun.setRezervisanaSredstva(racun.getRezervisanaSredstva().add(iznos));
		merge(racun, racun.getId());
	}

	@Override
	public void transferFunds(String racunKupca, String racunDobavljaca, BigDecimal iznos) throws IOException, JAXBException {
		
	}

	@Override
	public void skiniSaRacuna(String racunDuznika, BigDecimal iznos)
			throws IOException, JAXBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uplatiNovac(String racunDobavljaca, BigDecimal iznos)
			throws IOException, JAXBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RacunFirme findByRacun(String brojRacuna) {
		try {
			List<RacunFirme> lista = findAll();
			for(RacunFirme racun : lista) {
				if(racun.getBrojRacuna().equals(brojRacuna)) {
					return racun;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
