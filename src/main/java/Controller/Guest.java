package Controller;

public class Guest {

    //attributs
    //
    private BigController bigController;
    private Menu menu;
    private Film film;
    private View.Guest guest;
    private Controller.Log log;
    private Controller.Register register;
    private Cinema.Guest g;

    //Constructeur
    public Guest(BigController bigController) {
        g= new Cinema.Guest();
        this.bigController=bigController;
        guest= new View.Guest(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(guest);

    }

    //méthodes
    //

    //Créer classe register controler
    public void register(){
        setRegister(new Register(this.bigController));
    }
    public void menu() {
        setMenu(new Menu(this.bigController));
    }
    public void log(){
        setLog(new Controller.Log(this.bigController));
    }
    public void anonyme(){
        g.setName("Anonyme");
        setFilm(new Film(this.bigController,g));
    }

    public void setVisible(boolean visible){
        guest.setVisible(visible);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Controller.Log getLog() {
        return log;
    }

    public void setLog(Controller.Log log) {
        this.log = log;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
