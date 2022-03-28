package Controller;

import javax.swing.*;
import java.sql.*;

public class BigController {

    Connect c;
    JFrame frame;
// CONNEXION A LA BASE DE DONNEE, CONNEXION POUR EFFECTUER LES QUERY ET CREATION DU MENU
    public BigController()throws SQLException, ClassNotFoundException{
        frame= new JFrame();
        frame.setSize(1000,1000);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url= "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
        Connection conn = DriverManager.getConnection(url, "root", "");
        Statement stmt = conn.createStatement();
        c=new Connect(conn, stmt);
        create_menu();
    }

    public void create_menu(){
        Menu m = new Menu(this);
    }

    public Connect getC() {
        return c;
    }

    public JFrame getFrame() {
        return frame;
    }
}
