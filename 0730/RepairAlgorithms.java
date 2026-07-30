import java.util.ArrayList;
import java.util.List;

public class RepairAlgorithms {

    public static void sortByPriorityDescendingStable(List<RepairTask> tasks, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        sortByPriorityDescendingStable(tasks, left, mid);
        sortByPriorityDescendingStable(tasks, mid + 1, right);
        mergeByPriorityDescendingStable(tasks, left, mid, right);
    }

    private static void mergeByPriorityDescendingStable(List<RepairTask> tasks, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        List<RepairTask> leftList = new ArrayList<>();
        List<RepairTask> rightList = new ArrayList<>();

        for (int i = 0; i < leftSize; i++) {
            leftList.add(tasks.get(left + i));
        }
        for (int j = 0; j < rightSize; j++) {
            rightList.add(tasks.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = left;

        while (i < leftSize && j < rightSize) {
            if (leftList.get(i).getPriority() >= rightList.get(j).getPriority()) {
                tasks.set(k, leftList.get(i));
                i++;
            } else {
                tasks.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            tasks.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < rightSize) {
            tasks.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    public static RepairTask searchById(List<RepairTask> tasks, String taskId) {
        if (tasks == null || taskId == null) return null;

        for (RepairTask task : tasks) {
            if (task.getTaskId().equalsIgnoreCase(taskId)) {
                return task;
            }
        }
        return null;
    }

    public static List<RepairTask> searchByDeviceName(List<RepairTask> tasks, String deviceName) {
        List<RepairTask> results = new ArrayList<>();
        if (tasks == null || deviceName == null) return results;

        for (RepairTask task : tasks) {
            if (task.getDeviceName().toLowerCase().contains(deviceName.toLowerCase())) {
                results.add(task);
            }
        }
        return results;
    }
}
