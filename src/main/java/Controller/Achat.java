package Controller;

import Model.Film;
import Model.Guest;
import Model.*;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Achat {
    //attributs
    private BigController bigController;
    private View.Achat achat;
    private Menu menu;
    private Model.Film film;
    private Session session;
    private Ticket ticket;
    private Guest guest;
    private boolean aBoolean;
    private String path;
    private QRCode qrCode;

    /***
     * constructeur 1 - si l'utilisateur est connecté, ayant en paramètres Les attributs de l'objet de guest (get en bdd),
     * de ticket, session et film (en fonction de ce que l'utilisateur a implémenté lors de son utilisation
     * Ici on demande les informations sur la carte de credit, ect.
     * @param big
     * @param ticket
     * @param guest
     * @param session
     * @param film
     */
    public Achat(BigController big, Ticket ticket, Guest guest, Session session, Film film){
        aBoolean=false; //le bool est faux car l'utilisateur est connecté
        this.film=film; //on récupère les infos de chaques paramètres
        this.guest=guest; //
        this.session=session; //
        this.ticket=ticket; //
        this.qrCode=new QRCode(); //Génération d'un QRCode
        this.bigController=big;
        setPath("C:/JAVA/Java ECE/testqrcode/Quote.png");
        achat= new View.Achat(this,this.bigController.getFrame()); //Affichage de la frame Achat dans le package view
        this.bigController.getFrame().getContentPane().add(achat);
    }

    /**
     * constructeur 2 - si l'utilisateur est en anonyme on appel ce constructeur à partir de la methode ticket
     * @param big
     * @param ticket
     * @param guest
     * @param session
     * @param film
     * @param anonyme
     * @param path
     */
    public Achat(BigController big, Ticket ticket, Guest guest, Session session, Film film, boolean anonyme, String path){
        aBoolean=anonyme; //l'utilisateur est anonyme, anonyme est true
        this.film=film;
        this.guest=guest;
        this.session=session;
        this.ticket=ticket;
        setPath(path);
        this.bigController=big;
        achat= new View.Achat(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(achat);
    }

    //méthodes

    /**
     *  La méthode ici permet l'envoi d'un mail à l'utilisateur ou la generation d'un QR code avec les informations de l'achat
     */
    public void ticket(){
        if(this.guest.getMail()!=null){ //Le guest mail est nul -> on ne peut pas appliquer la query car ça implique que l'utilisateur est anonyme
            this.bigController.getC().SQLFormationTicket(film,session,ticket,guest);
            achat.mes1();
        //Le message ci-dessous sera envoyé par mail
        String mes="Bonjour,\nVoici votre ticket: \nMail achat: "+guest.getMail()+"\nFilm: "+film.getFilmName()+"\nSeance du: "+session.getDate()+"\nNombre de places: "+ticket.getNbrPlace()+"\nPrix: "+ticket.getPrice();
        //les paramètres ici sont l'email d'envoi, le mot de passe du mail, le mail du l'user, l'objet et le message.
        Mail.send("cinemaleshalles5@gmail.com","CinemaH1!",guest.getMail(),"Ticket Cinema",mes);
        }
        else { //si l'user est anonyme
            setaBoolean(true);
            setPath("C:/JAVA/Java ECE/testqrcode/"+ticket.getDateAchat()+".png");
            //QRCODE generator
            String str = "Film: "+film.getFilmName()+"\nSeance du: "+session.getDate()+"\nNombre de places: "+ticket.getNbrPlace()+"\nPrice: "+ticket.getPrice();
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            //génère un code QR avec une capacité de correction d'erreur de bas niveau (L)
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            try {
                this.qrCode.generateQRcode(str, getPath(), charset, hashMap, 200, 200);
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Achat(this.bigController,ticket,guest,session,film,isaBoolean(),getPath());
            achat.mes3();
        }
        System.exit(0);
    }
    public void menu(){
        setMenu(new Controller.Menu(this.bigController));
    }

    /**
     * visibilité de la page d'achat
     * @param visible
     */
    public void setVisible(boolean visible){
        achat.setVisible(visible);
    }



    // getters & setters
    public Menu getMenu() {
        return menu;
    }
    public boolean isaBoolean() {
        return aBoolean;
    }

    public Film getFilm() {
        return film;
    }

    public Session getSession() {
        return session;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getPath() {
        return path;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
