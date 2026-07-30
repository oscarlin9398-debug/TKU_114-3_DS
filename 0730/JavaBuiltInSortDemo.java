import java.util.Arrays;

public class JavaBuiltInSortDemo {
    public static void main(String[] args) {
        int[] values = {38, 27, 43, 3, 9};
        String[] names = {"Cara", "Amy", "Dan", "Ben"};

        Arrays.sort(values);
        Arrays.sort(names);

        System.out.println("數字：" + Arrays.toString(values));
        System.out.println("姓名：" + Arrays.toString(names));
    }
}
