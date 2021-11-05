package org.zoolu.sound.codec.gsm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class PcmToGsmEncoderDiffblueTest {
  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    PcmToGsmEncoder actualPcmToGsmEncoder = new PcmToGsmEncoder();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Assert
    assertEquals(0, actualPcmToGsmEncoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testConstructor2() throws UnsupportedEncodingException {
    // Arrange and Act
    PcmToGsmEncoder actualPcmToGsmEncoder = new PcmToGsmEncoder();
    byte[] in_buff = "AAA￿AAAA".getBytes("UTF-8");

    // Assert
    assertEquals(0, actualPcmToGsmEncoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode() throws UnsupportedEncodingException {
    // Arrange
    PcmToGsmEncoder pcmToGsmEncoder = new PcmToGsmEncoder();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, pcmToGsmEncoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode2() throws UnsupportedEncodingException {
    // Arrange
    PcmToGsmEncoder pcmToGsmEncoder = new PcmToGsmEncoder();
    byte[] in_buff = "AAA￿AAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, pcmToGsmEncoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode3() throws UnsupportedEncodingException {
    // Arrange
    PcmToGsmEncoder pcmToGsmEncoder = new PcmToGsmEncoder();

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> pcmToGsmEncoder.encode(new byte[]{}, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode4() throws UnsupportedEncodingException {
    // Arrange
    PcmToGsmEncoder pcmToGsmEncoder = new PcmToGsmEncoder();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> pcmToGsmEncoder.encode(in_buff, -1, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode5() throws UnsupportedEncodingException {
    // Arrange
    PcmToGsmEncoder pcmToGsmEncoder = new PcmToGsmEncoder();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(NegativeArraySizeException.class,
        () -> pcmToGsmEncoder.encode(in_buff, 2, Integer.MIN_VALUE, "AAAAAAAA".getBytes("UTF-8"), 2));
  }
}

