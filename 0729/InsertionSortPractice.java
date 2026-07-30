import java.util.Arrays;

public class InsertionSortPractice {
    public static void main(String[] args) {
        int[] data1 = {30, 10, 20, 50, 40, 5};
        int[] sortedData = {5, 10, 20, 30, 40, 50};
        int[] reverseData = {50, 40, 30, 20, 10, 5};

        System.out.println("=== 測試 1：一般陣列移動追蹤 ===");
        System.out.println("原始陣列：" + Arrays.toString(data1));
        insertionSortTrace(data1);

        System.out.println("\n=== 測試 2：已排序陣列測試 ===");
        System.out.println("原始陣列：" + Arrays.toString(sortedData));
        insertionSortTrace(sortedData);

        System.out.println("\n=== 測試 3：反向排序陣列測試 ===");
        System.out.println("原始陣列：" + Arrays.toString(reverseData));
        insertionSortTrace(reverseData);

        printComparisonSummary();
    }

    public static void insertionSortTrace(int[] values) {
        if (values == null || values.length <= 1) {
            System.out.println("陣列無需排序。");
            return;
        }

        int comparisons = 0;
        int shifts = 0;

        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;

            while (position >= 0) {
                comparisons++;
                if (values[position] > key) {
                    values[position + 1] = values[position];
                    shifts++;
                    position--;
                } else {
                    break;
                }
            }

            values[position + 1] = key;

            System.out.println("第 " + index + " 輪：key = " + key + 
                               ", 最終插入索引 = " + (position + 1) + 
                               ", 當前陣列 = " + Arrays.toString(values));
        }

        System.out.println("----------------------------------------");
        System.out.println("排序完成：" + Arrays.toString(values));
        System.out.println("總比對次數：" + comparisons + " 次");
        System.out.println("總右移次數：" + shifts + " 次");
    }

    public static void printComparisonSummary() {
        System.out.println("\n========================================");
        System.out.println("【測試結果分析與移動次數結論】");
        System.out.println("1. 移動（右移）次數最多的資料組為：反向排序陣列 (Reverse Sorted Data)");
        System.out.println("2. 原因分析：");
        System.out.println("   - 當陣列為完全反向（降冪）時，為 Insertion Sort 的最壞情況（Worst Case）。");
        System.out.println("   - 每個新加入的 key 均小於左側已排序區的所有元素，必須一路比對並將左側所有元素向右移動。");
        System.out.println("   - 長度 N=6 的反向陣列右移總次數為：1 + 2 + 3 + 4 + 5 = 15 次。");
        System.out.println("   - 相對地，已排序陣列為最佳情況（Best Case），右移次數為 0 次。");
        System.out.println("========================================");
    }
}
