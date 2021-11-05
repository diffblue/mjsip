package org.mjsip.sip.address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class UnexpectedUriSchemeExceptionDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    UnexpectedUriSchemeException actualUnexpectedUriSchemeException = new UnexpectedUriSchemeException();

    // Assert
    assertNull(actualUnexpectedUriSchemeException.getCause());
    assertEquals("org.mjsip.sip.address.UnexpectedUriSchemeException: Unexpected URI's scheme.",
        actualUnexpectedUriSchemeException.toString());
    assertEquals(0, actualUnexpectedUriSchemeException.getSuppressed().length);
    assertEquals("Unexpected URI's scheme.", actualUnexpectedUriSchemeException.getMessage());
    assertEquals("Unexpected URI's scheme.", actualUnexpectedUriSchemeException.getLocalizedMessage());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    UnexpectedUriSchemeException actualUnexpectedUriSchemeException = new UnexpectedUriSchemeException("Scheme");

    // Assert
    assertNull(actualUnexpectedUriSchemeException.getCause());
    assertEquals("org.mjsip.sip.address.UnexpectedUriSchemeException: Unexpected URI's scheme 'Scheme'.",
        actualUnexpectedUriSchemeException.toString());
    assertEquals(0, actualUnexpectedUriSchemeException.getSuppressed().length);
    assertEquals("Unexpected URI's scheme 'Scheme'.", actualUnexpectedUriSchemeException.getMessage());
    assertEquals("Unexpected URI's scheme 'Scheme'.", actualUnexpectedUriSchemeException.getLocalizedMessage());
  }
}

