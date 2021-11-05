package org.mjsip.sip.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.zoolu.net.SocketAddress;

class UdpKeepAliveDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    UdpKeepAlive actualUdpKeepAlive = new UdpKeepAlive(new SocketAddress("42 Main St"), 2L);
    actualUdpKeepAlive.setDeltaTime(2L);

    // Assert
    assertEquals(2L, actualUdpKeepAlive.getDeltaTime());
    SocketAddress expectedDestSoAddress = actualUdpKeepAlive.target;
    assertSame(expectedDestSoAddress, actualUdpKeepAlive.getDestSoAddress());
  }

  @Test
  void testIsRunning() {
    // Arrange, Act and Assert
    assertTrue((new UdpKeepAlive(new SocketAddress("42 Main St"), 2L)).isRunning());
  }

  @Test
  void testSetDestSoAddress() {
    // Arrange
    UdpKeepAlive udpKeepAlive = new UdpKeepAlive(new SocketAddress("42 Main St"), 2L);
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    udpKeepAlive.setDestSoAddress(socketAddress);

    // Assert
    assertSame(socketAddress, udpKeepAlive.getDestSoAddress());
  }

  @Test
  void testSetExpirationTime() {
    // Arrange
    UdpKeepAlive udpKeepAlive = new UdpKeepAlive(new SocketAddress("42 Main St"), 2L);

    // Act
    udpKeepAlive.setExpirationTime(0L);

    // Assert
    assertEquals(0L, udpKeepAlive.expire);
  }

  @Test
  void testSendToken() throws IOException {
    // Arrange
    UdpKeepAlive udpKeepAlive = new UdpKeepAlive(new SocketAddress("42 Main St"), 2L);

    // Act
    udpKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, udpKeepAlive.getDeltaTime());
    assertFalse(udpKeepAlive.stop);
    SocketAddress expectedDestSoAddress = udpKeepAlive.target;
    assertSame(expectedDestSoAddress, udpKeepAlive.getDestSoAddress());
    assertEquals(0L, udpKeepAlive.expire);
  }

  @Test
  void testSendToken2() throws IOException {
    // Arrange
    UdpKeepAlive udpKeepAlive = new UdpKeepAlive(null, 2L);

    // Act
    udpKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, udpKeepAlive.getDeltaTime());
    assertFalse(udpKeepAlive.stop);
    assertEquals(0L, udpKeepAlive.expire);
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("null (2ms)", (new UdpKeepAlive(new SocketAddress("42 Main St"), 2L)).toString());
  }
}

