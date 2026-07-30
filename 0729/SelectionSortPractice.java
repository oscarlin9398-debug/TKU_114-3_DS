import java.util.Arrays;

public class SelectionSortPractice {
    public static void main(String[] args) {
        int[] data1 = {42, 18, 35, 7, 29, 14};
        int[] emptyData = {};
        int[] singleData = {99};

        System.out.println("=== 測試 1：標準陣列排序追蹤 ===");
        System.out.println("原始陣列：" + Arrays.toString(data1));
        selectionSortTrace(data1);

        System.out.println("\n=== 測試 2：空陣列測試 ===");
        System.out.println("原始陣列：" + Arrays.toString(emptyData));
        selectionSortTrace(emptyData);

        System.out.println("\n=== 測試 3：單一元素陣列測試 ===");
        System.out.println("原始陣列：" + Arrays.toString(singleData));
        selectionSortTrace(singleData);
    }

    public static void selectionSortTrace(int[] values) {
        if (values == null || values.length <= 1) {
            System.out.println("陣列無需排序。");
            System.out.println("結果陣列：" + Arrays.toString(values));
            System.out.println("比較次數：0，交換次數：0");
            return;
        }

        int comparisons = 0;
        int swaps = 0;

        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            for (int current = start + 1; current < values.length; current++) {
                comparisons++;
                if (values[current] < values[minIndex]) {
                    minIndex = current;
                }
            }

            if (minIndex != start) {
                int temp = values[start];
                values[start] = values[minIndex];
                values[minIndex] = temp;
                swaps++;
            }

            System.out.println("第 " + (start + 1) + " 輪：start = " + start + 
                               ", 選中的最小值索引 minIndex = " + minIndex + 
                               ", 當前陣列 = " + Arrays.toString(values));
        }

        System.out.println("----------------------------------------");
        System.out.println("排序完成：" + Arrays.toString(values));
        System.out.println("總比較次數：" + comparisons + " 次");
        System.out.println("總交換次數：" + swaps + " 次");
    }
}