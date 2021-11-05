package org.zoolu.sound.codec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AMRDiffblueTest {
  @Test
  void testFrameSize() {
    // Arrange, Act and Assert
    assertEquals(14, AMR.frameSize(1));
    assertEquals(13, AMR.frameSize(0));
    assertEquals(Short.SIZE, AMR.frameSize(2));
    assertEquals(18, AMR.frameSize(3));
    assertEquals(20, AMR.frameSize(4));
    assertEquals(21, AMR.frameSize(5));
    assertEquals(27, AMR.frameSize(6));
    assertEquals(0, AMR.frameSize(-1));
    assertEquals(Integer.SIZE, AMR.frameSize(7));
    assertEquals(6, AMR.frameSize(8));
    assertEquals(1, AMR.frameSize(AMR.M15_NO_DATA));
  }

  @Test
  void testFramePayloadBitSize() {
    // Arrange, Act and Assert
    assertEquals(103, AMR.framePayloadBitSize(1));
    assertEquals(95, AMR.framePayloadBitSize(0));
    assertEquals(118, AMR.framePayloadBitSize(2));
    assertEquals(134, AMR.framePayloadBitSize(3));
    assertEquals(148, AMR.framePayloadBitSize(4));
    assertEquals(159, AMR.framePayloadBitSize(5));
    assertEquals(204, AMR.framePayloadBitSize(6));
    assertEquals(0, AMR.framePayloadBitSize(-1));
    assertEquals(244, AMR.framePayloadBitSize(7));
    assertEquals(39, AMR.framePayloadBitSize(8));
    assertEquals(0, AMR.framePayloadBitSize(AMR.M15_NO_DATA));
  }
}

