public class Employee {
    private String id;
    private String name;
    private String department;
    private String extension;

    public Employee(String id, String name, String department, String extension) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.extension = extension;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return String.format("員工編號: %-6s | 姓名: %-6s | 部門: %-8s | 分機: %s", 
                id, name, department, extension);
    }
}
