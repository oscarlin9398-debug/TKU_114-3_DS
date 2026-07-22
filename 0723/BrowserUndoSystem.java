import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserUndoSystem {

    private Deque<String> history = new ArrayDeque<>();

    public void visit(String url) {
        history.push(url);
        System.out.println("開啟頁面：" + url);
    }

    public void back() {
        if (history.isEmpty()) {
            System.out.println("無法返回：目前沒有歷史紀錄");
            return;
        }

        String current = history.pop();
        System.out.println("離開頁面：" + current);

        if (history.isEmpty()) {
            System.out.println("目前狀態：已關閉所有頁面");
        } else {
            System.out.println("目前頁面：" + history.peek());
        }
    }

    public void currentPage() {
        if (history.isEmpty()) {
            System.out.println("目前頁面：空白頁 (無開啟頁面)");
        } else {
            System.out.println("目前頁面：" + history.peek());
        }
    }

    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("=== 開始進行 8 次瀏覽操作測試 ===");

        browser.currentPage();
        browser.back();
        browser.visit("https://google.com");
        browser.visit("https://canvas.edu");
        browser.currentPage();
        browser.visit("https://canvas.edu/assignment/1");
        browser.back();
        browser.back();
    }
}
