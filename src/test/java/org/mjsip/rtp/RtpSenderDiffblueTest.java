package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.zoolu.net.IpAddress;
import org.zoolu.net.SocketAddress;
import org.zoolu.net.UdpSocket;

class RtpSenderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    RtpSender actualRtpSender = new RtpSender(1, null, socketAddress);

    // Assert
    assertEquals(0L, actualRtpSender.getOctectCounter());
    assertEquals(0L, actualRtpSender.getPacketCounter());
    assertNull(actualRtpSender.getRemoteSoAddress());
    RtpContext rtpContext = actualRtpSender.rtp_context;
    assertEquals(1, rtpContext.getPayloadType());
    assertNull(rtpContext.getCsrc());
    RtpSocket rtpSocket = actualRtpSender.rtp_socket;
    assertSame(socketAddress, rtpSocket.remote_dest_soaddr);
    assertNull(rtpSocket.getUdpSocket());
    assertTrue(rtpSocket.symmetric_rtp);
  }

  @Test
  void testConstructor2() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("Soaddr");

    // Act
    RtpSender actualRtpSender = new RtpSender(1, null, socketAddress);

    // Assert
    assertEquals(0L, actualRtpSender.getOctectCounter());
    assertEquals(0L, actualRtpSender.getPacketCounter());
    assertNull(actualRtpSender.getRemoteSoAddress());
    RtpContext rtpContext = actualRtpSender.rtp_context;
    assertEquals(1, rtpContext.getPayloadType());
    assertNull(rtpContext.getCsrc());
    RtpSocket rtpSocket = actualRtpSender.rtp_socket;
    assertSame(socketAddress, rtpSocket.remote_dest_soaddr);
    assertNull(rtpSocket.getUdpSocket());
    assertTrue(rtpSocket.symmetric_rtp);
  }

  @Test
  void testConstructor3() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("org.zoolu.net.SocketAddress");

    // Act
    RtpSender actualRtpSender = new RtpSender(1, null, socketAddress);

    // Assert
    assertEquals(0L, actualRtpSender.getOctectCounter());
    assertEquals(0L, actualRtpSender.getPacketCounter());
    assertNull(actualRtpSender.getRemoteSoAddress());
    RtpContext rtpContext = actualRtpSender.rtp_context;
    assertEquals(1, rtpContext.getPayloadType());
    assertNull(rtpContext.getCsrc());
    RtpSocket rtpSocket = actualRtpSender.rtp_socket;
    assertSame(socketAddress, rtpSocket.remote_dest_soaddr);
    assertNull(rtpSocket.getUdpSocket());
    assertTrue(rtpSocket.symmetric_rtp);
  }

  @Test
  void testConstructor4() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("42 Main St", 8080);

    // Act
    RtpSender actualRtpSender = new RtpSender(1, null, socketAddress);

    // Assert
    assertEquals(0L, actualRtpSender.getOctectCounter());
    assertEquals(0L, actualRtpSender.getPacketCounter());
    assertNull(actualRtpSender.getRemoteSoAddress());
    RtpContext rtpContext = actualRtpSender.rtp_context;
    assertEquals(1, rtpContext.getPayloadType());
    assertNull(rtpContext.getCsrc());
    RtpSocket rtpSocket = actualRtpSender.rtp_socket;
    assertSame(socketAddress, rtpSocket.remote_dest_soaddr);
    assertNull(rtpSocket.getUdpSocket());
    assertTrue(rtpSocket.symmetric_rtp);
  }

  @Test
  void testConstructor5() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    RtpSender actualRtpSender = new RtpSender(1, null, new SocketAddress(socketAddress));

    // Assert
    assertEquals(0L, actualRtpSender.getOctectCounter());
    assertEquals(0L, actualRtpSender.getPacketCounter());
    assertNull(actualRtpSender.getRemoteSoAddress());
    RtpContext rtpContext = actualRtpSender.rtp_context;
    assertEquals(1, rtpContext.getPayloadType());
    assertNull(rtpContext.getCsrc());
    RtpSocket rtpSocket = actualRtpSender.rtp_socket;
    assertEquals(socketAddress, rtpSocket.remote_dest_soaddr);
    assertNull(rtpSocket.getUdpSocket());
    assertTrue(rtpSocket.symmetric_rtp);
  }

  @Test
  void testConstructor6() {
    // Arrange
    RtpContext rtp_context = new RtpContext(1);
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    RtpSender actualRtpSender = new RtpSender(rtp_context, null, socketAddress);

    // Assert
    assertEquals(0L, actualRtpSender.getOctectCounter());
    assertEquals(0L, actualRtpSender.getPacketCounter());
    assertNull(actualRtpSender.getRemoteSoAddress());
    RtpSocket rtpSocket = actualRtpSender.rtp_socket;
    assertSame(socketAddress, rtpSocket.remote_dest_soaddr);
    assertNull(rtpSocket.getUdpSocket());
    assertTrue(rtpSocket.symmetric_rtp);
  }

  @Test
  void testConstructor7() {
    // Arrange
    RtpContext rtp_context = new RtpContext(1);
    SocketAddress socketAddress = new SocketAddress("Soaddr");

    // Act
    RtpSender actualRtpSender = new RtpSender(rtp_context, null, socketAddress);

    // Assert
    assertEquals(0L, actualRtpSender.getOctectCounter());
    assertEquals(0L, actualRtpSender.getPacketCounter());
    assertNull(actualRtpSender.getRemoteSoAddress());
    RtpSocket rtpSocket = actualRtpSender.rtp_socket;
    assertSame(socketAddress, rtpSocket.remote_dest_soaddr);
    assertNull(rtpSocket.getUdpSocket());
    assertTrue(rtpSocket.symmetric_rtp);
  }

  @Test
  void testConstructor8() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    RtpSender actualRtpSender = new RtpSender((RtpContext) null, null, socketAddress);

    // Assert
    assertEquals(0L, actualRtpSender.getOctectCounter());
    assertNull(actualRtpSender.rtp_context);
    assertEquals(0L, actualRtpSender.getPacketCounter());
    assertNull(actualRtpSender.getRemoteSoAddress());
    RtpSocket rtpSocket = actualRtpSender.rtp_socket;
    assertNull(rtpSocket.getUdpSocket());
    assertSame(socketAddress, rtpSocket.remote_dest_soaddr);
    assertTrue(rtpSocket.symmetric_rtp);
  }

  @Test
  void testGetLocalPort() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);
    RtpContext rtp_context = new RtpContext(1);

    // Act and Assert
    assertEquals(8080, (new RtpSender(rtp_context, udpSocket, new SocketAddress("42 Main St"))).getLocalPort());
    verify(udpSocket).getLocalPort();
  }

  @Test
  void testGetLocalPort2() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);
    RtpContext rtp_context = new RtpContext(1);

    // Act and Assert
    assertEquals(8080, (new RtpSender(rtp_context, udpSocket, new SocketAddress("42 Main St", 8080))).getLocalPort());
    verify(udpSocket).getLocalPort();
  }

  @Test
  void testGetLocalPort3() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);
    RtpContext rtp_context = new RtpContext(1);

    // Act and Assert
    assertEquals(8080,
        (new RtpSender(rtp_context, udpSocket, new SocketAddress(new SocketAddress("42 Main St")))).getLocalPort());
    verify(udpSocket).getLocalPort();
  }

  @Test
  void testGetLocalPort4() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    // Act and Assert
    assertEquals(8080, (new RtpSender(1, udpSocket, new SocketAddress("42 Main St"))).getLocalPort());
    verify(udpSocket).getLocalPort();
  }

  @Test
  void testGetLocalPort5() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    // Act and Assert
    assertEquals(8080, (new RtpSender(1, udpSocket, new SocketAddress("org.zoolu.net.SocketAddress"))).getLocalPort());
    verify(udpSocket).getLocalPort();
  }

  @Test
  void testGetLocalPort6() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    RtpSender rtpSender = new RtpSender(1, udpSocket, new SocketAddress("42 Main St"));
    rtpSender.setRemoteSoAddress(null);

    // Act and Assert
    assertEquals(8080, rtpSender.getLocalPort());
    verify(udpSocket).getLocalPort();
  }

  @Test
  void testGetLocalPort7() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    RtpSender rtpSender = new RtpSender(1, udpSocket, new SocketAddress("42 Main St"));
    rtpSender.setTimestamp(10L);

    // Act and Assert
    assertEquals(8080, rtpSender.getLocalPort());
    verify(udpSocket).getLocalPort();
  }

  @Test
  void testGetLocalPort8() throws UnknownHostException {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    // Act and Assert
    assertEquals(8080,
        (new RtpSender(1, udpSocket, new SocketAddress(IpAddress.getByName("42"), 8080))).getLocalPort());
    verify(udpSocket).getLocalPort();
  }

  @Test
  void testGetLocalPort9() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    // Act and Assert
    assertEquals(8080,
        (new RtpSender(1, udpSocket, new SocketAddress(new IpAddress("42 Main St"), 8080))).getLocalPort());
    verify(udpSocket).getLocalPort();
  }

  @Test
  void testGetLocalPort10() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    // Act and Assert
    assertEquals(8080,
        (new RtpSender(1, udpSocket, new SocketAddress(new IpAddress((InetAddress) null), 8080))).getLocalPort());
    verify(udpSocket).getLocalPort();
  }

  @Test
  void testSetRemoteSoAddress() {
    // Arrange
    RtpContext rtp_context = new RtpContext(1);
    RtpSender rtpSender = new RtpSender(rtp_context, null, new SocketAddress("42 Main St"));
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    rtpSender.setRemoteSoAddress(socketAddress);

    // Assert
    assertSame(socketAddress, rtpSender.rtp_socket.remote_dest_soaddr);
  }

  @Test
  void testSetRemoteSoAddress2() {
    // Arrange
    RtpSender rtpSender = new RtpSender(1, null, new SocketAddress("42 Main St"));
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    rtpSender.setRemoteSoAddress(socketAddress);

    // Assert
    assertSame(socketAddress, rtpSender.rtp_socket.remote_dest_soaddr);
  }

  @Test
  void testGetRemoteSoAddress() {
    // Arrange
    RtpContext rtp_context = new RtpContext(1);

    // Act and Assert
    assertNull((new RtpSender(rtp_context, null, new SocketAddress("42 Main St"))).getRemoteSoAddress());
  }

  @Test
  void testGetRemoteSoAddress2() {
    // Arrange
    RtpContext rtp_context = new RtpContext(1);

    // Act and Assert
    assertNull((new RtpSender(rtp_context, null, new SocketAddress("Soaddr"))).getRemoteSoAddress());
  }

  @Test
  void testGetRemoteSoAddress3() {
    // Arrange, Act and Assert
    assertNull((new RtpSender(1, null, new SocketAddress("42 Main St"))).getRemoteSoAddress());
  }

  @Test
  void testGetSSRC() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.getSsrc()).thenReturn(1L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("42 Main St"));

    // Act and Assert
    assertEquals(1L, rtpSender.getSSRC());
    verify(rtpContext).getSsrc();
    assertEquals(0L, rtpSender.getRtpTimestamp());
  }

  @Test
  void testGetSSRC2() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.getSsrc()).thenReturn(1L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("org.zoolu.net.SocketAddress"));

    // Act and Assert
    assertEquals(1L, rtpSender.getSSRC());
    verify(rtpContext).getSsrc();
    assertEquals(0L, rtpSender.getRtpTimestamp());
  }

  @Test
  void testGetSSRC3() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.getSsrc()).thenReturn(1L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("42 Main St", 8080));

    // Act and Assert
    assertEquals(1L, rtpSender.getSSRC());
    verify(rtpContext).getSsrc();
    assertEquals(0L, rtpSender.getRtpTimestamp());
  }

  @Test
  void testGetSSRC4() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.getSsrc()).thenReturn(1L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress(new SocketAddress("42 Main St")));

    // Act and Assert
    assertEquals(1L, rtpSender.getSSRC());
    verify(rtpContext).getSsrc();
    assertEquals(0L, rtpSender.getRtpTimestamp());
  }

  @Test
  void testGetSSRC5() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.getSsrc()).thenReturn(1L);

    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("42 Main St"));
    rtpSender.setRemoteSoAddress(new SocketAddress("42 Main St"));

    // Act and Assert
    assertEquals(1L, rtpSender.getSSRC());
    verify(rtpContext).getSsrc();
    assertEquals(0L, rtpSender.getRtpTimestamp());
  }

  @Test
  void testGetRtpTimestamp() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.getTimestamp()).thenReturn(10L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("42 Main St"));

    // Act and Assert
    assertEquals(10L, rtpSender.getRtpTimestamp());
    verify(rtpContext).getTimestamp();
    assertEquals(0L, rtpSender.getSSRC());
  }

  @Test
  void testGetRtpTimestamp2() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.getTimestamp()).thenReturn(10L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("org.zoolu.net.SocketAddress"));

    // Act and Assert
    assertEquals(10L, rtpSender.getRtpTimestamp());
    verify(rtpContext).getTimestamp();
    assertEquals(0L, rtpSender.getSSRC());
  }

  @Test
  void testGetRtpTimestamp3() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.getTimestamp()).thenReturn(10L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("42 Main St", 8080));

    // Act and Assert
    assertEquals(10L, rtpSender.getRtpTimestamp());
    verify(rtpContext).getTimestamp();
    assertEquals(0L, rtpSender.getSSRC());
  }

  @Test
  void testGetRtpTimestamp4() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.getTimestamp()).thenReturn(10L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress(new SocketAddress("42 Main St")));

    // Act and Assert
    assertEquals(10L, rtpSender.getRtpTimestamp());
    verify(rtpContext).getTimestamp();
    assertEquals(0L, rtpSender.getSSRC());
  }

  @Test
  void testGetRtpTimestamp5() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.getTimestamp()).thenReturn(10L);

    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("42 Main St"));
    rtpSender.setRemoteSoAddress(new SocketAddress("42 Main St"));

    // Act and Assert
    assertEquals(10L, rtpSender.getRtpTimestamp());
    verify(rtpContext).getTimestamp();
    assertEquals(0L, rtpSender.getSSRC());
  }

  @Test
  void testSetTimestamp() {
    // Arrange
    RtpContext rtp_context = new RtpContext(1);
    RtpSender rtpSender = new RtpSender(rtp_context, null, new SocketAddress("42 Main St"));

    // Act
    rtpSender.setTimestamp(10L);

    // Assert
    assertEquals(10L, rtpSender.rtp_context.getTimestamp());
  }

  @Test
  void testSetTimestamp2() {
    // Arrange
    RtpContext rtp_context = new RtpContext(1);
    RtpSender rtpSender = new RtpSender(rtp_context, null, new SocketAddress("Soaddr"));

    // Act
    rtpSender.setTimestamp(10L);

    // Assert
    assertEquals(10L, rtpSender.rtp_context.getTimestamp());
  }

  @Test
  void testSetTimestamp3() {
    // Arrange
    RtpSender rtpSender = new RtpSender(1, null, new SocketAddress("42 Main St"));

    // Act
    rtpSender.setTimestamp(10L);

    // Assert
    assertEquals(10L, rtpSender.rtp_context.getTimestamp());
  }

  @Test
  void testIncTimestamp() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.incTimestamp(anyLong())).thenReturn(1L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("42 Main St"));

    // Act
    rtpSender.incTimestamp(2L);

    // Assert
    verify(rtpContext).incTimestamp(anyLong());
    assertEquals(0L, rtpSender.getRtpTimestamp());
  }

  @Test
  void testIncTimestamp2() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.incTimestamp(anyLong())).thenReturn(1L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("org.zoolu.net.SocketAddress"));

    // Act
    rtpSender.incTimestamp(2L);

    // Assert
    verify(rtpContext).incTimestamp(anyLong());
    assertEquals(0L, rtpSender.getRtpTimestamp());
  }

  @Test
  void testIncTimestamp3() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.incTimestamp(anyLong())).thenReturn(1L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("42 Main St", 8080));

    // Act
    rtpSender.incTimestamp(2L);

    // Assert
    verify(rtpContext).incTimestamp(anyLong());
    assertEquals(0L, rtpSender.getRtpTimestamp());
  }

  @Test
  void testIncTimestamp4() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.incTimestamp(anyLong())).thenReturn(1L);
    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress(new SocketAddress("42 Main St")));

    // Act
    rtpSender.incTimestamp(2L);

    // Assert
    verify(rtpContext).incTimestamp(anyLong());
    assertEquals(0L, rtpSender.getRtpTimestamp());
  }

  @Test
  void testIncTimestamp5() {
    // Arrange
    RtpContext rtpContext = mock(RtpContext.class);
    when(rtpContext.incTimestamp(anyLong())).thenReturn(1L);

    RtpSender rtpSender = new RtpSender(rtpContext, null, new SocketAddress("42 Main St"));
    rtpSender.setRemoteSoAddress(new SocketAddress("42 Main St"));

    // Act
    rtpSender.incTimestamp(2L);

    // Assert
    verify(rtpContext).incTimestamp(anyLong());
    assertEquals(0L, rtpSender.getRtpTimestamp());
  }

  @Test
  void testClose() {
    // Arrange
    RtpContext rtp_context = new RtpContext(1);
    RtpSender rtpSender = new RtpSender(rtp_context, null, new SocketAddress("42 Main St"));

    // Act
    rtpSender.close();

    // Assert that nothing has changed
    assertEquals(0L, rtpSender.getOctectCounter());
    assertEquals(0L, rtpSender.getPacketCounter());
  }

  @Test
  void testClose2() {
    // Arrange
    RtpContext rtp_context = new RtpContext(1);
    RtpSender rtpSender = new RtpSender(rtp_context, null, new SocketAddress("Soaddr"));

    // Act
    rtpSender.close();

    // Assert that nothing has changed
    assertEquals(0L, rtpSender.getOctectCounter());
    assertEquals(0L, rtpSender.getPacketCounter());
  }

  @Test
  void testClose3() {
    // Arrange
    RtpSender rtpSender = new RtpSender(1, null, new SocketAddress("42 Main St"));

    // Act
    rtpSender.close();

    // Assert that nothing has changed
    assertEquals(0L, rtpSender.getOctectCounter());
    assertEquals(0L, rtpSender.getPacketCounter());
  }
}

