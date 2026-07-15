import java.util.Scanner;

public class NumberParsingDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入整數：");
        String input = sc.nextLine();

        try {
            int value = Integer.parseInt(input.trim());
            System.out.println("兩倍為：" + value * 2);
        } catch (NumberFormatException e) {
            System.out.println("格式錯誤，必須輸入整數");
        }

        sc.close();
    }
}
