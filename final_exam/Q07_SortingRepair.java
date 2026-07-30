import java.util.Arrays;

public class Q07_SortingRepair {

    public static void main(String[] args) {
        int[] source = {31, 12, 45, 12, 8, 27};
        System.out.println("Selection 降冪：" + Arrays.toString(selectionSortDescending(source)));
        System.out.println("Insertion 升冪：" + Arrays.toString(insertionSortAscending(source)));
        System.out.println("原始資料：" + Arrays.toString(source));
    }

    public static int[] selectionSortDescending(int[] source) {
        if (source == null) {
            return new int[0];
        }
        int[] result = source.clone();
        for (int start = 0; start < result.length - 1; start++) {
            int selectedIndex = start;
            for (int index = start + 1; index < result.length; index++) {
                if (result[index] > result[selectedIndex]) {
                    selectedIndex = index;
                }
            }
            int temp = result[start];
            result[start] = result[selectedIndex];
            result[selectedIndex] = temp;
        }
        return result;
    }

    public static int[] insertionSortAscending(int[] source) {
        if (source == null) {
            return new int[0];
        }
        int[] result = source.clone();
        for (int index = 1; index < result.length; index++) {
            int key = result[index];
            int position = index - 1;
            while (position >= 0 && result[position] > key) {
                result[position + 1] = result[position];
                position--;
            }
            result[position + 1] = key;
        }
        return result;
    }
}
