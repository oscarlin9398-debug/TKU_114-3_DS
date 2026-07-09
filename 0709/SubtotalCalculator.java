public class SubtotalCalculator {
    public static void main(String[] args) {
        int total = calculateSubtotal(35, 3);
        System.out.println("Subtotal: " + total);
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }
}
