package Controller;

import java.sql.SQLException;

public class Admin {
    private BigController bigController;
    private Menu menu;
    private View.Admin admin;
    Cinema.Admin a;

    public Admin(BigController co){
        this.bigController=co;
        a= new Cinema.Admin();
        admin= new View.Admin(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(admin);
    }

    public void login(String email, String psw) {
        a.setMail(email);
        a.setPassword(psw);
        System.out.println(a.getMail() + a.getPassword());

        try {
            this.bigController.getC().SQLQueryAdmin(a.getMail(), a.getPassword());
            if (this.bigController.getC().SQLQueryAdmin(a.getMail(), a.getPassword())) {
                admin.mes1();
                System.out.println("Vous etes con!");
                System.exit(0);
            } else {
                admin.mes2();
                System.out.println("pas bon retray");
                login( email, psw);
            }
        } catch (
                SQLException e) {
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
}
