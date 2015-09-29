package sessionbeans.nalogzaplacanje;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;

import sessionbeans.common.GenericDaoLocal;
import xws.tim7.entities.nalogzaplacanje.NalogZaPlacanjeType;

public interface INalogZaPlacanje extends GenericDaoLocal<NalogZaPlacanjeType, Long> {
	
	public NalogZaPlacanjeType findByNalog(String idPorukeNaloga)throws IOException, JAXBException;
	
	public List<NalogZaPlacanjeType> findByRacunAndDate(String brojRacuna, XMLGregorianCalendar datum) throws IOException, JAXBException;
	
	public List<NalogZaPlacanjeType> findOlderByRacunAndDate(String brojRacuna, XMLGregorianCalendar datum) throws IOException, JAXBException;
	
	
}
