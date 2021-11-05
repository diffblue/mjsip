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

class G711ToPcmAudioInputStreamDiffblueTest {
  @Test
  void testInnerRead() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    G711ToPcmAudioInputStream g711ToPcmAudioInputStream = new G711ToPcmAudioInputStream(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L));

    // Act and Assert
    assertEquals(2, g711ToPcmAudioInputStream.innerRead("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testInnerRead2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'});
    G711ToPcmAudioInputStream g711ToPcmAudioInputStream = new G711ToPcmAudioInputStream(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L));

    // Act and Assert
    assertEquals(2, g711ToPcmAudioInputStream.innerRead("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testInnerRead3() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("UAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    G711ToPcmAudioInputStream g711ToPcmAudioInputStream = new G711ToPcmAudioInputStream(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L));

    // Act and Assert
    assertEquals(2, g711ToPcmAudioInputStream.innerRead("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testInnerRead4() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("ﾀAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    G711ToPcmAudioInputStream g711ToPcmAudioInputStream = new G711ToPcmAudioInputStream(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L));

    // Act and Assert
    assertEquals(2, g711ToPcmAudioInputStream.innerRead("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testInnerRead5() throws UnsupportedEncodingException {
    // Arrange
    G711ToPcmAudioInputStream g711ToPcmAudioInputStream = new G711ToPcmAudioInputStream(
        new AudioInputStream(null, new AudioFormat(new AudioFormat.Encoding("Name"), 10.0f, 3, 1, 3, 10.0f, true), 3L));

    // Act and Assert
    assertEquals(0, g711ToPcmAudioInputStream.innerRead("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testInnerRead6() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new G711ToPcmAudioInputStream(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L))).innerRead(new byte[]{}));
  }

  @Test
  void testAvailable() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(6,
        (new G711ToPcmAudioInputStream(new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)))
            .available());
  }

  @Test
  void testInnerAvailable() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(6,
        (new G711ToPcmAudioInputStream(new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)))
            .innerAvailable());
  }

  @Test
  void testMarkSupported() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertFalse(
        (new G711ToPcmAudioInputStream(new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)))
            .markSupported());
  }

  @Test
  void testRead() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(184,
        (new G711ToPcmAudioInputStream(new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)))
            .read());
  }

  @Test
  void testRead2() {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'});

    // Act and Assert
    assertEquals(128,
        (new G711ToPcmAudioInputStream(new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)))
            .read());
  }

  @Test
  void testRead3() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("UAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(248,
        (new G711ToPcmAudioInputStream(new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)))
            .read());
  }

  @Test
  void testSkip() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        (new G711ToPcmAudioInputStream(new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)))
            .skip(1L));
  }
}

