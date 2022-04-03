package Controller;

import Model.Benefice;

import java.sql.SQLException;

public class Register {
    //attributs
    private BigController bigController;
    private Guest guest;
    private Film film;
    private View.Register register;
    private Model.Guest g;
    private Benefice benefice;

    //Constructeur
    public Register(BigController co){
        g= new Model.Guest(); //jointure à la base de données et ses attributs
        this.benefice=new Benefice();
        this.bigController=co;
        try { //Affichage des différents bénéfice auquel l'utilisateur pourra accéder depuis la base de donnée
            this.bigController.getC().SQLQueryAllBenefice(this.benefice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        register= new View.Register(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(register);
    }

    //méthodes
    //

    //Envoie dans la query les informations nécessaire pour créer un attribut de guest
    public void register1(String name, String mail, String psw, String number, int age, String benef){
        g.setName(name); //ces set sont implémentés dans l'affichage, ainsi les attributs name,mail...
        g.setMail(mail); //sont instanciés par l'utilisateur
        g.setPsw(psw);
        g.setTel(number);
        g.setAge(age);
        g.setBenef(benef);

        try {
            //la méthode SQLQueryCheckGuest vérifie si l'email n'est pas déjà utilisé dans un objet de la base de donnée
            if(!this.bigController.getC().SQLQueryCheckGuest(g.getMail())) {
                //Ici, le controller a vérifié que la connexion et la query s'est bien passée
                this.bigController.getC().SQLQueryNewGuest(g.getName(), g.getMail(), g.getPsw(), g.getTel(), g.getAge(), g.getBenef());
                register.mes1();
                //ainsi, on affiche la partie film, l'utilisateur est connecté avec les informations qu'il a noté
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


    public void film(){setFilm(new Film(this.bigController,g));}

    public void guest() {
        setGuest(new Guest(this.bigController));
    }

    //getter & setters
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

    public Benefice getBenefice() {
        return benefice;
    }

    public void setBenefice(Benefice benefice) {
        this.benefice = benefice;
    }
}
