import java.util.ArrayDeque;
import java.util.Deque;

public class StackQueueDecisionDemo {
    public static void main(String[] args) {
        Deque<String> backHistory = new ArrayDeque<>();
        backHistory.push("Home");
        backHistory.push("Courses");
        backHistory.push("Java");

        Deque<String> uploadQueue = new ArrayDeque<>();
        uploadQueue.offer("fileA.txt");
        uploadQueue.offer("fileB.txt");
        uploadQueue.offer("fileC.txt");

        System.out.println("返回上一頁：" + backHistory.pop());
        System.out.println("先上傳：" + uploadQueue.poll());
    }
}
