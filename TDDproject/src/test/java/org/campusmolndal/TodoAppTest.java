package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoAppTest {
    private TodoApp todoApp;
    private ByteArrayOutputStream outputStream;

    @Test
    public void ToDoApp(){ // Test for the constructor of TodoApp to see if the lists are empty when the program starts
        // Arrange
        TodoApp todoAppConstructor = new TodoApp();

        // Act
       List one = todoAppConstructor.getTodos();
       List two = todoAppConstructor.getUsers();

         // Assert
        assertTrue(one.isEmpty());
        assertTrue(two.isEmpty());

    }


    @BeforeEach
    void setUp() { // Create a new TodoApp object before each test
        todoApp = new TodoApp();
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    void CreateUser() { // Test for the createUser method to see if the user is created and added to the list
        //Arrange - done in setUp()

        //Act
        todoApp.createUser(1, "John", 25);
        List<User> users = todoApp.getUsers();

        //Assert
        assertTrue(users.size() == 1);
    }

    @Test
    void CreateTodo() {
        //Arrange - done in setUp()

        // Act
        todoApp.createTodo(1, "Buy groceries", false, 1);
        List<Todo> todos = todoApp.getTodos();

        // Assert
        assertTrue(todos.size() == 1);
    }

    @Test
    void DeleteUserById() {
        //Arrange - done in setUp()

        //Act
        todoApp.createUser(1, "John", 25);
        todoApp.deleteUserById(1);
        List<User> users = todoApp.getUsers();

        //Assert
        assertTrue(users.isEmpty());
    }

    @Test
    void DeleteAllUsers() {
        //Arrange - done in setUp()

        // Act
        todoApp.createUser(1, "John", 25);
        todoApp.createUser(2, "Jane", 30);
        todoApp.deleteAllUsers();
        List<User> users = todoApp.getUsers();

        // Assert
        assertTrue(users.isEmpty());
    }

    @Test
    void DeleteTodoById() {
        //Arrange - done in setUp()

        // Act
        todoApp.createTodo(1, "Buy groceries", false, 1);
        todoApp.deleteTodoById(1);
        List<Todo> todos = todoApp.getTodos();

        // Assert
        assertTrue(todos.isEmpty());
    }

    @Test
    void DeleteAllTodos() {
        //Arrange - done in setUp()

        // Act
        todoApp.createTodo(1, "Buy groceries", false, 1);
        todoApp.createTodo(2, "Clean the house", false, 1);
        todoApp.deleteAllTodos();
        List<Todo> todos = todoApp.getTodos();

        // Assert
        assertTrue(todos.isEmpty());
    }

    @Test
    void FindUserById() {
        //Arrange - done in setUp()

        // Act
        todoApp.createUser(1, "Adam", 25);
        User user = todoApp.findUserById(1);

        // Assert
        assertEquals("Adam", user.getName());
    }

    @Test
    void FindUserByName() {
        //Arrange - done in setUp()

        // Act
        todoApp.createUser(1, "John", 25);
        User user = todoApp.findUserByName("John");

        // Assert
        assertEquals(1, user.getId());
    }

    @Test
    void FindTodoById() {
        //Arrange - done in setUp()

        // Act
        todoApp.createTodo(1, "Buy groceries", false, 1);
        Todo todo = todoApp.findTodoById(1);

        // Assert
        assertEquals("Buy groceries", todo.getText());
    }

    @Test
    void UpdateTodoText() {
        //Arrange - done in setUp()

        // Act
        todoApp.createTodo(1, "Buy groceries", false, 1);
        todoApp.updateTodoText(1, "Buy milk");
        List<Todo> todos = todoApp.getTodos();

        assertEquals("Buy milk", todos.get(0).getText());
    }

    @Test
    void UpdateTodoStatus() {
        //Arrange - done in setUp()

        // Act
        todoApp.createTodo(1, "Buy groceries", false, 1);
        todoApp.updateTodoStatus(1, true);
        List<Todo> todos = todoApp.getTodos();

        // Assert
        assertTrue(todos.get(0).isDone());
    }

    @Test
    void testUpdateUser() {
        //Arrange - done in setUp()

        // Act
        todoApp.createUser(1, "John", 25);
        todoApp.updateUser(1, "Jane");
        List<User> users = todoApp.getUsers();

        // Assert
        assertEquals("Jane", users.get(0).getName());
    }


    @Test
    void ShowAllTodos() {
        // Arrange - done in setUp()

        // Act
        todoApp.createTodo(1, "Work", false, 1);
        todoApp.createTodo(2, "Clean the house", true, 1);
        System.setOut(new PrintStream(outputStream)); // Redirect console output
        todoApp.showAllTodos();
        String expectedOutput = "All Todos:\n" +
                "ID: 1\n" +
                "Text: Work\n" +
                "Done: false\n" +
                "Assigned to: 1\n" +
                "\n" +
                "ID: 2\n" +
                "Text: Clean the house\n" +
                "Done: true\n" +
                "Assigned to: 1\n" +
                "\n";

        // Assert
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void ShowTodoById() {
        // Arrange - done in setUp()

        // Act
        todoApp.createTodo(1, "School", false, 1);
        System.setOut(new PrintStream(outputStream)); // Redirect console output
        todoApp.showTodoById(1);
        String expectedOutput = "Todo ID: 1\n" +
                "Text: School\n" +
                "Done: false\n" +
                "Assigned to: 1\n";

        // Assert
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void ShowAllUsers() { // Checks if the output is correct
        // Arrange - partly done in setUp()

        // Act
        todoApp.createUser(1, "Kerim", 25);
        todoApp.createUser(2, "Kozo", 30);
        System.setOut(new PrintStream(outputStream)); // Redirect console output
        todoApp.showAllUsers();
        String expectedOutput = "All Users:\n" +
                "ID: 1\n" +
                "Name: Kerim\n" +
                "Age: 25\n" +
                "\n" +
                "ID: 2\n" +
                "Name: Kozo\n" +
                "Age: 30\n" +
                "\n";

        // Assert
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void ShowUserById() {
        // Arrange - done in setUp()

        // Act
        todoApp.createUser(1, "Jason", 25);
        System.setOut(new PrintStream(outputStream)); // Redirect console output
        todoApp.showUserById(1);
        String expectedOutput = "User ID: 1\n" +
                "Name: Jason\n" +
                "Age: 25\n";

        // Assert
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void ShowUsersTodos() {
        // Arrange - done in setUp()

        // Act
        todoApp.createUser(1, "Jonas", 25);
        todoApp.createTodo(1, "Work", false, 1);
        todoApp.createTodo(2, "Clean the house", false, 2);
        System.setOut(new PrintStream(outputStream)); // Redirect console output
        todoApp.showUsersTodos(1);
        String expectedOutput = "Todos assigned to Jonas:\n" +
                "ID: 1\n" +
                "Text: Work\n" +
                "Done: false\n" +
                "\n";

        // Assert
        assertEquals(expectedOutput, outputStream.toString());
    }
}
