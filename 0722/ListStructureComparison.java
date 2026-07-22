import java.util.ArrayList;
import java.util.LinkedList;

public class ListStructureComparison {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();

        arrayList.add("A");
        arrayList.add("B");
        linkedList.add("A");
        linkedList.add("B");

        arrayList.add(0, "Start");
        linkedList.addFirst("Start");

        System.out.println("ArrayList：" + arrayList);
        System.out.println("LinkedList：" + linkedList);
    }
}
