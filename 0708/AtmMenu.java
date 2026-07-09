import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int balance = 1000;
        int option = -1;

        while (option != 0) {
            System.out.println("=== ATM Menu ===");
            System.out.println("1：查詢餘額");
            System.out.println("2：存款");
            System.out.println("3：提款");
            System.out.println("0：離開");
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("目前餘額為：" + balance + " 元");
                    System.out.println();
                    break;

                case 2:
                    System.out.print("請輸入存款金額：");
                    int deposit = sc.nextInt();
                    
                    while (deposit <= 0) {
                        System.out.print("存款金額必須大於 0，請重新輸入：");
                        deposit = sc.nextInt();
                    }
                    
                    balance += deposit;
                    System.out.println("存款成功！目前餘額為：" + balance + " 元");
                    System.out.println();
                    break;

                case 3:
                    System.out.print("請輸入提款金額：");
                    int withdraw = sc.nextInt();
                    
                    while (withdraw <= 0 || withdraw > balance) {
                        if (withdraw <= 0) {
                            System.out.print("提款金額必須大於 0，請重新輸入：");
                        } else {
                            System.out.print("餘額不足（目前餘額 " + balance + " 元），請重新輸入：");
                        }
                        withdraw = sc.nextInt();
                    }
                    
                    balance -= withdraw;
                    System.out.println("提款成功！請取走您的現金。目前餘額為：" + balance + " 元");
                    System.out.println();
                    break;

                case 0:
                    System.out.println("謝謝光臨，請記得取回您的卡片！");
                    break;

                default:
                    System.out.println("不正確的選項，請重新輸入。");
                    System.out.println();
            }
        }

        sc.close();
    }
}
