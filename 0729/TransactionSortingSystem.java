public class TransactionSortingSystem {
    public static void main(String[] args) {
        Transaction[] transactions = {
            new Transaction("TXN1001", "ACC-883019", 15000, 1680000005L),
            new Transaction("TXN1002", "ACC-120482",  3200, 1680000001L),
            new Transaction("TXN1003", "ACC-552910", 15000, 1680000002L), // 與 TXN1001 同金額，時間較早 (應排在前面)
            new Transaction("TXN1004", "ACC-991024", 50000, 1680000004L), // 最高金額
            new Transaction("TXN1005", "ACC-331092",  3200, 1680000003L), // 與 TXN1002 同金額，時間較晚
            new Transaction("TXN1006", "ACC-771239", 15000, 1680000003L), // 與 TXN1001, TXN1003 同金額，時間居中
            new Transaction("TXN1007", "ACC-449102",  8500, 1680000006L)
        };

        System.out.println("=== 排序前的原始交易紀錄 ===");
        printTransactions(transactions);

        // 複製一份資料進行排序，保護原始紀錄
        Transaction[] sortedTransactions = copyTransactions(transactions);
        insertionSortTransactions(sortedTransactions);

        System.out.println("\n=== 排序後的交易紀錄 (金額降冪 -> 時間序號升冪) ===");
        printTransactions(sortedTransactions);
    }

    public static void insertionSortTransactions(Transaction[] transactions) {
        if (transactions == null || transactions.length <= 1) {
            return;
        }

        for (int index = 1; index < transactions.length; index++) {
            Transaction key = transactions[index];
            if (key == null) {
                continue;
            }

            int position = index - 1;

            while (position >= 0 && transactions[position] != null && shouldSwap(transactions[position], key)) {
                transactions[position + 1] = transactions[position];
                position--;
            }

            transactions[position + 1] = key;
        }
    }

    private static boolean shouldSwap(Transaction current, Transaction key) {
        // 第一優先：金額較大者往前排（金額降冪）
        if (current.getAmount() < key.getAmount()) {
            return true;
        }

        // 第二優先：金額相同時，時間序號較小者往前排（時間升冪）
        if (current.getAmount() == key.getAmount() && current.getTimestamp() > key.getTimestamp()) {
            return true;
        }

        return false;
    }

    public static Transaction[] copyTransactions(Transaction[] source) {
        if (source == null) {
            return new Transaction[0];
        }

        Transaction[] copy = new Transaction[source.length];
        for (int i = 0; i < source.length; i++) {
            if (source[i] != null) {
                copy[i] = new Transaction(
                        source[i].getTransactionId(),
                        source[i].getAccountNumber(),
                        source[i].getAmount(),
                        source[i].getTimestamp()
                );
            }
        }
        return copy;
    }

    public static void printTransactions(Transaction[] transactions) {
        if (transactions == null || transactions.length == 0) {
            System.out.println("無交易紀錄資料。");
            return;
        }

        System.out.println("-----------------------------------------------------------------------------");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println("-----------------------------------------------------------------------------");
    }
}
