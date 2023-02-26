package cbs;

import java.time.LocalDate;


//class that stores all necessary film data
public class Film {

    protected String filmTitle;
    protected LocalDate startDate, endDate;
    protected String screening1, screening2, screening3, filmTrailer, filmDescription, filmPoster;
    protected int filmIndex;

    public Film(String filmTitle, LocalDate startDate, LocalDate endDate, String screening1, String screening2, String screening3, String filmTrailer, String filmDescription, String filmPoster, int filmIndex) {
        this.filmTitle = filmTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.screening1 = screening1;
        this.screening2 = screening2;
        this.screening3 = screening3;
        this.filmTrailer = filmTrailer;
        this.filmDescription = filmDescription;
        this.filmPoster = filmPoster;
        this.filmIndex = filmIndex;

    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getFilmTrailer() {
        return filmTrailer;
    }

    public String getFilmPoster() {
        return filmPoster;
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
                ", filmIndex='" + filmIndex + '\'' +
                '}';
    }
}
