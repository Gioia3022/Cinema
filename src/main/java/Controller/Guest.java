package Controller;

import View.Log;

import java.sql.SQLException;

public class Guest {
    private BigController bigController;
    private Menu menu;
    private View.Guest guest;
    private Controller.Log log;
    private Controller.Register register;


    public Guest(BigController bigController) {
        this.bigController=bigController;
        guest= new View.Guest(this,this.bigController.getFrame());
    }

    public void register(){
        this.bigController.getFrame().getContentPane().add(new View.Register(register,this.bigController.getFrame()));
    }
    public void menu() {
        this.bigController.getFrame().getContentPane().add(new View.Menu(menu,this.bigController.getFrame()));
    }
    public void log(){
        this.bigController.getFrame().getContentPane().add(new Log(log,this.bigController.getFrame()));
    }
    public void setVisible(boolean visible){
        guest.setVisible(visible);
    }
}
