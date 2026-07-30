public class Transaction {
    private String transactionId;
    private String accountNumber;
    private int amount;
    private long timestamp;

    public Transaction(String transactionId, String accountNumber, int amount, long timestamp) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("交易編號: %-8s | 帳號: %-12s | 金額: NT$ %-7d | 時間序號: %d",
                transactionId, accountNumber, amount, timestamp);
    }
}
