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
        rs=stmt.executeQuery("SELECT * FROM admin");
        while (rs.next()){
            System.out.println(rs.getString("AdminName"));
        }
    }

    public void closeCourseDataConnection() throws SQLException {
        conn.close();
    }



    //Checks validity of mail
    public boolean SQLQueryAdmin(String user, String pass ) throws SQLException {
        boolean check;
        // language=<SQL>
        String sql= "SELECT AdminMail from admin WHERE AdminMail =? AND AdminMP= ? ;";
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

    public void SQLQueryAdminNewMP(String name, String mail, String MP) throws SQLException{
        // language=<SQL>
        String sql= "UPDATE admin SET AdminMP=? WHERE AdminMail =? AND AdminName=?;";
        pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,MP);
        pstmt.setString(2,mail);
        pstmt.setString(3,name);
        pstmt.executeUpdate();
    }

    public boolean SQLQueryGuest(String user, String pass ) throws SQLException {
        boolean check;
        // language=<SQL>
        String sql= "SELECT GuestMail from guest WHERE GuestMail =? AND GuestMP= ? ;";
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
    public void SQLQueryGuestNewMP(String name, String mail, String MP,int tel) throws SQLException{
        // language=<SQL>
        String sql= "UPDATE guest SET GuestMP=? WHERE GuestMail =? AND GuestName=? AND GuestTel=?;";
        pstmt = conn.prepareStatement(sql);
        System.out.println(1);

        pstmt.setString(1,MP);
        pstmt.setString(2,mail);
        pstmt.setString(3,name);
        pstmt.setInt(4,tel);
        pstmt.executeUpdate();
    }

    public void SQLQueryNewGuest(String name, String mail,String psw, int tel, int age, String benef)throws SQLException{
        // language=<SQL>
        String sql= "INSERT INTO guest (GuestName,GuestMP, GuestMail,GuestBenefice, GuestAge,GuestTel) VALUES (?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,psw);
        pstmt.setString(3,mail);
        pstmt.setString(4,benef);
        pstmt.setInt(5,age);
        pstmt.setInt(6,tel);
        pstmt.executeUpdate();
    }

    //Gives all film names from database
    public ArrayList<String> SQLQueryFilmName(Cinema.Film film_model) throws SQLException {
        film_model.setNames(new ArrayList<>());
        // language=<SQL>
        String sql = "Select FilmName from film";

        rs = stmt.executeQuery(sql);

        while (rs != null && rs.next()) {
            String name = rs.getString(1);
            film_model.getNames().add(name);
        }
        return film_model.getNames();
    }
    public void SQLQueryInfoFilm(String name, Cinema.Film film_model)throws SQLException{
        // language=<SQL>
        String sql = "Select * from film where FilmName= '"+ name+ "';";
        pstmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);

        while (rs != null && rs.next()) {
            film_model.setFilmName(rs.getString("FilmName"));
            film_model.setFilmGenre(rs.getString("FilmGenre"));
            film_model.setDuration(rs.getInt("FilmLength"));
            film_model.setFilmRelease(rs.getDate("FilmRelease"));
            film_model.setDirector(rs.getString("Director"));
            film_model.setImage(rs.getString("FilmImage"));
        }
    }

}
