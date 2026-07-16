public class Q08_ArrayRemove {
    public static void main(String[] args) {
        int[] values = {4, 7, 2, 7, 9, 7, 1};
        int target = 7;

        System.out.println("出現次數：" + countOccurrences(values, target));
        System.out.println("最後索引：" + findLastIndex(values, target));

        int[] result = removeAll(values, target);
        System.out.print("移除後：");
        printArray(result);
        System.out.print("原始陣列：");
        printArray(values);
    }

    public static int countOccurrences(int[] data, int target) {
        if (data == null) {
            return 0;
        }
        int count = 0;
        for (int value : data) {
            if (value == target) {
                count++;
            }
        }
        return count;
    }

    public static int findLastIndex(int[] data, int target) {
        if (data == null) {
            return -1;
        }
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int[] removeAll(int[] data, int target) {
        if (data == null) {
            return null;
        }

        int occurrences = countOccurrences(data, target);
        int[] result = new int[data.length - occurrences];
        int index = 0;

        for (int value : data) {
            if (value != target) {
                result[index] = value;
                index++;
            }
        }
        return result;
    }

    private static void printArray(int[] data) {
        if (data == null) {
            System.out.println("null");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
