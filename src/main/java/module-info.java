module com.example.cinemabookingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens cbs to javafx.fxml;
    exports cbs;
}