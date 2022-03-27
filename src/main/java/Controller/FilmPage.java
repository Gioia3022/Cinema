package Controller;

public class FilmPage {
    private Cinema.Film film;
    private View.FilmPage filmPage;
    private Controller.Film film_controller;
    private BigController bigController;

    public FilmPage(BigController bigController, Cinema.Film f) {
        this.film=f;
        System.out.println(this.film.getDuration());
        this.bigController=bigController;
        filmPage= new View.FilmPage(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(filmPage);

    }
    public void setVisible(boolean visible){
        filmPage.setVisible(visible);
    }

    public Controller.Film getFilm_controller() {
        return film_controller;
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
