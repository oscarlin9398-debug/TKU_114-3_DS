import java.util.ArrayDeque;
import java.util.Deque;

public class ServiceQueueSimulation {
    public static void main(String[] args) {
        Deque<String> waiting = new ArrayDeque<>();
        waiting.offer("A001 Amy");
        waiting.offer("A002 Ben");
        waiting.offer("A003 Cara");

        serveNext(waiting);
        serveNext(waiting);
        System.out.println("下一位：" + waiting.peek());
        System.out.println("等待人數：" + waiting.size());
    }

    public static void serveNext(Deque<String> waiting) {
        String customer = waiting.poll();
        if (customer == null) {
            System.out.println("目前無人等待");
        } else {
            System.out.println("服務：" + customer);
        }
    }
}
