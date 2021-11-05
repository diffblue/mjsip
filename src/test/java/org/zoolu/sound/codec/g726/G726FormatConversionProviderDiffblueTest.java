package org.zoolu.sound.codec.g726;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import org.junit.jupiter.api.Test;

class G726FormatConversionProviderDiffblueTest {
  @Test
  void testGetTargetEncodings() {
    // Arrange
    G726FormatConversionProvider g726FormatConversionProvider = new G726FormatConversionProvider();

    // Act
    AudioFormat.Encoding[] actualTargetEncodings = g726FormatConversionProvider
        .getTargetEncodings(new AudioFormat(10.0f, 3, 1, true, true));

    // Assert
    assertSame(g726FormatConversionProvider.G726_ENCODING, actualTargetEncodings);
    assertEquals(3, actualTargetEncodings.length);
  }

  @Test
  void testGetTargetEncodings2() {
    // Arrange
    G726FormatConversionProvider g726FormatConversionProvider = new G726FormatConversionProvider();

    // Act
    AudioFormat.Encoding[] actualTargetEncodings = g726FormatConversionProvider
        .getTargetEncodings(new AudioFormat(10.0f, 3, 1, false, true));

    // Assert
    assertSame(g726FormatConversionProvider.NO_ENCODING, actualTargetEncodings);
    assertEquals(0, actualTargetEncodings.length);
  }

  @Test
  void testGetTargetEncodings3() {
    // Arrange
    G726FormatConversionProvider g726FormatConversionProvider = new G726FormatConversionProvider();

    // Act
    AudioFormat.Encoding[] actualTargetEncodings = g726FormatConversionProvider
        .getTargetEncodings(new AudioFormat(0.5f, 3, 1, false, true));

    // Assert
    assertSame(g726FormatConversionProvider.NO_ENCODING, actualTargetEncodings);
    assertEquals(0, actualTargetEncodings.length);
  }

  @Test
  void testGetTargetFormats() {
    // Arrange
    G726FormatConversionProvider g726FormatConversionProvider = new G726FormatConversionProvider();
    AudioFormat.Encoding target_encoding = new AudioFormat.Encoding("Name");

    // Act and Assert
    assertEquals(0, g726FormatConversionProvider.getTargetFormats(target_encoding,
        new AudioFormat(10.0f, 3, 1, true, true)).length);
  }

  @Test
  void testGetAudioInputStream() throws UnsupportedEncodingException {
    // Arrange
    G726FormatConversionProvider g726FormatConversionProvider = new G726FormatConversionProvider();
    AudioFormat.Encoding target_encoding = new AudioFormat.Encoding("Name");
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> g726FormatConversionProvider.getAudioInputStream(target_encoding,
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)));
  }

  @Test
  void testGetAudioInputStream2() throws UnsupportedEncodingException {
    // Arrange
    G726FormatConversionProvider g726FormatConversionProvider = new G726FormatConversionProvider();
    AudioFormat.Encoding target_encoding = new AudioFormat.Encoding("Name");
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> g726FormatConversionProvider.getAudioInputStream(target_encoding,
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, false, true), 3L)));
  }

  @Test
  void testGetAudioInputStream3() throws UnsupportedEncodingException {
    // Arrange
    G726FormatConversionProvider g726FormatConversionProvider = new G726FormatConversionProvider();
    AudioFormat target_format = new AudioFormat(10.0f, 3, 1, true, true);

    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> g726FormatConversionProvider.getAudioInputStream(target_format,
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)));
  }

  @Test
  void testGetAudioInputStream4() throws UnsupportedEncodingException {
    // Arrange
    G726FormatConversionProvider g726FormatConversionProvider = new G726FormatConversionProvider();
    AudioFormat target_format = new AudioFormat(10.0f, 3, 1, true, true);

    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> g726FormatConversionProvider.getAudioInputStream(target_format,
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, false, true), 3L)));
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    G726FormatConversionProvider actualG726FormatConversionProvider = new G726FormatConversionProvider();

    // Assert
    AudioFormat.Encoding[] expectedSourceEncodings = actualG726FormatConversionProvider.BOTH_ENCODINGS;
    AudioFormat.Encoding[] sourceEncodings = actualG726FormatConversionProvider.getSourceEncodings();
    assertSame(expectedSourceEncodings, sourceEncodings);
    assertSame(sourceEncodings, actualG726FormatConversionProvider.getTargetEncodings());
  }
}

