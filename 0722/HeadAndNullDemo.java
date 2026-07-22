public class HeadAndNullDemo {
    public static void main(String[] args) {
        IntNode head = null;
        System.out.println("是否為空：" + (head == null));

        head = new IntNode(10);
        head.next = new IntNode(20);

        System.out.println("第一筆：" + head.data);
        System.out.println("第二筆：" + head.next.data);
        System.out.println("最後 next：" + head.next.next);
    }
}
