public class ArrayTraversal {
    public static void main(String[] args) {
        int[] scores = {80, 75, 92, 68, 88};

        // 概念 5：使用一般 for 迴圈（可以拿到索引 i）
        System.out.println("=== 一般 for 迴圈走訪 ===");
        for (int i = 0; i < scores.length; i++) {
            System.out.println("索引 " + i + "：" + scores[i]);
        }

        // 概念 6：使用 for-each 迴圈（簡潔，純讀取值）
        System.out.println("=== for-each 迴圈走訪 ===");
        for (int score : scores) {
            System.out.println(score);
        }
    }
}