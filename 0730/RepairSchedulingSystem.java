import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RepairSchedulingSystem {

    private List<RepairTask> allTasks = new ArrayList<>();
    private Queue<RepairTask> waitingQueue = new ArrayDeque<>();
    private Deque<RepairTask> completedStack = new ArrayDeque<>();
    private Set<String> taskIdSet = new HashSet<>();

    public boolean addRepairTask(String taskId, String deviceName, int priority) {
        if (taskIdSet.contains(taskId)) {
            System.out.printf("  [系統警告] 新增失敗！工作編號 %s 已存在，無法重複建立。%n", taskId);
            return false;
        }

        RepairTask task = new RepairTask(taskId, deviceName, priority);
        allTasks.add(task);
        waitingQueue.offer(task);
        taskIdSet.add(taskId);
        System.out.printf("  [系統成功] 工作 [%s - %s] (優先級: P%d) 已成功登記並進入等待佇列。%n", taskId, deviceName, priority);
        return true;
    }

    public void processNextTask() {
        System.out.println("--- 處理下一筆維修工作 (Queue -> Stack) ---");
        RepairTask task = waitingQueue.poll();
        if (task == null) {
            System.out.println("  [系統提示] 目前沒有等待維修的工作 (Queue is empty)。");
        } else {
            task.setStatus("已完成");
            completedStack.push(task);
            System.out.println("  [完成維修] " + task);
        }
    }

    public void undoLastCompletedTask() {
        System.out.println("--- 復原上一筆已完成工作 (Stack -> Queue) ---");
        RepairTask task = completedStack.poll();
        if (task == null) {
            System.out.println("  [系統提示] 沒有可供復原的已完成工作 (Stack is empty)。");
        } else {
            task.setStatus("等待中");
            waitingQueue.offer(task);
            System.out.println("  [成功復原] 工作 " + task.getTaskId() + " 已重新回到等待佇列。");
        }
    }

    public void sortAllTasksByPriority() {
        System.out.println("--- 所有工作依優先等級降冪排列 (穩定 Merge Sort) ---");
        if (allTasks.isEmpty()) {
            System.out.println("  [系統資訊] 目前無任何工作紀錄。");
            return;
        }

        RepairAlgorithms.sortByPriorityDescendingStable(allTasks, 0, allTasks.size() - 1);
        for (RepairTask task : allTasks) {
            System.out.println("  " + task);
        }
    }

    public void searchTaskById(String taskId) {
        System.out.printf("--- 依工作編號搜尋 [%s] ---%n", taskId);
        RepairTask task = RepairAlgorithms.searchById(allTasks, taskId);
        if (task != null) {
            System.out.println("  [搜尋成功] " + task);
        } else {
            System.out.printf("  [搜尋失敗] 查無工作編號 [%s]。%n", taskId);
        }
    }

    public void searchTaskByDevice(String deviceName) {
        System.out.printf("--- 依設備名稱搜尋 [%s] ---%n", deviceName);
        List<RepairTask> results = RepairAlgorithms.searchByDeviceName(allTasks, deviceName);
        if (results.isEmpty()) {
            System.out.printf("  [搜尋結果] 查無含有設備名稱 [%s] 的工作。%n", deviceName);
        } else {
            System.out.printf("  找到 %d 筆相關工作：%n", results.size());
            for (RepairTask task : results) {
                System.out.println("    " + task);
            }
        }
    }

    public void displaySystemStatistics() {
        System.out.println("==================================================================");
        System.out.println("                     系 統 當 前 統 計 數 據");
        System.out.println("==================================================================");
        System.out.printf("  總工作筆數:   %d 筆%n", allTasks.size());
        System.out.printf("  等待維修筆數: %d 筆 (Queue)%n", waitingQueue.size());
        System.out.printf("  已完成筆數:   %d 筆 (Stack)%n", completedStack.size());
        System.out.println("------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        RepairSchedulingSystem system = new RepairSchedulingSystem();

        System.out.println("==================================================================");
        System.out.println("         課 後 作 業 二 ： 維 修 工 作 排 程 系 統");
        System.out.println("==================================================================\n");

        System.out.println("=== 1. 空資料庫與邊界情境測試 ===");
        system.displaySystemStatistics();
        system.processNextTask();
        system.undoLastCompletedTask();
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 2. 新增工作與重複編號測試 ===");
        system.addRepairTask("TSK001", "工業伺服器 A", 3);
        system.addRepairTask("TSK002", "產線手臂 B", 5);
        system.addRepairTask("TSK003", "控制螢幕 C", 3);
        system.addRepairTask("TSK004", "不斷電系統 D", 5);
        system.addRepairTask("TSK005", "網路交換器 E", 1);

        // 重複編號測試
        system.addRepairTask("TSK002", "重複設備", 4);
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 3. 處理維修與 Stack 復原功能測試 ===");
        system.processNextTask(); // 處理 TSK001
        system.processNextTask(); // 處理 TSK002
        system.displaySystemStatistics();

        system.undoLastCompletedTask(); // 復原 TSK002
        system.displaySystemStatistics();
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 4. 穩定 Merge Sort 排序 (優先級降冪，同等級保持登記順序) ===");
        system.sortAllTasksByPriority();
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 5. 搜尋功能測試 (編號與設備名稱) ===");
        system.searchTaskById("TSK003");
        system.searchTaskById("TSK999"); // 不存在
        system.searchTaskByDevice("伺服器");
        system.searchTaskByDevice("發電機"); // 不存在
        System.out.println("------------------------------------------------------------------\n");

        system.displaySystemStatistics();
    }
}
