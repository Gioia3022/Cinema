package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

public class Film {
    private BigController bigController;
    private Menu menu;
    private View.Film film_view;
    Cinema.Film film;
    ArrayList<Cinema.Film> filmArrayList;
    ArrayList<String> names;
    public Film(BigController co){
        this.bigController=co;
        this.film= new Cinema.Film();
        try {
            setFilmArrayList(this.bigController.getC().SQLQueryFilm(film));
            setNamesArrayList(this.bigController.getC().SQLQueryFilmName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        film_view= new View.Film(this,this.bigController.getFrame());
        this.bigController.getFrame().getContentPane().add(film_view);
    }

    public void setNamesArrayList(ArrayList<String> namesArrayList) {
        this.names = namesArrayList;
    }

    public void setFilmArrayList(ArrayList<Cinema.Film> filmArrayList) {
        this.filmArrayList = filmArrayList;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setVisible(boolean visible){
        film_view.setVisible(visible);
    }
}
