package Controller;

public class Admin {
    private BigController bigController; //instancie la connection à la base et l'affichage de la Frame
    private Menu menu; //Objet Controller de menu
    private View.Admin admin; //Objet admin partie affichage
    private boolean mp_oublie;
    private Model.Admin a; //lien modèle admin
    private AdminMenu adminMenu;


    /**
     * constructeur
     * @param co
     */
    public Admin(BigController co){ // Constructeur 1
        this.setMp_oublie(false);
        this.bigController=co; //connexion Query sql
        a= new Model.Admin();
        admin= new View.Admin(this,this.bigController.getFrame());  //
        this.bigController.getFrame().getContentPane().add(admin);     //Frame de la page admin
    }
    public Admin(BigController co, boolean mp){ // Constructeur 2 dans le cas où mot de passe oublié
        this.setMp_oublie(mp); //Affichage mot de passe oublié
        this.bigController=co;
        a= new Model.Admin();
        admin= new View.Admin(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(admin);
    }

    //méthodes

    /**
     * verification d'acces des admin
     * LE SQLQueryAdmin return un boolean qui est true si l'admin existe (voir connect)
     * @param email
     * @param psw
     */
    public void login(String email, String psw) { //
        a.setMail(email);
        a.setPassword(psw);
        this.bigController.getC().SQLQueryAdmin(a.getMail(), a.getPassword()); //get Connexion, query mail et pswrd
        if (this.bigController.getC().SQLQueryAdmin(a.getMail(), a.getPassword())) { //si true
            admin.mes1();
            AM();
            getAdminMenu().setVisible(true);
            setVisible(false);
        } else { //si false
            admin.mes2();
            setVisible(true);
        }
    }


    public void oublie(){ //si l'admin oublie son mdp -> comment on gère l'affichage
        setVisible(false);
        new Admin(this.bigController, true); //constructeur 2 appelé avec mp true
    }

    /**
     * méthode mot de passe oublié
     * Query permettant l'update
     * @param name
     * @param email
     * @param psw
     */
    public void mp_oublie(String name, String email, String psw){
        a.setMail(email);
        a.setPassword(psw);
        a.setName(name);
        this.bigController.getC().SQLQueryAdminNewMP(a.getName(), a.getMail(), a.getPassword());

    }

    public void menu() {
        setMenu(new Menu(this.bigController));
    } //retour affichage menu (back)
    public void AM(){setAdminMenu(new AdminMenu(this.bigController));}

    //getter setters attribut de chaque classes qui peuvent être appelées par la classe Admin
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
    public AdminMenu getAdminMenu() {
        return adminMenu;
    }
    public void setAdminMenu(AdminMenu adminMenu) {
        this.adminMenu = adminMenu;
    }
}
