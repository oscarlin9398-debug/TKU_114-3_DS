public class Q07_ArrayAudit {
    private static final int MIN_VALID = 10;
    private static final int MAX_VALID = 60;
    private static final int TARGET = 35;
    private static final int INVALID_MARK = -1;

    public static void main(String[] args) {
        int[] readings = {12, 71, 35, -4, 35, 22, 60, 9, 48, 61};
        System.out.println("有效筆數：" + countValid(readings));
        System.out.printf("有效平均：%.2f%n", averageValid(readings));
        System.out.println("最後符合門檻的索引：" + findLastAtLeast(readings, TARGET));
        int[] cleaned = createCleanCopy(readings);
        System.out.print("清理後資料：");
        printArray(cleaned);
        System.out.print("原始資料：");
        printArray(readings);
    }

    private static boolean isValid(int value) {
        return value >= MIN_VALID && value <= MAX_VALID;
    }

    public static int countValid(int[] data) {
        if (data == null) {
            return 0;
        }
        int count = 0;
        for (int value : data) {
            if (isValid(value)) {
                count++;
            }
        }
        return count;
    }

    public static double averageValid(int[] data) {
        if (data == null) {
            return -1.0;
        }
        int count = 0;
        long sum = 0;
        for (int value : data) {
            if (isValid(value)) {
                sum += value;
                count++;
            }
        }
        if (count == 0) {
            return -1.0;
        }
        return (double) sum / count;
    }

    public static int findLastAtLeast(int[] data, int target) {
        if (data == null) {
            return -1;
        }
        for (int i = data.length - 1; i >= 0; i--) {
            if (isValid(data[i]) && data[i] >= target) {
                return i;
            }
        }
        return -1;
    }

    public static int[] createCleanCopy(int[] data) {
        if (data == null) {
            return null;
        }
        int[] cleanArray = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            if (isValid(data[i])) {
                cleanArray[i] = data[i];
            } else {
                cleanArray[i] = INVALID_MARK;
            }
        }
        return cleanArray;
    }

    private static void printArray(int[] data) {
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
