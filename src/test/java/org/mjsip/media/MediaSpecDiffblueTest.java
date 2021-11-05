package org.mjsip.media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class MediaSpecDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    MediaSpec actualMediaSpec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    // Assert
    assertEquals(1, actualMediaSpec.getAVP());
    assertEquals("Type", actualMediaSpec.getType());
    assertEquals(1, actualMediaSpec.getSampleRate());
    assertEquals(3, actualMediaSpec.getPacketSize());
    assertEquals("Codec", actualMediaSpec.getCodec());
    assertEquals(1, actualMediaSpec.getChannels());
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("Type 1 Codec 1 3 1", (new MediaSpec("Type", 1, "Codec", 1, 1, 3)).toString());
    assertEquals("Type 1", (new MediaSpec("Type", 1, null, 1, 1, 3)).toString());
  }
}

