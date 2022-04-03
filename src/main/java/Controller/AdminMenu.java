package Controller;

public class AdminMenu {
    private BigController bigController; //instancie la connection à la base et l'affichage de la Frame
    private Menu menu; //Objet menu
    private View.AdminMenu adminMenu; //Objet admin partie affichage
    private AdminSession adminSession;
    private AdminFilm adminFilm;
    private AdminBenefice adminBenefice;

    /**
     * Constructeur
     * @param co
     */
    public AdminMenu(BigController co){ // Constructeur 1
        this.bigController=co; //connexion Query sql
        adminMenu= new View.AdminMenu(this,this.bigController.getFrame());  //
        this.bigController.getFrame().getContentPane().add(adminMenu);     //Frame de la page admin
    }

    public void menu() {
        setMenu(new Menu(this.bigController));
    } //retour affichage menu (back)
    public void film(){setAdminFilm(new AdminFilm(this.bigController));}
    public void seance(){setAdminSession(new AdminSession(this.bigController));}
    public void benef(){setAdminBenefice(new AdminBenefice(this.bigController));}
    //getter setters attribut
    public void setVisible(boolean visible){
        adminMenu.setVisible(visible);
    } //affichage menu
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public AdminSession getAdminSession() {
        return adminSession;
    }

    public AdminFilm getAdminFilm() {
        return adminFilm;
    }

    public AdminBenefice getAdminBenefice() {
        return adminBenefice;
    }

    public void setAdminSession(AdminSession adminSession) {
        this.adminSession = adminSession;
    }
    public void setAdminFilm(AdminFilm adminFilm) {
        this.adminFilm = adminFilm;
    }
    public void setAdminBenefice(AdminBenefice adminBenefice){this.adminBenefice=adminBenefice;}

}
