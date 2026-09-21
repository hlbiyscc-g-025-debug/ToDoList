import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    private List<String> tasks = new ArrayList<>();
    private List<String> completedTasks = new ArrayList<>();

    public void addTask(String task) {
        if (task != null && !task.trim().isEmpty() && !tasks.contains(task))
            tasks.add(task);
    }

    public List<String> getTasks() {
        return tasks;
    }
    public void deleteTask(String task) {
        tasks.remove(task);
    }
    public void editTask(String oldTask, String newTask) {
        int index = tasks.indexOf(oldTask);

        if (index != -1) {
            tasks.set(index, newTask);
        }
    }
    public void completeTask(String task) {
        if (tasks.contains(task)) {
            completedTasks.add(task);
        }
    }

    public boolean isCompleted(String task) {
        return completedTasks.contains(task);
    }
}