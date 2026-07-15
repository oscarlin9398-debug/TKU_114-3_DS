/*
1. 錯誤位置：System.out.println("系統結束，年齡：" + age)
   錯誤類型：編譯錯誤
   原因：語法不完整，結尾漏掉分號（;）。
   修正方式：在該行結尾補上分號。

2. 錯誤位置：for (int i = 0; i <= scores.length; i++)
   錯誤類型：執行錯誤 (ArrayIndexOutOfBoundsException)
   原因：迴圈終止條件使用 <=，導致當 i 等於 3 時，存取 scores[3] 發生陣列越界。
   修正方式：將 <= 改為 <。

3. 錯誤位置：double average = total / scores.length;
   錯誤類型：邏輯錯誤 
   原因：total 與 scores.length 皆為整數，相除會直接捨去小數點，導致 average 遺失精度。
   修正方式：將 total 強制轉型為 double，改寫為 (double) total / scores.length。

4. 錯誤位置：int age = sc.nextInt(); 之後
   錯誤類型：Scanner 換行問題
   原因：nextInt() 沒有讀走緩衝區中的換行符號，導致後續的 nextLine() 直接讀到空字串。
   修正方式：在 nextInt() 之後、nextLine() 之前插入一行 sc.nextLine() 清空緩衝區。

5. 錯誤位置：if (command == "exit")
   錯誤類型：字串比較錯誤
   原因：使用 == 比較字串，比的是記憶體位址而非文字內容。
   修正方式：改用 equals() 方法，寫成 "exit".equals(command)。

*/

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        if ("exit".equals(command)) {
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}
