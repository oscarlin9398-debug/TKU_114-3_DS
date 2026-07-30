public class ProductSortPractice {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P101", "Gaming Mouse", 1290, 25),
            new Product("P102", "Mechanical KB", 2500, 10),
            new Product("P103", "USB-C Hub", 850, 40),
            new Product("P104", "Budget Mouse", 650, 50),
            new Product("P105", "BT Headset", 1290, 15),
            new Product("P106", "Gaming Monitor", 5200, 8),
            new Product("P107", "Webcam 1080p", 1290, 30),
            new Product("P108", "Mouse Pad", 350, 100)
        };

        System.out.println("=== 排序前的商品列表 ===");
        printProducts(products);

        insertionSortByPrice(products);

        System.out.println("\n=== 依價格升冪排序後的商品列表 (驗證穩定性) ===");
        printProducts(products);
    }

    public static void insertionSortByPrice(Product[] products) {
        if (products == null || products.length <= 1) {
            return;
        }

        for (int index = 1; index < products.length; index++) {
            Product key = products[index];
            if (key == null) {
                continue;
            }

            int position = index - 1;

            while (position >= 0 && 
                   products[position] != null && 
                   products[position].getPrice() > key.getPrice()) {
                
                products[position + 1] = products[position];
                position--;
            }

            products[position + 1] = key;
        }
    }

    public static void printProducts(Product[] products) {
        if (products == null || products.length == 0) {
            System.out.println("無商品資料。");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }
    }
}
