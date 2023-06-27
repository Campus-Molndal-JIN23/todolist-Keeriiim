package org.campusmolndal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class dbHandlerTest{

    @Test
    public void testCheckIfExists_WhenTableExists_ShouldReturnTrue() throws SQLException { // Test the checkIfExists method when the table exists
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        Statement stmt = Mockito.mock(Statement.class); // Create a mock statement
        ResultSet rs = Mockito.mock(ResultSet.class); // Create a mock result set
        Mockito.when(conn.createStatement()).thenReturn(stmt); // When the connection is created, return the mock statement
        Mockito.when(stmt.executeQuery(Mockito.anyString())).thenReturn(rs); // When the statement is executed, return the mock result set
        Mockito.when(rs.next()).thenReturn(true); // When the result set is called, return true

        dbHandler handler = new dbHandler(); // Create a new instance of the dbHandler class
        handler.conn = conn; // Set the connection to the mock connection

        // Act
        boolean result = handler.checkIfExists(); // Call the method

        // Assert
        assertTrue(result); // Verify that the result is false
        Mockito.verify(conn, Mockito.times(1)).createStatement(); // Verify that the connection is created once
        Mockito.verify(stmt, Mockito.times(1)).executeQuery(Mockito.anyString()); // Verify that the statement is executed once
        Mockito.verify(rs, Mockito.times(1)).next(); // Verify that the result set is called once
    }

    @Test
    public void testCheckIfExists_WhenTableDoesNotExist_ShouldReturnFalse() throws SQLException { // Test that the method returns false when the table does not exist
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        Statement stmt = Mockito.mock(Statement.class); // Create a mock statement
        ResultSet rs = Mockito.mock(ResultSet.class); // Create a mock result set
        Mockito.when(conn.createStatement()).thenReturn(stmt); // When the connection is created, return the mock statement
        Mockito.when(stmt.executeQuery(Mockito.anyString())).thenReturn(rs); // When the statement is executed, return the mock result set
        Mockito.when(rs.next()).thenReturn(false); // When the result set is called, return false

        dbHandler handler = new dbHandler(); // Create a new instance of the dbHandler class
        handler.conn = conn; // Set the connection to the mock connection

        // Act
        boolean result = handler.checkIfExists(); // Call the method

        // Assert
        assertFalse(result); // Verify that the result is false
        Mockito.verify(conn, Mockito.times(1)).createStatement(); // Verify that the connection is created once
        Mockito.verify(stmt, Mockito.times(1)).executeQuery(Mockito.anyString()); // Verify that the statement is executed once
        Mockito.verify(rs, Mockito.times(1)).next(); // Verify that the result set is called once
    }

   @Test
    public void testLoadUsersFromDatabase_ShouldReturnListOfUsers() throws SQLException { // Test the loadUsersFromDatabase method
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        PreparedStatement statement = Mockito.mock(PreparedStatement.class); // Create a mock statement
        ResultSet resultSet = Mockito.mock(ResultSet.class); // Create a mock result set
        Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(statement); // When the connection is created, return the mock statement
        Mockito.when(statement.executeQuery()).thenReturn(resultSet); // When the statement is executed, return the mock result set

        Mockito.when(resultSet.next()).thenReturn(true, false); // First time true, second time false
        Mockito.when(resultSet.getInt(Mockito.anyString())).thenReturn(1).thenReturn(30);; // First time 1, second time 30
        Mockito.when(resultSet.getString(Mockito.anyString())).thenReturn("John"); // First time John

        dbHandler handler = new dbHandler(); // Create a new dbHandler object
        handler.conn = conn; // Set the connection to the mocked connection

        // Act
        List<User> users = handler.loadUsersFromDatabase(); // Call the loadUsersFromDatabase method

        // Assert
        assertEquals(1, users.size()); // Check that the size of the list is 1
        User user = users.get(0); // Get the first user in the list
        assertEquals(1, user.getId()); // Check that the id is 1
        assertEquals("John", user.getName()); // Check that the name is John
        assertEquals(30, user.getAge()); // Check that the age is 30
}

    @Test
    public void testLoadTodosFromDatabase_ShouldReturnListOfTodos() throws SQLException { // Test the loadTodosFromDatabase method
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        PreparedStatement statement = Mockito.mock(PreparedStatement.class); // Create a mock statement
        ResultSet resultSet = Mockito.mock(ResultSet.class); // Create a mock result set
        Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(statement); // When the connection is created, return the mock statement
        Mockito.when(statement.executeQuery()).thenReturn(resultSet); // When the statement is executed, return the mock result set

        Mockito.when(resultSet.next()).thenReturn(true, false); // First time true, second time false
        Mockito.when(resultSet.getInt(Mockito.anyString())).thenReturn(1); // First time 1
        Mockito.when(resultSet.getString(Mockito.anyString())).thenReturn("Task 1"); //
        Mockito.when(resultSet.getBoolean(Mockito.anyString())).thenReturn(false);
        Mockito.when(resultSet.getInt(Mockito.anyString())).thenReturn(1);

        dbHandler handler = new dbHandler(); // Create a new dbHandler object
        handler.conn = conn; // Set the connection to the mocked connection

        // Act
        List<Todo> todos = handler.loadTodosFromDatabase(); // Call the loadTodosFromDatabase method

        // Assert
        assertEquals(1, todos.size()); // Check that the size of the list is 1
        Todo todo = todos.get(0); // Get the first todo in the list
        assertEquals(1, todo.getId()); // Check that the id is 1
        assertEquals("Task 1", todo.getText()); // Check that the text is Task 1
        assertFalse(todo.isDone()); // Check that the todo is not done

        assertEquals(1, todo.getAssignedTo()); // Check that the assignedTo is 1
        Mockito.verify(conn, Mockito.times(1)).prepareStatement(Mockito.anyString()); // Verify that the connection is created once
        Mockito.verify(statement, Mockito.times(1)).executeQuery(); // Verify that the statement is executed once
        Mockito.verify(resultSet, Mockito.times(2)).next(); // Verify that the result set is called twice
        Mockito.verify(resultSet, Mockito.times(2)).getInt(Mockito.anyString()); // Verify that the result set is called twice
        Mockito.verify(resultSet, Mockito.times(1)).getString(Mockito.anyString()); // Verify that the result set is called once
        Mockito.verify(resultSet, Mockito.times(1)).getBoolean(Mockito.anyString()); // Verify that the result set is called once
    }

    @Test
    public void testFirstSetUp_ShouldCreateUsersAndToDoListsTables() throws SQLException { // Test the firstSetUp method
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        Statement stmt = Mockito.mock(Statement.class); // Create a mock statement
        Mockito.when(conn.createStatement()).thenReturn(stmt); // When the connection is created, return the mock statement

        dbHandler handler = new dbHandler(); // Create a new dbHandler object
        handler.conn = conn; // Set the connection to the mocked connection
        TodoApp todoApp = new TodoApp(); // Create a new TodoApp object

        // Act
        handler.firstSetUp(todoApp); // Call the firstSetUp method

        // Assert
        Mockito.verify(conn, Mockito.times(2)).createStatement(); // Verify that the connection is created twice
        Mockito.verify(stmt, Mockito.times(1)).executeUpdate(Mockito.contains("CREATE TABLE IF NOT EXISTS Users")); // Verify that the statement is executed once
        Mockito.verify(stmt, Mockito.times(1)).executeUpdate(Mockito.contains("CREATE TABLE IF NOT EXISTS ToDoLists")); // Verify that the statement is executed once
    }

    @Test
    public void testUpdateUserTable_ShouldInsertUsersIntoDatabase() throws SQLException { // Test the updateUserTable method
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        Statement stmt = Mockito.mock(Statement.class); // Create a mock statement
        ResultSet resultSet = Mockito.mock(ResultSet.class); // Create a mock result set
        Mockito.when(conn.createStatement()).thenReturn(stmt); // When the connection is created, return the mock statement
        Mockito.when(stmt.executeQuery(Mockito.anyString())).thenReturn(resultSet); // When the statement is executed, return the mock result set
        Mockito.when(resultSet.next()).thenReturn(false); // First time false

        dbHandler handler = new dbHandler(); // Create a new dbHandler object
        handler.conn = conn; // Set the connection to the mocked connection
        TodoApp todoApp = new TodoApp(); // Create a new TodoApp object
        List<User> users = new ArrayList<>(); // Create a new list of users
        User user = new User(1, "John", 30); // Create a new user
        users.add(user); // Add the user to the list
        todoApp.setUsers(users); // Set the users in the TodoApp object

        // Act
        handler.updateUserTable(todoApp); // Call the updateUserTable method

        // Assert
        Mockito.verify(conn, Mockito.times(1)).createStatement(); // Verify that the connection is created once
        Mockito.verify(stmt, Mockito.times(1)).executeQuery(Mockito.anyString()); // Verify that the statement is executed once
        Mockito.verify(resultSet, Mockito.times(1)).next(); // Verify that the result set is called once
        Mockito.verify(stmt, Mockito.times(1)).executeUpdate(Mockito.anyString()); // Verify that the statement is executed once
    }

    @Test
    public void testUpdateToDoListsTable_ShouldInsertTodoListsIntoDatabase() throws SQLException { // Test the updateToDoListsTable method
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        Statement stmt = Mockito.mock(Statement.class); // Create a mock statement
        ResultSet resultSet = Mockito.mock(ResultSet.class); // Create a mock result set
        Mockito.when(conn.createStatement()).thenReturn(stmt); // When the connection is created, return the mock statement
        Mockito.when(stmt.executeQuery(Mockito.anyString())).thenReturn(resultSet); // When the statement is executed, return the mock result set
        Mockito.when(resultSet.next()).thenReturn(false); // First time false

        dbHandler handler = new dbHandler(); // Create a new dbHandler object
        handler.conn = conn;// Set the connection to the mocked connection
        TodoApp todoApp = new TodoApp(); // Create a new TodoApp object
        List<Todo> todos = new ArrayList<>(); // Create a new list of todos
        Todo todo = new Todo(1, "Task 1", false, 1); // Create a new todo
        todos.add(todo); // Add the todo to the list
        todoApp.setTodos(todos); // Set the todos in the TodoApp object

        // Act
        handler.updateToDoListsTable(todoApp); // Call the updateToDoListsTable method

        // Assert
        Mockito.verify(conn, Mockito.times(1)).createStatement(); // Verify that the connection is created once
        Mockito.verify(stmt, Mockito.times(1)).executeQuery(Mockito.anyString()); // Verify that the statement is executed once
        Mockito.verify(resultSet, Mockito.times(1)).next(); // Verify that the result set is called once
        Mockito.verify(stmt, Mockito.times(1)).executeUpdate(Mockito.anyString()); // Verify that the statement is executed once
    }
}
