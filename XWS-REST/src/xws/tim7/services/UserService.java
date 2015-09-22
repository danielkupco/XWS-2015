package xws.tim7.services;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import xws.tim7.entities.user.User;
import xws.tim7.sessionbeans.user.UserDaoLocal;
import xws.tim7.util.ServiceException;

@Path("/user")
public class UserService {

	private static Logger log = Logger.getLogger(UserService.class);

	@EJB
	private UserDaoLocal userDao;

	@GET 
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public User loginGet(@QueryParam("username") String username, @QueryParam("password") String password) {
    	User user = null;
		try {
        	user = userDao.login(username, password);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
		if(user==null){
			throw new ServiceException("Wrong username or password", Status.FORBIDDEN);
		}
		log.info("USER: "+user);
    	return user;
    }
    
	@POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User login(User sentUser) {
    	User user = null;
		try {
        	user = userDao.login(sentUser.getUsername(), sentUser.getPassword());
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
		if(user==null){//ako ne uspe prijava posalje se greska 403 - znam sta hoces, ali ti ne dozvoljavam 
			throw new ServiceException("Wrong username or password", Status.FORBIDDEN);
		}
		
    	return user;
    }
	
	@GET
    @Path("logout")
    @Produces(MediaType.TEXT_HTML)
    public String logout() {
		try {
			userDao.logout();
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    	return "ok";
    }
	
	@GET
    @Path("init")
    @Produces(MediaType.TEXT_PLAIN)
    public Response initialize() {
		User u1 = new User();
		u1.setId(new Long(111));
		u1.setUsername("admin");
		u1.setPassword("21232f297a57a5a743894a0e4a801fc3");
		u1.setFirstname("Admin");
		u1.setLastname("Adminovic");
		
		User u2 = new User();
		u2.setId(new Long(222));
		u2.setUsername("pera");
		u2.setPassword("d8795f0d07280328f80e59b1e8414c49");
		u2.setFirstname("Pera");
		u2.setLastname("Peric");
		
		try {
			userDao.persist(u1);
			userDao.persist(u2);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.ok().entity("Users created...").build();
		
    }

}
