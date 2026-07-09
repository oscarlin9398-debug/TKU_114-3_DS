public class PricePrinter {
    public static void main(String[] args) {
        printItem("Black tea", 30);
        printItem("Green tea", 40);
    }

    public static void printItem(String itemName, int price) {
        System.out.println("商品：" + itemName + "，價格：$" + price);
    }
}
