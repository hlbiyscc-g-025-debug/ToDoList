import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ToDoListTest {

    @Test
    void shouldAddTask() {
        
        ToDoList todoList = new ToDoList();

        
        todoList.addTask("Study QA");

        
        assertEquals(1, todoList.getTasks().size());
    }
    @Test
    void shouldDeleteTask() {
        
        ToDoList todoList = new ToDoList();
        todoList.addTask("Study QA");

        
        todoList.deleteTask("Study QA");

        
        assertEquals(0, todoList.getTasks().size());
    }
    @Test
    void shouldCompleteTask() {
        
        ToDoList todoList = new ToDoList();
        todoList.addTask("Study QA");

        
        todoList.completeTask("Study QA");

        
        assertTrue(todoList.isCompleted("Study QA"));
    }
    @Test
    void shouldEditTask() {
        
        ToDoList todoList = new ToDoList();
        todoList.addTask("Study QA");

        
        todoList.editTask("Study QA", "Study Quality Assurance");

        
        assertEquals("Study Quality Assurance", todoList.getTasks().get(0));
    }
    @Test
    void shouldNotAddEmptyTask() {
        
        ToDoList todoList = new ToDoList();

        
        todoList.addTask("");

        
        assertEquals(0, todoList.getTasks().size());
    }
    @Test
    void shouldNotCompleteTaskThatDoesNotExist() {
        
        ToDoList todoList = new ToDoList();

        
        todoList.completeTask("Task that does not exist");

        
        assertEquals(false, todoList.isCompleted("Task that does not exist"));
    }
    @Test
    void shouldNotAddDuplicateTask() {
        
        ToDoList todoList = new ToDoList();
        todoList.addTask("Study QA");

        
        todoList.addTask("Study QA");

        
        assertEquals(1, todoList.getTasks().size());
    }
    @Test
    void shouldNotAddSpacesOnlyTask() {
        
        ToDoList todoList = new ToDoList();

        
        todoList.addTask("   ");

        
        assertEquals(0, todoList.getTasks().size());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "     "})
    void shouldNotAddInvalidTasks(String task) {

        
        ToDoList todoList = new ToDoList();

        
        todoList.addTask(task);

        
        assertEquals(0, todoList.getTasks().size());
    }
    @Test
    void shouldNotEditTaskToEmptyName() {
    
        ToDoList todoList = new ToDoList();
        todoList.addTask("Study QA");

        
        todoList.editTask("Study QA", "");

        
        assertEquals("Study QA", todoList.getTasks().get(0));
    }
    @Test
    void shouldNotCompleteTaskTwice() {
        
        ToDoList todoList = new ToDoList();
        todoList.addTask("Study QA");

        
        todoList.completeTask("Study QA");
        todoList.completeTask("Study QA");

        
        assertEquals(1, todoList.getCompletedTasks().size());
    }
    @Test
    void shouldNotEditTaskToDuplicateName() {
        ToDoList todoList = new ToDoList();

        todoList.addTask("Study QA");
        todoList.addTask("Complete assignment");

        todoList.editTask("Complete assignment", "Study QA");

        assertEquals(2, todoList.getTasks().size());
        assertTrue(todoList.getTasks().contains("Study QA"));
        assertTrue(todoList.getTasks().contains("Complete assignment"));
    }
    @Test
    void shouldRemoveCompletedTaskWhenDeleted() {
        ToDoList todoList = new ToDoList();

        todoList.addTask("Study QA");
        todoList.completeTask("Study QA");

        todoList.deleteTask("Study QA");

        assertFalse(todoList.getTasks().contains("Study QA"));
        assertFalse(todoList.getCompletedTasks().contains("Study QA"));
    }
        @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(new File("test-tasks.txt").toPath());
    }

    @Test
    void shouldSaveTasksToFile() throws IOException {
        ToDoList todoList = new ToDoList("test-tasks.txt");

        todoList.addTask("Study QA");
        todoList.completeTask("Study QA");

        todoList.saveTasks();

        String savedData = Files.readString(
                new File("test-tasks.txt").toPath()
        );

        assertTrue(savedData.contains("Study QA|true"));
    }

    @Test
    void shouldLoadTasksFromFile() throws IOException {
        File testFile = new File("test-tasks.txt");

        Files.writeString(
                testFile.toPath(),
                "Study QA|true\nComplete assignment|false\n"
        );

        ToDoList todoList = new ToDoList("test-tasks.txt");

        todoList.loadTasks();

        assertTrue(todoList.getTasks().contains("Study QA"));
        assertTrue(todoList.getTasks().contains("Complete assignment"));
        assertTrue(todoList.isCompleted("Study QA"));
        assertFalse(todoList.isCompleted("Complete assignment"));
    }
    @Test
    void shouldHandleMissingFileWhenLoadingTasks() {
        ToDoList todoList = new ToDoList("missing-tasks.txt");

        todoList.loadTasks();

        assertTrue(todoList.getTasks().isEmpty());
        assertTrue(todoList.getCompletedTasks().isEmpty());
    }
}