package xws.tim7.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.apache.openejb.server.httpd.HttpResponse;

import xws.tim7.entities.faktura.Faktura;
import xws.tim7.entities.faktura.Stavka;
import xws.tim7.entities.firma.Firma;
import xws.tim7.sessionbeans.faktura.FakturaDao;
import xws.tim7.sessionbeans.firma.FirmaDao;

@Path("/firma")
public class FirmaService {

	private static Logger log = Logger.getLogger(FirmaService.class);

	@EJB
	private FirmaDao firmaDao;
	
	@EJB
	private FakturaDao fakturaDao;
	
	
	public FirmaService() {
		//init();
	}
	
	/////////// test metode ///////////
	
	@GET
	@Path("/test")
	public Response test() {
		log.info("calling -> [firma] test");
		return Response.ok().build();
	}
		
	@POST
	@Path("/say")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response say(String message) {
		log.info("calling -> [firma] say");
		return Response.ok().entity("I say " + message).build();
	}
	
	///////////////////////////////////
	
	// #1
	@POST
	@Path("/{url_kupca}/partneri/{id_dob}/fakture")
	public Response posaljiFakturu(
			@PathParam("url_kupca") String url, 
			@PathParam("id_dob") String pib,
			Faktura faktura){

		try {
			Firma kupac = firmaDao.findByURL(url);
			Firma dobavljac = firmaDao.findByPIB(pib);
			
			if(firmaDao.isPartnerWith(kupac.getId(), dobavljac.getId())){
				fakturaDao.persist(faktura);
				return Response.created(new URI(url+"/partneri/"+pib+"/fakture/"+faktura.getId())).build();
			}else if(!firmaDao.isPartnerWith(kupac.getId(), dobavljac.getId())){
				return Response.status(HttpResponse.SC_FORBIDDEN).build();
			}
			
			
			//TODO: U slucaju neispravne fakture, odgovor je HTTP 400 Bad Request.
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		} catch (JAXBException e) {
			e.printStackTrace();
			return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		}
		
		
		return null;
		
	}
	
	// #2
	@GET
	@Path("{urlKupca}/partneri/{dobavljacId}/fakture")
	Response getInvoicesByBuyerForProvider(@PathParam("urlKupca") String urlKupca,
			@PathParam("dobavljacId") Long dobavljacId) {
		try {
			Firma kupac = firmaDao.findByURL(urlKupca);

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
		
	// #3
	@GET
	@Path("/{url}/partneri/{id_dob}/fakture/{id_fakture}")
	@Produces("application/xml")
	public Response getFaktura(
			@PathParam("url") String url,
			@PathParam("id_dob") String pib,
			@PathParam("id_fakture") Long idFakture){

		Firma kupac;
		Firma dobavljac;
		try {
			kupac = firmaDao.findByURL(url);
			dobavljac = firmaDao.findByPIB(pib);
			Faktura faktura = fakturaDao.findById(idFakture);
			
			if(firmaDao.isPartnerWith(kupac.getId(), dobavljac.getId()) && (faktura != null)){
				return Response.ok().type("application/xml").entity(faktura).build();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		} catch (JAXBException e) {
			e.printStackTrace();
			return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		}
		
		return Response.status(HttpResponse.SC_NOT_FOUND).build();
		
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
			Firma kupac = firmaDao.findByURL(urlKupca);
			
			if(firmaDao.isPartnerWith(kupac.getId(), dobavljacId)) {
				Faktura faktura = fakturaDao.findById(idFakture);
				return Response.ok().type("application/xml").entity(faktura.getStavka()).build();
			}
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return Response.status(404).build(); 
	}
		
	// #5
	@POST
	@Path("/{url}/partneri/{id_dob}/fakture/{id_fak}/stavke")
	public Response dodajStavkuFakture(
			@PathParam("url") String url,
			@PathParam("id_dob") String pib,
			@PathParam("id_fak") Long idFakture,
			Stavka stavka){
		
		Firma kupac;
		Firma dobavljac;
		Faktura faktura;
		
		
		try {
			kupac = firmaDao.findByURL(url);
			dobavljac = firmaDao.findByPIB(pib);
			faktura = fakturaDao.findById(idFakture);
			
			if(firmaDao.isPartnerWith(kupac.getId(), dobavljac.getId()) && (faktura!=null)){
				faktura.getStavka().add(stavka);
				fakturaDao.persist(faktura);
				return Response.created(new URI(url+"/partneri/"+pib+"/fakture/"+faktura.getId()+"/stavke/"+stavka.getId())).build();
			}
			if(!firmaDao.isPartnerWith(kupac.getId(), dobavljac.getId())){
				return Response.status(HttpResponse.SC_FORBIDDEN).build();
			}
			if(faktura == null){
				return Response.status(HttpResponse.SC_NOT_FOUND).build();
			}
			
			
			//TODO: U slučaju neispravne stavke, odgovor je HTTP 400 Bad Request.
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		} catch (JAXBException e) {
			e.printStackTrace();
			return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		}
		
		return null;
		
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
			Firma kupac = firmaDao.findByURL(urlKupca);
			
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
		
	// #7
	@PUT
	@Path("/{url}/partneri/{id_dob}/fakture/{id_fak}/stavke/{rbr_stav}")
	public Response izmeniStavku(
			@PathParam("url") String url,
			@PathParam("id_dob") String pib,
			@PathParam("id_fak") Long idFakture,
			@PathParam("rbr_stav") Long rbrStavke,
			Stavka stavka){
		
		Firma kupac;
		Firma dobavljac;
		Faktura faktura;
		
		try {
			kupac = firmaDao.findByURL(url);
			dobavljac = firmaDao.findByPIB(pib);
			faktura = fakturaDao.findById(idFakture);
			
			if(firmaDao.isPartnerWith(kupac.getId(), dobavljac.getId())
					&& (fakturaDao.findItemInFaktura(idFakture, rbrStavke) != null)){
				fakturaDao.updateStavka(idFakture, stavka);
				return Response.ok().build();
			}
			
			if(!firmaDao.isPartnerWith(kupac.getId(), dobavljac.getId())){
				return Response.status(HttpResponse.SC_FORBIDDEN).build();
			}
			
			if( (faktura == null) || (fakturaDao.findItemInFaktura(idFakture, rbrStavke) == null)){
				return Response.status(HttpResponse.SC_NOT_FOUND).build();
			}
			
			//TODO : u slucaju neispravne stavke HTTP 400 Bad Request.
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		} catch (JAXBException e) {
			e.printStackTrace();
			return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		}

		return null;
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
			Firma kupac = firmaDao.findByURL(urlKupca);
			
			if(firmaDao.isPartnerWith(kupac.getId(), dobavljacId)) {
				Faktura faktura = fakturaDao.findById(idFakture);
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

