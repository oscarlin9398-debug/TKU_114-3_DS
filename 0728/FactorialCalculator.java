public class FactorialCalculator {
    public static void main(String[] args) {
        for (int number = 0; number <= 5; number++) {
            System.out.println(number + "! = " + factorial(number));
        }
    }

    public static long factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("階乘不接受負數");
        }

        if (number == 0) {
            return 1;
        }

        return number * factorial(number - 1);
    }
}
