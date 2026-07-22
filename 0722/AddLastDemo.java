public class AddLastDemo {
    public static void main(String[] args) {
        IntNode head = null;
        head = addLast(head, 10);
        head = addLast(head, 20);
        head = addLast(head, 30);
        printList(head);
    }

    public static IntNode addLast(IntNode head, int value) {
        IntNode newNode = new IntNode(value);
        if (head == null) {
            return newNode;
        }
        IntNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
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
