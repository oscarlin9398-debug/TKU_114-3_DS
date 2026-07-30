public class SequentialSearchDemo {
    public static void main(String[] args) {
        int[] productIds = {105, 203, 118, 450, 326, 118};

        printResult(productIds, 450);
        printResult(productIds, 118);
        printResult(productIds, 999);
    }

    public static int sequentialSearch(int[] values, int target) {
        for (int index = 0; index < values.length; index++) {
            if (values[index] == target) {
                return index;
            }
        }
        return -1;
    }

    public static void printResult(int[] values, int target) {
        int index = sequentialSearch(values, target);
        if (index == -1) {
            System.out.println(target + "：找不到");
        } else {
            System.out.println(target + "：索引 " + index);
        }
    }
}
