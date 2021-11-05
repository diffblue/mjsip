package org.zoolu.sound.codec.amr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import org.junit.jupiter.api.Test;

class AmrFormatConversionProviderDiffblueTest {
  @Test
  void testGetTargetEncodings() {
    // Arrange
    AmrFormatConversionProvider amrFormatConversionProvider = new AmrFormatConversionProvider();

    // Act
    AudioFormat.Encoding[] actualTargetEncodings = amrFormatConversionProvider
        .getTargetEncodings(new AudioFormat(10.0f, 3, 1, true, true));

    // Assert
    assertSame(amrFormatConversionProvider.AMR_ENCODINGS, actualTargetEncodings);
    assertEquals(9, actualTargetEncodings.length);
  }

  @Test
  void testGetTargetEncodings2() {
    // Arrange
    AmrFormatConversionProvider amrFormatConversionProvider = new AmrFormatConversionProvider();

    // Act
    AudioFormat.Encoding[] actualTargetEncodings = amrFormatConversionProvider
        .getTargetEncodings(new AudioFormat(10.0f, 3, 1, false, true));

    // Assert
    assertSame(amrFormatConversionProvider.NO_ENCODINGS, actualTargetEncodings);
    assertEquals(0, actualTargetEncodings.length);
  }

  @Test
  void testGetTargetEncodings3() {
    // Arrange
    AmrFormatConversionProvider amrFormatConversionProvider = new AmrFormatConversionProvider();

    // Act
    AudioFormat.Encoding[] actualTargetEncodings = amrFormatConversionProvider
        .getTargetEncodings(new AudioFormat(0.5f, 3, 1, false, true));

    // Assert
    assertSame(amrFormatConversionProvider.NO_ENCODINGS, actualTargetEncodings);
    assertEquals(0, actualTargetEncodings.length);
  }

  @Test
  void testGetTargetFormats() {
    // Arrange
    AmrFormatConversionProvider amrFormatConversionProvider = new AmrFormatConversionProvider();
    AudioFormat.Encoding target_encoding = new AudioFormat.Encoding("Name");

    // Act and Assert
    assertEquals(0,
        amrFormatConversionProvider.getTargetFormats(target_encoding, new AudioFormat(10.0f, 3, 1, true, true)).length);
  }

  @Test
  void testGetTargetFormats2() {
    // Arrange
    AmrFormatConversionProvider amrFormatConversionProvider = new AmrFormatConversionProvider();
    AmrEncoding target_encoding = new AmrEncoding("Name", 1);

    // Act and Assert
    assertEquals(0,
        amrFormatConversionProvider.getTargetFormats(target_encoding, new AudioFormat(10.0f, 3, 1, true, true)).length);
  }

  @Test
  void testGetAudioInputStream() throws UnsupportedEncodingException {
    // Arrange
    AmrFormatConversionProvider amrFormatConversionProvider = new AmrFormatConversionProvider();
    AudioFormat.Encoding target_encoding = new AudioFormat.Encoding("Name");
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> amrFormatConversionProvider.getAudioInputStream(target_encoding,
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)));
  }

  @Test
  void testGetAudioInputStream2() throws UnsupportedEncodingException {
    // Arrange
    AmrFormatConversionProvider amrFormatConversionProvider = new AmrFormatConversionProvider();
    AudioFormat target_format = new AudioFormat(10.0f, 3, 1, true, true);

    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> amrFormatConversionProvider.getAudioInputStream(target_format,
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L)));
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    AmrFormatConversionProvider actualAmrFormatConversionProvider = new AmrFormatConversionProvider();

    // Assert
    AudioFormat.Encoding[] expectedSourceEncodings = actualAmrFormatConversionProvider.BOTH_ENCODINGS;
    AudioFormat.Encoding[] sourceEncodings = actualAmrFormatConversionProvider.getSourceEncodings();
    assertSame(expectedSourceEncodings, sourceEncodings);
    assertSame(sourceEncodings, actualAmrFormatConversionProvider.getTargetEncodings());
  }
}

