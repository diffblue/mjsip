package org.mjsip.media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.mjsip.net.UdpRelay;
import org.zoolu.util.LogLevel;
import org.zoolu.util.LogWriter;

class LoopbackMediaStreamerDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    FlowSpec flow_spec = new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction());

    // Act
    LoopbackMediaStreamer actualLoopbackMediaStreamer = new LoopbackMediaStreamer(flow_spec,
        new LogWriter(new StringWriter()));

    // Assert
    assertTrue(actualLoopbackMediaStreamer.logger instanceof LogWriter);
    UdpRelay udpRelay = actualLoopbackMediaStreamer.udp_relay;
    assertEquals("localhost:8080-->42 Main St:8080", udpRelay.toString());
    assertTrue(udpRelay.isRunning());
    assertEquals(8080, udpRelay.getLocalPort());
    assertEquals(3000, udpRelay.getSoTimeout());
  }

  @Test
  void testConstructor2() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    FlowSpec flow_spec = new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction());

    ByteArrayOutputStream out = new ByteArrayOutputStream(3);

    // Act
    LoopbackMediaStreamer actualLoopbackMediaStreamer = new LoopbackMediaStreamer(flow_spec,
        new LogWriter(out, new LogLevel("Name", 42)));

    // Assert
    assertTrue(actualLoopbackMediaStreamer.logger instanceof LogWriter);
    UdpRelay udpRelay = actualLoopbackMediaStreamer.udp_relay;
    assertEquals("localhost:8080-->42 Main St:8080", udpRelay.toString());
    assertTrue(udpRelay.isRunning());
    assertEquals(8080, udpRelay.getLocalPort());
    assertEquals(3000, udpRelay.getSoTimeout());
  }

  @Test
  void testConstructor3() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 1);

    FlowSpec flow_spec = new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction());

    // Act
    LoopbackMediaStreamer actualLoopbackMediaStreamer = new LoopbackMediaStreamer(flow_spec,
        new LogWriter(new StringWriter()));

    // Assert
    assertTrue(actualLoopbackMediaStreamer.logger instanceof LogWriter);
    UdpRelay udpRelay = actualLoopbackMediaStreamer.udp_relay;
    assertEquals("localhost:8080-->42 Main St:8080", udpRelay.toString());
    assertTrue(udpRelay.isRunning());
    assertEquals(8080, udpRelay.getLocalPort());
    assertEquals(3000, udpRelay.getSoTimeout());
  }

  @Test
  void testConstructor4() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    FlowSpec flow_spec = new FlowSpec(media_spec, 8080, null, 8080, new FlowSpec.Direction());

    // Act
    LoopbackMediaStreamer actualLoopbackMediaStreamer = new LoopbackMediaStreamer(flow_spec,
        new LogWriter(new StringWriter()));

    // Assert
    assertTrue(actualLoopbackMediaStreamer.logger instanceof LogWriter);
    UdpRelay udpRelay = actualLoopbackMediaStreamer.udp_relay;
    assertEquals("localhost:8080-->null:8080", udpRelay.toString());
    assertTrue(udpRelay.isRunning());
    assertEquals(8080, udpRelay.getLocalPort());
    assertEquals(3000, udpRelay.getSoTimeout());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    LoopbackMediaStreamer actualLoopbackMediaStreamer = new LoopbackMediaStreamer(null,
        new LogWriter(new StringWriter()));

    // Assert
    assertTrue(actualLoopbackMediaStreamer.logger instanceof LogWriter);
    assertNull(actualLoopbackMediaStreamer.udp_relay);
  }

  @Test
  void testConstructor6() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    // Act
    LoopbackMediaStreamer actualLoopbackMediaStreamer = new LoopbackMediaStreamer(
        new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction()), null);

    // Assert
    assertNull(actualLoopbackMediaStreamer.logger);
    UdpRelay udpRelay = actualLoopbackMediaStreamer.udp_relay;
    assertEquals("localhost:8080-->42 Main St:8080", udpRelay.toString());
    assertTrue(udpRelay.isRunning());
    assertEquals(8080, udpRelay.getLocalPort());
    assertEquals(3000, udpRelay.getSoTimeout());
  }

  @Test
  void testStart() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    FlowSpec flow_spec = new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction());

    // Act and Assert
    assertTrue((new LoopbackMediaStreamer(flow_spec, new LogWriter(new StringWriter()))).start());
  }

  @Test
  void testStart2() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("42", 1, "Codec", 1, 1, 3);

    FlowSpec flow_spec = new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction());

    // Act and Assert
    assertTrue((new LoopbackMediaStreamer(flow_spec, new LogWriter(new StringWriter()))).start());
  }

  @Test
  void testStart3() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 0);

    FlowSpec flow_spec = new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction());

    // Act and Assert
    assertTrue((new LoopbackMediaStreamer(flow_spec, new LogWriter(new StringWriter()))).start());
  }

  @Test
  void testStart4() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    FlowSpec flow_spec = new FlowSpec(media_spec, -1, "42 Main St", 8080, new FlowSpec.Direction());

    // Act and Assert
    assertTrue((new LoopbackMediaStreamer(flow_spec, new LogWriter(new StringWriter()))).start());
  }

  @Test
  void testStart5() {
    // Arrange, Act and Assert
    assertTrue((new LoopbackMediaStreamer(null, new LogWriter(new StringWriter()))).start());
  }

  @Test
  void testStart6() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("42", 1, "Codec", 1, 1, 3);

    FlowSpec flow_spec = new FlowSpec(media_spec, -1, "42 Main St", 8080, new FlowSpec.Direction());

    // Act and Assert
    assertTrue((new LoopbackMediaStreamer(flow_spec, new LogWriter(new StringWriter()))).start());
  }

  @Test
  void testHalt() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    FlowSpec flow_spec = new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction());

    LoopbackMediaStreamer loopbackMediaStreamer = new LoopbackMediaStreamer(flow_spec,
        new LogWriter(new StringWriter()));

    // Act and Assert
    assertTrue(loopbackMediaStreamer.halt());
    assertNull(loopbackMediaStreamer.udp_relay);
  }

  @Test
  void testHalt2() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("relay halted", 1, "Codec", 1, 1, 3);

    FlowSpec flow_spec = new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction());

    LoopbackMediaStreamer loopbackMediaStreamer = new LoopbackMediaStreamer(flow_spec,
        new LogWriter(new StringWriter()));

    // Act and Assert
    assertTrue(loopbackMediaStreamer.halt());
    assertNull(loopbackMediaStreamer.udp_relay);
  }

  @Test
  void testHalt3() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    FlowSpec flow_spec = new FlowSpec(media_spec, 60, "42 Main St", 8080, new FlowSpec.Direction());

    LoopbackMediaStreamer loopbackMediaStreamer = new LoopbackMediaStreamer(flow_spec,
        new LogWriter(new StringWriter()));

    // Act and Assert
    assertTrue(loopbackMediaStreamer.halt());
    assertNull(loopbackMediaStreamer.udp_relay);
  }

  @Test
  void testHalt4() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    FlowSpec flow_spec = new FlowSpec(media_spec, 8080, null, 8080, new FlowSpec.Direction());

    LoopbackMediaStreamer loopbackMediaStreamer = new LoopbackMediaStreamer(flow_spec,
        new LogWriter(new StringWriter()));

    // Act and Assert
    assertTrue(loopbackMediaStreamer.halt());
    assertNull(loopbackMediaStreamer.udp_relay);
  }

  @Test
  void testHalt5() {
    // Arrange, Act and Assert
    assertTrue((new LoopbackMediaStreamer(null, new LogWriter(new StringWriter()))).halt());
  }

  @Test
  void testHalt6() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("relay halted", 1, "Codec", 1, 1, 3);

    LoopbackMediaStreamer loopbackMediaStreamer = new LoopbackMediaStreamer(
        new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction()), null);

    // Act and Assert
    assertTrue(loopbackMediaStreamer.halt());
    assertNull(loopbackMediaStreamer.udp_relay);
  }

  @Test
  void testOnUdpRelaySourceChanged() {
    // Arrange
    MediaSpec media_spec = new MediaSpec(null, 1, "Codec", 1, 1, 3);

    LoopbackMediaStreamer loopbackMediaStreamer = new LoopbackMediaStreamer(
        new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction()), null);
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, null);

    // Act
    loopbackMediaStreamer.onUdpRelaySourceChanged(udpRelay, "42 Main St", 8080);

    // Assert that nothing has changed
    assertEquals("localhost:8080-->42 Main St:8080", udpRelay.toString());
    assertTrue(udpRelay.isRunning());
    assertEquals(8080, udpRelay.getLocalPort());
    assertEquals(3000, udpRelay.getSoTimeout());
  }

  @Test
  void testOnUdpRelayTerminated() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("UDP relay: terminated.", 1, "Codec", 1, 1, 3);

    LoopbackMediaStreamer loopbackMediaStreamer = new LoopbackMediaStreamer(
        new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction()), null);
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, null);

    // Act
    loopbackMediaStreamer.onUdpRelayTerminated(udpRelay);

    // Assert that nothing has changed
    assertEquals("localhost:8080-->42 Main St:8080", udpRelay.toString());
    assertTrue(udpRelay.isRunning());
    assertEquals(8080, udpRelay.getLocalPort());
    assertEquals(3000, udpRelay.getSoTimeout());
  }
}

