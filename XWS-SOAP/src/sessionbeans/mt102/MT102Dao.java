package sessionbeans.mt102;

import sessionbeans.common.GenericDao;
import xws.tim7.entities.mt102.MT102Type;

public class MT102Dao extends GenericDao<MT102Type, Long> implements MT102DaoLocal {

	public static final String contextPath = "xws.tim7.mt102";
	public static final String schemaName = "nalog_za_grupna_placanja_mt102";
	
	public MT102Dao() {
		super(contextPath, schemaName);
	}
	
}
