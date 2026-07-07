import java.util.Scanner;

public class WhileInputDemo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int number = -1;

        while (number != 0) {
            System.out.print("請輸入整數 ：");
            number = s.nextInt();

            if (number != 0) {
                System.out.println("輸入了: " + number);
            }
        }

        s.close();
    }
}
