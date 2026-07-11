import java.util.Scanner;

public class PatternGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        // 利用 while 迴圈讓選單可以重複操作，直到使用者輸入 0 才會跳出
        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            // 依據輸入的選項決定呼叫哪一個圖形生成方法
            switch (option) {
                case 1:
                    printMultiplicationTable();
                    break;
                case 2:
                    int height = readPositiveInt(sc, "請輸入正三角形高度：");
                    printTriangle(height);
                    break;
                case 3:
                    int revHeight = readPositiveInt(sc, "請輸入倒三角形高度：");
                    printReverseTriangle(revHeight);
                    break;
                case 4:
                    int rows = readPositiveInt(sc, "請輸入方格列數（Row）：");
                    int cols = readPositiveInt(sc, "請輸入方格欄數（Column）：");
                    printNumberGrid(rows, cols);
                    break;
                case 0:
                    System.out.println("系統已離開。");
                    break;
                default:
                    System.out.println("錯誤：無效選項，請重新輸入。");
                    System.out.println();
            }
        }
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== 圖形與表格產生器 ===");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形星號");
        System.out.println("3. 倒三角形星號");
        System.out.println("4. 數字方格");
        System.out.println("0. 離開");
    }

    // 驗證輸入的整數是否合法（必須大於 0）
    public static int readPositiveInt(Scanner sc, String message) {
        System.out.print(message);
        int value = sc.nextInt();
        
        // 防錯機制：如果使用者輸入 0 或負數，會強制進入迴圈要求重新輸入
        while (value <= 0) {
            System.out.println("錯誤：數值必須大於 0！");
            System.out.print(message);
            value = sc.nextInt();
        }
        return value;
    }

    // 列印 9x9 乘法表
    public static void printMultiplicationTable() {
        System.out.println("\n--- 九九乘法表 ---");
        // 外層迴圈控制被乘數（1到9），內層迴圈控制乘數（1到9）
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                // 使用 \t 讓每個乘法公式在畫面上可以對齊
                System.out.print(i + "*" + j + "=" + (i * j) + "\t");
            }
            System.out.println(); // 每一列印完後換行
        }
        System.out.println();
    }

    // 列印正三角形星號
    public static void printTriangle(int height) {
        System.out.println("\n--- 正三角形 ---");
        // 控制總共有幾列，每一列的星號數量剛好等於目前的列數 i
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 列印倒三角形星號
    public static void printReverseTriangle(int height) {
        System.out.println("\n--- 倒三角形 ---");
        // 控制總共有幾列，採倒數方式（從高度倒數到1），每一列印出 i 個星號
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 列印指定列數與欄數的數字方格
    public static void printNumberGrid(int rows, int cols) {
        System.out.println("\n--- 數字方格 ---");
        // 外層迴圈控制總列數（Row），內層迴圈控制總欄數（Column）
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                // 每一列都從數字 1 開始往後印到指定的欄數
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
