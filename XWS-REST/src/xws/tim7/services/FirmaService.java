package xws.tim7.services;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.apache.openejb.server.httpd.HttpResponse;

import xws.tim7.entities.faktura.Faktura;
import xws.tim7.entities.faktura.Stavka;
import xws.tim7.entities.firma.Firma;
import xws.tim7.entities.firma.TRacuni;
import xws.tim7.sessionbeans.faktura.FakturaDaoLocal;
import xws.tim7.sessionbeans.firma.FirmaDaoLocal;
import xws.tim7.util.Authenticate;
import xws.tim7.util.Tim7XMLValidator;

@Path("/firma")
public class FirmaService {

	private static Logger log = Logger.getLogger(FirmaService.class);

	@EJB
	private FirmaDaoLocal firmaDao;
	
	@EJB
	private FakturaDaoLocal fakturaDao;
	
	
	public FirmaService() {
		//init();
	}
	
	/////////// test metode ///////////
	
	@GET
	@Path("/{broj}/test")
	public Response test(@PathParam("broj") Long broj) {
		log.info("calling -> [firma] test, broj = " + broj);
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
	
	/////////// init metode ///////////
	
	@GET
    @Path("initFirme")
    @Produces(MediaType.TEXT_PLAIN)
    public Response initializeFirme() {
		
		JAXBContext context;
		try {
//			context = JAXBContext.newInstance("xws.tim7.entities.firma");
//			Unmarshaller unmarshaller = context.createUnmarshaller();
//			
//			log.info("creating firma 1...");
//			Firma firma = (Firma) unmarshaller.unmarshal(new File("../webapps/initData/firma1.xml"));
//			
//			log.info(firma.toString());
//			log.info(firma.getId() + " - " + firma.getNazivFirme());
//			log.info("firma 1 created...");
//			firmaDao.persist(firma);
//			log.info("firma 1 persisted...");
//			
//			log.info("creating firma 2...");
//			firma = (Firma) unmarshaller.unmarshal(new File("../webapps/initData/firma2.xml"));
//
//			log.info(firma.getId() + " - " + firma.getNazivFirme());
//			log.info("firma 2 created...");
//			firmaDao.persist(firma);
//			log.info("firma 2 persisted...");
			
			log.info("firmaDao " + firmaDao);
			
			Firma firma = new Firma();
			firma.setId(new Long(1));
			firma.setIDFirme(1);
			firma.setNazivFirme("Firma 1");
			firma.setAdresa("Adresa firme 1");
			firma.setUrl("firma1");
			firma.setPIB("11111222223");
			Firma.Partneri fp = new Firma.Partneri();
			List<String> l1 = new ArrayList<String>();
			l1.add("44444555556");
			fp.setPib(l1);
			firma.setPartneri(fp);
			TRacuni tr = new TRacuni();
			List<String> l2 = new ArrayList<String>();
			l2.add("111-2222222222222-33");
			firma.setRacuni(tr);
			
			firmaDao.persist(firma);
			
			firma = new Firma();
			firma.setId(new Long(2));
			firma.setIDFirme(2);
			firma.setNazivFirme("Firma 2");
			firma.setAdresa("Adresa firme 2");
			firma.setUrl("firma2");
			firma.setPIB("44444555556");
			Firma.Partneri fp2 = new Firma.Partneri();
			List<String> l3 = new ArrayList<String>();
			l3.add("11111222223");
			fp2.setPib(l3);
			firma.setPartneri(fp2);
			TRacuni tr2 = new TRacuni();
			List<String> l4 = new ArrayList<String>();
			l4.add("444-5555555555555-66");
			firma.setRacuni(tr2);
			
			firmaDao.persist(firma);

			return Response.ok().entity("Firme uspesno kreirane...").build();
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().entity("Doslo je do greske...").build();
    }
	
	@GET
    @Path("initFakture")
    @Produces(MediaType.TEXT_PLAIN)
    public Response initializeFakture() {
		
		JAXBContext context;
		try {
			context = JAXBContext.newInstance("xws.tim7.entities.faktura");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			log.info("creating faktura 1...");
			Faktura faktura = (Faktura) unmarshaller.unmarshal(new File("../webapps/initData/faktura1.xml"));
			
			log.info("faktura 1 created...");
			fakturaDao.persist(faktura);
			log.info("faktura 1 persisted...");
			
			log.info("creating faktura 2...");
			faktura = (Faktura) unmarshaller.unmarshal(new File("../webapps/initData/faktura2.xml"));
			
			log.info("faktura 2 created...");
			fakturaDao.persist(faktura);
			log.info("faktura 2 persisted...");

			return Response.ok().entity("Fakture uspesno kreirane...").build();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.serverError().entity("Doslo je do greske...").build();
    }
	
	/////////////////////////////////////////
	
	/////////////// custom get metode ///////////////
	
	@GET
	@Path("/firme")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Firma> getFirme() {
		log.info("get firme...");
		List<Firma> firme = new ArrayList<Firma>();
		try {
			firme = firmaDao.findAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("ukupno " + firme.size());
		return firme;
	}
		
	///////////////////////////////////////////
	
	// #1
	@POST
	@Path("/{url_kupca}/partneri/{pib_dob}/fakture")
	@Consumes(MediaType.APPLICATION_JSON)
	@Authenticate
	public Response posaljiFakturu(
			@PathParam("url_kupca") String url, 
			@PathParam("pib_dob") String pib,
			Faktura faktura){

		
		for(int i = 0; i < faktura.getStavka().size(); ++i){
			faktura.getStavka().get(i).setRedniBroj(BigInteger.valueOf(i+1));
		}
		
		if(Tim7XMLValidator.validateFromObject(faktura, "../webapps/xws/WEB-INF/scheme/Faktura.xsd", "xws.tim7.entities.faktura")) {
		
			try {
				Firma kupac = firmaDao.findByURL(url);
				//Firma dobavljac = firmaDao.findByPIB(pib);
				
				if(firmaDao.isPartnerWith(kupac.getId(), pib)){
					Faktura nova = fakturaDao.persist(faktura);
					log.info("faktura je kreirana sa id-em: " + nova.getId());
					URI location = new URI(url+"/partneri/"+pib+"/fakture/"+faktura.getId());
					return Response.created(location).header("Content-Location", location).build();
				} else {
					return Response.status(HttpResponse.SC_FORBIDDEN).build();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		else {
			return Response.status(HttpResponse.SC_BAD_REQUEST).build();
		}
		
		return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		
	}
	
	// #2
	@GET
	@Authenticate
	@Path("/{urlKupca}/partneri/{pib_dob}/fakture")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInvoicesByBuyerForProvider(@PathParam("urlKupca") String urlKupca,
			@PathParam("pib_dob") String pib_dob) {
		try {
			Firma kupac = firmaDao.findByURL(urlKupca);
			log.info("urlKupca " + urlKupca);
			log.info("kupac " + kupac);
			log.info("pib " + pib_dob);
			log.info("firmaDao " + firmaDao);
			log.info("fakturaDao " + fakturaDao);
			// 200 OK + Lista
			if (firmaDao.isPartnerWith(kupac.getId(), pib_dob)) {
				List<Faktura> fakture = fakturaDao.getFaktureByBuyerAndSeller(kupac.getPIB(), pib_dob);
				log.info("fakture za json: " + fakture.size());
				return Response.ok().entity(fakture).build();
			}
			else {
				return Response.status(HttpResponse.SC_NOT_FOUND).build();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
	}
		
	// #3
	@GET
	@Path("/{url}/partneri/{pib_dob}/fakture/{id_fakture}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public Response getFaktura(
			@PathParam("url") String url,
			@PathParam("pib_dob") String pib,
			@PathParam("id_fakture") Long idFakture){

		Firma kupac;
		//Firma dobavljac;
		try {
			kupac = firmaDao.findByURL(url);
			//dobavljac = firmaDao.findByPIB(pib);
			Faktura faktura = fakturaDao.findById(idFakture);
			
			if(firmaDao.isPartnerWith(kupac.getId(), pib) && (faktura != null)){
				//return Response.ok().entity(faktura).build();
				return Response.ok().entity(faktura).build();
			}
			else {
				return Response.status(HttpResponse.SC_NOT_FOUND).build();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
		
	}
	
	// #4
	@GET
	@Path("{urlKupca}/partneri/{pib_dob}/fakture/{idFakture}/stavke")
	@Authenticate
	public Response getInvoiceItemsByBuyerForProvider(
			@PathParam("urlKupca") String urlKupca,
			@PathParam("pib_dob") String pib_dob,
			@PathParam("idFakture") Long idFakture)
	{
		try {
			Firma kupac = firmaDao.findByURL(urlKupca);
			Faktura faktura = fakturaDao.findById(idFakture);
			
			if(firmaDao.isPartnerWith(kupac.getId(), pib_dob) && faktura != null) {
				// type("application/xml")
				return Response.ok().entity(faktura.getStavka()).build();
			}
			else {
				return Response.status(HttpResponse.SC_NOT_FOUND).build();
			}
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
	}
		
	// #5
	@POST
	@Path("/{url}/partneri/{pib_dob}/fakture/{id_fak}/stavke")
	@Authenticate
	public Response dodajStavkuFakture(
			@PathParam("url") String url,
			@PathParam("pib_dob") String pib,
			@PathParam("id_fak") Long idFakture,
			Stavka stavka){
		
		Firma kupac;
		Firma dobavljac;
		Faktura faktura;
		
		
		try {
			kupac = firmaDao.findByURL(url);
			dobavljac = firmaDao.findByPIB(pib);
			faktura = fakturaDao.findById(idFakture);
			
			stavka.setRedniBroj(BigInteger.valueOf(faktura.getStavka().size()+1));
			
			log.info("***NOVA STAVKA**** ---->"+stavka.getNazivRobeIliUsluge());
			log.info("***NOVA STAVKA[redniBroj] -----> "+stavka.getRedniBroj());
			
			if(Tim7XMLValidator.validateFromObject(stavka, "../webapps/xws/WEB-INF/scheme/Faktura.xsd", "xws.tim7.entities.faktura")) {
				if(faktura != null) {
					if(firmaDao.isPartnerWith(kupac.getId(), pib)) {
						//fakturaDao.createStavka(idFakture, stavka);
						
						faktura.getStavka().add(stavka);
						fakturaDao.merge(faktura, idFakture);
		
						URI location = new URI(url+"/partneri/"+pib+"/fakture/"+faktura.getId()+"/stavke/"+stavka.getRedniBroj());
						return Response.created(location).header("Content-Location", location).build();
					}
					else {
						return Response.status(HttpResponse.SC_FORBIDDEN).build();
					}
				}
				else {
					return Response.status(HttpResponse.SC_NOT_FOUND).build();
				}
			}
			else {
				return Response.status(HttpResponse.SC_BAD_REQUEST).build();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
	}
		
	// #6
	@GET
	@Path("{urlKupca}/partneri/{pib_dob}/fakture/{idFakture}/stavke/{redniBroj}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public Response getNthInvoiceItemsByBuyerForProvider(
			@PathParam("urlKupca") String urlKupca,
			@PathParam("pib_dob") String pib,
			@PathParam("idFakture") Long idFakture,
			@PathParam("redniBroj") int redniBroj)
	{
		try {
			Firma kupac = firmaDao.findByURL(urlKupca);
			
			if(firmaDao.isPartnerWith(kupac.getId(), pib)) {
				Faktura faktura = fakturaDao.findById(idFakture);
				List<Stavka> stavke = faktura.getStavka();
				for(Stavka stavka : stavke) {
					if(stavka.getRedniBroj().equals(redniBroj)) {
						return Response.ok().entity(stavka).build();
					}
				}	
			}
			else {
				return Response.status(HttpResponse.SC_NOT_FOUND).build();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
	}
		
	// #7
	@PUT
	@Path("/{url}/partneri/{id_dob}/fakture/{id_fak}/stavke/{rbr_stav}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Authenticate
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
			stavka = fakturaDao.findItemInFaktura(idFakture, rbrStavke);
			
			if(Tim7XMLValidator.validateFromObject(stavka, "../webapps/xws/WEB-INF/scheme/Faktura.xsd", "xws.tim7.entities.faktura")) {
				
				if(faktura == null || stavka == null) {
					return Response.status(HttpResponse.SC_NOT_FOUND).build();
				}
				else {
					if(firmaDao.isPartnerWith(kupac.getId(), pib)) {
						fakturaDao.updateStavka(idFakture, stavka);
						return Response.ok().build();
					}
					else {
						return Response.status(HttpResponse.SC_FORBIDDEN).build();
					}
				}
			}
			else {
				return Response.status(HttpResponse.SC_BAD_REQUEST).build();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
	}
	
	// #8
	@DELETE
	@Path("{urlKupca}/partneri/{pib_dob}/fakture/{idFakture}/stavke/{redniBroj}")
	@Authenticate
	public Response deleteNthInvoiceItembyBuyerForProvider(
			@PathParam("urlKupca") String urlKupca,
			@PathParam("pib_dob") String pib,
			@PathParam("idFakture") Long idFakture,
			@PathParam("redniBroj") int redniBroj)
	{
		try {
			Firma kupac = firmaDao.findByURL(urlKupca);
			
			if(firmaDao.isPartnerWith(kupac.getId(), pib)) {
				
				log.info("***(1)DELETE TEST*** -------------->isPartnerWith je true");
				
				Faktura faktura = fakturaDao.findById(idFakture);
				
				log.info("***(2)DELETE TEST*** -------------->idFakture: " + idFakture);
				
				if(faktura == null) {
					return Response.status(HttpResponse.SC_NOT_FOUND).build();
				}
				
				for (Iterator<Stavka> iter = faktura.getStavka().iterator(); iter.hasNext(); ) {
				    Stavka item = iter.next();
				    
				    log.info("***DELETE TEST*** (redniBrojStavke:"+item.getRedniBroj()+") uporedjuje se sa redniBroj= " +redniBroj);
				    
				    if(item.getRedniBroj().intValue() == redniBroj) {
				    	log.info("***DELETE TEST***-------------->TRUE-uso u if");
				    	fakturaDao.removeItemFromFaktura(faktura.getId(), item.getRedniBroj());
				    	
					    return Response.noContent().build();	
				    }
				}

				return Response.status(HttpResponse.SC_NOT_FOUND).build();
			} 
			else {
				return Response.status(HttpResponse.SC_FORBIDDEN).build();
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return Response.status(HttpResponse.SC_INTERNAL_SERVER_ERROR).build();
	}
	
		
}

