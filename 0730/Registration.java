public class Registration {
    private String regId;
    private String name;
    private String phone;
    private String status;

    public Registration(String regId, String name, String phone) {
        this.regId = regId;
        this.name = name;
        this.phone = phone;
        this.status = "正取";
    }

    public String getRegId() {
        return regId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("報名編號: %-6s | 姓名: %-8s | 電話: %-11s | 狀態: %s",
                regId, name, phone, status);
    }
}
