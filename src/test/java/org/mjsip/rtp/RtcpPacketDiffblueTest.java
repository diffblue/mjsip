package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class RtcpPacketDiffblueTest {
  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    RtcpPacket actualRtcpPacket = new RtcpPacket("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    byte[] expectedPacketBuffer = actualRtcpPacket.buffer;
    assertSame(expectedPacketBuffer, actualRtcpPacket.getPacketBuffer());
    assertEquals(0, actualRtcpPacket.getPacketOffset());
  }

  @Test
  void testConstructor2() throws UnsupportedEncodingException {
    // Arrange and Act
    RtcpPacket actualRtcpPacket = new RtcpPacket("AAAAAAAA".getBytes("UTF-8"), 2);

    // Assert
    byte[] expectedPacketBuffer = actualRtcpPacket.buffer;
    assertSame(expectedPacketBuffer, actualRtcpPacket.getPacketBuffer());
    assertEquals(2, actualRtcpPacket.getPacketOffset());
  }

  @Test
  void testGetVersion() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(1, (new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))).getVersion());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtcpPacket(new byte[]{})).getVersion());
    assertEquals(1, (new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2)).getVersion());
  }

  @Test
  void testSetVersion() throws UnsupportedEncodingException {
    // Arrange
    RtcpPacket rtcpPacket = new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    rtcpPacket.setVersion(1);

    // Assert
    assertEquals(1, rtcpPacket.getVersion());
  }

  @Test
  void testSetVersion2() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtcpPacket(new byte[]{})).setVersion(1));
  }

  @Test
  void testSetVersion3() throws UnsupportedEncodingException {
    // Arrange
    RtcpPacket rtcpPacket = new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2);

    // Act
    rtcpPacket.setVersion(1);

    // Assert
    assertEquals(1, rtcpPacket.getVersion());
  }

  @Test
  void testGetPaddingLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))).getPaddingLength());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtcpPacket(new byte[]{})).getPaddingLength());
    assertEquals(0, (new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2)).getPaddingLength());
  }

  @Test
  void testSetPaddingLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))).setPaddingLength(3));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2)).setPaddingLength(3));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtcpPacket(new byte[]{})).setPaddingLength(0));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtcpPacket(new byte[]{})).setPaddingLength(-1));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new RtcpPacket("AAAAAAAA".getBytes("UTF-8"))).setPaddingLength(1));
  }

  @Test
  void testSetPaddingLength2() throws UnsupportedEncodingException {
    // Arrange
    RtcpPacket rtcpPacket = new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    rtcpPacket.setPaddingLength(0);

    // Assert that nothing has changed
    assertEquals(24, rtcpPacket.getPacketBuffer().length);
    assertEquals(0, rtcpPacket.getPacketOffset());
  }

  @Test
  void testGetPayloadType() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(65, (new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))).getPayloadType());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtcpPacket(new byte[]{})).getPayloadType());
    assertEquals(65, (new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2)).getPayloadType());
  }

  @Test
  void testSetPayloadType() throws UnsupportedEncodingException {
    // Arrange
    RtcpPacket rtcpPacket = new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    rtcpPacket.setPayloadType(1);

    // Assert
    assertEquals(1, rtcpPacket.getPayloadType());
  }

  @Test
  void testSetPayloadType2() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtcpPacket(new byte[]{})).setPayloadType(1));
  }

  @Test
  void testSetPayloadType3() throws UnsupportedEncodingException {
    // Arrange
    RtcpPacket rtcpPacket = new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2);

    // Act
    rtcpPacket.setPayloadType(1);

    // Assert
    assertEquals(1, rtcpPacket.getPayloadType());
  }

  @Test
  void testGetPacketLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(66824, (new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))).getPacketLength());
    assertEquals(66824, (new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2)).getPacketLength());
  }

  @Test
  void testSetPacketLength() throws UnsupportedEncodingException {
    // Arrange
    RtcpPacket rtcpPacket = new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    rtcpPacket.setPacketLength(3);

    // Assert
    assertEquals(262144, rtcpPacket.getPacketLength());
  }

  @Test
  void testSetPacketLength2() throws UnsupportedEncodingException {
    // Arrange
    RtcpPacket rtcpPacket = new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2);

    // Act
    rtcpPacket.setPacketLength(3);

    // Assert
    assertEquals(3, rtcpPacket.getVersion());
    assertEquals(255, rtcpPacket.getPayloadType());
  }
}

