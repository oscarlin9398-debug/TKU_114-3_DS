import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();

        while (true) {
            System.out.println("\n=== 名單管理系統 ===");
            System.out.println("1. 新增姓名");
            System.out.println("2. 搜尋姓名");
            System.out.println("3. 修改姓名");
            System.out.println("4. 刪除姓名");
            System.out.println("5. 列出全部");
            System.out.println("6. 離開系統");
            System.out.print("請選擇操作 (1-6)：");

            if (!scanner.hasNextInt()) {
                System.out.println("錯誤：請輸入有效的選單數字！");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addName(scanner, names);
                    break;
                case 2:
                    searchName(scanner, names);
                    break;
                case 3:
                    updateName(scanner, names);
                    break;
                case 4:
                    deleteName(scanner, names);
                    break;
                case 5:
                    listAllNames(names);
                    break;
                case 6:
                    System.out.println("感謝使用，系統已結束。");
                    scanner.close();
                    return;
                default:
                    System.out.println("錯誤：請輸入選項 1 到 6 之間的數字。");
            }
        }
    }

    public static void addName(Scanner scanner, ArrayList<String> names) {
        System.out.print("請輸入要新增的姓名：");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("錯誤：姓名不可為空白！");
            return;
        }

        names.add(input);
        System.out.println("新增成功：[" + input + "]");
    }

    public static void searchName(Scanner scanner, ArrayList<String> names) {
        System.out.print("請輸入要搜尋的姓名：");
        String target = scanner.nextLine().trim();

        if (target.isEmpty()) {
            System.out.println("錯誤：搜尋關鍵字不可為空白！");
            return;
        }

        boolean found = false;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(target)) {
                System.out.println("找到匹配姓名：" + names.get(i) + " (位置索引：" + i + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("未找到包含 [" + target + "] 的姓名。");
        }
    }

    public static void updateName(Scanner scanner, ArrayList<String> names) {
        System.out.print("請輸入要修改的舊姓名：");
        String oldName = scanner.nextLine().trim();

        int index = findIndexIgnoreCase(names, oldName);
        if (index == -1) {
            System.out.println("修改失敗：找不到舊姓名 [" + oldName + "]。");
            return;
        }

        System.out.print("請輸入新的姓名：");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("修改失敗：新姓名不可為空白！");
            return;
        }

        names.set(index, newName);
        System.out.println("修改成功：已將 [" + oldName + "] 更新為 [" + newName + "]。");
    }

    public static void deleteName(Scanner scanner, ArrayList<String> names) {
        System.out.print("請輸入要刪除的姓名：");
        String target = scanner.nextLine().trim();

        boolean removed = false;
        for (int i = names.size() - 1; i >= 0; i--) {
            if (names.get(i).equalsIgnoreCase(target)) {
                names.remove(i);
                removed = true;
            }
        }

        if (removed) {
            System.out.println("刪除成功：已移除名單中相符的姓名 [" + target + "]。");
        } else {
            System.out.println("刪除失敗：找不到姓名 [" + target + "]。");
        }
    }

    public static void listAllNames(ArrayList<String> names) {
        if (names.isEmpty()) {
            System.out.println("目前名單為空。");
            return;
        }

        System.out.println("\n--- 目前名單列表 (共 " + names.size() + " 筆) ---");
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }

    public static int findIndexIgnoreCase(ArrayList<String> names, String target) {
        if (target.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
}
