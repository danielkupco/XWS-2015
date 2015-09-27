package sessionbeans.mt103;

import sessionbeans.common.GenericDao;
import xws.tim7.entities.mt103.MT103Type;

public class MT103Dao extends GenericDao<MT103Type, Long> implements MT103DaoLocal {

	public static final String contextPath = "xws.tim7.entities.mt103";
	public static final String schemaName = "rtgs_mt103";
	
	public MT103Dao() {
		super(contextPath, schemaName);
	}

}
