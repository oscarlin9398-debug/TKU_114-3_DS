import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class Q10_Task {
    private String id;
    private String title;

    public Q10_Task(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return id + " " + title;
    }
}

public class Q10_WorkflowSystem {
    private ArrayList<Q10_Task> allTasks = new ArrayList<>();
    private Deque<Q10_Task> waiting = new ArrayDeque<>();
    private Deque<Q10_Task> completed = new ArrayDeque<>();

    public static void main(String[] args) {
        Q10_WorkflowSystem system = new Q10_WorkflowSystem();
        system.addTask(new Q10_Task("T201", "Backup"));
        system.addTask(new Q10_Task("T105", "Update"));
        system.addTask(new Q10_Task("T330", "Report"));

        System.out.println("下一筆：" + system.peekNext());
        System.out.println("完成：" + system.processNext());
        System.out.println("完成：" + system.processNext());
        System.out.println("復原：" + system.undoLast());
        System.out.println("下一筆：" + system.peekNext());
        System.out.println("搜尋：" + system.findById("t330"));
        System.out.println("等待數：" + system.waitingCount());
        System.out.println("完成數：" + system.completedCount());
    }

    public boolean addTask(Q10_Task task) {
        if (task == null || task.getId() == null || task.getId().trim().isEmpty()) {
            return false;
        }

        for (Q10_Task existingTask : allTasks) {
            if (existingTask.getId().equalsIgnoreCase(task.getId())) {
                return false;
            }
        }

        allTasks.add(task);
        waiting.addLast(task);
        return true;
    }

    public Q10_Task processNext() {
        if (waiting.isEmpty()) {
            return null;
        }
        Q10_Task task = waiting.pollFirst();
        completed.push(task);
        return task;
    }

    public Q10_Task undoLast() {
        if (completed.isEmpty()) {
            return null;
        }
        Q10_Task task = completed.pop();
        waiting.addFirst(task);
        return task;
    }

    public Q10_Task peekNext() {
        return waiting.peekFirst();
    }

    public Q10_Task findById(String id) {
        if (id == null) {
            return null;
        }
        for (Q10_Task task : allTasks) {
            if (task.getId().equalsIgnoreCase(id)) {
                return task;
            }
        }
        return null;
    }

    public int waitingCount() {
        return waiting.size();
    }

    public int completedCount() {
        return completed.size();
    }
}
