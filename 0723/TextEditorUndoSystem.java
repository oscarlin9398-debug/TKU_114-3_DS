import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorUndoSystem {

    private String text = "";
    private Deque<String> history = new ArrayDeque<>();

    public void append(String value) {
        history.push(text);
        text += value;
        System.out.println("新增文字：「" + value + "」");
    }

    public void delete(int count) {
        if (count <= 0) {
            return;
        }
        history.push(text);
        if (count >= text.length()) {
            text = "";
        } else {
            text = text.substring(0, text.length() - count);
        }
        System.out.println("刪除最後 " + count + " 個字元");
    }

    public boolean undo() {
        if (history.isEmpty()) {
            System.out.println("Undo 失敗：目前沒有歷史紀錄可復原");
            return false;
        }
        text = history.pop();
        System.out.println("執行 Undo 成功");
        return true;
    }

    public void showText() {
        System.out.println("目前文字內容：「" + text + "」");
    }

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("=== 開始進行文字編輯與連續 Undo 測試 ===");

        editor.showText();
        editor.undo();

        System.out.println("\n--- 開始編輯操作 ---");
        editor.append("Java");
        editor.showText();

        editor.append(" Data Structure");
        editor.showText();

        editor.append(" - Stack Demo");
        editor.showText();

        editor.delete(13);
        editor.showText();

        System.out.println("\n--- 開始連續 Undo 三次驗證 ---");
        System.out.print("[第一次 Undo] ");
        editor.undo();
        editor.showText();

        System.out.print("[第二次 Undo] ");
        editor.undo();
        editor.showText();

        System.out.print("[第三次 Undo] ");
        editor.undo();
        editor.showText();

        System.out.println("\n--- 邊界測試 (多餘的 Undo) ---");
        System.out.print("[第四次 Undo] ");
        editor.undo();
        editor.showText();
    }
}
