package Controller;

public class Guest {

    //attributs
    //
    private BigController bigController; //Attribut appelant la classe big controler et donc frame et connexion à la bdd
    private Menu menu;
    private Film film;
    private View.Guest guest;
    private Controller.Log log;
    private Controller.Register register;
    private Model.Guest g;

    //Constructeur
    //Connexion à la bdd par appel du controler selon le modèle MVC
    public Guest(BigController bigController) {
        g= new Model.Guest();
        this.bigController=bigController;
        guest= new View.Guest(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(guest); //Creation de l'affichage de la classe admin

    }

    //méthodes
    //Dans la partie view, cliquer sur le bouton Register/Menu/Log/Anonyme appelle cette fonction qui permet
    //la création de la frame Guest/Admin et la connection de ses attribut à la bdd
    public void register(){
        setRegister(new Register(this.bigController));
    }
    public void menu() {
        setMenu(new Menu(this.bigController));
    }
    public void log(){
        setLog(new Controller.Log(this.bigController));
    }
    //Cette méthode permet de se connecter et d'accéder à la partie selection du film sans être connecté
    //Ainsi il pourra acheter des tickets et recevra un QRCode implémenté dans la partie programmation
    //La base de donnée sera modifiée même si l'utilisateur n'est pas connecté ainsi
    public void anonyme(){
        g.setName("Anonyme");
        setFilm(new Film(this.bigController,g));
    }

    //getter & setters attribut de chaque classes qui peuvent être appelées par la classe Guest
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
