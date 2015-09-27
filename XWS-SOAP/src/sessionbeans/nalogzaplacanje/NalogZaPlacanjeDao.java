package sessionbeans.nalogzaplacanje;

import sessionbeans.common.GenericDao;
import xws.tim7.entities.nalogzaplacanje.NalogZaPlacanjeType;

public class NalogZaPlacanjeDao extends GenericDao<NalogZaPlacanjeType, Long> implements NalogZaPlacanjeDaoLocal {

	public static final String contextPath = "xws.tim7.entities.nalogzaplacanje";
	public static final String schemaName = "nalog_za_placanje";
	
	public NalogZaPlacanjeDao() {
		super(contextPath, schemaName);
	}
}
