public class Q05_RecursiveArrayReport {

    public static void main(String[] args) {
        int[] data = {12, -3, 25, 8, 25, 40, 5};
        System.out.println("10～30 筆數：" + countInRange(data, 0, 10, 30));
        System.out.println("正數總和：" + sumPositive(data, 0));
        System.out.println("25 最後索引：" + findLast(data, 0, 25));
        System.out.println("99 最後索引：" + findLast(data, 0, 99));
    }

    public static int countInRange(int[] data, int index, int minimum, int maximum) {
        if (data == null || index < 0 || index >= data.length) {
            return 0;
        }
        int count = (data[index] >= minimum && data[index] <= maximum) ? 1 : 0;
        return count + countInRange(data, index + 1, minimum, maximum);
    }

    public static int sumPositive(int[] data, int index) {
        if (data == null || index < 0 || index >= data.length) {
            return 0;
        }
        int val = (data[index] > 0) ? data[index] : 0;
        return val + sumPositive(data, index + 1);
    }

    public static int findLast(int[] data, int index, int target) {
        if (data == null || index < 0 || index >= data.length) {
            return -1;
        }
        int laterIndex = findLast(data, index + 1, target);
        if (laterIndex != -1) {
            return laterIndex;
        }
        if (data[index] == target) {
            return index;
        }
        return -1;
    }
}