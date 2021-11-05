package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ContentDispositionHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ContentDispositionHeader actualContentDispositionHeader = new ContentDispositionHeader("42");

    // Assert
    assertEquals("42", actualContentDispositionHeader.getDisposition());
    assertEquals("Content-Disposition: 42\r\n", actualContentDispositionHeader.toString());
    assertEquals("42", actualContentDispositionHeader.getValue());
    assertEquals("42", actualContentDispositionHeader.getToken());
    assertNull(actualContentDispositionHeader.getParameters());
    assertTrue(actualContentDispositionHeader.getParameterNames().isEmpty());
    assertEquals(CoreSipHeaders.Content_Disposition, actualContentDispositionHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ContentDispositionHeader actualContentDispositionHeader = new ContentDispositionHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualContentDispositionHeader.getValue());
    assertEquals("Hname", actualContentDispositionHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    ContentDispositionHeader actualContentDispositionHeader = new ContentDispositionHeader(header);

    // Assert
    assertEquals("42", actualContentDispositionHeader.getValue());
    assertEquals("Hname", actualContentDispositionHeader.getName());
  }

  @Test
  void testGetDisposition() {
    // Arrange, Act and Assert
    assertEquals("42", (new ContentDispositionHeader("42")).getDisposition());
    assertEquals("", (new ContentDispositionHeader("")).getDisposition());
  }

  @Test
  void testGetDisposition2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals("42", (new ContentDispositionHeader(header)).getDisposition());
  }

  @Test
  void testDispositionEqualsTo() {
    // Arrange, Act and Assert
    assertTrue((new ContentDispositionHeader("42")).dispositionEqualsTo("42"));
    assertFalse((new ContentDispositionHeader("Hvalue")).dispositionEqualsTo("42"));
    assertFalse((new ContentDispositionHeader("")).dispositionEqualsTo("42"));
  }

  @Test
  void testDispositionEqualsTo2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new ContentDispositionHeader(header)).dispositionEqualsTo("42"));
  }

  @Test
  void testIsSession() {
    // Arrange, Act and Assert
    assertFalse((new ContentDispositionHeader("42")).isSession());
    assertTrue((new ContentDispositionHeader("session")).isSession());
    assertFalse((new ContentDispositionHeader("")).isSession());
  }

  @Test
  void testIsSession2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContentDispositionHeader(header)).isSession());
  }

  @Test
  void testIsRender() {
    // Arrange, Act and Assert
    assertFalse((new ContentDispositionHeader("42")).isRender());
    assertTrue((new ContentDispositionHeader("render")).isRender());
    assertFalse((new ContentDispositionHeader("")).isRender());
  }

  @Test
  void testIsRender2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContentDispositionHeader(header)).isRender());
  }

  @Test
  void testIsIcon() {
    // Arrange, Act and Assert
    assertFalse((new ContentDispositionHeader("42")).isIcon());
    assertTrue((new ContentDispositionHeader("icon")).isIcon());
    assertFalse((new ContentDispositionHeader("")).isIcon());
  }

  @Test
  void testIsIcon2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContentDispositionHeader(header)).isIcon());
  }

  @Test
  void testIsAlert() {
    // Arrange, Act and Assert
    assertFalse((new ContentDispositionHeader("42")).isAlert());
    assertTrue((new ContentDispositionHeader("alert")).isAlert());
    assertFalse((new ContentDispositionHeader("")).isAlert());
  }

  @Test
  void testIsAlert2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContentDispositionHeader(header)).isAlert());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new ContentDispositionHeader("42")).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ContentDispositionHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new ContentDispositionHeader("42")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new ContentDispositionHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new ContentDispositionHeader("42")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ContentDispositionHeader(header)).getParameters());
  }

  @Test
  void testGetToken() {
    // Arrange, Act and Assert
    assertEquals("42", (new ContentDispositionHeader("42")).getToken());
    assertEquals("", (new ContentDispositionHeader("")).getToken());
  }

  @Test
  void testGetToken2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals("42", (new ContentDispositionHeader(header)).getToken());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new ContentDispositionHeader("42")).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContentDispositionHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new ContentDispositionHeader("42")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContentDispositionHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ContentDispositionHeader("42")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new ContentDispositionHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetParameter() {
    // Arrange
    ContentDispositionHeader contentDispositionHeader = new ContentDispositionHeader("42");

    // Act
    contentDispositionHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", contentDispositionHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    ContentDispositionHeader contentDispositionHeader = new ContentDispositionHeader(new Header());

    // Act
    contentDispositionHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", contentDispositionHeader.getValue());
  }

  @Test
  void testTokenEqualsTo() {
    // Arrange, Act and Assert
    assertFalse((new ContentDispositionHeader("42")).tokenEqualsTo("ABC123"));
    assertTrue((new ContentDispositionHeader("ABC123")).tokenEqualsTo("ABC123"));
    assertFalse((new ContentDispositionHeader("")).tokenEqualsTo("ABC123"));
  }

  @Test
  void testTokenEqualsTo2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContentDispositionHeader(header)).tokenEqualsTo("ABC123"));
  }
}

