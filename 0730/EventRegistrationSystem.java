import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class EventRegistrationSystem {

    private final int capacity;
    private List<Registration> allRegistrations = new ArrayList<>();
    private Queue<Registration> waitingQueue = new ArrayDeque<>();
    private Deque<Registration> cancelledStack = new ArrayDeque<>();
    private Set<String> regIdSet = new HashSet<>();
    private int confirmedCount = 0;

    public EventRegistrationSystem(int capacity) {
        this.capacity = capacity;
    }

    public boolean register(String regId, String name, String phone) {
        if (regIdSet.contains(regId)) {
            System.out.printf("  [系統警告] 報名失敗！報名編號 %s 已存在，重複編號不可註冊。%n", regId);
            return false;
        }

        Registration reg = new Registration(regId, name, phone);
        regIdSet.add(regId);
        allRegistrations.add(reg);

        if (confirmedCount < capacity) {
            confirmedCount++;
            System.out.printf("  [報名成功] [%s - %s] 已成功取得正取名額 (正取: %d/%d)。%n",
                    regId, name, confirmedCount, capacity);
        } else {
            reg.setStatus("候補中");
            waitingQueue.offer(reg);
            System.out.printf("  [額滿轉候補] 正取已滿 (%d/%d)，[%s - %s] 已進入候補佇列 (候補順序: 第 %d 位)。%n",
                    capacity, capacity, regId, name, waitingQueue.size());
        }
        return true;
    }

    public void cancelRegistration(String regId) {
        System.out.printf("--- 取消報名申請 [%s] ---%n", regId);

        Registration target = null;
        for (Registration reg : allRegistrations) {
            if (reg.getRegId().equalsIgnoreCase(regId)) {
                target = reg;
                break;
            }
        }

        if (target == null) {
            System.out.printf("  [取消失敗] 查無報名編號 [%s] 的紀錄，無法辦理取消。%n", regId);
            return;
        }

        if (target.getStatus().equals("已取消")) {
            System.out.printf("  [取消失敗] 報名編號 [%s] 先前已辦理過取消。%n", regId);
            return;
        }

        String oldStatus = target.getStatus();
        target.setStatus("已取消");
        cancelledStack.push(target);
        System.out.printf("  [辦理成功] 報名編號 [%s - %s] (原狀態: %s) 已成功辦理取消並存入復原堆疊。%n",
                target.getRegId(), target.getName(), oldStatus);

        if (oldStatus.equals("正取")) {
            confirmedCount--;
            System.out.println("  [釋出名額] 正取出現缺額，檢查候補佇列...");

            Registration candidate = waitingQueue.poll();
            if (candidate != null) {
                candidate.setStatus("正取");
                confirmedCount++;
                System.out.printf("  [候補遞補] 候補人員 [%s - %s] 已自動遞補為正取名額！(正取: %d/%d)%n",
                        candidate.getRegId(), candidate.getName(), confirmedCount, capacity);
            } else {
                System.out.println("  [候補資訊] 目前候補佇列為空 (Empty Queue)，無人需要遞補。");
            }
        } else if (oldStatus.equals("候補中")) {
            waitingQueue.remove(target);
            System.out.printf("  [佇列調整] [%s] 自候補佇列中移出，目前候補人數: %d 位。%n",
                    target.getRegId(), waitingQueue.size());
        }
    }

    public void undoLastCancellation() {
        System.out.println("--- 復原最近一次取消紀錄 (Stack -> Restore) ---");
        Registration reg = cancelledStack.poll();

        if (reg == null) {
            System.out.println("  [復原失敗] 沒有任何取消紀錄可供復原 (Stack is empty)。");
            return;
        }

        if (confirmedCount < capacity) {
            reg.setStatus("正取");
            confirmedCount++;
            System.out.printf("  [復原成功] 名額未滿，[%s - %s] 已順利恢復為【正取】身份。%n",
                    reg.getRegId(), reg.getName());
        } else {
            reg.setStatus("候補中");
            waitingQueue.offer(reg);
            System.out.printf("  [復原成功] 正取已滿，[%s - %s] 已重新加入【候補佇列】末端。%n",
                    reg.getRegId(), reg.getName());
        }
    }

    public void sortAndDisplayById() {
        System.out.println("--- 依報名編號排序顯示 (Merge Sort) ---");
        if (allRegistrations.isEmpty()) {
            System.out.println("  目前無任何報名資料。");
            return;
        }

        RegistrationAlgorithms.sortByIdAscending(allRegistrations, 0, allRegistrations.size() - 1);
        for (Registration reg : allRegistrations) {
            System.out.println("  " + reg);
        }
    }

    public void searchByIdBinary(String targetId) {
        System.out.printf("--- 依編號查詢 (Binary Search) [%s] ---%n", targetId);
        if (allRegistrations.isEmpty()) {
            System.out.println("  [搜尋失敗] 目前報名資料庫為空。");
            return;
        }

        RegistrationAlgorithms.sortByIdAscending(allRegistrations, 0, allRegistrations.size() - 1);
        int index = RegistrationAlgorithms.binarySearchById(allRegistrations, targetId);

        if (index != -1) {
            System.out.printf("  [搜尋成功] 位於索引位置 %d：%n    %s%n", index, allRegistrations.get(index));
        } else {
            System.out.printf("  [搜尋失敗] 查無報名編號 [%s] 的資料。%n", targetId);
        }
    }

    public void searchByNameSequential(String name) {
        System.out.printf("--- 依姓名查詢 (Sequential Search) [%s] ---%n", name);
        List<Registration> results = RegistrationAlgorithms.sequentialSearchByName(allRegistrations, name);

        if (results.isEmpty()) {
            System.out.printf("  [搜尋結果] 查無姓名為 [%s] 的任何報名紀錄。%n", name);
        } else {
            System.out.printf("  找到 %d 筆符合姓名的報名紀錄：%n", results.size());
            for (Registration reg : results) {
                System.out.println("    " + reg);
            }
        }
    }

    public void displayStatusSummary() {
        System.out.println("==================================================================");
        System.out.println("                     活 動 報 名 當 前 統 計");
        System.out.println("==================================================================");
        System.out.printf("  活動上限人數: %d 位%n", capacity);
        System.out.printf("  目前正取人數: %d 位%n", confirmedCount);
        System.out.printf("  候補佇列人數: %d 位 (Queue)%n", waitingQueue.size());
        System.out.printf("  取消紀錄筆數: %d 筆 (Stack)%n", cancelledStack.size());
        System.out.printf("  總登記筆數:   %d 筆%n", allRegistrations.size());
        System.out.println("------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        // 設定活動正取上限為 3 人
        EventRegistrationSystem system = new EventRegistrationSystem(3);

        System.out.println("==================================================================");
        System.out.println("         課 後 作 業 四 ： 活 動 報 名 與 候 補 系 統");
        System.out.println("==================================================================\n");

        System.out.println("=== 1. 空佇列、空堆疊與邊界取消情境測試 ===");
        system.displayStatusSummary();
        system.undoLastCancellation();
        system.cancelRegistration("REG999"); // 取消不存在編號
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 2. 正常報名、重複編號與額滿轉候補測試 ===");
        system.register("REG804", "Alice", "0912-345678");
        system.register("REG105", "Bob", "0923-456789");
        system.register("REG930", "Charlie", "0934-567890");

        // 重複編號測試
        system.register("REG105", "DuplicateBob", "0999-000000");

        // 超出容量，進入候補 Queue
        system.register("REG212", "David", "0945-678901");
        system.register("REG501", "Eve", "0956-789012");
        system.displayStatusSummary();
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 3. 取消報名、自動候補遞補與復原功能測試 ===");
        // 取消正取 REG804 (Alice)，應由候補第一位 REG212 (David) 自動遞補
        system.cancelRegistration("REG804");
        system.displayStatusSummary();

        // 復原剛剛取消的 REG804 (Alice)，因正取已滿，應回歸候補 Queue
        system.undoLastCancellation();
        system.displayStatusSummary();
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 4. Merge Sort 排序與搜尋功能測試 ===");
        system.sortAndDisplayById();
        System.out.println();

        // 二分搜尋
        system.searchByIdBinary("REG105");
        system.searchByIdBinary("REG999"); // 不存在

        // 循序搜尋
        System.out.println();
        system.searchByNameSequential("David");
        system.searchByNameSequential("Frank"); // 不存在
    }
}
