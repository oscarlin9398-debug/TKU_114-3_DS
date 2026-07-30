public class Q08_AlgorithmDecision {

    public static void main(String[] args) {
        int[] data = new int[64];
        for (int index = 0; index < data.length; index++) {
            data[index] = (index + 1) * 3;
        }

        System.out.println("已排序：" + isSortedAscending(data));
        System.out.println("循序比較次數：" + sequentialChecks(data, 192));
        System.out.println("二分比較次數：" + binaryChecks(data, 192));
        System.out.println("建議：" + chooseSearch(true, data.length, 5));
    }

    public static boolean isSortedAscending(int[] data) {
        if (data == null || data.length <= 1) {
            return true;
        }

        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] > data[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static int sequentialChecks(int[] data, int target) {
        if (data == null || data.length == 0) {
            return 0;
        }

        int checks = 0;
        for (int i = 0; i < data.length; i++) {
            checks++;
            if (data[i] == target) {
                break;
            }
        }
        return checks;
    }

    public static int binaryChecks(int[] data, int target) {
        if (data == null || data.length == 0) {
            return 0;
        }

        int low = 0;
        int high = data.length - 1;
        int checks = 0;

        while (low <= high) {
            checks++;
            int mid = low + (high - low) / 2;
            if (data[mid] == target) {
                break;
            } else if (data[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return checks;
    }

    public static String chooseSearch(boolean sorted, int dataSize, int expectedSearches) {
        if (sorted && dataSize >= 32 && expectedSearches >= 2) {
            return "BINARY";
        }
        return "SEQUENTIAL";
    }
}
