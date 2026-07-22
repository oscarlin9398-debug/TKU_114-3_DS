public class LinkedListTraversalDemo {
    public static void main(String[] args) {
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);

        IntNode current = head;
        int total = 0;
        while (current != null) {
            System.out.println("目前資料：" + current.data);
            total += current.data;
            current = current.next;
        }

        System.out.println("總和：" + total);
        System.out.println("head 仍是：" + head.data);
    }
}
