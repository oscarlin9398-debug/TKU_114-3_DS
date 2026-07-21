import java.util.ArrayList;

public class SafeRemoveDemo {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(40);
        scores.add(80);
        scores.add(50);
        scores.add(90);

        for (int i = scores.size() - 1; i >= 0; i--) {
            if (scores.get(i) < 60) {
                scores.remove(i);
            }
        }

        System.out.println("保留的成績：" + scores);
    }
}
