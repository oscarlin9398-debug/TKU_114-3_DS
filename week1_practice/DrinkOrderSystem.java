import java.util.Scanner;

public class DrinkOrderSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 初始化每種商品的銷售杯數計數器
        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;

        int totalItems = 0;
        int totalAmount = 0;
        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            // 如果選 0 就直接跳出，準備結帳
            if (option == 0) {
                break;
            }

            int price = getPrice(option);

            // 輸入不合法選項的防錯處理
            if (price == 0) {
                System.out.println("錯誤：無效的選項，請重新輸入！\n");
                continue;
            }

            String itemName = getItemName(option);
            int quantity = readValidQuantity(sc);
            int subtotal = calculateSubtotal(price, quantity);

            // 累加總杯數與折扣前總金額
            totalItems += quantity;
            totalAmount += subtotal;

            // 根據選項，把數量加到對應的飲料計數器中
            switch (option) {
                case 1:
                    blackTeaCount += quantity;
                    break;
                case 2:
                    greenTeaCount += quantity;
                    break;
                case 3:
                    milkTeaCount += quantity;
                    break;
                case 4:
                    coffeeCount += quantity;
                    break;
            }

            // 印出本次單項小計
            System.out.println(itemName + " x " + quantity);
            System.out.println("Subtotal: " + subtotal);
            System.out.println();
        }

        // 計算折扣後金額並列印收據
        int finalAmount = calculateDiscountedTotal(totalAmount);
        printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount, totalItems, totalAmount, finalAmount);

        sc.close();
    }

    // 顯示飲料選單
    public static void printMenu() {
        System.out.println("=== Drink Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Milk tea   $45");
        System.out.println("4. Coffee     $50");
        System.out.println("0. Checkout");
    }

    // 根據選項回傳商品單價
    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 45;
            case 4: return 50;
            default: return 0; // 不合法選項回傳 0 元
        }
    }

    // 根據選項回傳商品名稱
    public static String getItemName(int option) {
        switch (option) {
            case 1: return "Black tea";
            case 2: return "Green tea";
            case 3: return "Milk tea";
            case 4: return "Coffee";
            default: return "Unknown";
        }
    }

    // 讀取合法數量
    public static int readValidQuantity(Scanner sc) {
        System.out.print("請輸入數量：");
        int quantity = sc.nextInt();
        
        while (quantity <= 0) {
            System.out.print("錯誤：數量必須大於 0，請重新輸入：");
            quantity = sc.nextInt();
        }
        return quantity;
    }

    // 計算單項商品小計
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    // 依據滿額規則計算折扣後金額
    public static int calculateDiscountedTotal(int totalAmount) {
        // 滿 300 元打 9 折
        if (totalAmount >= 300) {
            return (int) (totalAmount * 0.9);
        } else {
            return totalAmount;
        }
    }

    // 印出最終收據
    public static void printReceipt(int blackTeaCount, int greenTeaCount, int milkTeaCount, int coffeeCount, int totalItems, int totalAmount, int finalAmount) {
        System.out.println();
        System.out.println("=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green tea: " + greenTeaCount);
        System.out.println("Milk tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);
        
        // 判斷是否有打折並印出標籤
        if (totalAmount >= 300) {
            System.out.println("Discount: 10% OFF");
        } else {
            System.out.println("Discount: No");
        }
        
        System.out.println("Final amount: " + finalAmount);
    }
}
