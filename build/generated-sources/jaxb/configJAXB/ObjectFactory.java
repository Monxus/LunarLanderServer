//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: PM.10.18 a las 05:23:43 PM CEST 
//


package configJAXB;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the configJAXB package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: configJAXB
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Configuraciones }
     * 
     */
    public Configuraciones createConfiguraciones() {
        return new Configuraciones();
    }

    /**
     * Create an instance of {@link Configuraciones.Config }
     * 
     */
    public Configuraciones.Config createConfiguracionesConfig() {
        return new Configuraciones.Config();
    }
    
    public Configuraciones xmlToObject(File f) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Configuraciones.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //Do the job, return object
            return (Configuraciones) jaxbUnmarshaller.unmarshal(f);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void objectToXml(Configuraciones confis, File rf) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Configuraciones.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Optional
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Do the job
            jaxbMarshaller.marshal(confis, rf);

            //Optional: output pretty printed
            //jaxbMarshaller.marshal(cds, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void printObject(Configuraciones confis) {
        String result = "";
        //Read every CD from list
        List<Configuraciones.Config> llistaconfis = confis.getConfig();
        for (int i = 0; i < llistaconfis.size(); i++) {
            result += "\n " + "Nombre: " + llistaconfis.get(i).getNombre();
            result += "\n " + "Dificultad: " + llistaconfis.get(i).getDificultad();
            result += "\n " + "Nave: " + llistaconfis.get(i).getNave();
            result += "\n " + "Lugar Aterrizaje: " + llistaconfis.get(i).getLugar();
            result += "\n ";
        }
        System.out.println(result);
    }

}
