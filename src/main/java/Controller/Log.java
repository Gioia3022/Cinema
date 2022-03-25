package Controller;

import java.sql.SQLException;

public class Log {

    private BigController bigController;
    private Guest guest;
    private View.Log log;
    Cinema.Guest a;
    View.Log a1;

    public Log(BigController co){
        a= new Cinema.Guest();
        this.bigController=co;
        a1= new View.Log(this,this.bigController.getFrame());
    }

    public void login(String email, String psw) {
        a.setMail(email);
        a.setPsw(psw);
        System.out.println(a.getMail() + a.getPsw());
        try {
            this.bigController.getC().SQLQueryAdmin(a.getMail(), a.getPsw());
            if (this.bigController.getC().SQLQueryAdmin(a.getMail(), a.getPsw()) == true) {
                a1.mes1();
                System.out.println("Vous etes con!");
                System.exit(0);
            } else {
                a1.mes2();
                System.out.println("pas bon retray");
                login( email, psw);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void guest() {
        this.bigController.getFrame().getContentPane().add(new View.Guest(guest,this.bigController.getFrame()));
    }
    public void setVisible(boolean visible){
        guest.setVisible(visible);
    }
    public void exit(){
        System.exit(0);
    }
}
