package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import org.junit.jupiter.api.Test;

class TcpSocketDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    TcpSocket actualTcpSocket = new TcpSocket();

    // Assert
    assertFalse(actualTcpSocket.isConnected());
    assertNull(actualTcpSocket.socket);
  }

  @Test
  void testIsConnected() {
    // Arrange, Act and Assert
    assertFalse((new TcpSocket()).isConnected());
  }

  @Test
  void testClose() throws IOException {
    // Arrange
    TcpSocket tcpSocket = new TcpSocket(new Socket());

    // Act
    tcpSocket.close();

    // Assert that nothing has changed
    assertEquals(0, tcpSocket.getPort());
  }

  @Test
  void testGetAddress() {
    // Arrange and Act
    IpAddress actualAddress = (new TcpSocket(new Socket())).getAddress();

    // Assert
    assertSame(actualAddress.getInetAddress(), actualAddress.inet_address);
    assertEquals("127.0.0.1", actualAddress.toString());
  }

  @Test
  void testGetLocalAddress() {
    // Arrange and Act
    IpAddress actualLocalAddress = (new TcpSocket(new Socket())).getLocalAddress();

    // Assert
    InetAddress expectedInetAddress = actualLocalAddress.inet_address;
    assertSame(expectedInetAddress, actualLocalAddress.getInetAddress());
    assertEquals("0.0.0.0", actualLocalAddress.toString());
  }

  @Test
  void testGetLocalPort() {
    // Arrange, Act and Assert
    assertEquals(-1, (new TcpSocket(new Socket())).getLocalPort());
  }

  @Test
  void testGetPort() {
    // Arrange, Act and Assert
    assertEquals(0, (new TcpSocket(new Socket())).getPort());
  }

  @Test
  void testGetSoTimeout() throws SocketException {
    // Arrange, Act and Assert
    assertEquals(0, (new TcpSocket(new Socket())).getSoTimeout());
  }

  @Test
  void testSetSoTimeout() throws SocketException {
    // Arrange
    TcpSocket tcpSocket = new TcpSocket(new Socket());

    // Act
    tcpSocket.setSoTimeout(10);

    // Assert that nothing has changed
    assertEquals(0, tcpSocket.getPort());
  }
}

