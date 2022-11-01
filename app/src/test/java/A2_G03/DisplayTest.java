package A2_G03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import nl.altindag.console.ConsoleCaptor;
// import org.hamcrest.MatcherAssert.assertThat;
import com.google.common.truth.Truth;


class DisplayTest {

    @Test 
    void displayConstructorTest() {
        Display d = new Display();
        assertNotNull(d);
    }

    @Test 
    void testSellerInterface() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        Display d = new Display();
 
        // assertEquals("50: 30\n20: 30\n10: 30\n5: 30\n", c.availableChangeDisplay(coin));
        d.sellerInterface();
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Welcome Seller! Please select from one of the following options:");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[1] Modify item name");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[2] Modify item code");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[3] Modify item category");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[4] Modify item quantity");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[5] Modify item price");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[6] Obtain list of current available items");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[7] Obtain item summary");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[8] Purchase from Vending Machine");
        // d.sellerInterface();
        
        
        consoleCaptor.close();
    
    }

    @Test 
    void testOwnerInterface() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        Display d = new Display();
 
        // assertEquals("50: 30\n20: 30\n10: 30\n5: 30\n", c.availableChangeDisplay(coin));
        d.ownerInterface();


        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Welcome Owner! Please select from one of the following options:");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[1] Modify item name");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[2] Modify item code");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[3] Modify item category");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[4] Modify item quantity");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[5] Modify item price");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[6] Add/remove Seller");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[7] Add/remove Cashier");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[8] Add/remove Owner");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[9] Obtain list of usernames with associated roles");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[10] Obtain summary of cancelled transactions");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[11] Purchase from Vending Machine");
        // Truth.assertThat(consoleCaptor.getStandardOutput()).contains("\n");
        // d.sellerInterface();
        
        
        consoleCaptor.close();
    
    }


    @Test 
    void testCashierInterface() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        Display d = new Display();
 
        // assertEquals("50: 30\n20: 30\n10: 30\n5: 30\n", c.availableChangeDisplay(coin));
        d.cashierInterface();
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Welcome Cashier! Please select from one of the following options:");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[1] Modify change");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[2] View current available change");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[3] Obtain summary of transactions");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("[4] Purchase from Vending Machine");

        
        // Truth.assertThat(consoleCaptor.getStandardOutput()).contains("\n");
        // d.sellerInterface();
        
        
        consoleCaptor.close();
    
    }

    @Test 
    void testDisplayCancelled () {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        Display d = new Display();
        HashMap<Product, Integer> boughtProductTest = new HashMap<Product, Integer>();
        Product choc = new Chocolate("Mars", 11, 5.0);
        boughtProductTest.put(choc, 9);
        
        String date          = "12-12-12";
        String time          = "5:54:09";
        String paymentMethod = "cash";
        double moneyPaid     = 12.90;
        double returnChange  = 10.90;
        
        Transaction t = new Transaction(date, time, paymentMethod, boughtProductTest, moneyPaid, returnChange);
        t.reasonCancelled = "run out of money";
        ArrayList<Transaction> cancelTest = new ArrayList<Transaction>();
        cancelTest.add(t);
        

        d.displayCancelled(cancelTest);
    
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Cancelled transactions:");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("12-12-12, 5:54:09, Mars x 9, run out of money");
        
        consoleCaptor.close();
    
    }

    @Test 
    void testDisplayChange() {
        Map<String, Integer> note = new LinkedHashMap<String, Integer>();
        Map<String, Integer> coin = new LinkedHashMap<String, Integer>();

        note.put("100", 5);
        note.put("50", 5);
        

        coin.put("50", 30);
        coin.put("20", 30);

        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        Display d = new Display();
 
        // assertEquals("50: 30\n20: 30\n10: 30\n5: 30\n", c.availableChangeDisplay(coin));
        d.displayChange(note, coin);

        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("$100: 5");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("$50: 5");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("50c :30");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("20c :30");

        consoleCaptor.close();
        
    }

    @Test
    void testTransactionSummary() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        Display d = new Display();

        HashMap<Product, Integer> boughtProductTest = new HashMap<Product, Integer>();
        Product choc = new Chocolate("Mars", 11, 5.0);
        boughtProductTest.put(choc, 9);
        
        String date          = "12-12-12";
        String time          = "5:54:09";
        String paymentMethod = "cash";
        double moneyPaid     = 12.90;
        double returnChange  = 10.90;
        
        Transaction t = new Transaction(date, time, paymentMethod, boughtProductTest, moneyPaid, returnChange);
        ArrayList<Transaction> completeTest = new ArrayList<Transaction>();
        completeTest.add(t);

        d.transactionSummary(completeTest);
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("12-12-12, 5:54:09, Mars x 9, Paid: 12.9 Change: 10.9, cash");

        consoleCaptor.close();
    }

    @Test
    void testDisplayCreditCards() {
        User owner = new Owner("Owner1", "password");
        Display d = new Display();

        owner.addCardInfo("card", "info");

        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        d.displayCreditCards(owner);

        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Saved credit cards:");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Card name: card");
        
        consoleCaptor.close();
    }

    @Test
    void testDisplayLastFive() {
        User owner = new Owner("Owner1", "password");
        Display d = new Display();
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        
        HashMap<Product, Integer> boughtProductTest = new HashMap<Product, Integer>();
        Product choc = new Chocolate("Mars", 11, 5.0);
        boughtProductTest.put(choc, 9);
        
        String date          = "12-12-12";
        String time          = "5:54:09";
        String paymentMethod = "cash";
        double moneyPaid     = 12.90;
        double returnChange  = 10.90;
        
        Transaction t = new Transaction(date, time, paymentMethod, boughtProductTest, moneyPaid, returnChange);
        // ArrayList<Transaction> historyTest = new ArrayList<Transaction>();
        // historyTest.add(t);

        owner.addTransaction(t);
        

        d.displayLastFive(owner);

        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Purchase history:");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("12-12-12, 5:54:09, Mars x 9, Paid: $12.9, Change: $10.9, cash");
        
        consoleCaptor.close();
    }

    @Test
    void testDisplayProduct() {
        Display d = new Display();
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        Product chips = new Chips("chip", 21, 30.0);
        Product drink = new Drink("drink", 01, 23.0);
        Product choc = new Chocolate("Mars", 11, 5.0);
        Product candy = new Candies("Chuba chup", 13, 1.0);
        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(chips);
        productList.add(drink);
        productList.add(choc);
        productList.add(candy);

        d.displayProducts(productList);

        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Product name   | Product Category | Price     | Quantity available");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("-------------------------------------------------------------------");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("drink          | Drink            | 23.0      | 7");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("-------------------------------------------------------------------");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Mars           | Chocolates       | 5.0       | 7");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("-------------------------------------------------------------------");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("chip           | Chips            | 30.0      | 7");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("-------------------------------------------------------------------");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Chuba chup     | Candies          | 1.0       | 7");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("-------------------------------------------------------------------");
        consoleCaptor.close();
    }

}

