import java.util.Scanner;

public class AtmMethodHomework {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        int balance = 1000;
        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項：");
            option = s.nextInt();

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;

                case 2:
                    int depositAmount = readPositiveAmount(s, "請輸入存款金額：");
                    balance = deposit(balance, depositAmount);
                    System.out.println("存款成功！");
                    printBalance(balance);
                    break;

                case 3:
                    int withdrawAmount = readPositiveAmount(s, "請輸入提款金額：");
                    while (withdrawAmount > balance) {
                        System.out.println("餘額不足，請重新輸入。");
                        withdrawAmount = readPositiveAmount(s, "請輸入提款金額：");
                    }
                    balance = withdraw(balance, withdrawAmount);
                    System.out.println("提款成功！請取走您的現金。");
                    printBalance(balance);
                    break;

                case 0:
                    System.out.println("謝謝光臨，請記得取回您的卡片！");
                    break;

                default:
                    System.out.println("不正確的選項，請重新輸入。");
                    System.out.println();
            }
        }

        s.close();
    }

    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1：查詢餘額");
        System.out.println("2：存款");
        System.out.println("3：提款");
        System.out.println("0：離開");
    }

    public static int readPositiveAmount(Scanner s, String message) {
        System.out.print(message);
        int amount = s.nextInt();
        
        while (amount <= 0) {
            System.out.print("金額必須大於 0，請重新輸入：");
            amount = s.nextInt();
        }
        
        return amount;
    }

    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    public static void printBalance(int balance) {
        System.out.println("目前餘額為：" + balance + " 元");
        System.out.println();
    }
}
