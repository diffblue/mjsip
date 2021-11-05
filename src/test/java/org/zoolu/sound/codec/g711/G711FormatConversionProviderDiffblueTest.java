package org.zoolu.sound.codec.g711;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import org.junit.jupiter.api.Test;

class G711FormatConversionProviderDiffblueTest {
  @Test
  void testGetTargetEncodings() {
    // Arrange
    G711FormatConversionProvider g711FormatConversionProvider = new G711FormatConversionProvider();

    // Act
    AudioFormat.Encoding[] actualTargetEncodings = g711FormatConversionProvider
        .getTargetEncodings(new AudioFormat(10.0f, 3, 1, true, true));

    // Assert
    assertSame(g711FormatConversionProvider.G711_ENCODING, actualTargetEncodings);
    assertEquals(2, actualTargetEncodings.length);
  }

  @Test
  void testGetTargetEncodings2() {
    // Arrange
    G711FormatConversionProvider g711FormatConversionProvider = new G711FormatConversionProvider();

    // Act
    AudioFormat.Encoding[] actualTargetEncodings = g711FormatConversionProvider
        .getTargetEncodings(new AudioFormat(10.0f, 3, 1, false, true));

    // Assert
    assertSame(g711FormatConversionProvider.NO_ENCODING, actualTargetEncodings);
    assertEquals(0, actualTargetEncodings.length);
  }

  @Test
  void testGetTargetEncodings3() {
    // Arrange
    G711FormatConversionProvider g711FormatConversionProvider = new G711FormatConversionProvider();

    // Act
    AudioFormat.Encoding[] actualTargetEncodings = g711FormatConversionProvider
        .getTargetEncodings(new AudioFormat(0.5f, 3, 1, false, true));

    // Assert
    assertSame(g711FormatConversionProvider.NO_ENCODING, actualTargetEncodings);
    assertEquals(0, actualTargetEncodings.length);
  }

  @Test
  void testGetTargetFormats() {
    // Arrange
    G711FormatConversionProvider g711FormatConversionProvider = new G711FormatConversionProvider();
    AudioFormat.Encoding target_encoding = new AudioFormat.Encoding("Name");

    // Act and Assert
    assertEquals(0, g711FormatConversionProvider.getTargetFormats(target_encoding,
        new AudioFormat(10.0f, 3, 1, true, true)).length);
  }

  @Test
  void testGetTargetFormats2() {
    // Arrange
    G711FormatConversionProvider g711FormatConversionProvider = new G711FormatConversionProvider();
    G711Encoding target_encoding = new G711Encoding("Name");

    // Act and Assert
    assertEquals(0, g711FormatConversionProvider.getTargetFormats(target_encoding,
        new AudioFormat(10.0f, 3, 1, true, true)).length);
  }

  @Test
  void testGetAudioInputStream() throws UnsupportedEncodingException {
    // Arrange
    G711FormatConversionProvider g711FormatConversionProvider = new G711FormatConversionProvider();
    AudioFormat.Encoding target_encoding = new AudioFormat.Encoding("Name");
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> g711FormatConversionProvider.getAudioInputStream(target_encoding,
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)));
  }

  @Test
  void testGetAudioInputStream2() throws UnsupportedEncodingException {
    // Arrange
    G711FormatConversionProvider g711FormatConversionProvider = new G711FormatConversionProvider();
    G711Encoding target_encoding = new G711Encoding("Name");
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> g711FormatConversionProvider.getAudioInputStream(target_encoding,
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)));
  }

  @Test
  void testGetAudioInputStream3() throws UnsupportedEncodingException {
    // Arrange
    G711FormatConversionProvider g711FormatConversionProvider = new G711FormatConversionProvider();
    AudioFormat target_format = new AudioFormat(10.0f, 3, 1, true, true);

    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> g711FormatConversionProvider.getAudioInputStream(target_format,
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)));
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    G711FormatConversionProvider actualG711FormatConversionProvider = new G711FormatConversionProvider();

    // Assert
    AudioFormat.Encoding[] expectedSourceEncodings = actualG711FormatConversionProvider.BOTH_ENCODINGS;
    AudioFormat.Encoding[] sourceEncodings = actualG711FormatConversionProvider.getSourceEncodings();
    assertSame(expectedSourceEncodings, sourceEncodings);
    assertSame(sourceEncodings, actualG711FormatConversionProvider.getTargetEncodings());
  }
}

