package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class VectorUtilsDiffblueTest {
  @Test
  void testArrayToVector() {
    // Arrange and Act
    Vector actualArrayToVectorResult = VectorUtils.arrayToVector(new Object[]{"Array"});

    // Assert
    assertEquals(1, actualArrayToVectorResult.size());
    assertEquals("Array", actualArrayToVectorResult.get(0));
  }

  @Test
  void testAddArray() {
    // Arrange
    Vector vector = new Vector(1);

    // Act
    VectorUtils.addArray(vector, new Object[]{"42"});

    // Assert
    assertEquals(1, vector.size());
    assertEquals("42", vector.get(0));
  }

  @Test
  void testAddVector() {
    // Arrange
    Vector vector = new Vector(1);

    Vector vector1 = new Vector(1);
    vector1.add("42");

    // Act
    VectorUtils.addVector(vector, vector1);

    // Assert
    assertEquals(1, vector.size());
    assertEquals("42", vector.get(0));
  }

  @Test
  void testVectorToArray() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> VectorUtils.vectorToArray(new Vector(1), new Object[]{"Array"}));
  }

  @Test
  void testVectorToArray2() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act and Assert
    assertEquals(1, VectorUtils.vectorToArray(vector, new Object[]{"Array"}).length);
  }

  @Test
  void testCopy() {
    // Arrange, Act and Assert
    assertTrue(VectorUtils.copy(new Vector(1)).isEmpty());
  }

  @Test
  void testCopy2() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    Vector actualCopyResult = VectorUtils.copy(vector);

    // Assert
    assertEquals(1, actualCopyResult.size());
    assertEquals("42", actualCopyResult.get(0));
  }
}

