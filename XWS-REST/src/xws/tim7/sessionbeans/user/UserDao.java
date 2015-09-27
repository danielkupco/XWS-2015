package xws.tim7.sessionbeans.user;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import xws.tim7.entities.user.User;
import xws.tim7.interceptors.AuthenticationInterceptor;
import xws.tim7.sessionbeans.common.GenericDao;
import xws.tim7.util.Authenticate;

@Stateless
@Local(UserDaoLocal.class)
public class UserDao extends GenericDao<User, Long> implements UserDaoLocal {

	
	public static final String contextPath = "xws.tim7.entities.user";
	
	public static final String schemaName = "user";
	
	public UserDao() {
		super(contextPath, schemaName);
	}

	@Context
	private HttpServletRequest request;

	private static Logger log = Logger.getLogger(UserDao.class);
	
	@Override
	public User login(String username, String password) throws NoSuchAlgorithmException, IOException, JAXBException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes("UTF-8"));
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		password = sb.toString();
		
//		if(username.equals("admin") && password.equals("21232f297a57a5a743894a0e4a801fc3")) {
//			log.info("logovanje uspesno!");
//			User u = new User();
//			u.setId(new Long(123));
//			u.setUsername(username);
//			u.setPassword(password);
//			u.setFirstname("Admin");
//			u.setLastname("Adminovic");
//			return u;
//		}

//		InputStream input = findBy("//user[@username='pera']", true);
//		BufferedReader br = new BufferedReader(new InputStreamReader(input));
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			System.out.println(line);
//		}
		
		List<User> users = findAll();
		for(User u : users) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				log.info("logovanje uspesno!");
				request.getSession().setAttribute("user", u);
				return u;
			}
		}
			
		return null;
		
	}
	
	@Override
	public void logout(){
		log.info("LOGOUT");
		request.getSession().invalidate();
	}

}
