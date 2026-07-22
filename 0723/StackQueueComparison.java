import java.util.ArrayDeque;
import java.util.Deque;

public class StackQueueComparison {
    public static void main(String[] args) {
        Deque<String> stack = new ArrayDeque<>();
        Deque<String> queue = new ArrayDeque<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        queue.offer("A");
        queue.offer("B");
        queue.offer("C");

        System.out.println("Stack 先取出：" + stack.pop());
        System.out.println("Queue 先取出：" + queue.poll());
    }
}
