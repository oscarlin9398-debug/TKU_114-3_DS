public class RecursiveDigitCounter {
    public static void main(String[] args) {
        int[][] testCases = {
            {732313, 3},
            {55555, 5},
            {123456, 9},
            {0, 0},
            {70080, 0},
            {8, 8}
        };

        for (int[] testCase : testCases) {
            int number = testCase[0];
            int target = testCase[1];
            System.out.println("數字 " + number + " 中，數字 " + target + " 出現的次數為：" + countDigit(number, target));
        }
    }

    public static int countDigit(int number, int target) {
        if (target < 0 || target > 9) {
            throw new IllegalArgumentException("target 必須介於 0 到 9 之間");
        }

        if (number < 0) {
            throw new IllegalArgumentException("number 不可為負數");
        }

        if (number < 10) {
            return (number == target) ? 1 : 0;
        }

        int currentDigit = number % 10;
        int count = (currentDigit == target) ? 1 : 0;

        return count + countDigit(number / 10, target);
    }
}
