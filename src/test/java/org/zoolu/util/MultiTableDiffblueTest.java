package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class MultiTableDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    MultiTable actualMultiTable = new MultiTable();

    // Assert
    assertEquals("{}", actualMultiTable.toString());
    assertTrue(actualMultiTable.table.isEmpty());
  }

  @Test
  void testClear() {
    // Arrange
    MultiTable multiTable = new MultiTable();

    // Act
    multiTable.clear();

    // Assert
    assertTrue(multiTable.isEmpty());
  }

  @Test
  void testContains() {
    // Arrange, Act and Assert
    assertFalse((new MultiTable()).contains("Value"));
  }

  @Test
  void testContainsKey() {
    // Arrange, Act and Assert
    assertFalse((new MultiTable()).containsKey("Key"));
  }

  @Test
  void testContainsKey2() {
    // Arrange
    MultiTable multiTable = new MultiTable();
    multiTable.put("Key", "Value");

    // Act and Assert
    assertTrue(multiTable.containsKey("Key"));
  }

  @Test
  void testGet() {
    // Arrange, Act and Assert
    assertNull((new MultiTable()).get("Key"));
  }

  @Test
  void testGet2() {
    // Arrange
    MultiTable multiTable = new MultiTable();

    // Act and Assert
    assertNull(multiTable.get(new Vector<Object>(1)));
  }

  @Test
  void testIsEmpty() {
    // Arrange, Act and Assert
    assertTrue((new MultiTable()).isEmpty());
  }

  @Test
  void testIsEmpty2() {
    // Arrange
    MultiTable multiTable = new MultiTable();
    multiTable.put("Key", "Value");

    // Act and Assert
    assertFalse(multiTable.isEmpty());
  }

  @Test
  void testPut() {
    // Arrange
    MultiTable multiTable = new MultiTable();

    // Act
    multiTable.put("Key", "Value");

    // Assert
    assertFalse(multiTable.isEmpty());
  }

  @Test
  void testPut2() {
    // Arrange
    MultiTable multiTable = new MultiTable();

    // Act
    multiTable.put(new Vector<Object>(1), "Value");

    // Assert
    assertFalse(multiTable.isEmpty());
  }

  @Test
  void testRemove() {
    // Arrange
    MultiTable multiTable = new MultiTable();

    // Act
    multiTable.remove("Key");

    // Assert
    assertTrue(multiTable.isEmpty());
  }

  @Test
  void testRemove2() {
    // Arrange
    MultiTable multiTable = new MultiTable();

    // Act
    multiTable.remove("Key", "Value");

    // Assert that nothing has changed
    assertTrue(multiTable.isEmpty());
  }

  @Test
  void testRemove3() {
    // Arrange
    MultiTable multiTable = new MultiTable();
    multiTable.put("Key", "Value");

    // Act
    multiTable.remove("Key", "Value");

    // Assert
    assertTrue(multiTable.isEmpty());
  }

  @Test
  void testRemove4() {
    // Arrange
    MultiTable multiTable = new MultiTable();

    // Act
    multiTable.remove(0, "Value");

    // Assert that nothing has changed
    assertTrue(multiTable.isEmpty());
  }

  @Test
  void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new MultiTable()).size());
  }
}

