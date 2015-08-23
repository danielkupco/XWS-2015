
package xws.tim7.presek;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xws.tim7.presek package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xws.tim7.presek
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PresekType }
     * 
     */
    public PresekType createPresekType() {
        return new PresekType();
    }

    /**
     * Create an instance of {@link ReturnPresek }
     * 
     */
    public ReturnPresek createReturnPresek() {
        return new ReturnPresek();
    }

    /**
     * Create an instance of {@link PresekType.ZaglavljePreseka }
     * 
     */
    public PresekType.ZaglavljePreseka createPresekTypeZaglavljePreseka() {
        return new PresekType.ZaglavljePreseka();
    }

    /**
     * Create an instance of {@link PresekType.StavkaPreseka }
     * 
     */
    public PresekType.StavkaPreseka createPresekTypeStavkaPreseka() {
        return new PresekType.StavkaPreseka();
    }

}
