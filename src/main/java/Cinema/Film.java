package Cinema;
import java.util.*;

public class Film {
    String FilmName, FilmGenre, Director;
    Date FilmRelease;
    int FilmLenght;

    public String getFilmName() {
        return FilmName;
    }

    public void setFilmName(String filmName) {
        this.FilmName = filmName;
    }

    public String getFilmGenre() {
        return FilmGenre;
    }

    public void setFilmGenre(String filmGenre) {
        FilmGenre = filmGenre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public Date getFilmRelease() {
        return FilmRelease;
    }

    public void setFilmRelease(Date filmRelease) {
        FilmRelease = filmRelease;
    }

    public int getFilmLenght() {
        return FilmLenght;
    }

    public void setFilmLenght(int filmLenght) {
        FilmLenght = filmLenght;
    }
}