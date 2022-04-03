package Controller;

import Model.Film;

import java.sql.Date;

public class AdminFilm {
    private BigController bigController; //instancie la connection à la base et l'affichage de la Frame
    private View.AdminFillm adminFilm; //Objet admin partie affichage
    private AdminMenu adminMenu;
    private Film film;

    /**
     * Constructeur de la page film
     * @param bigController
     */
    public AdminFilm(BigController bigController) {
        this.bigController=bigController; //connexion Query sql
        this.film=new Film();
        this.film.setNames(this.bigController.getC().SQLQueryFilmName(this.film));
        adminFilm= new View.AdminFillm(this,this.bigController.getFrame());  //
        this.bigController.getFrame().getContentPane().add(adminFilm);     //Frame de la page admin
    }

    /**
     * Cette methode permet d'éffacer un film
     * @param film_name
     */
    public void delete(String film_name){
        this.bigController.getC().SQLQueryDeleteFilm(film_name);
        menu();
        setVisible(false);
        getMenu().setVisible(true);
    }

    /**
     * Ajout d'un nouveau film a la bdd
     * @param name
     * @param gender
     * @param length
     * @param date
     * @param director
     * @param url
     */
    public void addFilm(String name, String gender, String length, String date, String director, String url){
        Date d= Date.valueOf(date);
        this.bigController.getC().SQLQueryAddFilm(name, gender, Integer.parseInt(length), d, director, url);
        menu();
        setVisible(false);
        getMenu().setVisible(true);
    }

    public void menu(){
        setMenu(new AdminMenu(this.bigController));
    }
    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }
    public void setVisible(boolean visible){
        adminFilm.setVisible(visible);
    } //affichage
    public AdminMenu getMenu() {
        return adminMenu;
    }
    public void setMenu(AdminMenu menu) {
        this.adminMenu = menu;
    }
}
