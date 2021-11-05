package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.zoolu.net.UdpProvider;
import org.zoolu.net.UdpSocket;

class RtpControlDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    RtpControl actualRtpControl = new RtpControl("Cname", (UdpSocket) null);

    // Assert
    assertFalse(actualRtpControl.udp_socket_is_local);
    assertEquals(-1L, actualRtpControl.start_timestamp);
    assertNull(actualRtpControl.rtp_sender);
    assertNull(actualRtpControl.rtp_receiver);
    assertEquals("Cname", actualRtpControl.cname);
    RtcpProvider rtcpProvider = actualRtpControl.rtcp;
    assertNull(rtcpProvider.remote_dest_soaddr);
    assertSame(actualRtpControl, rtcpProvider.listener);
    assertFalse(rtcpProvider.getSymmetricRtcpMode());
    assertNull(rtcpProvider.getRemoteSourceSoAddress());
    UdpProvider udpProvider = rtcpProvider.getUdpProvider();
    assertNull(udpProvider.getUdpSocket());
    assertEquals(2000, udpProvider.getSoTimeout());
    assertEquals(0, udpProvider.getMinimumReceivedDataLength());
    assertEquals(0L, udpProvider.getAliveTime());
    assertTrue(udpProvider.isRunning());
  }

  @Test
  void testSetSymmetricRtcpMode() {
    // Arrange
    RtpControl rtpControl = new RtpControl("Cname", (UdpSocket) null);

    // Act
    rtpControl.setSymmetricRtcpMode(true);

    // Assert
    assertTrue(rtpControl.rtcp.getSymmetricRtcpMode());
  }

  @Test
  void testSetSymmetricRtcpMode2() {
    // Arrange
    RtpControl rtpControl = new RtpControl("42", (UdpSocket) null);

    // Act
    rtpControl.setSymmetricRtcpMode(true);

    // Assert
    assertTrue(rtpControl.rtcp.getSymmetricRtcpMode());
  }

  @Test
  void testSetSymmetricRtcpMode3() {
    // Arrange
    RtpControl rtpControl = new RtpControl("Cname", (UdpSocket) null);
    rtpControl.setRtpReceiver(null);

    // Act
    rtpControl.setSymmetricRtcpMode(true);

    // Assert
    assertTrue(rtpControl.rtcp.getSymmetricRtcpMode());
  }

  @Test
  void testSetSymmetricRtcpMode4() throws UnknownHostException {
    // Arrange
    RtpControl rtpControl = new RtpControl("Cname", (UdpSocket) null, "42", 8080);

    // Act
    rtpControl.setSymmetricRtcpMode(true);

    // Assert
    assertTrue(rtpControl.rtcp.getSymmetricRtcpMode());
  }

  @Test
  void testSendReport() {
    // Arrange
    RtpControl rtpControl = new RtpControl("Cname", (UdpSocket) null);

    // Act
    rtpControl.sendReport();

    // Assert that nothing has changed
    assertFalse(rtpControl.udp_socket_is_local);
    assertEquals(-1L, rtpControl.start_timestamp);
    assertEquals("Cname", rtpControl.cname);
  }

  @Test
  void testSendReport2() {
    // Arrange
    RtpControl rtpControl = new RtpControl("DEBUG: sendReport()", (UdpSocket) null);

    // Act
    rtpControl.sendReport();

    // Assert that nothing has changed
    assertFalse(rtpControl.udp_socket_is_local);
    assertEquals(-1L, rtpControl.start_timestamp);
    assertEquals("DEBUG: sendReport()", rtpControl.cname);
  }

  @Test
  void testSendReport3() {
    // Arrange
    RtpControl rtpControl = new RtpControl("Cname", (UdpSocket) null);
    rtpControl.setRtpReceiver(null);

    // Act
    rtpControl.sendReport();

    // Assert that nothing has changed
    assertFalse(rtpControl.udp_socket_is_local);
    assertEquals(-1L, rtpControl.start_timestamp);
    assertEquals("Cname", rtpControl.cname);
  }

  @Test
  void testHalt() {
    // Arrange
    RtpControl rtpControl = new RtpControl("Cname", (UdpSocket) null);

    // Act
    rtpControl.halt();

    // Assert
    assertNull(rtpControl.rtp_sender);
    assertNull(rtpControl.rtp_receiver);
  }

  @Test
  void testHalt2() {
    // Arrange
    RtpControl rtpControl = new RtpControl("Cname", (UdpSocket) null);
    rtpControl.setRtpReceiver(null);

    // Act
    rtpControl.halt();

    // Assert
    assertNull(rtpControl.rtp_sender);
    assertNull(rtpControl.rtp_receiver);
  }

  @Test
  void testHalt3() throws UnknownHostException {
    // Arrange
    RtpControl rtpControl = new RtpControl("Cname", (UdpSocket) null, "42", 8080);

    // Act
    rtpControl.halt();

    // Assert
    assertNull(rtpControl.rtp_sender);
    assertNull(rtpControl.rtp_receiver);
  }
}

