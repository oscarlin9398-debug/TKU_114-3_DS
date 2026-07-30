public class Order {
    private String orderId;
    private String customerName;
    private double amount;

    public Order(String orderId, String customerName, double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("訂單編號: %-6s | 顧客姓名: %-8s | 金額: $%.2f", orderId, customerName, amount);
    }
}