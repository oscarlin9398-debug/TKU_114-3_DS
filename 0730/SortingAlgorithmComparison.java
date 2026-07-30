import java.util.Arrays;

public class SortingAlgorithmComparison {
    public static void main(String[] args) {
        int[] original = {9, 3, 7, 1, 8, 2};

        int[] selectionData = original.clone();
        int[] insertionData = original.clone();
        int[] mergeData = original.clone();

        selectionSort(selectionData);
        insertionSort(insertionData);
        mergeSort(mergeData);

        System.out.println("Selection：" +
            Arrays.toString(selectionData));
        System.out.println("Insertion：" +
            Arrays.toString(insertionData));
        System.out.println("Merge：" +
            Arrays.toString(mergeData));
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

    public static void mergeSort(int[] values) {
        int[] temp = new int[values.length];
        mergeSort(values, temp, 0, values.length - 1);
    }

    private static void mergeSort(
        int[] values, int[] temp, int left, int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(values, temp, left, mid);
        mergeSort(values, temp, mid + 1, right);
        merge(values, temp, left, mid, right);
    }

    private static void merge(
        int[] values, int[] temp, int left, int mid, int right
    ) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            temp[k++] = values[i] <= values[j]
                ? values[i++]
                : values[j++];
        }
        while (i <= mid) {
            temp[k++] = values[i++];
        }
        while (j <= right) {
            temp[k++] = values[j++];
        }
        for (int index = left; index <= right; index++) {
            values[index] = temp[index];
        }
    }
}
