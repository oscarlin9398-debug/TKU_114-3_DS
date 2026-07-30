import java.util.ArrayDeque;
import java.util.Deque;

public class Q03_UndoStackFix {
    public static void main(String[] args) {
        Deque<String> history = new ArrayDeque<>();
        history.push("Open file");
        history.push("Type title");
        history.push("Delete line");
        System.out.println("最近操作：" + peekLatest(history));
        System.out.println("復原：" + undo(history));
        System.out.println("復原：" + undo(history));
        System.out.println("剩餘最近操作：" + peekLatest(history));
    }

    public static String undo(Deque<String> history) {
        if (history.isEmpty()) {
            return "EMPTY";
        }
        return history.pop();
    }

    public static String peekLatest(Deque<String> history) {
        if (history.isEmpty()) {
            return "EMPTY";
        }
        return history.peek();
    }
}
