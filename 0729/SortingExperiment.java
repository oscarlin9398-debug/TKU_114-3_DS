import java.util.Arrays;
import java.util.Random;

public class SortingExperiment {

    // 紀錄統計數據的資料結構
    static class SortMetrics {
        long comparisons = 0;
        long swaps = 0;
        long shifts = 0;

        void reset() {
            comparisons = 0;
            swaps = 0;
            shifts = 0;
        }
    }

    public static void main(String[] args) {
        int size = 100;

        // 1. 建立已排序資料 (Sorted)
        int[] sortedData = new int[size];
        for (int i = 0; i < size; i++) {
            sortedData[i] = (i + 1) * 10;
        }

        // 2. 建立反向排序資料 (Reverse Sorted)
        int[] reverseData = new int[size];
        for (int i = 0; i < size; i++) {
            reverseData[i] = (size - i) * 10;
        }

        // 3. 建立隨機排列資料 (Random)
        int[] randomData = new int[size];
        Random rand = new Random(42); // 固定種子以確保每次執行結果一致
        for (int i = 0; i < size; i++) {
            randomData[i] = rand.nextInt(1000);
        }

        System.out.println("==================================================================");
        System.out.println("                 選 擇 排序 與 插入 排序 效 能 實 驗 (N = " + size + ")");
        System.out.println("==================================================================");

        runExperiment("已排序資料 (Sorted)", sortedData);
        runExperiment("反向排序資料 (Reverse Sorted)", reverseData);
        runExperiment("隨機排列資料 (Random)", randomData);

        printObservationSummary();
    }

    public static void runExperiment(String dataType, int[] originalData) {
        System.out.println("\n【測試資料組：" + dataType + "】");

        // 使用相同的資料副本測試 Selection Sort
        int[] selectionCopy = Arrays.copyOf(originalData, originalData.length);
        SortMetrics selectionMetrics = new SortMetrics();
        selectionSort(selectionCopy, selectionMetrics);

        // 使用相同的資料副本測試 Insertion Sort
        int[] insertionCopy = Arrays.copyOf(originalData, originalData.length);
        SortMetrics insertionMetrics = new SortMetrics();
        insertionSort(insertionCopy, insertionMetrics);

        // 格式化輸出統計數據
        System.out.printf("  %-16s | 比較次數: %5d | 交換次數: %5d | 元素移動(右移): %5d%n",
                "Selection Sort", selectionMetrics.comparisons, selectionMetrics.swaps, selectionMetrics.shifts);
        System.out.printf("  %-16s | 比較次數: %5d | 交換次數: %5d | 元素移動(右移): %5d%n",
                "Insertion Sort", insertionMetrics.comparisons, insertionMetrics.swaps, insertionMetrics.shifts);
    }

    // Selection Sort 實作與統計
    public static void selectionSort(int[] arr, SortMetrics metrics) {
        metrics.reset();
        int n = arr.length;

        for (int start = 0; start < n - 1; start++) {
            int minIndex = start;

            for (int current = start + 1; current < n; current++) {
                metrics.comparisons++;
                if (arr[current] < arr[minIndex]) {
                    minIndex = current;
                }
            }

            if (minIndex != start) {
                int temp = arr[start];
                arr[start] = arr[minIndex];
                arr[minIndex] = temp;
                metrics.swaps++;
            }
        }
    }

    // Insertion Sort 實作與統計
    public static void insertionSort(int[] arr, SortMetrics metrics) {
        metrics.reset();
        int n = arr.length;

        for (int index = 1; index < n; index++) {
            int key = arr[index];
            int position = index - 1;

            while (position >= 0) {
                metrics.comparisons++;
                if (arr[position] > key) {
                    arr[position + 1] = arr[position];
                    metrics.shifts++;
                    position--;
                } else {
                    break;
                }
            }
            arr[position + 1] = key;
        }
    }

    public static void printObservationSummary() {
        System.out.println("\n==================================================================");
        System.out.println("                       實 驗 觀 察 結 論");
        System.out.println("==================================================================");
        System.out.println("1. 已排序資料 (Sorted Data):");
        System.out.println("   - Insertion Sort 展現最佳情況 O(N)，只需 N-1 次比較，0 次交換與移動。");
        System.out.println("   - Selection Sort 比對次數依然為固定 N(N-1)/2 次，表現不因資料有序而提升。");
        System.out.println();
        System.out.println("2. 反向排序資料 (Reverse Sorted Data):");
        System.out.println("   - Insertion Sort 面臨最壞情況 O(N^2)，比較與移動次數皆達最高 peak。");
        System.out.println("   - Selection Sort 比較次數相同，但每輪都需要交換，交換次數達到最大值 (N-1)。");
        System.out.println();
        System.out.println("3. 隨機排列資料 (Random Data):");
        System.out.println("   - Insertion Sort 平均比較與移動次數約為最壞情況的一半，整體表現仍優於 Selection Sort。");
        System.out.println("   - Selection Sort 保持極低的交換次數 (最多 N-1 次)，當寫入/交換記憶體成本高昂時較具優勢。");
        System.out.println("==================================================================");
    }
}
