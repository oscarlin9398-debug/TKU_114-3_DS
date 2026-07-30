public class AllOccurrenceSearch {
    public static void main(String[] args) {
        int[] numbers = {15, 23, 15, 42, 15, 8, 23, 99};

        System.out.println("=== 測試 1：搜尋多次出現的數值 (15) ===");
        findAllOccurrences(numbers, 15);

        System.out.println("\n=== 測試 2：搜尋出現一次的數值 (42) ===");
        findAllOccurrences(numbers, 42);

        System.out.println("\n=== 測試 3：搜尋不存在的數值 (100) ===");
        findAllOccurrences(numbers, 100);
    }

    public static void findAllOccurrences(int[] values, int target) {
        if (values == null || values.length == 0) {
            System.out.println("陣列為空，無法搜尋。");
            return;
        }

        int checks = 0;
        int count = 0;

        StringBuilder indices = new StringBuilder();

        for (int index = 0; index < values.length; index++) {
            checks++;
            if (values[index] == target) {
                if (count > 0) {
                    indices.append(", ");
                }
                indices.append(index);
                count++;
            }
        }

        System.out.println("搜尋目標：" + target);
        if (count > 0) {
            System.out.println("找到位置（索引）：[" + indices.toString() + "]");
            System.out.println("總共出現次數：" + count + " 次");
        } else {
            System.out.println("搜尋結果：找不到數值 " + target);
        }
        System.out.println("實際比較次數：" + checks + " 次");
    }
}
