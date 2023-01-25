package cbs;

import java.sql.*;
import java.time.LocalDate;

public class databaseHandler {

    public static ResultSet queryData(String query) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/cbs/CBS.accdb");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }

    public static void addUserRecord(String firstName, String lastName, String email, String password) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/cbs/CBS.accdb");
        String update = "INSERT INTO tblUsers([firstName], [lastName], [email], [password]) VALUES (?,?,?,?)";
        PreparedStatement st = con.prepareStatement(update);
        st.setString(1, firstName);
        st.setString(2, lastName);
        st.setString(3, email);
        st.setString(4, password);
        st.executeUpdate();
    }

    public static void addFilmRecord(String filmName, LocalDate startDate, LocalDate endDate, String screening1, String screening2, String screening3, String filmTrailer, String filmDescription, String filmPoster) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/cbs/CBS.accdb");
        String update = "INSERT INTO tblFilms([filmName], [startDate], [endDate], [screening1], [screening2], [screening3], [filmTrailer], [filmDescription], [filmPoster])";
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

    }

}
