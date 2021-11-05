package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ReasonHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ReasonHeader actualReasonHeader = new ReasonHeader("42");

    // Assert
    assertEquals(SipHeaders.Reason, actualReasonHeader.getName());
    assertEquals("Reason: 42\r\n", actualReasonHeader.toString());
    assertFalse(actualReasonHeader.hasParameterText());
    assertFalse(actualReasonHeader.hasParameterCause());
    assertEquals("42", actualReasonHeader.getValue());
    assertEquals("42", actualReasonHeader.getToken());
    assertEquals("42", actualReasonHeader.getReason());
    assertNull(actualReasonHeader.getParameters());
    assertNull(actualReasonHeader.getParameterText());
    assertTrue(actualReasonHeader.getParameterNames().isEmpty());
    assertNull(actualReasonHeader.getParameterCause());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ReasonHeader actualReasonHeader = new ReasonHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("Hname", actualReasonHeader.getName());
    assertEquals("42", actualReasonHeader.getValue());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    ReasonHeader actualReasonHeader = new ReasonHeader(header);

    // Assert
    assertEquals("Hname", actualReasonHeader.getName());
    assertEquals("42", actualReasonHeader.getValue());
  }

  @Test
  void testGetReason() {
    // Arrange, Act and Assert
    assertEquals("42", (new ReasonHeader("42")).getReason());
    assertEquals("", (new ReasonHeader("")).getReason());
  }

  @Test
  void testGetReason2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals("42", (new ReasonHeader(header)).getReason());
  }

  @Test
  void testIsReasonProtocolSIP() {
    // Arrange, Act and Assert
    assertFalse((new ReasonHeader("42")).isReasonProtocolSIP());
    assertTrue((new ReasonHeader("SIP")).isReasonProtocolSIP());
    assertFalse((new ReasonHeader("")).isReasonProtocolSIP());
  }

  @Test
  void testIsReasonProtocolSIP2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReasonHeader(header)).isReasonProtocolSIP());
  }

  @Test
  void testIsReasonProtocolQ850() {
    // Arrange, Act and Assert
    assertFalse((new ReasonHeader("42")).isReasonProtocolQ850());
    assertTrue((new ReasonHeader("Q.850")).isReasonProtocolQ850());
    assertFalse((new ReasonHeader("")).isReasonProtocolQ850());
  }

  @Test
  void testIsReasonProtocolQ8502() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReasonHeader(header)).isReasonProtocolQ850());
  }

  @Test
  void testHasParameterCause() {
    // Arrange, Act and Assert
    assertFalse((new ReasonHeader("42")).hasParameterCause());
  }

  @Test
  void testHasParameterCause2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReasonHeader(header)).hasParameterCause());
  }

  @Test
  void testGetParameterCause() {
    // Arrange, Act and Assert
    assertNull((new ReasonHeader("42")).getParameterCause());
  }

  @Test
  void testGetParameterCause2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ReasonHeader(header)).getParameterCause());
  }

  @Test
  void testHasParameterText() {
    // Arrange, Act and Assert
    assertFalse((new ReasonHeader("42")).hasParameterText());
  }

  @Test
  void testHasParameterText2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReasonHeader(header)).hasParameterText());
  }

  @Test
  void testGetParameterText() {
    // Arrange, Act and Assert
    assertNull((new ReasonHeader("42")).getParameterText());
  }

  @Test
  void testGetParameterText2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ReasonHeader(header)).getParameterText());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new ReasonHeader("42")).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ReasonHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new ReasonHeader("42")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new ReasonHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new ReasonHeader("42")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ReasonHeader(header)).getParameters());
  }

  @Test
  void testGetToken() {
    // Arrange, Act and Assert
    assertEquals("42", (new ReasonHeader("42")).getToken());
    assertEquals("", (new ReasonHeader("")).getToken());
  }

  @Test
  void testGetToken2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals("42", (new ReasonHeader(header)).getToken());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new ReasonHeader("42")).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReasonHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new ReasonHeader("42")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReasonHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ReasonHeader("42")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new ReasonHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetParameter() {
    // Arrange
    ReasonHeader reasonHeader = new ReasonHeader("42");

    // Act
    reasonHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", reasonHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    ReasonHeader reasonHeader = new ReasonHeader(new Header());

    // Act
    reasonHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", reasonHeader.getValue());
  }

  @Test
  void testTokenEqualsTo() {
    // Arrange, Act and Assert
    assertFalse((new ReasonHeader("42")).tokenEqualsTo("ABC123"));
    assertTrue((new ReasonHeader("ABC123")).tokenEqualsTo("ABC123"));
    assertFalse((new ReasonHeader("")).tokenEqualsTo("ABC123"));
  }

  @Test
  void testTokenEqualsTo2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReasonHeader(header)).tokenEqualsTo("ABC123"));
  }
}

