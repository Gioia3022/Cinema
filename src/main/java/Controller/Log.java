package Controller;

import java.sql.SQLException;

public class Log {
    //attributs
    //

    private BigController bigController;
    private Guest guest;
    private Film film;
    private View.Log log;
    private boolean mp_oublie;
    private Model.Guest g;

    //Constructeur 1
    public Log(BigController co){
        this.setMp(false);
        g= new Model.Guest();
        this.bigController=co;
        log= new View.Log(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(log);
    }
    //Constructeur 2 - Mot de passe oublié -> affichage frame mp oublié pour guest
    public Log(BigController co, boolean mp){
        this.setMp(mp);
        g= new Model.Guest();
        this.bigController=co;
        log= new View.Log(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(log);
    }

    //Verif si les infos sont propres à un attribut de la classe Guest (QUERY)
    public void login(String email, String psw) {
        g.setMail(email);
        g.setPsw(psw);
        try {
            this.bigController.getC().SQLQueryGuest(g.getMail(), g.getPsw());
            if (this.bigController.getC().SQLQueryGuest(g.getMail(), g.getPsw())) {
                log.mes1();
                film();
                getFilm().setVisible(true);
                setVisible(false);
            } else {
                log.mes2();
                setVisible(true);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    //appel constructeur 2 pour le mot de passe oublié
    public void oublie(){
        setVisible(false);
        new Log(this.bigController, true);
    }

    //Query mot de passe oublié : appel query Update si email, name et mdp correspondent à un attribut sinon pas d'update
    public void mp_oub(String name, String email, String psw, String tel){
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

    public void film(){setFilm(new Film(this.bigController,g));}

    //getters & setter
    //

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

    public Model.Guest getG() {
        return g;
    }

    public void setG(Model.Guest g) {
        this.g = g;
    }
}
