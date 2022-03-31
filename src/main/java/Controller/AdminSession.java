package Controller;

import Model.Session;

import java.sql.SQLException;

public class AdminSession {
    private BigController bigController; //instancie la connection à la base et l'affichage de la Frame
    private View.AdminSession adminSession; //Objet admin partie affichage
    private AdminMenu adminMenu;
    private Session session;
    public AdminSession(BigController bigController) {this.bigController=bigController; //connexion Query sql
        this.session=new Session();
        try {
            this.bigController.getC().SQLQueryAllSeances(this.session);
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
    }

    public void modifier(String date){

    }

    public void ajouter(String name, String room, String date){
        int r= Integer.parseInt(room);
        try {
            this.bigController.getC().SQLQueryAddSeance(name,r,date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void setSession(Session session) {
        this.session = session;
    }
}
