import java.util.Scanner;

public class ScannerLineDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        System.out.println("姓名：" + name);
        System.out.println("年齡：" + age);

        sc.close();
    }
}
