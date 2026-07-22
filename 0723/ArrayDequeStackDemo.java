import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeStackDemo {
    public static void main(String[] args) {
        Deque<String> history = new ArrayDeque<>();

        history.push("Open file");
        history.push("Type title");
        history.push("Delete line");

        System.out.println("最近操作：" + history.peek());
        System.out.println("復原：" + history.pop());
        System.out.println("復原後頂端：" + history.peek());
        System.out.println("剩餘紀錄：" + history);
    }
}
