package A2_G03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
import nl.altindag.console.ConsoleCaptor;
// import org.hamcrest.MatcherAssert.assertThat;
import com.google.common.truth.Truth;


class VendingMachineTest {

    @Test 
    void vendingConstructorTest() {
        VendingMachine v = new VendingMachine();
        assertNotNull(v);
    }

    @Test 
    void testGetUser() {
        VendingMachine vm = new VendingMachine();
        User s = new Customer("seller", "seller123");
        vm.allUsers.add(s);

        assertEquals("seller" , vm.getUser("seller").getUsername());
    
    }

    @Test 
    void testUserExists() {
        VendingMachine vm = new VendingMachine();
        User s = new Customer("seller", "seller123");
        vm.allUsers.add(s);

        assertEquals(true , vm.userExists("seller"));
    
    }

    @Test 
    void testFindProduct() {
        VendingMachine vm = new VendingMachine();
        Product chips = new Chips("chip", 21, 30.0);
        vm.productList.add(chips);

        assertEquals(true , vm.findProduct("chip"));
        assertEquals(false , vm.userExists("chips"));
    
    }

    @Test 
    void testModifyItemPrice() {
        VendingMachine vm = new VendingMachine();
        Product chips = new Chips("chip", 21, 30.0);
        vm.productList.add(chips);

        
        assertEquals(30.0 , chips.getPrice());
        vm.modifyItemPrice("chip", 21.0);
        assertEquals(21.0 , chips.getPrice());
    
    }

    @Test 
    void testModifyItemName() {
        VendingMachine vm = new VendingMachine();
        Product chips = new Chips("chip", 21, 30.0);
        vm.productList.add(chips);

        
        assertEquals("chip" , chips.getName());
        vm.modifyItemName("chip", "chippie");
        assertEquals("chippie" , chips.getName());
    
    }

    @Test 
    void testModifyItemCategory() {
        VendingMachine vm = new VendingMachine();
        Product chips = new Chips("chip", 21, 30.0);
        vm.productList.add(chips);

        
        assertEquals("Chips" , chips.getCategory());
        vm.modifyItemCategory("chip", "Drink");
        assertEquals("Drink" , chips.getCategory());
    
    }

    @Test 
    void testModifyItemCode() {
        VendingMachine vm = new VendingMachine();
        Product chips = new Chips("chip", 21, 30.0);
        vm.productList.add(chips);

        
        assertEquals(21 , chips.getCode());
        vm.modifyItemCode("chip", 12);
        assertEquals(12 , chips.getCode());
    
    }

    @Test 
    void testModifyItemQuantity() {
        VendingMachine vm = new VendingMachine();
        Product chips = new Chips("chip", 21, 30.0);
        vm.productList.add(chips);

        
        assertEquals(7 , chips.getStock());
        vm.modifyItemQuantity("chip", 12);
        assertEquals(12 , chips.getStock());
    
    }

    @Test 
    void testDecreaseQuantity() {
        VendingMachine vm = new VendingMachine();
        HashMap<Product, Integer> boughtProductTest = new HashMap<Product, Integer>();
        Product chips = new Chips("chip", 21, 30.0);
        boughtProductTest.put(chips, 2);
        
        assertEquals(7 , chips.getStock());
        vm.decreaseQty(boughtProductTest);
        assertEquals(5 , chips.getStock());
    }

    @Test 
    void testScanMethod() {
        VendingMachine vm = new VendingMachine();

        HashMap<Product, Integer> boughtProductTest = new HashMap<Product, Integer>();
        Product chips = new Chips("chip", 21, 30.0);
        boughtProductTest.put(chips, 7);
        
        String date          = "12-12-12";
        String time          = "5:54:09";
        String paymentMethod = "cash";
        double moneyPaid     = 12.90;
        double returnChange  = 10.90;
        
        assertNotNull(vm.scan(date, time, paymentMethod, boughtProductTest, moneyPaid, returnChange));
        
    }

    @Test 
    void testReturnChange() {
        VendingMachine vm = new VendingMachine();
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        Map<String, Integer> note = new LinkedHashMap<String, Integer>();
        Map<String, Integer> coin = new LinkedHashMap<String, Integer>();
        
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
        coin.put("2", 30);
        coin.put("1", 30);

        vm.returnChange(note, coin, 40.0);

        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Please take your change:");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("$20: 2");
        
        vm.returnChange(note, coin, 39.98);

        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("Please take your change:");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("$20: 1");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("$10: 1");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("$5: 1");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("$2: 2");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("50c: 1");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("20c: 2");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("5c: 1");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("2c: 1");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("1c: 1");
        
        consoleCaptor.close();
    }
}