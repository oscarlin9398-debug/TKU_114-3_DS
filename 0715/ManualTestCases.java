public class ManualTestCases {
    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }

    public static void runTest(int input, boolean expected) {
        boolean actual = isValidScore(input);
        String result = actual == expected ? "通過" : "失敗";

        System.out.println("輸入：" + input + "，預期：" + expected + "，實際：" + actual + "，結果：" + result);
    }

    public static void main(String[] args) {
        runTest(80, true);
        runTest(0, true);
        runTest(100, true);
        runTest(-1, false);
        runTest(101, false);
    }
}
