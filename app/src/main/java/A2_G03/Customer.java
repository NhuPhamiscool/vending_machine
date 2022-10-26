package A2_G03;
import java.util.*;

public class Customer extends User {
    
    String username;
    String password;
    boolean anon;
    Map<String, String> cardInfo;
    List<Transaction> purchaseHistory;

    public Customer(String username, String password){
        super(username, password);
        this.purchaseHistory = new ArrayList<>();
        this.cardInfo = new HashMap<String, String>();
        this.anon = false;
    }

    public void addCardInfo(String cardHolderName, String pin) {
        this.cardInfo.put(cardHolderName, pin);
    }

    public void getCardInfo() {
        System.out.println("These are cards of yours we have recorded. Please specify which one you want to proceed the payment with ");
        for (HashMap.Entry<String, String> entry : this.cardInfo.entrySet()) {
            System.out.println(entry.getKey());
        }
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