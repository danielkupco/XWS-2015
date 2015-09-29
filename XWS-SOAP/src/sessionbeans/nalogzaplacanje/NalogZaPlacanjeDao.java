package sessionbeans.nalogzaplacanje;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;

import sessionbeans.common.GenericDao;
import xws.tim7.entities.nalogzaplacanje.NalogZaPlacanjeType;

@Stateless
@Local(NalogZaPlacanjeDaoLocal.class)
@Remote(INalogZaPlacanje.class)
public class NalogZaPlacanjeDao extends GenericDao<NalogZaPlacanjeType, Long> implements NalogZaPlacanjeDaoLocal {

	public static final String contextPath = "xws.tim7.entities.nalogzaplacanje";
	public static final String schemaName = "nalog_za_placanje";
	
	
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
		
		List<NalogZaPlacanjeType> retVal= new ArrayList<NalogZaPlacanjeType>();
		for(NalogZaPlacanjeType nalog : listaNaloga){
			if((nalog.getOsnovaNalogaZaPlacanje().getRacunDuznika().equals(brojRacuna) ||  nalog.getOsnovaNalogaZaPlacanje().getRacunPoverioca().equals(brojRacuna))
					&& nalog.getDatumNaloga().equals(datum)){
				retVal.add(nalog);
			}
		}
		
		return null;
	}

	@Override
	public List<NalogZaPlacanjeType> findOlderByRacunAndDate(String brojRacuna,
			XMLGregorianCalendar datum) throws IOException, JAXBException {
		
		List<NalogZaPlacanjeType> listaNaloga = findAll();
		
		List<NalogZaPlacanjeType> retVal = new ArrayList<NalogZaPlacanjeType>();
		
		for(NalogZaPlacanjeType nalog : listaNaloga){
			if((nalog.getOsnovaNalogaZaPlacanje().getRacunDuznika().equals(brojRacuna) ||  nalog.getOsnovaNalogaZaPlacanje().getRacunPoverioca().equals(brojRacuna))
					&& (nalog.getDatumNaloga().compare(datum) < 0) ){
				retVal.add(nalog);
			}
		}
		return retVal;
	}
}
