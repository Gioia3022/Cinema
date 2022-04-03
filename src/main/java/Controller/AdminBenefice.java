package Controller;

import Model.Benefice;

import java.sql.SQLException;

public class AdminBenefice {

    private BigController bigController; //instancie la connexion à la base et l'affichage de la Frame
    private View.AdminBenefice adminBenefice; //Objet admin partie affichage
    private AdminMenu adminMenu;
    private Benefice benefice;
    private int choix;

    public AdminBenefice(BigController bigController) {
        this.bigController=bigController; //connexion Query sql
        this.benefice=new Benefice();
        try {
            this.bigController.getC().SQLQueryAllBenefice(this.benefice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adminBenefice= new View.AdminBenefice(this,this.bigController.getFrame());  //
        this.bigController.getFrame().getContentPane().add(adminBenefice);     //Frame de la page admin

    }
    //constructeur 2 - bénéfice choisi
    public AdminBenefice(BigController bigController, int choice) {
        this.setChoix(choice);
        this.bigController=bigController; //connexion Query sql
        this.benefice=new Benefice();
        try {
            this.bigController.getC().SQLQueryAllBenefice(this.benefice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adminBenefice= new View.AdminBenefice(this,this.bigController.getFrame());  //
        this.bigController.getFrame().getContentPane().add(adminBenefice);     //Frame de la page admin

    }

    //méthodes

    //création bénéfice
    public void a(){
        setVisible(false);
        new AdminBenefice(this.bigController,1);
    }

    //création bénéfice avec comme nom name
    public void m(String name){
        benefice.setName(name);
        setVisible(false);
        new AdminBenefice(this.bigController,2);
    }

    //delete bénefice
    public void delete(String name){
        try {
            this.bigController.getC().SQLQueryDeleteBenefice(name); //delete l'attribut avec son nom
        } catch (SQLException e) {
            e.printStackTrace();
        }
        menu();
        setVisible(false);
        getAdminMenu().setVisible(true);
    }

    //ajout benefice dans la base de donnée
    public void ajout(String name, String discount){
        float final_discount=1;
        final_discount=final_discount-(Float.valueOf(discount)/100);
        System.out.println(final_discount);
        try {
            //Query implémentant dans la base de donnée le nouveau bénéfice
            this.bigController.getC().SQLQueryAddBenefice(name,final_discount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        menu();
        setVisible(false);
        getAdminMenu().setVisible(true);
    }

    //modification d'un benefice choisi déjà dans la base de donnée
    public void modif(String new_remise){
        float f=Float.parseFloat(new_remise);
        float final_discount=1;
        final_discount=final_discount-(Float.parseFloat(new_remise)/100);
        try {
            this.bigController.getC().SQLQueryModifBenef(final_discount,benefice.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        menu();
        setVisible(false);
        getAdminMenu().setVisible(true);
    }

    //getters & setters
    public void setVisible(boolean visible) {
        adminBenefice.setVisible(visible);
    }
    public void menu(){
        setAdminMenu(new AdminMenu(this.bigController));
    }

    public AdminMenu getAdminMenu() {
        return adminMenu;
    }

    public void setAdminMenu(AdminMenu adminMenu) {
        this.adminMenu = adminMenu;
    }

    public Benefice getBenefice() {
        return benefice;
    }

    public void setBenefice(Benefice benefice) {
        this.benefice = benefice;
    }

    public int getChoix() {
        return choix;
    }

    public void setChoix(int choix) {
        this.choix = choix;
    }

}
