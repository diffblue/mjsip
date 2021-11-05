package org.zoolu.sound.codec.g711;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import org.junit.jupiter.api.Test;

class PcmToG711AudioInputStreamDiffblueTest {
  @Test
  void testInnerRead() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    PcmToG711AudioInputStream pcmToG711AudioInputStream = new PcmToG711AudioInputStream(input_stream,
        new AudioFormat(10.0f, 3, 1, true, true));

    // Act and Assert
    assertEquals(1, pcmToG711AudioInputStream.innerRead("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testInnerRead2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream(new byte[]{'A', 0, 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'});
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    PcmToG711AudioInputStream pcmToG711AudioInputStream = new PcmToG711AudioInputStream(input_stream,
        new AudioFormat(10.0f, 3, 1, true, true));

    // Act and Assert
    assertEquals(1, pcmToG711AudioInputStream.innerRead("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testInnerRead3() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("A￿AAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    PcmToG711AudioInputStream pcmToG711AudioInputStream = new PcmToG711AudioInputStream(input_stream,
        new AudioFormat(10.0f, 3, 1, true, true));

    // Act and Assert
    assertEquals(1, pcmToG711AudioInputStream.innerRead("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testInnerRead4() throws UnsupportedEncodingException {
    // Arrange
    AudioInputStream input_stream = new AudioInputStream(null,
        new AudioFormat(new AudioFormat.Encoding("Name"), 10.0f, 3, 1, 3, 10.0f, true), 3L);

    PcmToG711AudioInputStream pcmToG711AudioInputStream = new PcmToG711AudioInputStream(input_stream,
        new AudioFormat(10.0f, 3, 1, true, true));

    // Act and Assert
    assertEquals(0, pcmToG711AudioInputStream.innerRead("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testInnerRead5() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new PcmToG711AudioInputStream(input_stream, new AudioFormat(10.0f, 3, 1, true, true)))
            .innerRead(new byte[]{}));
  }

  @Test
  void testAvailable() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    // Act and Assert
    assertEquals(1,
        (new PcmToG711AudioInputStream(input_stream, new AudioFormat(10.0f, 3, 1, true, true))).available());
  }

  @Test
  void testInnerAvailable() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    // Act and Assert
    assertEquals(1,
        (new PcmToG711AudioInputStream(input_stream, new AudioFormat(10.0f, 3, 1, true, true))).innerAvailable());
  }

  @Test
  void testMarkSupported() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    // Act and Assert
    assertFalse(
        (new PcmToG711AudioInputStream(input_stream, new AudioFormat(10.0f, 3, 1, true, true))).markSupported());
  }

  @Test
  void testSkip() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioInputStream input_stream = new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L);

    // Act and Assert
    assertEquals(0L, (new PcmToG711AudioInputStream(input_stream, new AudioFormat(10.0f, 3, 1, true, true))).skip(1L));
  }
}

