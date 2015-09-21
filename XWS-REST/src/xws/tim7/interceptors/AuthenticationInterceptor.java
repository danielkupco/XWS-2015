package xws.tim7.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import xws.tim7.entities.user.User;
import xws.tim7.util.Authenticate;
import xws.tim7.util.ServiceException;

@Interceptor
@Authenticate
public class AuthenticationInterceptor {

	public AuthenticationInterceptor() {
		super();
	}

	private static Logger log = Logger.getLogger(AuthenticationInterceptor.class);

	@Context
	private HttpServletRequest request;

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception{
		log.info("Authentication interceptor invoked!");
		
		User user = (User) request.getSession().getAttribute("user");
		log.info("user: "+user);
		if (user == null) {
			throw new ServiceException("Not logged in", Status.UNAUTHORIZED);
		}	
		
		Object result = context.proceed();
		return result;
	}

	

}
