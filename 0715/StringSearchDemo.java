public class StringSearchDemo {
    public static void main(String[] args) {
        String text = "Java Programming and Data Structures";
        String keyword = "data";

        String normalizedText = text.toLowerCase();
        String normalizedKeyword = keyword.toLowerCase();

        System.out.println("是否包含：" + normalizedText.contains(normalizedKeyword));
        System.out.println("第一次出現位置：" + normalizedText.indexOf(normalizedKeyword));
    }
}
