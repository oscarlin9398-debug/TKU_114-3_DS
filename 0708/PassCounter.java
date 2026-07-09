public class PassCounter {
    public static void main(String[] args) {
        int passCount = 0;

        int score1 = 87;
        int score2 = 55;
        int score3 = 67;

        if (score1 >= 60) {
            passCount++;
        }

        if (score2 >= 60) {
            passCount++;
        }

        if (score3 >= 60) {
            passCount++;
        }

        System.out.println("Pass count: " + passCount);
    }
}
