package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ContentLengthHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ContentLengthHeader actualContentLengthHeader = new ContentLengthHeader(3);

    // Assert
    assertEquals("3", actualContentLengthHeader.getValue());
    assertEquals(CoreSipHeaders.Content_Length, actualContentLengthHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ContentLengthHeader actualContentLengthHeader = new ContentLengthHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualContentLengthHeader.getValue());
    assertEquals("Hname", actualContentLengthHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    ContentLengthHeader actualContentLengthHeader = new ContentLengthHeader(header);

    // Assert
    assertEquals("42", actualContentLengthHeader.getValue());
    assertEquals("Hname", actualContentLengthHeader.getName());
  }

  @Test
  void testGetContentLength() {
    // Arrange, Act and Assert
    assertEquals(3, (new ContentLengthHeader(3)).getContentLength());
    assertEquals(42, (new ContentLengthHeader(new Header(new Header("Hname", "42")))).getContentLength());
  }

  @Test
  void testSetContentLength() {
    // Arrange
    ContentLengthHeader contentLengthHeader = new ContentLengthHeader(3);

    // Act
    contentLengthHeader.setContentLength(3);

    // Assert
    assertEquals("3", contentLengthHeader.getValue());
  }
}

