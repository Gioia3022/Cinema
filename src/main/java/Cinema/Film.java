package Cinema;

import java.util.*;

public class Film {
    String filmName, filmGenre, director;
    Date filmRelease;
    int duration;

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
}