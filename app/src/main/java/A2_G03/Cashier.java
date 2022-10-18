import java.util.*;

public class Cashier  {
    public Cashier() {

    }

    public void handleChangeProcess (Map<String, Integer> availableMon, double totalChange) {
        int leftChange = totalChange;

        System.out.println("Please take your change: ");
        for (HashMap.Entry<String, Integer> entry : change.entrySet()) {
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
                change.put(entry.getKey(), change.get(entry.getKey()) - numOfNote);
                System.out.println(entry.getKey() + ": " + String.valueOf(numOfNote));

            }

            if (leftChange == 0) {
                break;
            }
        }

        if (leftChange == 0) {
            System.out.println("there is no available change. Do you want to input new note (press Y) or do you want to cancel the order (press C)");
        }
    }

    public void returnChange(Map<String, Integer> note, Map<String, Integer> coin, double totalChange) {
        int leftNote = 0;
        int leftCoin = 0;

        // if change is not a whole
        if (totalChange % 1 != 0) {
            String tc = Double.toString(totalChange);
            String[] convert = tc.split("\\.");

            leftNote = Integer.parseInt(convert[0]);
            leftCoin = Integer.parseInt(convert[1]);

            handleChangeProcess(note, leftNote);
            handleChangeProcess(coin, leftCoin);

        } else {
            String tc = Double.toString(totalChange);
            String[] convert = tc.split("\\.");

            leftNote = Integer.parseInt(convert[0]);

            handleChangeProcess(note, leftNote);
        }
    }

    public void modifyChange(Map<String, Integer> change, String toChange, int quantity) {
        change.put(toChange, change.get(toChange) + quantity);
    }

    public void availableChangeDisplay(Map<String, Integer> change) {
        for (HashMap.Entry<String, Integer> entry : change.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.valueOf(entry.getValue()));
        }
    }

    public void transactionSummary() {
        for (Transaction t : Transaction.allTrans) {
            System.out.println(t.getDate() + t.getTime() + t.getItem() + t.getMoneyPaid() + t.getReturnChange() + t.getPaymentMethod());
        }
    }
}