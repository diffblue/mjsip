package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.zoolu.net.SocketAddress;
import org.zoolu.net.UdpProvider;

class RtpProviderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    RtpProvider actualRtpProvider = new RtpProvider(null, mock(RtpProviderListener.class));

    // Assert
    assertTrue(actualRtpProvider.symmetric_rtp);
    assertNull(actualRtpProvider.remote_dest_soaddr);
    UdpProvider expectedUdpProvider = actualRtpProvider.udp;
    UdpProvider udpProvider = actualRtpProvider.getUdpProvider();
    assertSame(expectedUdpProvider, udpProvider);
    assertEquals(0L, udpProvider.getAliveTime());
    assertTrue(udpProvider.isRunning());
    assertNull(udpProvider.getUdpSocket());
    assertEquals(2000, udpProvider.getSoTimeout());
    assertEquals(0, udpProvider.getMinimumReceivedDataLength());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    RtpProvider actualRtpProvider = new RtpProvider(null, new SocketAddress("42 Main St"),
        mock(RtpProviderListener.class));

    // Assert
    assertTrue(actualRtpProvider.symmetric_rtp);
    UdpProvider expectedUdpProvider = actualRtpProvider.udp;
    UdpProvider udpProvider = actualRtpProvider.getUdpProvider();
    assertSame(expectedUdpProvider, udpProvider);
    assertTrue(udpProvider.isRunning());
    assertNull(udpProvider.getUdpSocket());
    assertEquals(2000, udpProvider.getSoTimeout());
    assertEquals(0, udpProvider.getMinimumReceivedDataLength());
    assertEquals(0L, udpProvider.getAliveTime());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    RtpProvider actualRtpProvider = new RtpProvider(null, new SocketAddress("Soaddr"), mock(RtpProviderListener.class));

    // Assert
    assertTrue(actualRtpProvider.symmetric_rtp);
    UdpProvider expectedUdpProvider = actualRtpProvider.udp;
    UdpProvider udpProvider = actualRtpProvider.getUdpProvider();
    assertSame(expectedUdpProvider, udpProvider);
    assertTrue(udpProvider.isRunning());
    assertNull(udpProvider.getUdpSocket());
    assertEquals(2000, udpProvider.getSoTimeout());
    assertEquals(0, udpProvider.getMinimumReceivedDataLength());
    assertEquals(0L, udpProvider.getAliveTime());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    RtpProvider actualRtpProvider = new RtpProvider(null, new SocketAddress("org.zoolu.net.SocketAddress"),
        mock(RtpProviderListener.class));

    // Assert
    assertTrue(actualRtpProvider.symmetric_rtp);
    UdpProvider expectedUdpProvider = actualRtpProvider.udp;
    UdpProvider udpProvider = actualRtpProvider.getUdpProvider();
    assertSame(expectedUdpProvider, udpProvider);
    assertTrue(udpProvider.isRunning());
    assertNull(udpProvider.getUdpSocket());
    assertEquals(2000, udpProvider.getSoTimeout());
    assertEquals(0, udpProvider.getMinimumReceivedDataLength());
    assertEquals(0L, udpProvider.getAliveTime());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    RtpProvider actualRtpProvider = new RtpProvider(null, new SocketAddress("42 Main St", 8080),
        mock(RtpProviderListener.class));

    // Assert
    assertTrue(actualRtpProvider.symmetric_rtp);
    UdpProvider expectedUdpProvider = actualRtpProvider.udp;
    UdpProvider udpProvider = actualRtpProvider.getUdpProvider();
    assertSame(expectedUdpProvider, udpProvider);
    assertTrue(udpProvider.isRunning());
    assertNull(udpProvider.getUdpSocket());
    assertEquals(2000, udpProvider.getSoTimeout());
    assertEquals(0, udpProvider.getMinimumReceivedDataLength());
    assertEquals(0L, udpProvider.getAliveTime());
  }

  @Test
  void testConstructor6() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    RtpProvider actualRtpProvider = new RtpProvider(null, new SocketAddress(socketAddress),
        mock(RtpProviderListener.class));

    // Assert
    assertTrue(actualRtpProvider.symmetric_rtp);
    assertEquals(socketAddress, actualRtpProvider.remote_dest_soaddr);
    UdpProvider expectedUdpProvider = actualRtpProvider.udp;
    UdpProvider udpProvider = actualRtpProvider.getUdpProvider();
    assertSame(expectedUdpProvider, udpProvider);
    assertTrue(udpProvider.isRunning());
    assertNull(udpProvider.getUdpSocket());
    assertEquals(2000, udpProvider.getSoTimeout());
    assertEquals(0, udpProvider.getMinimumReceivedDataLength());
    assertEquals(0L, udpProvider.getAliveTime());
  }

  @Test
  void testSend() throws IOException {
    // Arrange
    RtpProvider rtpProvider = new RtpProvider(null, null);
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpProvider.send(rtpPacket);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
    assertTrue(rtpProvider.symmetric_rtp);
    UdpProvider expectedUdpProvider = rtpProvider.udp;
    assertSame(expectedUdpProvider, rtpProvider.getUdpProvider());
  }

  @Test
  void testSend2() throws IOException {
    // Arrange
    RtpProvider rtpProvider = new RtpProvider(null, mock(RtpProviderListener.class));
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpProvider.send(rtpPacket);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
    assertTrue(rtpProvider.symmetric_rtp);
    UdpProvider expectedUdpProvider = rtpProvider.udp;
    assertSame(expectedUdpProvider, rtpProvider.getUdpProvider());
  }

  @Test
  void testOnServiceTerminated() {
    // Arrange
    RtpProvider rtpProvider = new RtpProvider(null, null);

    // Act
    rtpProvider.onServiceTerminated(null, new Exception("An error occurred"));

    // Assert that nothing has changed
    assertTrue(rtpProvider.symmetric_rtp);
    UdpProvider expectedUdpProvider = rtpProvider.udp;
    assertSame(expectedUdpProvider, rtpProvider.getUdpProvider());
  }
}

