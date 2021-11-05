package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class RtpPacketDiffblueTest {
  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    RtpPacket actualRtpPacket = new RtpPacket("AAAAAAAA".getBytes("UTF-8"), 3);

    // Assert
    byte[] expectedPacketBuffer = actualRtpPacket.buffer;
    assertSame(expectedPacketBuffer, actualRtpPacket.getPacketBuffer());
    assertEquals(3, actualRtpPacket.getPacketLength());
    assertEquals(0, actualRtpPacket.getPacketOffset());
  }

  @Test
  void testConstructor2() throws UnsupportedEncodingException {
    // Arrange and Act
    RtpPacket actualRtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(0, actualRtpPacket.getCsrcCount());
    assertFalse(actualRtpPacket.hasMarker());
    assertEquals(10L, actualRtpPacket.getTimestamp());
    assertEquals(1L, actualRtpPacket.getSsrc());
    assertEquals(10, actualRtpPacket.getSequenceNumber());
    assertEquals(3, actualRtpPacket.getPayload().length);
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(15, actualRtpPacket.getPacketLength());
    assertEquals(15, actualRtpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor3() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new RtpPacket(1, 1L, 10, 10L, new byte[]{}, 1, 3));

  }

  @Test
  void testConstructor4() throws UnsupportedEncodingException {
    // Arrange and Act
    RtpPacket actualRtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAA".getBytes("UTF-8"), 1, -1);

    // Assert
    assertEquals(0, actualRtpPacket.getCsrcCount());
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(11, actualRtpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor5() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class,
        () -> new RtpPacket(1, 1L, 10, 10L, "AAAAAAAA".getBytes("UTF-8"), 1, Integer.MIN_VALUE));

  }

  @Test
  void testConstructor6() throws UnsupportedEncodingException {
    // Arrange and Act
    RtpPacket actualRtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L}, "AAAAAAAA".getBytes("UTF-8"),
        1, 3);

    // Assert
    assertEquals(4, actualRtpPacket.getCsrcCount());
    assertFalse(actualRtpPacket.hasMarker());
    assertEquals(10L, actualRtpPacket.getTimestamp());
    assertEquals(1L, actualRtpPacket.getSsrc());
    assertEquals(10, actualRtpPacket.getSequenceNumber());
    assertEquals(3, actualRtpPacket.getPayload().length);
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(31, actualRtpPacket.getPacketLength());
    assertEquals(31, actualRtpPacket.getPacketBuffer().length);
    assertEquals(4, actualRtpPacket.getCsrcList().length);
  }

  @Test
  void testConstructor7() throws UnsupportedEncodingException {
    // Arrange and Act
    RtpPacket actualRtpPacket = new RtpPacket(1, 1L, 10, 10L, null, "AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(0, actualRtpPacket.getCsrcCount());
    assertFalse(actualRtpPacket.hasMarker());
    assertEquals(10L, actualRtpPacket.getTimestamp());
    assertEquals(1L, actualRtpPacket.getSsrc());
    assertEquals(10, actualRtpPacket.getSequenceNumber());
    assertEquals(3, actualRtpPacket.getPayload().length);
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(15, actualRtpPacket.getPacketLength());
    assertEquals(15, actualRtpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor8() throws UnsupportedEncodingException {
    // Arrange and Act
    RtpPacket actualRtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{}, "AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(0, actualRtpPacket.getCsrcCount());
    assertFalse(actualRtpPacket.hasMarker());
    assertEquals(10L, actualRtpPacket.getTimestamp());
    assertEquals(1L, actualRtpPacket.getSsrc());
    assertEquals(10, actualRtpPacket.getSequenceNumber());
    assertEquals(3, actualRtpPacket.getPayload().length);
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(15, actualRtpPacket.getPacketLength());
    assertEquals(15, actualRtpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor9() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L}, new byte[]{}, 1, 3));

  }

  @Test
  void testConstructor10() throws UnsupportedEncodingException {
    // Arrange and Act
    RtpPacket actualRtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L}, "AAAAAAAA".getBytes("UTF-8"),
        1, -1);

    // Assert
    assertEquals(0, actualRtpPacket.getCsrcCount());
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(27, actualRtpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor11() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L},
        "AAAAAAAA".getBytes("UTF-8"), 1, Integer.MIN_VALUE));

  }

  @Test
  void testConstructor12() throws UnsupportedEncodingException {
    // Arrange and Act
    RtpPacket actualRtpPacket = new RtpPacket(1, 1L, 10, 10L, null, "AAAAAAAA".getBytes("UTF-8"), 1, -1);

    // Assert
    assertEquals(0, actualRtpPacket.getCsrcCount());
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(11, actualRtpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor13() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act
    RtpPacket actualRtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(0, actualRtpPacket.getCsrcCount());
    assertFalse(actualRtpPacket.hasMarker());
    assertEquals(3, actualRtpPacket.getPayload().length);
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(15, actualRtpPacket.getPacketLength());
    assertEquals(15, actualRtpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor14() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1, 1L);

    // Act
    RtpPacket actualRtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(0, actualRtpPacket.getCsrcCount());
    assertFalse(actualRtpPacket.hasMarker());
    assertEquals(1L, actualRtpPacket.getSsrc());
    assertEquals(3, actualRtpPacket.getPayload().length);
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(15, actualRtpPacket.getPacketLength());
    assertEquals(15, actualRtpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor15() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1, 1L, 1, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L});

    // Act
    RtpPacket actualRtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(8, actualRtpPacket.getCsrcCount());
    assertFalse(actualRtpPacket.hasMarker());
    assertEquals(10L, actualRtpPacket.getTimestamp());
    assertEquals(1L, actualRtpPacket.getSsrc());
    assertEquals(1, actualRtpPacket.getSequenceNumber());
    assertEquals(3, actualRtpPacket.getPayload().length);
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(47, actualRtpPacket.getPacketLength());
    assertEquals(47, actualRtpPacket.getPacketBuffer().length);
    assertEquals(8, actualRtpPacket.getCsrcList().length);
  }

  @Test
  void testConstructor16() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act
    RtpPacket actualRtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAA".getBytes("UTF-8"), 1, -1);

    // Assert
    assertEquals(0, actualRtpPacket.getCsrcCount());
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(11, actualRtpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor17() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act and Assert
    assertThrows(NegativeArraySizeException.class,
        () -> new RtpPacket(rtp_ctx, "AAAAAAAA".getBytes("UTF-8"), 1, Integer.MIN_VALUE));

  }

  @Test
  void testConstructor18() throws UnsupportedEncodingException {
    // Arrange and Act
    RtpPacket actualRtpPacket = new RtpPacket("AAAAAAAA".getBytes("UTF-8"), 3);

    // Assert
    assertEquals(0, actualRtpPacket.getCsrcCount());
    assertEquals(0, actualRtpPacket.getPacketOffset());
    assertEquals(8, actualRtpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor19() throws UnsupportedEncodingException {
    // Arrange and Act
    RtpPacket actualRtpPacket = new RtpPacket("AAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Assert
    assertEquals(0, actualRtpPacket.getCsrcCount());
    assertEquals(2, actualRtpPacket.getPacketOffset());
    assertEquals(8, actualRtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPacketLength() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setPacketLength(3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPacketLength2() {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(new byte[]{}, 3);

    // Act
    rtpPacket.setPacketLength(3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(3, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPacketLength3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setPacketLength(3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPacketLength4() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPacketLength(3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPacketLength5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPacketLength(3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPacketLength6() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPacketLength(3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPacketLength7() {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(new byte[]{}, 0, 3);

    // Act
    rtpPacket.setPacketLength(3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(3, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPacketLength8() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> (new RtpPacket(new byte[]{}, 2, 3)).setPacketLength(-1));
  }

  @Test
  void testGetVersion() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).getVersion());
    assertEquals(1, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).getVersion());
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).getVersion());
    assertEquals(2, (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getVersion());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).getVersion());
    assertEquals(2, (new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getVersion());
  }

  @Test
  void testGetVersion2() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act and Assert
    assertEquals(2, (new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getVersion());
  }

  @Test
  void testSetVersion() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setVersion(1);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetVersion2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setVersion(1);

    // Assert
    assertEquals(1, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetVersion3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setVersion(1);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetVersion4() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setVersion(1);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetVersion5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setVersion(1);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetVersion6() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).setVersion(1));
  }

  @Test
  void testSetVersion7() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setVersion(1);

    // Assert
    assertEquals(8, rtpPacket.getCsrcCount());
  }

  @Test
  void testGetPaddingLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).getPaddingLength());
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).getPaddingLength());
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).getPaddingLength());
    assertEquals(0,
        (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getPaddingLength());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).getPaddingLength());
    assertEquals(0, (new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getPaddingLength());
  }

  @Test
  void testGetPaddingLength2() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act and Assert
    assertEquals(0, (new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getPaddingLength());
  }

  @Test
  void testSetPaddingLength() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setPaddingLength(3);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPaddingLength2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setPaddingLength(3);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPaddingLength3() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).setPaddingLength(3));
  }

  @Test
  void testSetPaddingLength4() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).setPaddingLength(3));
  }

  @Test
  void testSetPaddingLength5() {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(new byte[]{}, 0);

    // Act
    rtpPacket.setPaddingLength(3);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(0, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPaddingLength6() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).setPaddingLength(0));
  }

  @Test
  void testSetPaddingLength7() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
            "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).setPaddingLength(3));
  }

  @Test
  void testHasExtension() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse((new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).hasExtension());
    assertFalse((new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).hasExtension());
    assertFalse((new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).hasExtension());
    assertFalse((new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).hasExtension());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).hasExtension());
    assertFalse((new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).hasExtension());
  }

  @Test
  void testHasExtension2() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act and Assert
    assertFalse((new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).hasExtension());
  }

  @Test
  void testSetExtension() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setExtension(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetExtension2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setExtension(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(12, rtpPacket.getPacketLength());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetExtension3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setExtension(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetExtension4() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setExtension(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(15, rtpPacket.getPacketLength());
    assertEquals(15, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetExtension5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setExtension(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(15, rtpPacket.getPacketLength());
    assertEquals(15, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetExtension6() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).setExtension(true));
  }

  @Test
  void testSetExtension7() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setExtension(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(47, rtpPacket.getPacketLength());
    assertEquals(47, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testGetCsrcCount() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).getCsrcCount());
    assertEquals(1, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).getCsrcCount());
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).getCsrcCount());
    assertEquals(0, (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getCsrcCount());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).getCsrcCount());
    assertEquals(8, (new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getCsrcCount());
  }

  @Test
  void testGetCsrcCount2() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act and Assert
    assertEquals(0, (new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getCsrcCount());
  }

  @Test
  void testSetCsrcCount() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setCsrcCount(3);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetCsrcCount2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setCsrcCount(3);

    // Assert
    assertEquals(3, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetCsrcCount3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setCsrcCount(3);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetCsrcCount4() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setCsrcCount(3);

    // Assert
    assertEquals(3, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetCsrcCount5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setCsrcCount(3);

    // Assert
    assertEquals(3, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetCsrcCount6() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).setCsrcCount(3));
  }

  @Test
  void testSetCsrcCount7() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setCsrcCount(3);

    // Assert
    assertEquals(3, rtpPacket.getCsrcCount());
  }

  @Test
  void testHasMarker() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse((new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).hasMarker());
    assertFalse((new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).hasMarker());
    assertFalse((new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).hasMarker());
    assertFalse((new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).hasMarker());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).hasMarker());
    assertFalse((new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).hasMarker());
  }

  @Test
  void testHasMarker2() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act and Assert
    assertFalse((new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).hasMarker());
  }

  @Test
  void testSetMarker() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setMarker(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetMarker2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setMarker(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(12, rtpPacket.getPacketLength());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetMarker3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setMarker(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetMarker4() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setMarker(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(15, rtpPacket.getPacketLength());
    assertEquals(15, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetMarker5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setMarker(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(15, rtpPacket.getPacketLength());
    assertEquals(15, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetMarker6() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).setMarker(true));
  }

  @Test
  void testSetMarker7() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setMarker(true);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(47, rtpPacket.getPacketLength());
    assertEquals(47, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testGetPayloadType() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(-1, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).getPayloadType());
    assertEquals(65, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).getPayloadType());
    assertEquals(-1, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).getPayloadType());
    assertEquals(1,
        (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getPayloadType());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).getPayloadType());
    assertEquals(1, (new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getPayloadType());
  }

  @Test
  void testGetPayloadType2() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act and Assert
    assertEquals(1, (new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getPayloadType());
  }

  @Test
  void testSetPayloadType() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setPayloadType(1);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPayloadType2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setPayloadType(1);

    // Assert
    assertFalse(rtpPacket.hasMarker());
  }

  @Test
  void testSetPayloadType3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setPayloadType(1);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPayloadType4() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPayloadType(1);

    // Assert
    assertFalse(rtpPacket.hasMarker());
  }

  @Test
  void testSetPayloadType5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPayloadType(1);

    // Assert
    assertFalse(rtpPacket.hasMarker());
  }

  @Test
  void testSetPayloadType6() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).setPayloadType(1));
  }

  @Test
  void testSetPayloadType7() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPayloadType(1);

    // Assert
    assertFalse(rtpPacket.hasMarker());
  }

  @Test
  void testGetSequenceNumber() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).getSequenceNumber());
    assertEquals(16705, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).getSequenceNumber());
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).getSequenceNumber());
    assertEquals(10,
        (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getSequenceNumber());
    assertEquals(0, (new RtpPacket(new byte[]{}, 0)).getSequenceNumber());
    assertEquals(10, (new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getSequenceNumber());
  }

  @Test
  void testSetSequenceNumber() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setSequenceNumber(1);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetSequenceNumber2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setSequenceNumber(1);

    // Assert
    assertEquals(1, rtpPacket.getSequenceNumber());
  }

  @Test
  void testSetSequenceNumber3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setSequenceNumber(1);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetSequenceNumber4() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setSequenceNumber(1);

    // Assert
    assertEquals(1, rtpPacket.getSequenceNumber());
  }

  @Test
  void testSetSequenceNumber5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setSequenceNumber(1);

    // Assert
    assertEquals(1, rtpPacket.getSequenceNumber());
  }

  @Test
  void testSetSequenceNumber6() {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(new byte[]{}, 0);

    // Act
    rtpPacket.setSequenceNumber(1);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(0, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetSequenceNumber7() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setSequenceNumber(1);

    // Assert
    assertEquals(1, rtpPacket.getSequenceNumber());
  }

  @Test
  void testGetTimestamp() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0L, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).getTimestamp());
    assertEquals(1094795585L, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).getTimestamp());
    assertEquals(0L, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).getTimestamp());
    assertEquals(10L,
        (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getTimestamp());
    assertEquals(0L, (new RtpPacket(new byte[]{}, 0)).getTimestamp());
    assertEquals(10L, (new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getTimestamp());
  }

  @Test
  void testSetTimestamp() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setTimestamp(10L);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetTimestamp2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setTimestamp(10L);

    // Assert
    assertEquals(10L, rtpPacket.getTimestamp());
  }

  @Test
  void testSetTimestamp3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setTimestamp(10L);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetTimestamp4() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setTimestamp(10L);

    // Assert
    assertEquals(10L, rtpPacket.getTimestamp());
  }

  @Test
  void testSetTimestamp5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setTimestamp(10L);

    // Assert
    assertEquals(10L, rtpPacket.getTimestamp());
  }

  @Test
  void testSetTimestamp6() {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(new byte[]{}, 0);

    // Act
    rtpPacket.setTimestamp(10L);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(0, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetTimestamp7() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setTimestamp(10L);

    // Assert
    assertEquals(10L, rtpPacket.getTimestamp());
  }

  @Test
  void testGetSsrc() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0L, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).getSsrc());
    assertEquals(1094795585L, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).getSsrc());
    assertEquals(0L, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).getSsrc());
    assertEquals(1L, (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getSsrc());
    assertEquals(0L, (new RtpPacket("AAAAAAAA".getBytes("UTF-8"), 0)).getSsrc());
  }

  @Test
  void testSetSsrc() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setSsrc(1L);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetSsrc2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setSsrc(1L);

    // Assert
    assertEquals(1L, rtpPacket.getSsrc());
  }

  @Test
  void testSetSsrc3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setSsrc(1L);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetSsrc4() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setSsrc(1L);

    // Assert
    assertEquals(1L, rtpPacket.getSsrc());
  }

  @Test
  void testSetSsrc5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setSsrc(1L);

    // Assert
    assertEquals(1L, rtpPacket.getSsrc());
  }

  @Test
  void testSetSsrc6() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAA".getBytes("UTF-8"), 0);

    // Act
    rtpPacket.setSsrc(1L);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(8, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testGetCsrcList() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).getCsrcList());
    assertNull((new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).getCsrcList());
    assertEquals(0,
        (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getCsrcList().length);
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).getCsrcList());
  }

  @Test
  void testGetCsrcList2() throws UnsupportedEncodingException {
    // Arrange and Act
    long[] actualCsrcList = (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).getCsrcList();

    // Assert
    assertEquals(1, actualCsrcList.length);
    assertEquals(16705L, actualCsrcList[0]);
  }

  @Test
  void testGetCsrcList3() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act and Assert
    assertEquals(0, (new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getCsrcList().length);
  }

  @Test
  void testGetCsrcList4() {
    // Arrange and Act
    long[] actualCsrcList = (new RtpPacket(new byte[]{1, 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 12)).getCsrcList();

    // Assert
    assertEquals(1, actualCsrcList.length);
    assertEquals(16705L, actualCsrcList[0]);
  }

  @Test
  void testSetCsrcList() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setCsrcList(new long[]{1L, 1L, 1L, 1L});

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetCsrcList2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setCsrcList(new long[]{1L, 1L, 1L, 1L});

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(12, rtpPacket.getPacketLength());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetCsrcList3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setCsrcList(new long[]{1L, 1L, 1L, 1L});

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetCsrcList4() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setCsrcList(new long[]{1L, 1L, 1L, 1L});

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(15, rtpPacket.getPacketLength());
    assertEquals(15, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetCsrcList5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setCsrcList(new long[]{1L, 1L, 1L, 1L});

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(15, rtpPacket.getPacketLength());
    assertEquals(15, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetCsrcList6() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setCsrcList(null);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetCsrcList7() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setCsrcList(new long[]{1L, 1L, 1L, 1L});

    // Assert
    assertEquals(4, rtpPacket.getCsrcCount());
    assertEquals(4, rtpPacket.getCsrcList().length);
  }

  @Test
  void testSetHeader() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetHeader2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L);

    // Assert
    assertEquals(1, rtpPacket.getCsrcCount());
    assertFalse(rtpPacket.hasMarker());
    assertEquals(10L, rtpPacket.getTimestamp());
    assertEquals(1L, rtpPacket.getSsrc());
    assertEquals(10, rtpPacket.getSequenceNumber());
  }

  @Test
  void testSetHeader3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetHeader4() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertFalse(rtpPacket.hasMarker());
    assertEquals(10L, rtpPacket.getTimestamp());
    assertEquals(1L, rtpPacket.getSsrc());
    assertEquals(10, rtpPacket.getSequenceNumber());
  }

  @Test
  void testSetHeader5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertFalse(rtpPacket.hasMarker());
    assertEquals(10L, rtpPacket.getTimestamp());
    assertEquals(1L, rtpPacket.getSsrc());
    assertEquals(10, rtpPacket.getSequenceNumber());
  }

  @Test
  void testSetHeader6() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L);

    // Assert
    assertEquals(8, rtpPacket.getCsrcCount());
    assertFalse(rtpPacket.hasMarker());
    assertEquals(10L, rtpPacket.getTimestamp());
    assertEquals(1L, rtpPacket.getSsrc());
    assertEquals(10, rtpPacket.getSequenceNumber());
  }

  @Test
  void testSetHeader7() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAA".getBytes("UTF-8"), 0);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L);

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(8, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetHeader8() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L});

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetHeader9() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L});

    // Assert
    assertEquals(1, rtpPacket.getCsrcCount());
    assertFalse(rtpPacket.hasMarker());
    assertEquals(10L, rtpPacket.getTimestamp());
    assertEquals(1L, rtpPacket.getSsrc());
    assertEquals(10, rtpPacket.getSequenceNumber());
  }

  @Test
  void testSetHeader10() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L});

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetHeader11() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L});

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertFalse(rtpPacket.hasMarker());
    assertEquals(10L, rtpPacket.getTimestamp());
    assertEquals(1L, rtpPacket.getSsrc());
    assertEquals(10, rtpPacket.getSequenceNumber());
  }

  @Test
  void testSetHeader12() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L});

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertFalse(rtpPacket.hasMarker());
    assertEquals(10L, rtpPacket.getTimestamp());
    assertEquals(1L, rtpPacket.getSsrc());
    assertEquals(10, rtpPacket.getSequenceNumber());
  }

  @Test
  void testSetHeader13() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L, new long[]{});

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetHeader14() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L});

    // Assert
    assertEquals(4, rtpPacket.getCsrcCount());
    assertFalse(rtpPacket.hasMarker());
    assertEquals(10L, rtpPacket.getTimestamp());
    assertEquals(1L, rtpPacket.getSsrc());
    assertEquals(10, rtpPacket.getSequenceNumber());
    assertEquals(4, rtpPacket.getCsrcList().length);
  }

  @Test
  void testSetHeader15() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAA".getBytes("UTF-8"), 0);

    // Act
    rtpPacket.setHeader(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L});

    // Assert that nothing has changed
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(8, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetHeader16() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);
    RtpContext rtpContext = new RtpContext(1);

    // Act
    rtpPacket.setHeader(rtpContext);

    // Assert that nothing has changed
    assertEquals(0, rtpContext.getCC());
    assertEquals(1, rtpContext.getPayloadType());
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetHeader17() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setHeader(new RtpContext(1));

    // Assert
    assertEquals(1, rtpPacket.getCsrcCount());
    assertFalse(rtpPacket.hasMarker());
  }

  @Test
  void testSetHeader18() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);
    RtpContext rtpContext = new RtpContext(1);

    // Act
    rtpPacket.setHeader(rtpContext);

    // Assert that nothing has changed
    assertEquals(0, rtpContext.getCC());
    assertEquals(1, rtpContext.getPayloadType());
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(2, rtpPacket.getPacketOffset());
    assertEquals(24, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetHeader19() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setHeader(new RtpContext(1));

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertFalse(rtpPacket.hasMarker());
  }

  @Test
  void testSetHeader20() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setHeader(new RtpContext(1));

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertFalse(rtpPacket.hasMarker());
  }

  @Test
  void testGetHeaderLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(3, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).getHeaderLength());
    assertEquals(Short.SIZE, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).getHeaderLength());
    assertEquals(3, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).getHeaderLength());
    assertEquals(12,
        (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getHeaderLength());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).getHeaderLength());
    assertEquals(44, (new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getHeaderLength());
  }

  @Test
  void testGetHeaderLength2() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act and Assert
    assertEquals(12, (new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getHeaderLength());
  }

  @Test
  void testSetPayload() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPayload2() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(11, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPayload3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(8, rtpPacket.getPayload().length);
    assertEquals(24, rtpPacket.getPacketLength());
  }

  @Test
  void testSetPayload4() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), -1);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testSetPayload5() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPayload6() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(8, rtpPacket.getPayload().length);
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(20, rtpPacket.getPacketLength());
    assertEquals(20, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPayload7() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(8, rtpPacket.getPayload().length);
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(20, rtpPacket.getPacketLength());
    assertEquals(20, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPayload8() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPayload9() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(new byte[]{}, 3);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(6, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPayload10() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(3, rtpPacket.getPayload().length);
    assertEquals(19, rtpPacket.getPacketLength());
  }

  @Test
  void testSetPayload11() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPayload12() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(3, rtpPacket.getPayload().length);
    assertEquals(15, rtpPacket.getPacketLength());
  }

  @Test
  void testSetPayload13() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(3, rtpPacket.getPayload().length);
    assertEquals(15, rtpPacket.getPacketLength());
  }

  @Test
  void testSetPayload14() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), -1);

    // Act
    rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"), 1, 0);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPayload15() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), -1);

    // Act and Assert
    assertThrows(OutOfMemoryError.class,
        () -> rtpPacket.setPayload("AAAAAAAA".getBytes("UTF-8"), 1, Integer.MIN_VALUE));
  }

  @Test
  void testGetPayload() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).getPayload().length);
    assertThrows(NegativeArraySizeException.class,
        () -> (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).getPayload());
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).getPayload().length);
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).getPayload());
  }

  @Test
  void testGetPayload2() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act
    byte[] actualPayload = (new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getPayload();

    // Assert
    assertEquals(3, actualPayload.length);
    assertEquals('A', actualPayload[0]);
    assertEquals('A', actualPayload[1]);
    assertEquals('A', actualPayload[2]);
  }

  @Test
  void testGetPayload3() throws UnsupportedEncodingException {
    // Arrange and Act
    byte[] actualPayload = (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3))
        .getPayload();

    // Assert
    assertEquals(3, actualPayload.length);
    assertEquals('A', actualPayload[0]);
    assertEquals('A', actualPayload[1]);
    assertEquals('A', actualPayload[2]);
  }

  @Test
  void testGetPayload4() throws UnsupportedEncodingException {
    // Arrange and Act
    byte[] actualPayload = (new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getPayload();

    // Assert
    assertEquals(3, actualPayload.length);
    assertEquals('A', actualPayload[0]);
    assertEquals('A', actualPayload[1]);
    assertEquals('A', actualPayload[2]);
  }

  @Test
  void testGetPayloadLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)).getPayloadLength());
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12)).getPayloadLength());
    assertEquals(0, (new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3)).getPayloadLength());
    assertEquals(3,
        (new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getPayloadLength());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new RtpPacket(new byte[]{}, 12)).getPayloadLength());
    assertEquals(3, (new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getPayloadLength());
  }

  @Test
  void testGetPayloadLength2() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);

    // Act and Assert
    assertEquals(3, (new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)).getPayloadLength());
  }

  @Test
  void testSetPayloadLength() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3);

    // Act
    rtpPacket.setPayloadLength(3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPayloadLength2() {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(new byte[]{}, 3);

    // Act
    rtpPacket.setPayloadLength(3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(6, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPayloadLength3() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 12);

    // Act
    rtpPacket.setPayloadLength(3);

    // Assert
    assertEquals(19, rtpPacket.getPacketLength());
  }

  @Test
  void testSetPayloadLength4() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act
    rtpPacket.setPayloadLength(3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
  }

  @Test
  void testSetPayloadLength5() throws UnsupportedEncodingException {
    // Arrange
    RtpContext rtp_ctx = new RtpContext(1);
    RtpPacket rtpPacket = new RtpPacket(rtp_ctx, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPayloadLength(3);

    // Assert
    assertEquals(15, rtpPacket.getPacketLength());
  }

  @Test
  void testSetPayloadLength6() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPayloadLength(3);

    // Assert
    assertEquals(15, rtpPacket.getPacketLength());
  }

  @Test
  void testSetPayloadLength7() {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(new byte[]{}, 6);

    // Act
    rtpPacket.setPayloadLength(3);

    // Assert
    assertEquals(0, rtpPacket.getCsrcCount());
    assertEquals(0, rtpPacket.getPacketOffset());
    assertEquals(9, rtpPacket.getPacketBuffer().length);
  }

  @Test
  void testSetPayloadLength8() throws UnsupportedEncodingException {
    // Arrange
    RtpPacket rtpPacket = new RtpPacket(1, 1L, 10, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Act
    rtpPacket.setPayloadLength(3);

    // Assert
    assertEquals(47, rtpPacket.getPacketLength());
  }

  @Test
  void testGetLong() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(16705L, RtpPacket.getLong("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    assertEquals(0L, RtpPacket.getLong(new byte[]{}, 3, 3));
  }

  @Test
  void testGetBit() {
    // Arrange, Act and Assert
    assertFalse(RtpPacket.getBit((byte) 'A', 1));
    assertTrue(RtpPacket.getBit((byte) 'A', 6));
  }
}

