public class StringCompareDemo {
    public static void main(String[] args) {
        String first = new String("Java");
        String second = "Java";

        System.out.println(first == second);
        System.out.println(first.equals(second));
        System.out.println(first.equalsIgnoreCase("java"));
    }
}
