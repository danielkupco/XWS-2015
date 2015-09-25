package sessionbeans.mt910;

import sessionbeans.common.GenericDao;
import xws.tim7.globals.MT9XXType;

public class MT910Dao extends GenericDao<MT9XXType, Long> implements MT910DaoLocal {

	public static final String contextPath = "xws.tim7.globals";
	public static final String schemaName = "globals";
	
	public MT910Dao() {
		super(contextPath, schemaName);
	}
}
