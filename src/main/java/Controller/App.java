package Controller;

import java.sql.SQLException;

public class App {
    /**
     * MAIN : Création attribut controller
     * @param args
     */
    public static void main(String[] args)  {
        try {
            BigController bc= new BigController();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
