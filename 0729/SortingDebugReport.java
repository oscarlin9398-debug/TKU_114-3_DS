import java.util.Arrays;

public class SortingDebugReport {

    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("                 排 序 演 算 法 除 錯 實 驗 報 告");
        System.out.println("==================================================================\n");

        runBug1Demo();
        System.out.println("\n------------------------------------------------------------------\n");

        runBug2Demo();
        System.out.println("\n------------------------------------------------------------------\n");

        runBug3Demo();
    }

    // =========================================================================
    // 錯誤版本 1：內層範圍錯誤 (Selection Sort 邊界控制不當)
    // =========================================================================

    /*
     * 【錯誤原因說明】
     * 內層迴圈應該從 start + 1 開始掃描至陣列末端 (current < n)，
     * 但此錯誤版本誤將邊界寫成 current < n - 1，導致陣列最後一個元素永遠無法參與最小值比較。
     * 當最小值恰好位於陣列最後一個位置時，就會導致排序失敗。
     */
    public static void bug1SelectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        for (int start = 0; start < n - 1; start++) {
            int minIndex = start;

            // BUG：漏掉最後一個元素 (current < n - 1 應為 current < n)
            for (int current = start + 1; current < n - 1; current++) {
                if (arr[current] < arr[minIndex]) {
                    minIndex = current;
                }
            }

            if (minIndex != start) {
                int temp = arr[start];
                arr[start] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    public static void fixed1SelectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        for (int start = 0; start < n - 1; start++) {
            int minIndex = start;

            // FIX：完整掃描至陣列最後一個元素 (current < n)
            for (int current = start + 1; current < n; current++) {
                if (arr[current] < arr[minIndex]) {
                    minIndex = current;
                }
            }

            if (minIndex != start) {
                int temp = arr[start];
                arr[start] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    private static void runBug1Demo() {
        System.out.println("【測試案例 1：內層範圍錯誤 (Selection Sort 漏看最後一個元素)】");
        // 觸發條件：最小值位在陣列最後一個位置
        int[] testData1 = {80, 50, 90, 70, 10};

        int[] bugCopy = Arrays.copyOf(testData1, testData1.length);
        bug1SelectionSort(bugCopy);

        int[] fixCopy = Arrays.copyOf(testData1, testData1.length);
        fixed1SelectionSort(fixCopy);

        System.out.println("原始資料:   " + Arrays.toString(testData1));
        System.out.println("錯誤版結果: " + Arrays.toString(bugCopy) + "  <-- 10 遺漏未排到最前面");
        System.out.println("修正版結果: " + Arrays.toString(fixCopy));
    }

    // =========================================================================
    // 錯誤版本 2：key 未保存 (Insertion Sort 直接讀取已被覆蓋的陣列位置)
    // =========================================================================

    /*
     * 【錯誤原因說明】
     * 在 Insertion Sort 中，右移過程會覆蓋掉 arr[index] 的原始數值。
     * 錯誤版本沒有先將待插入的值保存在區域變數 key 中，而是在右移過程與插入位置直接使用 arr[index]。
     * 當 arr[index - 1] > arr[index] 觸發第一次右移 (arr[index] = arr[index - 1]) 時，
     * arr[index] 的原始數值便已永久遺失並被覆蓋。
     */
    public static void bug2InsertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        for (int index = 1; index < n; index++) {
            // BUG：沒有將 arr[index] 保存在 key 變數中
            int position = index - 1;

            // BUG：直接使用 arr[index] 進行比較，但其值在右移時會被覆蓋
            while (position >= 0 && arr[position] > arr[index]) {
                arr[position + 1] = arr[position];
                position--;
            }

            // BUG：誤將已被覆蓋的值寫回，導致資料重複與遺失
            arr[position + 1] = arr[index];
        }
    }

    public static void fixed2InsertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        for (int index = 1; index < n; index++) {
            // FIX：在進行右移之前，先備份 key = arr[index]
            int key = arr[index];
            int position = index - 1;

            while (position >= 0 && arr[position] > key) {
                arr[position + 1] = arr[position];
                position--;
            }

            // FIX：將備份的 key 放回正確的插入位置
            arr[position + 1] = key;
        }
    }

    private static void runBug2Demo() {
        System.out.println("【測試案例 2：key 未保存 (Insertion Sort 值被右移覆蓋)】");
        // 觸發條件：後方有較小的元素需要往前移動，會發生覆蓋
        int[] testData2 = {30, 10, 20};

        int[] bugCopy = Arrays.copyOf(testData2, testData2.length);
        bug2InsertionSort(bugCopy);

        int[] fixCopy = Arrays.copyOf(testData2, testData2.length);
        fixed2InsertionSort(fixCopy);

        System.out.println("原始資料:   " + Arrays.toString(testData2));
        System.out.println("錯誤版結果: " + Arrays.toString(bugCopy) + "  <-- 10 被覆蓋遺失，產生重複 30");
        System.out.println("修正版結果: " + Arrays.toString(fixCopy));
    }

    // =========================================================================
    // 錯誤版本 3：比較方向錯誤 (需求為升冪，判斷式寫反導致變成降冪)
    // =========================================================================

    /*
     * 【錯誤原因說明】
     * 題目要求由小到大排序（升冪排序），
     * 錯誤版本的判斷式寫成 arr[position] < key，導致較小的數字被向右移動，
     * 最終輸出變成由大到小的降冪排序，無法符合升冪規則。
     */
    public static void bug3InsertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        for (int index = 1; index < n; index++) {
            int key = arr[index];
            int position = index - 1;

            // BUG：比較方向相反 (< 導致變成降冪排序)
            while (position >= 0 && arr[position] < key) {
                arr[position + 1] = arr[position];
                position--;
            }

            arr[position + 1] = key;
        }
    }

    public static void fixed3InsertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        for (int index = 1; index < n; index++) {
            int key = arr[index];
            int position = index - 1;

            // FIX：改為 >，當前元素比 key 大時右移，達成升冪排序
            while (position >= 0 && arr[position] > key) {
                arr[position + 1] = arr[position];
                position--;
            }

            arr[position + 1] = key;
        }
    }

    private static void runBug3Demo() {
        System.out.println("【測試案例 3：比較方向錯誤 (應為升冪卻變成降冪)】");
        // 觸發條件：未排序陣列，預期輸出升冪 [12, 25, 45, 68]
        int[] testData3 = {45, 12, 68, 25};

        int[] bugCopy = Arrays.copyOf(testData3, testData3.length);
        bug3InsertionSort(bugCopy);

        int[] fixCopy = Arrays.copyOf(testData3, testData3.length);
        fixed3InsertionSort(fixCopy);

        System.out.println("原始資料:   " + Arrays.toString(testData3));
        System.out.println("錯誤版結果: " + Arrays.toString(bugCopy) + "  <-- 方向顛倒，變為降冪");
        System.out.println("修正版結果: " + Arrays.toString(fixCopy));
    }
}
