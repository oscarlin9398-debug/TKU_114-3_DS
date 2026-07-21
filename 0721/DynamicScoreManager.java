import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        while (true) {
            System.out.print("請輸入成績 (0-100)，輸入 -1 結束：");
            if (!scanner.hasNextInt()) {
                System.out.println("輸入錯誤，請輸入整數！");
                scanner.next();
                continue;
            }

            int input = scanner.nextInt();
            if (input == -1) {
                break;
            }

            if (input < 0 || input > 100) {
                System.out.println("成績範圍必須介於 0 到 100 之間！");
            } else {
                scores.add(input);
            }
        }

        if (scores.isEmpty()) {
            System.out.println("未輸入任何有效成績。");
        } else {
            System.out.println("\n--- 統計結果 ---");
            System.out.println("總筆數：" + scores.size());
            System.out.println("平均分數：" + getAverage(scores));
            System.out.println("最高分：" + getMax(scores));
            System.out.println("最低分：" + getMin(scores));
            System.out.println("及格名單：" + getPassedScores(scores));
        }

        scanner.close();
    }

    public static double getAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return (double) total / scores.size();
    }

    public static int getMax(ArrayList<Integer> scores) {
        int max = scores.get(0);
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    public static int getMin(ArrayList<Integer> scores) {
        int min = scores.get(0);
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

    public static ArrayList<Integer> getPassedScores(ArrayList<Integer> scores) {
        ArrayList<Integer> passed = new ArrayList<>();
        for (int score : scores) {
            if (score >= 60) {
                passed.add(score);
            }
        }
        return passed;
    }
}
