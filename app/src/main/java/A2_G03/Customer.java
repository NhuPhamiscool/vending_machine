package A2_G03;
import java.util.*;

public class Customer extends User {
    
    String username;
    String password;
    boolean anon;
    List<Transaction> purchaseHistory;

    public Customer(String username, String password){
        super(username, password);
        this.purchaseHistory = new ArrayList<>();
        this.anon = false;
    }

    public void setAnon(){
        this.anon = true;
    }

    public boolean isAnon(){
        return this.anon;
    }

    public void addTransaction(Transaction t){
        this.purchaseHistory.add(t);
    }

    public List<Transaction> getPurchaseHistory(){
        return this.purchaseHistory;
    }

}