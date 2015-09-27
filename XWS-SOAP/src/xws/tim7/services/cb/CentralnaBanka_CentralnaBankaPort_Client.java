
package xws.tim7.services.cb;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import xws.tim7.entities.globals.StatusType;
import xws.tim7.entities.mt102.MT102Type;
import xws.tim7.entities.mt103.MT103Type;
import xws.tim7.entities.nalogzaplacanje.NalogZaPlacanjeType;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-08-22T23:28:00.980+02:00
 * Generated source version: 2.6.5
 * 
 */
public final class CentralnaBanka_CentralnaBankaPort_Client {

    private static final QName SERVICE_NAME = new QName("http://xws/tim7/cb", "CentralnaBankaService");

    private String wsdlPart = "http://localhost:8080/CentralnaBanka?wsdl";
    private String bankaId;
    
    private CentralnaBanka getService() {
    	CentralnaBanka retVal = null;
    	try {
    		URL wsdlUrl = new URL(wsdlPart);
            CentralnaBankaService ss = new CentralnaBankaService(wsdlUrl, SERVICE_NAME);
            retVal = ss.getCentralnaBankaPort();  
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return retVal;
    }
    
    public StatusType primiMT102(MT102Type mt102) {
    	//xws.tim7.entities.mt102.ObjectFactory factory = new xws.tim7.entities.mt102.ObjectFactory();
    	//MT102Type clearingNalog = factory.createMT102Type(nzp);
    	return this.getService().primiMT102(mt102);
    }
    
    public StatusType primitMT103(NalogZaPlacanjeType nzp) {
    	xws.tim7.entities.mt103.ObjectFactory factory = new xws.tim7.entities.mt103.ObjectFactory();
    	MT103Type rtgsNalog = factory.createMT103Type(nzp);
    	return this.getService().primiMT103(rtgsNalog);
    }
    
    public CentralnaBanka_CentralnaBankaPort_Client(String banka) {
    	this.bankaId = banka;		//banka koja poziva
    }
    
 
}
