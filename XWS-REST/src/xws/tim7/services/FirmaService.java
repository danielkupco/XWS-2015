package xws.tim7.services;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

@Path("/api/")
public class FirmaService {

	private static Logger log = Logger.getLogger(FirmaService.class);

	@EJB
	private FakturaDaoLocal faktureDao;
	
	@EJB
	private FirmaDaoLocal firmaDao;

	@Context
	private HttpServletRequest request;

	// Potrebno za frontend

	@POST
	@Path("partneri/{id_dobavljaca}/fakture/") // <url_kupca>/partneri/<id_dobavljaca>/fakture/
	@Consumes(MediaType.APPLICATION_XML)
	@Authenticate
	public Response createNeposlataFaktura(Faktura faktura) {
		System.out.println("Napravi fakturu za slanje");
		System.out.println("User: "
				+ ((User)request.getSession().getAttribute("user")).getUsername());

		if (!ValidationUtil.isValid("http://localhost:8080/scheme/faktura.xsd",
				Faktura.class, faktura)) {
			System.out.println("\nNije validno");
			return Response.status(Status.BAD_REQUEST).entity("Faktura nije validna").build();
		}

		User user;
		try {
			user = userDao.findById(((User)request.getSession().getAttribute("user")).getUsername());
		} catch (Exception e) {
			return Response.status(Status.FORBIDDEN).build();
		}
		
		if (!user.checkAuthorization("unosFakture")) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		if (!ValidationUtil.isValid("http://localhost:8080/scheme/faktura.xsd",
				Faktura.class, faktura)) {
			System.out.println("\nNije validno");
			return Response.status(Status.BAD_REQUEST).entity("Faktura nije validna!").build();
		}

		// ako nema id-a = nova faktura, samo odgovorno lice (radnik) moze da
		// napravi novu fakturu
		try {
			fakturaDao.createNeposlatuFakturu(faktura);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@POST
	@Path("fakture/{id_fakture}")
	@Consumes(MediaType.APPLICATION_XML)
	@Authenticate
	public Response putNeposlataFaktura(
			@PathParam("id_fakture") Long id_fakture, Faktura faktura) {
		System.out.println("Izmeni fakturu za slanje");
		System.out.println("User: "
				+ ((User)request.getSession().getAttribute("user")).getUsername());

		
		User user;
		try {
			user = userDao.findById(((User)request.getSession().getAttribute("user")).getUsername());
		} catch (Exception e) {
			return Response.status(Status.FORBIDDEN).build();
		}
		
		if (!ValidationUtil.isValid("http://localhost:8080/scheme/faktura.xsd",
				Faktura.class, faktura)) {
			System.out.println("\nNije validno");
			return Response.status(Status.BAD_REQUEST).entity("Faktura nije validna!").build();
		}

		// ima id, provera da li postoji u neposlatim fakturama taj id,
		// proverava se cena koja je u toj fakturu u bazi i
		// proverava se da li je samo cena izmenjena i proverava se
		// autorizacija
		try {
			Faktura fakturaUBazi = fakturaDao.findById(id_fakture);
			if (!ValidationUtil.proveriDaLiJeSamoCenaPromenjena(fakturaUBazi,
					faktura)) {
				System.out.println("Promenjeno je nesto osim cena");
				return Response.status(Status.FORBIDDEN).build();
			}
			BigDecimal cenaUBazi = fakturaUBazi.getIznosZaUplatu();
			if (cenaUBazi.compareTo(cenaGranica) > 0
					&& user.checkAuthorization("izmenaCeneFaktureIznadGranice")) {
				// preko granice, samo direktor sme da menja
				fakturaDao.updateNeposlatuFakturu(id_fakture, faktura);
				return Response.status(Status.OK).build();
			} else if (cenaUBazi.compareTo(cenaGranica) <= 0
					&& user.checkAuthorization("izmenaCeneFaktureIspodGranice")) {
				// nije preko granice, samo shef sme da menja
				fakturaDao.updateNeposlatuFakturu(id_fakture, faktura);
				return Response.status(Status.OK).build();
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}

	}

	@POST
	@Path("odobrifakturu/{id_fakture}")
	@Consumes(MediaType.APPLICATION_XML)
	@Authenticate
	public Response odobriNeposlatuFakturu(
			@PathParam("id_fakture") String id_fakture, Faktura faktura) {
		System.out.println("Odobri fakturu");
		System.out.println("User: "
				+ ((User)request.getSession().getAttribute("user")).getUsername());

		if (!ValidationUtil.isValid("http://localhost:8080/scheme/faktura.xsd",
				Faktura.class, faktura)) {
			System.out.println("\nNije validno");
			return Response.status(Status.BAD_REQUEST).entity("Faktura nije validna!").build();
		}
		System.out.println("PROVERA DA LI JE TOTAL VALUE = VREDNOST ROBE + VREDNOST USLUGA - porezi - rabati itd.");
		if (!ValidationUtil.checkTotalAmount(faktura)) {
			System.out.println("\nNije validno");
			return Response.status(Status.BAD_REQUEST).entity("Iznosi se ne poklapaju!").build();
		}
		
		User user;
		try {
			user = userDao.findById(((User)request.getSession().getAttribute("user")).getUsername());
		} catch (Exception e) {
			return Response.status(Status.FORBIDDEN).build();
		}

		Faktura fakturaUBazi;
		try {
			fakturaUBazi = fakturaDao.findById(Long.parseLong(id_fakture));
			if (!ValidationUtil.proveriDaLiJeSamoCenaPromenjena(fakturaUBazi,
					faktura)) {
				System.out.println("Promenjeno je nesto osim cena");
				return Response.status(Status.FORBIDDEN).build();
			}
			BigDecimal cena = faktura.getIznosZaUplatu();
			BigDecimal cenaUBazi = fakturaUBazi.getIznosZaUplatu();

			Firma partner = firmaDao.getPartner(Long.parseLong(faktura.getKupacPIB()));
			
			String url = partner.getAdresaServisa() + "/partneri/" + 
					rs.ac.uns.ftn.xws.util.Context.firmaPib + "/fakture/";

			if (user.checkAuthorization("odobravanjeFaktureIznadGranice")
					|| (user.checkAuthorization("odobravanjeFaktureIspodGranice")
							&& cena.compareTo(cenaGranica) <= 0 && cenaUBazi
							.compareTo(cenaGranica) <= 0)) {
				try (CloseableHttpClient client = HttpClientBuilder.create()
						.build()) {
					HttpPost post = new HttpPost(url);

					String xml = ValidationUtil.unmarshall(Faktura.class,
							faktura);

					HttpEntity entity = new ByteArrayEntity(
							xml.getBytes("UTF-8"));
					post.setEntity(entity);
					HttpResponse response = client.execute(post);
					if (response.getStatusLine().getStatusCode() == 201) {
						Header headers[] = response.getAllHeaders();
						for (Header h : headers) {
							if (h.getName().equals("Content-Location")) {
								fakturaDao.remove(Long.parseLong(id_fakture));
								return Response.status(Status.CREATED)
										.header(h.getName(), h.getValue())
										.build();
							}
						}
					} else {
						return Response.status(
								response.getStatusLine().getStatusCode())
								.build();
					}
				} catch (IOException e) {
					System.out.println("Neki error");
					return Response.status(Status.NOT_FOUND).build();
				}
			} else if ((user
					.checkAuthorization("izmenaCeneFaktureIspodGranice")
					&& cena.compareTo(cenaGranica) > 0 && cenaUBazi
					.compareTo(cenaGranica) <= 0)) {
				fakturaDao.updateNeposlatuFakturu(Long.parseLong(id_fakture),
						faktura);
				return Response.status(Status.OK).build();
			}

		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}

	@DELETE
	@Path("odbijfakturu/{faktura_id}")
	@Authenticate
	public Response odbijNeposlatuFakturu(
			@PathParam("faktura_id") Long faktura_id) {

		try {
			Faktura fakturaUBazi = fakturaDao.findById(faktura_id);
			User user = userDao.findById(((User)request.getSession().getAttribute("user")).getUsername());
			if (user.checkAuthorization("odbijanjeFaktureIznadGranice")) {
				fakturaDao.remove(faktura_id);
			} else if (fakturaUBazi.getIznosZaUplatu().compareTo(cenaGranica) <= 0
					&& user.checkAuthorization("odbijanjeFaktureIspodGranice")) {
				fakturaDao.remove(faktura_id);
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}

			return Response.status(Status.OK).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Path("user/login/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(JSONObject loginData) {
		System.out.println("LOGIN LOGIN LOGIN");

		String username = loginData.get("username").toString();
		String attemptedPassword = loginData.get("password").toString();

		try {
			User user = userDao.login(username, attemptedPassword);
			if (user != null) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("username", user.getUsername());
				jsonObject.put("role", user.getRole().getRoleName());
				JSONArray operations = new JSONArray();
				for (int i = 0; i < user.getRole().getOperations().getOperation().size(); i++) {
					operations.add(user.getRole().getOperations().getOperation().get(i).getOperName());
				}
				jsonObject.put("operations", operations);
				
				System.out.println(jsonObject.toJSONString());
				return Response.status(Status.OK).entity(jsonObject).build();
			}
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("user/logout/")
	public Response logout() {
		userDao.logout();
		return Response.status(Status.OK).build();
	}

	@GET
	@Path("fakture")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public Response getNeposlateFakture() {
		System.out.println("GET neposlate fakture");
		List<Faktura> fakture = null;

		try {
			fakture = fakturaDao.findAll();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		Gson gson = new Gson();
		String faktureString = gson.toJson(fakture);
		// /////////////////////////////////////////
		// convert the json string back to object
		// Faktura f = gson.fromJson(json, Faktura.class);
		// //////////////////////////////////////

		return Response.status(Status.OK).type(MediaType.APPLICATION_JSON)
				.entity(faktureString).build();
	}

	@GET
	@Path("fakture/{faktura_id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public Response getNeposlatuFakturu(@PathParam("faktura_id") Long faktura_id) {
		try {
			Faktura faktura = fakturaDao.findById(faktura_id);
			if (faktura == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			Gson gson = new Gson();
			String fakturaString = gson.toJson(faktura);
			System.out.println(fakturaString);

			return Response.status(Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(fakturaString).build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("partneri")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public Response getPartneri() {
		List<Firma> partneri;
		try {
			partneri = firmaDao.getPartners();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		}
		Gson gson = new Gson();
		String partneriString = gson.toJson(partneri);
		return Response.status(Status.OK).entity(partneriString).build();
	}

	@GET
	@Path("partneri/{partner_pib}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public Response getPartner(@PathParam("partner_pib") Long partnerPib) {
		System.out.println("GET partner info");
		
		List<Firma> firmaIpartner = new ArrayList<Firma>();
		try {
			firmaIpartner.add(firmaDao.getFirmaInfo());
			firmaIpartner.add(firmaDao.getPartner(partnerPib));
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Gson gson = new Gson();
		String partnerString = gson.toJson(firmaIpartner);
		return Response.status(Status.OK).entity(partnerString).build();
	}
	
	// ovo sluzi za dobijanje firmainfo
	@GET
	@Path("firma")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public Response getPartner() {
		System.out.println("GET firma info");
		
		Firma firma;
		try {
			firma = firmaDao.getFirmaInfo();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		Gson gson = new Gson();
		String firmaString = gson.toJson(firma);
		return Response.status(Status.OK).entity(firmaString).build();
	}
	
	
	
	@GET
	@Path("primljenefakture")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public Response getPrimljeneFakture() {
		try {
			System.out.println("PRIMLJENE");
			List<Faktura> fakture = primljeneFaktureDao.findAll();
			Gson gson = new Gson();
			String faktureString = gson.toJson(fakture);
			System.out.println(faktureString);
			return Response.status(Status.OK).entity(faktureString).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("primljenefakture/{faktura_id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public Response getPrimljenuFakturu(@PathParam("faktura_id") Long fakturaId) {
		
		try {
			Faktura faktura = primljeneFaktureDao.findById(fakturaId);
			if (faktura == null) {
				return Response.status(Status.NOT_FOUND).build();
			}
			Gson gson = new Gson();
			String fakturaString = gson.toJson(faktura);
			System.out.println(fakturaString);

			return Response.status(Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(fakturaString).build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}

