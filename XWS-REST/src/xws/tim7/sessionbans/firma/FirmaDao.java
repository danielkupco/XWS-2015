package xws.tim7.sessionbans.firma;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;

import xws.tim7.entities.firma.Firma;
import xws.tim7.sessionbeans.common.GenericDao;


@Stateless
@Local(FirmaDaoLocal.class)
public class FirmaDao extends GenericDao<Firma, Long> implements FirmaDaoLocal {

	
	public static final String contextPath = "xws.tim7.entities.firma";
	
	public static final String schemaName = "Firma";
	
	public FirmaDao() {
		super(contextPath, schemaName);
	}

	@Override
	public List<Firma> getPartners(Long firmId) throws IOException,
			JAXBException {
		
		Firma frm = findById(firmId);
		return frm.getPartneri().getFirma();
		
		
	}

	@Override
	public Firma findPartnerById(Long firmId, Long partnerId)
			throws IOException, JAXBException {
		
		Firma frm = findById(firmId);
		
		for(Firma f : frm.getPartneri().getFirma()){
			if(f.getId().equals(partnerId))
				return f;
		}
		
		return null;
		
	}

	@Override
	public boolean isPartnerWith(Long firmId, Long partnerId)
			throws IOException, JAXBException {
		
		Firma frm = findById(firmId);
		
		for(Firma f : frm.getPartneri().getFirma()){
			if(f.getId().equals(partnerId))
				return true;
		}
		
		return false;
	}

	
	
	
	
	
}
