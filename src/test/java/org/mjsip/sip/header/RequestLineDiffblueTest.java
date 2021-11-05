package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;

class RequestLineDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    RequestLine actualRequestLine = new RequestLine("Method", new GenericURI("Uri"));

    // Assert
    GenericURI expectedAddress = actualRequestLine.uri;
    assertSame(expectedAddress, actualRequestLine.getAddress());
    assertEquals("Method", actualRequestLine.getMethod());
    assertEquals("Method Uri SIP/2.0\r\n", actualRequestLine.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    RequestLine actualRequestLine = new RequestLine("Request", "Str uri");

    // Assert
    assertEquals("Request Str uri SIP/2.0\r\n", actualRequestLine.toString());
    assertEquals("Request", actualRequestLine.getMethod());
    assertEquals("Str uri", actualRequestLine.getAddress().getSpecificPart());
  }

  @Test
  void testClone() {
    // Arrange and Act
    Object actualCloneResult = (new RequestLine("Request", "Str uri")).clone();

    // Assert
    assertEquals("Request Str uri SIP/2.0\r\n", actualCloneResult.toString());
    assertEquals("Request", ((RequestLine) actualCloneResult).getMethod());
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new RequestLine("Request", "Str uri")).equals(null));
    assertFalse((new RequestLine("Request", "Str uri")).equals("Different type to RequestLine"));
  }

  @Test
  void testEquals2() {
    // Arrange
    RequestLine requestLine = new RequestLine("Request", "Str uri");

    // Act and Assert
    assertTrue(requestLine.equals(requestLine));
    int expectedHashCodeResult = requestLine.hashCode();
    assertEquals(expectedHashCodeResult, requestLine.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    RequestLine requestLine = new RequestLine("Request", "Str uri");
    RequestLine requestLine1 = new RequestLine("Request", "Str uri");

    // Act and Assert
    assertTrue(requestLine.equals(requestLine1));
    int notExpectedHashCodeResult = requestLine.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, requestLine1.hashCode()));
  }

  @Test
  void testEquals4() {
    // Arrange
    RequestLine requestLine = new RequestLine("Str uri", "Str uri");

    // Act and Assert
    assertFalse(requestLine.equals(new RequestLine("Request", "Str uri")));
  }

  @Test
  void testEquals5() {
    // Arrange
    RequestLine requestLine = new RequestLine("Request", "Request");

    // Act and Assert
    assertFalse(requestLine.equals(new RequestLine("Request", "Str uri")));
  }
}

