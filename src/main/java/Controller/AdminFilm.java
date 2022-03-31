package Controller;

import Model.Film;

import java.sql.Date;
import java.sql.SQLException;

public class AdminFilm {
    private BigController bigController; //instancie la connection à la base et l'affichage de la Frame
    private View.AdminFillm adminFilm; //Objet admin partie affichage
    private AdminMenu adminMenu;
    private Film film;

    public AdminFilm(BigController bigController) {
        this.bigController=bigController; //connexion Query sql
        this.film=new Film();
        try {
            this.film.setNames(this.bigController.getC().SQLQueryFilmName(this.film));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adminFilm= new View.AdminFillm(this,this.bigController.getFrame());  //
        this.bigController.getFrame().getContentPane().add(adminFilm);     //Frame de la page admin
    }

    public void delete(String film_name){
        try {
            this.bigController.getC().SQLQueryDeleteFilm(film_name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        menu();
        setVisible(false);
        getMenu().setVisible(true);
    }

    public void addFilm(String name, String gender, String length, String date, String director, String url){
        Date d= Date.valueOf(date);
        try {
            this.bigController.getC().SQLQueryAddFilm(name, gender, Integer.parseInt(length), d, director, url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
