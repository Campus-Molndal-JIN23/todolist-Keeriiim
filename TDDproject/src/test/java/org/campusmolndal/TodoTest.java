package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    public void testTodoConstructor() { // Test for the constructor of Todo
        // Arrange
        int id = 1;
        String text = "Test";
        boolean done = false;
        int assignedTo = 1;

        // Act
        Todo todo = new Todo(id, text, done, assignedTo);

        // Assert
        assertEquals(id, todo.getId());
        assertEquals(text, todo.getText());
        assertEquals(done, todo.isDone());
        assertEquals(assignedTo, todo.getAssignedTo());
    }
}