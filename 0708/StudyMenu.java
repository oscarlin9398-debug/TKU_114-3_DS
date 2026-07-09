import java.util.Scanner;

public class StudyMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("=== Study Menu ===");
            System.out.println("1：輸出 Review Java");
            System.out.println("2：輸出 Practice loops");
            System.out.println("3：輸出 Push to GitHub");
            System.out.println("0：離開");
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Review Java");
                    break;
                case 2:
                    System.out.println("Practice loops");
                    break;
                case 3:
                    System.out.println("Push to GitHub");
                    break;
                case 0:
                    System.out.println("離開系統");
                    break;
                default:
                    System.out.println("不正確的選項，請重新輸入");
            }
        }

        sc.close();
    }
}
