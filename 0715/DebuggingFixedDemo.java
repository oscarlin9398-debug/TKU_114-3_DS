public class DebuggingFixedDemo {
    public static void main(String[] args) {
        String[] names = {"Keyboard", "Mouse", "Monitor"};
        int[] prices = {890, 490, 5200};
        int total = 0;

        for (int i = 0; i < prices.length; i++) {
            total += prices[i];
            System.out.println(names[i] + "：" + prices[i]);
        }

        double average = (double) total / prices.length;
        System.out.printf("平均價格：%.2f%n", average);

        String target = new String("Mouse");
        if (target.equals(names[1])) {
            System.out.println("找到 Mouse");
        }
    }
}
