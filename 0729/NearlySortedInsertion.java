import java.util.Arrays;

public class NearlySortedInsertion {
    public static void main(String[] args) {
        int[] values = {10, 20, 30, 40, 35, 50};
        int moves = insertionSort(values);

        System.out.println("排序結果：" + Arrays.toString(values));
        System.out.println("移動次數：" + moves);
    }

    public static int insertionSort(int[] values) {
        int moves = 0;

        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;

            while (position >= 0 && values[position] > key) {
                values[position + 1] = values[position];
                moves++;
                position--;
            }
            values[position + 1] = key;
        }
        return moves;
    }
}
