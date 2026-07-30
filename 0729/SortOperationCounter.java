public class SortOperationCounter {
    public static void main(String[] args) {
        int[] values = {10, 20, 30, 50, 40};

        int[] selectionData = values.clone();
        int[] insertionData = values.clone();

        int[] selectionCounts = selectionSort(selectionData);
        int[] insertionCounts = insertionSort(insertionData);

        System.out.printf(
            "Selection：比較 %d 次，交換 %d 次%n",
            selectionCounts[0], selectionCounts[1]
        );
        System.out.printf(
            "Insertion：比較 %d 次，移動 %d 次%n",
            insertionCounts[0], insertionCounts[1]
        );
    }

    public static int[] selectionSort(int[] values) {
        int comparisons = 0;
        int swaps = 0;

        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                comparisons++;
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }
            if (minIndex != start) {
                int temp = values[start];
                values[start] = values[minIndex];
                values[minIndex] = temp;
                swaps++;
            }
        }
        return new int[] {comparisons, swaps};
    }

    public static int[] insertionSort(int[] values) {
        int comparisons = 0;
        int moves = 0;

        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;

            while (position >= 0) {
                comparisons++;
                if (values[position] <= key) {
                    break;
                }
                values[position + 1] = values[position];
                moves++;
                position--;
            }
            values[position + 1] = key;
        }
        return new int[] {comparisons, moves};
    }
}
