package Controller;

import java.sql.*;
import java.util.ArrayList;


public class Connect {
    Connection conn;
    ResultSet rs;
    private ResultSetMetaData rsMeta;
    Statement stmt;
    PreparedStatement pstmt = null;


    public Connect(Connection c, Statement s) throws SQLException {
        this.conn=c;
        this.stmt=s;
        // language=<SQL>
        rs=stmt.executeQuery("SELECT * FROM guest");
        while (rs.next()){
            System.out.println(rs.getString("GuestMP"));
        }
    }

    /*
    public void connectData() throws SQLException {
        conn = DriverManager.getConnection();
    }*/
    public void closeCourseDataConnection() throws SQLException {
        conn.close();
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
        sql= "SELECT AdminMail from admin WHERE AdminMail =? AND AdminMP= ? ;";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,String.valueOf(user));
        pstmt.setString(2,String.valueOf(pass));
        rs = pstmt.executeQuery();
        if (rs.next()) {
                check = true;
        }else
            check = false;
        return check;
    }
    public boolean SQLQueryGuest(String user, String pass ) throws SQLException {
        boolean check=true;
        // language=<SQL>
        String sql;
        // language=<SQL>
        sql= "SELECT GuestMail from guest WHERE GuestMail =? AND GuestMP= ? ;";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,String.valueOf(user));
        pstmt.setString(2,String.valueOf(pass));
        rs = pstmt.executeQuery();
        if (rs.next()) {
            check = true;
        }else
            check = false;
        return check;
    }
    public void SQLQueryNewGuest(String name, String mail,String psw, int tel, int age, int benef)throws SQLException{
        // language=<SQL>
        String sql;
        // language=<SQL>
        sql= "INSERT INTO guest (GuestName,GuestMP, GuestMail,GuestBenefice, GuestAge,GuestTel) VALUES (?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,psw);
        pstmt.setString(3,mail);
        pstmt.setInt(4,benef);
        pstmt.setInt(5,age);
        pstmt.setInt(6,tel);
        pstmt.executeUpdate();
    }

}
