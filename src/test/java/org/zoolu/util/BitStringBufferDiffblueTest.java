package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BitStringBufferDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertTrue((new BitStringBuffer()).buffer.isEmpty());
  }

  @Test
  void testClear() {
    // Arrange
    BitStringBuffer bitStringBuffer = new BitStringBuffer();

    // Act
    bitStringBuffer.clear();

    // Assert
    assertTrue(bitStringBuffer.buffer.isEmpty());
  }

  @Test
  void testAppend() {
    // Arrange
    BitStringBuffer bitStringBuffer = new BitStringBuffer();

    // Act and Assert
    assertSame(bitStringBuffer, bitStringBuffer.append(new BitStringBuffer()));
  }
}

