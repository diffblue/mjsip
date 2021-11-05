package org.zoolu.sound.codec.g726;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class G726EncodingDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("Name", (new G726Encoding("Name")).toString());
  }
}

