public class BinarySearchTrace {
    public static void main(String[] args) {
        int[] values = {3, 8, 12, 17, 21, 25, 31, 40};
        int index = binarySearch(values, 25);
        System.out.println("結果索引：" + index);
    }

    public static int binarySearch(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.printf(
                "low=%d, mid=%d, high=%d, value=%d%n",
                low, mid, high, values[mid]
            );

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
}
