package Controller;

import javax.swing.*;

public class Menu {
    private Controller.BigController bigController;
    private Controller.Guest guest_controller;
    private Admin admin_controller;
    private View.Menu menu;

    public Menu(BigController bigController) {
        this.bigController=bigController;
        this.menu= new View.Menu(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(this.menu);
        this.bigController.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.bigController.getFrame().setVisible(true);

    }
    public void guest() {
        setGuest_controller(new Guest(this.bigController));
    }
    public void admin() {
        setAdmin_controller(new Admin(this.bigController));
    }
    public void setVisible(boolean visible){
        menu.setVisible(visible);
    }

    public void exit(){
        System.exit(0);
    }

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
