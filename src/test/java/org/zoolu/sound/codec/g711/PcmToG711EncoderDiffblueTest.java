package org.zoolu.sound.codec.g711;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioFormat;
import org.junit.jupiter.api.Test;

class PcmToG711EncoderDiffblueTest {
  @Test
  void testALAWConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    PcmToG711Encoder.ALAW actualAlaw = new PcmToG711Encoder.ALAW();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Assert
    assertEquals(1, actualAlaw.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("Name", (new PcmToG711Encoder(new AudioFormat.Encoding("Name"))).g711_encoding.toString());
  }

  @Test
  void testEncode() throws UnsupportedEncodingException {
    // Arrange
    PcmToG711Encoder pcmToG711Encoder = new PcmToG711Encoder(new AudioFormat.Encoding("Name"));
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, pcmToG711Encoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode2() throws UnsupportedEncodingException {
    // Arrange
    PcmToG711Encoder pcmToG711Encoder = new PcmToG711Encoder(new AudioFormat.Encoding("Name"));

    // Act and Assert
    assertEquals(1, pcmToG711Encoder.encode(new byte[]{'A', 'A', 'A', 0, 'A', 'A', 'A', 'A'}, 2, 3,
        "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode3() throws UnsupportedEncodingException {
    // Arrange
    PcmToG711Encoder pcmToG711Encoder = new PcmToG711Encoder(new AudioFormat.Encoding("Name"));
    byte[] in_buff = "AAA￿AAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, pcmToG711Encoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode4() throws UnsupportedEncodingException {
    // Arrange
    PcmToG711Encoder pcmToG711Encoder = new PcmToG711Encoder(new AudioFormat.Encoding("Name"));

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> pcmToG711Encoder.encode(new byte[]{}, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode5() throws UnsupportedEncodingException {
    // Arrange
    PcmToG711Encoder pcmToG711Encoder = new PcmToG711Encoder(new AudioFormat.Encoding("Name"));
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> pcmToG711Encoder.encode(in_buff, -1, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode6() throws UnsupportedEncodingException {
    // Arrange
    PcmToG711Encoder pcmToG711Encoder = new PcmToG711Encoder(new AudioFormat.Encoding("Name"));

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> pcmToG711Encoder.encode("AAAAAAAA".getBytes("UTF-8"), 2, 3, new byte[]{}, 2));
  }

  @Test
  void testULAWConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    PcmToG711Encoder.ULAW actualUlaw = new PcmToG711Encoder.ULAW();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Assert
    assertEquals(1, actualUlaw.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }
}

