import java.util.Scanner;

public class ProductSortingSystem {
    public static void main(String[] args) {
        // 原始商品資料（至少 10 筆，包含同價位與同庫存）
        StoreProduct[] originalProducts = {
            new StoreProduct("P101", "Gaming Mouse", 1290, 25),
            new StoreProduct("P102", "Mechanical KB", 2500, 10),
            new StoreProduct("P103", "USB-C Hub", 850, 40),
            new StoreProduct("P104", "Budget Mouse", 650, 50),
            new StoreProduct("P105", "BT Headset", 1290, 15),
            new StoreProduct("P106", "Gaming Monitor", 5200, 8),
            new StoreProduct("P107", "Webcam 1080p", 1290, 30),
            new StoreProduct("P108", "Mouse Pad", 350, 100),
            new StoreProduct("P109", "HDMI Cable", 350, 60),
            new StoreProduct("P110", "Laptop Stand", 850, 25)
        };

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n============================================");
            System.out.println("          商 品 報 表 排序 選 單");
            System.out.println("============================================");
            System.out.println("1. 依價格升冪排序 (低 -> 高)");
            System.out.println("2. 依價格降冪排序 (高 -> 低)");
            System.out.println("3. 依庫存降冪排序 (多 -> 少)");
            System.out.println("4. 顯示原始商品資料");
            System.out.println("0. 離開系統");
            System.out.print("請選擇操作項目 (0-4)：");

            String choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    sortAndDisplay(originalProducts, 1, "價格", "升冪 (低 -> 高)");
                    break;
                case "2":
                    sortAndDisplay(originalProducts, 2, "價格", "降冪 (高 -> 低)");
                    break;
                case "3":
                    sortAndDisplay(originalProducts, 3, "庫存", "降冪 (多 -> 少)");
                    break;
                case "4":
                    System.out.println("【當前顯示：原始商品資料（未排序）】");
                    printProducts(originalProducts);
                    break;
                case "0":
                    System.out.println("系統已離開，感謝使用！");
                    running = false;
                    break;
                default:
                    System.out.println("錯誤：無效的選項，請輸入 0 到 4 之間的數字。");
            }
        }

        scanner.close();
    }

    public static void sortAndDisplay(StoreProduct[] source, int mode, String fieldName, String direction) {
        // 深層複製陣列，確保對副本排序不會污染原始資料
        StoreProduct[] workingCopy = copyProducts(source);

        // 自行實作的排序演算法 (Insertion Sort)
        insertionSort(workingCopy, mode);

        System.out.println("【排序欄位：" + fieldName + " | 排序方向：" + direction + "】");
        printProducts(workingCopy);
    }

    public static void insertionSort(StoreProduct[] products, int mode) {
        if (products == null || products.length <= 1) {
            return;
        }

        for (int index = 1; index < products.length; index++) {
            StoreProduct key = products[index];
            if (key == null) {
                continue;
            }

            int position = index - 1;

            while (position >= 0 && products[position] != null && shouldMoveRight(products[position], key, mode)) {
                products[position + 1] = products[position];
                position--;
            }

            products[position + 1] = key;
        }
    }

    private static boolean shouldMoveRight(StoreProduct current, StoreProduct key, int mode) {
        switch (mode) {
            case 1: // 價格升冪
                return current.getPrice() > key.getPrice();
            case 2: // 價格降冪
                return current.getPrice() < key.getPrice();
            case 3: // 庫存降冪
                return current.getStock() < key.getStock();
            default:
                return false;
        }
    }

    public static StoreProduct[] copyProducts(StoreProduct[] source) {
        if (source == null) {
            return new StoreProduct[0];
        }

        StoreProduct[] copy = new StoreProduct[source.length];
        for (int i = 0; i < source.length; i++) {
            if (source[i] != null) {
                copy[i] = new StoreProduct(
                        source[i].getId(),
                        source[i].getName(),
                        source[i].getPrice(),
                        source[i].getStock()
                );
            }
        }
        return copy;
    }

    public static void printProducts(StoreProduct[] products) {
        if (products == null || products.length == 0) {
            System.out.println("無商品資料。");
            return;
        }

        System.out.println("------------------------------------------------------------------");
        for (StoreProduct product : products) {
            System.out.println(product);
        }
        System.out.println("------------------------------------------------------------------");
    }
}

