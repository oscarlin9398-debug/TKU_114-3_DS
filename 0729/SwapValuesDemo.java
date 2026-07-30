import java.util.Arrays;

public class SwapValuesDemo {
    public static void main(String[] args) {
        int[] values = {40, 10, 30, 20};

        System.out.println("交換前：" + Arrays.toString(values));
        swap(values, 0, 3);
        System.out.println("交換後：" + Arrays.toString(values));
    }

    public static void swap(int[] values, int left, int right) {
        int temp = values[left];
        values[left] = values[right];
        values[right] = temp;
    }
}
