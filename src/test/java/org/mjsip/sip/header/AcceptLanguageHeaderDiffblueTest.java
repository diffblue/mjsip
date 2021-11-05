package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class AcceptLanguageHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("42", (new AcceptLanguageHeader("42")).getAcceptLanguage());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AcceptLanguageHeader actualAcceptLanguageHeader = new AcceptLanguageHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualAcceptLanguageHeader.getAcceptLanguage());
    assertEquals("Hname", actualAcceptLanguageHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    AcceptLanguageHeader actualAcceptLanguageHeader = new AcceptLanguageHeader(header);

    // Assert
    assertEquals("42", actualAcceptLanguageHeader.getAcceptLanguage());
    assertEquals("Hname", actualAcceptLanguageHeader.getName());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new AcceptLanguageHeader("42")).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new AcceptLanguageHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new AcceptLanguageHeader("42")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new AcceptLanguageHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new AcceptLanguageHeader("42")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new AcceptLanguageHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new AcceptLanguageHeader("42")).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new AcceptLanguageHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new AcceptLanguageHeader("42")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new AcceptLanguageHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new AcceptLanguageHeader("42")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new AcceptLanguageHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetParameter() {
    // Arrange
    AcceptLanguageHeader acceptLanguageHeader = new AcceptLanguageHeader("42");

    // Act
    acceptLanguageHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", acceptLanguageHeader.getAcceptLanguage());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    AcceptLanguageHeader acceptLanguageHeader = new AcceptLanguageHeader(new Header());

    // Act
    acceptLanguageHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", acceptLanguageHeader.getAcceptLanguage());
  }
}

