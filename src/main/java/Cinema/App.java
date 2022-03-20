package Cinema;

import javax.swing.*;
import java.nio.file.Path;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        JFrame frame= new JFrame();
        frame.setSize(1000,1000);
        //We call the menu
        //frame.getContentPane(new Menu(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        String url= "jdbc:mysql:\\localhost:3306";
        //Connect c= new Connect(url);


        //JXDatePicker d= new JXDatePicker();
        //create calender;
    }
}
