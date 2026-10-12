import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ToDoList todoList = new ToDoList();

        boolean running = true;

        System.out.println("================================");
        System.out.println("       WELCOME TO TODOLIST");
        System.out.println("================================");

        while (running) {

            System.out.println("\nChoose an option:");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Complete task");
            System.out.println("4. Edit task");
            System.out.println("5. Delete task");
            System.out.println("6. View completed tasks");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    System.out.print("Enter the task name: ");
                    String newTask = scanner.nextLine();

                    todoList.addTask(newTask);
                    break;

                case "2":
                    System.out.println("\nYour tasks:");

                    if (todoList.getTasks().isEmpty()) {
                        System.out.println("No tasks available.");
                    } else {
                        for (String task : todoList.getTasks()) {
                            System.out.println("- " + task);
                        }
                    }
                    break;

                case "3":
                    System.out.print("Enter the task to complete: ");
                    String taskToComplete = scanner.nextLine();

                    todoList.completeTask(taskToComplete);
                    break;

                case "4":
                    System.out.print("Enter the current task name: ");
                    String oldTask = scanner.nextLine();

                    System.out.print("Enter the new task name: ");
                    String updatedTask = scanner.nextLine();

                    todoList.editTask(oldTask, updatedTask);
                    break;

                case "5":
                    System.out.print("Enter the task to delete: ");
                    String taskToDelete = scanner.nextLine();

                    todoList.deleteTask(taskToDelete);
                    break;

                case "6":
                    System.out.println("\nCompleted tasks:");

                    if (todoList.getCompletedTasks().isEmpty()) {
                        System.out.println("No completed tasks.");
                    } else {
                        for (String task : todoList.getCompletedTasks()) {
                            System.out.println("- " + task);
                        }
                    }
                    break;

                case "7":
                    running = false;
                    System.out.println("Thank you for using ToDoList!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1 to 7.");
            }
        }

        scanner.close();
    }
}