package Cinema;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Connect {
    String url;
    Connection conn = null;
    ResultSet rs = null;
    private ResultSetMetaData rsMeta;
    Statement stmt = null;
    PreparedStatement pstmt = null;

    public Connect(String url) throws SQLException {
        this.url=url;
    }

    public void connectData() throws SQLException {
        conn = DriverManager.getConnection(url);
    }
    public void closeCourseDataConnection() throws SQLException {
        conn.close();
    }
    public void createStatement() throws SQLException {
        this.stmt = conn.createStatement();
    }

    //Gives all film names from database
    public ArrayList<String> SQLQueryFilmName() throws SQLException {
        ArrayList<String> film = new ArrayList<>();
        // language=<SQL>
        String sql = "Select FilmName from film";

        rs = stmt.executeQuery(sql);

        while (rs != null && rs.next()) {
            String name = rs.getString(1);
            film.add(name);
        }
        return film;
    }
    //Checks validity of mail
    public boolean SQLQueryAdmin(String user, String pass ) throws SQLException {
        boolean check=true;
        // language=<SQL>
        String sql;
        // language=<SQL>
        sql= "SELECT AdminMail from admin WHERE AdminMail ='" + user + "';";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        if(!rs.wasNull()){
            check=true;
            String p= rs.getString(3);
            if(pass==p){
                check =true;
            }
            else
                check=false;
        }
        else
            check=false;
        return check;
    }
}
