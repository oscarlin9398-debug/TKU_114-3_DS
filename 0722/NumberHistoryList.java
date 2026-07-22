public class NumberHistoryList {

    private IntNode head;
    private int size;

    public NumberHistoryList() {
        this.head = null;
        this.size = 0;
    }

    public void addFirst(int value) {
        IntNode newNode = new IntNode(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(int value) {
        IntNode newNode = new IntNode(value);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        IntNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    public boolean contains(int target) {
        IntNode current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean removeValue(int target) {
        if (head == null) {
            return false;
        }
        if (head.data == target) {
            head = head.next;
            size--;
            return true;
        }

        IntNode previous = head;
        IntNode current = head.next;
        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void printList() {
        if (head == null) {
            System.out.println("串列內容：null (空串列)");
            return;
        }
        IntNode current = head;
        System.out.print("串列內容：");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void printStats() {
        System.out.println("--- 統計數據 ---");
        System.out.println("Size (節點數)：" + size);
        if (head == null) {
            System.out.println("總和：0");
            System.out.println("最大值：N/A (空串列)");
            System.out.println("最小值：N/A (空串列)");
            return;
        }

        int sum = 0;
        int max = head.data;
        int min = head.data;
        IntNode current = head;

        while (current != null) {
            int val = current.data;
            sum += val;
            if (val > max) max = val;
            if (val < min) min = val;
            current = current.next;
        }

        System.out.println("總和：" + sum);
        System.out.println("最大值：" + max);
        System.out.println("最小值：" + min);
    }

    public static void main(String[] args) {
        NumberHistoryList history = new NumberHistoryList();

        System.out.println("=== 操作 1：測試空串列狀態與統計 ===");
        history.printList();
        history.printStats();

        System.out.println("\n=== 操作 2：前端新增 20 (addFirst) ===");
        history.addFirst(20);
        history.printList();

        System.out.println("\n=== 操作 3：前端新增 10 (addFirst) ===");
        history.addFirst(10);
        history.printList();

        System.out.println("\n=== 操作 4：尾端新增 30 (addLast) ===");
        history.addLast(30);
        history.printList();

        System.out.println("\n=== 操作 5：尾端新增 40 (addLast) ===");
        history.addLast(40);
        history.printList();
        history.printStats();

        System.out.println("\n=== 操作 6：搜尋測試 (contains) ===");
        System.out.println("是否包含 30：" + history.contains(30));
        System.out.println("是否包含 99：" + history.contains(99));

        System.out.println("\n=== 操作 7：刪除中間節點 30 (removeValue) ===");
        boolean removed30 = history.removeValue(30);
        System.out.println("刪除 30 結果：" + removed30);
        history.printList();

        System.out.println("\n=== 操作 8：刪除不存在的節點 99 (removeValue) ===");
        boolean removed99 = history.removeValue(99);
        System.out.println("刪除 99 結果：" + removed99);
        history.printList();
        history.printStats();
    }
}
