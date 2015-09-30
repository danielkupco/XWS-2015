package xws.tim7.services;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

import soap.tim7.entities.banka.Banka;
import soap.tim7.entities.racun_firme.RacunFirme;
import soap.tim7.services.banka.Banka_BankaPort_Client;
import xws.tim7.entities.faktura.Faktura;
import xws.tim7.entities.faktura.Stavka;
import xws.tim7.entities.firma.Firma;
import xws.tim7.entities.firma.TRacuni;
import xws.tim7.sessionbeans.bank.BankaDaoLocal2;
import xws.tim7.sessionbeans.faktura.FakturaDaoLocal;
import xws.tim7.sessionbeans.firma.FirmaDaoLocal;
import xws.tim7.sessionbeans.nalog.NalogZaPlacanjeDaoLocal2;
import xws.tim7.sessionbeans.racun.RacunFirmeDaoLocal2;
import xws.tim7.util.Authenticate;
import xws.tim7.util.Tim7XMLValidator;

@Path("/firma")
public class FirmaService {

	private static Logger log = Logger.getLogger(FirmaService.class);

	@EJB
	private FirmaDaoLocal firmaDao;
	
	@EJB
	private FakturaDaoLocal fakturaDao;
	
	@EJB
	private NalogZaPlacanjeDaoLocal2 nalogDao;
	
	@EJB
	private RacunFirmeDaoLocal2 racunDao;
	
	@EJB
	private BankaDaoLocal2 bankaDao;
	
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
		
		//JAXBContext context;
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
			//firma.setId(new Long(1));
			firma.setIDFirme(1);
			firma.setNazivFirme("Firma 1");
			firma.setAdresa("Adresa firme 1");
			firma.setUrl("firma1");
			firma.setPIB("11111222223");
			Firma.Partneri fp = new Firma.Partneri();
			List<String> l1 = new ArrayList<String>();
			l1.add("44444555556");
			l1.add("77777888889");
			fp.setPib(l1);
			firma.setPartneri(fp);
			TRacuni tr = new TRacuni();
			List<String> l2 = new ArrayList<String>();
			l2.add("111-2222222222222-33");
			tr.setRacun(l2);
			firma.setRacuni(tr);
			
			firmaDao.persist(firma);
			
			firma = new Firma();
			//firma.setId(new Long(2));
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
			tr2.setRacun(l4);
			firma.setRacuni(tr2);
			
			firmaDao.persist(firma);
			
			firma = new Firma();
			//firma.setId(new Long(3));
			firma.setIDFirme(3);
			firma.setNazivFirme("Firma 3");
			firma.setAdresa("Adresa firme 3");
			firma.setUrl("firma3");
			firma.setPIB("77777888889");
			Firma.Partneri fp3 = new Firma.Partneri();
			List<String> l5 = new ArrayList<String>();
			l5.add("11111222223");
			fp3.setPib(l5);
			firma.setPartneri(fp3);
			TRacuni tr3 = new TRacuni();
			List<String> l6 = new ArrayList<String>();
			l6.add("111-8888888888888-99");
			tr3.setRacun(l6);
			firma.setRacuni(tr3);
			
			firmaDao.persist(firma);

			log.info("Firme uspesno kreirane...");
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
		log.info("Doslo je do greske...");
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
			
			Faktura faktura = (Faktura) unmarshaller.unmarshal(new File("../webapps/initData/faktura1.xml"));
			fakturaDao.persist(faktura);
			
			faktura = (Faktura) unmarshaller.unmarshal(new File("../webapps/initData/faktura2.xml"));
			fakturaDao.persist(faktura);
			
			faktura = (Faktura) unmarshaller.unmarshal(new File("../webapps/initData/faktura3.xml"));
			fakturaDao.persist(faktura);
			
			faktura = (Faktura) unmarshaller.unmarshal(new File("../webapps/initData/faktura4.xml"));
			fakturaDao.persist(faktura);

