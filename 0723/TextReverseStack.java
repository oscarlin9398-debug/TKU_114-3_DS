import java.util.ArrayDeque;
import java.util.Deque;

public class TextReverseStack {
    public static void main(String[] args) {
        String text = "Data Structure";
        System.out.println("原文字：" + text);
        System.out.println("反轉後：" + reverse(text));
    }

    public static String reverse(String text) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < text.length(); i++) {
            stack.push(text.charAt(i));
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }
}
