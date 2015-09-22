package xws.tim7.sessionbeans.firma;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import xws.tim7.entities.firma.Firma;
import xws.tim7.sessionbeans.common.GenericDaoLocal;

public interface FirmaDaoLocal extends GenericDaoLocal<Firma, Long> {
	
	public boolean isPartnerWith(Long firmId, Long partnerPIB) throws IOException, JAXBException;
	
	public Firma findByURL(String url) throws IOException, JAXBException;
	
	public Firma findByPIB(String pib) throws IOException, JAXBException;
	
}