			log.info("Fakture uspesno kreirane...");
			return Response.ok().entity("Fakture uspesno kreirane...").build();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Doslo je do greske...");
		return Response.serverError().entity("Doslo je do greske...").build();
    }
	
	
	
	@GET
    @Path("initBanke")
    @Produces(MediaType.TEXT_PLAIN)
    public Response initializeBanke() {
		log.info("initBanke pogodjeno");
		log.info("banka dao: " + bankaDao);
		JAXBContext context;
		try {
			context = JAXBContext.newInstance("soap.tim7.entities.banka");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			Banka banka = (Banka) unmarshaller.unmarshal(new File("../webapps/initData/banka1.xml"));

			log.info("////// banka dao: " + bankaDao);
			log.info("////// banka: " + banka);
			
			bankaDao.persist(banka);
			
			banka = (Banka) unmarshaller.unmarshal(new File("../webapps/initData/banka2.xml"));
			bankaDao.persist(banka);

			log.info("Banke uspesno kreirane...");
			return Response.ok().entity("Banke uspesno kreirane...").build();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Doslo je do greske...");
		return Response.serverError().entity("Doslo je do greske...").build();
    }
	
	@GET
    @Path("initRacuni")
    @Produces(MediaType.TEXT_PLAIN)
    public Response initializeRacuni() {
		
		JAXBContext context;
		try {
			context = JAXBContext.newInstance("soap.tim7.entities.racun_firme");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			RacunFirme racun = (RacunFirme) unmarshaller.unmarshal(new File("../webapps/initData/racun_firme1.xml"));
			racunDao.persist(racun);
			
			racun = (RacunFirme) unmarshaller.unmarshal(new File("../webapps/initData/racun_firme2.xml"));
			racunDao.persist(racun);

			racun = (RacunFirme) unmarshaller.unmarshal(new File("../webapps/initData/racun_firme3.xml"));
			racunDao.persist(racun);
			
			log.info("Racuni uspesno kreirani...");
			return Response.ok().entity("Racuni uspesno kreirani...").build();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Doslo je do greske...");
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
			// 200 OK + Lista
			if (firmaDao.isPartnerWith(kupac.getId(), pib_dob)) {
				List<Faktura> fakture = fakturaDao.getFaktureByBuyerAndSeller(kupac.getPIB(), pib_dob);
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
		Faktura faktura;
		
		try {
			kupac = firmaDao.findByURL(url);
			faktura = fakturaDao.findById(idFakture);
			stavka = fakturaDao.findItemInFaktura(idFakture, rbrStavke);
			
			if(Tim7XMLValidator.validateFromObject(stavka, "../webapps/xws/WEB-INF/scheme/Faktura.xsd", "xws.tim7.entities.faktura")) {
				if(faktura == null || stavka == null) {
					return Response.status(HttpResponse.SC_NOT_FOUND).build();
				}
				else {
					if(firmaDao.isPartnerWith(kupac.getId(), pib)) {
						Faktura f = fakturaDao.updateStavka(idFakture, stavka);
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
				
				Faktura faktura = fakturaDao.findById(idFakture);
				
				if(faktura == null) {
					return Response.status(HttpResponse.SC_NOT_FOUND).build();
				}
				
				for (Iterator<Stavka> iter = faktura.getStavka().iterator(); iter.hasNext(); ) {
				    Stavka item = iter.next();
				   
				    if(item.getRedniBroj().intValue() == redniBroj) {
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
	
	// #3
		@GET
		@Path("/posaljiFakturu/{id_fakture}")
		@Produces(MediaType.APPLICATION_JSON)
		@Authenticate
		public Response posaljiFakturu(
				@PathParam("id_fakture") Long idFakture){

			// Firma kupac;
			// Firma dobavljac;
			try {
				//kupac = firmaDao.findByURL(url);
				//dobavljac = firmaDao.findByPIB(pib);
				Faktura faktura = fakturaDao.findById(idFakture);
				
				log.info("faktura: " + faktura);
				log.info("nalogdao: " + nalogDao);
				
				if(nalogDao.findByNalog(faktura.getZaglavlje().getIDPoruke()) == null) { // faktura jos nije poslata
					// ObjectFactory nalogFactory = new ObjectFactory();
					// //TODO uzima 1. racun, trebalo bi obezbediti biranje racuna sa kog ce se skinuti novac
					// NalogZaPlacanjeType nalog = nalogFactory.createNalogZaPlacanjeType(faktura, kupac.getRacuni().getRacun().get(0)); 
					Banka_BankaPort_Client client = new Banka_BankaPort_Client((firmaDao.findByPIB(faktura.getZaglavlje().getKupac().getPIB())).getRacuni().getRacun().get(0));
					soap.tim7.entities.faktura.Faktura f = new soap.tim7.entities.faktura.Faktura();
					
					f.setId( faktura.getId());
					
					f.setZaglavlje(new soap.tim7.entities.faktura.Faktura.Zaglavlje());
					
					f.getZaglavlje().setBrojRacuna(faktura.getZaglavlje().getBrojRacuna());
					f.getZaglavlje().setDatumRacuna(faktura.getZaglavlje().getDatumRacuna());
					f.getZaglavlje().setDatumValute(faktura.getZaglavlje().getDatumValute());
					
					//DOBAVLJAC
					f.getZaglavlje().setDobavljac(new soap.tim7.entities.faktura.TFirma());
					f.getZaglavlje().getDobavljac().setAdresa(faktura.getZaglavlje().getDobavljac().getAdresa());
					f.getZaglavlje().getDobavljac().setNaziv(faktura.getZaglavlje().getDobavljac().getNaziv());
					f.getZaglavlje().getDobavljac().setPIB(faktura.getZaglavlje().getDobavljac().getPIB());
					
					log.info("-----DODAT DOBAVLJAC : "+f.getZaglavlje().getDobavljac().getNaziv());
					
					////
					
					f.getZaglavlje().setIDPoruke(faktura.getZaglavlje().getIDPoruke());
					f.getZaglavlje().setIznosZaUplatu(faktura.getZaglavlje().getIznosZaUplatu());
					
					////KUPAC
					f.getZaglavlje().setKupac(new soap.tim7.entities.faktura.TFirma());
					f.getZaglavlje().getKupac().setAdresa(faktura.getZaglavlje().getKupac().getAdresa());
					f.getZaglavlje().getKupac().setNaziv(faktura.getZaglavlje().getKupac().getNaziv());
					f.getZaglavlje().getKupac().setPIB(faktura.getZaglavlje().getKupac().getPIB());
					
					log.info("-----DODAT KUPAC :"+f.getZaglavlje().getKupac().getNaziv());
					/////
					
					
					f.getZaglavlje().setOznakaValute(faktura.getZaglavlje().getOznakaValute());
					f.getZaglavlje().setUkupanPorez(faktura.getZaglavlje().getUkupanPorez());
					f.getZaglavlje().setUkupanRabat(faktura.getZaglavlje().getUkupanRabat());
					f.getZaglavlje().setUkupnoRobaIUsluge(faktura.getZaglavlje().getUkupnoRobaIUsluge());
					f.getZaglavlje().setUplataNaRacun(faktura.getZaglavlje().getUplataNaRacun());
					f.getZaglavlje().setVrednostRobe(faktura.getZaglavlje().getVrednostRobe());
					f.getZaglavlje().setVrednostUsluga(faktura.getZaglavlje().getVrednostUsluga());
					
					
					List<soap.tim7.entities.faktura.Stavka> lista = new ArrayList<soap.tim7.entities.faktura.Stavka>();

					
					for(Stavka s : faktura.getStavka()){
						soap.tim7.entities.faktura.Stavka ss = new soap.tim7.entities.faktura.Stavka();
						ss.setId(s.getId());
						ss.setIznosRabata(s.getIznosRabata());
						ss.setJedinicaMere(s.getJedinicaMere());
						ss.setJedinicnaCena(s.getJedinicnaCena());
						ss.setKolicina(s.getKolicina());
						ss.setNazivRobeIliUsluge(s.getNazivRobeIliUsluge());
						ss.setProcenatRabata(s.getProcenatRabata());
						ss.setRedniBroj(s.getRedniBroj());
						ss.setUkupanPorez(s.getUkupanPorez());
						ss.setUmanjenoZaRabat(s.getUmanjenoZaRabat());
						ss.setVrednost(s.getVrednost());
						
						lista.add(ss);
					}
					
					f.setStavka(lista);

					log.info("-----dodata lista stavki");
					
					client.posaljiNalogZaPlacanje(f);
					return Response.ok().build();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JAXBException e) {
				e.printStackTrace();
			}

			return Response.status(HttpResponse.SC_BAD_REQUEST).build();
			
		}
		
}

