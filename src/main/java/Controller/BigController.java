package Controller;

import javax.swing.*;
import java.sql.*;

public class BigController {

    Connect c;
    JFrame frame;

    /**
     * CONNEXION A LA BASE DE DONNEE, CONNEXION POUR EFFECTUER LES QUERY ET CREATION DU MENU
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public BigController()throws SQLException, ClassNotFoundException{
        frame= new JFrame();
        frame.setSize(1000,1000);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url= "jdbc:mysql://localhost:3306/cineme?useSSL=false&serverTimezone=UTC"; //connexion à la BBD
        Connection conn = DriverManager.getConnection(url, "root", "");
        Statement stmt = conn.createStatement();
        c=new Connect(conn, stmt);
        create_menu();
    }

//Création de l'objet de type Menu, qui logiquement permet la création de la 1e frame
    public void create_menu(){
        Menu m = new Menu(this);
    }

//Getters, ici les deux getters permettent l'envoi de l'atttribut c, attribut connexion création de query
    public Connect getC() {
        return c;
    }
//Frame attribut permettant la création d'une nouvelle frame
    public JFrame getFrame() {
        return frame;
    }
}
