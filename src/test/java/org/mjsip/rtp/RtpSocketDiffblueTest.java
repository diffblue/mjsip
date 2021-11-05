package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class RtpSocketDiffblueTest {
  @Test
  void testSend() throws IOException {
    // Arrange
    RtpSocket rtpSocket = new RtpSocket(null);
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpSocket.send(rtpPacket);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
    assertTrue(rtpSocket.symmetric_rtp);
  }
}

