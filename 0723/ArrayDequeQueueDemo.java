import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeQueueDemo {
    public static void main(String[] args) {
        Deque<String> printJobs = new ArrayDeque<>();

        printJobs.offer("report.pdf");
        printJobs.offer("photo.png");
        printJobs.offer("slides.pptx");

        while (!printJobs.isEmpty()) {
            String job = printJobs.poll();
            System.out.println("列印：" + job);
        }

        System.out.println("剩餘工作：" + printJobs.size());
    }
}
