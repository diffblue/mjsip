package org.zoolu.sound.codec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class G711DiffblueTest {
  @Test
  void testSearch() {
    // Arrange, Act and Assert
    assertEquals(4, G711.search(42, new int[]{1, 1, 1, 1}));
    assertEquals(0, G711.search(1, new int[]{1, 1, 1, 1}));
  }

  @Test
  void testLinear2alaw() {
    // Arrange, Act and Assert
    assertEquals(215, G711.linear2alaw(42));
    assertEquals(90, G711.linear2alaw(-1));
    assertEquals(42, G711.linear2alaw(Integer.MIN_VALUE));
    assertEquals(170, G711.linear2alaw(32767));
  }

  @Test
  void testAlaw2linear() {
    // Arrange, Act and Assert
    assertEquals(-32256, G711.alaw2linear(42));
    assertEquals(-8, G711.alaw2linear(85));
    assertEquals(5504, G711.alaw2linear(128));
  }

  @Test
  void testLinear2ulaw() {
    // Arrange, Act and Assert
    assertEquals(250, G711.linear2ulaw(42));
    assertEquals(231, G711.linear2ulaw(255));
    assertEquals(Float.MAX_EXPONENT, G711.linear2ulaw(-1));
    assertEquals(128, G711.linear2ulaw(32767));
  }

  @Test
  void testUlaw2linear() {
    // Arrange, Act and Assert
    assertEquals(-5372, G711.ulaw2linear(42));
    assertEquals(0, G711.ulaw2linear(-1));
  }

  @Test
  void testAlaw2ulaw() {
    // Arrange, Act and Assert
    assertEquals(0, G711.alaw2ulaw(42));
    assertEquals(209, G711.alaw2ulaw(255));
  }

  @Test
  void testUlaw2alaw() {
    // Arrange, Act and Assert
    assertEquals(0, G711.ulaw2alaw(42));
    assertEquals(213, G711.ulaw2alaw(255));
  }
}

