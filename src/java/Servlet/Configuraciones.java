//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: PM.10.18 a las 05:23:43 PM CEST 
//


package Servlet;

import java.util.ArrayList;
import java.util.List;


public class Configuraciones {

    protected List<Configuraciones.Config> config;

    public List<Configuraciones.Config> getConfig() {
        if (config == null) {
            config = new ArrayList<Configuraciones.Config>();
        }
        return this.config;
    }


   
    public static class Config {

        protected String nombre;
        protected String dificultad;
        protected String nave;
        protected String lugar;

       
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String value) {
            this.nombre = value;
        }

        
        public String getDificultad() {
            return dificultad;
        }

        
        public void setDificultad(String value) {
            this.dificultad = value;
        }

       
        public String getNave() {
            return nave;
        }

        
        public void setNave(String value) {
            this.nave = value;
        }

        
        public String getLugar() {
            return lugar;
        }

        
        public void setLugar(String value) {
            this.lugar = value;
        }


    }

}
