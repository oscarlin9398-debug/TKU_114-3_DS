import java.util.Arrays;

public class MergeArrayPractice {

    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("            課 堂 實 作 一 ： 合 併 兩 個 排 序 陣 列");
        System.out.println("==================================================================\n");

        runTestCase("測試 1：標準長度不同、包含重複值與負數",
                new int[]{-15, -3, 0, 5, 12, 12, 20},
                new int[]{-8, -3, 2, 5, 15, 25, 30, 40});

        runTestCase("測試 2：第一個陣列為空 (Array 1 is empty)",
                new int[]{},
                new int[]{-5, 0, 10, 20});

        runTestCase("測試 3：第二個陣列為空 (Array 2 is empty)",
                new int[]{-10, -2, 8},
                new int[]{});

        runTestCase("測試 4：兩陣列皆為空 (Both empty)",
                new int[]{},
                new int[]{});

        runTestCase("測試 5：兩陣列有大量重疊數值",
                new int[]{-1, -1, 1, 1, 3},
                new int[]{-1, 0, 1, 2, 3, 3});
    }

    public static int[] mergeUniqueSortedArrays(int[] arr1, int[] arr2) {
        if (arr1 == null) arr1 = new int[0];
        if (arr2 == null) arr2 = new int[0];

        // 雙指標走訪計算不重複元素個數
        int i = 0, j = 0;
        int uniqueCount = 0;
        Integer lastAdded = null;

        while (i < arr1.length || j < arr2.length) {
            int current;

            if (i < arr1.length && (j >= arr2.length || arr1[i] <= arr2[j])) {
                current = arr1[i];
                i++;
            } else {
                current = arr2[j];
                j++;
            }

            if (lastAdded == null || current != lastAdded) {
                uniqueCount++;
                lastAdded = current;
            }
        }

        // 建立精確大小的結果陣列並充填資料
        int[] result = new int[uniqueCount];
        int index1 = 0; // 索引 1：走訪 arr1
        int index2 = 0; // 索引 2：走訪 arr2
        int indexR = 0; // 索引 3：寫入 result

        while (index1 < arr1.length || index2 < arr2.length) {
            int current;

            // 選擇較小值的元素推進
            if (index1 < arr1.length && (index2 >= arr2.length || arr1[index1] <= arr2[index2])) {
                current = arr1[index1];
                index1++;
            } else {
                current = arr2[index2];
                index2++;
            }

            // 去重檢查：僅當第一次寫入或與前一個寫入值不同時才寫入
            if (indexR == 0 || current != result[indexR - 1]) {
                result[indexR] = current;
                indexR++;
            }
        }

        return result;
    }

    private static void runTestCase(String title, int[] arr1, int[] arr2) {
        System.out.println("【" + title + "】");
        System.out.println("  陣列一: " + Arrays.toString(arr1));
        System.out.println("  陣列二: " + Arrays.toString(arr2));

        int[] result = mergeUniqueSortedArrays(arr1, arr2);

        System.out.println("  合併後: " + Arrays.toString(result));
        System.out.println("  總長度: " + result.length);
        System.out.println("------------------------------------------------------------------");
    }
}
