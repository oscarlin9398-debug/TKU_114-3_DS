import java.util.Arrays;

public class InsertionSortDemo {
    public static void main(String[] args) {
        int[] values = {12, 11, 13, 5, 6};

        System.out.println("排序前：" + Arrays.toString(values));
        insertionSort(values);
        System.out.println("排序後：" + Arrays.toString(values));
    }

    public static void insertionSort(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;

            while (position >= 0 && values[position] > key) {
                values[position + 1] = values[position];
                position--;
            }

            values[position + 1] = key;
        }
    }
}
