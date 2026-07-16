import java.util.Scanner;

public class Q02_ScannerInputFix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入數量：");
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入課程名稱：");
        String courseName = sc.nextLine();

        System.out.print("請輸入單價：");
        int price = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入備註：");
        String note = sc.nextLine();

        int total = quantity * price;

        System.out.println("=== 登記結果 ===");
        System.out.println("課程：" + courseName);
        System.out.println("數量：" + quantity);
        System.out.println("總額：" + total);
        System.out.println("備註：" + note);

        sc.close();
    }
}
