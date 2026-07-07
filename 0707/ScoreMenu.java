import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = s.nextLine();

        System.out.print("請輸入 Java 成績：");
        double javaScore = s.nextDouble();

        System.out.print("請輸入 English 成績：");
        double englishScore = s.nextDouble();

        System.out.print("請輸入 Math 成績：");
        double mathScore = s.nextDouble();

        double average = (javaScore + englishScore + mathScore) / 3.0;
        String status = (average >= 60) ? "Pass" : "Fail";
        String grade = "";

        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        int option = -1;
        while (option != 0) {
            System.out.println("\n=== 選單 ===");
            System.out.println("1：顯示平均分數");
            System.out.println("2：顯示及格狀態");
            System.out.println("3：顯示等第");
            System.out.println("0：離開");
            System.out.print("請選擇功能：");
            option = s.nextInt();

            switch (option) {
                case 1:
                    System.out.println("平均分數: " + average);
                    break;
                case 2:
                    System.out.println("及格狀態: " + status);
                    break;
                case 3:
                    System.out.println("等第: " + grade);
                    break;
                case 0:
                    System.out.println("系統已結束。");
                    break;
                default:
                    System.out.println("未知選項，請重新輸入。");
            }
        }
        s.close();
    }
}
