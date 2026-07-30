import java.util.Arrays;

public class SelectionSortTrace {
    public static void main(String[] args) {
        int[] values = {64, 25, 12, 22, 11};
        selectionSort(values);
    }

    public static void selectionSort(int[] values) {
        System.out.println("開始：" + Arrays.toString(values));

        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }

            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;

            System.out.printf(
                "第 %d 輪：%s%n",
                start + 1,
                Arrays.toString(values)
            );
        }
    }
}
