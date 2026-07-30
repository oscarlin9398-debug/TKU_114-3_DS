import java.util.Arrays;

public class RangeSearchSystem {
    public static void main(String[] args) {
        // 含重複資料的已排序陣列
        int[] numbers = {10, 20, 20, 20, 20, 30, 40, 50, 50, 60};
        int[] emptyArray = {};

        System.out.println("=== 測試 1：搜尋重複多次的數字 (20) ===");
        searchRangeAndPrint(numbers, 20);

        System.out.println("\n=== 測試 2：搜尋重複兩次的數字 (50) ===");
        searchRangeAndPrint(numbers, 50);

        System.out.println("\n=== 測試 3：搜尋只出現一次的數字 (30) ===");
        searchRangeAndPrint(numbers, 30);

        System.out.println("\n=== 測試 4：搜尋邊界元素 - 第一筆 (10) ===");
        searchRangeAndPrint(numbers, 10);

        System.out.println("\n=== 測試 5：搜尋邊界元素 - 最後一筆 (60) ===");
        searchRangeAndPrint(numbers, 60);

        System.out.println("\n=== 測試 6：搜尋不存在的數字 (25) ===");
        searchRangeAndPrint(numbers, 25);

        System.out.println("\n=== 測試 7：搜尋空陣列 (20) ===");
        searchRangeAndPrint(emptyArray, 20);
    }

    public static int[] searchRange(int[] values, int target) {
        int first = findBound(values, target, true);
        
        // 若連第一個位置都找不到，代表目標不存在，直接回傳 [-1, -1]
        if (first == -1) {
            return new int[]{-1, -1};
        }

        int last = findBound(values, target, false);
        return new int[]{first, last};
    }

    /**
     * 修改版 Binary Search
     * @param isFirst true 代表找第一個出現位置；false 代表找最後一個出現位置
     */
    private static int findBound(int[] values, int target, boolean isFirst) {
        if (values == null || values.length == 0) {
            return -1;
        }

        int low = 0;
        int high = values.length - 1;
        int boundIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (values[mid] == target) {
                boundIndex = mid; // 紀錄目前找到的位置
                if (isFirst) {
                    high = mid - 1; // 找第一個：繼續向左半部收攏
                } else {
                    low = mid + 1;  // 找最後一個：繼續向右半部收攏
                }
            } else if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return boundIndex;
    }

    public static void searchAndPrintRange(int[] values, int target) {
        // 保持封裝性，維持與原本呼叫介面相容
        searchRangeAndPrint(values, target);
    }

    private static void searchRangeAndPrint(int[] values, int target) {
        int[] range = searchRange(values, target);
        System.out.println("搜尋目標：" + target);
        System.out.println("索引範圍：" + Arrays.toString(range));

        if (range[0] != -1) {
            int count = range[1] - range[0] + 1;
            System.out.println("總共出現次數：" + count + " 次");
        } else {
            System.out.println("搜尋結果：找不到目標值");
        }
    }
}
