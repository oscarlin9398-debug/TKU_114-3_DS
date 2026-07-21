import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        while (true) {
            System.out.println("\n=== 聯絡人管理系統 ===");
            System.out.println("1. 新增聯絡人");
            System.out.println("2. 搜尋聯絡人");
            System.out.println("3. 修改聯絡人電話");
            System.out.println("4. 刪除聯絡人");
            System.out.println("5. 印出完整清單");
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
                    addContact(scanner, contacts);
                    break;
                case 2:
                    searchContact(scanner, contacts);
                    break;
                case 3:
                    updatePhone(scanner, contacts);
                    break;
                case 4:
                    deleteContact(scanner, contacts);
                    break;
                case 5:
                    listAllContacts(contacts);
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

    public static void addContact(Scanner scanner, ArrayList<Contact> contacts) {
        System.out.print("請輸入聯絡人代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("錯誤：代碼不可為空白！");
            return;
        }

        if (findContactByCode(contacts, code) != null) {
            System.out.println("錯誤：代碼 [" + code + "] 已存在，不可重複新增！");
            return;
        }

        System.out.print("請輸入姓名：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("錯誤：姓名不可為空白！");
            return;
        }

        System.out.print("請輸入電話：");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入電子郵件：");
        String email = scanner.nextLine().trim();

        contacts.add(new Contact(code, name, phone, email));
        System.out.println("新增成功：[" + name + "]");
    }

    public static void searchContact(Scanner scanner, ArrayList<Contact> contacts) {
        System.out.print("請輸入要搜尋的代碼或姓名：");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("錯誤：搜尋關鍵字不可為空白！");
            return;
        }

        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getCode().equalsIgnoreCase(keyword) || contact.getName().equalsIgnoreCase(keyword)) {
                System.out.println("找到聯絡人 -> " + contact);
                found = true;
            }
        }

        if (!found) {
            System.out.println("未找到符合 [" + keyword + "] 的聯絡人。");
        }
    }

    public static void updatePhone(Scanner scanner, ArrayList<Contact> contacts) {
        System.out.print("請輸入要修改電話的聯絡人代碼：");
        String code = scanner.nextLine().trim();

        Contact contact = findContactByCode(contacts, code);
        if (contact == null) {
            System.out.println("修改失敗：找不到代碼為 [" + code + "] 的聯絡人。");
            return;
        }

        System.out.println("目前聯絡人資訊：" + contact);
        System.out.print("請輸入新的電話號碼：");
        String newPhone = scanner.nextLine().trim();

        if (newPhone.isEmpty()) {
            System.out.println("修改失敗：新電話號碼不可為空白！");
            return;
        }

        contact.setPhone(newPhone);
        System.out.println("修改成功！[" + contact.getName() + "] 的電話已更新為：" + newPhone);
    }

    public static void deleteContact(Scanner scanner, ArrayList<Contact> contacts) {
        System.out.print("請輸入要刪除的聯絡人代碼：");
        String code = scanner.nextLine().trim();

        Contact contact = findContactByCode(contacts, code);
        if (contact == null) {
            System.out.println("刪除失敗：找不到代碼為 [" + code + "] 的聯絡人。");
            return;
        }

        contacts.remove(contact);
        System.out.println("刪除成功：已移除聯絡人 [" + contact.getName() + "]。");
    }

    public static void listAllContacts(ArrayList<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("目前通訊錄為空，沒有任何聯絡人資料。");
            return;
        }

        System.out.println("\n--- 完整聯絡人清單 (共 " + contacts.size() + " 筆) ---");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i));
        }
    }

    public static Contact findContactByCode(ArrayList<Contact> contacts, String code) {
        if (code.isEmpty()) {
            return null;
        }
        for (Contact contact : contacts) {
            if (contact.getCode().equalsIgnoreCase(code)) {
                return contact;
            }
        }
        return null;
    }
}
