/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAux;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ramon
 */
public class ioBD {

    public void registrarUser(Connection conn, String name, String username, String pwd) throws SQLException {
        String sql = "INSERT into user(name,username,password) VALUES (?,?,?);";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, name);
        pst.setString(2, username);
        pst.setString(3, pwd);
        pst.execute();
    }

    public User GetUser(Connection conn, String username) throws SQLException {
        String sql = "SELECT id,name,username,password FROM user WHERE username = ?;";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        rs.next();

        int id = rs.getInt("id");
        String name = rs.getString("name");
        String un = rs.getString("username");
        String pwd = rs.getString("password");
        User userAux = new User(id, name, un, pwd);

        return userAux;
    }

    public String GetNameUser(Connection conn, String uid) throws SQLException {
        String sql = "SELECT name FROM user WHERE id = ?;";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, uid);
        ResultSet rs = pst.executeQuery();
        rs.next();
        String name = rs.getString("name");
        return name;

    }

    public boolean comprobarLogUser(Connection conn, String username) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM user WHERE username = ?;";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        rs.next();

        int numAux = rs.getInt("total");

        if (numAux == 1) {
            return true;
        }
        return false;
    }

    public boolean comprobarRegUser(Connection conn, String username) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM user WHERE username = ? ;";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        rs.next();

        int numAux = rs.getInt("total");

        if (numAux == 0) {
            return true;
        }
        return false;
    }

    public void crearTablas(Connection conn) throws SQLException {
        String sql1 = "CREATE TABLE `configuracion` ("
                + "`id` int(11) NOT NULL,"
                + "`user_id` int(11) NOT NULL"
                + "`config_name` varchar(20) NOT NULL,"
                + "`dif_id` int(11) NOT NULL,"
                + "`nave_id` int(11) NOT NULL,"
                + "`planeta_id` int(11) NOT NULL"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        PreparedStatement pst = conn.prepareStatement(sql1);
        pst.execute();

        String sql2 = "CREATE TABLE `dificultad` (  `id` int(11) NOT NULL,  `dif` varchar(10) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        pst = conn.prepareStatement(sql2);
        pst.execute();

        String sql3 = "CREATE TABLE `nave` (  `id` int(11) NOT NULL,  `nave` varchar(10) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        pst = conn.prepareStatement(sql3);
        pst.execute();
        String sql4 = "CREATE TABLE `planeta` (  `id` int(11) NOT NULL,  `planeta` varchar(10) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        pst = conn.prepareStatement(sql4);
        pst.execute();
        String sql5 = "CREATE TABLE `score` ("
                + "`id` int(11) NOT NULL,"
                + "`conf_id` int(11) NOT NULL,"
                + "`speed` float NOT NULL,"
                + "`fuel` float NOT NULL,"
                + "`init_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                + "`end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        pst = conn.prepareStatement(sql5);
        pst.execute();

        String sql6 = "CREATE TABLE `user` ("
                + "`id` int(11) NOT NULL,"
                + "`name` varchar(20) NOT NULL,"
                + "`username` varchar(20) NOT NULL,"
                + "`password` varchar(20) NOT NULL"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        pst = conn.prepareStatement(sql6);
        pst.execute();
        String sql7 = "ALTER TABLE `configuracion`"
                + "ADD PRIMARY KEY (`id`),"
                + "ADD KEY `user_id` (`user_id`,`dif_id`,`nave_id`,`planeta_id`),"
                + "ADD KEY `dif_id` (`dif_id`),"
                + "ADD KEY `nave_id` (`nave_id`),"
                + "ADD KEY `planeta_id` (`planeta_id`);";
        pst = conn.prepareStatement(sql7);
        pst.execute();
        String sql8 = "ALTER TABLE `dificultad`  ADD PRIMARY KEY (`id`);";
        pst = conn.prepareStatement(sql8);
        pst.execute();
        String sql9 = "ALTER TABLE `nave`  ADD PRIMARY KEY (`id`);";
        pst = conn.prepareStatement(sql9);
        pst.execute();
        String sql10 = "ALTER TABLE `planeta`  ADD PRIMARY KEY (`id`);";
        pst = conn.prepareStatement(sql10);
        pst.execute();
        String sql11 = "ALTER TABLE `score`  ADD PRIMARY KEY (`id`),  ADD KEY `conf_id` (`conf_id`);";
        pst = conn.prepareStatement(sql11);
        pst.execute();
        String sql12 = "ALTER TABLE `user`  ADD PRIMARY KEY (`id`);";
        pst = conn.prepareStatement(sql12);
        pst.execute();
//
//String sql13="ALTER TABLE `configuracion`  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;";
//
//String sql14="ALTER TABLE `score` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;";
//
//String sql15="ALTER TABLE `user` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;";

        String sql16 = "ALTER TABLE `configuracion`"
                + "ADD CONSTRAINT `configuracion_ibfk_1` FOREIGN KEY (`dif_id`) REFERENCES `dificultad` (`id`) ON UPDATE CASCADE,"
                + "ADD CONSTRAINT `configuracion_ibfk_2` FOREIGN KEY (`nave_id`) REFERENCES `nave` (`id`) ON UPDATE CASCADE,"
                + "ADD CONSTRAINT `configuracion_ibfk_3` FOREIGN KEY (`planeta_id`) REFERENCES `planeta` (`id`) ON UPDATE CASCADE,"
                + "ADD CONSTRAINT `configuracion_ibfk_5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;";
        pst = conn.prepareStatement(sql16);
        pst.execute();
        String sql17 = "ALTER TABLE `score`"
                + "ADD CONSTRAINT `score_ibfk_1` FOREIGN KEY (`conf_id`) REFERENCES `configuracion` (`id`) ON UPDATE CASCADE;"
                + "COMMIT;";
        pst = conn.prepareStatement(sql17);
        pst.execute();

    }
}
