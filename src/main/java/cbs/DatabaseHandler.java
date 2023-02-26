package cbs;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseHandler {

    //class that handles all database operations

    private final static String DATABASE_URL = "jdbc:ucanaccess://src/main/resources/cbs/CBS.accdb";


//returns requested result set based on input sql
    public static ResultSet queryData(String query) throws Exception {
        Connection con = DriverManager.getConnection(DATABASE_URL);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        con.close();
        return rs;

    }

    //method to add a user record
    public static void addUserRecord(String firstName, String lastName, String email, String password) throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL);
        String update = "INSERT INTO tblUsers([firstName], [lastName], [email], [password]) VALUES (?,?,?,?)";
        PreparedStatement st = con.prepareStatement(update);
        st.setString(1, firstName);
        st.setString(2, lastName);
        st.setString(3, email);
        st.setString(4, password);
        st.executeUpdate();
        con.close();
    }

    //method to add a film record
    public static void addFilmRecord(String filmName, LocalDate startDate, LocalDate endDate, String screening1, String screening2, String screening3, String filmTrailer, String filmDescription, String filmPoster) throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL);
        String update = "INSERT INTO tblFilms([filmName], [startDate], [endDate], [screening1], [screening2], [screening3], [filmTrailer], [filmDescription], [filmPoster]) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement st = con.prepareStatement(update);
        st.setString(1, filmName);
        st.setString(2, startDate.toString());
        st.setString(3, endDate.toString());
        st.setString(4, screening1);
        st.setString(5, screening2);
        st.setString(6, screening3);
        st.setString(7, filmTrailer);
        st.setString(8, filmDescription);
        st.setString(9, filmPoster);
        st.executeUpdate();
        con.close();

    }

    //method to add a booking record
    public static void addBookingRecord(int filmID, int userID, LocalDate bookingDate, String bookingTime, String bookingSeat) throws SQLException{
        Connection con = DriverManager.getConnection(DATABASE_URL);
        String update = "INSERT INTO tblBookings([filmID],[userID],[bookingDate],[bookingTime],[bookingSeat]) VALUES (?,?,?,?,?)";
        PreparedStatement st = con.prepareStatement(update);
        st.setInt(1, filmID);
        st.setInt(2, userID);
        st.setString(3, bookingDate.toString());
        st.setString(4, bookingTime);
        st.setString(5, bookingSeat);
        st.executeUpdate();
        con.close();
    }

    //method that updates database to cancel a booking
    public static void cancelBooking(int bookingID) throws SQLException{
        Connection con = DriverManager.getConnection(DATABASE_URL);
        String update = "UPDATE tblBookings SET isCancelled=? WHERE bookingID = "+bookingID;
        PreparedStatement st = con.prepareStatement(update);
        st.setBoolean(1, true);
        st.executeUpdate();
        con.close();
        System.out.println("success");
    }

    //method that updates a database to make a user an employee
    public static void makeEmployee(int userID) throws SQLException{
        Connection con = DriverManager.getConnection(DATABASE_URL);
        String update = "UPDATE tblUsers SET isEmployee=? WHERE userID = "+userID;
        PreparedStatement st = con.prepareStatement(update);
        st.setBoolean(1, true);
        st.executeUpdate();
        con.close();
        System.out.println("success");
    }

    //method to delete a specified film record
    public static void deleteFilmRecord(int filmID) throws Exception {
        Connection con = DriverManager.getConnection(DATABASE_URL);
        String delete = "DELETE From tblFilms WHERE tblFilms.filmID = " + filmID;
        PreparedStatement st = con.prepareStatement(delete);
        st.executeUpdate();
        con.close();
    }

}
