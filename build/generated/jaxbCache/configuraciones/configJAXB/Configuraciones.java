//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.10.18 a las 05:23:43 PM CEST 
//


package configJAXB;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="config" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="dificultad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="nave" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="lugar" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "config"
})
@XmlRootElement(name = "configuraciones")
public class Configuraciones {

    protected List<Configuraciones.Config> config;

    /**
     * Gets the value of the config property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the config property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfig().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Configuraciones.Config }
     * 
     * 
     */
    public List<Configuraciones.Config> getConfig() {
        if (config == null) {
            config = new ArrayList<Configuraciones.Config>();
        }
        return this.config;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="dificultad" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="nave" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="lugar" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nombre",
        "dificultad",
        "nave",
        "lugar"
    })
    public static class Config {

        @XmlElement(required = true)
        protected String nombre;
        @XmlElement(required = true)
        protected String dificultad;
        @XmlElement(required = true)
        protected String nave;
        @XmlElement(required = true)
        protected String lugar;
        @XmlAttribute(name = "id")
        protected Byte id;

        /**
         * Obtiene el valor de la propiedad nombre.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Define el valor de la propiedad nombre.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombre(String value) {
            this.nombre = value;
        }

        /**
         * Obtiene el valor de la propiedad dificultad.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDificultad() {
            return dificultad;
        }

        /**
         * Define el valor de la propiedad dificultad.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDificultad(String value) {
            this.dificultad = value;
        }

        /**
         * Obtiene el valor de la propiedad nave.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNave() {
            return nave;
        }

        /**
         * Define el valor de la propiedad nave.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNave(String value) {
            this.nave = value;
        }

        /**
         * Obtiene el valor de la propiedad lugar.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLugar() {
            return lugar;
        }

        /**
         * Define el valor de la propiedad lugar.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLugar(String value) {
            this.lugar = value;
        }

        /**
         * Obtiene el valor de la propiedad id.
         * 
         * @return
         *     possible object is
         *     {@link Byte }
         *     
         */
        public Byte getId() {
            return id;
        }

        /**
         * Define el valor de la propiedad id.
         * 
         * @param value
         *     allowed object is
         *     {@link Byte }
         *     
         */
        public void setId(Byte value) {
            this.id = value;
        }

    }

}
