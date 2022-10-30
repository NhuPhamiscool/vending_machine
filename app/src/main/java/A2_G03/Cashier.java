package A2_G03;

import java.util.*;
import java.io.*;
import org.json.simple.parser.*;
import org.json.*;

public class Cashier extends User {
    // HashMap<String, Integer> change 
    // Map<String, Integer> change = new HashMap<>();

    public Cashier(String username, String password) {
        super(username, password);
        // change.put("20", 3);
        // change.put("10", 3);
        // change.put("5", 3);
        // change.put("50", 3);
        // change.put("1", 3);
        // change.put("2", 3);
    }

    // public void handleChangeProcess (Map<String, Integer> availableMon, int totalChange) {
    //     int leftChange = totalChange;

    //     System.out.println("Please take your change: ");
    //     for (HashMap.Entry<String, Integer> entry : availableMon.entrySet()) {
    //         int note      = Integer.parseInt(entry.getKey());
    //         int available = entry.getValue();

    //         // if the notes less than the change to return
    //         if (note <= leftChange && available != 0) {
    //             // calculate how many notes needed
    //             int numOfNote = leftChange / note % note;

    //             // check if available note satisfy number of notes we need
    //             if (available <= numOfNote) {
    //                 numOfNote -= available;
    //             }

    //             // update change to return by minus itself with the number of notes multiply the note
    //             leftChange -= note * numOfNote;
    //             availableMon.put(entry.getKey(), availableMon.get(entry.getKey()) - numOfNote);
    //             System.out.println(entry.getKey() + ": " + String.valueOf(numOfNote));

    //         }

    //         if (leftChange == 0) {
    //             break;
    //         }
    //     }

    //     if (leftChange == 0) {
    //         System.out.println("there is no available change. Do you want to input new note (press Y) or do you want to cancel the order (press C)");
    //     }
    // }

    // public void returnChange(Map<String, Integer> note, Map<String, Integer> coin, double totalChange) {
    //     int leftNote = 0;
    //     int leftCoin = 0;

    //     // if change is not a whole
    //     if (totalChange % 1 != 0) {
    //         String tc = Double.toString(totalChange);
    //         String[] convert = tc.split("\\.");

    //         leftNote = Integer.parseInt(convert[0]);
    //         leftCoin = Integer.parseInt(convert[1]);

    //         handleChangeProcess(note, leftNote);
    //         handleChangeProcess(coin, leftCoin);

    //     } else {
    //         String tc = Double.toString(totalChange);
    //         String[] convert = tc.split("\\.");

    //         leftNote = Integer.parseInt(convert[0]);

    //         handleChangeProcess(note, leftNote);
    //     }
    // }

    // public static boolean payByCard(String cardHolderName, String pin) throws ParseException {
    //     org.json.simple.JSONArray ja;

    //     try {
    //         String jsonString;
           
    //         jsonString = Database.read("credit_cards.json");
    //         JSONParser parser = new JSONParser();
    //         ja = (org.json.simple.JSONArray) parser.parse(jsonString);
            
    //     }
    //     catch (IOException e) {
    //         e.printStackTrace();
    //         return false;
    //     }

    //     for (int i = 0 ; i < ja.size(); i++) {
    //         org.json.simple.JSONObject obj = (org.json.simple.JSONObject) ja.get(i);
    //         if (obj.get("name").equals(cardHolderName) && obj.get("number").equals(pin)) {
    //             return true; 
    //         }
    //     }
    //     return false;





    // }

    // public void modifyChange(Map<String, Integer> change, String toChange, int quantity) {
    //     change.put(toChange, change.get(toChange) + quantity);
    // }

    // public void availableChangeDisplay(Map<String, Integer> change) {
    //     for (HashMap.Entry<String, Integer> entry : change.entrySet()) {
    //         System.out.println(entry.getKey() + ": " + String.valueOf(entry.getValue()));
    //     }
    // }

    // public void transactionSummary() {
    //     for (Transaction t : Transaction.completedTransaction) {
    //         System.out.println(t.getDate() + t.getTime() + t.getItemName() + String.valueOf(t.getMoneyPaid()) + String.valueOf(t.getReturnChange()) + t.getPaymentMethod());
    //     }
    // }


    public static boolean payByCard(String cardHolderName, String pin) throws ParseException {
        org.json.simple.JSONArray ja;

        try {
            String jsonString;
           
            jsonString = Database.read("credit_cards.json");
            JSONParser parser = new JSONParser();
            ja = (org.json.simple.JSONArray) parser.parse(jsonString);
            
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        for (int i = 0 ; i < ja.size(); i++) {
            org.json.simple.JSONObject obj = (org.json.simple.JSONObject) ja.get(i);
            if (obj.get("name").equals(cardHolderName) && obj.get("number").equals(pin)) {
                return true; 
            }
        }
        return false;
    }

    public void modifyChange(Map<String, Integer> change, String toChange, int quantity) {
        change.put(toChange, change.get(toChange) + quantity);
    }

    public String availableChangeDisplay(Map<String, Integer> change) {
        String output = "";
        
        for (HashMap.Entry<String, Integer> entry : change.entrySet()) {
            output += entry.getKey() + ": " + String.valueOf(entry.getValue()) + "\n";
        }
        return output;
    }

}