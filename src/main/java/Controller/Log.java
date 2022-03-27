package Controller;

import java.sql.SQLException;

public class Log {

    private BigController bigController;
    private Guest guest;
    private Film film;
    private View.Log log;
    private boolean mp_oublie;
    private Cinema.Guest g;

    public Log(BigController co){
        this.setMp(false);
        g= new Cinema.Guest();
        this.bigController=co;
        log= new View.Log(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(log);
    }
    public Log(BigController co, boolean mp){
        this.setMp(mp);
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
            this.bigController.getC().SQLQueryGuest(g.getMail(), g.getPsw());
            if (this.bigController.getC().SQLQueryGuest(g.getMail(), g.getPsw())) {
                log.mes1();
            } else {
                log.mes2();
                System.out.println("pas bon retray");
                setVisible(true);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void oublie(){
        setVisible(false);
        new Log(this.bigController, true);
    }
    public void mp_oub(String name, String email, String psw, int tel){
        System.out.println(name);
        g.setMail(email);
        g.setPsw(psw);
        g.setName(name);
        g.setTel(tel);
        try {
            this.bigController.getC().SQLQueryGuestNewMP(g.getName(), g.getMail(), g.getPsw(),g.getTel());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void guest() {
        setGuest(new Guest(this.bigController));
    }

    public void film(){setFilm(new Film(this.bigController));}

    public void setVisible(boolean visible){
        log.setVisible(visible);
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film){ this.film=film;}

    public boolean getMp() {
        return mp_oublie;
    }

    public void setMp(boolean mp_oublie) {
        this.mp_oublie = mp_oublie;
    }
}
