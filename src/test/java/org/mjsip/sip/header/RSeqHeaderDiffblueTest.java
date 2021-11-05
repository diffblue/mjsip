package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class RSeqHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    RSeqHeader actualRSeqHeader = new RSeqHeader("42");

    // Assert
    assertEquals(SipHeaders.RSeq, actualRSeqHeader.getName());
    assertEquals("RSeq: 42\r\n", actualRSeqHeader.toString());
    assertEquals("42", actualRSeqHeader.getValue());
    assertEquals(42L, actualRSeqHeader.getSequenceNumber());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    RSeqHeader actualRSeqHeader = new RSeqHeader(1L);

    // Assert
    assertEquals(SipHeaders.RSeq, actualRSeqHeader.getName());
    assertEquals("1", actualRSeqHeader.getValue());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    RSeqHeader actualRSeqHeader = new RSeqHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("Hname", actualRSeqHeader.getName());
    assertEquals("42", actualRSeqHeader.getValue());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    RSeqHeader actualRSeqHeader = new RSeqHeader(header);

    // Assert
    assertEquals("Hname", actualRSeqHeader.getName());
    assertEquals("42", actualRSeqHeader.getValue());
  }

  @Test
  void testGetSequenceNumber() {
    // Arrange, Act and Assert
    assertEquals(1L, (new RSeqHeader(1L)).getSequenceNumber());
  }

  @Test
  void testGetSequenceNumber2() {
    // Arrange
    RSeqHeader rSeqHeader = new RSeqHeader("Hvalue");
    rSeqHeader.setSequenceNumber(0L);

    // Act and Assert
    assertEquals(0L, rSeqHeader.getSequenceNumber());
  }

  @Test
  void testSetSequenceNumber() {
    // Arrange
    RSeqHeader rSeqHeader = new RSeqHeader(1L);

    // Act
    rSeqHeader.setSequenceNumber(1L);

    // Assert
    assertEquals("1", rSeqHeader.getValue());
  }

  @Test
  void testIncSequenceNumber() {
    // Arrange
    RSeqHeader rSeqHeader = new RSeqHeader(1L);

    // Act
    RSeqHeader actualIncSequenceNumberResult = rSeqHeader.incSequenceNumber();

    // Assert
    assertSame(rSeqHeader, actualIncSequenceNumberResult);
    assertEquals("2", actualIncSequenceNumberResult.getValue());
  }

  @Test
  void testIncSequenceNumber2() {
    // Arrange
    RSeqHeader rSeqHeader = new RSeqHeader("Hvalue");
    rSeqHeader.setSequenceNumber(0L);

    // Act
    RSeqHeader actualIncSequenceNumberResult = rSeqHeader.incSequenceNumber();

    // Assert
    assertSame(rSeqHeader, actualIncSequenceNumberResult);
    assertEquals("1", actualIncSequenceNumberResult.getValue());
  }
}

