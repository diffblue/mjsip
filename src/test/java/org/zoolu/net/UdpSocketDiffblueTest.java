package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import org.junit.jupiter.api.Test;

class UdpSocketDiffblueTest {
  @Test
  void testClose() throws SocketException {
    // Arrange
    UdpSocket udpSocket = new UdpSocket(new DatagramSocket((SocketAddress) null));

    // Act
    udpSocket.close();

    // Assert that nothing has changed
    assertEquals(-1, udpSocket.getLocalPort());
    assertEquals(0L, udpSocket.getReceiverPacketCounter());
    assertEquals(0L, udpSocket.getSenderOctectCounter());
    assertEquals(0L, udpSocket.getReceiverOctectCounter());
    assertEquals(0L, udpSocket.getSenderPacketCounter());
  }
}

