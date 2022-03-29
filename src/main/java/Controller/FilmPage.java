package Controller;

import Cinema.Guest;
import Cinema.Session;
import Cinema.Ticket;

import java.sql.SQLException;

public class FilmPage {
    //attributs
    //
    private Cinema.Film film;
    private Guest guest;
    private View.FilmPage filmPage;
    private Controller.Film film_controller;
    private BigController bigController;
    private Session session;
    private Achat achat;
    private Ticket ticket;
    private int usable_seets, occupied_seets;

    //méthodes
    //

    //constructeur - Création de l'affichage Film page appelé dans la classe Film
    public FilmPage(BigController bigController, Cinema.Film f, Guest g) {
        this.film=f;
        this.guest=g;
        session=new Session();
        this.ticket=new Ticket();
        this.bigController=bigController;
        try {
            this.bigController.getC().SQLSessionsDispo(this.film.getFilmName(),session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nbrOfTickets();
        filmPage= new View.FilmPage(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(filmPage);

    }

    public void nbrOfTickets(){
        int tot_seets;
        try {
            tot_seets=this.bigController.getC().SQLQueryRoom(getSession());
            occupied_seets=this.bigController.getC().SQLQuerySessionSeets(getSession());
            setUsable_seets(tot_seets-occupied_seets);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newTicket(String session, String nbrT){
        this.ticket.setNbrPlace(Integer.parseInt(nbrT));
        int seets= ticket.getNbrPlace()+getOccupied_seets();
        try {
            this.bigController.getC().SQLQueryBookedSeets(seets,getSession());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        float price=10, remise = 1, ticketPrice = 0;
        this.session.setDate(session);
        if(this.guest.getMail()!=null){
        try {
            System.out.println(this.guest.getMail());
            this.bigController.getC().SQLrecupInfoGuest(this.guest);
            price=this.bigController.getC().SQLQueryPrice();
            remise=this.bigController.getC().SQLQueryBenefice(this.guest.getBenef());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        ticketPrice=price*remise;
        ticketPrice=ticketPrice*Integer.parseInt(nbrT);
        this.ticket.setPrice(ticketPrice);
        setAchat(new Achat(this.bigController, this.ticket, this.guest, this.session, this.film));
        setVisible(false);
        achat.setVisible(true);
    }

    //set visible le film si le booléen est true
    public void setVisible(boolean visible){
        filmPage.setVisible(visible);
    }

    //getters & setters
    public Cinema.Session getSession() {
        return session;
    }
    public Cinema.Ticket getTicket() {
        return ticket;
    }
    public Cinema.Film getFilm() {
        return film;
    }

    public void film(){setFilm(new Film(this.bigController,guest));}
    public Film getFilm_c() {
        return film_controller;
    }
    public void setFilm(Film film) {
        this.film_controller = film;
    }

    public void setAchat(Achat achat) {
        this.achat = achat;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getUsable_seets() {
        return usable_seets;
    }

    public void setUsable_seets(int usable_seets) {
        this.usable_seets = usable_seets;
    }

    public int getOccupied_seets() {
        return occupied_seets;
    }

    public void setOccupied_seets(int occupied_seets) {
        this.occupied_seets = occupied_seets;
    }
}
