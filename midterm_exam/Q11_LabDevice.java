public class Q11_LabDevice {
    private String code;
    private String name;
    private int usageHours;
    private boolean active;

    public Q11_LabDevice(
            String code,
            String name,
            int usageHours,
            boolean active
    ) {
        if (code == null || code.trim().isEmpty()) {
            this.code = "UNKNOWN";
        } else {
            this.code = code.trim();
        }

        if (name == null || name.trim().isEmpty()) {
            this.name = "Unnamed";
        } else {
            this.name = name.trim();
        }

        this.usageHours = Math.max(usageHours, 0);
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getUsageHours() {
        return usageHours;
    }

    public boolean isActive() {
        return active;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void addUsageHours(int hours) {
        if (hours > 0) {
            this.usageHours += hours;
        }
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean needsMaintenance() {
        return usageHours >= 100;
    }

    @Override
    public String toString() {
        String status = active ? "active" : "inactive";
        return code + " | " + name + " | " + usageHours + " hours | " + status;
    }

    public static void main(String[] args) {
        Q11_LabDevice device = new Q11_LabDevice(
                " D01 ",
                " Printer ",
                90,
                true
        );
        device.addUsageHours(30);
        device.addUsageHours(-5);
        device.setName(" 3D Printer ");
        System.out.println(device);
        System.out.println("需要保養：" + device.needsMaintenance());
        device.deactivate();
        System.out.println("啟用狀態：" + device.isActive());
    }
}
