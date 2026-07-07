public class BmiFixed {
    public static void main(String[] args) {
        double height = 1.75;
        double weight = 68.0;
        double bmi = weight / (height * height);

        System.out.println("BMI: " + bmi);
    }
}
