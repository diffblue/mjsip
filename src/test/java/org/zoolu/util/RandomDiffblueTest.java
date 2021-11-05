package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class RandomDiffblueTest {
  @Test
  void testNextInt() {
    // Arrange, Act and Assert
    assertEquals(0, Random.nextInt(1));
    assertThrows(ArithmeticException.class, () -> Random.nextInt(0));
  }

  @Test
  void testNextLong() {
    // Arrange, Act and Assert
    assertEquals(0L, Random.nextLong(1L));
    assertThrows(ArithmeticException.class, () -> Random.nextLong(0L));
  }

  @Test
  void testNextBytes() {
    // Arrange, Act and Assert
    assertEquals(3, Random.nextBytes(3).length);
    assertThrows(NegativeArraySizeException.class, () -> Random.nextBytes(-1));
  }

  @Test
  void testNextString() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> Random.nextString(-1));
  }

  @Test
  void testNextNumString() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> Random.nextNumString(-1));
  }

  @Test
  void testNextHexString() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> Random.nextHexString(-1));
  }

  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertSame((new Random()).rand, Random.rand);
  }
}

