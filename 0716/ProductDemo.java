public class ProductDemo {
    public static void main(String[] args) {
        Product product = new Product("Keyboard", 890, 12);

        System.out.println("原始資料：" + product);

        if (product.setPrice(920)) {
            System.out.println("價格修改成功");
        }

        if (product.restock(5)) {
            System.out.println("補貨成功");
        }

        if (product.sell(3)) {
            System.out.println("出售成功");
        }

        System.out.println("更新後：" + product);
        System.out.println("低庫存：" + product.isLowStock());
        System.out.println("庫存價值：" + product.getInventoryValue());
    }
}
