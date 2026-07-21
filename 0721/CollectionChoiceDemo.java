import java.util.ArrayList;

public class CollectionChoiceDemo {
    public static void main(String[] args) {
        double[] monthlyRates = new double[12];
        monthlyRates[0] = 1.2;
        monthlyRates[1] = 1.5;

        ArrayList<String> registrations = new ArrayList<>();
        registrations.add("Amy");
        registrations.add("Ben");
        registrations.remove("Amy");

        System.out.println("固定月份數：" + monthlyRates.length);
        System.out.println("目前報名數：" + registrations.size());
    }
}
