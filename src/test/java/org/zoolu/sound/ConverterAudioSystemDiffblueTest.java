package org.zoolu.sound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.sound.sampled.AudioFormat;
import org.junit.jupiter.api.Test;

class ConverterAudioSystemDiffblueTest {
  @Test
  void testConvertAudioOutputStream() throws Exception {
    // Arrange
    CodecType codec = new CodecType("G711_ULAW", 1, 3, 1);

    // Act
    AudioOutputStream actualConvertAudioOutputStreamResult = ConverterAudioSystem.convertAudioOutputStream(codec, 10.0f,
        new AudioOutputStream(SimpleAudioSystem.getBaseAudioFormat(10.0f, 1)));

    // Assert
    AudioFormat expectedFormat = actualConvertAudioOutputStreamResult.format;
    assertSame(expectedFormat, actualConvertAudioOutputStreamResult.getFormat());
    assertNull(((ConvertedAudioOutputStream) actualConvertAudioOutputStreamResult).os);
    assertEquals(SimpleAudioSystem.INTERNAL_BUFFER_SIZE,
        ((ConvertedAudioOutputStream) actualConvertAudioOutputStreamResult).buff.length);
    assertTrue(
        ((ConvertedAudioOutputStream) actualConvertAudioOutputStreamResult).ingress_output_stream instanceof org.zoolu.util.PipeOutputStream);
    assertEquals(0,
        ((ConvertedAudioOutputStream) actualConvertAudioOutputStreamResult).converted_input_stream.available());
    assertNull(((ConvertedAudioOutputStream) actualConvertAudioOutputStreamResult).final_output_stream.os);
  }

  @Test
  void testConvertAudioOutputStream2() throws Exception {
    // Arrange
    CodecType codec = new CodecType("G711_ULAW", 1, 3, 1);

    // Act
    AudioOutputStream actualConvertAudioOutputStreamResult = ConverterAudioSystem.convertAudioOutputStream(codec, 0.5f,
        new AudioOutputStream(SimpleAudioSystem.getBaseAudioFormat(0.5f, 1)));

    // Assert
    AudioFormat expectedFormat = actualConvertAudioOutputStreamResult.format;
    assertSame(expectedFormat, actualConvertAudioOutputStreamResult.getFormat());
    assertNull(((ConvertedAudioOutputStream) actualConvertAudioOutputStreamResult).os);
    assertEquals(SimpleAudioSystem.INTERNAL_BUFFER_SIZE,
        ((ConvertedAudioOutputStream) actualConvertAudioOutputStreamResult).buff.length);
    assertTrue(
        ((ConvertedAudioOutputStream) actualConvertAudioOutputStreamResult).ingress_output_stream instanceof org.zoolu.util.PipeOutputStream);
    assertEquals(0,
        ((ConvertedAudioOutputStream) actualConvertAudioOutputStreamResult).converted_input_stream.available());
    assertNull(((ConvertedAudioOutputStream) actualConvertAudioOutputStreamResult).final_output_stream.os);
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    new ConverterAudioSystem();

    // Assert
    assertFalse(SimpleAudioSystem.DEBUG);
  }
}

