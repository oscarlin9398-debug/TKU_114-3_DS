public class FirstOccurrenceSearch {
    public static void main(String[] args) {
        int[] values = {10, 20, 20, 20, 30, 40};

        System.out.println("20 第一次出現：" +
            findFirst(values, 20));
        System.out.println("25 第一次出現：" +
            findFirst(values, 25));
    }

    public static int findFirst(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int answer = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (values[mid] == target) {
                answer = mid;
                high = mid - 1;
            } else if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }
}
