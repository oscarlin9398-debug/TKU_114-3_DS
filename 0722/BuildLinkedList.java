public class BuildLinkedList {
    public static void main(String[] args) {
        IntNode head = new IntNode(10);
        IntNode node2 = new IntNode(20);
        IntNode node3 = new IntNode(30);
        IntNode node4 = new IntNode(40);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;

        printListAndCalculate(head);

        System.out.println("\n--- 邊界測試：空串列 ---");
        printListAndCalculate(null);
    }

    public static void printListAndCalculate(IntNode head) {
        if (head == null) {
            System.out.println("串列內容：null (空串列)");
            System.out.println("總節點數：0");
            System.out.println("數值總和：0");
            return;
        }

        IntNode current = head;
        int count = 0;
        int sum = 0;

        System.out.print("串列內容：");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            count++;
            sum += current.data;
            current = current.next;
        }
        System.out.println(" -> null");

        System.out.println("總節點數：" + count);
        System.out.println("數值總和：" + sum);
    }
}
