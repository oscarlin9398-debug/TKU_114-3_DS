public class RecursiveBinarySearch {
    public static void main(String[] args) {
        int[] values = {10, 20, 30, 40, 50, 60, 70};

        System.out.println("50 的索引：" +
            binarySearch(values, 50, 0, values.length - 1));
        System.out.println("15 的索引：" +
            binarySearch(values, 15, 0, values.length - 1));
    }

    public static int binarySearch(
        int[] values,
        int target,
        int low,
        int high
    ) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        if (values[mid] == target) {
            return mid;
        }
        if (target < values[mid]) {
            return binarySearch(values, target, low, mid - 1);
        }
        return binarySearch(values, target, mid + 1, high);
    }
}
