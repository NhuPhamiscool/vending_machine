package A2_G03;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
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
    Map<String, Integer> note = new LinkedHashMap<String, Integer>();
    Map<String, Integer> coin = new LinkedHashMap<String, Integer>();
    // Display display;
    Database database;

    public VendingMachine() {
        this.allUsers = new ArrayList<>();
        this.currentUser = null;
        this.anonymous = new Customer(null, null);
        this.productList = new ArrayList<Product>();
        note.put("100", 5);
        note.put("50", 5);
        note.put("20", 5);
        note.put("10", 10);
        note.put("5", 10);
        note.put("2", 20);
        note.put("1", 20);

        coin.put("50", 30);
        coin.put("20", 30);
        coin.put("10", 30);
        coin.put("5", 30);
        // display = new Display();
        this.database = new Database();

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

    private boolean login() throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean success = false;
        String username = "";
        String password = "";
        PasswordField pw = new PasswordField();

        while (!success) {
            System.out.println("Please enter your username: ");
            username = scan.next();
            // System.out.println("Please enter your password: ");
            password = pw.readPassword("Please enter your password: ");
            
            if (Database.checkUser(username, password)) {
                System.out.println("\nLogin successful.");
                this.currentUser = this.getUser(username);
                return true;

            } else {
                System.out.println("Incorrect username or password. Please enter 1 to try again or 2 to quit.");
                String option = scan.next();

                while (!option.equals("1") && !option.equals("2")) {
                    System.out.println("Invalid option, please try again.");
                    option = scan.next();
                }
            
                if (option.equals("2")) {
                    return false;
                } 
            }

        }
        return false;
    }

    public void createAccount() throws Exception {
        String username = "";
        String password = "";
        Scanner scan = new Scanner(System.in);
        boolean notUser = true;

        while (notUser) {
            System.out.println("Please enter a username: ");
            username = scan.next();

            while(username.length() == 0 || username.equals("")) {
                System.out.println("Please specify a valid username.");
            }
            boolean checkExists = this.userExists(username);

            if (checkExists) {
                System.out.println("Username already exists. Please enter 1 to try again or 2 to quit.");
                String option = scan.next();
                
                while (!option.equals("1") && !option.equals("2")) {
                    System.out.println("Invalid option, please try again.");
                    option = scan.next();
                }
                if (option.equals("2")) {
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

        System.out.println("Welcome to the Vending Machine");
        System.out.println("Please select from the following options: \n[1] for login, [2] for create account, [3] to continue as guest.");
        
        String opt = scan.next();
        while (opt.isEmpty() || (!opt.equals("1") && !opt.equals("2") && !opt.equals("3"))) {
            System.out.println("Option invalid, please try again. \nPlease enter [1] for login, [2] for create account, [3] to continue as guest");
            opt = scan.next();
        }

        if (opt.equals("1")) {
            if (login() == false) {
                return;
            }

        } else if (opt.equals("2")) {
            createAccount();

        } else if (opt.equals("3")) {
            this.currentUser = anonymous;
        }

        HashMap<Product, Integer> selected = select();
        paymentHandle(selected, scan);


        // Transaction t = scan(String.valueOf(date), String.valueOf(time), paymentMethod, selected, moneyPaid, returnChange);
        // currentUser.addTransaction(t);

        if (currentUser instanceof Owner) {
            System.out.println("Welcome Owner");
            System.out.println("As an owner, here are your options for performing functionalities . \nA list of usernames in the vending machine\nA summary of cancelled transcations");
        }

        else if (currentUser instanceof Seller){
            ////
        }

        else if (currentUser instanceof User) {
            
            
 
        
        } else if (currentUser instanceof Cashier) {
            
        }


    }

    private void paymentHandle(HashMap<Product, Integer> selected, Scanner scan) throws Exception {
        String paymentMethod = "";
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        double moneyPaid = 0.0;
        double returnChange = 0.0;
        double total = 0;

        // calculating total by looping through the selected list
        for (Map.Entry<Product, Integer> entry : selected.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }

        System.out.println("Your total is" + String.valueOf(total));
        System.out.println("Please type [1] to proceed to payment process or [2] to cancel purchase");

        String paymentOption = scan.next();

        while (!paymentOption.equals("1") && ! paymentOption.equals("2")) {
            System.out.println("Invalid option, please try again.");
            System.out.println("Please type [1] to proceed to payment process or [2] to cancel purchase");
            paymentOption = scan.next();
        }

        
        if (paymentOption.equals("1")) {
            System.out.println("Please type [1] to pay with card or [2] to pay with cash");
            paymentOption = scan.next();

            while (!paymentOption.equals("1") && ! paymentOption.equals("2")) {
                System.out.println("Invalid option, please try again.");
                System.out.println("Please type [1] to pay with card or [2] to pay with cash");
                paymentOption = scan.next();
            }

            // card payment method
            if (paymentOption.equals("1")) {
                System.out.println("Please enter card holder name");
                String cardHolderName = scan.next();

                System.out.println("Please enter pin");
                String pin = scan.next();

            
                if (Cashier.payByCard(cardHolderName, pin)) {
                    System.out.println("transaction completed. Thank you.");
                    // currentUser.addCardInfo(cardHolderName, pin);
                }

                moneyPaid = total;
                paymentMethod = "card";
            
                
            } else {
                System.out.println("Please input your cash");
                double paymentAmount = scan.nextDouble();
                
                double totalChange = paymentAmount - total;
                if (totalChange <= 0) {
                    
                    // error message 
                }
                returnChange(note, coin, totalChange);
                moneyPaid = paymentAmount;
                returnChange = totalChange;
                paymentMethod = "cash";

                System.out.println("transaction completed. Thank you.");  
            }

            Transaction t = scan(String.valueOf(date), String.valueOf(time), paymentMethod, selected, moneyPaid, returnChange);
        }
        // Transaction t = scan(String.valueOf(date), String.valueOf(time), paymentMethod, selected, moneyPaid, returnChange);
    }

    
    public Transaction scan(String date, String time, String paymentMethod, HashMap<Product, Integer> selectedProduct, 
    double moneyPaid, double returnChange) {
        // System.out.println("Your total is" + String.valueOf())
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
        HashMap<Product, Integer> selectedProducts = new HashMap<Product, Integer>();
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

    public void handleChangeProcess (Map<String, Integer> availableMon, int totalChange) {
        int leftChange = totalChange;

        for (HashMap.Entry<String, Integer> entry : availableMon.entrySet()) {
            int note      = Integer.parseInt(entry.getKey());
            int available = entry.getValue();

            // if the notes less than the change to return
            if (note <= leftChange && available != 0) {
                // calculate how many notes needed
                int numOfNote = leftChange / note % note;

                // check if available note satisfy number of notes we need
                if (available <= numOfNote) {
                    numOfNote -= available;
                }

                // update change to return by minus itself with the number of notes multiply the note
                leftChange -= note * numOfNote;
                availableMon.put(entry.getKey(), availableMon.get(entry.getKey()) - numOfNote);
                System.out.println(entry.getKey() + ": " + String.valueOf(numOfNote));

            }

            if (leftChange == 0) {
                break;
            }
        }

        if (leftChange != 0) {
            System.out.println("there is no available change. Do you want to input new note (press Y) or do you want to cancel the order (press C)");
        }
    }

    public void returnChange(Map<String, Integer> note, Map<String, Integer> coin, double totalChangeDe) {
        int leftNote = 0;
        int leftCoin = 0;
        String withoutLossOf0TotalChange = String.format("%.2f", totalChangeDe);
        double totalChange = Double.valueOf(withoutLossOf0TotalChange);
        // if change is not a whole
        if (totalChange % 1 != 0) {
            // String tc = Double.toString(totalChange);
            String[] convert = withoutLossOf0TotalChange.split("\\.");

            leftNote = Integer.parseInt(convert[0]);
            
            leftCoin = Integer.parseInt(convert[1]);
            // System.out.println(leftCoin);
            System.out.println("Please take your change: ");
            handleChangeProcess(note, leftNote);
            handleChangeProcess(coin, leftCoin);

        } else {
            // System.out.println(totalChange);
            String tc = Double.toString(totalChange);
            String[] convert = tc.split("\\.");

            leftNote = Integer.parseInt(convert[0]);

            handleChangeProcess(note, leftNote);
        }
    }


}

