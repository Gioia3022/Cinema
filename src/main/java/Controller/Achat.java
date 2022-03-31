package Controller;

import Model.Film;
import Model.Guest;
import Model.*;

import java.sql.SQLException;

public class Achat {

    private BigController bigController;
    private View.Achat achat;
    private Menu menu;
    private Model.Film film;
    private Session session;
    private Ticket ticket;
    private Guest guest;
    private boolean aBoolean;

    public Achat(BigController big, Ticket ticket, Guest guest, Session session, Film film){
        aBoolean=false;
        this.film=film;
        this.guest=guest;
        this.session=session;
        this.ticket=ticket;
        this.bigController=big;
        achat= new View.Achat(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(achat);
    }

    public Achat(BigController big, Ticket ticket, Guest guest, Session session, Film film, boolean anonyme){
        aBoolean=anonyme;
        this.film=film;
        this.guest=guest;
        this.session=session;
        this.ticket=ticket;
        this.bigController=big;
        achat= new View.Achat(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(achat);
    }

    public void ticket(){
        if(this.guest.getMail()!=null){
        try {
            this.bigController.getC().SQLFormationTicket(film,session,ticket,guest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        achat.mes1();
        String mes="Bonjour,\nVoici votre ticket: \nMail achat: "+guest.getMail()+"\nFilm: "+film.getFilmName()+"\nSeance du: "+session.getDate()+"\nNombre de places: "+ticket.getNbrPlace()+"\nPrice: "+ticket.getPrice();
        Mail.send("cinemaleshalles5@gmail.com","CinemaH1!",guest.getMail(),"Ticket Cinema",mes);
        }
        else {
            setaBoolean(true);
            new Achat(this.bigController,ticket,guest,session,film,isaBoolean());
            achat.mes3();
        }
        System.exit(0);
    }
    public void menu(){
        setMenu(new Controller.Menu(this.bigController));
    }

    public void setVisible(boolean visible){
        achat.setVisible(visible);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public Film getFilm() {
        return film;
    }

    public Session getSession() {
        return session;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
