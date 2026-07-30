import java.util.Scanner;

public class SeatNumberSearchPractice {
    public static void main(String[] args) {
        int[] seatNumbers = {101, 105, 108, 112, 115, 120, 125, 128, 130, 135, 140, 145};
        Scanner scanner = new Scanner(System.in);

        System.out.print("請輸入要搜尋的座位編號：");
        if (scanner.hasNextInt()) {
            int targetSeat = scanner.nextInt();
            searchAndPrint(seatNumbers, targetSeat);
        } else {
            System.out.println("輸入格式錯誤，請輸入整數編號。");
        }

        scanner.close();
    }

    public static void searchAndPrint(int[] values, int target) {
        int index = binarySearchTrace(values, target);

        System.out.println("----------------------------------------");
        if (index != -1) {
            System.out.println("搜尋成功！座位 " + target + " 位於索引：" + index);
        } else {
            System.out.println("搜尋失敗！找不到座位編號 " + target);
        }
    }

    public static int binarySearchTrace(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int round = 1;

        System.out.println("\n--- 開始二分搜尋（目標：" + target + "）---");

        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.println("第 " + round + " 輪：low=" + low + ", mid=" + mid + " (值:" + values[mid] + "), high=" + high);

            if (values[mid] == target) {
                return mid;
            } else if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            round++;
        }

        return -1;
    }
}
