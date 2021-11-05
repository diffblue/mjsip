package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class InfoPackageHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    InfoPackageHeader actualInfoPackageHeader = new InfoPackageHeader("42");

    // Assert
    assertEquals(SipHeaders.Info_Package, actualInfoPackageHeader.getName());
    assertEquals("Info-Package: 42\r\n", actualInfoPackageHeader.toString());
    assertEquals("42", actualInfoPackageHeader.getValue());
    assertEquals("42", actualInfoPackageHeader.getToken());
    assertNull(actualInfoPackageHeader.getParameters());
    assertTrue(actualInfoPackageHeader.getParameterNames().isEmpty());
    assertEquals("42", actualInfoPackageHeader.getPackage());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    InfoPackageHeader actualInfoPackageHeader = new InfoPackageHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("Hname", actualInfoPackageHeader.getName());
    assertEquals("42", actualInfoPackageHeader.getValue());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    InfoPackageHeader actualInfoPackageHeader = new InfoPackageHeader(header);

    // Assert
    assertEquals("Hname", actualInfoPackageHeader.getName());
    assertEquals("42", actualInfoPackageHeader.getValue());
  }

  @Test
  void testGetPackage() {
    // Arrange, Act and Assert
    assertEquals("42", (new InfoPackageHeader("42")).getPackage());
    assertEquals("", (new InfoPackageHeader("")).getPackage());
  }

  @Test
  void testGetPackage2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals("42", (new InfoPackageHeader(header)).getPackage());
  }

  @Test
  void testPackageEqualsTo() {
    // Arrange, Act and Assert
    assertTrue((new InfoPackageHeader("42")).packageEqualsTo("42"));
    assertFalse((new InfoPackageHeader("Hvalue")).packageEqualsTo("42"));
    assertFalse((new InfoPackageHeader("")).packageEqualsTo("42"));
  }

  @Test
  void testPackageEqualsTo2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new InfoPackageHeader(header)).packageEqualsTo("42"));
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new InfoPackageHeader("42")).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new InfoPackageHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new InfoPackageHeader("42")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new InfoPackageHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new InfoPackageHeader("42")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new InfoPackageHeader(header)).getParameters());
  }

  @Test
  void testGetToken() {
    // Arrange, Act and Assert
    assertEquals("42", (new InfoPackageHeader("42")).getToken());
    assertEquals("", (new InfoPackageHeader("")).getToken());
  }

  @Test
  void testGetToken2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals("42", (new InfoPackageHeader(header)).getToken());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new InfoPackageHeader("42")).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new InfoPackageHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new InfoPackageHeader("42")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new InfoPackageHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new InfoPackageHeader("42")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new InfoPackageHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetParameter() {
    // Arrange
    InfoPackageHeader infoPackageHeader = new InfoPackageHeader("42");

    // Act
    infoPackageHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", infoPackageHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    InfoPackageHeader infoPackageHeader = new InfoPackageHeader(new Header());

    // Act
    infoPackageHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", infoPackageHeader.getValue());
  }

  @Test
  void testTokenEqualsTo() {
    // Arrange, Act and Assert
    assertFalse((new InfoPackageHeader("42")).tokenEqualsTo("ABC123"));
    assertTrue((new InfoPackageHeader("ABC123")).tokenEqualsTo("ABC123"));
    assertFalse((new InfoPackageHeader("")).tokenEqualsTo("ABC123"));
  }

  @Test
  void testTokenEqualsTo2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new InfoPackageHeader(header)).tokenEqualsTo("ABC123"));
  }
}

