package Cinema;

import javax.swing.*;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        String url= "jdbc:mysql:\\localhost:3306";
        JFrame frame= new JFrame();
        frame.setSize(1000,1000);
        //We call the menu
        Connect c= new Connect(url);
        frame.getContentPane().add(new Menu(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //Connect c= new Connect(url);


        //JXDatePicker d= new JXDatePicker();
        //create calender;
    }
}
