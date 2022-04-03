package Controller;


import Model.Benefice;
import Model.Guest;
import Model.Session;
import Model.Ticket;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

// CLASSE PERMETTANT LA CREATION DE TOUTES LES QUERY EVOQUE DANS LES AUTRES CLASSES
//
public class Connect {
    private Connection conn;
    private ResultSet rs;
    private Statement stmt;
    private PreparedStatement pstmt = null;

//test connection bdd
    public Connect(Connection c, Statement s) throws SQLException {
        this.conn=c;
        this.stmt=s;
        // language=<SQL>
        rs=stmt.executeQuery("SELECT * FROM admin");
        while (rs.next()){
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

    public void SQLQueryAllBenefice(Benefice benefice) throws SQLException {
        benefice.setNames(new ArrayList<>());
        benefice.setDiscounts(new ArrayList<>());
        // language=<SQL>
        String sql= "SELECT NomBenef from benefice";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery(sql);
        while (rs != null && rs.next()) {
            benefice.setName(rs.getString("NomBenef"));
            benefice.getNames().add(benefice.getName());
        }
        sql= "SELECT Discount from benefice";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery(sql);
        while (rs != null && rs.next()) {
            benefice.setDiscount(rs.getFloat("Discount"));
            benefice.getDiscounts().add(benefice.getDiscount());
        }
    }

    public void SQLQueryAddBenefice(String Name, float benef) throws SQLException {
        // language=<SQL>
        String sql="INSERT INTO benefice (NomBenef, Discount) VALUES (?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,Name);
        pstmt.setFloat(2,benef);
        pstmt.executeUpdate();
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

    public void SQLQueryDeleteBenefice(String name) throws SQLException {
        // language=<SQL>
        String sql="delete from benefice where NomBenef='"+name+"'; ";

        pstmt=conn.prepareStatement(sql);
        pstmt.executeUpdate(sql);

        sql= "UPDATE guest SET GuestBenefice=? WHERE GuestBenefice ='"+name+"';";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,"");
        pstmt.executeUpdate();
    }

    public void SQLQueryModifBenef(float remise, String name) throws SQLException {
        // language=<SQL>
        String sql= "UPDATE benefice SET Discount=? WHERE NomBenef ='"+name+"';";
        pstmt = conn.prepareStatement(sql);
        pstmt.setFloat(1,remise);
        pstmt.executeUpdate();
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

    public void SQLQueryDeleteFilm(String name) throws SQLException {
        // language=<SQL>
        int ID=0;
        String sql="Select FilmID FROM film WHERE FilmName='"+name+"';";
        pstmt=conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            ID=rs.getInt("FilmID");
        }
        // language=<SQL>
        String sql1="delete from session where FilmID="+ID+"; ";
        pstmt=conn.prepareStatement(sql1);
        pstmt.executeUpdate(sql1);
        sql1="delete from ticket where FilmID="+ID+"; ";
        pstmt=conn.prepareStatement(sql1);
        pstmt.executeUpdate(sql1);
        sql1="delete from film where FilmID="+ID+"; ";
        pstmt=conn.prepareStatement(sql1);
        pstmt.executeUpdate(sql1);
    }

    public void SQLQueryAddFilm(String name, String gender, int length, Date date, String director, String url) throws SQLException {
        // language=<SQL>
        String sql= "INSERT INTO film (FilmName, FilmGenre, FilmLength, FilmRelease, Director,FilmImage) VALUES (?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,gender);
        pstmt.setInt(3,length);
        pstmt.setDate(4,date);
        pstmt.setString(5,director);
        pstmt.setString(6,url);
        pstmt.executeUpdate();
    }

    public void SQLQueryAllSeances(Session session) throws SQLException {
        session.setDateArrayList(new ArrayList<>());
        // language=<SQL>
        String sql= "SELECT SessionDate from session";
        pstmt=conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);

        while (rs != null && rs.next()) {
            session.setDate(rs.getString("SessionDate"));
            session.getDateArrayList().add(session.getDate());
        }
    }

    public void SQLQueryDeleteSeance(String date) throws SQLException {
        // language=<SQL>
        int ID=0;
        String sql="Select SessionID FROM session WHERE SessionDate='"+date+"';";
        pstmt=conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            ID=rs.getInt("SessionID");
        }
        sql="delete from ticket where SessionID="+ID+"; ";
        pstmt=conn.prepareStatement(sql);
        pstmt.executeUpdate(sql);
        sql="delete from session where SessionID="+ID+"; ";
        pstmt=conn.prepareStatement(sql);
        pstmt.executeUpdate(sql);
    }

    public void SQLQueryAddSeance(String name, int room, String date) throws SQLException {
        int ID=0;
        // language=<SQL>
        String sql= "SELECT FilmID from film WHERE FilmName='"+name+"';";
        pstmt=conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            ID=rs.getInt("FilmID");
        }
        sql= "INSERT INTO session (FilmID, RoomID, SessionDate, NbrBookedSeets) VALUES (?,?,?,0)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,ID);
        pstmt.setInt(2,room);
        pstmt.setString(3,date);
        pstmt.executeUpdate();
    }

    public void SQLQueryUpdateSeance(String new_date, String old_date) throws SQLException {
        // language=<SQL>
        int ID=0;
        String sql="Select SessionID FROM session WHERE SessionDate='"+old_date+"';";
        pstmt=conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            ID=rs.getInt("SessionID");
        }
        sql= "UPDATE session SET SessionDate=? WHERE SessionID ='"+ID+"';";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,new_date);
        pstmt.executeUpdate();
    }

}
