import java.util.Arrays;

public class MergeSortDemo {
    public static void main(String[] args) {
        int[] values = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("排序前：" + Arrays.toString(values));
        mergeSort(values);
        System.out.println("排序後：" + Arrays.toString(values));
    }

    public static void mergeSort(int[] values) {
        if (values == null || values.length < 2) {
            return;
        }

        int[] temp = new int[values.length];
        mergeSort(values, temp, 0, values.length - 1);
    }

    private static void mergeSort(
        int[] values,
        int[] temp,
        int left,
        int right
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
        int[] values,
        int[] temp,
        int left,
        int mid,
        int right
    ) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int tempIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (values[leftIndex] <= values[rightIndex]) {
                temp[tempIndex++] = values[leftIndex++];
            } else {
                temp[tempIndex++] = values[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            temp[tempIndex++] = values[leftIndex++];
        }

        while (rightIndex <= right) {
            temp[tempIndex++] = values[rightIndex++];
        }

        for (int index = left; index <= right; index++) {
            values[index] = temp[index];
        }
    }
}
