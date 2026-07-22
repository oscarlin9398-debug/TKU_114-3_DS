public class LinkedListReverseDemo {
    public static void main(String[] args) {
        IntLinkedList list = new IntLinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        System.out.print("反轉前：");
        list.print();
        list.reverse();
        System.out.print("反轉後：");
        list.print();
    }
}