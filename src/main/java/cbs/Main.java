package cbs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Main extends Application {

    static Parent root;
    static Stage primaryStage;
    static Main m = null;
    static Boolean employeeMode;
    static User currentUser;
    static String selectedFilmTitle = "", selectedDate = "", selectedTime = "";
    static ArrayList<String> selectedSeats;
    static HashSet<Employee> employees = new HashSet<Employee>();
    static HashSet<Customer> customers = new HashSet<Customer>();

    public static void main(String[] args) throws Exception {

        m = new Main();

        retrieveEmployeeData();

        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }

        launch(args);

    }

    static void retrieveEmployeeData() throws Exception {
        ResultSet rs = databaseHandler.retrieveData("Select * from User");
        while (rs.next()) {
            if (rs.getBoolean(6)) {
                Main.employees.add(new Employee(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            } else {
                Main.customers.add(new Customer(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }
    }

    static void setCurrentUser(User u){
        Main.currentUser = u;

    }

    static void setEmployeeMode(boolean employeeMode){
        Main.employeeMode = employeeMode;
        System.out.println("Employee mode: "+Main.employeeMode);
    }

    static void setRoot(Parent root){
        Main.root = root;
    }

    static Parent getRoot(){
        return root;
    }

    static Stage getStage(){
        return primaryStage;
    }

    static HashSet<Employee> getEmployeeList() {
        return employees;
    }

    static HashSet<Customer> getCustomerList() {
        return customers;
    }

    static void resetEmployeeList(){
        employees.clear();
    }

    static void resetCustomerList(){
        customers.clear();
    }

    static Main getMainApplication(){

        return m;
    }

    static boolean getEmployeeMode(){
        return employeeMode;
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        try{
            root=FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            Main.primaryStage = primaryStage;
            Scene scene = new Scene(root, 900, 450);
            primaryStage.setTitle("Cinema Booking System");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

}