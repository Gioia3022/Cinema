package Controller;
import java.sql.*;

public class App {
    //private static ResultSet rs;
    public static void main(String[] args)  {
        try {
            BigController bc= new BigController();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //JXDatePicker d= new JXDatePicker();
        //create calender;
    }
}
