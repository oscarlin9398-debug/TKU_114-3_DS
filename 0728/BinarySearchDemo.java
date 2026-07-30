public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] seatNumbers = {101, 105, 108, 112, 120, 125, 130, 145};

        printResult(seatNumbers, 120);
        printResult(seatNumbers, 101);
        printResult(seatNumbers, 999);
    }

    public static int binarySearch(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (values[mid] == target) {
                return mid;
            } else if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public static void printResult(int[] values, int target) {
        int index = binarySearch(values, target);
        System.out.println(target + "：" +
            (index == -1 ? "找不到" : "索引 " + index));
    }
}
