package Controller;

import java.sql.SQLException;

public class Admin {
    private BigController bigController; //instancie la connection à la base et l'affichage de la Frame
    private Menu menu; //Objet menu
    private View.Admin admin; //Objet admin partie affichage
    private boolean mp_oublie;
    private Cinema.Admin a; //lien modèle admin

    //constructeur
    public Admin(BigController co){ // Constructeur 1
        this.setMp_oublie(false);
        this.bigController=co; //connexion Query sql
        a= new Cinema.Admin();
        admin= new View.Admin(this,this.bigController.getFrame());  //
        this.bigController.getFrame().getContentPane().add(admin);     //Frame de la page admin
    }
    public Admin(BigController co, boolean mp){ // Constructeur 2 dans le cas où mot de passe oublié
        this.setMp_oublie(mp); //Affichage mot de passe oublié
        this.bigController=co;
        a= new Cinema.Admin();
        admin= new View.Admin(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(admin);
    }

    //méthodes
    public void login(String email, String psw) { //
        a.setMail(email);
        a.setPassword(psw);
        System.out.println(a.getMail() + a.getPassword());
//LE SQLQueryAdmin est un boolean qui est true sur la query est respecté (voir connect)
        try {
            this.bigController.getC().SQLQueryAdmin(a.getMail(), a.getPassword()); //get Connexion, query mail et pswrd
            if (this.bigController.getC().SQLQueryAdmin(a.getMail(), a.getPassword())) { //si true
                admin.mes1();
                System.out.println("Vous etes con!");
                System.exit(0);
            } else { //si false
                admin.mes2();
                System.out.println("pas bon retry");
                setVisible(true);
            }
        } catch (
                SQLException e) { // si la connexion ne marche pas
            e.printStackTrace(); // affichage erreur connexion à la bdd
        }
    }

    public void oublie(){ //
        setVisible(false);
        new Admin(this.bigController, true); //constructeur 2 appelé avec mp true
    }

    public void mp_oublie(String name, String email, String psw){ //méthode mot de passe oublié
        System.out.println(name);
        a.setMail(email);
        a.setPassword(psw);
        a.setName(name);
            try { // Query permettant l'update
                this.bigController.getC().SQLQueryAdminNewMP(a.getName(), a.getMail(), a.getPassword());
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public void menu() {
        setMenu(new Menu(this.bigController));
    }
    public void setVisible(boolean visible){
        admin.setVisible(visible);
    }
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public boolean getMp_oublie() {
        return mp_oublie;
    }

    public void setMp_oublie(boolean mp_oublie) {
        this.mp_oublie = mp_oublie;
    }
}
