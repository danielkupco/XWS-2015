package xws.tim7.services.firma1;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.apache.openejb.server.httpd.HttpResponse;

import xws.tim7.entities.faktura.Faktura;
import xws.tim7.entities.faktura.Stavka;
import xws.tim7.entities.firma.Firma;
import xws.tim7.sessionbeans.faktura.FakturaDao;
import xws.tim7.sessionbeans.firma.FirmaDao;


@Path("/")
public class OddServices {
	
	@EJB
	private FirmaDao firmaDao;
	
	@EJB
	private FakturaDao fakturaDao;
	
	
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
			
			if(firmaDao.isPartnerWith(kupac.getId(), dobavljac.getId())){
				return Response.ok().type("application/xml").entity(fakturaDao.findById(idFakture)).build();
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
	
	
	
	@PUT
	@Path("{url}/partneri/{id_dob}/fakture/{id_fak}/stavke/{rbr_stav}")
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
	
}
