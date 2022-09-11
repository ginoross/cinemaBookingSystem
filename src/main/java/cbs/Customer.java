package cbs;

public class Customer extends User {

    private double accountBalance;

    public Customer(String firstName, String lastName,String email, String password, double accountBalance) {
        super(firstName, lastName,email, password);
        this.accountBalance = accountBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void addToAccountBalance(double addAmount) {
        if (addAmount > 0) {
            this.accountBalance += addAmount;
        } else {
            System.out.println("Invalid amount. You must add a positive amount.");
        }
    }

    public String getType() {
        return "customer";
    }

    @Override
    public String toString() {
        return "Customer{" +
                "accountBalance=" + accountBalance +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
