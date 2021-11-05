package org.mjsip.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.zoolu.net.SocketAddress;
import org.zoolu.net.UdpProvider;
import org.zoolu.net.UdpSocket;

class UdpConnectionDiffblueTest {
  @Test
  void testConstructor2() {
    // Arrange
    UdpSocket socket = mock(UdpSocket.class);

    // Act
    UdpConnection actualUdpConnection = new UdpConnection(socket, new SocketAddress("42 Main St"), null);

    // Assert
    assertNull(actualUdpConnection.listener);
    assertEquals("udp:null:0<->42 Main St:-1", actualUdpConnection.toString());
    UdpProvider udpProvider = actualUdpConnection.udp_provider;
    assertEquals(2000, udpProvider.getSoTimeout());
    assertEquals(0, udpProvider.getMinimumReceivedDataLength());
    assertEquals(0L, udpProvider.getAliveTime());
    assertEquals("udp:null:0", udpProvider.toString());
    assertTrue(udpProvider.isRunning());
  }

  @Test
  void testGetUdpSocket() {
    // Arrange, Act and Assert
    assertNull((new UdpConnection(null, new SocketAddress("42 Main St"), null)).getUdpSocket());
    assertNull((new UdpConnection(null, new SocketAddress("Soaddr"), null)).getUdpSocket());
    assertNull(
        (new UdpConnection(null, new SocketAddress("42 Main St"), mock(UdpConnectionListener.class))).getUdpSocket());
  }

  @Test
  void testGetUdpSocket2() {
    // Arrange
    UdpConnection udpConnection = new UdpConnection(null, new SocketAddress("42 Main St"), null);
    udpConnection.setRemoteAddress(new SocketAddress("42 Main St"));

    // Act and Assert
    assertNull(udpConnection.getUdpSocket());
  }

  @Test
  void testGetUdpSocket3() {
    // Arrange
    UdpConnection udpConnection = new UdpConnection(null, new SocketAddress("42 Main St", 8080), null);
    udpConnection.setRemoteAddress(new SocketAddress("42 Main St"));

    // Act and Assert
    assertNull(udpConnection.getUdpSocket());
  }

  @Test
  void testGetUdpSocket4() {
    // Arrange
    UdpConnection udpConnection = new UdpConnection(null, new SocketAddress("42 Main St", 0), null);
    udpConnection.setRemoteAddress(new SocketAddress("42 Main St"));

    // Act and Assert
    assertNull(udpConnection.getUdpSocket());
  }

  @Test
  void testOnServiceTerminated() {
    // Arrange
    UdpConnection udpConnection = new UdpConnection(null, new SocketAddress("42 Main St"), null);

    // Act
    udpConnection.onServiceTerminated(null, new Exception("An error occurred"));

    // Assert that nothing has changed
    SocketAddress expectedRemoteAddress = udpConnection.remote_soaddr;
    assertSame(expectedRemoteAddress, udpConnection.getRemoteAddress());
  }
}

