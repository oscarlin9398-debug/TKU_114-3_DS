import java.util.ArrayList;

public class CourseCrudSystem {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();

        addCourse(courses, new Course("C01", "Java"));
        addCourse(courses, new Course("C02", "Data Structures"));

        Course found = findCourse(courses, "C02");
        if (found != null) {
            found.enroll();
            found.enroll();
        }

        removeCourse(courses, "C01");

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public static void addCourse(ArrayList<Course> courses, Course course) {
        if (findCourse(courses, course.getCode()) == null) {
            courses.add(course);
        }
    }

    public static Course findCourse(ArrayList<Course> courses, String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    public static boolean removeCourse(ArrayList<Course> courses, String code) {
        Course found = findCourse(courses, code);
        if (found == null) {
            return false;
        }
        return courses.remove(found);
    }
}
