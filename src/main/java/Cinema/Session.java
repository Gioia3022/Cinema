package Cinema;

import java.time.LocalTime;
import java.util.Date;

public class Session {
    private Date SessionDate;
    LocalTime FilmTime;
    //ArrayList<Date>

    //Getter & setters attributs de la table MySql Session
    public Date getSessionDate() {
        return SessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.SessionDate = sessionDate;
    }

    public LocalTime getFilmTime() {
        return FilmTime;
    }

    public void setFilmTime(LocalTime filmTime) {
        this.FilmTime = filmTime;
    }

}
