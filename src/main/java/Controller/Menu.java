package Controller;

import javax.swing.*;

public class Menu {
    private Controller.BigController bigController;
    private Controller.Guest guest_controller;
    private Admin admin_controller;
    private View.Menu menu;

    public Menu(BigController bigController) {
        this.bigController=bigController;
        this.bigController.getFrame().getContentPane().add(new View.Menu(this,this.bigController.getFrame()));
        this.bigController.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.bigController.getFrame().setVisible(true);
        menu= new View.Menu(this,this.bigController.getFrame());
    }
    public void guest() {
        this.bigController.getFrame().getContentPane().add(new View.Guest(guest_controller, this.bigController.getFrame()));
    }
    public void admin() {
        this.bigController.getFrame().getContentPane().add(new View.Admin(admin_controller,this.bigController.getFrame()));
    }
    public void setVisible(boolean visible){
        menu.setVisible(visible);
    }
    public void exit(){
        System.exit(0);
    }
}
