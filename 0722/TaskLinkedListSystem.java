public class TaskLinkedListSystem {

    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("--- 1. 邊界測試：空串列印出與刪除 ---");
        taskList.printAllTasks();
        taskList.removeTask("T001");

        System.out.println("\n--- 2. 測試一般工作新增 (addNormalTask / addLast) ---");
        taskList.addNormalTask("T001", "撰寫 Java 鏈結串列作業");
        taskList.addNormalTask("T002", "準備期末考專案報告");
        taskList.printAllTasks();

        System.out.println("\n--- 3. 測試緊急工作插隊 (addUrgentTask / addFirst) ---");
        taskList.addUrgentTask("T000", "回覆教授的重要 Email");
        taskList.printAllTasks();

        System.out.println("\n--- 4. 測試重複 ID 防護機制 ---");
        taskList.addNormalTask("T001", "重複的工作");

        System.out.println("\n--- 5. 測試標示工作完成 (completeTask) ---");
        taskList.completeTask("T000"); // 完成第一項緊急工作
        taskList.printAllTasks();

        System.out.println("\n--- 6. 測試僅印出「未完成」工作 (printPendingTasks) ---");
        taskList.printPendingTasks();

        System.out.println("\n--- 7. 測試刪除中間與頭端工作 (removeTask) ---");
        taskList.removeTask("T000"); // 刪除 head
        taskList.printAllTasks();

        System.out.println("\n--- 8. 測試刪除不存在的工作 ---");
        taskList.removeTask("T999"); // 找不到資料，size 不應變更

        System.out.println("\n--- 9. 邊界測試：持續刪除直到串列恢復為空 ---");
        taskList.removeTask("T001");
        taskList.removeTask("T002");
        taskList.printAllTasks();
        taskList.printPendingTasks();
    }
}
