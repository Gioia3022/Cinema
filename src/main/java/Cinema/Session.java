package Cinema;
import java.util.*;
import java.sql.Time;

public class Session {
    Date SessionDate;
    Time FilmTime;

    //Getter & setters attributs de la table MySql Session
    public Date getSessionDate() {
        return SessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.SessionDate = sessionDate;
    }

    public Time getFilmTime() {
        return FilmTime;
    }

    public void setFilmTime(Time filmTime) {
        this.FilmTime = filmTime;
    }

}
