package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.zoolu.net.IpAddress;
import org.zoolu.net.SocketAddress;
import org.zoolu.net.UdpProvider;
import org.zoolu.net.UdpSocket;

class RtcpProviderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    RtcpProvider actualRtcpProvider = new RtcpProvider(mock(UdpSocket.class), mock(RtcpProviderListener.class));

    // Assert
    assertNull(actualRtcpProvider.getRemoteSourceSoAddress());
    assertNull(actualRtcpProvider.remote_dest_soaddr);
    UdpProvider expectedUdpProvider = actualRtcpProvider.udp;
    UdpProvider udpProvider = actualRtcpProvider.getUdpProvider();
    assertSame(expectedUdpProvider, udpProvider);
    assertFalse(actualRtcpProvider.getSymmetricRtcpMode());
    assertEquals("udp:null:0", udpProvider.toString());
    assertTrue(udpProvider.isRunning());
    assertEquals(2000, udpProvider.getSoTimeout());
    assertEquals(0, udpProvider.getMinimumReceivedDataLength());
    assertEquals(0L, udpProvider.getAliveTime());
  }

  @Test
  void testConstructor2() {
    // Arrange
    UdpSocket udp_socket = mock(UdpSocket.class);

    // Act
    RtcpProvider actualRtcpProvider = new RtcpProvider(udp_socket, new SocketAddress("42 Main St"),
        mock(RtcpProviderListener.class));

    // Assert
    assertNull(actualRtcpProvider.getRemoteSourceSoAddress());
    assertFalse(actualRtcpProvider.getSymmetricRtcpMode());
    UdpProvider expectedUdpProvider = actualRtcpProvider.udp;
    UdpProvider udpProvider = actualRtcpProvider.getUdpProvider();
    assertSame(expectedUdpProvider, udpProvider);
    assertEquals("udp:null:0", udpProvider.toString());
    assertTrue(udpProvider.isRunning());
    assertEquals(0L, udpProvider.getAliveTime());
    assertEquals(2000, udpProvider.getSoTimeout());
    assertEquals(0, udpProvider.getMinimumReceivedDataLength());
  }

  @Test
  void testSend() throws IOException {
    // Arrange
    RtcpProvider rtcpProvider = new RtcpProvider(mock(UdpSocket.class), mock(RtcpProviderListener.class));

    // Act and Assert
    assertThrows(IOException.class,
        () -> rtcpProvider.send(new RtcpCompoundPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))));
  }

  @Test
  void testSend2() throws IOException {
    // Arrange
    RtcpProvider rtcpProvider = new RtcpProvider(mock(UdpSocket.class), null);

    // Act and Assert
    assertThrows(IOException.class,
        () -> rtcpProvider.send(new RtcpCompoundPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))));
  }

  @Test
  void testSend3() throws IOException {
    // Arrange
    RtcpProvider rtcpProvider = new RtcpProvider(mock(UdpSocket.class), mock(RtcpProviderListener.class));

    // Act and Assert
    assertThrows(IOException.class,
        () -> rtcpProvider.send(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))));
  }

  @Test
  void testSend4() throws IOException {
    // Arrange
    RtcpProvider rtcpProvider = new RtcpProvider(mock(UdpSocket.class), null);

    // Act and Assert
    assertThrows(IOException.class,
        () -> rtcpProvider.send(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))));
  }

  @Test
  void testOnServiceTerminated() {
    // Arrange
    RtcpProviderListener rtcpProviderListener = mock(RtcpProviderListener.class);
    doNothing().when(rtcpProviderListener).onServiceTerminated((RtcpProvider) any(), (Exception) any());
    RtcpProvider rtcpProvider = new RtcpProvider(mock(UdpSocket.class), rtcpProviderListener);

    // Act
    rtcpProvider.onServiceTerminated(null, new Exception("An error occurred"));

    // Assert
    verify(rtcpProviderListener).onServiceTerminated((RtcpProvider) any(), (Exception) any());
    assertEquals("udp:null:0", rtcpProvider.getUdpProvider().toString());
  }
}

