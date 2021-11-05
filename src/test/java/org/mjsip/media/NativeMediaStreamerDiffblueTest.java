package org.mjsip.media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.jupiter.api.Test;
import org.zoolu.util.LogWriter;

class NativeMediaStreamerDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    NativeMediaStreamer actualNativeMediaStreamer = new NativeMediaStreamer("Command", new String[]{"Args"}, 8080, 8080,
        new LogWriter(new StringWriter()));

    // Assert
    assertEquals(1, actualNativeMediaStreamer.args.length);
    assertEquals(8080, actualNativeMediaStreamer.remote_port);
    assertNull(actualNativeMediaStreamer.media_process);
    assertTrue(actualNativeMediaStreamer.logger instanceof LogWriter);
    assertEquals(8080, actualNativeMediaStreamer.local_port);
    assertEquals("Command", actualNativeMediaStreamer.command);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    NativeMediaStreamer actualNativeMediaStreamer = new NativeMediaStreamer("Command", new String[]{"Args"},
        new LogWriter(new StringWriter()));

    // Assert
    assertEquals(1, actualNativeMediaStreamer.args.length);
    assertEquals(0, actualNativeMediaStreamer.remote_port);
    assertNull(actualNativeMediaStreamer.media_process);
    assertTrue(actualNativeMediaStreamer.logger instanceof LogWriter);
    assertEquals(0, actualNativeMediaStreamer.local_port);
    assertEquals("Command", actualNativeMediaStreamer.command);
  }

  @Test
  void testStart() {
    // Arrange, Act and Assert
    assertFalse(
        (new NativeMediaStreamer("Command", new String[]{"foo", "foo", "foo"}, new LogWriter(new StringWriter())))
            .start());
    assertFalse((new NativeMediaStreamer("Command", null, new LogWriter(new StringWriter()))).start());
    assertFalse((new NativeMediaStreamer("Command", null, null)).start());
  }

  @Test
  void testStart2() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null);
    logWriter.setLoggingLevel(null);

    // Act and Assert
    assertFalse((new NativeMediaStreamer("Command", new String[]{"foo", "foo", "foo"}, logWriter)).start());
  }

  @Test
  void testHalt() {
    // Arrange, Act and Assert
    assertTrue(
        (new NativeMediaStreamer("Command", new String[]{"foo", "foo", "foo"}, new LogWriter(new StringWriter())))
            .halt());
    assertTrue((new NativeMediaStreamer("Command", new String[]{"foo", "foo", "foo"}, null)).halt());
  }

  @Test
  void testHalt2() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null);
    logWriter.setLoggingLevel(null);

    // Act and Assert
    assertTrue((new NativeMediaStreamer("Command", new String[]{"foo", "foo", "foo"}, logWriter)).halt());
  }
}

