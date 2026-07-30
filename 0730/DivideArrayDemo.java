import java.util.Arrays;

public class DivideArrayDemo {
    public static void main(String[] args) {
        int[] values = {38, 27, 43, 3};
        printRanges(values, 0, values.length - 1, 0);
    }

    public static void printRanges(
        int[] values,
        int left,
        int right,
        int depth
    ) {
        String indent = "  ".repeat(depth);
        int[] range = Arrays.copyOfRange(values, left, right + 1);
        System.out.println(indent + Arrays.toString(range));

        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        printRanges(values, left, mid, depth + 1);
        printRanges(values, mid + 1, right, depth + 1);
    }
}
