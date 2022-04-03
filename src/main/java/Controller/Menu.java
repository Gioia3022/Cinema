package Controller;

import javax.swing.*;

public class Menu {
    //attributs

    private Controller.BigController bigController;
    private Controller.Guest guest_controller; //Controller vers frame et c de la classe Guest
    private Admin admin_controller; //Contrôller vers la frame et c de la classe Admin
    private View.Menu menu; //Attribut de type View, donc affichage menu

    //Constructeur
    public Menu(BigController bigController) {
        this.bigController=bigController; //Création connexion à la bdd
        this.menu= new View.Menu(this,this.bigController.getFrame()); //
        this.bigController.getFrame().getContentPane().add(this.menu); //
         this.bigController.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
        this.bigController.getFrame().setVisible(true); //Affichage de la Frame menu

    }

    //Méthodes
    //Dans la partie view, cliquer sur le bouton Guest/Admin appelle cette fonction qui permet
    //la création de la frame Guest/Admin et la connection de ses attribut à la bdd
    public void guest() {
        setGuest_controller(new Guest(this.bigController));
    }
    public void admin() {
        setAdmin_controller(new Admin(this.bigController));
    }
    public void setVisible(boolean visible){
        menu.setVisible(visible);
    }

    //getters & setters
    public Guest getGuest_controller() {
        return guest_controller;
    }
    public void setGuest_controller(Guest guest_controller) {
        this.guest_controller = guest_controller;
    }
    public Admin getAdmin_controller() {
        return admin_controller;
    }
    public void setAdmin_controller(Admin admin_controller) {
        this.admin_controller = admin_controller;
    }
}
