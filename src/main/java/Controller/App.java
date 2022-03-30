package Controller;

import java.sql.SQLException;

public class App {
    //private static ResultSet rs;
    public static void main(String[] args)  { //MAIN : LANCE L'AFFICHAGE DU MENU
        try {
            BigController bc= new BigController(); //création attribut controller
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //JXDatePicker d= new JXDatePicker();
        //create calender;
    }
}
