public class TwoDimensionalArray {
    public static void main(String[] args) {
        int[][] matrix = {
            {10, 20, 30},
            {40, 50, 60}
        };

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                System.out.printf("%4d", matrix[row][column]);
            }
            System.out.println();
        }
    }
}
