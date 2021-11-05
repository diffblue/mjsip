package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class RtcpCompoundPacketDiffblueTest {
  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    RtcpCompoundPacket actualRtcpCompoundPacket = new RtcpCompoundPacket("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    byte[] expectedPacketBuffer = actualRtcpCompoundPacket.buffer;
    assertSame(expectedPacketBuffer, actualRtcpCompoundPacket.getPacketBuffer());
    assertEquals(0, actualRtcpCompoundPacket.getPacketLength());
    assertEquals(0, actualRtcpCompoundPacket.getPacketOffset());
  }

  @Test
  void testConstructor2() throws UnsupportedEncodingException {
    // Arrange and Act
    RtcpCompoundPacket actualRtcpCompoundPacket = new RtcpCompoundPacket("AAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Assert
    byte[] expectedPacketBuffer = actualRtcpCompoundPacket.buffer;
    assertSame(expectedPacketBuffer, actualRtcpCompoundPacket.getPacketBuffer());
    assertEquals(3, actualRtcpCompoundPacket.getPacketLength());
    assertEquals(2, actualRtcpCompoundPacket.getPacketOffset());
  }

  @Test
  void testConstructor3() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> new RtcpCompoundPacket(new RtcpPacket[]{new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))}));
  }

  @Test
  void testConstructor4() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new RtcpCompoundPacket(
        new RtcpPacket[]{new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2)}));
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    RtcpCompoundPacket actualRtcpCompoundPacket = new RtcpCompoundPacket(new RtcpPacket[]{});

    // Assert
    assertEquals(0, actualRtcpCompoundPacket.getPacketBuffer().length);
    assertEquals(0, actualRtcpCompoundPacket.getPacketOffset());
    assertEquals(0, actualRtcpCompoundPacket.getPacketLength());
  }

  @Test
  void testGetRtcpPackets() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new RtcpCompoundPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))).getRtcpPackets().length);
  }
}

