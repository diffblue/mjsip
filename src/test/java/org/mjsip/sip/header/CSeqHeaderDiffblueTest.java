package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class CSeqHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    CSeqHeader actualCSeqHeader = new CSeqHeader("42");

    // Assert
    assertEquals("", actualCSeqHeader.getMethod());
    assertEquals("CSeq: 42\r\n", actualCSeqHeader.toString());
    assertEquals("42", actualCSeqHeader.getValue());
    assertEquals(42L, actualCSeqHeader.getSequenceNumber());
    assertEquals(CoreSipHeaders.CSeq, actualCSeqHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    CSeqHeader actualCSeqHeader = new CSeqHeader(1L, "Method");

    // Assert
    assertEquals("1 Method", actualCSeqHeader.getValue());
    assertEquals(CoreSipHeaders.CSeq, actualCSeqHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    CSeqHeader actualCSeqHeader = new CSeqHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualCSeqHeader.getValue());
    assertEquals("Hname", actualCSeqHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    CSeqHeader actualCSeqHeader = new CSeqHeader(header);

    // Assert
    assertEquals("42", actualCSeqHeader.getValue());
    assertEquals("Hname", actualCSeqHeader.getName());
  }

  @Test
  void testGetMethod() {
    // Arrange, Act and Assert
    assertEquals("", (new CSeqHeader("42")).getMethod());
  }

  @Test
  void testGetSequenceNumber() {
    // Arrange, Act and Assert
    assertEquals(42L, (new CSeqHeader("42")).getSequenceNumber());
    assertEquals(1L, (new CSeqHeader(1L, "Method")).getSequenceNumber());
  }

  @Test
  void testSetMethod() {
    // Arrange
    CSeqHeader cSeqHeader = new CSeqHeader("42");

    // Act
    cSeqHeader.setMethod("Method");

    // Assert
    assertEquals("42 Method", cSeqHeader.getValue());
  }

  @Test
  void testSetMethod2() {
    // Arrange
    CSeqHeader cSeqHeader = new CSeqHeader(1L, "Method");

    // Act
    cSeqHeader.setMethod("Method");

    // Assert
    assertEquals("1 Method", cSeqHeader.getValue());
  }

  @Test
  void testSetSequenceNumber() {
    // Arrange
    CSeqHeader cSeqHeader = new CSeqHeader("42");

    // Act
    cSeqHeader.setSequenceNumber(1L);

    // Assert
    assertEquals("1 ", cSeqHeader.getValue());
  }

  @Test
  void testSetSequenceNumber2() {
    // Arrange
    CSeqHeader cSeqHeader = new CSeqHeader(new Header());
    cSeqHeader.setValue("42");

    // Act
    cSeqHeader.setSequenceNumber(1L);

    // Assert
    assertEquals("1 ", cSeqHeader.getValue());
  }

  @Test
  void testIncSequenceNumber() {
    // Arrange
    CSeqHeader cSeqHeader = new CSeqHeader("42");

    // Act
    CSeqHeader actualIncSequenceNumberResult = cSeqHeader.incSequenceNumber();

    // Assert
    assertSame(cSeqHeader, actualIncSequenceNumberResult);
    assertEquals("43 ", actualIncSequenceNumberResult.getValue());
  }

  @Test
  void testIncSequenceNumber2() {
    // Arrange
    CSeqHeader cSeqHeader = new CSeqHeader(1L, "Method");

    // Act
    CSeqHeader actualIncSequenceNumberResult = cSeqHeader.incSequenceNumber();

    // Assert
    assertSame(cSeqHeader, actualIncSequenceNumberResult);
    assertEquals("2 Method", actualIncSequenceNumberResult.getValue());
  }
}

