import java.util.ArrayList;
import java.util.Arrays;

public class Q01_ArrayListCleanup {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>(
            Arrays.asList(72, 35, 28, 80, 41, 39, 90)
        );
        int removed = removeBelow(scores, 40);
        System.out.println("移除筆數：" + removed);
        System.out.println("保留資料：" + scores);
    }

    public static int removeBelow(ArrayList<Integer> scores, int minimum) {
        if (scores == null || scores.isEmpty()) {
            return 0;
        }
        int removedCount = 0;
        for (int i = scores.size() - 1; i >= 0; i--) {
            if (scores.get(i) < minimum) {
                scores.remove(i);
                removedCount++;
            }
        }
        return removedCount;
    }
}