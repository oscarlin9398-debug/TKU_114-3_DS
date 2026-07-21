import java.util.ArrayList;

public class ArrayListReadUpdateDemo {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(80);
        scores.add(75);
        scores.add(90);

        System.out.println("修改前第 2 筆：" + scores.get(1));
        scores.set(1, 85);
        System.out.println("修改後第 2 筆：" + scores.get(1));
        System.out.println("資料筆數：" + scores.size());
        System.out.println("全部成績：" + scores);
    }
}
