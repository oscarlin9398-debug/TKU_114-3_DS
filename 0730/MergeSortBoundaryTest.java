import java.util.Arrays;

public class MergeSortBoundaryTest {
    public static void main(String[] args) {
        test(new int[] {});
        test(new int[] {5});
        test(new int[] {1, 2, 3, 4});
        test(new int[] {4, 3, 2, 1});
        test(new int[] {7, 7, 7, 7});
        test(new int[] {5, -2, 5, 0, -2});
    }

    public static void test(int[] values) {
        System.out.println("排序前：" + Arrays.toString(values));
        mergeSort(values);
        System.out.println("排序後：" + Arrays.toString(values));
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
