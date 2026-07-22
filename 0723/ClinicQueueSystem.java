import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class ClinicQueueSystem {

    private Deque<Patient> queue = new ArrayDeque<>();
    private HashSet<String> registeredNumbers = new HashSet<>();
    private int servedCount = 0;

    public boolean register(String number, String name, String department) {
        if (registeredNumbers.contains(number)) {
            System.out.println("掛號失敗：號碼 " + number + " 已存在");
            return false;
        }

        Patient patient = new Patient(number, name, department);
        queue.offer(patient);
        registeredNumbers.add(number);
        System.out.println("掛號成功：" + patient);
        return true;
    }

    public void callNext() {
        Patient patient = queue.poll();
        if (patient == null) {
            System.out.println("叫號失敗：目前沒有等待的患者");
            return;
        }

        servedCount++;
        System.out.println("叫號服務：" + patient);
    }

    public void peekNext() {
        Patient patient = queue.peek();
        if (patient == null) {
            System.out.println("下一位患者：目前無人等待");
        } else {
            System.out.println("下一位患者：" + patient);
        }
    }

    public void showWaitingList() {
        System.out.println("\n--- 目前等待清單 ---");
        if (queue.isEmpty()) {
            System.out.println("無人等待");
            return;
        }

        for (Patient p : queue) {
            System.out.println(p);
        }
    }

    public void showDepartmentCount(String department) {
        int count = 0;
        for (Patient p : queue) {
            if (p.getDepartment().equals(department)) {
                count++;
            }
        }
        System.out.println(department + " 等待人數：" + count + " 人");
    }

    public void showServedCount() {
        System.out.println("已服務總人數：" + servedCount + " 人");
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        System.out.println("=== 診所叫號系統測試 ===");

        clinic.peekNext();
        clinic.callNext();

        System.out.println("\n--- 開始進行掛號 ---");
        clinic.register("101", "王小明", "內科");
        clinic.register("102", "李大同", "外科");
        clinic.register("101", "張六七", "內科");
        clinic.register("103", "陳美麗", "內科");

        clinic.showWaitingList();

        System.out.println("\n--- 檢視科別等待人數與下一位 ---");
        clinic.showDepartmentCount("內科");
        clinic.showDepartmentCount("外科");
        clinic.peekNext();

        System.out.println("\n--- 開始叫號服務 ---");
        clinic.callNext();
        clinic.callNext();

        clinic.showWaitingList();
        clinic.showServedCount();

        System.out.println("\n--- 叫號清空佇列測試 ---");
        clinic.callNext();
        clinic.callNext();

        clinic.showServedCount();
    }
}
