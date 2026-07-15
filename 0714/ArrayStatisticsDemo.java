public class ArrayStatisticsDemo {
    public static void main(String[] args) {
        int[] scores = {80, 75, 92, 68, 88};
        int total = 0;
        
        // 將最大值與最小值初始設定為陣列的第一個元素
        int max = scores[0];
        int min = scores[0];
        int passCount = 0;

        // 使用單一 for-each 迴圈完成所有統計
        for (int score : scores) {
            total += score;

            // 判斷並更新最大值
            if (score > max) {
                max = score;
            }
            // 判斷並更新最小值
            if (score < min) {
                min = score;
            }
            // 累計及格人數
            if (score >= 60) {
                passCount++;
            }
        }

        // 轉型為 double 避免整數除法造成誤差
        double average = (double) total / scores.length;

        System.out.println("總分：" + total);
        System.out.printf("平均：%.2f%n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("及格人數：" + passCount);
    }
}
