public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount first = new BankAccount("A001", "Amy", 5000);
        BankAccount second = new BankAccount("A002", "Ben", 1000);

        System.out.println("原始帳戶狀態：");
        System.out.println(first);
        System.out.println(second);
        System.out.println();

        System.out.println("--- 測試存款與提款 ---");
        System.out.println("Amy 存款 500 元：" + first.deposit(500));
        System.out.println("Ben 提款 300 元：" + second.withdraw(300));
        System.out.println("Ben 提款 -50 元 (應失敗)：" + second.withdraw(-50));
        System.out.println();

        System.out.println("--- 測試成功轉帳 ---");
        System.out.println("Amy 轉帳 2000 元給 Ben：" + first.transferTo(second, 2000));
        System.out.println("Amy 帳戶狀態：" + first);
        System.out.println("Ben 帳戶狀態：" + second);
        System.out.println();

        System.out.println("--- 測試失敗轉帳 (餘額不足) ---");
        System.out.println("Ben 轉帳 10000 元給 Amy (應失敗)：" + second.transferTo(first, 10000));
        System.out.println();

        System.out.println("最終帳戶狀態：");
        System.out.println(first);
        System.out.println(second);
    }
}
