package Controller;

import Model.Guest;

import java.sql.SQLException;

public class Film {
    //attributs
    private BigController bigController;
    private Guest guest;
    private Menu menu;
    private View.Film film_view;
    private Model.Film film;
    private FilmPage filmPage;

    //Constructeur
    //Le constructeur a en paramètre l'objet g de Type Guest pour que l'utilisateur soit bien connecté
    public Film(BigController co, Guest g){
        this.guest=g;
        this.bigController=co;
        this.film= new Model.Film(); //Attribut de la table film de la base de donnée
        try { //les Noms des films se trouvent dans la base de donnée, d'ou la query demandant le nom du film en param
            this.film.setNames(this.bigController.getC().SQLQueryFilmName(this.film));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        film_view= new View.Film(this,this.bigController.getFrame()); //creation frame de la classe film (view)
        this.bigController.getFrame().getContentPane().add(film_view);
    }
    //méthodes
    //le film en paramètre de la query demandé par l'utilisateur affichera les attributs de l'objet souhaité de la table film
    public void get_info(String name){
        try {
            this.bigController.getC().SQLQueryInfoFilm(name,film);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void filmPage(){
        setFilmPage(new FilmPage(this.bigController,film, guest));
    }

    //getters & setter
    public Model.Film getFilm() {
        return film;
    }

    public FilmPage getFilmPage() {
        return filmPage;
    }

    public void setFilmPage(FilmPage filmPage) {
        this.filmPage = filmPage;
    }

    public void menu() {
        setMenu(new Menu(this.bigController));
    }
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setVisible(boolean visible){
        film_view.setVisible(visible);
    }
}
