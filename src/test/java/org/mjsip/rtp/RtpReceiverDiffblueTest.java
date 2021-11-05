package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import org.zoolu.net.UdpProvider;
import org.zoolu.net.UdpSocket;

class RtpReceiverDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    RtpReceiver actualRtpReceiver = new RtpReceiver(null, mock(RtpReceiverListener.class));

    // Assert
    assertEquals(-1, actualRtpReceiver.getPayloadType());
    assertNull(actualRtpReceiver.getRemoteSoAddress());
    RtpProvider rtpProvider = actualRtpReceiver.rtp_provider;
    assertNull(rtpProvider.remote_dest_soaddr);
    assertTrue(rtpProvider.symmetric_rtp);
    UdpProvider udpProvider = rtpProvider.getUdpProvider();
    assertNull(udpProvider.getUdpSocket());
    assertEquals(2000, udpProvider.getSoTimeout());
    assertEquals(0, udpProvider.getMinimumReceivedDataLength());
    assertEquals(0L, udpProvider.getAliveTime());
    assertTrue(udpProvider.isRunning());
  }

  @Test
  void testGetLocalPort() {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);
    RtpReceiver rtpReceiver = new RtpReceiver(udpSocket, null);

    // Act and Assert
    assertEquals(8080, rtpReceiver.getLocalPort());
    verify(udpSocket).getLocalPort();
    assertEquals("udp:null:8080", rtpReceiver.rtp_provider.getUdpProvider().toString());
  }

  @Test
  void testGetLocalPort2() throws UnsupportedEncodingException {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    RtpReceiver rtpReceiver = new RtpReceiver(udpSocket, null);
    rtpReceiver.processRtpReceivedPacket(null, new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3));

    // Act and Assert
    assertEquals(8080, rtpReceiver.getLocalPort());
    verify(udpSocket).getLocalPort();
    assertEquals("udp:null:8080", rtpReceiver.rtp_provider.getUdpProvider().toString());
  }

  @Test
  void testGetLocalPort3() throws UnsupportedEncodingException {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    RtpReceiver rtpReceiver = new RtpReceiver(udpSocket, null);
    rtpReceiver.processRtpReceivedPacket(null, new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3));

    // Act and Assert
    assertEquals(8080, rtpReceiver.getLocalPort());
    verify(udpSocket).getLocalPort();
    assertEquals("udp:null:8080", rtpReceiver.rtp_provider.getUdpProvider().toString());
  }

  @Test
  void testGetLocalPort4() throws UnsupportedEncodingException {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    RtpReceiver rtpReceiver = new RtpReceiver(udpSocket, null);
    RtpContext rtp_ctx = new RtpContext(1);
    rtpReceiver.processRtpReceivedPacket(null,
        new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3));

    // Act and Assert
    assertEquals(8080, rtpReceiver.getLocalPort());
    verify(udpSocket).getLocalPort();
    assertEquals("udp:null:8080", rtpReceiver.rtp_provider.getUdpProvider().toString());
  }

  @Test
  void testGetLocalPort5() throws UnsupportedEncodingException {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    RtpReceiver rtpReceiver = new RtpReceiver(udpSocket, null);
    rtpReceiver.processRtpReceivedPacket(null,
        new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3));

    // Act and Assert
    assertEquals(8080, rtpReceiver.getLocalPort());
    verify(udpSocket).getLocalPort();
    assertEquals("udp:null:8080", rtpReceiver.rtp_provider.getUdpProvider().toString());
  }

  @Test
  void testGetLocalPort6() throws UnsupportedEncodingException {
    // Arrange
    UdpSocket udpSocket = mock(UdpSocket.class);
    when(udpSocket.getLocalPort()).thenReturn(8080);

    RtpReceiver rtpReceiver = new RtpReceiver(udpSocket, null);
    rtpReceiver.processRtpReceivedPacket(null, new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3));

    // Act and Assert
    assertEquals(8080, rtpReceiver.getLocalPort());
    verify(udpSocket).getLocalPort();
    assertEquals("udp:null:8080", rtpReceiver.rtp_provider.getUdpProvider().toString());
  }

  @Test
  void testGetRemoteSoAddress() {
    // Arrange, Act and Assert
    assertNull((new RtpReceiver(null, null)).getRemoteSoAddress());
    assertNull((new RtpReceiver(null, mock(RtpReceiverListener.class))).getRemoteSoAddress());
  }

  @Test
  void testGetSSRC() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new RtpReceiver(null, null)).getSSRC());
    assertEquals(-1L, (new RtpReceiver(null, mock(RtpReceiverListener.class))).getSSRC());
  }

  @Test
  void testGetSSRC2() throws UnsupportedEncodingException {
    // Arrange
    RtpReceiver rtpReceiver = new RtpReceiver(null, null);
    rtpReceiver.processRtpReceivedPacket(null, new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3));

    // Act and Assert
    assertEquals(0L, rtpReceiver.getSSRC());
  }

  @Test
  void testGetSSRC3() throws UnsupportedEncodingException {
    // Arrange
    RtpReceiver rtpReceiver = new RtpReceiver(null, null);
    rtpReceiver.processRtpReceivedPacket(null, new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3));

    // Act and Assert
    assertEquals(0L, rtpReceiver.getSSRC());
  }

  @Test
  void testGetSSRC4() throws UnsupportedEncodingException {
    // Arrange
    RtpReceiver rtpReceiver = new RtpReceiver(null, null);
    rtpReceiver.processRtpReceivedPacket(null,
        new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3));

    // Act and Assert
    assertEquals(1L, rtpReceiver.getSSRC());
  }

  @Test
  void testGetSSRC5() throws UnsupportedEncodingException {
    // Arrange
    RtpReceiver rtpReceiver = new RtpReceiver(null, null);
    rtpReceiver.processRtpReceivedPacket(null, new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3));

    // Act and Assert
    assertEquals(1L, rtpReceiver.getSSRC());
  }

  @Test
  void testGetPayloadType() {
    // Arrange, Act and Assert
    assertEquals(-1, (new RtpReceiver(null, null)).getPayloadType());
    assertEquals(-1, (new RtpReceiver(null, mock(RtpReceiverListener.class))).getPayloadType());
  }

  @Test
  void testGetPayloadType2() throws UnsupportedEncodingException {
    // Arrange
    RtpReceiver rtpReceiver = new RtpReceiver(null, null);
    rtpReceiver.processRtpReceivedPacket(null, new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3));

    // Act and Assert
    assertEquals(-1, rtpReceiver.getPayloadType());
  }

  @Test
  void testGetPayloadType3() throws UnsupportedEncodingException {
    // Arrange
    RtpReceiver rtpReceiver = new RtpReceiver(null, null);
    rtpReceiver.processRtpReceivedPacket(null, new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3));

    // Act and Assert
    assertEquals(-1, rtpReceiver.getPayloadType());
  }

  @Test
  void testGetPayloadType4() throws UnsupportedEncodingException {
    // Arrange
    RtpReceiver rtpReceiver = new RtpReceiver(null, null);
    RtpContext rtp_ctx = new RtpContext(1);
    rtpReceiver.processRtpReceivedPacket(null,
        new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3));

    // Act and Assert
    assertEquals(1, rtpReceiver.getPayloadType());
  }

  @Test
  void testGetPayloadType5() throws UnsupportedEncodingException {
    // Arrange
    RtpReceiver rtpReceiver = new RtpReceiver(null, null);
    rtpReceiver.processRtpReceivedPacket(null,
        new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3));

    // Act and Assert
    assertEquals(1, rtpReceiver.getPayloadType());
  }

  @Test
  void testGetPayloadType6() throws UnsupportedEncodingException {
    // Arrange
    RtpReceiver rtpReceiver = new RtpReceiver(null, null);
    rtpReceiver.processRtpReceivedPacket(null, new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3));

    // Act and Assert
    assertEquals(1, rtpReceiver.getPayloadType());
  }

  @Test
  void testProcessRtpReceivedPacket() throws UnsupportedEncodingException {
    // Arrange
    RtpReceiver rtpReceiver = new RtpReceiver(null, null);

    // Act
    rtpReceiver.processRtpReceivedPacket(null, new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3));

    // Assert
    assertEquals(-1, rtpReceiver.getPayloadType());
    RtpContext rtpContext = rtpReceiver.rtp_context;
    assertEquals(0, rtpContext.getSequenceNumber());
    assertEquals(-1, rtpContext.getPayloadType());
    assertNull(rtpContext.getCsrc());
    assertEquals(0L, rtpContext.getTimestamp());
    assertEquals(0L, rtpContext.getSsrc());
  }

  @Test
  void testProcessRtpReceivedPacket2() throws UnsupportedEncodingException {
    // Arrange
    RtpReceiverListener rtpReceiverListener = mock(RtpReceiverListener.class);
    doNothing().when(rtpReceiverListener).onReceivedPacket((RtpReceiver) any(), (RtpPacket) any());
    RtpReceiver rtpReceiver = new RtpReceiver(null, rtpReceiverListener);
    RtpProvider rtp = new RtpProvider(null, null);

    // Act
    rtpReceiver.processRtpReceivedPacket(rtp, new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3));

    // Assert
    verify(rtpReceiverListener).onReceivedPacket((RtpReceiver) any(), (RtpPacket) any());
  }
}

