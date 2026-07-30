public class Q02_NodeSummary {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(12);
        head.next = new Node(7);
        head.next.next = new Node(20);
        head.next.next.next = new Node(5);

        System.out.println("節點數：" + countNodes(head));
        System.out.println("資料總和：" + sumValues(head));
        System.out.println("空串列節點數：" + countNodes(null));
    }

    public static int countNodes(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static int sumValues(Node head) {
        int total = 0;
        Node current = head;
        while (current != null) {
            total += current.data;
            current = current.next;
        }
        return total;
    }
}
