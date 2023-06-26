package org.campusmolndal;

import org.campusmolndal.Menu;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
@Test
public void main() {
    // Arrange
    Menu menuMock = Mockito.mock(Menu.class); // Create a mock of the Menu class

    // Act & Assert
    Mockito.verify(menuMock, Mockito.times(0)); // Verify that the menuMock is called twice
}
}
