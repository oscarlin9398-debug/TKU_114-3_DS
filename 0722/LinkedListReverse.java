public class LinkedListReverse {
    public static void main(String[] args) {
        System.out.println("--- 1. 測試多節點反轉 (10 -> 20 -> 30 -> 40) ---");
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);

        System.out.print("反轉前：");
        printList(head);

        head = reverse(head);

        System.out.print("反轉後：");
        printList(head);

        System.out.println("\n--- 2. 邊界測試：單一節點反轉 (100) ---");
        IntNode singleHead = new IntNode(100);
        System.out.print("反轉前：");
        printList(singleHead);

        singleHead = reverse(singleHead);

        System.out.print("反轉後：");
        printList(singleHead);

        System.out.println("\n--- 3. 邊界測試：空串列反轉 ---");
        IntNode nullHead = null;
        System.out.print("反轉前：");
        printList(nullHead);

        nullHead = reverse(nullHead);

        System.out.print("反轉後：");
        printList(nullHead);
    }

    public static IntNode reverse(IntNode head) {
        if (head == null) {
            System.out.println("【訊息】串列為空，無需反轉。");
            return null;
        }

        if (head.next == null) {
            System.out.println("【訊息】串列僅有一個節點，反轉後保持不變。");
            return head;
        }

        IntNode previous = null;
        IntNode current = head;

        while (current != null) {
            IntNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
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
