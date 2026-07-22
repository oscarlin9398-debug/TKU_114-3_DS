public class StackBoundaryDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(2);

        System.out.println(stack.push(10));
        System.out.println(stack.push(20));
        System.out.println("overflow：" + stack.push(30));

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("underflow：" + stack.pop());
        System.out.println("empty peek：" + stack.peek());
    }
}
