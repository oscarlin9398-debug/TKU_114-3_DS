import java.util.Scanner;

public class ProductManagementSystem {
    private static Product[] products = new Product[10];
    private static int productCount = 0;
    private static int successOperations = 0;
    private static int failedOperations = 0;

    public static void initProducts() {
        addProductDirectly(new Product("Keyboard", 890, 12));
        addProductDirectly(new Product("Mouse", 490, 20));
        addProductDirectly(new Product("Monitor", 5200, 5));
        addProductDirectly(new Product("Headphones", 1500, 8));
        addProductDirectly(new Product("Webcam", 1200, 15));
    }

    public static void addProductDirectly(Product product) {
        if (productCount < products.length) {
            products[productCount] = product;
            productCount++;
        }
    }

    public static void showAllProducts() {
        System.out.println("\n=== 全部商品清單 ===");
        boolean hasProduct = false;
        for (Product p : products) {
            if (p != null) {
                System.out.println(p);
                hasProduct = true;
            }
        }
        if (!hasProduct) {
            System.out.println("目前系統中沒有任何商品。");
        }
    }

    public static Product findProductByName(String name) {
        if (name == null) {
            return null;
        }
        String targetName = name.trim();
        for (Product p : products) {
            if (p != null && p.getName().equalsIgnoreCase(targetName)) {
                return p;
            }
        }
        return null;
    }

    public static void handleSearch(Scanner scanner) {
        System.out.print("請輸入要搜尋的商品名稱：");
        String name = scanner.nextLine();
        Product p = findProductByName(name);
        if (p != null) {
            System.out.println("找到商品：" + p);
            successOperations++;
        } else {
            System.out.println("錯誤：找不到該商品。");
            failedOperations++;
        }
    }

    public static void handleAddNewProduct(Scanner scanner) {
        if (productCount >= products.length) {
            System.out.println("錯誤：商品庫存空間已滿，無法新增！");
            failedOperations++;
            return;
        }

        System.out.print("請輸入新商品名稱：");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("錯誤：商品名稱不可為空白！");
            failedOperations++;
            return;
        }

        if (findProductByName(name) != null) {
            System.out.println("錯誤：已存在同名商品，不可重複新增！");
            failedOperations++;
            return;
        }

        System.out.print("請輸入商品價格：");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.print("請輸入初始庫存：");
        int stock = Integer.parseInt(scanner.nextLine());

        Product newProduct = new Product(name, price, stock);
        addProductDirectly(newProduct);
        System.out.println("商品新增成功：" + newProduct);
        successOperations++;
    }

    public static void handleSellProduct(Scanner scanner) {
        System.out.print("請輸入要出售的商品名稱：");
        String name = scanner.nextLine();
        Product p = findProductByName(name);
        if (p == null) {
            System.out.println("錯誤：找不到該商品。");
            failedOperations++;
            return;
        }

        System.out.print("請輸入出售數量：");
        int qty = Integer.parseInt(scanner.nextLine());
        if (p.sell(qty)) {
            System.out.println("出售成功！現有庫存：" + p.getStock());
            successOperations++;
        } else {
            System.out.println("錯誤：出售失敗，數量不合規或庫存不足！");
            failedOperations++;
        }
    }

    public static void handleRestockProduct(Scanner scanner) {
        System.out.print("請輸入要補貨的商品名稱：");
        String name = scanner.nextLine();
        Product p = findProductByName(name);
        if (p == null) {
            System.out.println("錯誤：找不到該商品。");
            failedOperations++;
            return;
        }

        System.out.print("請輸入補貨數量：");
        int qty = Integer.parseInt(scanner.nextLine());
        if (p.restock(qty)) {
            System.out.println("補貨成功！現有庫存：" + p.getStock());
            successOperations++;
        } else {
            System.out.println("錯誤：補貨數量必須大於 0！");
            failedOperations++;
        }
    }

    public static void handleModifyPrice(Scanner scanner) {
        System.out.print("請輸入要修改價格的商品名稱：");
        String name = scanner.nextLine();
        Product p = findProductByName(name);
        if (p == null) {
            System.out.println("錯誤：找不到該商品。");
            failedOperations++;
            return;
        }

        System.out.print("請輸入新價格：");
        int price = Integer.parseInt(scanner.nextLine());
        if (p.setPrice(price)) {
            System.out.println("價格修改成功！新價格：" + p.getPrice());
            successOperations++;
        } else {
            System.out.println("錯誤：價格必須大於 0！");
            failedOperations++;
        }
    }

    public static void showLowStockProducts() {
        System.out.println("\n=== 低庫存商品 (少於 10 件) ===");
        boolean hasLowStock = false;
        for (Product p : products) {
            if (p != null && p.isLowStock()) {
                System.out.println(p);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("目前沒有低庫存商品。");
        }
        successOperations++;
    }

    public static void showTotalInventoryValue() {
        long total = 0;
        for (Product p : products) {
            if (p != null) {
                total += p.getInventoryValue();
            }
        }
        System.out.println("\n目前全店庫存總價值：" + total + " 元");
        successOperations++;
    }

    public static void printSummary() {
        System.out.println("\n==================================");
        System.out.println("系統已成功結束。操作摘要如下：");
        System.out.println("成功操作次數：" + successOperations);
        System.out.println("失敗操作次數：" + failedOperations);
        System.out.println("==================================");
    }

    public static void main(String[] args) {
        initProducts();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n*** 商品管理系統選單 ***");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 搜尋商品");
            System.out.println("3. 新增商品");
            System.out.println("4. 出售商品");
            System.out.println("5. 補充庫存");
            System.out.println("6. 修改商品價格");
            System.out.println("7. 顯示低庫存商品");
            System.out.println("8. 顯示全部庫存總價值");
            System.out.println("9. 結束系統");
            System.out.print("請選擇操作 (1-9)：");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showAllProducts();
                    break;
                case "2":
                    handleSearch(scanner);
                    break;
                case "3":
                    handleAddNewProduct(scanner);
                    break;
                case "4":
                    handleSellProduct(scanner);
                    break;
                case "5":
                    handleRestockProduct(scanner);
                    break;
                case "6":
                    handleModifyPrice(scanner);
                    break;
                case "7":
                    showLowStockProducts();
                    break;
                case "8":
                    showTotalInventoryValue();
                    break;
                case "9":
                    printSummary();
                    running = false;
                    break;
                default:
                    System.out.println("無效的選擇，請重新輸入！");
                    failedOperations++;
                    break;
            }
        }
        scanner.close();
    }
}
