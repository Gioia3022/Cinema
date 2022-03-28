package Controller;

import Cinema.Session;

import java.sql.*;
import java.util.ArrayList;

// CLASSE PERMETTANT LA CREATION DE TOUTES LES QUERY EVOQUE DANS LES AUTRES CLASSES
//
public class Connect {
    Connection conn;
    ResultSet rs;
    private ResultSetMetaData rsMeta;
    Statement stmt;
    PreparedStatement pstmt = null;

//test connection bdd
    public Connect(Connection c, Statement s) throws SQLException {
        this.conn=c;
        this.stmt=s;
        // language=<SQL>
        rs=stmt.executeQuery("SELECT * FROM admin");
        while (rs.next()){
            System.out.println(rs.getString("AdminName"));
        }
    }
//methode close connection avec la bdd
    public void closeCourseDataConnection() throws SQLException {
        conn.close();
    }



    //connexion admin : si le mail et le mp n'est pas trouvé sur un même attribut -> check = false -> msg d'erreur
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

    //Creation d'un attribut de la table admin dans la bdd avec le nom d'utilisateur, l'email, et le mdp
    public void SQLQueryAdminNewMP(String name, String mail, String MP) throws SQLException{
        // language=<SQL>
        String sql= "UPDATE admin SET AdminMP=? WHERE AdminMail =? AND AdminName=?;";
        pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,MP);
        pstmt.setString(2,mail);
        pstmt.setString(3,name);
        pstmt.executeUpdate();
    }

    public boolean SQLQueryCheckGuest(String mail) throws SQLException {
        boolean exists;
        // language=<SQL>
        String sql= "SELECT GuestMail AND GuestName AND GuestTel from guest WHERE GUESTMail =?;";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,String.valueOf(mail));
        rs = pstmt.executeQuery();
        if (rs.next()) {
            exists = true;
        }else
            exists = false;
        return exists;
    }

    // verification que le guest exciste ou pas
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

    // modification du mot passe de l'utilisateur lorsqu'il a choisi de le modifier
    //Update le mot de passe pour l'attribut de la table guest comportant le name, mail et tel inscrit envoyé en paramètre
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

    //Création d'un nouvel attribut dans la table Guest avec les valeurs inscrites en paramètre
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

    //Donne toutes la valeur FilmName de la table Film dans la bdd
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

    // A partir des FilmName, on donne les valeur associé
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
    public void SQLSessionsDispo(String nameFilm, Session s) throws SQLException {
        int IDFilm=0;

        // language=<SQL>
        String sql ="Select FilmID from film where FilmName= '"+nameFilm+"';";
        pstmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);
        while (rs != null && rs.next()) {
            IDFilm=rs.getInt("FilmID");
        }
        // language=<SQL>
        String sql_1 ="Select FilmTime from session where FilmID= "+ IDFilm+";";
        pstmt = conn.prepareStatement(sql_1);
        rs = stmt.executeQuery(sql_1);
        while (rs != null && rs.next()) {
            s.setFilmTime(rs.getTime("FilmTime").toLocalTime().minusHours(1));
        }

        // language=<SQL>
        String sql_2 ="Select SessionDate from session where FilmID= "+ IDFilm+";";
        pstmt = conn.prepareStatement(sql_2);
        rs = stmt.executeQuery(sql_2);
        while (rs != null && rs.next()) {
            s.setSessionDate(rs.getDate("SessionDate"));
        }
    }

}
