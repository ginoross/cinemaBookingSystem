package cbs;

public class Employee extends User {

    public Employee(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public String getType() {
        return "employee";
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
