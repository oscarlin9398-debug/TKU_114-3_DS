import java.util.ArrayDeque;
import java.util.Deque;

public class DeliveryProcessingSystem {

    private Deque<DeliveryTask> waitingQueue = new ArrayDeque<>();
    private Deque<DeliveryTask> completedStack = new ArrayDeque<>();

    public void addTask(String taskId, String destination) {
        DeliveryTask task = new DeliveryTask(taskId, destination);
        waitingQueue.offer(task);
        System.out.println("新增配送工作：" + task);
    }

    public void processNext() {
        DeliveryTask task = waitingQueue.poll();
        if (task == null) {
            System.out.println("處理失敗：目前沒有待配送工作");
            return;
        }

        completedStack.push(task);
        System.out.println("完成配送工作：" + task);
    }

    public void peekNext() {
        DeliveryTask task = waitingQueue.peek();
        if (task == null) {
            System.out.println("下一筆工作：無待處理工作");
        } else {
            System.out.println("下一筆工作：" + task);
        }
    }

    public void undoLastCompleted() {
        DeliveryTask task = completedStack.poll();
        if (task == null) {
            System.out.println("復原失敗：沒有已完成的工作紀錄");
            return;
        }

        waitingQueue.offer(task);
        System.out.println("已復原工作並放回佇列尾端：" + task);
    }

    public void showStatus() {
        System.out.println("\n=== 目前系統狀態 ===");
        System.out.println("等待配送數：" + waitingQueue.size());
        System.out.println("已完成配送數：" + completedStack.size());
        System.out.println("待配送清單 (Front -> Rear)：" + waitingQueue);
        System.out.println("完成紀錄清單 (Top -> Bottom)：" + completedStack);
        System.out.println("====================\n");
    }

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        System.out.println("=== 配送工作流程系統測試 ===");

        system.peekNext();
        system.processNext();
        system.undoLastCompleted();

        System.out.println("\n--- 新增 3 筆配送工作 ---");
        system.addTask("D101", "台北市信義區");
        system.addTask("D102", "新北市板橋區");
        system.addTask("D103", "高雄市鳳山區");

        system.showStatus();

        System.out.println("--- 處理前兩筆工作 ---");
        system.processNext();
        system.processNext();

        system.showStatus();

        System.out.println("--- 執行復原最近一次完成的工作 ---");
        system.undoLastCompleted();

        system.showStatus();

        System.out.println("--- 繼續處理完所有工作 ---");
        system.processNext();
        system.processNext();

        system.showStatus();

        System.out.println("--- 邊界測試 (對空結構進行叫號與復原) ---");
        system.processNext();
        system.undoLastCompleted();
        system.undoLastCompleted();
        system.undoLastCompleted();
    }
}
