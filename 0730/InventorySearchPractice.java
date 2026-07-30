import java.util.Arrays;

public class InventorySearchPractice {

    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("            課 堂 實 作 三 ： 先 排 序 再 搜 尋 ( 庫 存 系統 )");
        System.out.println("==================================================================\n");

        int[] originalInventory = {804, 105, 930, 212, 501, 101, 740, 318, 625, 409, 999, 150, 330};

        System.out.println("=== 1. 排序前原始庫存編號 ===");
        System.out.println("庫存列表: " + Arrays.toString(originalInventory));
        System.out.println("總筆數:   " + originalInventory.length);
        System.out.println("------------------------------------------------------------------\n");

        int[] sortedInventory = Arrays.copyOf(originalInventory, originalInventory.length);
        mergeSort(sortedInventory, 0, sortedInventory.length - 1);

        System.out.println("=== 2. Merge Sort 排序後結果 (升冪) ===");
        System.out.println("庫存列表: " + Arrays.toString(sortedInventory));
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 3. Binary Search 邊界條件與關鍵案例測試 ===");

        // 測試第一筆 (最小編號)
        int targetFirst = sortedInventory[0];
        testBinarySearch(sortedInventory, targetFirst, "第一筆編號 (邊界: Minimum)");

        // 測試最後一筆 (最大編號)
        int targetLast = sortedInventory[sortedInventory.length - 1];
        testBinarySearch(sortedInventory, targetLast, "最後一筆編號 (邊界: Maximum)");

        // 測試中間任意筆數
        int targetMiddle = sortedInventory[sortedInventory.length / 2];
        testBinarySearch(sortedInventory, targetMiddle, "中間編號 (Normal Target)");

        // 測試不存在的編號 (小於最小值)
        testBinarySearch(sortedInventory, 50, "不存在編號 (過小: Below Range)");

        // 測試不存在的編號 (介於中間)
        testBinarySearch(sortedInventory, 500, "不存在編號 (中間缺號: Missing in Between)");

        // 測試不存在的編號 (大於最大值)
        testBinarySearch(sortedInventory, 1000, "不存在編號 (過大: Above Range)");
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
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

    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // 找到目標，回傳索引
            }

            if (arr[mid] < target) {
                left = mid + 1; // 目標較大，向右區間搜尋
            } else {
                right = mid - 1; // 目標較小，向左區間搜尋
            }
        }

        return -1; // 找不到目標，回傳 -1
    }

    private static void testBinarySearch(int[] arr, int target, String description) {
        int index = binarySearch(arr, target);
        System.out.printf("【測試情境】%-30s | 搜尋目標: %-4d | ", description, target);

        if (index != -1) {
            System.out.printf("結果: 找到 (索引位置: %d, 驗證值: %d)%n", index, arr[index]);
        } else {
            System.out.println("結果: 未找到 (回傳: -1)");
        }
    }
}
