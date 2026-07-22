import java.util.ArrayDeque;
import java.util.Deque;

public class UndoManager {
    private String text = "";
    private Deque<String> history = new ArrayDeque<>();

    public void append(String value) {
        history.push(text);
        text += value;
    }

    public boolean undo() {
        if (history.isEmpty()) {
            return false;
        }
        text = history.pop();
        return true;
    }

    public String getText() {
        return text;
    }

    public static void main(String[] args) {
        UndoManager editor = new UndoManager();
        editor.append("Java");
        editor.append(" Stack");
        editor.append(" Demo");
        System.out.println(editor.getText());

        editor.undo();
        System.out.println(editor.getText());
        editor.undo();
        System.out.println(editor.getText());
    }
}
