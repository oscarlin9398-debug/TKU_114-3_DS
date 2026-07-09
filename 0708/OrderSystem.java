import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int totalQuantity = 0;
        int totalAmount = 0;
        int option = -1;

        while (option != 0) {
            System.out.println("\n=== 飲料點餐系統 ===");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout 結帳");
            System.out.print("請輸入商品選項：");
            option = sc.nextInt();

            int price = 0;
            String drinkName = "";

            switch (option) {
                case 1:
                    drinkName = "Black tea";
                    price = 30;
                    break;
                case 2:
                    drinkName = "Green tea";
                    price = 35;
                    break;
                case 3:
                    drinkName = "Coffee";
                    price = 50;
                    break;
                case 0:
                    System.out.println("正在結帳");
                    break;
                default:
                    System.out.println("無此商品，請重新選擇。");
            }

            if (option == 1 || option == 2 || option == 3) {
                System.out.print("請輸入數量：");
                int quantity = sc.nextInt();

                while (quantity <= 0) {
                    System.out.print("數量必須大於 0，請重新輸入數量：");
                    quantity = sc.nextInt();
                }

                int subtotal = price * quantity;
                totalQuantity += quantity;
                totalAmount += subtotal;

                System.out.println("-> 已點: " + drinkName + " " + quantity + " 杯，本次小計: $" + subtotal);
            }
        }

        System.out.println("=== 結帳明細 ===");
        System.out.println("總數量: " + totalQuantity + " 杯");
        System.out.println("總金額: $" + totalAmount);
        System.out.println("謝謝光臨！");

        sc.close();
    }
}
