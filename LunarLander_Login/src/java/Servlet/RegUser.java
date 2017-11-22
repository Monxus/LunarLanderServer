/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ClasesAux.MysqlConnectionHandler;
import ClasesAux.ioBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramon
 */
public class RegUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MysqlConnectionHandler mysql = new MysqlConnectionHandler();
        Connection conn = mysql.doConnection();

        //Reibir parametros
        String nombre = request.getParameter("nombre");
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        try {

            //Crear objeto "ioBD"
            ioBD iobd = new ioBD();
            if (iobd.comprobarRegUser(conn, username)) {
                iobd.registrarUser(conn, nombre, username, pwd);
                iobd.crearConfigDefault(conn, username);
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println("{\"mess\":\"Se ha creado el usuario correctamente\"}");
            } else {
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println("{\"mess\":\"El nombre de usuario ya está en uso\"}");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegUser.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Error al crear el usuario\"}");
        } finally {
            mysql.closeConnection();
        }
    }

}
