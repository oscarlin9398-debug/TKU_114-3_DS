public class StringSplitDemo {
    public static void main(String[] args) {
        String record = " Keyboard , 890 , 12 ";
        String[] parts = record.split(",");

        if (parts.length == 3) {
            String name = parts[0].trim();
            int price = Integer.parseInt(parts[1].trim());
            int stock = Integer.parseInt(parts[2].trim());

            System.out.println("名稱：" + name);
            System.out.println("價格：" + price);
            System.out.println("庫存：" + stock);
        } else {
            System.out.println("資料格式錯誤");
        }
    }
}
