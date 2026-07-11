import java.util.Scanner;

public class AtmSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int balance = 1000;       // 初始餘額
        int depositCount = 0;    // 成功存款次數
        int withdrawCount = 0;   // 成功提款次數
        int totalDeposit = 0;    // 總存款金額
        int totalWithdraw = 0;   // 總提款金額

        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    // 查詢餘額
                    printBalance(balance);
                    break;

                case 2:
                    // 處理存款
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, depositAmount);
                    
                    // 更新統計數據
                    depositCount++;
                    totalDeposit += depositAmount;
                    
                    System.out.println("Balance: " + balance);
                    System.out.println();
                    break;

                case 3:
                    // 處理提款
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    
                    // 驗證提款金額是否大於餘額
                    while (!canWithdraw(balance, withdrawAmount)) {
                        System.out.println("錯誤：餘額不足，無法提款！");
                        withdrawAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    }
                    
                    balance = withdraw(balance, withdrawAmount);
                    
                    // 更新統計數據
                    withdrawCount++;
                    totalWithdraw += withdrawAmount;
                    
                    System.out.println("Balance: " + balance);
                    System.out.println();
                    break;

                case 4:
                    // 顯示目前操作統計（複用最後的總結方法印出即時狀況）
                    printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                    break;

                case 0:
                    // 離開系統
                    break;

                default:
                    System.out.println("錯誤：無效的選項，請重新輸入。");
                    System.out.println();
            }
        }

        // 離開迴圈後印出最終完整摘要
        printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
        sc.close();
    }

    // 顯示 ATM 功能選單
    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
    }

    // 讀取合法金額（金額必須大於 0）
    public static int readPositiveAmount(Scanner sc, String message) {
        System.out.print(message);
        int amount = sc.nextInt();
        
        while (amount <= 0) {
            System.out.print("錯誤：金額必須大於 0，請重新輸入：");
            amount = sc.nextInt();
        }
        return amount;
    }

    // 執行存款並回傳更新後的餘額
    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    // 執行提款並回傳更新後的餘額
    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    // 檢查目前餘額是否足夠讓使用者提款
    public static boolean canWithdraw(int balance, int amount) {
        return amount <= balance;
    }

    // 顯示目前的單純餘額
    public static void printBalance(int balance) {
        System.out.println("Balance: " + balance);
        System.out.println();
    }

    // 輸出操作摘要與最終數據
    public static void printSummary(int balance, int depositCount, int withdrawCount, int totalDeposit, int totalWithdraw) {
        System.out.println();
        System.out.println("=== ATM Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
        System.out.println();
    }
}
