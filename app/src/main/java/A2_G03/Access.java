// package A2_G03;

// import java.util.ArrayList;
// import java.util.Arrays;

// // read from JSON file

// public class Access {

//     ArrayList<String> ownerList = new ArrayList<>(Arrays.asList("owner1", "owner2"));
//     ArrayList<String> sellerList = new ArrayList<>(Arrays.asList("seller1", "seller2"));
//     ArrayList<String> cashierList = new ArrayList<>(Arrays.asList("cashier1", "cashier2"));
//     ArrayList<String> customerList = new ArrayList<>(Arrays.asList("customer1", "customer2"));  // no anonymous users Z

//     //  TODO: store objects


//     public boolean checkOwner(String username) {
//         for (int i = 0; i <= ownerList.size(); i++) {
//             if (username == ownerList.get(i)) return true;
//         } return false;
//     }

//     public boolean checkSeller(String username) {
//         for (int i = 0; i <= sellerList.size(); i++) {
//             if (username == sellerList.get(i)) return true;
//         } return false;
//     }

//     public boolean checkCashier(String username) {
//         for (int i = 0; i <= cashierList.size(); i++) {
//             if (username == cashierList.get(i)) return true;
//         } return false;
//     }

//     public boolean checkCustomer(String username) {
//         for (int i = 0; i <= customerList.size(); i++) {
//             if (username == customerList.get(i)) return true;
//         } return false;
//     }
// }