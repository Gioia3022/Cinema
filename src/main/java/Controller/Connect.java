package Controller;


import Model.Benefice;
import Model.Guest;
import Model.Session;
import Model.Ticket;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

// CLASSE PERMETTANT LA CREATION DE TOUTES LES QUERY EVOQUE DANS LES AUTRES CLASSES
public class Connect {
    private Connection conn;
    private ResultSet rs;
    private Statement stmt;
    private PreparedStatement pstmt = null;


    /**
     * Test connection bdd
     * @param c
     * @param s
     */
    public Connect(Connection c, Statement s){
        this.conn=c;
        this.stmt=s;
    }

    public void closeCourseDataConnection() throws SQLException {
        conn.close();
    }

    /**
     * Connexion admin : si le mail et le mp n'est pas trouvé sur un même attribut
     * -> check = false -> msg d'erreur
     * @param user
     * @param pass
     * @return
     */
    public boolean SQLQueryAdmin(String user, String pass ){
        boolean check = false;
        // language=<SQL>
        String sql= "SELECT AdminMail from admin WHERE AdminMail =? AND AdminMP= ? ;";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,String.valueOf(user));
            pstmt.setString(2,String.valueOf(pass));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            }else
                check = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    /**
     * Creation d'un attribut de la table admin dans la bdd avec le nom d'utilisateur,
     * l'email, et le mdp
     * @param name
     * @param mail
     * @param MP
     */
    public void SQLQueryAdminNewMP(String name, String mail, String MP){
        // language=<SQL>
        String sql= "UPDATE admin SET AdminMP=? WHERE AdminMail =? AND AdminName=?;";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,MP);
            pstmt.setString(2,mail);
            pstmt.setString(3,name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode qui verifie si le client existe
     * @param mail
     * @return
     */
    public boolean SQLQueryCheckGuest(String mail){
        boolean exists = false;
        // language=<SQL>
        String sql= "SELECT GuestMail AND GuestName AND GuestTel from guest WHERE GUESTMail =?;";
        try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,String.valueOf(mail));
        rs = pstmt.executeQuery();
        if (rs.next()) {
            exists = true;
        }else
            exists = false;
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return exists;
    }

    /**
     * Verification que le guest existe ou pas
     * @param user
     * @param pass
     * @return
     */
    public boolean SQLQueryGuest(String user, String pass )  {
        boolean check = false;
        // language=<SQL>
        String sql= "SELECT GuestMail from guest WHERE GuestMail =? AND GuestMP= ? ;";
        try{
            pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,String.valueOf(user));
        pstmt.setString(2,String.valueOf(pass));
        rs = pstmt.executeQuery();
        if (rs.next()) {
            check = true;
        }else
            check = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    /**
     * Modification du mot passe de l'utilisateur lorsqu'il a choisi de le modifier
     * Update le mot de passe pour l'attribut de la table guest comportant le name,
     * mail et tel inscrit envoyé en paramètre
     * @param name
     * @param mail
     * @param MP
     * @param tel
     */
    public void SQLQueryGuestNewMP(String name, String mail, String MP, String tel) {
        // language=<SQL>
        String sql= "UPDATE guest SET GuestMP=? WHERE GuestMail =? AND GuestName=? AND GuestTel=?;";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,MP);
            pstmt.setString(2,mail);
            pstmt.setString(3,name);
            pstmt.setString(4,tel);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insertion d'un nouveau client dans la table Guest avec les valeurs inscrites en paramètre
     * @param name
     * @param mail
     * @param psw
     * @param tel
     * @param age
     * @param benef
     */
    public void SQLQueryNewGuest(String name, String mail, String psw, String tel, int age, String benef) {
        // language=<SQL>
        String sql= "INSERT INTO guest (GuestName,GuestMP, GuestMail,GuestBenefice, GuestAge,GuestTel) VALUES (?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,psw);
            pstmt.setString(3,mail);
            pstmt.setString(4,benef);
            pstmt.setInt(5,age);
            pstmt.setString(6,tel);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Donne toutes la valeur FilmName de la table Film dans la bdd
     * @param film_model
     * @return
     */
    public ArrayList<String> SQLQueryFilmName(Model.Film film_model) {
        film_model.setNames(new ArrayList<>());
        // language=<SQL>
        String sql = "Select FilmName from film";

        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                String name = rs.getString(1);
                film_model.getNames().add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film_model.getNames();
    }

    /**
     * A partir des FilmName, on donne les valeur associé
     * @param name
     * @param film_model
     */
    public void SQLQueryInfoFilm(String name, Model.Film film_model) {
        // language=<SQL>
        String sql = "Select * from film where FilmName= '"+ name+ "';";

        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recuperation de la liste de seances associé à l'identifient d'un film
     * @param nameFilm
     * @param s
     */
    public void SQLSessionsDispo(String nameFilm, Session s){
        int IDFilm=0;
        s.setDateArrayList(new ArrayList<>());

        // language=<SQL>
        String sql ="Select FilmID from film where FilmName= '"+nameFilm+"';";
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Récuperation du type de benefice associé à un client
     * @param guest
     */
    public void SQLrecupInfoGuest(Guest guest)  {
        // language=<SQL>
        String sql ="Select GuestBenefice from guest where GuestMail='"+guest.getMail()+"';";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                guest.setBenef(rs.getString("GuestBenefice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insertion d'un nouveau ticket
     * @param film
     * @param session
     * @param ticket
     * @param guest
     */
    public void SQLFormationTicket(Model.Film film,Session session, Ticket ticket, Guest guest){
        int GuestID = 0, FimID=0, SessionID=0;
        // language=<SQL>
        String sql1="SELECT GuestID from guest WHERE GuestMail='"+guest.getMail()+"';";
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recuperation du prix à partir de la table cinema
     * @return
     */
    public float SQLQueryPrice()  {
        float price = 0;
        // language=<SQL>
        String sql="SELECT Price from cinema";
        try {
            pstmt=conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                price=rs.getFloat("Price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }

    /**
     * Récuperation de tout les noms et benefices dans des arraylist
     * @param benefice
     */
    public void SQLQueryAllBenefice(Benefice benefice) {
        benefice.setNames(new ArrayList<>());
        benefice.setDiscounts(new ArrayList<>());
        // language=<SQL>
        String sql= "SELECT NomBenef from benefice";
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Insertion d'un nouveau benefice
     * @param Name
     * @param benef
     */
    public void SQLQueryAddBenefice(String Name, float benef){
        // language=<SQL>
        String sql="INSERT INTO benefice (NomBenef, Discount) VALUES (?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,Name);
            pstmt.setFloat(2,benef);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Associer une remise à un nom de remise
     * @param name
     * @return
     */
    public float SQLQueryBenefice(String name) {
        float discount = 1;
        // language=<SQL>
        String sql="SELECT Discount from benefice where NomBenef='"+ name+"';";
        try {
            pstmt=conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                discount = rs.getFloat("Discount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }

    /**
     * Suppression d'un type de remise
     * @param name
     */
    public void SQLQueryDeleteBenefice(String name) {
        // language=<SQL>
        try {
            String sql="delete from benefice where NomBenef='"+name+"'; ";

            pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate(sql);

            sql= "UPDATE guest SET GuestBenefice=? WHERE GuestBenefice ='"+name+"';";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Modifier le pourcentage de remise
     * @param remise
     * @param name
     */
    public void SQLQueryModifBenef(float remise, String name) {
        // language=<SQL>
        String sql= "UPDATE benefice SET Discount=? WHERE NomBenef ='"+name+"';";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1,remise);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récuperation du nombre de places dispo dans une salle
     * @param session
     * @return
     */
    public int SQLQueryRoom(Session session) {
        int seets=0,RoomID = 0;
        // language=<SQL>
        String sql1="SELECT RoomID from session WHERE SessionDate='"+session.getDate()+"';";
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seets;
    }

    /**
     * Récupere numero de places déjà attitré
     * @param session
     * @return
     */
    public int SQLQuerySessionSeets(Session session) {
        int NumberedOfBookedSeets=0;
        // language=<SQL>
        String sql="SELECT NbrBookedSeets from session where SessionDate='"+ session.getDate() +"';";
        try {
            pstmt=conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                NumberedOfBookedSeets=rs.getInt("NbrBookedSeets");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return NumberedOfBookedSeets;
    }

    /**
     * Met a jour le nombre de places occupées
     * @param seets
     * @param s
     */
    public void SQLQueryBookedSeets(int seets, Session s) {
        // language=<SQL>
        String sql= "UPDATE session SET NbrBookedSeets="+seets+" WHERE SessionDate ='"+s.getDate()+"';";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Suppression d'un film
     * @param name
     */
    public void SQLQueryDeleteFilm(String name) {
        // language=<SQL>
        int ID=0;
        String sql="Select FilmID FROM film WHERE FilmName='"+name+"';";
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajout d'un film
     * @param name
     * @param gender
     * @param length
     * @param date
     * @param director
     * @param url
     */
    public void SQLQueryAddFilm(String name, String gender, int length, Date date, String director, String url) {
        // language=<SQL>
        String sql= "INSERT INTO film (FilmName, FilmGenre, FilmLength, FilmRelease, Director,FilmImage) VALUES (?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,gender);
            pstmt.setInt(3,length);
            pstmt.setDate(4,date);
            pstmt.setString(5,director);
            pstmt.setString(6,url);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajout séance
     * @param session
     */
    public void SQLQueryAllSeances(Session session) {
        session.setDateArrayList(new ArrayList<>());
        // language=<SQL>
        String sql= "SELECT SessionDate from session";
        try {
            pstmt=conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            while (rs != null && rs.next()) {
                session.setDate(rs.getString("SessionDate"));
                session.getDateArrayList().add(session.getDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprimer une séance
     * @param date
     */
    public void SQLQueryDeleteSeance(String date){
        // language=<SQL>
        int ID=0;
        String sql="Select SessionID FROM session WHERE SessionDate='"+date+"';";
        try {
            pstmt=conn.prepareStatement(sql);rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ID=rs.getInt("SessionID");
            }
            sql="delete from ticket where SessionID="+ID+"; ";
            pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate(sql);
            sql="delete from session where SessionID="+ID+"; ";
            pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprimer une séance
     * @param name
     * @param room
     * @param date
     */
    public void SQLQueryAddSeance(String name, int room, String date) {
        int ID=0;
        // language=<SQL>
        String sql= "SELECT FilmID from film WHERE FilmName='"+name+"';";
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mis à jour d'une séance
     * @param new_date
     * @param old_date
     */
    public void SQLQueryUpdateSeance(String new_date, String old_date) {
        // language=<SQL>
        int ID=0;
        String sql="Select SessionID FROM session WHERE SessionDate='"+old_date+"';";
        try {
            pstmt=conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ID=rs.getInt("SessionID");
            }
            sql= "UPDATE session SET SessionDate=? WHERE SessionID ='"+ID+"';";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,new_date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
