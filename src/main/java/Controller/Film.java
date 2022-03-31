package Controller;

import Model.Guest;

import java.sql.SQLException;

public class Film {
    private BigController bigController;
    private Guest guest;
    private Menu menu;
    private Log log;
    private View.Film film_view;
    private Model.Film film;
    private FilmPage filmPage;

    public Film(BigController co, Guest g){
        this.guest=g;
        this.bigController=co;
        this.film= new Model.Film();
        try {
            this.film.setNames(this.bigController.getC().SQLQueryFilmName(this.film));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        film_view= new View.Film(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(film_view);
    }

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
