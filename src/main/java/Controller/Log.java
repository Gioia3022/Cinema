package Controller;

import java.sql.SQLException;

public class Log {

    private BigController bigController;
    private Guest guest;
    private View.Log log;
    Cinema.Guest g;

    public Log(BigController co){
        g= new Cinema.Guest();
        this.bigController=co;
        log= new View.Log(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(log);
    }

    public void login(String email, String psw) {
        g.setMail(email);
        g.setPsw(psw);
        System.out.println(g.getMail() + g.getPsw());
        try {
            this.bigController.getC().SQLQueryAdmin(g.getMail(), g.getPsw());
            if (this.bigController.getC().SQLQueryAdmin(g.getMail(), g.getPsw()) == true) {
                log.mes1();
                System.out.println("Vous etes con!");
                System.exit(0);
            } else {
                log.mes2();
                System.out.println("pas bon retray");
                login( email, psw);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void guest() {
        setGuest(new Guest(this.bigController));
    }

    public void setVisible(boolean visible){
        guest.setVisible(visible);
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
