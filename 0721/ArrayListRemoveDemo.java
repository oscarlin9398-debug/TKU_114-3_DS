import java.util.ArrayList;

public class ArrayListRemoveDemo {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Amy");
        names.add("Ben");
        names.add("Cara");

        String removed = names.remove(1);
        boolean foundAndRemoved = names.remove("Amy");

        System.out.println("依索引刪除：" + removed);
        System.out.println("依內容刪除成功：" + foundAndRemoved);
        System.out.println("剩餘名單：" + names);
    }
}
