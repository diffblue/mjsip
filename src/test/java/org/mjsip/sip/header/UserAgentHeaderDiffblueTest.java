package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class UserAgentHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    UserAgentHeader actualUserAgentHeader = new UserAgentHeader("Info");
    actualUserAgentHeader.setInfo("Info");

    // Assert
    assertEquals("Info", actualUserAgentHeader.getInfo());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    UserAgentHeader actualUserAgentHeader = new UserAgentHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualUserAgentHeader.getInfo());
    assertEquals("Hname", actualUserAgentHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    UserAgentHeader actualUserAgentHeader = new UserAgentHeader(header);

    // Assert
    assertEquals("42", actualUserAgentHeader.getInfo());
    assertEquals("Hname", actualUserAgentHeader.getName());
  }
}

