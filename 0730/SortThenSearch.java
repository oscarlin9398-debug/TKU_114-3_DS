import java.util.Arrays;

public class SortThenSearch {
    public static void main(String[] args) {
        int[] productIds = {305, 101, 450, 220, 118, 390};

        mergeSort(productIds);
        System.out.println("排序後：" + Arrays.toString(productIds));

        int[] targets = {220, 305, 999};
        for (int target : targets) {
            int index = binarySearch(productIds, target);
            System.out.println(target + "：" +
                (index == -1 ? "找不到" : "索引 " + index));
        }
    }

    public static int binarySearch(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (values[mid] == target) {
                return mid;
            }
            if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
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