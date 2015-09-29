package xws.tim7.sessionbeans.nalog;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;

import xws.tim7.sessionbeans.common.GenericDaoLocal;
import xws.tim7.entities.nalogzaplacanje.NalogZaPlacanjeType;

public interface NalogZaPlacanjeDaoLocal2 extends GenericDaoLocal<NalogZaPlacanjeType, Long> {

	public NalogZaPlacanjeType findByNalog(String idPorukeNaloga)throws IOException, JAXBException;
	
	public List<NalogZaPlacanjeType> findByRacunAndDate(String brojRacuna, XMLGregorianCalendar datum) throws IOException, JAXBException;
	
	public List<NalogZaPlacanjeType> findOlderByRacunAndDate(String brojRacuna, XMLGregorianCalendar datum) throws IOException, JAXBException;
	
}
