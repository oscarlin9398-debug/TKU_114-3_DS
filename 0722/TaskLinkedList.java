public class TaskLinkedList {

    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean containsId(String id) {
        TaskNode current = head;
        while (current != null) {
            if (current.id.equals(id)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 緊急工作：插隊到前端 (addFirst)
    public boolean addUrgentTask(String id, String description) {
        if (containsId(id)) {
            System.out.println("【錯誤】新增失敗！工作代碼 [" + id + "] 已存在。");
            return false;
        }

        TaskNode newNode = new TaskNode(id, description);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("【🚨 緊急】已插入緊急工作到前端：[" + id + "] " + description);
        return true;
    }

    // 一般工作：排到尾端 (addLast)
    public boolean addNormalTask(String id, String description) {
        if (containsId(id)) {
            System.out.println("【錯誤】新增失敗！工作代碼 [" + id + "] 已存在。");
            return false;
        }

        TaskNode newNode = new TaskNode(id, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("【📌 一般】已新增工作到尾端：[" + id + "] " + description);
        return true;
    }

    // 將指定代碼的工作標示為完成
    public boolean completeTask(String id) {
        TaskNode current = head;
        while (current != null) {
            if (current.id.equals(id)) {
                if (current.isCompleted) {
                    System.out.println("【提示】工作 [" + id + "] 本來就已經是完成狀態。");
                    return true;
                }
                current.isCompleted = true;
                System.out.println("【🎉 完成】工作 [" + id + "] " + current.description + " 已標示為完成！");
                return true;
            }
            current = current.next;
        }
        System.out.println("【警告】找不到工作代碼 [" + id + "]，無法變更狀態。");
        return false;
    }

    // 刪除工作 (含 Head 判斷與防失聯處理)
    public boolean removeTask(String id) {
        if (head == null) {
            System.out.println("【警告】工作清單為空，無法刪除。");
            return false;
        }

        // 特例處理：刪除 Head
        if (head.id.equals(id)) {
            System.out.println("【刪除】成功移除工作：[" + head.id + "] " + head.description);
            head = head.next;
            size--;
            return true;
        }

        TaskNode previous = head;
        TaskNode current = head.next;

        while (current != null) {
            if (current.id.equals(id)) {
                previous.next = current.next;
                System.out.println("【刪除】成功移除工作：[" + current.id + "] " + current.description);
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("【警告】找不到工作代碼 [" + id + "]，刪除失敗。");
        return false;
    }

    // 列出所有工作及總數、未完成數
    public void printAllTasks() {
        int pendingCount = getPendingCount();
        System.out.println("\n================ 📋 完整工作清單 ================");
        System.out.println("統計：總工作數量 = " + size + " | 未完成數量 = " + pendingCount);
        System.out.println("--------------------------------------------------");

        if (head == null) {
            System.out.println("（目前沒有任何工作）");
            System.out.println("==================================================\n");
            return;
        }

        TaskNode current = head;
        int index = 1;
        while (current != null) {
            String status = current.isCompleted ? "[V] 已完成" : "[ ] 未完成";
            System.out.println(index + ". " + status + " | [" + current.id + "] " + current.description);
            current = current.next;
            index++;
        }
        System.out.println("==================================================\n");
    }

    // 只列出「未完成」的工作
    public void printPendingTasks() {
        int pendingCount = getPendingCount();
        System.out.println("\n================ ⏳ 待辦（未完成）工作 ================");
        System.out.println("未完成工作數量：" + pendingCount + " / " + size);
        System.out.println("--------------------------------------------------");

        if (head == null || pendingCount == 0) {
            System.out.println("（太棒了！目前沒有未完成的工作）");
            System.out.println("==================================================\n");
            return;
        }

        TaskNode current = head;
        int index = 1;
        while (current != null) {
            if (!current.isCompleted) {
                System.out.println(index + ". [ ] 未完成 | [" + current.id + "] " + current.description);
                index++;
            }
            current = current.next;
        }
        System.out.println("==================================================\n");
    }

    // 計算未完成工作量
    private int getPendingCount() {
        int pendingCount = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted) {
                pendingCount++;
            }
            current = current.next;
        }
        return pendingCount;
    }
}