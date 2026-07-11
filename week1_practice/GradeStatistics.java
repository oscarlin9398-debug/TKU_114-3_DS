import java.util.Scanner;

public class GradeStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;
        int total = 0;
        int max = -1;          // 用一個不可能的低分當最高分起點
        int min = 101;         // 用一個不可能的高分當最低分起點
        int passCount = 0;
        int failCount = 0;

        while (true) {
            System.out.print("請輸入成績（輸入 -1 結束）：");
            int score = sc.nextInt();

            // 如果第一個輸入就是 -1，或者中途輸入 -1，就跳出迴圈
            if (score == -1) {
                break;
            }

            // 呼叫驗證方法，如果不合法就提示並跳過這次迴圈
            if (!isValidScore(score)) {
                System.out.println("錯誤：成績必須在 0 到 100 之間！請重新輸入。");
                continue;
            }

            // 資料合法，開始累加與計數
            count++;
            total += score;

            // 判斷最高分與最低分
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }

            // 判斷及格與不及格人數
            if (isPassing(score)) {
                passCount++;
            } else {
                failCount++;
            }
        }

        // 檢查特殊情況：如果完全沒有輸入任何成績就結束
        if (count == 0) {
            System.out.println("No scores entered.");
        } else {
            // 計算平均
            double average = (double) total / count;
            String grade = getGrade(average);

            // 呼叫方法印出總結報表
            printSummary(count, total, average, max, min, passCount, failCount, grade);
        }

        sc.close();
    }

    // 驗證成績是否在合法範圍
    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }

    // 判斷是否及格
    public static boolean isPassing(int score) {
        return score >= 60;
    }

    // 根據平均分數判斷等級
    public static String getGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // 輸出最終成績統計結果
    public static void printSummary(int count, int total, double average, int max, int min, int passCount, int failCount, String grade) {
        System.out.println();
        System.out.println("=== Grade Summary ===");
        System.out.println("Count: " + count);
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Pass count: " + passCount);
        System.out.println("Fail count: " + failCount);
        System.out.println("Average grade: " + grade);
    }
}
