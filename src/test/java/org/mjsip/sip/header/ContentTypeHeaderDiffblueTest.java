package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ContentTypeHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ContentTypeHeader actualContentTypeHeader = new ContentTypeHeader("42");
    actualContentTypeHeader.setContentType("C Type");

    // Assert
    assertEquals("C Type", actualContentTypeHeader.getValue());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ContentTypeHeader actualContentTypeHeader = new ContentTypeHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualContentTypeHeader.getValue());
    assertEquals("Hname", actualContentTypeHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    ContentTypeHeader actualContentTypeHeader = new ContentTypeHeader(header);

    // Assert
    assertEquals("42", actualContentTypeHeader.getValue());
    assertEquals("Hname", actualContentTypeHeader.getName());
  }

  @Test
  void testGetContentType() {
    // Arrange, Act and Assert
    assertEquals("42", (new ContentTypeHeader("42")).getContentType());
    assertEquals("", (new ContentTypeHeader("")).getContentType());
  }

  @Test
  void testGetContentType2() {
    // Arrange
    ContentTypeHeader contentTypeHeader = new ContentTypeHeader("42");
    contentTypeHeader.setContentType("C Type");

    // Act and Assert
    assertEquals("C", contentTypeHeader.getContentType());
  }

  @Test
  void testGetContentType3() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals("42", (new ContentTypeHeader(header)).getContentType());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new ContentTypeHeader("42")).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ContentTypeHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new ContentTypeHeader("42")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new ContentTypeHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new ContentTypeHeader("42")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ContentTypeHeader(header)).getParameters());
  }

  @Test
  void testGetToken() {
    // Arrange, Act and Assert
    assertEquals("42", (new ContentTypeHeader("42")).getToken());
    assertEquals("", (new ContentTypeHeader("")).getToken());
  }

  @Test
  void testGetToken2() {
    // Arrange
    ContentTypeHeader contentTypeHeader = new ContentTypeHeader("42");
    contentTypeHeader.setContentType("C Type");

    // Act and Assert
    assertEquals("C", contentTypeHeader.getToken());
  }

  @Test
  void testGetToken3() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals("42", (new ContentTypeHeader(header)).getToken());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new ContentTypeHeader("42")).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContentTypeHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new ContentTypeHeader("42")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContentTypeHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ContentTypeHeader("42")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new ContentTypeHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetParameter() {
    // Arrange
    ContentTypeHeader contentTypeHeader = new ContentTypeHeader("42");

    // Act
    contentTypeHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", contentTypeHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    ContentTypeHeader contentTypeHeader = new ContentTypeHeader(new Header());

    // Act
    contentTypeHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", contentTypeHeader.getValue());
  }

  @Test
  void testTokenEqualsTo() {
    // Arrange, Act and Assert
    assertFalse((new ContentTypeHeader("42")).tokenEqualsTo("ABC123"));
    assertTrue((new ContentTypeHeader("ABC123")).tokenEqualsTo("ABC123"));
    assertFalse((new ContentTypeHeader("")).tokenEqualsTo("ABC123"));
  }

  @Test
  void testTokenEqualsTo2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContentTypeHeader(header)).tokenEqualsTo("ABC123"));
  }

  @Test
  void testTokenEqualsTo3() {
    // Arrange
    ContentTypeHeader contentTypeHeader = new ContentTypeHeader(new Header());
    contentTypeHeader.setContentType("C Type");

    // Act and Assert
    assertFalse(contentTypeHeader.tokenEqualsTo("ABC123"));
  }
}

