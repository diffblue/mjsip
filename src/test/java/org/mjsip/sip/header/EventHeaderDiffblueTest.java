package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class EventHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    EventHeader actualEventHeader = new EventHeader("java.text");

    // Assert
    assertEquals("java.text", actualEventHeader.getEvent());
    assertEquals("Event: java.text\r\n", actualEventHeader.toString());
    assertFalse(actualEventHeader.hasId());
    assertEquals("java.text", actualEventHeader.getValue());
    assertNull(actualEventHeader.getParameters());
    assertTrue(actualEventHeader.getParameterNames().isEmpty());
    assertEquals(SipHeaders.Event, actualEventHeader.getName());
    assertNull(actualEventHeader.getId());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    EventHeader actualEventHeader = new EventHeader("java.text", "42");

    // Assert
    assertEquals("java.text;id=42", actualEventHeader.getValue());
    assertEquals(SipHeaders.Event, actualEventHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    EventHeader actualEventHeader = new EventHeader(null, "42");

    // Assert
    assertEquals(";id=42", actualEventHeader.getValue());
    assertEquals(SipHeaders.Event, actualEventHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    EventHeader actualEventHeader = new EventHeader("java.text", null);

    // Assert
    assertEquals("java.text", actualEventHeader.getValue());
    assertEquals(SipHeaders.Event, actualEventHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    EventHeader actualEventHeader = new EventHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualEventHeader.getValue());
    assertEquals("Hname", actualEventHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    EventHeader actualEventHeader = new EventHeader(header);

    // Assert
    assertEquals("42", actualEventHeader.getValue());
    assertEquals("Hname", actualEventHeader.getName());
  }

  @Test
  void testGetEvent() {
    // Arrange, Act and Assert
    assertEquals("java.text", (new EventHeader("java.text")).getEvent());
    assertEquals(SipHeaders.Event, (new EventHeader("Event package")).getEvent());
    assertEquals("", (new EventHeader("")).getEvent());
    assertEquals("id=42", (new EventHeader("", "42")).getEvent());
  }

  @Test
  void testGetEvent2() {
    // Arrange
    EventHeader eventHeader = new EventHeader(new Header());
    eventHeader.setValue("42");

    // Act and Assert
    assertEquals("42", eventHeader.getEvent());
  }

  @Test
  void testGetId() {
    // Arrange, Act and Assert
    assertNull((new EventHeader("java.text")).getId());
    assertEquals("42", (new EventHeader("java.text", "42")).getId());
    assertEquals("", (new EventHeader("java.text", "")).getId());
  }

  @Test
  void testGetId2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new EventHeader(header)).getId());
  }

  @Test
  void testHasId() {
    // Arrange, Act and Assert
    assertFalse((new EventHeader("java.text")).hasId());
    assertTrue((new EventHeader("java.text", "42")).hasId());
  }

  @Test
  void testHasId2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new EventHeader(header)).hasId());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new EventHeader("java.text")).getParameter("Pname"));
    assertNull((new EventHeader("java.text", "42")).getParameter("Pname"));
    assertEquals("42", (new EventHeader("java.text", "42")).getParameter("id"));
    assertEquals("", (new EventHeader("java.text", "")).getParameter("id"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new EventHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new EventHeader("java.text")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange and Act
    Vector actualParameterNames = (new EventHeader("java.text", "42")).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("id", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new EventHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new EventHeader("java.text")).getParameters());
    assertEquals("id=42", (new EventHeader("java.text", "42")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new EventHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new EventHeader("java.text")).hasParameter("Pname"));
    assertFalse((new EventHeader("java.text", "42")).hasParameter("Pname"));
    assertTrue((new EventHeader("java.text", "42")).hasParameter("id"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new EventHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new EventHeader("java.text")).hasParameters());
    assertTrue((new EventHeader("java.text", "42")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new EventHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new EventHeader("java.text")).indexOfFirstSemi());
    assertEquals(9, (new EventHeader("java.text", "42")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new EventHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testRemoveParameter() {
    // Arrange
    EventHeader eventHeader = new EventHeader("java.text", "42");

    // Act
    eventHeader.removeParameter("id");

    // Assert
    assertEquals("java.text", eventHeader.getValue());
  }

  @Test
  void testRemoveParameters() {
    // Arrange
    EventHeader eventHeader = new EventHeader("java.text", "42");

    // Act
    eventHeader.removeParameters();

    // Assert
    assertEquals("java.text", eventHeader.getValue());
  }

  @Test
  void testSetParameter() {
    // Arrange
    EventHeader eventHeader = new EventHeader("java.text");

    // Act
    eventHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("java.text;Pname=42", eventHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    EventHeader eventHeader = new EventHeader("java.text", "42");

    // Act
    eventHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("java.text;id=42;Pname=42", eventHeader.getValue());
  }

  @Test
  void testSetParameter3() {
    // Arrange
    EventHeader eventHeader = new EventHeader("java.text", "42");

    // Act
    eventHeader.setParameter("id", "42");

    // Assert
    assertEquals("java.text;id=42", eventHeader.getValue());
  }

  @Test
  void testSetParameter4() {
    // Arrange
    EventHeader eventHeader = new EventHeader(new Header());

    // Act
    eventHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", eventHeader.getValue());
  }
}

