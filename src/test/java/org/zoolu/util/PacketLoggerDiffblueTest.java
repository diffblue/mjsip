package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.jupiter.api.Test;

class PacketLoggerDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(3);

    // Act
    new PacketLogger(byteArrayOutputStream);

    // Assert
    assertNull(PacketLogger.DEFAULT_LOGGER);
    assertEquals(0, byteArrayOutputStream.size());
    assertEquals("", byteArrayOutputStream.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange
    StringWriter stringWriter = new StringWriter();

    // Act and Assert
    assertSame((new PacketLogger(stringWriter)).out, stringWriter);
  }

  @Test
  void testClose() throws IOException {
    // Arrange
    PacketLogger packetLogger = new PacketLogger(new StringWriter());

    // Act
    packetLogger.close();

    // Assert
    assertNull(packetLogger.out);
  }

  @Test
  void testClose2() throws IOException {
    // Arrange
    PacketLogger packetLogger = new PacketLogger((Writer) null);

    // Act
    packetLogger.close();

    // Assert
    assertNull(packetLogger.out);
  }
}

