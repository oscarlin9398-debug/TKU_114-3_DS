public class NodeReferenceDemo {
    public static void main(String[] args) {
        IntNode first = new IntNode(10);
        IntNode second = new IntNode(20);

        first.next = second;

        System.out.println("first data：" + first.data);
        System.out.println("next data：" + first.next.data);
        System.out.println("second next：" + second.next);
    }
}
