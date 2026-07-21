import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<CartItem> cart = new ArrayList<>();

        while (true) {
            System.out.println("\n=== 購物車管理系統 ===");
            System.out.println("1. 加入商品");
            System.out.println("2. 修改商品數量");
            System.out.println("3. 移除商品");
            System.out.println("4. 檢視購物車與計算總額");
            System.out.println("5. 離開系統");
            System.out.print("請選擇操作 (1-5)：");

            if (!scanner.hasNextInt()) {
                System.out.println("錯誤：請輸入有效的選單數字！");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItem(scanner, cart);
                    break;
                case 2:
                    updateQuantity(scanner, cart);
                    break;
                case 3:
                    removeItem(scanner, cart);
                    break;
                case 4:
                    viewCart(cart);
                    break;
                case 5:
                    System.out.println("感謝使用，系統已結束。");
                    scanner.close();
                    return;
                default:
                    System.out.println("錯誤：請輸入選項 1 到 5 之間的數字。");
            }
        }
    }

    public static void addItem(Scanner scanner, ArrayList<CartItem> cart) {
        System.out.print("請輸入商品代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("錯誤：商品代碼不可為空白！");
            return;
        }

        System.out.print("請輸入欲加入的數量：");
        if (!scanner.hasNextInt()) {
            System.out.println("錯誤：數量必須為整數！");
            scanner.next();
            return;
        }
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (quantity <= 0) {
            System.out.println("錯誤：加入數量必須大於 0！");
            return;
        }

        CartItem existingItem = findItemByCode(cart, code);
        if (existingItem != null) {
            existingItem.addQuantity(quantity);
            System.out.println("購物車已存在此商品，數量已自動累加！目前數量：" + existingItem.getQuantity());
            return;
        }

        System.out.print("請輸入商品名稱：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("錯誤：商品名稱不可為空白！");
            return;
        }

        System.out.print("請輸入商品單價：");
        if (!scanner.hasNextInt()) {
            System.out.println("錯誤：單價必須為整數！");
            scanner.next();
            return;
        }
        int price = scanner.nextInt();
        scanner.nextLine();

        if (price < 0) {
            System.out.println("錯誤：單價不可為負數！");
            return;
        }

        cart.add(new CartItem(code, name, price, quantity));
        System.out.println("新增商品成功：[" + name + "]");
    }

    public static void updateQuantity(Scanner scanner, ArrayList<CartItem> cart) {
        System.out.print("請輸入要修改數量的商品代碼：");
        String code = scanner.nextLine().trim();

        CartItem item = findItemByCode(cart, code);
        if (item == null) {
            System.out.println("修改失敗：購物車中無此商品代碼 [" + code + "]。");
            return;
        }

        System.out.println("目前商品資訊：" + item);
        System.out.print("請輸入新的數量：");
        if (!scanner.hasNextInt()) {
            System.out.println("錯誤：數量必須為整數！");
            scanner.next();
            return;
        }
        int newQuantity = scanner.nextInt();
        scanner.nextLine();

        if (newQuantity <= 0) {
            System.out.println("修改失敗：數量小於或等於 0 時不接受更新！");
            return;
        }

        item.setQuantity(newQuantity);
        System.out.println("數量修改成功！商品 [" + item.getName() + "] 的新數量為：" + newQuantity);
    }

    public static void removeItem(Scanner scanner, ArrayList<CartItem> cart) {
        System.out.print("請輸入要移除的商品代碼：");
        String code = scanner.nextLine().trim();

        CartItem item = findItemByCode(cart, code);
        if (item == null) {
            System.out.println("移除失敗：購物車中無此商品代碼 [" + code + "]。");
            return;
        }

        cart.remove(item);
        System.out.println("移除成功：已將商品 [" + item.getName() + "] 自購物車移除。");
    }

    public static void viewCart(ArrayList<CartItem> cart) {
        if (cart.isEmpty()) {
            System.out.println("購物車目前是空的！");
            return;
        }

        System.out.println("\n--- 購物車清單 ---");
        int grandTotal = 0;
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            System.out.println((i + 1) + ". " + item);
            grandTotal += item.getSubtotal();
        }
        System.out.println("--------------------");
        System.out.println("購物車總金額：" + grandTotal + " 元");
    }

    public static CartItem findItemByCode(ArrayList<CartItem> cart, String code) {
        if (code.isEmpty()) {
            return null;
        }
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }
}
