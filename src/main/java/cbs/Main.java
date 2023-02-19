package cbs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Objects;

public class Main extends Application {

    static Parent root;
    static Stage primaryStage;
    static Main m = null;
    static Boolean employeeMode;
    static User currentUser;
    static String selectedFilmTitle = "";

    static HashSet<Employee> employees = new HashSet<>();
    static HashSet<Customer> customers = new HashSet<>();
    static HashSet<Film> films = new HashSet<>();
    static HashSet<Booking> bookings = new HashSet<>();


    public static void main(String[] args) throws Exception {

        m = new Main();

        retrieveEmployeeData();
        retrieveFilmData();
        retrieveBookingData();

        launch(args);

    }

    static void retrieveEmployeeData() throws Exception {
        ResultSet rs = DatabaseHandler.queryData("Select * from tblUsers");
        while (rs.next()) {
            if (rs.getBoolean(6)) {
                Main.employees.add(new Employee(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), true,  rs.getInt(1)));
            } else {
                Main.customers.add(new Customer(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), false,  rs.getInt(1)));
            }
        }
    }

    static void retrieveFilmData() throws Exception {
        ResultSet rs = DatabaseHandler.queryData("Select * from tblFilms");
        while (rs.next()) {
            Main.films.add(new Film(rs.getString(2), (rs.getDate(3)).toLocalDate(), (rs.getDate(4)).toLocalDate(), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(1)));
        }
    }

    static void retrieveBookingData() throws Exception {
        ResultSet rs = DatabaseHandler.queryData("Select * from tblBookings");
        while (rs.next()) {
            System.out.println(rs.getString(5) + rs.getString(6) + rs.getString(7));
            Main.bookings.add(new Booking(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getString(7), rs.getString(5), rs.getBoolean(6)));
        }
    }

    static void setCurrentUser(User u) {
        Main.currentUser = u;

    }

    static void setEmployeeMode(boolean employeeMode) {
        Main.employeeMode = employeeMode;
        System.out.println("Employee mode: " + Main.employeeMode);
    }

    static void setSelectedFilmTitle(String selectedFilmTitle) {
        Main.selectedFilmTitle = selectedFilmTitle;
    }

    static void setRoot(Parent root) {
        Main.root = root;
    }

    static Parent getRoot() {
        return root;
    }

    static Stage getStage() {
        return primaryStage;
    }


    static HashSet<Employee> getEmployeeList() {
        return employees;
    }

    static HashSet<Customer> getCustomerList() {
        return customers;
    }

    static Film getSelectedFilm(String selectedFilmTitle) {
        for (Film film : films) {
            if (film.filmTitle.equals(selectedFilmTitle)) {
                return film;
            }
        }
        return null;
    }


    static boolean getEmployeeMode() {
        return employeeMode;
    }


    @Override
    public void start(Stage primaryStage) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginPage.fxml")));
            Main.primaryStage = primaryStage;
            Scene scene = new Scene(root, 900, 450);
            primaryStage.setTitle("Cinema Booking System");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}