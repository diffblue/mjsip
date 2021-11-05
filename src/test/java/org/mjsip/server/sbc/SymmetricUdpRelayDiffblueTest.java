package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.zoolu.net.IpAddress;
import org.zoolu.net.SocketAddress;
import org.zoolu.net.UdpProvider;
import org.zoolu.net.UdpSocket;
import org.zoolu.util.LogLevel;
import org.zoolu.util.Timer;
import org.zoolu.util.TimerListener;

class SymmetricUdpRelayDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    SymmetricUdpRelay actualSymmetricUdpRelay = new SymmetricUdpRelay();

    // Assert
    assertEquals(0L, actualSymmetricUdpRelay.getLastLeftChangeTime());
    assertEquals(0L, actualSymmetricUdpRelay.getLastRightChangeTime());
    assertNull(actualSymmetricUdpRelay.getLeftSoAddress());
    assertNull(actualSymmetricUdpRelay.getRightSoAddress());
  }

  @Test
  void testIsRunning() {
    // Arrange, Act and Assert
    assertFalse((new SymmetricUdpRelay()).isRunning());
  }

  @Test
  void testHalt() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();

    // Act
    symmetricUdpRelay.halt();

    // Assert that nothing has changed
    assertEquals(0L, symmetricUdpRelay.getLastLeftChangeTime());
    assertEquals(0L, symmetricUdpRelay.relay_time);
    assertEquals(0L, symmetricUdpRelay.expire_time);
    assertEquals(0L, symmetricUdpRelay.getLastRightChangeTime());
  }

  @Test
  void testSetLeftSoAddress() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    symmetricUdpRelay.setLeftSoAddress(socketAddress);

    // Assert
    assertSame(socketAddress, symmetricUdpRelay.getLeftSoAddress());
  }

  @Test
  void testSetLeftSoAddress2() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();

    // Act
    symmetricUdpRelay.setLeftSoAddress(null);

    // Assert
    assertNull(symmetricUdpRelay.getLeftSoAddress());
  }

  @Test
  void testSetLeftSoAddress3() throws UnknownHostException {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    SocketAddress socketAddress = new SocketAddress(IpAddress.getByName("42"), 8080);

    // Act
    symmetricUdpRelay.setLeftSoAddress(socketAddress);

    // Assert
    assertEquals("0.0.0.42", socketAddress.getAddress().toString());
    assertSame(socketAddress, symmetricUdpRelay.getLeftSoAddress());
  }

  @Test
  void testSetLeftSoAddress4() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    SocketAddress socketAddress = new SocketAddress((IpAddress) null, 8080);

    // Act
    symmetricUdpRelay.setLeftSoAddress(socketAddress);

    // Assert
    assertSame(socketAddress, symmetricUdpRelay.getLeftSoAddress());
  }

  @Test
  void testSetLeftSoAddress5() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    SocketAddress socketAddress = new SocketAddress(new IpAddress((InetAddress) null), 8080);

    // Act
    symmetricUdpRelay.setLeftSoAddress(socketAddress);

    // Assert
    assertSame(socketAddress, symmetricUdpRelay.getLeftSoAddress());
  }

  @Test
  void testSetRightSoAddress() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    symmetricUdpRelay.setRightSoAddress(socketAddress);

    // Assert
    assertSame(socketAddress, symmetricUdpRelay.getRightSoAddress());
  }

  @Test
  void testSetRightSoAddress2() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();

    // Act
    symmetricUdpRelay.setRightSoAddress(null);

    // Assert
    assertNull(symmetricUdpRelay.getRightSoAddress());
  }

  @Test
  void testSetRightSoAddress3() throws UnknownHostException {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    SocketAddress socketAddress = new SocketAddress(IpAddress.getByName("42"), 8080);

    // Act
    symmetricUdpRelay.setRightSoAddress(socketAddress);

    // Assert
    assertEquals("0.0.0.42", socketAddress.getAddress().toString());
    assertSame(socketAddress, symmetricUdpRelay.getRightSoAddress());
  }

  @Test
  void testSetRightSoAddress4() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    SocketAddress socketAddress = new SocketAddress((IpAddress) null, 8080);

    // Act
    symmetricUdpRelay.setRightSoAddress(socketAddress);

    // Assert
    assertSame(socketAddress, symmetricUdpRelay.getRightSoAddress());
  }

  @Test
  void testSetRightSoAddress5() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    SocketAddress socketAddress = new SocketAddress(new IpAddress((InetAddress) null), 8080);

    // Act
    symmetricUdpRelay.setRightSoAddress(socketAddress);

    // Assert
    assertSame(socketAddress, symmetricUdpRelay.getRightSoAddress());
  }

  @Test
  void testOnServiceTerminated() throws UnknownHostException {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    UdpSocket udpSocket = mock(UdpSocket.class);
    doNothing().when(udpSocket).close();
    when(udpSocket.getLocalPort()).thenReturn(8080);
    when(udpSocket.getLocalAddress()).thenReturn(IpAddress.getByName("42"));
    UdpProvider udpProvider = new UdpProvider(udpSocket, new SymmetricUdpRelay());

    // Act
    symmetricUdpRelay.onServiceTerminated(udpProvider, new Exception("An error occurred"));

    // Assert
    verify(udpSocket).close();
    verify(udpSocket, atLeast(1)).getLocalAddress();
    verify(udpSocket, atLeast(1)).getLocalPort();
    assertEquals("udp:0.0.0.42:8080", udpProvider.toString());
  }

  @Test
  void testOnServiceTerminated2() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    UdpSocket udpSocket = mock(UdpSocket.class);
    doNothing().when(udpSocket).close();
    when(udpSocket.getLocalPort()).thenReturn(8080);
    when(udpSocket.getLocalAddress()).thenReturn(null);
    UdpProvider udpProvider = new UdpProvider(udpSocket, new SymmetricUdpRelay());

    // Act
    symmetricUdpRelay.onServiceTerminated(udpProvider, new Exception("An error occurred"));

    // Assert
    verify(udpSocket).close();
    verify(udpSocket, atLeast(1)).getLocalAddress();
    verify(udpSocket, atLeast(1)).getLocalPort();
    assertEquals("udp:null:8080", udpProvider.toString());
  }

  @Test
  void testOnServiceTerminated3() throws UnknownHostException {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    UdpSocket udpSocket = mock(UdpSocket.class);
    doNothing().when(udpSocket).close();
    when(udpSocket.getLocalPort()).thenReturn(8080);
    when(udpSocket.getLocalAddress()).thenReturn(IpAddress.getByName("42"));
    UdpProvider udpProvider = new UdpProvider(udpSocket, new SymmetricUdpRelay());

    // Act
    symmetricUdpRelay.onServiceTerminated(udpProvider, null);

    // Assert
    verify(udpSocket).close();
    verify(udpSocket).getLocalAddress();
    verify(udpSocket).getLocalPort();
    assertEquals("udp:0.0.0.42:8080", udpProvider.toString());
  }

  @Test
  void testOnServiceTerminated4() throws UnknownHostException {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    UdpSocket udpSocket = mock(UdpSocket.class);
    doNothing().when(udpSocket).close();
    when(udpSocket.getLocalPort()).thenReturn(8080);
    when(udpSocket.getLocalAddress()).thenReturn(IpAddress.getByName("42"));
    UdpProvider udpProvider = new UdpProvider(udpSocket, new SymmetricUdpRelay());

    Exception exception = new Exception("An error occurred");
    exception.addSuppressed(new Throwable());

    // Act
    symmetricUdpRelay.onServiceTerminated(udpProvider, exception);

    // Assert
    verify(udpSocket).close();
    verify(udpSocket, atLeast(1)).getLocalAddress();
    verify(udpSocket, atLeast(1)).getLocalPort();
    assertEquals("udp:0.0.0.42:8080", udpProvider.toString());
  }

  @Test
  void testOnServiceTerminated5() throws UnknownHostException {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    UdpSocket udpSocket = mock(UdpSocket.class);
    doNothing().when(udpSocket).close();
    when(udpSocket.getLocalPort()).thenReturn(Integer.MIN_VALUE);
    when(udpSocket.getLocalAddress()).thenReturn(IpAddress.getByName("42"));
    UdpProvider udpProvider = new UdpProvider(udpSocket, new SymmetricUdpRelay());

    Exception exception = new Exception("An error occurred");
    exception.addSuppressed(new Throwable());

    // Act
    symmetricUdpRelay.onServiceTerminated(udpProvider, exception);

    // Assert
    verify(udpSocket).close();
    verify(udpSocket, atLeast(1)).getLocalAddress();
    verify(udpSocket, atLeast(1)).getLocalPort();
    assertEquals("udp:0.0.0.42:-2147483648", udpProvider.toString());
  }

  @Test
  void testOnTimeout() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();

    // Act
    symmetricUdpRelay.onTimeout(new Timer(10L, mock(TimerListener.class)));

    // Assert
    assertNull(symmetricUdpRelay.timer);
  }

  @Test
  void testLog() {
    // Arrange
    SymmetricUdpRelay symmetricUdpRelay = new SymmetricUdpRelay();
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    symmetricUdpRelay.log(logLevel, new Exception("An error occurred"));

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals(0L, symmetricUdpRelay.getLastLeftChangeTime());
    assertEquals(0L, symmetricUdpRelay.relay_time);
    assertEquals(0L, symmetricUdpRelay.expire_time);
    assertEquals(0L, symmetricUdpRelay.getLastRightChangeTime());
  }
}

