package Controller;


import Model.Guest;
import Model.Session;
import Model.Ticket;

import java.sql.*;
import java.time.LocalDate;
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
    public void SQLQueryGuestNewMP(String name, String mail, String MP, String tel) throws SQLException{
        // language=<SQL>
        String sql= "UPDATE guest SET GuestMP=? WHERE GuestMail =? AND GuestName=? AND GuestTel=?;";
        pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,MP);
        pstmt.setString(2,mail);
        pstmt.setString(3,name);
        pstmt.setString(4,tel);
        pstmt.executeUpdate();
    }

    //Création d'un nouvel attribut dans la table Guest avec les valeurs inscrites en paramètre
    public void SQLQueryNewGuest(String name, String mail, String psw, String tel, int age, String benef)throws SQLException{
        // language=<SQL>
        String sql= "INSERT INTO guest (GuestName,GuestMP, GuestMail,GuestBenefice, GuestAge,GuestTel) VALUES (?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,psw);
        pstmt.setString(3,mail);
        pstmt.setString(4,benef);
        pstmt.setInt(5,age);
        pstmt.setString(6,tel);
        pstmt.executeUpdate();
    }

    //Donne toutes la valeur FilmName de la table Film dans la bdd
    public ArrayList<String> SQLQueryFilmName(Model.Film film_model) throws SQLException {
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
    public void SQLQueryInfoFilm(String name, Model.Film film_model)throws SQLException{
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
        s.setDateArrayList(new ArrayList<>());

        // language=<SQL>
        String sql ="Select FilmID from film where FilmName= '"+nameFilm+"';";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery(sql);
        while (rs != null && rs.next()) {
            IDFilm=rs.getInt("FilmID");
        }
        // language=<SQL>
        String sql_1 ="Select SessionDate from session where FilmID= "+ IDFilm+";";
        pstmt = conn.prepareStatement(sql_1);
        rs = pstmt.executeQuery(sql_1);
        while (rs != null && rs.next()) {
            s.setDate(rs.getString("SessionDate"));
            s.getDateArrayList().add(s.getDate());
            System.out.println(s.getDate());

        }
    }

    public void SQLrecupInfoGuest(Guest guest) throws SQLException {
        // language=<SQL>
        String sql ="Select GuestBenefice from guest where GuestMail='"+guest.getMail()+"';";
        pstmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            guest.setBenef(rs.getString("GuestBenefice"));
        }
    }

    public void SQLFormationTicket(Model.Film film,Session session, Ticket ticket, Guest guest) throws SQLException {
        int GuestID = 0, FimID=0, SessionID=0;
        // language=<SQL>
        String sql1="SELECT GuestID from guest WHERE GuestMail='"+guest.getMail()+"';";
        pstmt = conn.prepareStatement(sql1);
        rs = stmt.executeQuery(sql1);
        while (rs.next()) {
            GuestID=rs.getInt(1);
        }
        // language=<SQL>
        String sql2="SELECT FilmID from film WHERE FilmName='"+film.getFilmName()+"';";
        pstmt = conn.prepareStatement(sql2);
        rs = stmt.executeQuery(sql2);
        while (rs.next()) {
            FimID=rs.getInt(1);
        }
        // language=<SQL>
        String sql3="SELECT SessionID from session WHERE SessionDate='"+session.getDate()+"';";
        pstmt = conn.prepareStatement(sql3);
        rs = stmt.executeQuery(sql3);
        while (rs.next()) {
            SessionID=rs.getInt(1);
        }

        // language=<SQL>
        String sql ="INSERT INTO ticket (NbrPlace,Price,GuestID, FilmID, SessionID, DateAchat) VALUES (?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,ticket.getNbrPlace());
        pstmt.setFloat(2,ticket.getPrice());
        pstmt.setInt(3,GuestID);
        pstmt.setInt(4,FimID);
        pstmt.setInt(5,SessionID);
        pstmt.setDate(6, Date.valueOf(LocalDate.now()));
        pstmt.executeUpdate();
    }

    public float SQLQueryPrice() throws SQLException {
        float price = 0;
        // language=<SQL>
        String sql="SELECT Price from cinema";
        pstmt=conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            price=rs.getFloat("Price");
        }
        return price;
    }

    public float SQLQueryBenefice(String name) throws SQLException {
        float discount = 1;
        // language=<SQL>
        String sql="SELECT Discount from benefice where NomBenef='"+ name+"';";
        pstmt=conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            discount=rs.getFloat("Discount");
        }
        return discount;
    }

    public int SQLQueryRoom(Session session) throws SQLException {
        int seets=0,RoomID = 0;
        // language=<SQL>
        String sql1="SELECT RoomID from session WHERE SessionDate='"+session.getDate()+"';";
        pstmt = conn.prepareStatement(sql1);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            RoomID=rs.getInt("RoomID");
        }
        // language=<SQL>
        String sql="SELECT RoomNbrSeats from room where RoomID="+ RoomID +";";
        pstmt=conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            seets=rs.getInt("RoomNbrSeats");
        }
        System.out.println(seets);

        return seets;
    }

    public int SQLQuerySessionSeets(Session session) throws SQLException {
        int NumberedOfBookedSeets=0;
        // language=<SQL>
        String sql="SELECT NbrBookedSeets from session where SessionDate='"+ session.getDate() +"';";
        pstmt=conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            NumberedOfBookedSeets=rs.getInt("NbrBookedSeets");
        }
        return NumberedOfBookedSeets;
    }
    public void SQLQueryBookedSeets(int seets, Session s) throws SQLException {
        // language=<SQL>
        String sql= "UPDATE session SET NbrBookedSeets="+seets+" WHERE SessionDate ='"+s.getDate()+"';";
        pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }

}
