package org.campusmolndal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.configuration.IMockitoConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class dbHandlerTest{

    @Test
    public void testCheckIfExists_WhenTableExists_ShouldReturnTrue() throws SQLException { // Test the checkIfExists method when the table exists
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        Statement stmt = Mockito.mock(Statement.class); // Create a mock statement
        ResultSet rs = Mockito.mock(ResultSet.class); // Create a mock result set
        when(conn.createStatement()).thenReturn(stmt); // When the connection is created, return the mock statement
        when(stmt.executeQuery(anyString())).thenReturn(rs); // When the statement is executed, return the mock result set
        when(rs.next()).thenReturn(true); // When the result set is called, return true

        dbHandler handler = new dbHandler(); // Create a new instance of the dbHandler class
        handler.conn = conn; // Set the connection to the mock connection

        // Act
        boolean result = handler.checkIfExists(); // Call the method

        // Assert
        assertTrue(result); // Verify that the result is false
        verify(conn, Mockito.times(1)).createStatement(); // Verify that the connection is created once
        verify(stmt, Mockito.times(1)).executeQuery(anyString()); // Verify that the statement is executed once
        verify(rs, Mockito.times(1)).next(); // Verify that the result set is called once
    }

    @Test
    public void testCheckIfExists_WhenTableDoesNotExist_ShouldReturnFalse() throws SQLException { // Test that the method returns false when the table does not exist
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        Statement stmt = Mockito.mock(Statement.class); // Create a mock statement
        ResultSet rs = Mockito.mock(ResultSet.class); // Create a mock result set
        when(conn.createStatement()).thenReturn(stmt); // When the connection is created, return the mock statement
        when(stmt.executeQuery(anyString())).thenReturn(rs); // When the statement is executed, return the mock result set
        when(rs.next()).thenReturn(false); // When the result set is called, return false

        dbHandler handler = new dbHandler(); // Create a new instance of the dbHandler class
        handler.conn = conn; // Set the connection to the mock connection

        // Act
        boolean result = handler.checkIfExists(); // Call the method

        // Assert
        assertFalse(result); // Verify that the result is false
        verify(conn, Mockito.times(1)).createStatement(); // Verify that the connection is created once
        verify(stmt, Mockito.times(1)).executeQuery(anyString()); // Verify that the statement is executed once
        verify(rs, Mockito.times(1)).next(); // Verify that the result set is called once
    }

   @Test
    public void testLoadUsersFromDatabase_ShouldReturnListOfUsers() throws SQLException { // Test the loadUsersFromDatabase method
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        PreparedStatement statement = Mockito.mock(PreparedStatement.class); // Create a mock statement
        ResultSet resultSet = Mockito.mock(ResultSet.class); // Create a mock result set
        when(conn.prepareStatement(anyString())).thenReturn(statement); // When the connection is created, return the mock statement
        when(statement.executeQuery()).thenReturn(resultSet); // When the statement is executed, return the mock result set

        when(resultSet.next()).thenReturn(true, false); // First time true, second time false
        when(resultSet.getInt(anyString())).thenReturn(1).thenReturn(30);; // First time 1, second time 30
        when(resultSet.getString(anyString())).thenReturn("John"); // First time John

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
        when(conn.prepareStatement(anyString())).thenReturn(statement); // When the connection is created, return the mock statement
        when(statement.executeQuery()).thenReturn(resultSet); // When the statement is executed, return the mock result set

        when(resultSet.next()).thenReturn(true, false); // First time true, second time false
        when(resultSet.getInt(anyString())).thenReturn(1); // First time 1
        when(resultSet.getString(anyString())).thenReturn("Task 1"); //
        when(resultSet.getBoolean(anyString())).thenReturn(false);
        when(resultSet.getInt(anyString())).thenReturn(1);

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
        verify(conn, Mockito.times(1)).prepareStatement(anyString()); // Verify that the connection is created once
        verify(statement, Mockito.times(1)).executeQuery(); // Verify that the statement is executed once
        verify(resultSet, Mockito.times(2)).next(); // Verify that the result set is called twice
        verify(resultSet, Mockito.times(2)).getInt(anyString()); // Verify that the result set is called twice
        verify(resultSet, Mockito.times(1)).getString(anyString()); // Verify that the result set is called once
        verify(resultSet, Mockito.times(1)).getBoolean(anyString()); // Verify that the result set is called once
    }

    @Test
    public void testFirstSetUp_ShouldCreateUsersAndToDoListsTables() throws SQLException { // Test the firstSetUp method
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        Statement stmt = Mockito.mock(Statement.class); // Create a mock statement
        when(conn.createStatement()).thenReturn(stmt); // When the connection is created, return the mock statement

        dbHandler handler = new dbHandler(); // Create a new dbHandler object
        handler.conn = conn; // Set the connection to the mocked connection
        TodoApp todoApp = new TodoApp(); // Create a new TodoApp object

        // Act
        handler.firstSetUp(todoApp); // Call the firstSetUp method

        // Assert
        verify(conn, Mockito.times(2)).createStatement(); // Verify that the connection is created twice
        verify(stmt, Mockito.times(1)).executeUpdate(Mockito.contains("CREATE TABLE IF NOT EXISTS Users")); // Verify that the statement is executed once
        verify(stmt, Mockito.times(1)).executeUpdate(Mockito.contains("CREATE TABLE IF NOT EXISTS ToDoLists")); // Verify that the statement is executed once
    }

    @Test
    public void testUpdateUserTable() throws SQLException { // Test the updateUserTable method
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        TodoApp todoApp = new TodoApp(); // Create a new TodoApp object
        dbHandler dbHandler = new dbHandler(); // Create a new dbHandler object

        // Mock the connection.createStatement() method
        Statement stmt = Mockito.mock(Statement.class);
        when(conn.createStatement()).thenReturn(stmt);

        List<User> userList = new ArrayList<>();
        userList.clear();
        userList.add(new User(1, "John Doe", 25));
        todoApp.setUsers(userList);

        // Act
        dbHandler.updateUserTable(todoApp);

        // Assert
        List userCheck = dbHandler.loadUsersFromDatabase();
        assertEquals(userList.size(), userCheck.size());
    }

    @Test
    public void testUpdateToDoTable() throws SQLException { // Test the updateToDoTable method
        // Arrange
        Connection conn = Mockito.mock(Connection.class); // Create a mock connection
        TodoApp todoApp1 = new TodoApp(); // Create a new TodoApp object
        dbHandler dbHandler1 = new dbHandler(); // Create a new dbHandler object

        // Mock the connection.createStatement() method
        Statement stmt = Mockito.mock(Statement.class);
        when(conn.createStatement()).thenReturn(stmt);

        List<Todo> todoList1 = new ArrayList<>();
        todoList1.clear();
        todoList1.add(new Todo (1, "Task 1", false, 1));
        todoApp1.setTodos(todoList1);

        // Act
        dbHandler1.updateToDoListsTable(todoApp1);

        // Assert
        List <Todo> toDoCheck = dbHandler1.loadTodosFromDatabase();
        assertEquals(todoList1.size(), toDoCheck.size());
    }
}
