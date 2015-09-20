package xws.tim7.services.firma;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.apache.log4j.Logger;

import xws.tim7.entities.faktura.Faktura;
import xws.tim7.entities.faktura.Stavka;
import xws.tim7.entities.firma.Firma;
import xws.tim7.sessionbeans.faktura.FakturaDaoLocal;
import xws.tim7.sessionbeans.firma.FirmaDaoLocal;

@Path("/RisticServis")
public class RisticService {

	private static Logger log = Logger.getLogger(Firma.class);

	@EJB
	private FirmaDaoLocal firmaDao;

	@EJB
	private FakturaDaoLocal fakturaDao;

	// #2
	@GET
	@Path("{urlKupca}/partneri/{dobavljacId}/fakture")
	Response getInvoicesByBuyerForProvider(@PathParam("urlKupca") String urlKupca,
			@PathParam("dobavljacId") Long dobavljacId) {
		try {
			// IZMENI SVE OVO KORISTI firmaDao.isPartnerWith(..);
			Firma kupac = firmaDao.findByURL(urlKupca);
			Firma partner = firmaDao.findPartnerById(kupac.getId(), dobavljacId);

			// 200 OK + Lista
			if (firmaDao.isPartnerWith(kupac.getId(), dobavljacId)) {
				List<Faktura> fakture = fakturaDao.getFaktureByBuyerAndSeller(kupac.getId(), dobavljacId);
				return Response.ok().type("application/xml").entity(fakture).build();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return Response.status(404).build();
	}

	// #4
	@GET
	@Path("{urlKupca}/partneri/{dobavljacId}/fakture/{idFakture}/stavke")
	Response getInvoiceItemsByBuyerForProvider(
			@PathParam("urlKupca") String urlKupca,
			@PathParam("dobavljacId") Long dobavljacId,
			@PathParam("idFakture") Long idFakture)
	{
		try {
			// IZMENI SVE OVO KORISTI firmaDao.isPartnerWith(..);
			Firma kupac = firmaDao.findByURL(urlKupca);
			Firma partner = firmaDao.findPartnerById(kupac.getId(), dobavljacId);
			
			if(firmaDao.isPartnerWith(kupac.getId(), dobavljacId)) {
				Faktura faktura = fakturaDao.findById(idFakture);
				return Response.ok().type("application/xml").entity(faktura.getStavka()).build();
			}
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return Response.status(404).build(); 
	}

	// #6
	@GET
	@Path("{urlKupca}/partneri/{dobavljacId}/fakture/{idFakture}/stavke/{redniBroj}")
	Response getNthInvoiceItemsByBuyerForProvider(
			@PathParam("urlKupca") String urlKupca,
			@PathParam("dobavljacId") Long dobavljacId,
			@PathParam("idFakture") Long idFakture,
			@PathParam("redniBroj") int redniBroj)
	{
		try {
			// IZMENI SVE OVO KORISTI firmaDao.isPartnerWith(..);
			Firma kupac = firmaDao.findByURL(urlKupca);
			Firma partner = firmaDao.findPartnerById(kupac.getId(), dobavljacId);
			
			if(firmaDao.isPartnerWith(kupac.getId(), dobavljacId)) {
				Faktura faktura = fakturaDao.findById(idFakture);
				List<Stavka> stavke = faktura.getStavka();
				for(Stavka stavka : stavke) {
					if(stavka.getRedniBroj().equals(redniBroj)) {
						return Response.ok().type("application/xml").entity(stavka).build();
					}
				}	
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return Response.status(404).build();
	}
	
	// #8
	@DELETE
	@Path("{urlKupca}/partneri/{dobavljacId}/fakture/{idFakture}/stavke/{redniBroj}")
	Response deleteNthInvoiceItembyBuyerForProvider(
			@PathParam("urlKupca") String urlKupca,
			@PathParam("dobavljacId") Long dobavljacId,
			@PathParam("idFakture") Long idFakture,
			@PathParam("redniBroj") int redniBroj)
	{
		try {
			// IZMENI SVE OVO KORISTI firmaDao.isPartnerWith(..);
			Firma kupac = firmaDao.findByURL(urlKupca);
			Firma partner = firmaDao.findPartnerById(kupac.getId(), dobavljacId);
			
			if(firmaDao.isPartnerWith(kupac.getId(), dobavljacId)) {
				Faktura faktura = fakturaDao.findById(idFakture);
				List<Stavka> stavke = faktura.getStavka();
				for (Iterator<Stavka> iter = faktura.getStavka().iterator(); iter.hasNext(); ) {
				    Stavka item = iter.next();
				    if(item.getRedniBroj().equals(redniBroj)) {
				    	fakturaDao.removeItemFromFaktura(faktura.getId(), item.getId());
					    return Response.noContent().build();	
				    }
				}
			} else {
				// forbidden
				return Response.status(403).build();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//not found
		return Response.status(404).build();
	}
}
