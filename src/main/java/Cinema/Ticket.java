package Cinema;
import java.util.*;

public class Ticket {
    int Price, NbrPlace;
    Date DateAchat;

    public Date getDateAchat() {
        return DateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.DateAchat = dateAchat;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.Price = price;
    }

    public int getNbrPlace() {
        return NbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.NbrPlace = nbrPlace;
    }
}

