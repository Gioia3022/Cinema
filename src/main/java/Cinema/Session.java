package Cinema;

import java.util.ArrayList;

public class Session {
    ArrayList<String> dateArrayList;
    String date;

    //Getter & setters attributs de la table MySql Session
    public ArrayList<String> getDateArrayList() {
        return dateArrayList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDateArrayList(ArrayList<String> dateArrayList) {
        this.dateArrayList = dateArrayList;
    }
}
