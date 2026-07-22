import java.util.ArrayDeque;
import java.util.Deque;

public class QueueOperationDemo {
    public static void main(String[] args) {
        Deque<String> queue = new ArrayDeque<>();

        queue.offer("A001");
        queue.offer("A002");
        queue.offer("A003");

        System.out.println("下一位：" + queue.peek());
        System.out.println("叫號：" + queue.poll());
        System.out.println("新的下一位：" + queue.peek());
        System.out.println("等待人數：" + queue.size());
    }
}
