package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class AcceptEncodingHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("42", (new AcceptEncodingHeader("42")).getAcceptEncoding());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AcceptEncodingHeader actualAcceptEncodingHeader = new AcceptEncodingHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualAcceptEncodingHeader.getAcceptEncoding());
    assertEquals("Hname", actualAcceptEncodingHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    AcceptEncodingHeader actualAcceptEncodingHeader = new AcceptEncodingHeader(header);

    // Assert
    assertEquals("42", actualAcceptEncodingHeader.getAcceptEncoding());
    assertEquals("Hname", actualAcceptEncodingHeader.getName());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new AcceptEncodingHeader("42")).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new AcceptEncodingHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new AcceptEncodingHeader("42")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new AcceptEncodingHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new AcceptEncodingHeader("42")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new AcceptEncodingHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new AcceptEncodingHeader("42")).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new AcceptEncodingHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new AcceptEncodingHeader("42")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new AcceptEncodingHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new AcceptEncodingHeader("42")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new AcceptEncodingHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetParameter() {
    // Arrange
    AcceptEncodingHeader acceptEncodingHeader = new AcceptEncodingHeader("42");

    // Act
    acceptEncodingHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", acceptEncodingHeader.getAcceptEncoding());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    AcceptEncodingHeader acceptEncodingHeader = new AcceptEncodingHeader(new Header());

    // Act
    acceptEncodingHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", acceptEncodingHeader.getAcceptEncoding());
  }
}

