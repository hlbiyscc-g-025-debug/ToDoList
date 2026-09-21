import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToDoListTest {

    @Test
    void shouldAddTask() {
        // Arrange
        ToDoList todoList = new ToDoList();

        // Act
        todoList.addTask("Study QA");

        // Assert
        assertEquals(1, todoList.getTasks().size());
    }
    @Test
    void shouldDeleteTask() {
        // Arrange
        ToDoList todoList = new ToDoList();
        todoList.addTask("Study QA");

        // Act
        todoList.deleteTask("Study QA");

        // Assert
        assertEquals(0, todoList.getTasks().size());
    }
    @Test
    void shouldCompleteTask() {
        // Arrange
        ToDoList todoList = new ToDoList();
        todoList.addTask("Study QA");

        // Act
        todoList.completeTask("Study QA");

        // Assert
        assertTrue(todoList.isCompleted("Study QA"));
    }
    @Test
    void shouldEditTask() {
        // Arrange
        ToDoList todoList = new ToDoList();
        todoList.addTask("Study QA");

        // Act
        todoList.editTask("Study QA", "Study Quality Assurance");

        // Assert
        assertEquals("Study Quality Assurance", todoList.getTasks().get(0));
    }
    @Test
    void shouldNotAddEmptyTask() {
        // Arrange
        ToDoList todoList = new ToDoList();

        // Act
        todoList.addTask("");

        // Assert
        assertEquals(0, todoList.getTasks().size());
    }
    @Test
    void shouldNotCompleteTaskThatDoesNotExist() {
        // Arrange
        ToDoList todoList = new ToDoList();

        // Act
        todoList.completeTask("Task that does not exist");

        // Assert
        assertEquals(false, todoList.isCompleted("Task that does not exist"));
    }
    @Test
    void shouldNotAddDuplicateTask() {
        // Arrange
        ToDoList todoList = new ToDoList();
        todoList.addTask("Study QA");

        // Act
        todoList.addTask("Study QA");

        // Assert
        assertEquals(1, todoList.getTasks().size());
    }
    @Test
    void shouldNotAddSpacesOnlyTask() {
        // Arrange
        ToDoList todoList = new ToDoList();

        // Act
        todoList.addTask("   ");

        // Assert
        assertEquals(0, todoList.getTasks().size());
    }
}