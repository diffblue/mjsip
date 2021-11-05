package org.mjsip.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.zoolu.net.IpAddress;
import org.zoolu.net.SocketAddress;

class UdpToolDiffblueTest {
  @Test
  void testConstructor2() {
    // Arrange and Act
    UdpTool actualUdpTool = new UdpTool(8080, new StringWriter());

    // Assert
    assertFalse(actualUdpTool.ascii_mode);
    assertNull(actualUdpTool.udp);
    assertEquals(1000L, actualUdpTool.inter_time);
  }

  @Test
  void testSendTrace() {
    // Arrange
    UdpTool udpTool = new UdpTool(8080, new StringWriter());
    BufferedReader input = new BufferedReader(new StringReader("foo"));
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    udpTool.sendTrace(input, socketAddress);

    // Assert that nothing has changed
    assertEquals("42 Main St:-1", socketAddress.toString());
    assertEquals(-1, socketAddress.getPort());
    assertFalse(udpTool.ascii_mode);
    assertEquals(1000L, udpTool.inter_time);
  }

  @Test
  void testSendTrace2() {
    // Arrange
    UdpTool udpTool = new UdpTool(8080, new StringWriter());
    BufferedReader input = new BufferedReader(new StringReader("foo"));
    SocketAddress socketAddress = new SocketAddress(new IpAddress("42 Main St"), 8080);

    // Act
    udpTool.sendTrace(input, socketAddress);

    // Assert that nothing has changed
    assertEquals("42 Main St:8080", socketAddress.toString());
    assertEquals(8080, socketAddress.getPort());
    assertFalse(udpTool.ascii_mode);
    assertEquals(1000L, udpTool.inter_time);
  }

  @Test
  void testSendTrace3() {
    // Arrange
    UdpTool udpTool = new UdpTool(8080, new StringWriter());
    BufferedReader input = new BufferedReader(new StringReader("foo"));
    SocketAddress socketAddress = new SocketAddress("42 Main St", 8080);

    // Act
    udpTool.sendTrace(input, socketAddress);

    // Assert that nothing has changed
    assertEquals("42 Main St:8080", socketAddress.toString());
    assertEquals(8080, socketAddress.getPort());
    assertFalse(udpTool.ascii_mode);
    assertEquals(1000L, udpTool.inter_time);
  }

  @Test
  void testHalt() {
    // Arrange
    UdpTool udpTool = new UdpTool(8080, new StringWriter());

    // Act
    udpTool.halt();

    // Assert that nothing has changed
    assertFalse(udpTool.ascii_mode);
    assertEquals(1000L, udpTool.inter_time);
  }

  @Test
  void testHalt2() {
    // Arrange
    UdpTool udpTool = new UdpTool(0, new StringWriter());

    // Act
    udpTool.halt();

    // Assert that nothing has changed
    assertFalse(udpTool.ascii_mode);
    assertEquals(1000L, udpTool.inter_time);
  }

  @Test
  void testHalt3() {
    // Arrange
    UdpTool udpTool = new UdpTool(8080, new StringWriter());
    udpTool.setAsciiMode(true);

    // Act
    udpTool.halt();

    // Assert that nothing has changed
    assertTrue(udpTool.ascii_mode);
    assertEquals(1000L, udpTool.inter_time);
  }

  @Test
  void testHalt4() {
    // Arrange
    UdpTool udpTool = new UdpTool(8080, new StringWriter());
    udpTool.setInterTime(10L);

    // Act
    udpTool.halt();

    // Assert that nothing has changed
    assertFalse(udpTool.ascii_mode);
    assertEquals(10L, udpTool.inter_time);
  }
}

