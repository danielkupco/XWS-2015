package sessionbeans.mt900;

import sessionbeans.common.GenericDao;
import xws.tim7.globals.MT9XXType;

public class MT900Dao extends GenericDao<MT9XXType, Long> implements MT900DaoLocal {

	public static final String contextPath = "xws.tim7.globals";
	public static final String schemaName = "globals";
	
	public MT900Dao() {
		super(contextPath, schemaName);
	}

}
