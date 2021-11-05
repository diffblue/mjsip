package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class RecvInfoHeaderDiffblueTest {
  @Test
  void testAddElement() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    // Act
    recvInfoHeader.addElement("Elem");

    // Assert
    assertEquals("42, Elem", recvInfoHeader.getValue());
  }

  @Test
  void testAddElement2() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("");

    // Act
    recvInfoHeader.addElement("Elem");

    // Assert
    assertEquals("Elem", recvInfoHeader.getValue());
  }

  @Test
  void testAddElement3() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader(new Header());

    // Act
    recvInfoHeader.addElement("Elem");

    // Assert
    assertEquals("Elem", recvInfoHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    RecvInfoHeader actualRecvInfoHeader = new RecvInfoHeader("42");

    // Assert
    Vector elements = actualRecvInfoHeader.getElements();
    assertEquals(1, elements.size());
    assertEquals("42", elements.get(0));
    assertEquals("Recv-Info: 42\r\n", actualRecvInfoHeader.toString());
    assertEquals("42", actualRecvInfoHeader.getValue());
    Vector packages = actualRecvInfoHeader.getPackages();
    assertEquals(elements, packages);
    assertEquals(1, packages.size());
    assertEquals("42", packages.get(0));
    assertEquals(SipHeaders.Recv_Info, actualRecvInfoHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    RecvInfoHeader actualRecvInfoHeader = new RecvInfoHeader(new Vector(1));

    // Assert
    assertEquals("", actualRecvInfoHeader.getValue());
    assertEquals(SipHeaders.Recv_Info, actualRecvInfoHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    RecvInfoHeader actualRecvInfoHeader = new RecvInfoHeader(vector);

    // Assert
    assertEquals("42", actualRecvInfoHeader.getValue());
    assertEquals(SipHeaders.Recv_Info, actualRecvInfoHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    RecvInfoHeader actualRecvInfoHeader = new RecvInfoHeader(vector);

    // Assert
    assertEquals("42,42", actualRecvInfoHeader.getValue());
    assertEquals(SipHeaders.Recv_Info, actualRecvInfoHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    RecvInfoHeader actualRecvInfoHeader = new RecvInfoHeader(vector);

    // Assert
    assertEquals("foo", actualRecvInfoHeader.getValue());
    assertEquals(SipHeaders.Recv_Info, actualRecvInfoHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    RecvInfoHeader actualRecvInfoHeader = new RecvInfoHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualRecvInfoHeader.getValue());
    assertEquals("Hname", actualRecvInfoHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    RecvInfoHeader actualRecvInfoHeader = new RecvInfoHeader(header);

    // Assert
    assertEquals("42", actualRecvInfoHeader.getValue());
    assertEquals("Hname", actualRecvInfoHeader.getName());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    RecvInfoHeader actualRecvInfoHeader = new RecvInfoHeader(new String[]{"java.text"});

    // Assert
    assertEquals("java.text", actualRecvInfoHeader.getValue());
    assertEquals(SipHeaders.Recv_Info, actualRecvInfoHeader.getName());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    RecvInfoHeader actualRecvInfoHeader = new RecvInfoHeader(new String[]{"java.text", "java.text"});

    // Assert
    assertEquals("java.text,java.text", actualRecvInfoHeader.getValue());
    assertEquals(SipHeaders.Recv_Info, actualRecvInfoHeader.getName());
  }

  @Test
  void testGetPackages() {
    // Arrange and Act
    Vector actualPackages = (new RecvInfoHeader("42")).getPackages();

    // Assert
    assertEquals(1, actualPackages.size());
    assertEquals("42", actualPackages.get(0));
  }

  @Test
  void testGetPackages2() {
    // Arrange and Act
    Vector actualPackages = (new RecvInfoHeader(new String[]{"foo", "foo", "foo"})).getPackages();

    // Assert
    assertEquals(3, actualPackages.size());
    assertEquals("foo", actualPackages.get(0));
    assertEquals("foo", actualPackages.get(1));
    assertEquals("foo", actualPackages.get(2));
  }

  @Test
  void testGetPackages3() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");
    recvInfoHeader.addPackages("");

    // Act
    Vector actualPackages = recvInfoHeader.getPackages();

    // Assert
    assertEquals(1, actualPackages.size());
    assertEquals("42", actualPackages.get(0));
  }

  @Test
  void testGetPackages4() {
    // Arrange and Act
    Vector actualPackages = (new RecvInfoHeader(new String[]{"", "foo", "foo"})).getPackages();

    // Assert
    assertEquals(2, actualPackages.size());
    assertEquals("foo", actualPackages.get(0));
    assertEquals("foo", actualPackages.get(1));
  }

  @Test
  void testGetPackages5() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader(new Header());
    recvInfoHeader.addPackages("java.text");

    // Act
    Vector actualPackages = recvInfoHeader.getPackages();

    // Assert
    assertEquals(1, actualPackages.size());
    assertEquals("java.text", actualPackages.get(0));
  }

  @Test
  void testGetPackages6() {
    // Arrange and Act
    Vector actualPackages = (new RecvInfoHeader(new String[]{"foo", "", ""})).getPackages();

    // Assert
    assertEquals(1, actualPackages.size());
    assertEquals("foo", actualPackages.get(0));
  }

  @Test
  void testSetPackages() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    // Act
    recvInfoHeader.setPackages(new Vector(1));

    // Assert
    assertEquals("", recvInfoHeader.getValue());
  }

  @Test
  void testSetPackages2() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    recvInfoHeader.setPackages(vector);

    // Assert
    assertEquals("42", recvInfoHeader.getValue());
  }

  @Test
  void testSetPackages3() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    recvInfoHeader.setPackages(vector);

    // Assert
    assertEquals("42,42", recvInfoHeader.getValue());
  }

  @Test
  void testSetPackages4() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    recvInfoHeader.setPackages(vector);

    // Assert
    assertEquals("foo", recvInfoHeader.getValue());
  }

  @Test
  void testAddPackages() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    // Act
    recvInfoHeader.addPackages("java.text");

    // Assert
    assertEquals("42, java.text", recvInfoHeader.getValue());
  }

  @Test
  void testAddPackages2() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("");

    // Act
    recvInfoHeader.addPackages("java.text");

    // Assert
    assertEquals("java.text", recvInfoHeader.getValue());
  }

  @Test
  void testAddPackages3() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader(new Header());

    // Act
    recvInfoHeader.addPackages("java.text");

    // Assert
    assertEquals("java.text", recvInfoHeader.getValue());
  }

  @Test
  void testGetElements() {
    // Arrange and Act
    Vector actualElements = (new RecvInfoHeader("42")).getElements();

    // Assert
    assertEquals(1, actualElements.size());
    assertEquals("42", actualElements.get(0));
  }

  @Test
  void testGetElements2() {
    // Arrange and Act
    Vector actualElements = (new RecvInfoHeader(new String[]{"foo", "foo", "foo"})).getElements();

    // Assert
    assertEquals(3, actualElements.size());
    assertEquals("foo", actualElements.get(0));
    assertEquals("foo", actualElements.get(1));
    assertEquals("foo", actualElements.get(2));
  }

  @Test
  void testGetElements3() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");
    recvInfoHeader.addPackages("");

    // Act
    Vector actualElements = recvInfoHeader.getElements();

    // Assert
    assertEquals(1, actualElements.size());
    assertEquals("42", actualElements.get(0));
  }

  @Test
  void testGetElements4() {
    // Arrange and Act
    Vector actualElements = (new RecvInfoHeader(new String[]{"", "foo", "foo"})).getElements();

    // Assert
    assertEquals(2, actualElements.size());
    assertEquals("foo", actualElements.get(0));
    assertEquals("foo", actualElements.get(1));
  }

  @Test
  void testGetElements5() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader(new Header());
    recvInfoHeader.addPackages("java.text");

    // Act
    Vector actualElements = recvInfoHeader.getElements();

    // Assert
    assertEquals(1, actualElements.size());
    assertEquals("java.text", actualElements.get(0));
  }

  @Test
  void testGetElements6() {
    // Arrange and Act
    Vector actualElements = (new RecvInfoHeader(new String[]{"foo", "", ""})).getElements();

    // Assert
    assertEquals(1, actualElements.size());
    assertEquals("foo", actualElements.get(0));
  }

  @Test
  void testSetElements() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    // Act
    recvInfoHeader.setElements(new Vector(1));

    // Assert
    assertEquals("", recvInfoHeader.getValue());
  }

  @Test
  void testSetElements2() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    recvInfoHeader.setElements(vector);

    // Assert
    assertEquals("42", recvInfoHeader.getValue());
  }

  @Test
  void testSetElements3() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    recvInfoHeader.setElements(vector);

    // Assert
    assertEquals("42,42", recvInfoHeader.getValue());
  }

  @Test
  void testSetElements4() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    recvInfoHeader.setElements(vector);

    // Assert
    assertEquals("foo", recvInfoHeader.getValue());
  }

  @Test
  void testSetElements5() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    // Act
    recvInfoHeader.setElements(new String[]{"Elements"});

    // Assert
    assertEquals("Elements", recvInfoHeader.getValue());
  }

  @Test
  void testSetElements6() {
    // Arrange
    RecvInfoHeader recvInfoHeader = new RecvInfoHeader("42");

    // Act
    recvInfoHeader.setElements(new String[]{"Elements", "Elements"});

    // Assert
    assertEquals("Elements,Elements", recvInfoHeader.getValue());
  }
}

