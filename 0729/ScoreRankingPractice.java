import java.util.Arrays;

public class ScoreRankingPractice {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 92, 55, 60, 48, 85};

        System.out.println("原始成績：" + Arrays.toString(scores));
        System.out.println("----------------------------------------");

        selectionSortDescending(scores);
        displayRankings(scores);
    }

    public static void selectionSortDescending(int[] scores) {
        if (scores == null || scores.length <= 1) {
            return;
        }

        for (int start = 0; start < scores.length - 1; start++) {
            int maxIndex = start;

            for (int current = start + 1; current < scores.length; current++) {
                if (scores[current] > scores[maxIndex]) {
                    maxIndex = current;
                }
            }

            if (maxIndex != start) {
                int temp = scores[start];
                scores[start] = scores[maxIndex];
                scores[maxIndex] = temp;
            }
        }
    }

    public static void displayRankings(int[] sortedScores) {
        if (sortedScores == null || sortedScores.length == 0) {
            System.out.println("無成績資料可顯示。");
            return;
        }

        System.out.printf("%-6s | %-6s | %-6s%n", "名次", "分數", "狀態");
        System.out.println("----------------------------------------");

        int currentRank = 1;

        for (int i = 0; i < sortedScores.length; i++) {
            if (i > 0 && sortedScores[i] < sortedScores[i - 1]) {
                currentRank = i + 1;
            }

            int score = sortedScores[i];
            String status = (score >= 60) ? "及格" : "不及格";

            System.out.printf("第 %-4d 名 | %-6d | %s%n", currentRank, score, status);
        }
    }
}
