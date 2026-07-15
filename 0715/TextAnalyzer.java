import java.util.Scanner;

public class TextAnalyzer {

    public static String getValidInput(Scanner sc) {
        while (true) {
            System.out.print("請輸入一行文字：");
            String input = sc.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input;
            }
            System.out.println("輸入不可為空或全空白，請重新輸入。");
        }
    }

    public static String[] getWords(String text) {
        return text.trim().split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String lower = text.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    public static String findLongestWord(String[] words) {
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public static int countKeyword(String[] words, String keyword) {
        int count = 0;
        String target = keyword.trim().toLowerCase();
        for (String word : words) {
            if (word.toLowerCase().equals(target)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String original = getValidInput(sc);
        String cleaned = original.trim();
        String[] words = getWords(cleaned);

        System.out.println("原始字元數：" + original.length());
        System.out.println("有效字元數：" + cleaned.length());
        System.out.println("單字數量：" + words.length);
        System.out.println("英文字母母音總數：" + countVowels(cleaned));
        System.out.println("最長單字：" + findLongestWord(words));

        System.out.print("請輸入要搜尋的關鍵字：");
        String keyword = sc.nextLine();
        System.out.println("關鍵字出現次數：" + countKeyword(words, keyword));

        sc.close();
    }
}
