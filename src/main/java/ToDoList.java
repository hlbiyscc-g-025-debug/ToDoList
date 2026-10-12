import java.util.ArrayList;
import java.util.List;



public class ToDoList {

    private List<String> tasks = new ArrayList<>();
    private List<String> completedTasks = new ArrayList<>();

    public void addTask(String task) {
        if (task != null && !task.trim().isEmpty() && !tasks.contains(task)) {
            tasks.add(task);
             System.out.println("Task added: " + task);
        } else {
            System.out.println("Task could not be added.");
        
        }
    }

    public List<String> getTasks() {
        return tasks;
    }
    public void deleteTask(String task) {
        if (tasks.remove(task)) {
            completedTasks.remove(task);
            System.out.println("Task deleted: " + task);
        } else {
            System.out.println("Task not found: " + task);
        }
            
    }
    public void editTask(String oldTask, String newTask) {
        int index = tasks.indexOf(oldTask);

        if (index != -1 && newTask != null && !newTask.trim().isEmpty()  && (!tasks.contains(newTask) || oldTask.equals(newTask))) {
            tasks.set(index, newTask);
            if (completedTasks.contains(oldTask)) {
            completedTasks.remove(oldTask);
            completedTasks.add(newTask);
        
        }
        System.out.println("Task updated: " + oldTask + " -> " + newTask);
    } else {
        System.out.println("Task could not be edited.");
    }
        
    }
    public void completeTask(String task) {
        if (tasks.contains(task) && !completedTasks.contains(task)) {
            completedTasks.add(task);
        }
    }

    public boolean isCompleted(String task) {
        return completedTasks.contains(task);
    }
    public List<String> getCompletedTasks() {
        return completedTasks;
    }
}