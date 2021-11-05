package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class RtpContextDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1);
    actualRtpContext.incTimestamp(2L);
    actualRtpContext.setSequenceNumber(1);
    actualRtpContext.setTimestamp(10L);

    // Assert
    assertNull(actualRtpContext.getCsrc());
    assertEquals(1, actualRtpContext.getPayloadType());
    assertEquals(1, actualRtpContext.getSequenceNumber());
    assertEquals(10L, actualRtpContext.getTimestamp());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1);

    // Assert
    assertEquals(0, actualRtpContext.getCC());
    assertEquals(1, actualRtpContext.getPayloadType());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1, 1L);

    // Assert
    assertEquals(0, actualRtpContext.getCC());
    assertEquals(1L, actualRtpContext.getSsrc());
    assertEquals(1, actualRtpContext.getPayloadType());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1, -1L);

    // Assert
    assertEquals(0, actualRtpContext.getCC());
    assertEquals(1, actualRtpContext.getPayloadType());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1, 1L, 1, 10L);

    // Assert
    assertEquals(0, actualRtpContext.getCC());
    assertEquals(10L, actualRtpContext.getTimestamp());
    assertEquals(1L, actualRtpContext.getSsrc());
    assertEquals(1, actualRtpContext.getSequenceNumber());
    assertEquals(1, actualRtpContext.getPayloadType());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1, -1L, 1, 10L);

    // Assert
    assertEquals(0, actualRtpContext.getCC());
    assertEquals(10L, actualRtpContext.getTimestamp());
    assertEquals(1, actualRtpContext.getSequenceNumber());
    assertEquals(1, actualRtpContext.getPayloadType());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1, 1L, -1, 10L);

    // Assert
    assertEquals(0, actualRtpContext.getCC());
    assertEquals(10L, actualRtpContext.getTimestamp());
    assertEquals(1L, actualRtpContext.getSsrc());
    assertEquals(1, actualRtpContext.getPayloadType());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1, 1L, 1, -1L);

    // Assert
    assertEquals(0, actualRtpContext.getCC());
    assertEquals(1L, actualRtpContext.getSsrc());
    assertEquals(1, actualRtpContext.getSequenceNumber());
    assertEquals(1, actualRtpContext.getPayloadType());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1, 1L, 1, 10L, new long[]{1L, 1L, 1L, 1L});

    // Assert
    assertEquals(4, actualRtpContext.getCC());
    assertEquals(10L, actualRtpContext.getTimestamp());
    assertEquals(1L, actualRtpContext.getSsrc());
    assertEquals(1, actualRtpContext.getSequenceNumber());
    assertEquals(1, actualRtpContext.getPayloadType());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1, -1L, 1, 10L, new long[]{1L, 1L, 1L, 1L});

    // Assert
    assertEquals(4, actualRtpContext.getCC());
    assertEquals(10L, actualRtpContext.getTimestamp());
    assertEquals(1, actualRtpContext.getSequenceNumber());
    assertEquals(1, actualRtpContext.getPayloadType());
  }

  @Test
  void testConstructor11() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1, 1L, -1, 10L, new long[]{1L, 1L, 1L, 1L});

    // Assert
    assertEquals(4, actualRtpContext.getCC());
    assertEquals(10L, actualRtpContext.getTimestamp());
    assertEquals(1L, actualRtpContext.getSsrc());
    assertEquals(1, actualRtpContext.getPayloadType());
  }

  @Test
  void testConstructor12() {
    // Arrange and Act
    RtpContext actualRtpContext = new RtpContext(1, 1L, 1, -1L, new long[]{1L, 1L, 1L, 1L});

    // Assert
    assertEquals(4, actualRtpContext.getCC());
    assertEquals(1L, actualRtpContext.getSsrc());
    assertEquals(1, actualRtpContext.getSequenceNumber());
    assertEquals(1, actualRtpContext.getPayloadType());
  }

  @Test
  void testGetCC() {
    // Arrange, Act and Assert
    assertEquals(0, (new RtpContext(1)).getCC());
    assertEquals(0, (new RtpContext(0)).getCC());
    assertEquals(0, (new RtpContext(1, 1L)).getCC());
    assertEquals(0, (new RtpContext(1, 1L, 1, 10L)).getCC());
    assertEquals(8, (new RtpContext(1, 1L, 1, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L})).getCC());
  }

  @Test
  void testGetCC2() {
    // Arrange
    RtpContext rtpContext = new RtpContext(1);
    rtpContext.setSequenceNumber(0);

    // Act and Assert
    assertEquals(0, rtpContext.getCC());
  }

  @Test
  void testGetCC3() {
    // Arrange
    RtpContext rtpContext = new RtpContext(1);
    rtpContext.setTimestamp(10L);

    // Act and Assert
    assertEquals(0, rtpContext.getCC());
  }

  @Test
  void testGetCC4() {
    // Arrange
    RtpContext rtpContext = new RtpContext(1);
    rtpContext.incTimestamp(2L);

    // Act and Assert
    assertEquals(0, rtpContext.getCC());
  }

  @Test
  void testIncSequenceNumber() {
    // Arrange
    RtpContext rtpContext = new RtpContext(1, 1L, 1, 10L);

    // Act and Assert
    assertEquals(2, rtpContext.incSequenceNumber());
    assertEquals(2, rtpContext.getSequenceNumber());
  }

  @Test
  void testIncSequenceNumber2() {
    // Arrange
    RtpContext rtpContext = new RtpContext(1, 1L, 1, 10L, new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L});

    // Act and Assert
    assertEquals(2, rtpContext.incSequenceNumber());
    assertEquals(2, rtpContext.getSequenceNumber());
  }

  @Test
  void testIncSequenceNumber3() {
    // Arrange
    RtpContext rtpContext = new RtpContext(1);
    rtpContext.setSequenceNumber(0);

    // Act and Assert
    assertEquals(1, rtpContext.incSequenceNumber());
    assertEquals(1, rtpContext.getSequenceNumber());
  }
}

