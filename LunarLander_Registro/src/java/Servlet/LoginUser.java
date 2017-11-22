/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ClasesAux.MysqlConnectionHandler;
import ClasesAux.User;
import ClasesAux.ioBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramon
 */
public class LoginUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cookieName = "uid";
        String cookieUsername = "username";
        String cookiePwd = "pwd";
        Cookie[] cookies = request.getCookies();

        String idC = null;
        String usernameC = null;
        String pwdC = null;

        if (cookies != null) {

            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookieName.equals(cookie.getName())) {
                    idC = cookie.getValue();
                }
                if (cookieUsername.equals(cookie.getName())) {
                    usernameC = cookie.getValue();
                }
                if (cookiePwd.equals(cookie.getName())) {
                    pwdC = cookie.getValue();
                }
            }
            if (idC == null || usernameC == null || pwdC == null) {
                RequestDispatcher a = request.getRequestDispatcher("/index.html");
                a.forward(request, response);
            } else {
                MysqlConnectionHandler mysql = new MysqlConnectionHandler();
                Connection conn = mysql.doConnection();
                try {

                    ioBD iobd = new ioBD();
                    User user = iobd.GetUserById(conn, idC);
                    if (user.getUsername().equals(usernameC) && user.getPwd().equals(pwdC)) {
                    request.setAttribute("nombre", user.getName());
                    RequestDispatcher a = request.getRequestDispatcher("/game.jsp");
                    a.forward(request, response);
                    }else{
                       RequestDispatcher a = request.getRequestDispatcher("/index.html");
                a.forward(request, response); 
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(LoginUser.class.getName()).log(Level.SEVERE, null, ex);
                    RequestDispatcher a = request.getRequestDispatcher("/index.html");
                    a.forward(request, response);
                } finally {
                    mysql.closeConnection();
                }
            }

        } else {
            RequestDispatcher a = request.getRequestDispatcher("/index.html");
            a.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MysqlConnectionHandler mysql = new MysqlConnectionHandler();
        Connection conn = mysql.doConnection();

        //Reibir parametros
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        try {
            //Crear objeto "ioBD"
            ioBD iobd = new ioBD();
            if (iobd.comprobarLogUser(conn, username)) {
                User user = iobd.GetUser(conn, username);
                if (user.getPwd().equals(pwd)) {
                    Cookie userCookie = new Cookie("uid", Integer.toString(user.getId()));
                    Cookie usernameCookie = new Cookie("username", username);
                    Cookie pwdCookie = new Cookie("pwd", pwd);
                    userCookie.setMaxAge(60 * 60 * 24 * 365);
                    usernameCookie.setMaxAge(60 * 60 * 24 * 365);
                    pwdCookie.setMaxAge(60 * 60 * 24 * 365);
                    userCookie.setPath("/");
                    usernameCookie.setPath("/");
                    pwdCookie.setPath("/");
                    response.addCookie(userCookie);
                    response.addCookie(usernameCookie);
                    response.addCookie(pwdCookie);

                } else {
                    response.setContentType("application/json");
                    PrintWriter pw = response.getWriter();
                    pw.println("{\"mess\":\"La contraseña introducida es erronea\"}");
                }
            } else {
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println("{\"mess\":\"El usuario introducido no existe\"}");
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

    @Override
    public void init() {
        MysqlConnectionHandler mysql = new MysqlConnectionHandler();
        Connection conn = mysql.doConnection();

        try {
            ioBD iobd = new ioBD();
            iobd.crearTablas(conn);
        } catch (SQLException ex) {
            //Logger.getLogger(RegUser.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("La BD ya está creada");
        } finally {
            mysql.closeConnection();
        }
    }

}
