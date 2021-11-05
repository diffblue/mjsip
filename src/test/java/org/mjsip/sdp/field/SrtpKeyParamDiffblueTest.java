package org.mjsip.sdp.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class SrtpKeyParamDiffblueTest {
  @Test
  void testGetKeySalt2() {
    // Arrange, Act and Assert
    assertNull((new SrtpKeyParam("inline")).getKeySalt());
  }

  @Test
  void testGetKeySalt3() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(24,
        (new SrtpKeyParam("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "Lifetime", "Mki len")).getKeySalt().length);
  }

  @Test
  void testGetKeySalt4() throws UnsupportedEncodingException {
    // Arrange and Act
    byte[] actualKeySalt = (new SrtpKeyParam("AAAAAAAA".getBytes("UTF-8"), "Lifetime", "Mki len")).getKeySalt();

    // Assert
    assertEquals(8, actualKeySalt.length);
    assertEquals('A', actualKeySalt[0]);
    assertEquals('A', actualKeySalt[1]);
    assertEquals('A', actualKeySalt[2]);
    assertEquals('A', actualKeySalt[3]);
    assertEquals('A', actualKeySalt[4]);
    assertEquals('A', actualKeySalt[5]);
    assertEquals('A', actualKeySalt[6]);
    assertEquals('A', actualKeySalt[7]);
  }

  @Test
  void testGetKeySalt6() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(Short.SIZE,
        (new SrtpKeyParam("AAAAAAAAAAAAAAAA".getBytes("UTF-8"), "Lifetime", "Mki len")).getKeySalt().length);
  }
}

