package A2_G03;

import java.util.ArrayList;
import java.util.Arrays;

public class Chocolate extends Product {

    int available;
    int quantitySold = 0;

    ArrayList<String> chocolatesList = new ArrayList<>(Arrays.asList("Mars", "M&M", "Bounty", "Snickers"));

    public Chocolate (String name, int itemCode, double price) {
        super(name, itemCode, price);
        this.category = "Chocolates";
        this.available = currentStock - quantitySold;

    }
    // { Mars, M&M, Bounty, Snickers }

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