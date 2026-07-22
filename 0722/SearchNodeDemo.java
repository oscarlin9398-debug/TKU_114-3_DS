public class SearchNodeDemo {
    public static void main(String[] args) {
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);

        System.out.println("節點數：" + count(head));
        System.out.println("包含 20：" + contains(head, 20));
        System.out.println("包含 99：" + contains(head, 99));
    }

    public static int count(IntNode head) {
        int count = 0;
        IntNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
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
}
