public class ProductPriceSort {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P103", "Keyboard", 1290),
            new Product("P205", "Mouse", 650),
            new Product("P118", "Monitor", 5200),
            new Product("P310", "Webcam", 1290)
        };

        insertionSortByPrice(products);

        for (Product product : products) {
            System.out.println(product);
        }
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

    // 使用 static 內部類別，徹底避免與同資料夾的 Product.java 檔衝突
    private static class Product {
        private String id;
        private String name;
        private int price;

        public Product(String id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return id + " " + name + " $" + price;
        }
    }
}
