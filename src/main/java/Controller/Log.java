package Controller;

public class Log {
    //attributs
    //
    private BigController bigController;
    private Guest guest;
    private Film film;
    private View.Log log;
    private boolean mp_oublie; // Le bool ici permet l'utilisation d'un deuxième constructeur ou l'utilisateur a oublié son mdp
    private Model.Guest g;

    /**
     * Constructeur 1
     * @param co
     */
    public Log(BigController co){
        this.setMp(false); //le setMp est false car l'utilisateur n'a pas besoin de modifier son mot de passe
        g= new Model.Guest();
        this.bigController=co;
        log= new View.Log(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(log);
    }

    /**
     * Constructeur 2 - Mot de passe oublié -> affichage frame mp oublié pour guestù
     * @param co
     * @param mp
     */
    public Log(BigController co, boolean mp){
        this.setMp(mp); //ici mp est true, car l'utilisateur à envoyé l'information qu'il a oublé son mot de passe
        g= new Model.Guest();
        this.bigController=co;
        log= new View.Log(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(log);
    }

    /**
     * Verif si les infos appartiennent à un attribut de la classe Guest sur la base de donnée
     * Logiquement les informations sont envoyés à la classe Connect ou se trouvent toutes les QUERY
     * @param email
     * @param psw
     */
    public void login(String email, String psw) {
        g.setMail(email); //le setMail est rempli par un string inscrit dans la partie affichage du log
        g.setPsw(psw); //le setPsw est rempli par un string inscrit dans la partie affichage du log
        //informations importés depuis l'attribut du Model Mail pour être envoyés à la QUERY
        this.bigController.getC().SQLQueryGuest(g.getMail(), g.getPsw());
        //Si l'utilisateur de la base de donnée composé de l'email et du mot de passe est trouvé dans la
        //base de donnée, le if ci-dessous est true.
        if (this.bigController.getC().SQLQueryGuest(g.getMail(), g.getPsw())) {
            //Les méthodes ici instancie l'affichage de la partie Film
            log.mes1();
            film();
            getFilm().setVisible(true); //La partie Film est maintenant affichée
            setVisible(false); //La partie connexion n'est plus affichée
            //si l'utilisateur n'existe pas dans la base de donnée,
        } else {
            log.mes2();
            setVisible(true);
        }
    }

    //Méthodes
    //Appel de la méthode lorsque l'utilisateur intéragie avec l'affichage et le bouton Mot de passe oublié
    public void oublie(){
        setVisible(false);
        new Log(this.bigController, true);
    }

    /**
     * Query mot de passe oublié : appel query Update si email, name et mdp correspondent à un attribut sinon pas d'update
     * @param name
     * @param email
     * @param psw
     * @param tel
     */
    public void mp_oub(String name, String email, String psw, String tel){
        g.setMail(email);
        g.setPsw(psw);
        g.setName(name);
        g.setTel(tel);
        this.bigController.getC().SQLQueryGuestNewMP(g.getName(), g.getMail(), g.getPsw(),g.getTel());

    }
    //Appel des classes lors de l'interaction avec l'affichage de la classe Log
    public void guest() {
        setGuest(new Guest(this.bigController));
    }

    public void film(){setFilm(new Film(this.bigController,g));}

    //getters & setter
    //
    public void setVisible(boolean visible){
        log.setVisible(visible);
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

    public boolean getMp() {
        return mp_oublie;
    }

    public void setMp(boolean mp_oublie) {
        this.mp_oublie = mp_oublie;
    }

    public Model.Guest getG() {
        return g;
    }

    public void setG(Model.Guest g) {
        this.g = g;
    }
}
