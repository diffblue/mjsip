package org.zoolu.sound;

import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import org.junit.jupiter.api.Test;

class AudioOutputStreamDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    ByteArrayOutputStream os = new ByteArrayOutputStream(3);

    // Act
    AudioOutputStream actualAudioOutputStream = new AudioOutputStream(os,
        SimpleAudioSystem.getBaseAudioFormat(10.0f, 1));

    // Assert
    AudioFormat expectedFormat = actualAudioOutputStream.format;
    assertSame(expectedFormat, actualAudioOutputStream.getFormat());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AudioOutputStream actualAudioOutputStream = new AudioOutputStream(SimpleAudioSystem.getBaseAudioFormat(10.0f, 1));

    // Assert
    AudioFormat expectedFormat = actualAudioOutputStream.format;
    assertSame(expectedFormat, actualAudioOutputStream.getFormat());
  }

  @Test
  void testClose() throws IOException {
    // Arrange
    ByteArrayOutputStream os = new ByteArrayOutputStream(3);
    AudioOutputStream audioOutputStream = new AudioOutputStream(os, SimpleAudioSystem.getBaseAudioFormat(10.0f, 1));

    // Act
    audioOutputStream.close();

    // Assert that nothing has changed
    AudioFormat expectedFormat = audioOutputStream.format;
    assertSame(expectedFormat, audioOutputStream.getFormat());
  }

  @Test
  void testFlush() throws IOException {
    // Arrange
    ByteArrayOutputStream os = new ByteArrayOutputStream(3);
    AudioOutputStream audioOutputStream = new AudioOutputStream(os, SimpleAudioSystem.getBaseAudioFormat(10.0f, 1));

    // Act
    audioOutputStream.flush();

    // Assert that nothing has changed
    AudioFormat expectedFormat = audioOutputStream.format;
    assertSame(expectedFormat, audioOutputStream.getFormat());
  }

  @Test
  void testWrite() throws IOException {
    // Arrange
    ByteArrayOutputStream os = new ByteArrayOutputStream(3);
    AudioOutputStream audioOutputStream = new AudioOutputStream(os, SimpleAudioSystem.getBaseAudioFormat(10.0f, 1));

    // Act
    audioOutputStream.write(19088743);

    // Assert that nothing has changed
    AudioFormat expectedFormat = audioOutputStream.format;
    assertSame(expectedFormat, audioOutputStream.getFormat());
  }

  @Test
  void testWrite2() throws IOException {
    // Arrange
    ByteArrayOutputStream os = new ByteArrayOutputStream(3);
    AudioOutputStream audioOutputStream = new AudioOutputStream(os, SimpleAudioSystem.getBaseAudioFormat(10.0f, 1));

    // Act
    audioOutputStream.write("AAAAAAAA".getBytes("UTF-8"));

    // Assert that nothing has changed
    AudioFormat expectedFormat = audioOutputStream.format;
    assertSame(expectedFormat, audioOutputStream.getFormat());
  }

  @Test
  void testWrite3() throws IOException {
    // Arrange
    ByteArrayOutputStream os = new ByteArrayOutputStream(3);
    AudioOutputStream audioOutputStream = new AudioOutputStream(os, SimpleAudioSystem.getBaseAudioFormat(10.0f, 1));

    // Act
    audioOutputStream.write("AAAAAAAA".getBytes("UTF-8"), 0, 3);

    // Assert that nothing has changed
    AudioFormat expectedFormat = audioOutputStream.format;
    assertSame(expectedFormat, audioOutputStream.getFormat());
  }
}

