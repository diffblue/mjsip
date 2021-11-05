package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class AcceptHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    AcceptHeader actualAcceptHeader = new AcceptHeader();
    actualAcceptHeader.setAcceptRange("Range");

    // Assert
    assertEquals("Range", actualAcceptHeader.getAcceptRange());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AcceptHeader actualAcceptHeader = new AcceptHeader("42");
    actualAcceptHeader.setAcceptRange("Range");

    // Assert
    assertEquals("Range", actualAcceptHeader.getAcceptRange());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    AcceptHeader actualAcceptHeader = new AcceptHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualAcceptHeader.getAcceptRange());
    assertEquals("Hname", actualAcceptHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    AcceptHeader actualAcceptHeader = new AcceptHeader(header);

    // Assert
    assertEquals("42", actualAcceptHeader.getAcceptRange());
    assertEquals("Hname", actualAcceptHeader.getName());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new AcceptHeader("42")).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new AcceptHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new AcceptHeader("42")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new AcceptHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new AcceptHeader("42")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new AcceptHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new AcceptHeader("42")).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new AcceptHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new AcceptHeader("42")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new AcceptHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new AcceptHeader("42")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new AcceptHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetParameter() {
    // Arrange
    AcceptHeader acceptHeader = new AcceptHeader("42");

    // Act
    acceptHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", acceptHeader.getAcceptRange());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    AcceptHeader acceptHeader = new AcceptHeader(new Header());

    // Act
    acceptHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", acceptHeader.getAcceptRange());
  }
}

