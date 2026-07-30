public class EmployeeSearchSystem {
    public static void main(String[] args) {
        // 已依員工編號（ID）升冪排序的陣列
        Employee[] employees = {
            new Employee("E101", "Alice", "HR", "1011"),
            new Employee("E105", "Bob", "IT", "2021"),
            new Employee("E105", "Bob (Dup)", "IT", "2022"), // 測試重複編號
            new Employee("E112", "Charlie", "Finance", "3031"),
            new Employee("E120", "David", "IT", "2025"),
            new Employee("E135", "Eve", "Marketing", "4041")
        };

        Employee[] emptyEmployees = {};

        System.out.println("=== 測試 1：正常二分搜尋 (E120) ===");
        searchAndDisplay(employees, "E120");

        System.out.println("\n=== 測試 2：搜尋邊界 - 第一筆 (E101) ===");
        searchAndDisplay(employees, "E101");

        System.out.println("\n=== 測試 3：搜尋邊界 - 最後一筆 (E135) ===");
        searchAndDisplay(employees, "E135");

        System.out.println("\n=== 測試 4：搜尋不存在的編號 (E999) ===");
        searchAndDisplay(employees, "E999");

        System.out.println("\n=== 測試 5：搜尋空陣列 (E101) ===");
        searchAndDisplay(emptyEmployees, "E101");

        System.out.println("\n=== 測試 6：搜尋重複編號 (E105) ===");
        searchAndDisplay(employees, "E105");
    }

    public static int searchFirstById(Employee[] employees, String targetId) {
        if (employees == null || targetId == null || employees.length == 0) {
            return -1;
        }

        int low = 0;
        int high = employees.length - 1;
        int resultIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (employees[mid] == null || employees[mid].getId() == null) {
                return -1;
            }

            int cmp = employees[mid].getId().compareTo(targetId);

            if (cmp == 0) {
                resultIndex = mid;
                high = mid - 1; // 發現目標後繼續向左邊區間搜尋，確保找到第一個出現的位置（重複編號時）
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return resultIndex;
    }

    public static void searchAndDisplay(Employee[] employees, String targetId) {
        System.out.println("查詢目標編號：" + targetId);

        if (employees == null || employees.length == 0) {
            System.out.println("系統訊息：員工資料庫為空，無法執行搜尋。");
            return;
        }

        int index = searchFirstById(employees, targetId);

        if (index != -1) {
            System.out.println("找到員工資料（索引 " + index + "）：");
            System.out.println("  " + employees[index]);

            // 檢查後續是否有重複編號
            if (index + 1 < employees.length && employees[index + 1].getId().equals(targetId)) {
                System.out.println("警告：系統偵測到重複的員工編號！其他重複項目：");
                int dupIndex = index + 1;
                while (dupIndex < employees.length && employees[dupIndex].getId().equals(targetId)) {
                    System.out.println("  [重複] " + employees[dupIndex]);
                    dupIndex++;
                }
            }
        } else {
            System.out.println("搜尋結果：找不到編號為 " + targetId + " 的員工資料。");
        }
    }
}
