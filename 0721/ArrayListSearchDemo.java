import java.util.ArrayList;

public class ArrayListSearchDemo {
    public static void main(String[] args) {
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("SQL");
        courses.add("Web");

        String keyword = "SQL";
        boolean exists = courses.contains(keyword);
        int index = courses.indexOf(keyword);

        System.out.println("是否存在：" + exists);
        System.out.println("所在索引：" + index);
        System.out.println("找不到的結果：" + courses.indexOf("Python"));
    }
}
