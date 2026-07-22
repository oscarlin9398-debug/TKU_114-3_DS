public class IntLinkedListDemo {
    public static void main(String[] args) {
        IntLinkedList list = new IntLinkedList();
        list.addFirst(20);
        list.addFirst(10);
        list.addLast(30);

        list.print();
        System.out.println("size：" + list.size());
        System.out.println("contains 20：" + list.contains(20));

        System.out.println("remove first：" + list.removeFirst());
        list.removeValue(30);
        list.print();
    }
}
