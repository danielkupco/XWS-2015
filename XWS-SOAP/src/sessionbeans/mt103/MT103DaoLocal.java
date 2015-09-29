package sessionbeans.mt103;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDaoLocal;
import soap.tim7.entities.mt103.MT103Type;

public interface MT103DaoLocal extends GenericDaoLocal<MT103Type, Long> {

	public MT103Type findByMT910Id(String idPoruke)throws IOException, JAXBException;

}
