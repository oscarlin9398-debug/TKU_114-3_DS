import java.util.Scanner;

public class SimpleMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("=== Menu ===");
            System.out.println("1. Say hello");
            System.out.println("2. Say Java");
            System.out.println("0. Exit");
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Hello");
                    break;
                case 2:
                    System.out.println("Java");
                    break;
                case 0:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Unknown option");
            }
        }

        sc.close();
    }
}
