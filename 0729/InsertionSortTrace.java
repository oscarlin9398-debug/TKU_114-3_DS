import java.util.Arrays;

public class InsertionSortTrace {
    public static void main(String[] args) {
        int[] values = {8, 4, 6, 2};
        insertionSort(values);
    }

    public static void insertionSort(int[] values) {
        System.out.println("開始：" + Arrays.toString(values));

        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;

            while (position >= 0 && values[position] > key) {
                values[position + 1] = values[position];
                position--;
            }

            values[position + 1] = key;
            System.out.printf(
                "插入 %d：%s%n",
                key,
                Arrays.toString(values)
            );
        }
    }
}
