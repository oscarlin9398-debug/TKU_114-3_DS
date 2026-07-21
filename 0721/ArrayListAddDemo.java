import java.util.ArrayList;

public class ArrayListAddDemo {
    public static void main(String[] args) {
        ArrayList<String> courses = new ArrayList<>();

        courses.add("Java");
        courses.add("Python");
        courses.add(1, "SQL");

        System.out.println("課程數量：" + courses.size());
        System.out.println("課程清單：" + courses);
    }
}
