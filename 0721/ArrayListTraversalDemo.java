import java.util.ArrayList;

public class ArrayListTraversalDemo {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(80);
        scores.add(92);
        scores.add(76);

        for (int i = 0; i < scores.size(); i++) {
            System.out.println("索引 " + i + "：" + scores.get(i));
        }

        int total = 0;
        for (int score : scores) {
            total += score;
        }

        System.out.println("總分：" + total);
        System.out.println("平均：" + (double) total / scores.size());
    }
}
