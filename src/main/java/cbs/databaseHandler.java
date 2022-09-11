package cbs;

import java.sql.*;

public class databaseHandler {

    public static ResultSet retrieveData(String query) throws Exception{
        Connection con = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/cbs/CBS.accdb");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }

    public static void addUserRecord(String firstName, String lastName, String email, String password) throws Exception{
        Connection con = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/cbs/CBS.accdb");
        String update = "INSERT INTO User([firstName], [lastName], [email], [password]) VALUES (?,?,?,?)";
        PreparedStatement st = con.prepareStatement(update);
        st.setString(1,firstName);
        st.setString(2,lastName);
        st.setString(3,email);
        st.setString(4,password);
        st.executeUpdate();
    }


}
