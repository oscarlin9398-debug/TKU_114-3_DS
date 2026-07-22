import java.util.ArrayDeque;
import java.util.Deque;

public class DeliveryFlowDemo {
    public static void main(String[] args) {
        Deque<String> waiting = new ArrayDeque<>();
        Deque<String> completed = new ArrayDeque<>();

        waiting.offer("D001");
        waiting.offer("D002");
        waiting.offer("D003");

        processNext(waiting, completed);
        processNext(waiting, completed);
        undoLast(waiting, completed);

        System.out.println("等待：" + waiting);
        System.out.println("完成紀錄：" + completed);
    }

    public static void processNext(
        Deque<String> waiting,
        Deque<String> completed
    ) {
        String task = waiting.poll();
        if (task != null) {
            completed.push(task);
        }
    }

    public static void undoLast(
        Deque<String> waiting,
        Deque<String> completed
    ) {
        String task = completed.poll();
        if (task != null) {
            waiting.offer(task);
        }
    }
}
