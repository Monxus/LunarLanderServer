/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAux;

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
    
    public Configuraciones(List<Configuraciones.Config> confs) {

            this.config =confs;

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
            switch (value) {
                case "0":
                    this.dificultad = "facil";
                    break;
                case "1":
                    this.dificultad = "medio";
                    break;
                case "2":
                    this.dificultad = "dificil";
                    break;
                default:
                    this.dificultad = "facil";
                    break;
            }

        }

        public String getNave() {
            return nave;
        }

        public void setNave(String value) {
            switch (value) {
                case "0":
                    this.nave = "nave";
                    break;
                case "1":
                    this.nave = "ovni";
                    break;
                default:
                    this.nave = "nave";
                    break;
            }
        }

        public String getLugar() {
            return lugar;
        }

        public void setLugar(String value) {
            switch (value) {
                case "0":
                    this.lugar = "luna";
                    break;
                case "1":
                    this.lugar = "luna";
                    break;

                default:
                    this.lugar = "luna";
                    break;
            }

        }

    }
}
