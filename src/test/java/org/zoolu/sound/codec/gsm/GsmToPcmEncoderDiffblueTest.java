package org.zoolu.sound.codec.gsm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class GsmToPcmEncoderDiffblueTest {
  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    GsmToPcmEncoder actualGsmToPcmEncoder = new GsmToPcmEncoder();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Assert
    assertEquals(0, actualGsmToPcmEncoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode() throws UnsupportedEncodingException {
    // Arrange
    GsmToPcmEncoder gsmToPcmEncoder = new GsmToPcmEncoder();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, gsmToPcmEncoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }
}

