package cbs;

import java.time.LocalDate;
import java.util.Date;

public class Film {

    protected String filmTitle;
    protected LocalDate startDate, endDate;
    protected String screening1, screening2, screening3, filmTrailer, filmDescription, filmPoster;

    public Film(String filmTitle, LocalDate startDate, LocalDate endDate, String screening1, String screening2, String screening3, String filmTrailer, String filmDescription, String filmPoster) {
        this.filmTitle = filmTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.screening1 = screening1;
        this.screening2 = screening2;
        this.screening3 = screening3;
        this.filmTrailer = filmTrailer;
        this.filmDescription = filmDescription;
        this.filmPoster = filmPoster;

    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getScreening1() {
        return screening1;
    }

    public void setScreening1(String screening1) {
        this.screening1 = screening1;
    }

    public String getScreening2() {
        return screening2;
    }

    public void setScreening2(String screening2) {
        this.screening2 = screening2;
    }

    public String getScreening3() {
        return screening3;
    }

    public void setScreening3(String screening3) {
        this.screening3 = screening3;
    }

    public String getFilmTrailer() {
        return filmTrailer;
    }

    public void setFilmTrailer(String filmTrailer) {
        this.filmTrailer = filmTrailer;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    public String getFilmPoster() {
        return filmPoster;
    }

    public void setFilmPoster(String filmPoster) {
        this.filmPoster = filmPoster;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmTitle='" + filmTitle + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", screening1='" + screening1 + '\'' +
                ", screening2='" + screening2 + '\'' +
                ", screening3='" + screening3 + '\'' +
                ", filmTrailer='" + filmTrailer + '\'' +
                ", filmDescription='" + filmDescription + '\'' +
                ", filmPoster='" + filmPoster + '\'' +
                '}';
    }
}
