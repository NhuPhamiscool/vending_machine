import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;
import nl.altindag.console.ConsoleCaptor;
// import org.hamcrest.MatcherAssert.assertThat;
import com.google.common.truth.Truth;


class ShoppingBasketTest {

    @Test 
    void addItemTest() {
        List<String> item = new ArrayList<String>();
        item.add("apple");
        item.add("banana");
        item.add("orange");
        item.add("pear");

        ShoppingBasket test = new ShoppingBasket();
        assertThrows(
           ArgumentException.class,
           () -> test.addItem("h", 9);
        );

        assertThrows(
           ArgumentException.class,
           () -> test.addItem("", 9);
        );

        assertThrows(
           NumberException.class,
           () -> test.addItem("banana", 0);
        );

        assertThrows(
           NumberException.class,
           () -> test.addItem("banana", -1);
        );

        assertThrows(
           NumberException.class,
           () -> test.addItem("banana", "0");
        );
    }

    @Test 
    void testPayByCard() throws Exception {
        Cashier c = new Cashier("cashier", "cashier123");
        
        assertEquals(true , c.payByCard("Sergio", "42689"));
    
    }

    @Test
    void testModifyChange() {
        Cashier c = new Cashier("cashier", "cashier123");
        Map<String, Integer> coin = new LinkedHashMap<String, Integer>();
        coin.put("50", 30);
        coin.put("20", 30);
        coin.put("10", 30);
        coin.put("5", 30);
        
        c.modifyChange(coin, "50", 2);

        assertEquals(32, coin.get("50"));
    }

    @Test
    void testAvailableChangeDisplay() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        Cashier c = new Cashier("cashier", "cashier123");
        Map<String, Integer> coin = new LinkedHashMap<String, Integer>();
        coin.put("50", 30);
        coin.put("20", 30);
        coin.put("10", 30);
        coin.put("5", 30);
        
        

        // assertEquals("50: 30\n20: 30\n10: 30\n5: 30\n", c.availableChangeDisplay(coin));
        c.availableChangeDisplay(coin);
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("50: 30");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("20: 30");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("10: 30");
        Truth.assertThat(consoleCaptor.getStandardOutput()).contains("5: 30");
        
        consoleCaptor.close();
    }

}