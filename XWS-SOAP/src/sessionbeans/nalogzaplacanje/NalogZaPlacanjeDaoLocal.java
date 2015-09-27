package sessionbeans.nalogzaplacanje;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDaoLocal;
import xws.tim7.entities.nalogzaplacanje.NalogZaPlacanjeType;

public interface NalogZaPlacanjeDaoLocal extends GenericDaoLocal<NalogZaPlacanjeType, Long> {

	public NalogZaPlacanjeType findByNalog(String idPorukeNaloga)throws IOException, JAXBException;

}
