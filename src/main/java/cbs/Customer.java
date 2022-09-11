package cbs;

public class Customer extends User {

    public Customer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public String getType() {
        return "customer";
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
