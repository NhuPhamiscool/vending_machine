package A2_G03;

public class Product {
    // Item code Table
    // 01 ~ 10 Drinks
    // 11 ~ 20 Chocolates
    // 21 ~ 30 Chips
    // 31 ~ 40 Candies

    public String name;
    public String category;
    public int itemCode;
    public int currentStock;
    public double price;
    public final int INITIALStock = 7;
    public final int MAXStock = 15;
    public int quantitySold = 0;


    public Product(String name, int itemCode, double price) {
        this.name = name;
        this.itemCode = itemCode;
        this.price = price;
        this.currentStock = INITIALStock;
    }



    // To be called after each restock completed by seller(?)
    String getCategory() {
        return this.category;
    }
    
    int getCode() {
        return this.itemCode;
    }

    int getQuantitySold() {
        return this.quantitySold;
    }

    int getStock() {
        return this.currentStock;
    }

    String getName() {
        return this.name;
    }

    void setCategory(String category) {
        this.category = category;
    }
    
    void setCode(int code) {
        this.itemCode = code;
    }

    void setQuantity(int quantity) {
        this.currentStock = quantity;
    }

    void setName(String name) {
        this.name = name;
    }

    void setPrice(double price) {
        this.price = price;
    }


    // double getValue() {

    // }
    void addStock(int quantity) {
        int tmpStock = 0;
        tmpStock += quantity;
        if (tmpStock <= MAXStock && tmpStock > 0) {
            currentStock += quantity;
        }
    }

    void removeStock(int quantity) {
        int tmpStock = 0;
        tmpStock -= quantity;
        if (tmpStock <= MAXStock && tmpStock > 0) {
            currentStock -= quantity;
            quantitySold++;
        }
    }

    double getPrice() {
        return this.price;
    }

}