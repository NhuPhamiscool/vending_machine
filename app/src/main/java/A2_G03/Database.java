package A2_G03;

// import java.io.FileWriter;
// import java.io.IOException;
import org.json.simple.JSONArray;
// import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import org.json.*;
import java.io.FileReader;
import org.json.simple.parser.*;
import java.util.*;
import java.io.*;
import java.lang.Object;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;  
// import java.lang.Math;
// import java.time.LocalDate;

public class Database {
    private static final String FOLDER_PATH = "./data";

    public static void writeToFile(String fileName, String textToWrite, boolean append) throws IOException {
        File f = new File(FOLDER_PATH + "/" + fileName);

        if (f.createNewFile()){
            System.out.println("The file is created successfully!");
        }
        // else{
        //     System.out.println("The file already exists.");
        // }

        // File f = new File(FOLDER_PATH + "/" + fileName);
        FileWriter fWriter = new FileWriter(f, append);
        fWriter.write(textToWrite);
        fWriter.close();
    }
    
    public static void setUp() {
        // make a folder called data here
        File folder = new File(FOLDER_PATH);
        folder.mkdirs();

        JSONObject loginDetails = new JSONObject();
        // JSONObject cardDetails = new JSONObject();

        JSONArray nameAndCardDetail = new JSONArray();
        JSONArray nameAndPasswordCus = new JSONArray();
        JSONArray nameAndPasswordAd = new JSONArray();
        JSONArray nameAndPasswordSel = new JSONArray();
        JSONArray nameAndPasswordCas = new JSONArray();
        JSONArray nameAndPasswordOwn = new JSONArray();

        // JSONObject cus1 = new JSONObject();
        // cus1.put("huj", "ghudsjk");
        // nameAndPassword.put("user", "password");
        loginDetails.put("Customers", nameAndPasswordCus);
        loginDetails.put("Admin", nameAndPasswordAd);
        loginDetails.put("Seller", nameAndPasswordSel);
        loginDetails.put("Cashier", nameAndPasswordCas);
        loginDetails.put("Owner", nameAndPasswordOwn);

        try {

            writeToFile("login_info.json", loginDetails.toString(), false);
        }
        catch (IOException e) {
            System.out.println("Failed to write to data.json file");
            e.printStackTrace();
        }

    }

    public static String read(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(FOLDER_PATH + "/" + fileName);
        String str = new String(fis.readAllBytes());
        // System.out.println(str);
        fis.close();
        return str;
    }


    public static JSONObject getJSONFileObject (String fileName) throws Exception {
        
        try {
            String jsonString;
           
            jsonString = read(fileName + ".json");
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonString);
            return json;
            
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeFileProcess(String fileName, JSONObject jo)  {
        try {

            writeToFile(fileName + ".json", jo.toString(), false);
        }
        catch (IOException e) {
            System.out.println("Failed to write to data.json file");
            e.printStackTrace();
        }
    }


    public static void addCustomer (String username, String password)  throws Exception {
        JSONObject jObject =  getJSONFileObject("login_info");
        // create new currency
        // should we create new user here?
        // Currency cur = new Currency(baseName);

        
        Object roleName = jObject.get("Customers");

        JSONArray customerList = (JSONArray) roleName;

        JSONObject usernameAndPassword = new JSONObject();
        usernameAndPassword.put(username, password);

        customerList.add(usernameAndPassword);

        jObject.put("Customers", customerList);

        writeFileProcess("login_info", jObject);

    }

    // public static 

    public static boolean checkUser (String username, String password)  throws Exception {
        JSONObject jObject =  getJSONFileObject("login_info");
        
        for (Object role : jObject.keySet()) {
            JSONArray infoList = (JSONArray) jObject.get(role);

            for (int i = 0 ; i < infoList.size(); i++) {
                JSONObject obj = (JSONObject) infoList.get(i);
                String o = String.valueOf(obj);
                Map<String, String> userInfo = new Gson().fromJson(o, new TypeToken<HashMap<String, String>>() {  
                            }.getType());

                for (Map.Entry<String, String> entry : userInfo.entrySet()) {
                    if (entry.getKey().equals(username) && entry.getValue().equals(password)) {
                        return true;
                    }
                }
            }
            

        }
        return false;
       

    }
            //Print key and value
            // System.out.println("key: "+ keyStr + " value: " + keyvalue);

            //for nested objects iteration if required
            //if (keyvalue instanceof JSONObject)
            //    printJsonObject((JSONObject)keyvalue);
        // }
        
        // Object roleName = jObject.get("Customers");

        // JSONArray customerList = (JSONArray) roleName;

                     

        // for (int i = 0 ; i < customerList.size(); i++) {
        //     JSONObject obj = (JSONObject) customerList.get(i);
        //     String o = String.valueOf(obj);
        //     Map<String, String> userInfo = new Gson().fromJson(o, new TypeToken<HashMap<String, String>>() {  
        //                 }.getType());
        //     for (Map.Entry<String, String> entry : userInfo.entrySet()) {
        //         if (entry.getKey().equals(username) && entry.getValue().equals(password)) {
        //             return true;
        //         }
        //     }
            

        // }
    //     return false;
       

    // }



}