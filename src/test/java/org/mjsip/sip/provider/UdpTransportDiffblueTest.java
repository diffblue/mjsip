package org.mjsip.sip.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.zoolu.net.IpAddress;
import org.zoolu.net.UdpProvider;
import org.zoolu.net.UdpSocket;

class UdpTransportDiffblueTest {
  @Test
  void testGetLocalPort() {
    // Arrange, Act and Assert
    assertEquals(0, (new UdpTransport((UdpSocket) null)).getLocalPort());
  }

  @Test
  void testGetLocalPort2() {
    // Arrange
    UdpTransport udpTransport = new UdpTransport((UdpSocket) null);
    udpTransport.setListener(new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"}));

    // Act and Assert
    assertEquals(0, udpTransport.getLocalPort());
  }

  @Test
  void testGetLocalPort3() {
    // Arrange
    UdpTransport udpTransport = new UdpTransport((UdpSocket) null);
    udpTransport.setListener(new SipProvider("Via addr", 8080, new String[]{"foo", "foo", "foo"}));

    // Act and Assert
    assertEquals(0, udpTransport.getLocalPort());
  }

  @Test
  void testGetLocalPort4() {
    // Arrange
    UdpTransport udpTransport = new UdpTransport((UdpSocket) null);
    udpTransport.setListener(new SipProvider("=", 8080, new String[]{"foo", "foo", "foo"}));

    // Act and Assert
    assertEquals(0, udpTransport.getLocalPort());
  }

  @Test
  void testToString() throws UnknownHostException {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);
    when(udpSocket.getLocalAddress()).thenReturn(IpAddress.getByName("42"));
    UdpTransport udpTransport = new UdpTransport(udpSocket);

    // Act and Assert
    assertEquals("udp:0.0.0.42:8080", udpTransport.toString());
    verify(udpSocket).getLocalAddress();
    verify(udpSocket).getLocalPort();
    assertEquals(8080, udpTransport.getLocalPort());
    assertEquals("udp:0.0.0.42:8080", udpTransport.udp_provider.toString());
  }

  @Test
  void testToString2() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);
    when(udpSocket.getLocalAddress()).thenReturn(null);
    UdpTransport udpTransport = new UdpTransport(udpSocket);

    // Act and Assert
    assertEquals("udp:null:8080", udpTransport.toString());
    verify(udpSocket).getLocalAddress();
    verify(udpSocket).getLocalPort();
    assertEquals(8080, udpTransport.getLocalPort());
  }

  @Test
  void testToString3() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);
    when(udpSocket.getLocalAddress()).thenReturn(new IpAddress("42 Main St"));
    UdpTransport udpTransport = new UdpTransport(udpSocket);

    // Act and Assert
    assertEquals("udp:42 Main St:8080", udpTransport.toString());
    verify(udpSocket).getLocalAddress();
    verify(udpSocket).getLocalPort();
    assertEquals(8080, udpTransport.getLocalPort());
  }

  @Test
  void testToString4() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);
    when(udpSocket.getLocalAddress()).thenReturn(new IpAddress((InetAddress) null));
    UdpTransport udpTransport = new UdpTransport(udpSocket);

    // Act and Assert
    assertEquals("udp:null:8080", udpTransport.toString());
    verify(udpSocket).getLocalAddress();
    verify(udpSocket).getLocalPort();
    assertEquals(8080, udpTransport.getLocalPort());
  }
}

