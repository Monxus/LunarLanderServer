/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramon
 */
public class GetConfig extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetConfig</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetConfig at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Archivo donde está la config
            ServletContext context = getServletContext();
            String fullPath = context.getRealPath("/json/config.json");

            //Convertimos el fichero json a objeto con gson
            Gson gson = new Gson();
            Configuraciones configs = gson.fromJson(new FileReader(fullPath), Configuraciones.class);

//Convertimos el objeto en String
            String jsonInString = gson.toJson(configs);

//Devolvemos al cliente el string del json
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(jsonInString);

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Error al cargar la configuración del juego\"}");

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String dificultad = request.getParameter("dificultad");
            String nave = request.getParameter("nave");
            String lugar = request.getParameter("lugar");

            ServletContext context = getServletContext();
            String fullPath = context.getRealPath("/json/config.json");
            
            //Convertimos el fichero json a objeto con gson
            Gson gson = new Gson();
            Configuraciones configs = gson.fromJson(new FileReader(fullPath), Configuraciones.class);
           
            //Creamos el objeto configuracion con la configuración que nos llega
            Configuraciones.Config c = new Configuraciones.Config();
            c.setNombre(nombre);
            c.setDificultad(dificultad);
            c.setNave(nave);
            c.setLugar(lugar);
            
            //Añadimos la configuración nueva al objeto fichero configuraciones que hemos parseado antes.
            configs.getConfig().add(c);
            
            //Volvemos a convertir el objeto a fihero json
            //gson.toJson(configs,new FileWriter(fullPath));
            
            String jsonInString = gson.toJson(configs);
            File f = new File(fullPath);
            FileWriter fw = new FileWriter(f);
            fw.write(jsonInString);
            fw.close();

            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"mess\":\"Se ha guardado correctamente\"}");

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Error al guardar la configuración del juego\"}");

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
