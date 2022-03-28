package Controller;

import Cinema.Session;

import java.sql.SQLException;

public class FilmPage {
    //attributs
    //
    private Cinema.Film film;
    private View.FilmPage filmPage;
    private Controller.Film film_controller;
    private BigController bigController;
    private Session session;

    //méthodes
    //

    //constructeur 1 - Création de l'affichage Film page appelé dans la classe Film
    public FilmPage(BigController bigController, Cinema.Film f) {
        this.film=f;
        session=new Session();
        this.bigController=bigController;
        try {
            this.bigController.getC().SQLSessionsDispo(this.film.getFilmName(),session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        filmPage= new View.FilmPage(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(filmPage);

    }

    //set visible le film si le booléen est true
    public void setVisible(boolean visible){
        filmPage.setVisible(visible);
    }

    //getters & setters
    public Cinema.Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Cinema.Film getFilm() {
        return film;
    }

    public void film(){setFilm(new Film(this.bigController));}
    public Film getFilm_c() {
        return film_controller;
    }
    public void setFilm(Film film) {
        this.film_controller = film;
    }
}
