package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Paths;
import javax.management.loading.MLet;
import org.junit.jupiter.api.Test;

class TcpConnectionDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    TcpConnection actualTcpConnection = new TcpConnection(new TcpSocket(), 10L, mock(TcpConnectionListener.class));

    // Assert
    assertNull(actualTcpConnection.ostream);
    assertEquals(TcpConnection.DEFAULT_SOCKET_TIMEOUT, actualTcpConnection.socket_timeout);
    assertNull(actualTcpConnection.istream);
    assertEquals(10L, actualTcpConnection.alive_time);
    assertNull(actualTcpConnection.getSocket().socket);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    TcpConnection actualTcpConnection = new TcpConnection(null, 10L, mock(TcpConnectionListener.class));

    // Assert
    assertNull(actualTcpConnection.ostream);
    assertEquals(TcpConnection.DEFAULT_SOCKET_TIMEOUT, actualTcpConnection.socket_timeout);
    assertNull(actualTcpConnection.getSocket());
    assertNull(actualTcpConnection.istream);
    assertEquals(10L, actualTcpConnection.alive_time);
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    TcpConnection actualTcpConnection = new TcpConnection(new TcpSocket(), mock(TcpConnectionListener.class));

    // Assert
    assertNull(actualTcpConnection.ostream);
    assertEquals(TcpConnection.DEFAULT_SOCKET_TIMEOUT, actualTcpConnection.socket_timeout);
    assertNull(actualTcpConnection.istream);
    assertEquals(0L, actualTcpConnection.alive_time);
    assertNull(actualTcpConnection.getSocket().socket);
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    TcpConnection actualTcpConnection = new TcpConnection(null, mock(TcpConnectionListener.class));

    // Assert
    assertNull(actualTcpConnection.ostream);
    assertEquals(TcpConnection.DEFAULT_SOCKET_TIMEOUT, actualTcpConnection.socket_timeout);
    assertNull(actualTcpConnection.getSocket());
    assertNull(actualTcpConnection.istream);
    assertEquals(0L, actualTcpConnection.alive_time);
  }

  @Test
  void testGetRemoteAddress() {
    // Arrange and Act
    IpAddress actualRemoteAddress = (new TcpConnection(new TcpSocket(new Socket()), null)).getRemoteAddress();

    // Assert
    assertSame(actualRemoteAddress.getInetAddress(), actualRemoteAddress.inet_address);
    assertEquals("127.0.0.1", actualRemoteAddress.toString());
  }

  @Test
  void testGetRemotePort() {
    // Arrange, Act and Assert
    assertEquals(0, (new TcpConnection(new TcpSocket(new Socket()), null)).getRemotePort());
  }

  @Test
  void testGetLocalAddress() {
    // Arrange and Act
    IpAddress actualLocalAddress = (new TcpConnection(new TcpSocket(new Socket()), null)).getLocalAddress();

    // Assert
    InetAddress expectedInetAddress = actualLocalAddress.inet_address;
    assertSame(expectedInetAddress, actualLocalAddress.getInetAddress());
    assertEquals("0.0.0.0", actualLocalAddress.toString());
  }

  @Test
  void testGetLocalPort() {
    // Arrange, Act and Assert
    assertEquals(-1, (new TcpConnection(new TcpSocket(new Socket()), null)).getLocalPort());
  }

  @Test
  void testSend() throws IOException {
    // Arrange
    TcpConnection tcpConnection = new TcpConnection(new TcpSocket(), null);

    // Act
    tcpConnection.send("AAAAAAAA".getBytes("UTF-8"));

    // Assert that nothing has changed
    assertEquals(TcpConnection.DEFAULT_SOCKET_TIMEOUT, tcpConnection.socket_timeout);
    assertEquals(0L, tcpConnection.alive_time);
  }

  @Test
  void testSend2() throws IOException {
    // Arrange
    TcpConnection tcpConnection = new TcpConnection(new TcpSocket(new Socket()), null);

    // Act
    tcpConnection.send("AAAAAAAA".getBytes("UTF-8"));

    // Assert that nothing has changed
    assertEquals(-1, tcpConnection.getLocalPort());
    assertEquals(TcpConnection.DEFAULT_SOCKET_TIMEOUT, tcpConnection.socket_timeout);
    assertEquals(0L, tcpConnection.alive_time);
  }

  @Test
  void testSend3() throws IOException {
    // Arrange
    TcpConnection tcpConnection = new TcpConnection(new TcpSocket(), null);

    // Act
    tcpConnection.send("AAﾀAAAAA".getBytes("UTF-8"));

    // Assert that nothing has changed
    assertEquals(TcpConnection.DEFAULT_SOCKET_TIMEOUT, tcpConnection.socket_timeout);
    assertEquals(0L, tcpConnection.alive_time);
  }

  @Test
  void testRun() {
    // Arrange
    TcpConnection tcpConnection = new TcpConnection(new TcpSocket(), null);

    // Act
    tcpConnection.run();

    // Assert
    assertTrue(tcpConnection.stop);
    assertNull(tcpConnection.listener);
    assertFalse(tcpConnection.isRunning());
  }
}

