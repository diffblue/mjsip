package org.mjsip.sip.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.zoolu.net.IpAddress;
import org.zoolu.net.SocketAddress;

class ConnectionIdDiffblueTest {
  @Test
  void testConstructor() throws UnknownHostException {
    // Arrange
    IpAddress byName = IpAddress.getByName("42");

    // Act and Assert
    assertEquals("Protocol", (new ConnectionId("Protocol", byName, 8080)).getProtocol());
    assertEquals("0.0.0.42", byName.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange, Act and Assert
    assertEquals("Protocol", (new ConnectionId("Protocol", new SocketAddress("42 Main St"))).getProtocol());
  }

  @Test
  void testConstructor3() {
    // Arrange, Act and Assert
    assertEquals("Protocol",
        (new ConnectionId(new ConnectionId("Protocol", new SocketAddress("42 Main St")))).getProtocol());
  }

  @Test
  void testConstructor4() {
    // Arrange, Act and Assert
    assertEquals("42", (new ConnectionId(new ConnectionId("42", new SocketAddress("42 Main St")))).getProtocol());
  }

  @Test
  void testConstructor5() throws UnknownHostException {
    // Arrange
    SipTransportConnection sipTransportConnection = mock(SipTransportConnection.class);
    when(sipTransportConnection.getRemotePort()).thenReturn(8080);
    when(sipTransportConnection.getRemoteAddress()).thenReturn(IpAddress.getByName("42"));
    when(sipTransportConnection.getProtocol()).thenReturn("Protocol");

    // Act and Assert
    assertEquals("Protocol", (new ConnectionId(sipTransportConnection)).getProtocol());
    verify(sipTransportConnection).getProtocol();
    verify(sipTransportConnection).getRemoteAddress();
    verify(sipTransportConnection).getRemotePort();
  }

  @Test
  void testGetProtocol() {
    // Arrange, Act and Assert
    assertEquals("Protocol", (new ConnectionId("Protocol", new SocketAddress("42 Main St"))).getProtocol());
  }
}

