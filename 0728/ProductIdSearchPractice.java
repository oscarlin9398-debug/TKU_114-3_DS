import java.util.Scanner;

public class ProductIdSearchPractice {
    public static void main(String[] args) {
        int[] productIds = {305, 102, 450, 218, 110, 520, 326, 118};
        Scanner scanner = new Scanner(System.in);

        System.out.print("請輸入要搜尋的商品編號：");
        if (scanner.hasNextInt()) {
            int targetId = scanner.nextInt();
            searchAndPrint(productIds, targetId);
        } else {
            System.out.println("輸入格式錯誤，請輸入整數編號。");
        }

        scanner.close();
    }

    public static void searchAndPrint(int[] values, int target) {
        int checks = 0;
        int foundIndex = -1;

        for (int index = 0; index < values.length; index++) {
            checks++;
            if (values[index] == target) {
                foundIndex = index;
                break;
            }
        }

        if (foundIndex != -1) {
            System.out.println("找到商品！索引位置：" + foundIndex);
        } else {
            System.out.println("找不到商品編號 " + target);
        }
        System.out.println("實際比較次數：" + checks);
    }
}
