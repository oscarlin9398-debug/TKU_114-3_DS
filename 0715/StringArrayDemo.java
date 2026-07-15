public class StringArrayDemo {
    public static int findExact(String[] values, String keyword) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equalsIgnoreCase(keyword.trim())) {
                return i;
            }
        }
        return -1;
    }

    public static void printPartial(String[] values, String keyword) {
        String normalized = keyword.trim().toLowerCase();
        boolean found = false;

        for (String value : values) {
            if (value.toLowerCase().contains(normalized)) {
                System.out.println(value);
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到相符資料");
        }
    }

    public static void main(String[] args) {
        String[] products = {
            "Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"
        };

        int index = findExact(products, " mouse ");
        System.out.println("完整搜尋索引：" + index);
        System.out.println("部分搜尋結果：");
        printPartial(products, "o");
    }
}
