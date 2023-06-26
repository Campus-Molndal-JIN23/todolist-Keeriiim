package org.campusmolndal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest{

@Test
public void testUserConstructor() { // Test for the constructor of User
    // Arrange
    int id = 1;
    String name = "Test";
    int age = 5;

    // Act
    User user = new User(id, name, age);

    // Assert
    assertEquals(id, user.getId());
    assertEquals(name, user.getName());
    assertEquals(age, user.getAge());
}

}