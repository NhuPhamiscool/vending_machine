package A2_G03;

import java.util.*;

public class Seller extends User {
    public Seller(String username, String password) {
        super(username, password);
    }

    public void availableItemDisplay(ArrayList<Product> currentItem) {
        for (Product p: currentItem) {
            if (p.getStock() != 0) {
                System.out.println(p.getName() + ": " 
                                    + p.getCode() + ", " 
                                    + p.getCategory() + ", " 
                                    + p.getStock() + ", " 
                                    + p.getPrice());
            }
        }
    }


    // public String availableItemDisplay(ArrayList<Product> currentItem) {
    //     String output = "";
    //     for (Product p: currentItem) {
    //         if (p.getStock() != 0) {
    //             output += p.getName() + ": " 
    //                                 + p.getCode() + ", " 
    //                                 + p.getCategory() + ", " 
    //                                 + p.getStock() + ", " 
    //                                 + p.getPrice();
    //             output += "\n";
    //         }
    //     }
    //     return output;
    // }

    public void itemSummary(ArrayList<Product> currentItem) {
        for (Product p: currentItem) {
            System.out.println(p.getCode() + ": " 
                                    + p.getName() + ": " 
                                    + p.getQuantitySold());
        }
    }


    // public void modifyItem (Product itemToModify, String newItemName, int newItemCode, String newItemCategory,
    // int newItemQuantity, double newItemPrice) {
        
    //     itemToModify.setName(newItemName);
    //     itemToModify.setCode(newItemCode);
    //     itemToModify.setCategory(newItemCategory);
    //     itemToModify.setQuantity(newItemQuantity);
    //     itemToModify.setPrice(newItemPrice);
    // }

    // // create a hashmap of product with their current stock
    // public void availableItemDisplay(Map<Product, Integer> currentItem) {
    //     for (HashMap.Entry<Product, Integer> entry : currentItem.entrySet()) {
    //         if (entry.getValue() != 0) {
    //             System.out.println(entry.getKey().getName() + ": " 
    //                                 + entry.getKey().getCode() + ", " 
    //                                 + entry.getKey().getCategory() + ", " 
    //                                 + entry.getKey().getStock() + ", " 
    //                                 + entry.getKey().getPrice());

    //         }
    //     }
    // }

    // public void itemSummary(Map<Product, Integer> currentItem) {
    //     for (HashMap.Entry<Product, Integer> entry : currentItem.entrySet()) {
    //         System.out.println(entry.getKey().getCode() + ": " 
    //                                 + entry.getKey().getName() + ": " 
    //                                 + entry.getKey().getQuantitySold());
    //     }
    // }
}
