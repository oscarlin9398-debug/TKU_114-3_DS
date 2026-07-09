public class MaxMinDemo {
    public static void main(String[] args) {
        int score1 = 80;
        int score2 = 55;
        int score3 = 92;

        int max = score1;
        int min = score1;

        if (score2 > max) {
            max = score2;
        }

        if (score3 > max) {
            max = score3;
        }

        if (score2 < min) {
            min = score2;
        }

        if (score3 < min) {
            min = score3;
        }

        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
    }
}
