package cbs;

public class Employee extends User {

    public Employee(String firstName, String lastName, String email, String password, boolean isEmployee, int id) {
        super(firstName, lastName, email, password, isEmployee, id);
    }

    public boolean getIsEmployee() {
        return isEmployee;
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
