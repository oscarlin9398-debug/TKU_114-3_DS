import java.util.ArrayDeque;
import java.util.Deque;

public class CounterServiceSystem {

    private Deque<String> waitingQueue = new ArrayDeque<>();
    private Deque<String> historyStack = new ArrayDeque<>();

    public void takeNumber(String number, String name) {
        String customer = number + " " + name;
        waitingQueue.offer(customer);
        System.out.println("取號成功：" + customer);
    }

    public void callNext() {
        String customer = waitingQueue.poll();
        if (customer == null) {
            System.out.println("叫號失敗：目前沒有等待的顧客");
            return;
        }

        historyStack.push(customer);
        System.out.println("請 " + customer + " 到櫃台辦理");
    }

    public void peekNext() {
        String customer = waitingQueue.peek();
        if (customer == null) {
            System.out.println("下一位：無人等待");
        } else {
            System.out.println("下一位：" + customer);
        }
    }

    public void getWaitingCount() {
        System.out.println("目前等待人數：" + waitingQueue.size());
    }

    public void showHistory() {
        System.out.println("已處理紀錄 (最新到最舊)：" + historyStack);
    }

    public static void main(String[] args) {
        CounterServiceSystem system = new CounterServiceSystem();

        System.out.println("=== 櫃台叫號系統測試 ===");

        system.peekNext();
        system.callNext();

        system.takeNumber("A001", "Amy");
        system.takeNumber("A002", "Ben");
        system.takeNumber("A003", "Cara");

        system.getWaitingCount();
        system.peekNext();

        system.callNext();
        system.callNext();

        system.getWaitingCount();
        system.peekNext();

        system.showHistory();

        system.callNext();
        system.callNext();
    }
}
