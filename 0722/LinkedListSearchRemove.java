public class LinkedListSearchRemove {
    public static void main(String[] args) {
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);

        System.out.print("初始串列：");
        printList(head);

        System.out.println("\n--- 1. 測試搜尋機能 ---");
        System.out.println("包含 30：" + contains(head, 30));
        System.out.println("包含 99：" + contains(head, 99));

        System.out.println("\n--- 2. 測試刪除 中間節點 (20) ---");
        head = removeValue(head, 20);
        printList(head);

        System.out.println("\n--- 3. 測試刪除 Head 節點 (10) ---");
        head = removeValue(head, 10);
        printList(head);

        System.out.println("\n--- 4. 測試刪除 尾端節點 (40) ---");
        head = removeValue(head, 40);
        printList(head);

        System.out.println("\n--- 5. 測試刪除 找不到的資料 (99) ---");
        head = removeValue(head, 99);
        printList(head);

        System.out.println("\n--- 6. 邊界測試：刪除唯一節點 (30) ---");
        head = removeValue(head, 30);
        printList(head);

        System.out.println("\n--- 7. 邊界測試：空串列刪除 (10) ---");
        head = removeValue(head, 10);
        printList(head);
    }

    public static boolean contains(IntNode head, int target) {
        IntNode current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static IntNode removeValue(IntNode head, int target) {
        if (head == null) {
            System.out.println("【訊息】串列為空，無法執行刪除。");
            return null;
        }

        if (head.data == target) {
            System.out.println("【訊息】成功刪除 Head 節點 (" + target + ")");
            return head.next;
        }

        IntNode previous = head;
        IntNode current = head.next;

        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                System.out.println("【訊息】成功刪除節點 (" + target + ")");
                return head;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("【訊息】找不到目標資料 (" + target + ")，鏈結保持不變。");
        return head;
    }

    public static void printList(IntNode head) {
        if (head == null) {
            System.out.println("null (空串列)");
            return;
        }
        IntNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
