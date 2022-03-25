package Controller;

import java.sql.SQLException;

public class Register {
    private BigController bigController;
    private Guest guest;
    private View.Register register;
    Cinema.Guest a= new Cinema.Guest();
    View.Register register_view;

    public Register(BigController co){
        a= new Cinema.Guest();
        this.bigController=co;
        register_view= new View.Register(this,this.bigController.getFrame());
    }
    public void register1(String name, String mail, String psw){
        a.setName(name);
        a.setMail(mail);
        a.setPsw(psw);

        System.out.println(a.getName() + a.getMail() + a.getPsw());

        try {
            this.bigController.getC().SQLQueryNewGuest(a.getName(),a.getMail(),a.getPsw());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void guest() {
        this.bigController.getFrame().getContentPane().add(new View.Guest(guest,this.bigController.getFrame()));
    }
    public void setVisible(boolean visible){
        register.setVisible(visible);
    }
    public void exit(){
        System.exit(0);
    }
}
