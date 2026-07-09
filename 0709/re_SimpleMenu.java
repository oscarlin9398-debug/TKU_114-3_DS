import java.util.Scanner;

public class re_SimpleMenu {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項：");
            option = s.nextInt();

            switch (option) {
                case 1:
                    System.out.println("您選擇了功能 1");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("您選擇了功能 2");
                    System.out.println();
                    break;
                case 0:
                    System.out.println("系統已離開");
                    break;
                default:
                    System.out.println("無效選項，請重新輸入。");
                    System.out.println();
            }
        }

        s.close();
    }

    public static void printMenu() {
        System.out.println("=== 系統選單 ===");
        System.out.println("1. 執行功能一");
        System.out.println("2. 執行功能二");
        System.out.println("0. 離開系統");
    }
}
