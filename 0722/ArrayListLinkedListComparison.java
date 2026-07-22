import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListLinkedListComparison {
    public static void main(String[] args) {
        List<String> fastIndexAccess = new ArrayList<>();
        List<String> linkedNodes = new LinkedList<>();

        fastIndexAccess.add("A");
        fastIndexAccess.add("B");
        linkedNodes.add("A");
        linkedNodes.add("B");

        System.out.println("ArrayList index 1：" + fastIndexAccess.get(1));
        linkedNodes.add(0, "Start");
        System.out.println("LinkedList：" + linkedNodes);
    }
}
