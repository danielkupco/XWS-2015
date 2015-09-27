package xws.tim7.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class Tim7XMLValidator {


	private static Logger log = Logger.getLogger(Tim7XMLValidator.class);
	
	public static boolean validateFromObject(Object jaxbObject, String schemaPath, String contextPath) {
		
		File xmlFile = new File("/output.xml");
		
		//Definisemo kontekst, tj. paket(e) u kome se nalaze bean-ovi
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(contextPath);
			
			//serijalizacija u XML
			Marshaller marshaller = context.createMarshaller();

			//Hocemo da XML bude formatiran, tj. da postoji identacija
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			//serijalizacija u stream, koji je u ovom slucaju System.out
			marshaller.marshal(jaxbObject, xmlFile);
			
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Source xmlSource = new StreamSource(xmlFile);
		
		try {
			Source schemaFile = new StreamSource(new File(schemaPath));
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlSource);
			log.info(xmlSource.getSystemId() + " is valid");
			return true;
		} catch (SAXException e) {
			log.info(xmlSource.getSystemId() + " is NOT valid");
			log.info("Reason: " + e.getLocalizedMessage());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean validateFromFile(String xmlPath, String schemaPath) {

		Source xmlFile = new StreamSource(new File(xmlPath));
		try {
			URL schemaFile = new URL(schemaPath);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			log.info(xmlFile.getSystemId() + " is valid");
			return true;
		} catch (SAXException e) {
			log.info(xmlFile.getSystemId() + " is NOT valid");
			log.info("Reason: " + e.getLocalizedMessage());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
