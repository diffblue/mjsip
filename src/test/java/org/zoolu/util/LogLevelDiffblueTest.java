package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class LogLevelDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    LogLevel actualLogLevel = new LogLevel("Name", 42);

    // Assert
    assertEquals("Name", actualLogLevel.getName());
    assertEquals(42, actualLogLevel.getValue());
    assertEquals("Name", actualLogLevel.toString());
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new LogLevel("Name", 42)).equals(null));
    assertFalse((new LogLevel("Name", 42)).equals("Different type to LogLevel"));
  }

  @Test
  void testEquals2() {
    // Arrange
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act and Assert
    assertTrue(logLevel.equals(logLevel));
    int expectedHashCodeResult = logLevel.hashCode();
    assertEquals(expectedHashCodeResult, logLevel.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    LogLevel logLevel = new LogLevel("Name", 42);
    LogLevel logLevel1 = new LogLevel("Name", 42);

    // Act and Assert
    assertTrue(logLevel.equals(logLevel1));
    int notExpectedHashCodeResult = logLevel.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, logLevel1.hashCode()));
  }

  @Test
  void testEquals4() {
    // Arrange
    LogLevel logLevel = new LogLevel("Name", 0);

    // Act and Assert
    assertFalse(logLevel.equals(new LogLevel("Name", 42)));
  }
}

