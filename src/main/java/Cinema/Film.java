package Cinema;

import java.util.ArrayList;
import java.util.Date;

public class Film {
    String filmName, filmGenre, director;
    Date filmRelease;
    int duration;
    String image;
    ArrayList<Film> film;
    ArrayList<String> names;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getFilmRelease() {
        return filmRelease;
    }

    public void setFilmRelease(Date filmRelease) {
        this.filmRelease = filmRelease;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<Film> getFilm() {
        return film;
    }

    public void setFilm(ArrayList<Film> film) {
        this.film = film;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }
}