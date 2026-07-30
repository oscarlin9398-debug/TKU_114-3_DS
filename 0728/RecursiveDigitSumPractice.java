public class RecursiveDigitSumPractice {
    public static void main(String[] args) {
        int[] testNumbers = {5729, 0, 8, 12345, 9999};

        for (int number : testNumbers) {
            System.out.println(number + " 的各位數總和：" + digitSum(number));
        }
    }

    public static int digitSum(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("數字不可為負數");
        }

        if (number < 10) {
            return number;
        }

        return (number % 10) + digitSum(number / 10);
    }
}
