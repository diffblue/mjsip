package org.zoolu.sound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import org.junit.jupiter.api.Test;

class SimpleAudioSystemDiffblueTest {
  @Test
  void testGetAudioFormat() {
    // Arrange
    CodecType codecType = mock(CodecType.class);
    when(codecType.getFrameSize()).thenReturn(3);
    when(codecType.getName()).thenReturn("Name");
    when(codecType.getSamplesPerFrame()).thenReturn(1);

    // Act
    SimpleAudioSystem.getAudioFormat(codecType, 10.0f);

    // Assert
    verify(codecType).getFrameSize();
    verify(codecType, atLeast(1)).getName();
    verify(codecType).getSamplesPerFrame();
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    new SimpleAudioSystem();

    // Assert
    assertFalse(SimpleAudioSystem.DEBUG);
  }
}

