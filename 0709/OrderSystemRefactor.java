import java.util.Scanner;

public class OrderSystemRefactor {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        int totalItems = 0;
        int totalAmount = 0;
        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項：");
            option = s.nextInt();

            if (option == 0) {
                break;
            }

            int price = getPrice(option);

            if (price == 0) {
                System.out.println("無效選項，請重新輸入。");
                System.out.println();
                continue;
            }

            int quantity = readValidQuantity(s);
            int subtotal = calculateSubtotal(price, quantity);

            totalItems += quantity;
            totalAmount += subtotal;

            System.out.println("已加入購物車，小計：" + subtotal + " 元");
            System.out.println();
        }

        printReceipt(totalItems, totalAmount);
        s.close();
    }

    public static void printMenu() {
        System.out.println("=== 點餐系統 ===");
        System.out.println("1. 紅茶 ($30)");
        System.out.println("2. 綠茶 ($25)");
        System.out.println("3. 咖啡 ($50)");
        System.out.println("0. 結帳");
    }

    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 25;
            case 3: return 50;
            default: return 0;
        }
    }

    public static int readValidQuantity(Scanner s) {
        System.out.print("請輸入數量：");
        int quantity = s.nextInt();
        
        while (quantity <= 0) {
            System.out.print("數量必須大於 0，請重新輸入：");
            quantity = s.nextInt();
        }
        
        return quantity;
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("=== 結帳明細 ===");
        System.out.println("總數量：" + totalItems + " 杯");
        System.out.println("總金額：" + totalAmount + " 元");
        System.out.println("謝謝光臨！");
    }
}
