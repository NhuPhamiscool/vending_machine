package A2_G03;

public class Owner extends User {

    String name;
    String username;

    public Owner(String name, String username, boolean isAdmin) {
        super(name, username);
        // checkOwner(username);     // double check if this user has owner access rights
        // this.name = name;
        // this.username = username;
        isAdmin = true;
    }

    void addOwner() {}
    void removeOwner() {}

    // checkCashier();
    void addCahier() {}
    // create a new object of the type (from any user to cashier object)
    void removeCashier() {}

    // checkSeller();
    void addSeller() {}
    void removeSeller() {}


     // list of usernames in the vending machine with the associated role (customer, seller, cashier, or admin)
    void getUsernames() {
        // create separate username classes for owner/customer/seller/cashier

    }

    void viewCancelledTransactions() {
        // date & time
        // user (username or anonymous)
        // reason ("timeout", "user cancelled", "change not available", etc.)
    }

}