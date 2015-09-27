package sessionbeans.racun_firme;

import sessionbeans.common.GenericDao;
import xws.tim7.entities.racun_firme.RacunFirme;

public class RacunFirmeDao extends GenericDao<RacunFirme, Long> {

	public static final String contextPath = "xws.tim7.entities.racun_firme";
	public static final String schemaName = "racun_firme";
	
	public RacunFirmeDao() {
		super(contextPath, schemaName);
	}

}
