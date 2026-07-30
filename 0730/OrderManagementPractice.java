import java.util.ArrayDeque;
import java.util.ArrayList;

import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class OrderManagementPractice {

    private List<Order> mainOrders = new ArrayList<>();
    private Queue<Order> pendingQueue = new ArrayDeque<>();
    private Deque<Order> completedStack = new ArrayDeque<>();
    private Set<String> orderIdSet = new HashSet<>();

    public boolean addOrder(String orderId, String customerName, double amount) {
        if (orderIdSet.contains(orderId)) {
            System.out.printf("  [系統警告] 新增失敗！訂單編號 %s 已存在，無法重複建立。%n", orderId);
            return false;
        }

        Order newOrder = new Order(orderId, customerName, amount);
        mainOrders.add(newOrder);
        pendingQueue.offer(newOrder);
        orderIdSet.add(orderId);
        System.out.printf("  [系統成功] 訂單 %s (%s, $%.2f) 已成功建立並進入待處理佇列。%n", orderId, customerName, amount);
        return true;
    }

    public void peekNextPendingOrder() {
        System.out.println("--- 查看下一筆待處理訂單 (Peek Queue) ---");
        Order nextOrder = pendingQueue.peek();
        if (nextOrder == null) {
            System.out.println("  [佇列資訊] 待處理佇列為空 (Queue is empty)，無下一筆待處理訂單。");
        } else {
            System.out.println("  下一筆待處理: " + nextOrder);
        }
    }

    public void processNextOrder() {
        System.out.println("--- 處理下一筆訂單 (Poll Queue & Push Stack) ---");
        Order order = pendingQueue.poll();
        if (order == null) {
            System.out.println("  [錯誤提示] 無法處理！待處理佇列為空 (Queue is empty)。");
        } else {
            completedStack.push(order);
            System.out.println("  [已完成處理] " + order);
        }
    }

    public void peekLastCompletedOrder() {
        System.out.println("--- 查看最後一筆已完成訂單 (Peek Stack) ---");
        Order lastCompleted = completedStack.peek();
        if (lastCompleted == null) {
            System.out.println("  [堆疊資訊] 已完成堆疊為空 (Stack is empty)，無已完成紀錄。");
        } else {
            System.out.println("  最後完成紀錄: " + lastCompleted);
        }
    }

    public void displayAllSortedByAmount() {
        System.out.println("--- 主資料列表 (依金額降冪 Merge Sort 排序) ---");
        if (mainOrders.isEmpty()) {
            System.out.println("  目前主資料無任何訂單紀錄。");
            return;
        }

        OrderAlgorithms.sortByAmountDescending(mainOrders, 0, mainOrders.size() - 1);
        for (Order order : mainOrders) {
            System.out.println("  " + order);
        }
    }

    public void searchOrdersByCustomer(String customerName) {
        System.out.printf("--- 依顧客姓名 [%s] 搜尋訂單 ---%n", customerName);
        List<Order> results = OrderAlgorithms.searchByCustomerName(mainOrders, customerName);

        if (results.isEmpty()) {
            System.out.printf("  [搜尋結果] 查無顧客 [%s] 的任何訂單紀錄。%n", customerName);
        } else {
            System.out.printf("  找到 %d 筆相關訂單:%n", results.size());
            for (Order order : results) {
                System.out.println("    " + order);
            }
        }
    }

    public static void main(String[] args) {
        OrderManagementPractice manager = new OrderManagementPractice();

        System.out.println("==================================================================");
        System.out.println("            課 堂 實 作 四 ： 擴 充 訂 單 管 理 系 統");
        System.out.println("==================================================================\n");

        System.out.println("=== 1. 空 Queue 與空 Stack 邊界操作測試 ===");
        manager.peekNextPendingOrder();
        manager.processNextOrder();
        manager.peekLastCompletedOrder();
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 2. 正常新增訂單與重複編號測試 ===");
        manager.addOrder("ORD001", "Alice", 2500.0);
        manager.addOrder("ORD002", "Bob", 1200.0);
        manager.addOrder("ORD003", "Alice", 4800.0);
        manager.addOrder("ORD004", "Charlie", 3100.0);

        // 重複編號測試
        manager.addOrder("ORD002", "DuplicateBob", 9999.0);
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 3. 佇列 (Queue) 與堆疊 (Stack) 流程處理測試 ===");
        manager.peekNextPendingOrder();
        manager.processNextOrder();
        manager.processNextOrder();
        manager.peekNextPendingOrder();
        manager.peekLastCompletedOrder();
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 4. 顧客姓名搜尋測試 (多筆與找不到資料) ===");
        manager.searchOrdersByCustomer("Alice");
        manager.searchOrdersByCustomer("David");
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 5. 主資料金額降冪 Merge Sort 排序顯示 ===");
        manager.displayAllSortedByAmount();
    }
}
