import java.util.ArrayList;

public class ProductArrayListDemo {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("P01", "Keyboard", 10));
        products.add(new Product("P02", "Mouse", 6));
        products.add(new Product("P03", "Monitor", 3));

        Product found = findByCode(products, "P02");
        if (found != null) {
            found.addStock(4);
        }

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static Product findByCode(
        ArrayList<Product> products,
        String code
    ) {
        for (Product product : products) {
            if (product.getCode().equalsIgnoreCase(code)) {
                return product;
            }
        }
        return null;
    }
}
