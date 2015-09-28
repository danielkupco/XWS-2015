package xws.tim7.services.cb;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-08-22T23:28:01.294+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebServiceClient(name = "CentralnaBankaService", 
                  wsdlLocation = "file:../wsdl/centralnaBanka.wsdl",
                  targetNamespace = "http://xws/tim7/cb") 
public class CentralnaBankaService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://xws/tim7/cb", "CentralnaBankaService");
    public final static QName CentralnaBankaPort = new QName("http://xws/tim7/cb", "CentralnaBankaPort");
    static {
        URL url = null;
        try {
            url = new URL("file:../wsdl/centralnaBanka.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CentralnaBankaService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/D:/Programiranje/Eclipse workspaces/XWS/XWS_Tim7/WEB-INF/wsdl/centralnaBanka.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CentralnaBankaService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CentralnaBankaService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CentralnaBankaService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CentralnaBankaService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CentralnaBankaService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CentralnaBankaService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns CentralnaBanka
     */
    @WebEndpoint(name = "CentralnaBankaPort")
    public CentralnaBanka getCentralnaBankaPort() {
        return super.getPort(CentralnaBankaPort, CentralnaBanka.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CentralnaBanka
     */
    @WebEndpoint(name = "CentralnaBankaPort")
    public CentralnaBanka getCentralnaBankaPort(WebServiceFeature... features) {
        return super.getPort(CentralnaBankaPort, CentralnaBanka.class, features);
    }

}
