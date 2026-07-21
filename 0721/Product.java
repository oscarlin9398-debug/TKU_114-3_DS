public class Product {
    private String code;
    private String name;
    private int stock;

    public Product(String code, String name, int stock) {
        this.code = code;
        this.name = name;
        this.stock = Math.max(stock, 0);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void addStock(int amount) {
        if (amount > 0) {
            stock += amount;
        }
    }

    @Override
    public String toString() {
        return code + " | " + name + " | stock=" + stock;
    }
}
