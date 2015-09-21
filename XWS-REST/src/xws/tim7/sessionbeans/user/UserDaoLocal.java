package xws.tim7.sessionbeans.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.JAXBException;

import xws.tim7.entities.user.User;
import xws.tim7.sessionbeans.common.GenericDaoLocal;

public interface UserDaoLocal extends GenericDaoLocal<User, Long> {

	public User login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, IOException, JAXBException;

	public void logout();
	
}
