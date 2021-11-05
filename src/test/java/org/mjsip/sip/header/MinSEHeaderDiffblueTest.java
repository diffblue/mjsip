package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class MinSEHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    MinSEHeader actualMinSEHeader = new MinSEHeader(2);

    // Assert
    assertEquals("2", actualMinSEHeader.getValue());
    assertEquals(SipHeaders.Min_SE, actualMinSEHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    MinSEHeader actualMinSEHeader = new MinSEHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualMinSEHeader.getValue());
    assertEquals("Hname", actualMinSEHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    MinSEHeader actualMinSEHeader = new MinSEHeader(header);

    // Assert
    assertEquals("42", actualMinSEHeader.getValue());
    assertEquals("Hname", actualMinSEHeader.getName());
  }

  @Test
  void testGetDeltaSeconds() {
    // Arrange, Act and Assert
    assertEquals(2, (new MinSEHeader(2)).getDeltaSeconds());
    assertEquals(42, (new MinSEHeader(new Header(new Header("Hname", "42")))).getDeltaSeconds());
  }

  @Test
  void testGetDeltaSeconds2() {
    // Arrange
    Header header = new Header("Hname", "Hvalue");
    header.setValue("42");

    // Act and Assert
    assertEquals(42, (new MinSEHeader(header)).getDeltaSeconds());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new MinSEHeader(2)).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new MinSEHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new MinSEHeader(2)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new MinSEHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new MinSEHeader(2)).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new MinSEHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new MinSEHeader(2)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new MinSEHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new MinSEHeader(2)).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new MinSEHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new MinSEHeader(2)).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new MinSEHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetParameter() {
    // Arrange
    MinSEHeader minSEHeader = new MinSEHeader(2);

    // Act
    minSEHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("2;Pname=42", minSEHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    MinSEHeader minSEHeader = new MinSEHeader(new Header());

    // Act
    minSEHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", minSEHeader.getValue());
  }
}

