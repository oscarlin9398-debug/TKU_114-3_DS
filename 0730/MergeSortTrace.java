import java.util.Arrays;

public class MergeSortTrace {
    public static void main(String[] args) {
        int[] values = {8, 3, 6, 2};
        int[] temp = new int[values.length];

        mergeSort(values, temp, 0, values.length - 1, 0);
        System.out.println("完成：" + Arrays.toString(values));
    }

    public static void mergeSort(
        int[] values,
        int[] temp,
        int left,
        int right,
        int depth
    ) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(values, temp, left, mid, depth + 1);
        mergeSort(values, temp, mid + 1, right, depth + 1);
        merge(values, temp, left, mid, right);

        int[] range = Arrays.copyOfRange(values, left, right + 1);
        System.out.printf(
            "%s合併 %d..%d：%s%n",
            "  ".repeat(depth),
            left,
            right,
            Arrays.toString(range)
        );
    }

    public static void merge(
        int[] values,
        int[] temp,
        int left,
        int mid,
        int right
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
