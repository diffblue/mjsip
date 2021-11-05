package org.mjsip.media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import org.zoolu.net.UdpSocket;
import org.zoolu.util.Encoder;

class RtpStreamReceiverDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    RtpStreamReceiver actualRtpStreamReceiver = new RtpStreamReceiver(new ByteArrayOutputStream(3), mock(Encoder.class),
        (UdpSocket) null);

    // Assert
    assertFalse(actualRtpStreamReceiver.ssrc_check);
    assertFalse(actualRtpStreamReceiver.silence_padding);
    assertFalse(actualRtpStreamReceiver.socket_is_local_attribute);
    assertEquals(0, actualRtpStreamReceiver.getLocalPort());
    assertFalse(actualRtpStreamReceiver.isRunning());
    assertFalse(actualRtpStreamReceiver.sequence_check);
    assertNull(actualRtpStreamReceiver.rtp_payload_format);
    assertNull(actualRtpStreamReceiver.rtp_control);
    assertNull(actualRtpStreamReceiver.remote_soaddr);
    assertEquals(0L, actualRtpStreamReceiver.packet_counter);
    assertNull(actualRtpStreamReceiver.listener);
    assertEquals(0, actualRtpStreamReceiver.getRED());
  }

  @Test
  void testSetControl() {
    // Arrange
    RtpStreamReceiver rtpStreamReceiver = new RtpStreamReceiver(new ByteArrayOutputStream(3), mock(Encoder.class),
        (UdpSocket) null);

    // Act
    rtpStreamReceiver.setControl(null);

    // Assert
    assertNull(rtpStreamReceiver.rtp_control);
  }

  @Test
  void testGetLocalPort() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new RtpStreamReceiver(new ByteArrayOutputStream(3), mock(Encoder.class), (UdpSocket) null)).getLocalPort());
    assertEquals(0, (new RtpStreamReceiver(new ByteArrayOutputStream(3), null, (UdpSocket) null)).getLocalPort());
  }

  @Test
  void testRun() {
    // Arrange
    RtpStreamReceiver rtpStreamReceiver = new RtpStreamReceiver(new ByteArrayOutputStream(3), mock(Encoder.class),
        (UdpSocket) null);

    // Act
    rtpStreamReceiver.run();

    // Assert that nothing has changed
    assertFalse(rtpStreamReceiver.ssrc_check);
    assertFalse(rtpStreamReceiver.silence_padding);
    assertFalse(rtpStreamReceiver.socket_is_local_attribute);
    assertEquals(0, rtpStreamReceiver.getLocalPort());
    assertFalse(rtpStreamReceiver.isRunning());
    assertFalse(rtpStreamReceiver.sequence_check);
    assertEquals(0L, rtpStreamReceiver.packet_counter);
    assertEquals(0, rtpStreamReceiver.getRED());
  }

  @Test
  void testRun2() {
    // Arrange
    RtpStreamReceiver rtpStreamReceiver = new RtpStreamReceiver(new ByteArrayOutputStream(3), null, (UdpSocket) null);

    // Act
    rtpStreamReceiver.run();

    // Assert that nothing has changed
    assertFalse(rtpStreamReceiver.ssrc_check);
    assertFalse(rtpStreamReceiver.silence_padding);
    assertFalse(rtpStreamReceiver.socket_is_local_attribute);
    assertEquals(0, rtpStreamReceiver.getLocalPort());
    assertFalse(rtpStreamReceiver.isRunning());
    assertFalse(rtpStreamReceiver.sequence_check);
    assertEquals(0L, rtpStreamReceiver.packet_counter);
    assertEquals(0, rtpStreamReceiver.getRED());
  }

  @Test
  void testSetSequenceCheck() {
    // Arrange
    RtpStreamReceiver rtpStreamReceiver = new RtpStreamReceiver(new ByteArrayOutputStream(3), mock(Encoder.class),
        (UdpSocket) null);

    // Act
    rtpStreamReceiver.setSequenceCheck(true);

    // Assert
    assertTrue(rtpStreamReceiver.sequence_check);
  }

  @Test
  void testSetSilencePadding() {
    // Arrange
    RtpStreamReceiver rtpStreamReceiver = new RtpStreamReceiver(new ByteArrayOutputStream(3), mock(Encoder.class),
        (UdpSocket) null);

    // Act
    rtpStreamReceiver.setSilencePadding(true);

    // Assert
    assertTrue(rtpStreamReceiver.silence_padding);
    assertTrue(rtpStreamReceiver.sequence_check);
  }

  @Test
  void testByte2int() {
    // Arrange, Act and Assert
    assertEquals(65, RtpStreamReceiver.byte2int((byte) 'A'));
    assertEquals(16705, RtpStreamReceiver.byte2int((byte) 'A', (byte) 'A'));
    assertEquals(65, RtpStreamReceiver.byte2int((byte) 0, (byte) 'A'));
    assertEquals(2113, RtpStreamReceiver.byte2int((byte) 8, (byte) 'A'));
    assertEquals(22593, RtpStreamReceiver.byte2int((byte) 'X', (byte) 'A'));
  }
}

