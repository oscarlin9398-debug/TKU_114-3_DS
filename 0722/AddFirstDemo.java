public class AddFirstDemo {
    public static void main(String[] args) {
        IntNode head = new IntNode(20);
        head.next = new IntNode(30);

        IntNode newNode = new IntNode(10);
        newNode.next = head;
        head = newNode;

        printList(head);
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
