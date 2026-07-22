public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(3);

        System.out.println("push 10：" + stack.push(10));
        System.out.println("push 20：" + stack.push(20));
        System.out.println("push 30：" + stack.push(30));
        System.out.println("push 40：" + stack.push(40));
        stack.print();

        System.out.println("peek：" + stack.peek());
        System.out.println("pop：" + stack.pop());
        stack.print();
        System.out.println("size：" + stack.size());
    }
}
