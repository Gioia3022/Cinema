package Controller;

import Model.Film;
import Model.Session;

import java.sql.SQLException;

public class AdminSession {
    private BigController bigController; //instancie la connection à la base et l'affichage de la Frame
    private View.AdminSession adminSession; //Objet admin partie affichage
    private AdminMenu adminMenu;
    private Session session;
    private Film film;
    private boolean modifier;

    public AdminSession(BigController bigController) {
        setModifier(false);
        this.bigController=bigController; //connexion Query sql
        this.session=new Session();
        this.film= new Model.Film();
        try {
            this.film.setNames(this.bigController.getC().SQLQueryFilmName(this.film));
            this.bigController.getC().SQLQueryAllSeances(this.session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adminSession= new View.AdminSession(this,this.bigController.getFrame());  //
        this.bigController.getFrame().getContentPane().add(adminSession);     //Frame de la page admin
    }

    public AdminSession(BigController bigController, Session s, Film f, boolean modifier) {
        setModifier(modifier);
        this.bigController=bigController; //connexion Query sql
        this.session=s;
        this.film= f;
        try {
            this.bigController.getC().SQLSessionsDispo(this.film.getFilmName(),session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adminSession= new View.AdminSession(this,this.bigController.getFrame());  //
        this.bigController.getFrame().getContentPane().add(adminSession);     //Frame de la page admin
    }

    public void delete(String date){
        try {
            this.bigController.getC().SQLQueryDeleteSeance(date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        menu();
        setVisible(false);
        getAdminMenu().setVisible(true);
    }

    public void m(String film){
        getFilm().setFilmName(film);
        setVisible(false);
        new AdminSession(this.bigController, this.session, this.film, true);
    }
    public void modifier(String new_date, String old_date){
        try {
            this.bigController.getC().SQLQueryUpdateSeance(new_date,old_date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        menu();
        setVisible(false);
        getAdminMenu().setVisible(true);
    }

    public void ajouter(String name, String room, String date){
        int r= Integer.parseInt(room);
        try {
            this.bigController.getC().SQLQueryAddSeance(name,r,date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        menu();
        setVisible(false);
        getAdminMenu().setVisible(true);
    }

    public void setVisible(boolean visible){
        adminSession.setVisible(visible);
    } //affichage
    public void menu(){
        setAdminMenu(new AdminMenu(this.bigController));
    }

    public AdminMenu getAdminMenu() {
        return adminMenu;
    }

    public void setAdminMenu(AdminMenu adminMenu) {
        this.adminMenu = adminMenu;
    }

    public Session getSession() {
        return session;
    }

    public Model.Film getFilm() {
        return film;
    }

    public boolean isModifier() {
        return modifier;
    }

    public void setModifier(boolean modifier) {
        this.modifier = modifier;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
