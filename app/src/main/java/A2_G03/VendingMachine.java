package A2_G03;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
// import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

public class VendingMachine {
    User currentUser;
    ArrayList<User> allUsers;
    User anonymous;
    // why hashmap?
    // HashMap<Product, Integer> productList; 
    ArrayList<Product> productList; 

    public VendingMachine() {
        this.allUsers = new ArrayList<>();
        this.currentUser = null;
        this.anonymous = new Customer(null, null);
        this.productList = new ArrayList<Product>();
    }

    public boolean checkDetails(String username, String password){
        for (User u: this.allUsers){
            if (username.equals(u.getUsername())){
                if (password.equals(u.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }

    public User getUser(String username){
        for (User u: this.allUsers){
            if (username.equals(u.getUsername())){
                return u;
            }
        }
        return null;
    }

    public boolean userExists(String username){
        for (User u: this.allUsers){
            if (username.equals(u.username)){
                return true;
            }
        }
        return false;
    }

    public void login(){
        Scanner scan = new Scanner(System.in);
        boolean success = false;
        String username = "";
        String password = "";
        // Console console = System.console();

        while (!success){
            System.out.println("Please enter your username: ");
            username = scan.next();
            System.out.println("Please enter your password: ");
            password = scan.next();
            // char[] passwordArray = console.readPassword("Enter your  password: ");
            // password = new String(passwordArray);
            // for (int i = 0; i < passwordArray.length; i++) {
            //     System.out.print("*");
                
            // }
            // System.out.println();
            if (this.checkDetails(username, password)){
                System.out.println("\nLogin successful.");
                this.currentUser = this.getUser(username);
                success = true;
            } else {
                System.out.println("Incorrect username or password. Please enter 1 to try again or 2 to quit.");
                String option = scan.next();
                while (!option.equals("1") && !option.equals("2")){
                    System.out.println("Invalid option, please try again.");
                    option = scan.next();
                }
            
                if (option.equals("2")){
                    return;
                } 
            }

        }
    }

    public void createAccount() throws Exception {
        String username = "";
        String password = "";
        // Console console = System.console();
        Scanner scan = new Scanner(System.in);
        boolean notUser = true;
        while (notUser){
            System.out.println("Please enter a username: ");
            username = scan.next();
            while(username.length() == 0 || username.equals("")){
                System.out.println("Please specify a valid username.");
            }
            boolean checkExists = this.userExists(username);
            if (checkExists){
                System.out.println("Username already exists. Please enter 1 to try again or 2 to quit.");
                String option = scan.next();
                
                while (!option.equals("1") && !option.equals("2")){
                    System.out.println("Invalid option, please try again.");
                    option = scan.next();
                }
                if (option.equals("2")){
                    return;
                } 
            } else {
                notUser = false;
            }
        }

        while (password.length() < 1){
            System.out.println("Please enter a password");
            password = scan.next();
        }

        User newCustomer = new Customer(username, password);
        this.allUsers.add(newCustomer);
        System.out.println("Account successfully created.");
        Database.addCustomer(username, password);
        this.currentUser = newCustomer;
    }
    //run method

    public void run() throws Exception {
        //need to determine what the currentuser is. run the login part here. Olivia's code??
        Scanner scan = new Scanner(System.in);
        if (currentUser instanceof Owner) {
            System.out.println("Welcome Owner");
            System.out.println("As an owner, here are your options for performing functionalities . \nA list of usernames in the vending machine\nA summary of cancelled transcations");
        }

        else if (currentUser instanceof Seller){
            ////
        }

        else if (currentUser instanceof User) {
            
            System.out.println("Welcome to the Vending Machine");
            System.out.println("Please select from the following options: \n[1] for login, [2] for create account, [3] to continue as guest.");
            String opt = scan.next();
            while (opt.isEmpty() || (!opt.equals("1") && !opt.equals("2") && !opt.equals("3"))){
                System.out.println("Option invalid, please try again. \nPlease enter [1] for login, [2] for create account, [3] to continue as guest");
                opt = scan.next();
            }
            if (opt.equals("1")){
                this.login();
            } else if (opt.equals("2")){
                this.createAccount();
            } else if (opt.equals("3")) {
                this.currentUser = anonymous;
            }
            HashMap<Product, Integer> selected = select();
            String paymentMethod = "";
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            double moneyPaid = 0.0;
            double returnChange = 0.0;
            scan(String.valueOf(date), String.valueOf(time), paymentMethod, selected, moneyPaid, returnChange);
        
        } else if (currentUser instanceof Cashier) {
            
        }


    }

    //to be confirmed
    // public void printreciept


    public Transaction scan(String date, String time, String paymentMethod, HashMap<Product, Integer> selectedProduct, 
    double moneyPaid, double returnChange) {
        Transaction transaction = new Transaction(date, time, paymentMethod, selectedProduct, moneyPaid, returnChange );
        //putting the selecteed products into a transcation, ie. "scanning it"
        // for (Product p : selectedProduct.keySet()){
        //     transaction.addToTransaction(p, selectedProduct.get(p));

        // }
        transaction.calculateTotal();
        return transaction;
    }

    public void cancelTransaction(Transaction transaction){
        transaction.isCancelled = true;
    }




    public HashMap<Product, Integer> select() {
        //boolean done
        boolean done = false;
        HashMap<Product, Integer> selectedProducts = new HashMap<>();
        String itemName;
        Product selectedProd = null;
        int quantityWanted;

        while (!done) {
            Scanner sc=new Scanner(System.in);
            System.out.println("Please view from the existing products");

            for (Product p: this.productList) {
                System.out.println("Name: "  + p.getName() + " " + "Item Code " +  p.getCode() + " " + "Quantity " +   p.getStock());
            }
            System.out.println("Please enter the item name you would like to purchase");
            itemName = sc.next();
            System.out.println("Please enter the quantity of " + itemName + " you'd like to purchase");

            quantityWanted = sc.nextInt();

            // Product selectedProd;
            for (Product p: this.productList) {
                if (p.getName().equals(itemName)) {
                    selectedProd = p;
                }
            }

            if (selectedProd == null) {
                System.out.println("Please enter the item name again. There is no such product called" + itemName);
                continue;

            }

            if (selectedProd.getStock() == 0){
                System.out.println("Sorry, we are out of stock for this item. Please try again another day, or try enter another product again. ");
            }

            else if (selectedProd.getStock() < quantityWanted){
                System.out.println("Sorry, we only have " + String.valueOf(selectedProd.getStock()) + " available. ");
            }


            // if (this.getProducts.get(itemName).getStock() ==0){
            //     System.out.println("Sorry, we are out of stock for this item. Please try again another day, or try enter another product again. ");
            // }

            // else if (this.getProducts.get(itemName).getStock() < quantity){
            //     System.out.println("Sorry, we only have " + this.getProducts.get(itemName).getStock().toString() + " available. ");
            // }


            else {
                selectedProducts.put(selectedProd, quantityWanted);
                done = true;
                // return selectedProducts;

            }
        }
        return selectedProducts;

    }


}

