public class RecursiveArraySum {
    public static void main(String[] args) {
        int[] values = {12, 7, 5, 20, 6};

        System.out.println("總和：" + sum(values, 0));
        System.out.println("最大值：" + max(values, 0));
    }

    public static int sum(int[] values, int index) {
        if (index == values.length) {
            return 0;
        }
        return values[index] + sum(values, index + 1);
    }

    public static int max(int[] values, int index) {
        if (values.length == 0) {
            throw new IllegalArgumentException("陣列不可為空");
        }

        if (index == values.length - 1) {
            return values[index];
        }

        int remainingMax = max(values, index + 1);
        return Math.max(values[index], remainingMax);
    }
}
