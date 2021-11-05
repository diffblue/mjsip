package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class SystemUtilsDiffblueTest {
  @Test
  void testGetClassSimpleName() {
    // Arrange, Act and Assert
    assertEquals("Class name", SystemUtils.getClassSimpleName("Class name"));
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    new SystemUtils();

    // Assert
    assertNull(SystemUtils.DEFAULT_LOGGER);
  }
}

