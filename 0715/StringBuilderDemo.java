public class StringBuilderDemo {
    public static void main(String[] args) {
        String[] names = {"Keyboard", "Mouse", "Monitor"};
        StringBuilder report = new StringBuilder();

        report.append("商品清單\n");
        report.append("--------\n");

        for (int i = 0; i < names.length; i++) {
            report.append(i + 1)
                    .append(". ")
                    .append(names[i])
                    .append("\n");
        }

        System.out.print(report.toString());
    }
}
