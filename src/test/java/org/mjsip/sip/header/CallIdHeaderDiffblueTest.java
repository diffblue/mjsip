package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CallIdHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    CallIdHeader actualCallIdHeader = new CallIdHeader("42");
    actualCallIdHeader.setCallId("42");

    // Assert
    assertEquals("42", actualCallIdHeader.getValue());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    CallIdHeader actualCallIdHeader = new CallIdHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualCallIdHeader.getValue());
    assertEquals("Hname", actualCallIdHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    CallIdHeader actualCallIdHeader = new CallIdHeader(header);

    // Assert
    assertEquals("42", actualCallIdHeader.getValue());
    assertEquals("Hname", actualCallIdHeader.getName());
  }

  @Test
  void testGetCallId() {
    // Arrange, Act and Assert
    assertEquals("42", (new CallIdHeader("42")).getCallId());
    assertEquals("", (new CallIdHeader("")).getCallId());
  }

  @Test
  void testGetCallId2() {
    // Arrange
    CallIdHeader callIdHeader = new CallIdHeader(new Header());
    callIdHeader.setCallId("42");

    // Act and Assert
    assertEquals("42", callIdHeader.getCallId());
  }

  @Test
  void testGetCallId3() {
    // Arrange
    CallIdHeader callIdHeader = new CallIdHeader(new Header());
    callIdHeader.setCallId("Call Id");

    // Act and Assert
    assertEquals("Call", callIdHeader.getCallId());
  }
}

