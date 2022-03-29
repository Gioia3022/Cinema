package Cinema;
import java.util.*;

public class Ticket {
    private int NbrPlace;
    private float Price;
    private Date DateAchat;

    //Getter & setters attributs de la table MySql Ticket
    public Date getDateAchat() {
        return DateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.DateAchat = dateAchat;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        this.Price = price;
    }

    public int getNbrPlace() {
        return NbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.NbrPlace = nbrPlace;
    }
}

