import java.util.Arrays;
import java.util.Random;

public class AlgorithmComparisonReport {

    // 紀錄各排序演算法的比較次數
    static class SortResult {
        long comparisons;

        public SortResult() {
            this.comparisons = 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("               課 後 作 業 三 ： 演 算 法 比 較 報 告 程 式");
        System.out.println("=========================================================================================\n");

        int[] dataSizes = {16, 128, 1024};
        
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-12s | %-16s | %-16s | %-16s |%n", 
                "資料筆數", "資料狀態", "Selection Sort", "Insertion Sort", "Merge Sort");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (int size : dataSizes) {
            // 1. 已排序資料 (Sorted Data)
            int[] sortedData = generateSortedData(size);
            testAndPrintRow(size, "已排序 (Sorted)", sortedData);

            // 2. 反向排序資料 (Reverse Sorted Data)
            int[] reverseData = generateReverseData(size);
            testAndPrintRow(size, "反向 (Reverse)", reverseData);

            // 3. 固定亂序資料 (Random Data with fixed seed for fairness)
            int[] randomData = generateRandomData(size, 42);
            testAndPrintRow(size, "亂序 (Random)", randomData);

            System.out.println("-----------------------------------------------------------------------------------------");
        }

        printReportAnalysis();
    }

    private static void testAndPrintRow(int size, String dataType, int[] originalData) {
        // 使用相同原始資料的獨立副本
        int[] dataForSelection = Arrays.copyOf(originalData, originalData.length);
        int[] dataForInsertion = Arrays.copyOf(originalData, originalData.length);
        int[] dataForMerge = Arrays.copyOf(originalData, originalData.length);

        long selectionComp = selectionSort(dataForSelection);
        long insertionComp = insertionSort(dataForInsertion);
        long mergeComp = mergeSort(dataForMerge);

        System.out.printf("| %-10d | %-12s | %-16d | %-16d | %-16d |%n", 
                size, dataType, selectionComp, insertionComp, mergeComp);
    }

    // --- Selection Sort ---
    public static long selectionSort(int[] arr) {
        long comparisons = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
        return comparisons;
    }

    // --- Insertion Sort ---
    public static long insertionSort(int[] arr) {
        long comparisons = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        return comparisons;
    }

    // --- Merge Sort ---
    public static long mergeSort(int[] arr) {
        SortResult result = new SortResult();
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        mergeSortRecursive(arr, 0, arr.length - 1, result);
        return result.comparisons;
    }

    private static void mergeSortRecursive(int[] arr, int left, int right, SortResult result) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortRecursive(arr, left, mid, result);
        mergeSortRecursive(arr, mid + 1, right, result);
        merge(arr, left, mid, right, result);
    }

    private static void merge(int[] arr, int left, int mid, int right, SortResult result) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < leftSize && j < rightSize) {
            result.comparisons++;
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // --- 資料產生工具 ---
    private static int[] generateSortedData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (i + 1) * 10;
        }
        return data;
    }

    private static int[] generateReverseData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (size - i) * 10;
        }
        return data;
    }

    private static int[] generateRandomData(int size, long seed) {
        int[] data = new int[size];
        Random random = new Random(seed);
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(10000);
        }
        return data;
    }

    // --- 觀察結論與理論對照分析 ---
    private static void printReportAnalysis() {
        System.out.println("\n=========================================================================================");
        System.out.println("                     演 算 法 比 較 統 計 與 實 驗 結 論");
        System.out.println("=========================================================================================");
        System.out.println("1. Selection Sort (選擇排序法)：");
        System.out.println("   - 特性：不論資料狀態為已排序、反向或亂序，比較次數恆定為 N*(N-1)/2。");
        System.out.println("   - 原因：Selection Sort 缺乏提早結束迴圈的機制，時間複雜度始終為 O(N^2)。");
        System.out.println();
        System.out.println("2. Insertion Sort (插入排序法)：");
        System.out.println("   - 最佳狀況 (已排序)：只需比較 N-1 次即可完成排序，時間複雜度達最佳 O(N)。");
        System.out.println("   - 最壞狀況 (反向排序)：需比較 N*(N-1)/2 次，時間複雜度退化為 O(N^2)。");
        System.out.println("   - 特點：對於小規模或高度接近已排序的資料，比較次數顯著低於其他演算法。");
        System.out.println();
        System.out.println("3. Merge Sort (合併排序法)：");
        System.out.println("   - 特性：採用分治法 (Divide and Conquer)，比較次數落在 O(N log N) 的極窄區間內。");
        System.out.println("   - 規模擴展度：在 N=1024 時，Merge Sort 的比較次數約為 8000~9000 次，遠優於 O(N^2) 的 520,000+ 次。");
        System.out.println("   - 結論：當資料量極大時，Merge Sort 能提供極度穩定且高效的排序效能。");
        System.out.println();
        System.out.println("4. 評估指標說明：");
        System.out.println("   - 本報告採用「比較次數 (Comparisons)」做為客觀衡量指標，以排除 JVM 垃圾回收、硬體CPU脈衝");
        System.out.println("     及環境干擾，提供具備理論確定性的演算法效能評估結果。");
        System.out.println("=========================================================================================\n");
    }
}
