package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ServerHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ServerHeader actualServerHeader = new ServerHeader("Info");
    actualServerHeader.setInfo("Info");

    // Assert
    assertEquals("Info", actualServerHeader.getInfo());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ServerHeader actualServerHeader = new ServerHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualServerHeader.getInfo());
    assertEquals("Hname", actualServerHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    ServerHeader actualServerHeader = new ServerHeader(header);

    // Assert
    assertEquals("42", actualServerHeader.getInfo());
    assertEquals("Hname", actualServerHeader.getName());
  }
}

