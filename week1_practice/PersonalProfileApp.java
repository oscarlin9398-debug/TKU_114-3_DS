import java.util.Scanner;

public class PersonalProfileApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = sc.next();

        // 呼叫驗證方法取得正確的年齡、身高、體重
        int age = readPositiveInt(sc, "請輸入年齡：");
        double height = readPositiveDouble(sc, "請輸入身高（公尺）：");
        double weight = readPositiveDouble(sc, "請輸入體重（公斤）：");

        // 進行核心數據計算與邏輯判斷
        double bmi = calculateBmi(height, weight);
        String level = getBmiLevel(bmi);
        boolean adult = isAdult(age);

        // 印出健康報表
        printReport(name, age, adult, height, weight, bmi, level);

        sc.close();
    }

    // 驗證並讀取大於 0 的整數
    public static int readPositiveInt(Scanner sc, String message) {
        System.out.print(message);
        int value = sc.nextInt();
        
        // 使用while迴圈做到不合法就強制重複輸入
        while (value <= 0) {
            System.out.println("錯誤：輸入的值必須大於 0，請重新輸入！");
            System.out.print(message);
            value = sc.nextInt();
        }
        return value;
    }

    public static double readPositiveDouble(Scanner sc, String message) {
        System.out.print(message);
        double value = sc.nextDouble();
        
        //身高、體重不能為零或負數
        while (value <= 0) {
            System.out.println("錯誤：輸入的值必須大於 0，請重新輸入！");
            System.out.print(message);
            value = sc.nextDouble();
        }
        return value;
    }

    // 計算BMI：體重 / (身高 * 身高)
    public static double calculateBmi(double height, double weight) {
        return weight / (height * height);
    }

    // 根據BMI數值分級判斷
    public static String getBmiLevel(double bmi) {
     
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.0) {
            return "Normal";
        } else if (bmi < 27.0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    // 判斷是否滿18歲成年
    public static boolean isAdult(int age) {
      
        if (age >= 18) {
            return true;
        } else {
            return false;
        }
    }

    // 輸出報表
    public static void printReport(String name, int age, boolean adult, double height, double weight, double bmi, String level) {
        System.out.println();
        System.out.println("=== Personal Health Report ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Adult: " + adult);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("BMI: " + bmi);
        System.out.println("Level: " + level);
    }
}