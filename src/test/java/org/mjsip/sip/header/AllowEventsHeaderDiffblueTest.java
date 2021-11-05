package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class AllowEventsHeaderDiffblueTest {
  @Test
  void testAddElement() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    // Act
    allowEventsHeader.addElement("Elem");

    // Assert
    assertEquals("42, Elem", allowEventsHeader.getValue());
  }

  @Test
  void testAddElement2() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("");

    // Act
    allowEventsHeader.addElement("Elem");

    // Assert
    assertEquals("Elem", allowEventsHeader.getValue());
  }

  @Test
  void testAddElement3() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader(new Header());

    // Act
    allowEventsHeader.addElement("Elem");

    // Assert
    assertEquals("Elem", allowEventsHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    AllowEventsHeader actualAllowEventsHeader = new AllowEventsHeader("42");

    // Assert
    Vector elements = actualAllowEventsHeader.getElements();
    assertEquals(1, elements.size());
    assertEquals("42", elements.get(0));
    assertEquals("Allow-Events: 42\r\n", actualAllowEventsHeader.toString());
    assertEquals("42", actualAllowEventsHeader.getValue());
    assertEquals(SipHeaders.Allow_Events, actualAllowEventsHeader.getName());
    Vector events = actualAllowEventsHeader.getEvents();
    assertEquals(elements, events);
    assertEquals(1, events.size());
    assertEquals("42", events.get(0));
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AllowEventsHeader actualAllowEventsHeader = new AllowEventsHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualAllowEventsHeader.getValue());
    assertEquals("Hname", actualAllowEventsHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    AllowEventsHeader actualAllowEventsHeader = new AllowEventsHeader(header);

    // Assert
    assertEquals("42", actualAllowEventsHeader.getValue());
    assertEquals("Hname", actualAllowEventsHeader.getName());
  }

  @Test
  void testGetEvents() {
    // Arrange and Act
    Vector actualEvents = (new AllowEventsHeader("42")).getEvents();

    // Assert
    assertEquals(1, actualEvents.size());
    assertEquals("42", actualEvents.get(0));
  }

  @Test
  void testGetEvents2() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");
    allowEventsHeader.addEvent(SipHeaders.Event);

    // Act
    Vector actualEvents = allowEventsHeader.getEvents();

    // Assert
    assertEquals(2, actualEvents.size());
    assertEquals("42", actualEvents.get(0));
    assertEquals(SipHeaders.Event, actualEvents.get(1));
  }

  @Test
  void testGetEvents3() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");
    allowEventsHeader.addEvent("");

    // Act
    Vector actualEvents = allowEventsHeader.getEvents();

    // Assert
    assertEquals(1, actualEvents.size());
    assertEquals("42", actualEvents.get(0));
  }

  @Test
  void testGetEvents4() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader(new Header());
    allowEventsHeader.addEvent(SipHeaders.Event);

    // Act
    Vector actualEvents = allowEventsHeader.getEvents();

    // Assert
    assertEquals(1, actualEvents.size());
    assertEquals(SipHeaders.Event, actualEvents.get(0));
  }

  @Test
  void testSetEvents() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    // Act
    allowEventsHeader.setEvents(new Vector(1));

    // Assert
    assertEquals("", allowEventsHeader.getValue());
  }

  @Test
  void testSetEvents2() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    allowEventsHeader.setEvents(vector);

    // Assert
    assertEquals("42", allowEventsHeader.getValue());
  }

  @Test
  void testSetEvents3() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    allowEventsHeader.setEvents(vector);

    // Assert
    assertEquals("42,42", allowEventsHeader.getValue());
  }

  @Test
  void testSetEvents4() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    allowEventsHeader.setEvents(vector);

    // Assert
    assertEquals("foo", allowEventsHeader.getValue());
  }

  @Test
  void testAddEvent() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    // Act
    allowEventsHeader.addEvent(SipHeaders.Event);

    // Assert
    assertEquals("42, Event", allowEventsHeader.getValue());
  }

  @Test
  void testAddEvent2() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("");

    // Act
    allowEventsHeader.addEvent(SipHeaders.Event);

    // Assert
    assertEquals(SipHeaders.Event, allowEventsHeader.getValue());
  }

  @Test
  void testAddEvent3() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader(new Header());

    // Act
    allowEventsHeader.addEvent(SipHeaders.Event);

    // Assert
    assertEquals(SipHeaders.Event, allowEventsHeader.getValue());
  }

  @Test
  void testGetElements() {
    // Arrange and Act
    Vector actualElements = (new AllowEventsHeader("42")).getElements();

    // Assert
    assertEquals(1, actualElements.size());
    assertEquals("42", actualElements.get(0));
  }

  @Test
  void testGetElements2() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");
    allowEventsHeader.addEvent(SipHeaders.Event);

    // Act
    Vector actualElements = allowEventsHeader.getElements();

    // Assert
    assertEquals(2, actualElements.size());
    assertEquals("42", actualElements.get(0));
    assertEquals(SipHeaders.Event, actualElements.get(1));
  }

  @Test
  void testGetElements3() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");
    allowEventsHeader.addEvent("");

    // Act
    Vector actualElements = allowEventsHeader.getElements();

    // Assert
    assertEquals(1, actualElements.size());
    assertEquals("42", actualElements.get(0));
  }

  @Test
  void testGetElements4() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader(new Header());
    allowEventsHeader.addEvent(SipHeaders.Event);

    // Act
    Vector actualElements = allowEventsHeader.getElements();

    // Assert
    assertEquals(1, actualElements.size());
    assertEquals(SipHeaders.Event, actualElements.get(0));
  }

  @Test
  void testSetElements() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    // Act
    allowEventsHeader.setElements(new Vector(1));

    // Assert
    assertEquals("", allowEventsHeader.getValue());
  }

  @Test
  void testSetElements2() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    allowEventsHeader.setElements(vector);

    // Assert
    assertEquals("42", allowEventsHeader.getValue());
  }

  @Test
  void testSetElements3() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    allowEventsHeader.setElements(vector);

    // Assert
    assertEquals("42,42", allowEventsHeader.getValue());
  }

  @Test
  void testSetElements4() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    allowEventsHeader.setElements(vector);

    // Assert
    assertEquals("foo", allowEventsHeader.getValue());
  }

  @Test
  void testSetElements5() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    // Act
    allowEventsHeader.setElements(new String[]{"Elements"});

    // Assert
    assertEquals("Elements", allowEventsHeader.getValue());
  }

  @Test
  void testSetElements6() {
    // Arrange
    AllowEventsHeader allowEventsHeader = new AllowEventsHeader("42");

    // Act
    allowEventsHeader.setElements(new String[]{"Elements", "Elements"});

    // Assert
    assertEquals("Elements,Elements", allowEventsHeader.getValue());
  }
}

