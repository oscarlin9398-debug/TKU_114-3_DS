import java.util.Arrays;

public class MergeSortPractice {

    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("            課 堂 實 作 二 ： Merge Sort 執 行 追 蹤");
        System.out.println("==================================================================\n");

        int[] primaryData = {41, 12, 35, 8, 27, 19, 50, 3};
        runTest("主要追蹤範例 (Original Task Data)", primaryData, true);

        System.out.println("\n==================================================================");
        System.out.println("                     邊 界 與 特 殊 案 例 測 試");
        System.out.println("==================================================================\n");

        runTest("測試 1：空陣列 (Empty Array)", new int[]{}, false);
        runTest("測試 2：單筆資料 (Single Element)", new int[]{99}, false);
        runTest("測試 3：已排序資料 (Already Sorted)", new int[]{10, 20, 30, 40, 50}, false);
        runTest("測試 4：反向排序資料 (Reverse Sorted)", new int[]{50, 40, 30, 20, 10}, false);
    }

    public static void mergeSort(int[] arr, int left, int right, boolean showTrace) {
        // 遞迴停止條件：當子陣列長度為 0 或 1 時（left >= right）
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        if (showTrace) {
            System.out.printf("【拆分】範圍 [%d..%d] -> 左半部 [%d..%d], 右半部 [%d..%d]%n",
                    left, right, left, mid, mid + 1, right);
        }

        // 遞迴拆分左半部
        mergeSort(arr, left, mid, showTrace);

        // 遞迴拆分右半部
        mergeSort(arr, mid + 1, right, showTrace);

        // 合併兩半部
        merge(arr, left, mid, right, showTrace);
    }

    public static void merge(int[] arr, int left, int mid, int right, boolean showTrace) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        // 建立暫存陣列以保存兩半部資料
        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        // 使用三指標進行雙路合併
        int i = 0;    // 左暫存陣列指標
        int j = 0;    // 右暫存陣列指標
        int k = left; // 原陣列寫入指標

        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // 複製左邊剩餘元素
        while (i < leftSize) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // 複製右邊剩餘元素
        while (j < rightSize) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }

        if (showTrace) {
            System.out.printf("  [合併完成] 區間 [%d..%d] 內容: %s%n",
                    left, right, getSubArrayString(arr, left, right));
        }
    }

    private static void runTest(String title, int[] data, boolean showTrace) {
        System.out.println("【" + title + "】");
        System.out.println("  原始陣列: " + Arrays.toString(data));

        if (data == null || data.length == 0) {
            System.out.println("  處理結果: [] (長度為 0，直接返回)\n");
            return;
        }

        int[] copy = Arrays.copyOf(data, data.length);
        mergeSort(copy, 0, copy.length - 1, showTrace);

        System.out.println("  最終結果: " + Arrays.toString(copy) + "\n");
    }

    private static String getSubArrayString(int[] arr, int left, int right) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = left; i <= right; i++) {
            sb.append(arr[i]);
            if (i < right) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
