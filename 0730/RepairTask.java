public class RepairTask {
    private String taskId;
    private String deviceName;
    private int priority;
    private String status;

    public RepairTask(String taskId, String deviceName, int priority) {
        this.taskId = taskId;
        this.deviceName = deviceName;
        this.priority = priority;
        this.status = "等待中";
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("工作編號: %-6s | 設備名稱: %-12s | 優先等級: P%-2d | 狀態: %s",
                taskId, deviceName, priority, status);
    }
}
