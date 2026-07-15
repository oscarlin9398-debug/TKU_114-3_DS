import java.util.Scanner;

public class ProductArraySystem {

    public static void printMenu() {
        System.out.println("\n===== 商品陣列管理系統 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束系統");
        System.out.print("請輸入操作選項 (1-7)：");
    }

    public static void showAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n[商品列表]");
        System.out.printf("%-6s %-15s %-10s %-6s%n", "編號", "商品名稱", "價格", "庫存");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-8d %-17s %-12d %-6d%n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }

    public static void queryProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入商品編號 (1-" + names.length + ")：");
        int id = sc.nextInt();
        if (id < 1 || id > names.length) {
            System.out.println("錯誤：無此商品編號！");
            return;
        }
        int index = id - 1;
        System.out.println("\n[查詢結果]");
        System.out.println("名稱：" + names[index]);
        System.out.println("價格：" + prices[index]);
        System.out.println("庫存：" + stocks[index]);
    }

    public static boolean buyProduct(Scanner sc, String[] names, int[] stocks, int[] buyCountRef) {
        System.out.print("請輸入購買商品編號 (1-" + names.length + ")：");
        int id = sc.nextInt();
        if (id < 1 || id > names.length) {
            System.out.println("錯誤：無此商品編號！");
            return false;
        }
        int index = id - 1;
        System.out.print("請輸入購買數量：");
        int qty = sc.nextInt();
        if (qty <= 0) {
            System.out.println("錯誤：購買數量必須大於 0！");
            return false;
        }
        if (qty > stocks[index]) {
            System.out.println("錯誤：庫存不足！現有庫存：" + stocks[index]);
            return false;
        }
        stocks[index] -= qty;
        buyCountRef[0] += qty;
        System.out.println("成功購買 " + qty + " 個 " + names[index] + "！");
        return true;
    }

    public static boolean restockProduct(Scanner sc, String[] names, int[] stocks, int[] restockCountRef) {
        System.out.print("請輸入補貨商品編號 (1-" + names.length + ")：");
        int id = sc.nextInt();
        if (id < 1 || id > names.length) {
            System.out.println("錯誤：無此商品編號！");
            return false;
        }
        int index = id - 1;
        System.out.print("請輸入補貨數量：");
        int qty = sc.nextInt();
        if (qty <= 0) {
            System.out.println("錯誤：補貨數量必須大於 0！");
            return false;
        }
        stocks[index] += qty;
        restockCountRef[0] += qty;
        System.out.println("成功為 " + names[index] + " 補貨 " + qty + " 個！新庫存：" + stocks[index]);
        return true;
    }

    public static void showLowStock(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n[低庫存商品 (庫存 < 10)]");
        boolean found = false;
        System.out.printf("%-6s %-15s %-10s %-6s%n", "編號", "商品名稱", "價格", "庫存");
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf("%-8d %-17s %-12d %-6d%n", (i + 1), names[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    public static int calculateTotalValue(int[] prices, int[] stocks) {
        int total = 0;
        for (int i = 0; i < prices.length; i++) {
            total += prices[i] * stocks[i];
        }
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int[] totalBuyCount = {0};
        int[] totalRestockCount = {0};
        int menuClickCount = 0;

        boolean running = true;
        while (running) {
            printMenu();
            int choice = sc.nextInt();
            menuClickCount++;

            switch (choice) {
                case 1:
                    showAllProducts(names, prices, stocks);
                    break;
                case 2:
                    queryProduct(sc, names, prices, stocks);
                    break;
                case 3:
                    buyProduct(sc, names, stocks, totalBuyCount);
                    break;
                case 4:
                    restockProduct(sc, names, stocks, totalRestockCount);
                    break;
                case 5:
                    showLowStock(names, prices, stocks);
                    break;
                case 6:
                    int totalValue = calculateTotalValue(prices, stocks);
                    System.out.println("\n目前全部庫存總價值：" + totalValue + " 元");
                    break;
                case 7:
                    running = false;
                    System.out.println("\n===== 系統關閉，操作摘要 =====");
                    System.out.println("總操作次數：" + menuClickCount + " 次");
                    System.out.println("累計購買商品件數：" + totalBuyCount[0] + " 件");
                    System.out.println("累計補充商品件數：" + totalRestockCount[0] + " 件");
                    break;
                default:
                    System.out.println("錯誤：無效的選項，請重新輸入！");
            }
        }

        sc.close();
    }
}
