package org.zoolu.sound.codec.g711;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class G711EncodingDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("Name", (new G711Encoding("Name")).toString());
  }
}

