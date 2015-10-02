package sessionbeans.nalogzaplacanje;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import sessionbeans.common.GenericDao;
import soap.tim7.entities.nalogzaplacanje.NalogZaPlacanjeType;
import soap.tim7.services.banka.BankaImpl;

@Stateless
@Local(NalogZaPlacanjeDaoLocal.class)
public class NalogZaPlacanjeDao extends GenericDao<NalogZaPlacanjeType, Long> implements NalogZaPlacanjeDaoLocal {

	public static final String contextPath = "soap.tim7.entities.nalogzaplacanje";
	public static final String schemaName = "nalog_za_placanje";
	
	private static Logger log = Logger.getLogger(NalogZaPlacanjeDao.class);

	
	public NalogZaPlacanjeDao() {
		super(contextPath, schemaName);
	}

	@Override
	public NalogZaPlacanjeType findByNalog(String idPorukeNaloga)
			throws IOException, JAXBException {
		
		List<NalogZaPlacanjeType> listaNaloga = findAll();
		
		for(NalogZaPlacanjeType nalog : listaNaloga){
			if(nalog.getIDPoruke().equals(idPorukeNaloga)){
				return nalog;
			}
		}
		
		return null;
	}

	@Override
	public List<NalogZaPlacanjeType> findByRacunAndDate(String brojRacuna,
			XMLGregorianCalendar datum) throws IOException, JAXBException {
		
		List<NalogZaPlacanjeType> listaNaloga = findAll();
		
		log.info("/t*******findByRacunAndDate*******");
		log.info("/tPrimljen broj racuna: " + brojRacuna);
		log.info("/tDatum: "+datum);
		
		List<NalogZaPlacanjeType> retVal= new ArrayList<NalogZaPlacanjeType>();
		log.info("------lista naloga--------");
		for(NalogZaPlacanjeType nalog : listaNaloga){
			log.info("/tnalog.racunDuznika: "+nalog.getOsnovaNalogaZaPlacanje().getRacunDuznika());
			log.info("/tnalog.racunPoverioca: "+nalog.getOsnovaNalogaZaPlacanje().getRacunPoverioca());
			log.info("/tdatum: "+nalog.getDatumNaloga());
			if((nalog.getOsnovaNalogaZaPlacanje().getRacunDuznika().getBrojRacuna().equals(brojRacuna) ||  nalog.getOsnovaNalogaZaPlacanje().getRacunPoverioca().getBrojRacuna().equals(brojRacuna))
					&& nalog.getDatumNaloga().equals(datum)){
				retVal.add(nalog);
			}
		}
		log.info("*******return****************");
		return retVal;
	}

	@Override
	public List<NalogZaPlacanjeType> findOlderByRacunAndDate(String brojRacuna,
			XMLGregorianCalendar datum) throws IOException, JAXBException {
		
		List<NalogZaPlacanjeType> listaNaloga = findAll();
		
		log.info("/t//////////----findByRacunAndDate----///////////");
		log.info("/tPrimljen broj racuna: " + brojRacuna);
		log.info("/tDatum: "+datum);
		
		
		List<NalogZaPlacanjeType> retVal = new ArrayList<NalogZaPlacanjeType>();
		
		for(NalogZaPlacanjeType nalog : listaNaloga){
			log.info("/tnalog.racunDuznika: "+nalog.getOsnovaNalogaZaPlacanje().getRacunDuznika().getBrojRacuna());
			log.info("/tnalog.racunPoverioca: "+nalog.getOsnovaNalogaZaPlacanje().getRacunPoverioca());
			log.info("/tdatum: "+nalog.getDatumNaloga());
			
			if((nalog.getOsnovaNalogaZaPlacanje().getRacunDuznika().getBrojRacuna().equals(brojRacuna) ||  nalog.getOsnovaNalogaZaPlacanje().getRacunPoverioca().getBrojRacuna().equals(brojRacuna))
					&& (nalog.getDatumNaloga().compare(datum) < 0) ){
				retVal.add(nalog);
			}
		}
		
		log.info("///////////---return---////////////");
		return retVal;
	}
}
