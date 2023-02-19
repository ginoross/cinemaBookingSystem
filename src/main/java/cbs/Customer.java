package cbs;

public class Customer extends User {

    public Customer(String firstName, String lastName, String email, String password, boolean isEmployee, int ID) {
        super(firstName, lastName, email, password, isEmployee, ID);
    }

    public boolean getIsEmployee() {
        return isEmployee;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
