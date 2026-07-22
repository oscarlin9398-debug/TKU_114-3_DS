public class LinkedListEdgeCaseDemo {
    public static void main(String[] args) {
        IntLinkedList list = new IntLinkedList();

        System.out.println("空串列刪除：" + list.removeFirst());
        System.out.println("空串列移除 10：" + list.removeValue(10));

        list.addLast(10);
        System.out.println("單一節點 size：" + list.size());
        System.out.println("刪除唯一節點：" + list.removeFirst());
        System.out.println("是否為空：" + list.isEmpty());
        list.print();
    }
}
