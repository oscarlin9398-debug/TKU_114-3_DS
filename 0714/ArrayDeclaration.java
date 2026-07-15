public class ArrayDeclaration {
    public static void main(String[] args) {
        int[] emptyScores = new int[5];
        System.out.println("預設值：" + emptyScores[0]);

        int[] scores = {80, 75, 92, 68, 88};
        String[] products = {"Keyboard", "Mouse", "Monitor"};

        System.out.println(scores[0]); 
        System.out.println(scores[4]); 
        scores[1] = 78;
        System.out.println(scores[1]); 
        System.out.println("陣列長度：" + scores.length);
        System.out.println("最後一個元素：" + scores[scores.length - 1]);
    }
}