package Controller;

import java.sql.SQLException;

public class Register {
    private BigController bigController;
    private Guest guest;
    private View.Register register;
    private Cinema.Guest g;

    public Register(BigController co){
        g= new Cinema.Guest();
        this.bigController=co;
        register= new View.Register(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(register);
    }
    public void register1(String name, String mail, String psw, int number, int age,String benef){
        g.setName(name);
        g.setMail(mail);
        g.setPsw(psw);
        g.setTel(number);
        g.setAge(age);
        g.setBenef(benef);

        System.out.println(g.getName() + g.getMail() + g.getPsw());

        try {
            this.bigController.getC().SQLQueryNewGuest(g.getName(),g.getMail(),g.getPsw(), g.getTel(), g.getAge(),g.getBenef());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void guest() {
        setGuest(new Guest(this.bigController));
    }
    public void setVisible(boolean visible){
        register.setVisible(visible);
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
