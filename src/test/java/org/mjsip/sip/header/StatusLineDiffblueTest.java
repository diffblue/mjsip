package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class StatusLineDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    StatusLine actualStatusLine = new StatusLine(1, "foo");

    // Assert
    assertEquals(1, actualStatusLine.getCode());
    assertEquals("foo", actualStatusLine.getReason());
    assertEquals("SIP/2.0 1 foo\r\n", actualStatusLine.toString());
  }

  @Test
  void testClone() {
    // Arrange, Act and Assert
    assertEquals(1, ((StatusLine) (new StatusLine(1, "foo")).clone()).getCode());
    assertEquals("foo", ((StatusLine) (new StatusLine(1, "foo")).clone()).getReason());
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new StatusLine(1, "foo")).equals(null));
    assertFalse((new StatusLine(1, "foo")).equals("Different type to StatusLine"));
  }

  @Test
  void testEquals2() {
    // Arrange
    StatusLine statusLine = new StatusLine(1, "foo");

    // Act and Assert
    assertTrue(statusLine.equals(statusLine));
    int expectedHashCodeResult = statusLine.hashCode();
    assertEquals(expectedHashCodeResult, statusLine.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    StatusLine statusLine = new StatusLine(1, "foo");
    StatusLine statusLine1 = new StatusLine(1, "foo");

    // Act and Assert
    assertTrue(statusLine.equals(statusLine1));
    int notExpectedHashCodeResult = statusLine.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, statusLine1.hashCode()));
  }

  @Test
  void testEquals4() {
    // Arrange
    StatusLine statusLine = new StatusLine(0, "foo");

    // Act and Assert
    assertFalse(statusLine.equals(new StatusLine(1, "foo")));
  }

  @Test
  void testEquals5() {
    // Arrange
    StatusLine statusLine = new StatusLine(1, "org.mjsip.sip.header.StatusLine");

    // Act and Assert
    assertFalse(statusLine.equals(new StatusLine(1, "foo")));
  }
}

