package sessionbeans.mt102;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDaoLocal;
import xws.tim7.entities.banka.Banka;
import xws.tim7.entities.mt102.MT102Type;
import xws.tim7.entities.nalogzaplacanje.NalogZaPlacanjeType;

public interface MT102DaoLocal extends GenericDaoLocal<MT102Type, Long> {

	public void addNalog(NalogZaPlacanjeType nalogZaPlacanje, Banka bankaDuznika, Banka bankaPoverioca) throws IOException, JAXBException;

	public MT102Type findByMT910Id(String idPoruke) throws IOException, JAXBException;
	
}
