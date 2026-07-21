import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();

        while (true) {
            System.out.println("\n=== 選課管理系統 ===");
            System.out.println("1. 新增課程");
            System.out.println("2. 學生選課");
            System.out.println("3. 學生退選");
            System.out.println("4. 刪除課程");
            System.out.println("5. 搜尋課程");
            System.out.println("6. 顯示完整清單與統計資訊");
            System.out.println("7. 離開系統");
            System.out.print("請選擇操作 (1-7)：");

            if (!scanner.hasNextInt()) {
                System.out.println("錯誤：請輸入有效的選單數字！");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCourse(scanner, courses);
                    break;
                case 2:
                    enrollCourse(scanner, courses);
                    break;
                case 3:
                    dropCourse(scanner, courses);
                    break;
                case 4:
                    deleteCourse(scanner, courses);
                    break;
                case 5:
                    searchCourse(scanner, courses);
                    break;
                case 6:
                    showListAndStatistics(courses);
                    break;
                case 7:
                    System.out.println("感謝使用，系統已結束。");
                    scanner.close();
                    return;
                default:
                    System.out.println("錯誤：請輸入選項 1 到 7 之間的數字。");
            }
        }
    }

    public static void addCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.print("請輸入課程代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("錯誤：課程代碼不可為空白！");
            return;
        }

        if (findCourseByCode(courses, code) != null) {
            System.out.println("錯誤：課程代碼 [" + code + "] 已存在，不可重複新增！");
            return;
        }

        System.out.print("請輸入課程名稱：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("錯誤：課程名稱不可為空白！");
            return;
        }

        System.out.print("請輸入課程容量人數：");
        if (!scanner.hasNextInt()) {
            System.out.println("錯誤：容量人數必須為正整數！");
            scanner.next();
            return;
        }
        int capacity = scanner.nextInt();
        scanner.nextLine();

        if (capacity <= 0) {
            System.out.println("錯誤：課程容量必須大於 0！");
            return;
        }

        courses.add(new Course(code, name, capacity));
        System.out.println("新增課程成功：[" + name + "]");
    }

    public static void enrollCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.print("請輸入要加選的課程代碼：");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(courses, code);
        if (course == null) {
            System.out.println("選課失敗：找不到課程代碼 [" + code + "]。");
            return;
        }

        if (course.isFull()) {
            System.out.println("選課失敗：課程 [" + course.getName() + "] 已額滿！");
            return;
        }

        if (course.enroll()) {
            System.out.println("加選成功！[" + course.getName() + "] 目前選課人數：" + course.getEnrolled() + "/" + course.getCapacity());
        }
    }

    public static void dropCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.print("請輸入要退選的課程代碼：");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(courses, code);
        if (course == null) {
            System.out.println("退選失敗：找不到課程代碼 [" + code + "]。");
            return;
        }

        if (course.getEnrolled() <= 0) {
            System.out.println("退選失敗：課程 [" + course.getName() + "] 目前無人選修！");
            return;
        }

        if (course.drop()) {
            System.out.println("退選成功！[" + course.getName() + "] 目前選課人數：" + course.getEnrolled() + "/" + course.getCapacity());
        }
    }

    public static void deleteCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.print("請輸入要刪除的課程代碼：");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(courses, code);
        if (course == null) {
            System.out.println("刪除失敗：找不到課程代碼 [" + code + "]。");
            return;
        }

        courses.remove(course);
        System.out.println("刪除成功：已移除課程 [" + course.getName() + "]。");
    }

    public static void searchCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.print("請輸入要搜尋的課程代碼或名稱：");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("錯誤：搜尋關鍵字不可為空白！");
            return;
        }

        boolean found = false;
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(keyword) || course.getName().contains(keyword)) {
                System.out.println("找到課程 -> " + course);
                found = true;
            }
        }

        if (!found) {
            System.out.println("未找到符合關鍵字 [" + keyword + "] 的課程。");
        }
    }

    public static void showListAndStatistics(ArrayList<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("目前系統內無任何課程資料。");
            return;
        }

        System.out.println("\n--- 完整課程清單 ---");
        int totalEnrolled = 0;
        ArrayList<String> fullCourseNames = new ArrayList<>();

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.println((i + 1) + ". " + course);
            totalEnrolled += course.getEnrolled();
            if (course.isFull()) {
                fullCourseNames.add(course.getName());
            }
        }

        System.out.println("\n--- 系統統計資訊 ---");
        System.out.println("總課程數：" + courses.size() + " 門");
        System.out.println("總選課人次：" + totalEnrolled + " 人次");
        if (fullCourseNames.isEmpty()) {
            System.out.println("額滿課程：無");
        } else {
            System.out.println("額滿課程 (" + fullCourseNames.size() + " 門)：" + String.join(", ", fullCourseNames));
        }
    }

    public static Course findCourseByCode(ArrayList<Course> courses, String code) {
        if (code.isEmpty()) {
            return null;
        }
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }
}
