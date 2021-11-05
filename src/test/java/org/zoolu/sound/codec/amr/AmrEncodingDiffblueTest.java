package org.zoolu.sound.codec.amr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AmrEncodingDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals(1, (new AmrEncoding("Name", 1)).getMode());
  }
}

