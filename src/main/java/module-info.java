module com.example.cinemabookingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;


    opens cbs to javafx.fxml;
    exports cbs;
}