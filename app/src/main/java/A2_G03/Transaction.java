package A2_G03;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Transaction {
    boolean isCancelled;
    String itemName;
    // int quantity;
    String paymentMethod;
    String date;
    String time;
    double moneyPaid;
    double returnChange;    
    // double total;
    boolean approved;
    // HashMap<Product, Integer> productsAndQuantity;
    static ArrayList<Transaction> completedTransaction;
    HashMap<Product, Integer> boughtProduct;


    public Transaction(String date, String time, String paymentMethod, HashMap<Product, Integer> boughtProduct, double moneyPaid, double returnChange) {
        //setting initial state of cancellation to be false
        this.isCancelled=false;
        this.date = date;
        this.time = time;
        // this.quantity=quantity;
        this.paymentMethod = paymentMethod;
        this.itemName = itemName;
        this.moneyPaid = moneyPaid;
        this.returnChange = returnChange;
        // this.total= price*quantity;
        this.approved =false;
        // this.isCancelled=false;
        // this.productsAndQuantity = new HashMap<Product, Integer>();
        this.completedTransaction = new ArrayList<Transaction>();
        this.boughtProduct = new HashMap<Product, Integer>();
        // this.productsAndQuantity.put(item,quantity);

    }

    public void cancelTransaction(){
        this.isCancelled = true;

    }


    //approving the transcation because the payment has gone through
    public void approveTransaction() {
        //if the paymnet has been approved
        this.approved=true;
        //add to the transcation list

    }

    // public void addToTransaction(Product p, int quantity) {
    //     productsAndQuantity.put(p, quantity);
    // }

    public void addToTransaction(Transaction t) {
        // productsAndQuantity.put(p, quantity);
        completedTransaction.add(t);
    }

    public ArrayList<Transaction> getCompletedTransaction() {
        return this.completedTransaction;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public double getMoneyPaid() {
        return this.moneyPaid;
    }

    public double getReturnChange() {
        return this.returnChange;
    }



    public void calculateTotal() {

    }
}

    //time
    //date

    //amount of money paid
    //calculate change function
