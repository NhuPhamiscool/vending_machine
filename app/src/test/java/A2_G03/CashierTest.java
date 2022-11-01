package A2_G03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;
import nl.altindag.console.ConsoleCaptor;
// import org.hamcrest.MatcherAssert.assertThat;
import com.google.common.truth.Truth;


class CashierTest {

    @Test 
    void cashierConstructorTest() {
        // should we put constructor parameter into database
        // transaction method moved?
        // seller class changing void to String
        // inconsistent category name
        // add "ietm name | item code | .."
        // add "symbol $ or c when return change"
        // modify num of note cancel %
        // show card name only instead of card name  + number
        // getPin method added
        // purchase mpdify
        // there is not available change => writing scan for it instead of saying transaction completed
        Cashier c = new Cashier("cashier", "cashier123");
        assertNotNull(c);
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
