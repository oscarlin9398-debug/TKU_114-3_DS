public class RecursionTrace {
    public static void main(String[] args) {
        trace(3);
    }

    public static void trace(int level) {
        if (level == 0) {
            System.out.println("到達 base case");
            return;
        }

        System.out.println("進入：" + level);
        trace(level - 1);
        System.out.println("返回：" + level);
    }
}
