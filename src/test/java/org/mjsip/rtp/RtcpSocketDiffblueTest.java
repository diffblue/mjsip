package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class RtcpSocketDiffblueTest {
  @Test
  void testSend() throws IOException {
    // Arrange
    RtcpSocket rtcpSocket = new RtcpSocket(null);

    // Act and Assert
    assertThrows(IOException.class,
        () -> rtcpSocket.send(new RtcpCompoundPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))));
  }

  @Test
  void testSend2() throws IOException {
    // Arrange
    RtcpSocket rtcpSocket = new RtcpSocket(null);

    // Act and Assert
    assertThrows(IOException.class,
        () -> rtcpSocket.send(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))));
  }
}

