package A2_G03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;


class CashierTest {

    @Test 
    void cashierConstructorTest() {
        // should we put constructor parameter into database
        // transaction method moved?
        // seller class changing void to String
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
        Cashier c = new Cashier("cashier", "cashier123");
        Map<String, Integer> coin = new LinkedHashMap<String, Integer>();
        coin.put("50", 30);
        coin.put("20", 30);
        coin.put("10", 30);
        coin.put("5", 30);
        
        

        assertEquals("50: 30\n20: 30\n10: 30\n5: 30\n", c.availableChangeDisplay(coin));
    }

}
