package Controller;

import javax.swing.*;
import java.sql.*;

public class BigController {

    private static Connection conn = null;
    private static Statement stmt;
    private Menu m;
    Connect c;
    JFrame frame;

    public BigController()throws SQLException, ClassNotFoundException{
        frame= new JFrame();
        frame.setSize(1000,1000);
        Class.forName("com.mysql.cj.jdbc.Driver");
        /* ICI CHANGER LE NOM DE TA BDD ( DANS URL )*/
        String url= "jdbc:mysql://localhost:3306/cineme?useSSL=false&serverTimezone=UTC";
        conn = DriverManager.getConnection(url, "root", "");
        stmt = conn.createStatement();
        c=new Connect(conn,stmt);
        create_menu();
    }

    public void create_menu(){
        m=new Menu(this);
    }

    public Connect getC() {
        return c;
    }

    public JFrame getFrame() {
        return frame;
    }
}
