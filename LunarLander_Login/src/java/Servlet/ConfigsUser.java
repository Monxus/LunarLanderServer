/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ClasesAux.Configuraciones;
import ClasesAux.MysqlConnectionHandler;
import ClasesAux.User;
import ClasesAux.ioBD;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramon
 */
public class ConfigsUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cookieName = "uid";
        Cookie[] cookies = request.getCookies();
        MysqlConnectionHandler mysql = new MysqlConnectionHandler();
        Connection conn = mysql.doConnection();
        Gson gson = new Gson();
        if (cookies != null) {

            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];

                if (cookieName.equals(cookie.getName())) {
                    String uid = cookie.getValue();
                    ioBD iobd = new ioBD();

                    try {

                        Configuraciones configs = iobd.getConfiguraciones(conn, uid);
                        String jsonInString = gson.toJson(configs);

                        response.setContentType("application/json");
                        PrintWriter pw = response.getWriter();
                        pw.println(jsonInString);
                    } catch (SQLException ex) {

                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.setContentType("application/json");
                        PrintWriter pw = response.getWriter();
                        pw.println("{\"error\":\"Error al cargar la configuración del juego\"}");
                    } finally {
                        mysql.closeConnection();
                    }
                }

            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cookieName = "uid";
        Cookie[] cookies = request.getCookies();
        String uid = null;
        MysqlConnectionHandler mysql = new MysqlConnectionHandler();
        Connection conn = mysql.doConnection();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookieName.equals(cookie.getName())) {
                uid = cookie.getValue();
            }
        }
        String nombre = request.getParameter("nombre");
        String dificultad = request.getParameter("dificultad");
        String nave = request.getParameter("nave");
        String lugar = request.getParameter("lugar");
        System.out.println(uid);
        try {

            ioBD iobd = new ioBD();
            iobd.crearConfig(conn, uid, nombre, dificultad, nave, lugar);
            
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"mess\":\"Se ha guardado la configuración correctamente\"}");

        } catch (SQLException ex) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Error al guardar la configuración del juego\"}");
        } finally {
            mysql.closeConnection();
        }
    }

}
