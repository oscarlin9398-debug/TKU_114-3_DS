import java.util.Arrays;

public class SelectionSortDemo {
    public static void main(String[] args) {
        int[] values = {29, 10, 14, 37, 13};

        System.out.println("排序前：" + Arrays.toString(values));
        selectionSort(values);
        System.out.println("排序後：" + Arrays.toString(values));
    }

    public static void selectionSort(int[] values) {
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
        }
    }
}
