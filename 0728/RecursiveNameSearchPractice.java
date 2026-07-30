public class RecursiveNameSearchPractice {
    public static void main(String[] args) {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve"};
        String[] emptyNames = {};

        System.out.println("=== 測試 1：尋找第一筆 (Alice) ===");
        testSearch(names, "Alice");

        System.out.println("\n=== 測試 2：尋找最後一筆 (Eve) ===");
        testSearch(names, "Eve");

        System.out.println("\n=== 測試 3：尋找不存在資料 (Frank) ===");
        testSearch(names, "Frank");

        System.out.println("\n=== 測試 4：尋找空陣列 (Alice) ===");
        testSearch(emptyNames, "Alice");
    }

    public static int search(String[] names, String target, int index) {
        if (names == null || target == null || index >= names.length) {
            return -1;
        }

        if (names[index] != null && names[index].equals(target)) {
            return index;
        }

        return search(names, target, index + 1);
    }

    public static void testSearch(String[] names, String target) {
        int index = search(names, target, 0);
        if (index != -1) {
            System.out.println("找到 \"" + target + "\"，位於索引：" + index);
        } else {
            System.out.println("找不到 \"" + target + "\"（回傳 -1）");
        }
    }
}
