import java.util.Arrays;

public class SortChoiceDemo {
    public static void main(String[] args) {
        int[] smallUnsorted = {8, 3, 6, 1};
        int[] nearlySorted = {10, 20, 30, 50, 40};

        selectionSort(smallUnsorted);
        insertionSort(nearlySorted);

        System.out.println("小型資料：" +
            Arrays.toString(smallUnsorted));
        System.out.println("接近有序資料：" +
            Arrays.toString(nearlySorted));
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
