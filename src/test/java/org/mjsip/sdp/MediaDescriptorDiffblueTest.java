package org.mjsip.sdp;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.field.ConnectionField;
import org.mjsip.sdp.field.MediaField;

class MediaDescriptorDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    MediaField media = new MediaField("Media field");

    // Act
    MediaDescriptor actualMediaDescriptor = new MediaDescriptor(media, new ConnectionField("Connection field"));

    // Assert
    ConnectionField expectedConnection = actualMediaDescriptor.c;
    assertSame(expectedConnection, actualMediaDescriptor.getConnection());
    MediaField expectedMedia = actualMediaDescriptor.m;
    assertSame(expectedMedia, actualMediaDescriptor.getMedia());
    assertTrue(actualMediaDescriptor.av.isEmpty());
  }
}

