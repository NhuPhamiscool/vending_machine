package A2_G03;

import java.util.*;

public class Seller extends User {
    public Seller(String username, String password) {
        super(username, password);
    }

    public void modifyItem (Product itemToModify, String newItemName, int newItemCode, String newItemCategory,
    int newItemQuantity, double newItemPrice) {
        
        itemToModify.setName(newItemName);
        itemToModify.setCode(newItemCode);
        itemToModify.setCategory(newItemCategory);
        itemToModify.setQuantity(newItemQuantity);
        itemToModify.setPrice(newItemPrice);
    }

    // create a hashmap of product with their current stock
    public void availableItemDisplay(Map<Product, Integer> currentItem) {
        for (HashMap.Entry<Product, Integer> entry : currentItem.entrySet()) {
            if (entry.getValue() != 0) {
                System.out.println(entry.getKey().getName() + ": " 
                                    + entry.getKey().getCode() + ", " 
                                    + entry.getKey().getCategory() + ", " 
                                    + entry.getKey().getStock() + ", " 
                                    + entry.getKey().getPrice());

            }
        }
    }

    public void itemSummary(Map<Product, Integer> currentItem) {
        for (HashMap.Entry<Product, Integer> entry : currentItem.entrySet()) {
            System.out.println(entry.getKey().getCode() + ": " 
                                    + entry.getKey().getName() + ": " 
                                    + entry.getKey().getQuantitySold());
        }
    }
}
