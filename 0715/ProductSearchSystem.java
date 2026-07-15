/*
測試案例 1：正常值 - 功能 1 (顯示全部)，預期依序印出 5 項商品的所有資訊，實際通過
測試案例 2：正常值 - 功能 2 (完整) 輸入 " mouse "，預期顯示 Mouse 價格 490 與庫存 15，實際通過
測試案例 3：邊界值 - 功能 4 (最長商品)，預期顯示名稱最長的商品為 USB Type-C Cable，實際通過
測試案例 4：部分相符 - 功能 3 (部分) 輸入 "key"，預期顯示 Keyboard 商品資訊，實際通過
測試案例 5：錯誤值 - 功能 5 (位置) 輸入 "Camera"，預期提示在商品中找不到該關鍵字，實際通過
測試案例 6：空值防呆 - 功能 2 (完整) 輸入 "   "，預期提示搜尋關鍵字不可為空，實際通過
*/

import java.util.Scanner;

public class ProductSearchSystem {

    private static final String[] NAMES = {"Keyboard", "Mouse", "Monitor", "USB Type-C Cable", "Headset"};
    private static final int[] PRICES = {890, 490, 5200, 299, 1250};
    private static final int[] STOCKS = {10, 15, 5, 50, 8};

    public static void showAllProducts() {
        System.out.println("\n--- 商品清單 ---");
        for (int i = 0; i < NAMES.length; i++) {
            System.out.printf("名稱：%s | 價格：%d | 庫存：%d%n", NAMES[i], PRICES[i], STOCKS[i]);
        }
    }

    public static void exactSearch(String keyword) {
        String cleanKeyword = keyword.trim();
        if (cleanKeyword.isEmpty()) {
            System.out.println("搜尋關鍵字不可為空！");
            return;
        }

        boolean found = false;
        for (int i = 0; i < NAMES.length; i++) {
            if (NAMES[i].equalsIgnoreCase(cleanKeyword)) {
                System.out.printf("找到商品！名稱：%s | 價格：%d | 庫存：%d%n", NAMES[i], PRICES[i], STOCKS[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("找不到此商品。");
        }
    }

    public static void partialSearch(String keyword) {
        String cleanKeyword = keyword.trim().toLowerCase();
        if (cleanKeyword.isEmpty()) {
            System.out.println("搜尋關鍵字不可為空！");
            return;
        }

        boolean found = false;
        System.out.println("\n--- 部分搜尋結果 ---");
        for (int i = 0; i < NAMES.length; i++) {
            if (NAMES[i].toLowerCase().contains(cleanKeyword)) {
                System.out.printf("名稱：%s | 價格：%d | 庫存：%d%n", NAMES[i], PRICES[i], STOCKS[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("沒有相符的商品。");
        }
    }

    public static void showLongestProductName() {
        String longest = NAMES[0];
        int targetIndex = 0;

        for (int i = 1; i < NAMES.length; i++) {
            if (NAMES[i].length() > longest.length()) {
                longest = NAMES[i];
                targetIndex = i;
            }
        }

        System.out.println("\n--- 最長名稱商品 ---");
        System.out.printf("名稱：%s (字元數：%d) | 價格：%d | 庫存：%d%n", 
                NAMES[targetIndex], longest.length(), PRICES[targetIndex], STOCKS[targetIndex]);
    }

    public static void findKeywordIndex(String keyword) {
        String cleanKeyword = keyword.trim().toLowerCase();
        if (cleanKeyword.isEmpty()) {
            System.out.println("搜尋關鍵字不可為空！");
            return;
        }

        boolean found = false;
        for (String name : NAMES) {
            int index = name.toLowerCase().indexOf(cleanKeyword);
            if (index != -1) {
                System.out.printf("商品名稱：「%s」，關鍵字「%s」第一次出現位置的索引為：%d%n", name, keyword.trim(), index);
                found = true;
            }
        }

        if (!found) {
            System.out.println("在所有商品名稱中皆未找到該關鍵字。");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== 商品名稱搜尋系統 =====");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 完整名稱搜尋 (忽略大小寫與前後空白)");
            System.out.println("3. 部分名稱搜尋 (可顯示多筆結果)");
            System.out.println("4. 顯示名稱最長的商品");
            System.out.println("5. 顯示名稱中關鍵字第一次出現的位置");
            System.out.println("6. 結束");
            System.out.print("請選擇功能 (1-6)：");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("請輸入正確的數字選單！");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    showAllProducts();
                    break;
                case 2:
                    System.out.print("請輸入完整商品名稱：");
                    exactSearch(sc.nextLine());
                    break;
                case 3:
                    System.out.print("請輸入部分商品名稱：");
                    partialSearch(sc.nextLine());
                    break;
                case 4:
                    showLongestProductName();
                    break;
                case 5:
                    System.out.print("請輸入要找的位置關鍵字：");
                    findKeywordIndex(sc.nextLine());
                    break;
                case 6:
                    System.out.println("感謝使用，系統已關閉。");
                    running = false;
                    break;
                default:
                    System.out.println("無效的選項，請輸入 1-6 之間的數字。");
            }
        }
        sc.close();
    }
}
