public class GrowthRateTable {
    public static void main(String[] args) {
        int[] sizes = {16, 1024, 65536};

        System.out.printf("%10s %18s %18s%n",
            "n", "n^2", "n*log2(n)");

        for (int size : sizes) {
            long quadratic = (long) size * size;
            long nLogN = Math.round(
                size * (Math.log(size) / Math.log(2))
            );
            System.out.printf("%10d %18d %18d%n",
                size, quadratic, nLogN);
        }
    }
}
