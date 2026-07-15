import java.util.Scanner;

public class SalesMatrix {

    public static void inputSales(Scanner sc, int[][] sales) {
        for (int row = 0; row < sales.length; row++) {
            for (int col = 0; col < sales[row].length; col++) {
                int value;
                do {
                    System.out.print("請輸入商品 " + (row + 1) + " 第 " + (col + 1) + " 天的銷售量 (>= 0)：");
                    value = sc.nextInt();
                } while (value < 0);
                sales[row][col] = value;
            }
        }
    }

    public static void printSalesTable(int[][] sales) {
        System.out.println("\n===== 銷售矩陣報表 =====");
        System.out.println("        D1  D2  D3  D4");
        for (int row = 0; row < sales.length; row++) {
            System.out.print("商品 " + (row + 1) + "：");
            for (int col = 0; col < sales[row].length; col++) {
                System.out.printf("%4d", sales[row][col]);
            }
            System.out.println();
        }
        System.out.println("========================");
    }

    public static int[] calculateProductTotals(int[][] sales) {
        int[] totals = new int[sales.length];
        for (int row = 0; row < sales.length; row++) {
            int sum = 0;
            for (int col = 0; col < sales[row].length; col++) {
                sum += sales[row][col];
            }
            totals[row] = sum;
        }
        return totals;
    }

    public static int[] calculateDayTotals(int[][] sales) {
        int[] totals = new int[sales[0].length];
        for (int col = 0; col < sales[0].length; col++) {
            int sum = 0;
            for (int row = 0; row < sales.length; row++) {
                sum += sales[row][col];
            }
            totals[col] = sum;
        }
        return totals;
    }

    public static int findBestProduct(int[] productTotals) {
        int bestIndex = 0;
        int max = productTotals[0];
        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > max) {
                max = productTotals[i];
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sales = new int[3][4];

        inputSales(sc, sales);
        printSalesTable(sales);

        int[] productTotals = calculateProductTotals(sales);
        int[] dayTotals = calculateDayTotals(sales);

        System.out.println("\n[各商品銷售總量]");
        for (int i = 0; i < productTotals.length; i++) {
            System.out.println("商品 " + (i + 1) + " 總銷售量：" + productTotals[i]);
        }

        System.out.println("\n[每日全部商品銷售總量]");
        for (int i = 0; i < dayTotals.length; i++) {
            System.out.println("第 " + (i + 1) + " 天總銷售量：" + dayTotals[i]);
        }

        int bestProductIndex = findBestProduct(productTotals);
        System.out.println("\n總銷售量最高的商品：商品 " + (bestProductIndex + 1) 
                + " (共 " + productTotals[bestProductIndex] + " 件)");

        sc.close();
    }
}
