package Controller;

import Model.Film;
import Model.Guest;
import Model.*;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Achat {

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

    public Achat(BigController big, Ticket ticket, Guest guest, Session session, Film film){
        aBoolean=false;
        this.film=film;
        this.guest=guest;
        this.session=session;
        this.ticket=ticket;
        this.qrCode=new QRCode();
        this.bigController=big;
        setPath("C:/JAVA/Java ECE/testqrcode/Quote.png");
        achat= new View.Achat(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(achat);
    }

    public Achat(BigController big, Ticket ticket, Guest guest, Session session, Film film, boolean anonyme, String path){
        aBoolean=anonyme;
        this.film=film;
        this.guest=guest;
        this.session=session;
        this.ticket=ticket;
        setPath(path);
        this.bigController=big;
        achat= new View.Achat(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(achat);
    }

    public void ticket(){
        if(this.guest.getMail()!=null){
        try {
            this.bigController.getC().SQLFormationTicket(film,session,ticket,guest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        achat.mes1();
        String mes="Bonjour,\nVoici votre ticket: \nMail achat: "+guest.getMail()+"\nFilm: "+film.getFilmName()+"\nSeance du: "+session.getDate()+"\nNombre de places: "+ticket.getNbrPlace()+"\nPrice: "+ticket.getPrice();
        Mail.send("cinemaleshalles5@gmail.com","CinemaH1!",guest.getMail(),"Ticket Cinema",mes);
        }
        else {
            setaBoolean(true);
            setPath("C:/JAVA/Java ECE/testqrcode/"+ticket.getDateAchat()+".png");
            /**QRCODE generator*/
            String str = "Film: "+film.getFilmName()+"\nSeance du: "+session.getDate()+"\nNombre de places: "+ticket.getNbrPlace()+"\nPrice: "+ticket.getPrice();
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            //generates QR code with Low level(L) error correction capability
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

    public void setVisible(boolean visible){
        achat.setVisible(visible);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
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

    public void setPath(String path) {
        this.path = path;
    }
}
