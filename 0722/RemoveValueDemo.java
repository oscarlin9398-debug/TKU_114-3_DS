public class RemoveValueDemo {
    public static void main(String[] args) {
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);

        head = removeValue(head, 20);
        printList(head);
    }

    public static IntNode removeValue(IntNode head, int target) {
        if (head == null) {
            return null;
        }
        if (head.data == target) {
            return head.next;
        }
        IntNode previous = head;
        IntNode current = head.next;
        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                break;
            }
            previous = current;
            current = current.next;
        }
        return head;
    }

    public static void printList(IntNode head) {
        IntNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
