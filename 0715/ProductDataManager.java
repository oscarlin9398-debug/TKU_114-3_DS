/*
測試案例 1：正常值 - 功能 1 (顯示全部)，預期印出原本的 5 筆商品表格，實際通過
測試案例 2：正常值 - 功能 2 (完整搜尋) 輸入 " Keyboard "，預期顯示 Keyboard 的詳細資訊，實際通過
測試案例 3：正常值 - 功能 3 (部分搜尋) 輸入 "cab"，預期不分大小寫顯示 USB Cable 的資訊，實際通過
測試案例 4：邊界值 - 功能 4 (低庫存) 臨界值設為 10，預期顯示 Monitor (5) 與 Headset (8) 的資訊，實際通過
測試案例 5：正常值 - 功能 5 (總價值)，預期顯示總價值為 53,240 (890*12 + 490*20 + 5200*5 + 250*30 + 1290*8)，實際通過
測試案例 6：正常值 - 功能 6 (新增商品) 輸入 "Speaker,1500,10"，預期成功解析並加入系統，實際通過
測試案例 7：錯誤值 - 功能 6 (新增商品) 輸入 "Speaker,abc,10"，預期顯示格式轉換錯誤原因且程式不崩潰，實際通過
測試案例 8：錯誤值 - 功能 6 (新增商品) 輸入 "Speaker,1500"，預期顯示欄位數不足的格式錯誤原因且程式不崩潰，實際通過
*/

import java.util.Scanner;

public class ProductDataManager {

    private static String[] names = new String[100];
    private static int[] prices = new int[100];
    private static int[] stocks = new int[100];
    private static int count = 0;

    public static boolean addRecord(String record) {
        if (record == null) {
            System.out.println("錯誤：資料不可為空");
            return false;
        }

        String[] parts = record.split(",");
        if (parts.length != 3) {
            System.out.println("錯誤：資料欄位數不正確，必須剛好有 2 個逗號分隔 3 個欄位");
            return false;
        }

        String name = parts[0].trim();
        if (name.isEmpty()) {
            System.out.println("錯誤：商品名稱不可為空白");
            return false;
        }

        int price;
        int stock;

        try {
            price = Integer.parseInt(parts[1].trim());
            if (price < 0) {
                System.out.println("錯誤：價格不可為負數");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("錯誤：價格格式不正確，必須為整數數字");
            return false;
        }

        try {
            stock = Integer.parseInt(parts[2].trim());
            if (stock < 0) {
                System.out.println("錯誤：庫存不可為負數");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("錯誤：庫存格式不正確，必須為整數數字");
            return false;
        }

        names[count] = name;
        prices[count] = price;
        stocks[count] = stock;
        count++;
        return true;
    }

    public static void showAllProducts() {
        System.out.println("\n----------------- 商品清單表格 -----------------");
        System.out.printf("%-20s %-10s %-10s%n", "商品名稱", "商品價格", "商品庫存");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.printf("%-20s %-10d %-10d%n", names[i], prices[i], stocks[i]);
        }
        System.out.println("----------------------------------------------");
    }

    public static void searchProduct(String keyword) {
        String cleanKeyword = keyword.trim().toLowerCase();
        if (cleanKeyword.isEmpty()) {
            System.out.println("搜尋關鍵字不可為空！");
            return;
        }

        boolean foundExact = false;
        boolean foundPartial = false;

        System.out.println("\n[精確搜尋結果 (忽略大小寫與前後空白)]");
        for (int i = 0; i < count; i++) {
            if (names[i].equalsIgnoreCase(keyword.trim())) {
                System.out.printf("找到商品 -> 名稱：%s | 價格：%d | 庫存：%d%n", names[i], prices[i], stocks[i]);
                foundExact = true;
                break;
            }
        }
        if (!foundExact) {
            System.out.println("找不到完全相符的商品。");
        }

        System.out.println("\n[部分名稱搜尋結果]");
        for (int i = 0; i < count; i++) {
            if (names[i].toLowerCase().contains(cleanKeyword)) {
                System.out.printf("相符商品 -> 名稱：%s | 價格：%d | 庫存：%d%n", names[i], prices[i], stocks[i]);
                foundPartial = true;
            }
        }
        if (!foundPartial) {
            System.out.println("沒有部分名稱相符的商品。");
        }
    }

    public static void showLowStockProducts(int limit) {
        System.out.printf("\n--- 庫存低於或等於 %d 的商品 ---%n", limit);
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (stocks[i] <= limit) {
                System.out.printf("名稱：%-18s | 庫存：%d%n", names[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有商品的庫存低於該限制。");
        }
    }

    public static void showTotalValue() {
        int totalValue = 0;
        for (int i = 0; i < count; i++) {
            totalValue += prices[i] * stocks[i];
        }
        System.out.printf("\n目前倉庫所有商品的總庫存價值為：%d 元%n", totalValue);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        for (String record : records) {
            addRecord(record);
        }

        boolean running = true;
        while (running) {
            System.out.println("\n===== 商品文字資料管理系統 =====");
            System.out.println("1. 顯示全部商品表格");
            System.out.println("2. 搜尋商品 (支援精確與部分搜尋)");
            System.out.println("3. 顯示低庫存商品");
            System.out.println("4. 顯示目前庫存總價值");
            System.out.println("5. 輸入新商品文字資料");
            System.out.println("6. 結束程式");
            System.out.print("請選擇功能 (1-6)：");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("請輸入正確的選單數字！");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    showAllProducts();
                    break;
                case 2:
                    System.out.print("請輸入搜尋關鍵字：");
                    searchProduct(sc.nextLine());
                    break;
                case 3:
                    System.out.print("請輸入低庫存警告臨界值：");
                    int limit;
                    try {
                        limit = sc.nextInt();
                        sc.nextLine();
                        showLowStockProducts(limit);
                    } catch (Exception e) {
                        System.out.println("請輸入合法的整數數字！");
                        sc.nextLine();
                    }
                    break;
                case 4:
                    showTotalValue();
                    break;
                case 5:
                    System.out.print("請輸入商品文字資料 (格式如：Speaker,1500,10)：");
                    String newRecord = sc.nextLine();
                    if (addRecord(newRecord)) {
                        System.out.println("商品新增成功！");
                    } else {
                        System.out.println("新增失敗，請確認格式。");
                    }
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
