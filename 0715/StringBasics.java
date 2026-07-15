public class StringBasics {
    public static void main(String[] args) {
        String original = "  Java Programming  ";
        String text = original.trim();

        System.out.println("原始文字：" + original);
        System.out.println("移除前後空白：" + text);
        System.out.println("長度：" + text.length());
        System.out.println("第一個字元：" + text.charAt(0));
        System.out.println("最後字元：" + text.charAt(text.length() - 1));
        System.out.println("前四個字元：" + text.substring(0, 4));
        System.out.println("從索引 5 開始：" + text.substring(5));
        System.out.println("大寫：" + text.toUpperCase());
        System.out.println("小寫：" + text.toLowerCase());
        System.out.println("原始字串仍未改變：" + original);
    }
}
