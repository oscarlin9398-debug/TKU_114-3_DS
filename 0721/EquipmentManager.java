import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Equipment> equipments = new ArrayList<>();

        while (true) {
            System.out.println("\n=== 設備管理系統 ===");
            System.out.println("1. 新增設備");
            System.out.println("2. 依代碼搜尋");
            System.out.println("3. 借出設備");
            System.out.println("4. 歸還設備");
            System.out.println("5. 列出可借設備");
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
                    addEquipment(scanner, equipments);
                    break;
                case 2:
                    searchEquipment(scanner, equipments);
                    break;
                case 3:
                    borrowEquipment(scanner, equipments);
                    break;
                case 4:
                    returnEquipment(scanner, equipments);
                    break;
                case 5:
                    listAvailableEquipment(equipments);
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

    public static void addEquipment(Scanner scanner, ArrayList<Equipment> equipments) {
        System.out.print("請輸入設備代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("錯誤：設備代碼不可為空白！");
            return;
        }

        if (findEquipmentByCode(equipments, code) != null) {
            System.out.println("錯誤：新增失敗，設備代碼 [" + code + "] 已存在！");
            return;
        }

        System.out.print("請輸入設備名稱：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("錯誤：設備名稱不可為空白！");
            return;
        }

        equipments.add(new Equipment(code, name));
        System.out.println("新增成功：代碼 [" + code + "]，名稱 [" + name + "]");
    }

    public static void searchEquipment(Scanner scanner, ArrayList<Equipment> equipments) {
        System.out.print("請輸入要查詢的設備代碼：");
        String code = scanner.nextLine().trim();

        Equipment equipment = findEquipmentByCode(equipments, code);
        if (equipment != null) {
            System.out.println("查詢結果 -> " + equipment);
        } else {
            System.out.println("查無此設備代碼 [" + code + "]。");
        }
    }

    public static void borrowEquipment(Scanner scanner, ArrayList<Equipment> equipments) {
        System.out.print("請輸入要借出的設備代碼：");
        String code = scanner.nextLine().trim();

        Equipment equipment = findEquipmentByCode(equipments, code);
        if (equipment == null) {
            System.out.println("借出失敗：查無此設備代碼 [" + code + "]。");
            return;
        }

        if (equipment.borrowEquipment()) {
            System.out.println("借出成功！設備 [" + equipment.getName() + "] 已被借出。");
        } else {
            System.out.println("借出失敗：該設備目前已經是「已借出」狀態。");
        }
    }

    public static void returnEquipment(Scanner scanner, ArrayList<Equipment> equipments) {
        System.out.print("請輸入要歸還的設備代碼：");
        String code = scanner.nextLine().trim();

        Equipment equipment = findEquipmentByCode(equipments, code);
        if (equipment == null) {
            System.out.println("歸還失敗：查無此設備代碼 [" + code + "]。");
            return;
        }

        if (equipment.returnEquipment()) {
            System.out.println("歸還成功！設備 [" + equipment.getName() + "] 已恢復為「可借用」。");
        } else {
            System.out.println("歸還失敗：該設備目前本來就是「可借用」狀態，無需歸還。");
        }
    }

    public static void listAvailableEquipment(ArrayList<Equipment> equipments) {
        System.out.println("\n--- 目前可借用設備列表 ---");
        int count = 0;
        for (Equipment equipment : equipments) {
            if (equipment.isAvailable()) {
                System.out.println(equipment);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("目前沒有任何可借用的設備。");
        }
    }

    public static Equipment findEquipmentByCode(ArrayList<Equipment> equipments, String code) {
        if (code.isEmpty()) {
            return null;
        }
        for (Equipment equipment : equipments) {
            if (equipment.getCode().equalsIgnoreCase(code)) {
                return equipment;
            }
        }
        return null;
    }
}
