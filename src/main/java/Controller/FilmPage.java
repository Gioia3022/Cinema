package Controller;

import Model.Guest;
import Model.Session;
import Model.Ticket;

import java.sql.SQLException;

public class FilmPage {
    //attributs
    //
    private Model.Film film;
    private Guest guest;
    private View.FilmPage filmPage;
    private Controller.Film film_controller;
    private BigController bigController;
    private Session session;
    private Achat achat;
    private Ticket ticket;
    private int usable_seets, occupied_seets; //cet attribut va obtenir le nombre de place restantes via la bdd

    //constructeur - Création de l'affichage Film page appelé dans la classe Film
    public FilmPage(BigController bigController, Model.Film f, Guest g) {
        this.film=f; //appel des attributs du modèle, donc des attributs de la bdd de la table film
        this.guest=g;
        session=new Session(); //initialisation de la session de l'utilisateur, qui va pouvoir choisir une session, et ainsi un ticket
        this.ticket=new Ticket();
        this.bigController=bigController;
        try { //QUERY qui permet d'obtenir toutes les sessions disponibles pour le film choisi
            this.bigController.getC().SQLSessionsDispo(this.film.getFilmName(),session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nbrOfTickets(); //méthode décrite ci-dessous
        filmPage= new View.FilmPage(this,this.bigController.getFrame()); //Affichage du view de filmPage
        this.bigController.getFrame().getContentPane().add(filmPage);

    }

    //Méthodes
    public void nbrOfTickets(){
        int tot_seets; //cet attribut permet d'obtenir le nombre total de places selon la salle ou le film passe
        try {
            //Récupère le nombre de place tot de la salle ou est passé la session du film choisi depuis la base de donnée
            tot_seets=this.bigController.getC().SQLQueryRoom(getSession());
            //Récupère le nombre de places disponibles dans la salle
            occupied_seets=this.bigController.getC().SQLQuerySessionSeets(getSession());
            setUsable_seets(tot_seets-occupied_seets); //l'attribut est modifié selon un calcul simple
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Méthode de création du ticket de l'utilisateur avec comme paramètres la session du film et le nombre de tickets acheté
    public void newTicket(String session, String nbrT){
        this.ticket.setNbrPlace(Integer.parseInt(nbrT));
        int seets= ticket.getNbrPlace()+getOccupied_seets(); //seets est l'atribut affichant le nombre de places dispo
        try {
            this.bigController.getC().SQLQueryBookedSeets(seets,getSession());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        float price=10, remise = 1, ticketPrice = 0; //initialisation des prix des tickets sans benefices
        this.session.setDate(session); //la date du ticket est intancié par la date de la session
        if(this.guest.getMail()!=null){  //Si l'utilisateur est connecté
        try {
            //Récupère les info du guest (attribut de l'objet de la bdd)
            this.bigController.getC().SQLrecupInfoGuest(this.guest);
            //le prix est instancié dans la base de donnée
            price=this.bigController.getC().SQLQueryPrice();
            //La remise est calculé selon le type de bénéfice auquel l'utilisateur est accordé (bdd)
            remise=this.bigController.getC().SQLQueryBenefice(this.guest.getBenef());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        ticketPrice=price*remise; //ainsi la remise est ajouté au prix final
        ticketPrice=ticketPrice*Integer.parseInt(nbrT); //le prix final est multiplié par le nombre de tickets
        this.ticket.setPrice(ticketPrice); //le prix est donc set selon cet attribut
        setAchat(new Achat(this.bigController, this.ticket, this.guest, this.session, this.film));
        setVisible(false);
        achat.setVisible(true); //Passage au model de la classe Achat, ou l'utilisateur va rentrer son paiment
    }

    //set visible le film si le booléen est true
    public void setVisible(boolean visible){
        filmPage.setVisible(visible);
    }

    //getters & setters
    public Model.Session getSession() {
        return session;
    }
    public Model.Ticket getTicket() {
        return ticket;
    }
    public Model.Film getFilm() {
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
