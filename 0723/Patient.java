public class Patient {

    private String number;
    private String name;
    private String department;

    public Patient(String number, String name, String department) {
        this.number = number;
        this.name = name;
        this.department = department;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "[" + number + "] " + name + " (" + department + ")";
    }
}
