import java.util.Arrays;

public class SelectionSortDescending {
    public static void main(String[] args) {
        int[] scores = {82, 95, 71, 88, 95, 60};

        selectionSortDescending(scores);
        System.out.println(Arrays.toString(scores));
    }

    public static void selectionSortDescending(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int maxIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                if (values[index] > values[maxIndex]) {
                    maxIndex = index;
                }
            }

            int temp = values[start];
            values[start] = values[maxIndex];
            values[maxIndex] = temp;
        }
    }
}
