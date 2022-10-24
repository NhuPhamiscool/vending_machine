package A2_G03;

import java.util.ArrayList;
import java.util.Arrays;

class Drink extends Product {
    int available;
    int quantitySold = 0;
    ArrayList<String> drinksList = new ArrayList<>(Arrays.asList("Mineral Water", "Sprite", "Coca cola", "Pepsi", "Juice"));

    public Drink (String name, int itemCode, double price) {
        super(name, itemCode, price);
        this.category = "Drinks";
        this.available = currentStock - quantitySold;

    }

    // @Override
    // void addStock(int quantity) {
    //     int tmpStock = 0;
    //     tmpStock += quantity;
    //     if (tmpStock <= MAXStock && tmpStock > 0) {
    //         currentStock += quantity;
    //     }
    // }

    // void removeStock(int quantity) {
    //     int tmpStock = 0;
    //     tmpStock -= quantity;
    //     if (tmpStock <= MAXStock && tmpStock > 0) {
    //         currentStock -= quantity;
    //         quantitySold++;

    //     }
    // }

    // double getPrice() {
    //     return this.price;
    // }

}