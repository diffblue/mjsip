package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class AlertInfoHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    AlertInfoHeader actualAlertInfoHeader = new AlertInfoHeader("Absolute uri");

    // Assert
    assertEquals("Absolute uri", actualAlertInfoHeader.getAbsoluteURI());
    assertEquals(CoreSipHeaders.Alert_Info, actualAlertInfoHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AlertInfoHeader actualAlertInfoHeader = new AlertInfoHeader("<");

    // Assert
    assertEquals("", actualAlertInfoHeader.getAbsoluteURI());
    assertEquals(CoreSipHeaders.Alert_Info, actualAlertInfoHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    AlertInfoHeader actualAlertInfoHeader = new AlertInfoHeader(">");

    // Assert
    assertEquals("", actualAlertInfoHeader.getAbsoluteURI());
    assertEquals(CoreSipHeaders.Alert_Info, actualAlertInfoHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    AlertInfoHeader actualAlertInfoHeader = new AlertInfoHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualAlertInfoHeader.getAbsoluteURI());
    assertEquals("Hname", actualAlertInfoHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    AlertInfoHeader actualAlertInfoHeader = new AlertInfoHeader(header);

    // Assert
    assertEquals("42", actualAlertInfoHeader.getAbsoluteURI());
    assertEquals("Hname", actualAlertInfoHeader.getName());
  }

  @Test
  void testGetAbsoluteURI() {
    // Arrange, Act and Assert
    assertEquals("Absolute uri", (new AlertInfoHeader("Absolute uri")).getAbsoluteURI());
    assertEquals("42", (new AlertInfoHeader(new Header("Hname", "42"))).getAbsoluteURI());
  }

  @Test
  void testGetAbsoluteURI2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals("42", (new AlertInfoHeader(header)).getAbsoluteURI());
  }

  @Test
  void testSetAbsoluteURI() {
    // Arrange
    AlertInfoHeader alertInfoHeader = new AlertInfoHeader("Absolute uri");

    // Act
    alertInfoHeader.setAbsoluteURI("Absolute uri");

    // Assert
    assertEquals("Absolute uri", alertInfoHeader.getAbsoluteURI());
  }

  @Test
  void testSetAbsoluteURI2() {
    // Arrange
    AlertInfoHeader alertInfoHeader = new AlertInfoHeader("Absolute uri");

    // Act
    alertInfoHeader.setAbsoluteURI("<");

    // Assert
    assertEquals("", alertInfoHeader.getAbsoluteURI());
  }

  @Test
  void testSetAbsoluteURI3() {
    // Arrange
    AlertInfoHeader alertInfoHeader = new AlertInfoHeader("Absolute uri");

    // Act
    alertInfoHeader.setAbsoluteURI(">");

    // Assert
    assertEquals("", alertInfoHeader.getAbsoluteURI());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new AlertInfoHeader("Absolute uri")).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new AlertInfoHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new AlertInfoHeader("Absolute uri")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new AlertInfoHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new AlertInfoHeader("Absolute uri")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new AlertInfoHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new AlertInfoHeader("Absolute uri")).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new AlertInfoHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new AlertInfoHeader("Absolute uri")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new AlertInfoHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new AlertInfoHeader("Absolute uri")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new AlertInfoHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetParameter() {
    // Arrange
    AlertInfoHeader alertInfoHeader = new AlertInfoHeader("Absolute uri");

    // Act
    alertInfoHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Absolute uri", alertInfoHeader.getAbsoluteURI());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    AlertInfoHeader alertInfoHeader = new AlertInfoHeader(new Header());

    // Act
    alertInfoHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", alertInfoHeader.getAbsoluteURI());
  }
}

