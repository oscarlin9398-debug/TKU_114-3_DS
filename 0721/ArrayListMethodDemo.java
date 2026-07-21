import java.util.ArrayList;

public class ArrayListMethodDemo {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(55);
        scores.add(80);
        scores.add(92);

        ArrayList<Integer> passed = filterAtLeast(scores, 60);
        System.out.println("原始資料：" + scores);
        System.out.println("及格資料：" + passed);
    }

    public static ArrayList<Integer> filterAtLeast(
        ArrayList<Integer> values,
        int target
    ) {
        ArrayList<Integer> result = new ArrayList<>();

        for (int value : values) {
            if (value >= target) {
                result.add(value);
            }
        }

        return result;
    }
}
