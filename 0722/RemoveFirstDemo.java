public class RemoveFirstDemo {
    public static void main(String[] args) {
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);

        if (head != null) {
            int removed = head.data;
            head = head.next;
            System.out.println("刪除：" + removed);
        }

        System.out.println("新的 head：" + head.data);
    }
}
