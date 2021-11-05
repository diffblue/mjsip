package org.zoolu.sound.codec.g726;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import org.junit.jupiter.api.Test;

class G726ToPcmAudioInputStreamDiffblueTest {
  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0, (new G726ToPcmAudioInputStream(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L))).aux_buffer.length);
  }

  @Test
  void testMarkSupported() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertFalse(
        (new G726ToPcmAudioInputStream(new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)))
            .markSupported());
  }

  @Test
  void testRead() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0,
        (new G726ToPcmAudioInputStream(new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)))
            .read(new byte[]{}));
  }

  @Test
  void testSkip() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        (new G726ToPcmAudioInputStream(new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)))
            .skip(1L));
  }
}

