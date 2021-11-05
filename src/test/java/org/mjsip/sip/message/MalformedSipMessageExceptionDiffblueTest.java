package org.mjsip.sip.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class MalformedSipMessageExceptionDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    MalformedSipMessageException actualMalformedSipMessageException = new MalformedSipMessageException();

    // Assert
    assertNull(actualMalformedSipMessageException.getCause());
    assertEquals("org.mjsip.sip.message.MalformedSipMessageException", actualMalformedSipMessageException.toString());
    assertEquals(0, actualMalformedSipMessageException.getSuppressed().length);
    assertNull(actualMalformedSipMessageException.getMessage());
    assertNull(actualMalformedSipMessageException.getLocalizedMessage());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    MalformedSipMessageException actualMalformedSipMessageException = new MalformedSipMessageException(
        "An error occurred");

    // Assert
    assertNull(actualMalformedSipMessageException.getCause());
    assertEquals("org.mjsip.sip.message.MalformedSipMessageException: An error occurred",
        actualMalformedSipMessageException.toString());
    assertEquals(0, actualMalformedSipMessageException.getSuppressed().length);
    assertEquals("An error occurred", actualMalformedSipMessageException.getMessage());
    assertEquals("An error occurred", actualMalformedSipMessageException.getLocalizedMessage());
  }
}

