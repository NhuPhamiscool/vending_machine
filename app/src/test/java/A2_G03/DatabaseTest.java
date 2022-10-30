package A2_G03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class DatabaseTest {

    @Test 
    void databaseConstructorTest() {
        Database db = new Database();
        assertNotNull(db);
    }

    @Test 
    void testCheckUserAndAddCustomer() throws Exception {
        Database.addCustomer("customer", "customer123");
        
        assertEquals(true , Database.checkUser("customer", "customer123"));
    
    }

    @Test 
    void testGetJsonFileObject() throws Exception {
        // JSONObject expected = Database.getJSONFileObject("login_info");
        
        assertNotNull(Database.getJSONFileObject("login_info"));
    
    }

    @Test 
    void testRead() throws Exception {
        // JSONObject expected = Database.getJSONFileObject("login_info");
        
        assertNotNull(Database.read("login_info.json"));
    
    }

    @Test 
    void testSetUp() throws Exception {
        // JSONObject expected = Database.getJSONFileObject("login_info");
        Database.setUp();
        File file = new File("./data" + "/" + "login_info.json");
        assertTrue(file.exists());
        String actual = Database.read("login_info.json");
        String expected = "{\"Owner\":[],\"Customers\":[],\"Seller\":[],\"Admin\":[],\"Cashier\":[]}";
        // String expected = "{\"update_test\":[{\"rates\":{\"wen\":[2.0,1.0],\"sgp\":[1.98,0.0]},\"dates\":\"2012-09-09\"}]}";
        assertEquals(expected, actual);
        
    
    }

    // @Test
    // void testItemSummary() {
    //     Seller s = new Seller("seller", "seller123");
        
    //     Product chips = new Chips("chip", 21, 30.0);
    //     Product drink = new Drink("drink", 01, 23.0);

    //     ArrayList<Product> currentItem = new ArrayList<Product>();
    //     currentItem.add(chips);
    //     currentItem.add(drink);
        
    //     assertEquals("21: chip: 0\n1: drink: 0\n" , s.itemSummary(currentItem));
    // }
}
