package org.zoolu.sound.codec.g726;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import org.junit.jupiter.api.Test;

class PcmToG726AudioInputStreamDiffblueTest {
  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    // Act and Assert
    assertEquals(0,
        (new PcmToG726AudioInputStream(input_stream, new AudioFormat(10.0f, 3, 1, true, true))).aux_buffer.length);
  }

  @Test
  void testMarkSupported() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    // Act and Assert
    assertFalse(
        (new PcmToG726AudioInputStream(input_stream, new AudioFormat(10.0f, 3, 1, true, true))).markSupported());
  }

  @Test
  void testRead() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    // Act and Assert
    assertEquals(0,
        (new PcmToG726AudioInputStream(input_stream, new AudioFormat(10.0f, 3, 1, true, true))).read(new byte[]{}));
  }

  @Test
  void testSkip() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    // Act and Assert
    assertEquals(0L, (new PcmToG726AudioInputStream(input_stream, new AudioFormat(10.0f, 3, 1, true, true))).skip(1L));
  }
}

