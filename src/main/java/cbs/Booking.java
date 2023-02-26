package cbs;

import java.time.LocalDate;

//booking class that stores all necessary booking data

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

    public String getBookingSeat() {
        return bookingSeat;
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
