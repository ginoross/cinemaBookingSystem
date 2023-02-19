package cbs;

import java.time.LocalDate;

public class Booking {

    protected int BookingID;
    protected int FilmID;
    protected int UserID;
    protected LocalDate bookingDate;
    protected String bookingTime, bookingSeat;
    protected boolean isCancelled;

    public Booking(int bookingID, int filmID, int userID, LocalDate bookingDate, String bookingTime, String bookingSeat, boolean isCancelled) {
        this.BookingID = bookingID;
        this.FilmID = filmID;
        this.UserID = userID;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.bookingSeat = bookingSeat;
        this.isCancelled = isCancelled;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int bookingID) {
        BookingID = bookingID;
    }

    public int getFilmID() {
        return FilmID;
    }

    public void setFilmID(int filmID) {
        FilmID = filmID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getBookingSeat() {
        return bookingSeat;
    }

    public void setBookingSeat(String bookingSeat) {
        this.bookingSeat = bookingSeat;
    }

    public boolean getIsCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "BookingID=" + BookingID +
                ", FilmID=" + FilmID +
                ", UserID=" + UserID +
                ", bookingDate=" + bookingDate +
                ", bookingTime='" + bookingTime + '\'' +
                ", bookingSeat='" + bookingSeat + '\'' +
                ", isCancelled=" + isCancelled +
                '}';
    }
}
