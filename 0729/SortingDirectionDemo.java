import java.util.Arrays;

public class SortingDirectionDemo {
    public static void main(String[] args) {
        int[] scores = {78, 92, 65, 88, 92};

        int[] ascending = scores.clone();
        int[] descending = scores.clone();

        selectionSort(ascending, true);
        selectionSort(descending, false);

        System.out.println("原始資料：" + Arrays.toString(scores));
        System.out.println("升冪排列：" + Arrays.toString(ascending));
        System.out.println("降冪排列：" + Arrays.toString(descending));
    }

    public static void selectionSort(int[] values, boolean ascending) {
        for (int start = 0; start < values.length - 1; start++) {
            int selected = start;
            for (int index = start + 1; index < values.length; index++) {
                boolean shouldSelect = ascending
                    ? values[index] < values[selected]
                    : values[index] > values[selected];
                if (shouldSelect) {
                    selected = index;
                }
            }

            int temp = values[start];
            values[start] = values[selected];
            values[selected] = temp;
        }
    }
}
