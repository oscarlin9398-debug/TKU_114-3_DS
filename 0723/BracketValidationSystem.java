import java.util.ArrayDeque;
import java.util.Deque;

public class BracketValidationSystem {

    public static boolean isValid(String text) {
        if (text == null) {
            return true;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (isLeftBracket(ch)) {
                stack.push(ch);
            } else if (isRightBracket(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char left = stack.pop();
                if (!isMatchingPair(left, ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isLeftBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    private static boolean isRightBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')')
            || (left == '[' && right == ']')
            || (left == '{' && right == '}');
    }

    public static void main(String[] args) {
        String test1 = "if (a[0] == {b + c}) { return (x + y); }";
        String test2 = "a * (b + c) - [d / {e - f}]";
        String test3 = "(a + b) * [c - d]}";
        String test4 = "{a + [b * (c + d)]";
        String test5 = "([)]";

        System.out.println("測試 1 (多層巢狀與非括號字元)：" + isValid(test1));
        System.out.println("測試 2 (混合運算式)：" + isValid(test2));
        System.out.println("測試 3 (缺少左括號 / 右括號過多)：" + isValid(test3));
        System.out.println("測試 4 (缺少右括號 / 左括號過多)：" + isValid(test4));
        System.out.println("測試 5 (順序交叉錯誤)：" + isValid(test5));
    }
}
