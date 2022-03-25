package Controller;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


public class Connect {
    Connection conn;
    ResultSet rs = null;
    private ResultSetMetaData rsMeta;
    Statement stmt;
    PreparedStatement pstmt = null;


    public Connect(Connection c, Statement s) throws SQLException {
        this.conn=c;
        this.stmt=s;
        rs=stmt.executeQuery("SELECT * FROM Admin");
        while (rs.next()){
            System.out.println(rs.getString(4));
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
        conn.close();
        return check;
    }
    public void SQLQueryNewGuest(String name, String mail,String psw)throws SQLException{
        // language=<SQL>
        String sql;
        // language=<SQL>
        sql= "INSERT INTO guest (GuestName, GuestMP, GuestMail) VALUES (?, ?, ?)";
/*
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,psw);
        pstmt.setString(3,mail);
        pstmt.executeUpdate();*/

        stmt.executeUpdate(sql);
        this.conn.close();
    }
/*
    public void SQLQueryAddInfo(String benef, int age, int tel)throws SQLException{
        // language=<SQL>
        String sql;
        // language=<SQL>
        sql= "INSERT INTO guest (GuestBenefice, GuestAge, GuestTel)" +
                "VALUES (?, ?, ?)";
        //this.stmt.executeUpdate(sql);

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,benef);
        pstmt.setString(2,String.valueOf(age));
        pstmt.setString(3,String.valueOf(tel));
        pstmt.executeUpdate();

        this.conn.close();
    }

 */
}
