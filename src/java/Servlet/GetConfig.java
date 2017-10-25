/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;
import configJAXB.Configuraciones;
import configJAXB.ObjectFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

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
        try{
        //Set new File object
        ServletContext context = getServletContext();
        String fullPath = context.getRealPath("/config.xml");
        File f = new File(fullPath);
//parse xml to object (Personas, previusly created with JAXB)
        ObjectFactory jaxb = new ObjectFactory();
        Configuraciones configs = jaxb.xmlToObject(f);  //En aquest punt podem modificar prs, es un objecte.
//marshall object to string xml
        StringWriter sw = new StringWriter();
        JAXB.marshal(configs, sw);
        String xmlString = sw.toString();
//return XML
        response.setContentType("text/xml");
        PrintWriter pw = response.getWriter();
        pw.println(xmlString);
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
            String fullPath = context.getRealPath("/config.xml");
            
            File f = new File(fullPath);

//parsear el fichero
            ObjectFactory jaxb = new ObjectFactory();
            Configuraciones configs = jaxb.xmlToObject(f);

            Configuraciones.Config c = new Configuraciones.Config();
            byte num = 2;
            c.setId(num);
            c.setNombre(nombre);
            c.setDificultad(dificultad);
            c.setNave(nave);
            c.setLugar(lugar);
            configs.getConfig().add(c);

            JAXBContext jaxbContext = JAXBContext.newInstance(Configuraciones.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(configs, f);

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
