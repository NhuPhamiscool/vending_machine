import java.util.*;

public class Cashier {
    public Cashier() {

    }

    public void modifyChange(Map<String, Integer> change, String toChange, int quantity) {
        change.put(toChange, change.get(toChange) + quantity);
    }

    public void availableChangeDisplay(Map<String, Integer> change) {
        for (HashMap.Entry<String, Integer> entry : change.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void transactionSummary() {
        for (Transaction t : Transaction.allTrans) {
            System.out.println(t.getDate() + t.getTime() + t.getItem() + t.getMoneyPaid() + t.getReturnChange() + t.getPaymentMethod());
        }
    }
}