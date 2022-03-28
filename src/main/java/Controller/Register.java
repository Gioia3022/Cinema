package Controller;

import java.sql.SQLException;

public class Register {
    //attributs
    //

    private BigController bigController;
    private Guest guest;
    private Film film;
    private View.Register register;
    private Cinema.Guest g;

    //Constructeur
    public Register(BigController co){
        g= new Cinema.Guest();
        this.bigController=co;
        register= new View.Register(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(register);
    }

    //méthodes
    //

    //Envoie dans la query les informations nécessaire pour créer un attribut de guest
    public void register1(String name, String mail, String psw, int number, int age,String benef){
        g.setName(name);
        g.setMail(mail);
        g.setPsw(psw);
        g.setTel(number);
        g.setAge(age);
        g.setBenef(benef);

        try {
            if(!this.bigController.getC().SQLQueryCheckGuest(g.getMail())) {
                this.bigController.getC().SQLQueryNewGuest(g.getName(), g.getMail(), g.getPsw(), g.getTel(), g.getAge(), g.getBenef());
                register.mes1();
                film();
                getFilm().setVisible(true);
                setVisible(false);
            }
            else {
                setVisible(true);
                register.mes2();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void film(){setFilm(new Film(this.bigController));}

    public void guest() {
        setGuest(new Guest(this.bigController));
    }

    //getter & setters
    //

    public void setVisible(boolean visible){
        register.setVisible(visible);
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
}
