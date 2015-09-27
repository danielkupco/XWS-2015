package sessionbeans.banka;

import sessionbeans.common.GenericDao;
import xws.tim7.entities.banka.Banka;

public class BankaDao extends GenericDao<Banka, Long> implements BankaDaoLocal {

	public static final String contextPath = "xws.tim7.entities.banka";
	public static final String schemaName = "banka";
	
	public BankaDao() {
		super(contextPath, schemaName);
	}

}
