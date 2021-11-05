package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class AllowHeaderDiffblueTest {
  @Test
  void testAddElement() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    // Act
    allowHeader.addElement("Elem");

    // Assert
    assertEquals("42, Elem", allowHeader.getValue());
  }

  @Test
  void testAddElement2() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("");

    // Act
    allowHeader.addElement("Elem");

    // Assert
    assertEquals("Elem", allowHeader.getValue());
  }

  @Test
  void testAddElement3() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader(new Header());

    // Act
    allowHeader.addElement("Elem");

    // Assert
    assertEquals("Elem", allowHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    AllowHeader actualAllowHeader = new AllowHeader("42");

    // Assert
    Vector elements = actualAllowHeader.getElements();
    assertEquals(1, elements.size());
    assertEquals("42", elements.get(0));
    assertEquals("Allow: 42\r\n", actualAllowHeader.toString());
    assertEquals("42", actualAllowHeader.getValue());
    assertEquals(CoreSipHeaders.Allow, actualAllowHeader.getName());
    Vector methods = actualAllowHeader.getMethods();
    assertEquals(elements, methods);
    assertEquals(1, methods.size());
    assertEquals("42", methods.get(0));
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AllowHeader actualAllowHeader = new AllowHeader(new Vector(1));

    // Assert
    assertEquals("", actualAllowHeader.getValue());
    assertEquals(CoreSipHeaders.Allow, actualAllowHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    AllowHeader actualAllowHeader = new AllowHeader(vector);

    // Assert
    assertEquals("42", actualAllowHeader.getValue());
    assertEquals(CoreSipHeaders.Allow, actualAllowHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    AllowHeader actualAllowHeader = new AllowHeader(vector);

    // Assert
    assertEquals("42,42", actualAllowHeader.getValue());
    assertEquals(CoreSipHeaders.Allow, actualAllowHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    AllowHeader actualAllowHeader = new AllowHeader(vector);

    // Assert
    assertEquals("foo", actualAllowHeader.getValue());
    assertEquals(CoreSipHeaders.Allow, actualAllowHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    AllowHeader actualAllowHeader = new AllowHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualAllowHeader.getValue());
    assertEquals("Hname", actualAllowHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    AllowHeader actualAllowHeader = new AllowHeader(header);

    // Assert
    assertEquals("42", actualAllowHeader.getValue());
    assertEquals("Hname", actualAllowHeader.getName());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    AllowHeader actualAllowHeader = new AllowHeader(new String[]{"Elements"});

    // Assert
    assertEquals("Elements", actualAllowHeader.getValue());
    assertEquals(CoreSipHeaders.Allow, actualAllowHeader.getName());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    AllowHeader actualAllowHeader = new AllowHeader(new String[]{CoreSipHeaders.Allow, CoreSipHeaders.Allow});

    // Assert
    assertEquals("Allow,Allow", actualAllowHeader.getValue());
    assertEquals(CoreSipHeaders.Allow, actualAllowHeader.getName());
  }

  @Test
  void testGetMethods() {
    // Arrange and Act
    Vector actualMethods = (new AllowHeader("42")).getMethods();

    // Assert
    assertEquals(1, actualMethods.size());
    assertEquals("42", actualMethods.get(0));
  }

  @Test
  void testGetMethods2() {
    // Arrange and Act
    Vector actualMethods = (new AllowHeader(new String[]{"foo", "foo", "foo"})).getMethods();

    // Assert
    assertEquals(3, actualMethods.size());
    assertEquals("foo", actualMethods.get(0));
    assertEquals("foo", actualMethods.get(1));
    assertEquals("foo", actualMethods.get(2));
  }

  @Test
  void testGetMethods3() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");
    allowHeader.addElement("");

    // Act
    Vector actualMethods = allowHeader.getMethods();

    // Assert
    assertEquals(1, actualMethods.size());
    assertEquals("42", actualMethods.get(0));
  }

  @Test
  void testGetMethods4() {
    // Arrange and Act
    Vector actualMethods = (new AllowHeader(new String[]{"", "foo", "foo"})).getMethods();

    // Assert
    assertEquals(2, actualMethods.size());
    assertEquals("foo", actualMethods.get(0));
    assertEquals("foo", actualMethods.get(1));
  }

  @Test
  void testGetMethods5() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader(new Header());
    allowHeader.addElement("Elem");

    // Act
    Vector actualMethods = allowHeader.getMethods();

    // Assert
    assertEquals(1, actualMethods.size());
    assertEquals("Elem", actualMethods.get(0));
  }

  @Test
  void testGetMethods6() {
    // Arrange and Act
    Vector actualMethods = (new AllowHeader(new String[]{"foo", "", ""})).getMethods();

    // Assert
    assertEquals(1, actualMethods.size());
    assertEquals("foo", actualMethods.get(0));
  }

  @Test
  void testSetMethod() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    // Act
    allowHeader.setMethod(new Vector(1));

    // Assert
    assertEquals("", allowHeader.getValue());
  }

  @Test
  void testSetMethod2() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    allowHeader.setMethod(vector);

    // Assert
    assertEquals("42", allowHeader.getValue());
  }

  @Test
  void testSetMethod3() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    allowHeader.setMethod(vector);

    // Assert
    assertEquals("42,42", allowHeader.getValue());
  }

  @Test
  void testSetMethod4() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    allowHeader.setMethod(vector);

    // Assert
    assertEquals("foo", allowHeader.getValue());
  }

  @Test
  void testAddMethod() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    // Act
    allowHeader.addMethod("Method");

    // Assert
    assertEquals("42, Method", allowHeader.getValue());
  }

  @Test
  void testAddMethod2() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("");

    // Act
    allowHeader.addMethod("Method");

    // Assert
    assertEquals("Method", allowHeader.getValue());
  }

  @Test
  void testAddMethod3() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader(new Header());

    // Act
    allowHeader.addMethod("Method");

    // Assert
    assertEquals("Method", allowHeader.getValue());
  }

  @Test
  void testGetElements() {
    // Arrange and Act
    Vector actualElements = (new AllowHeader("42")).getElements();

    // Assert
    assertEquals(1, actualElements.size());
    assertEquals("42", actualElements.get(0));
  }

  @Test
  void testGetElements2() {
    // Arrange and Act
    Vector actualElements = (new AllowHeader(new String[]{"foo", "foo", "foo"})).getElements();

    // Assert
    assertEquals(3, actualElements.size());
    assertEquals("foo", actualElements.get(0));
    assertEquals("foo", actualElements.get(1));
    assertEquals("foo", actualElements.get(2));
  }

  @Test
  void testGetElements3() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");
    allowHeader.addElement("");

    // Act
    Vector actualElements = allowHeader.getElements();

    // Assert
    assertEquals(1, actualElements.size());
    assertEquals("42", actualElements.get(0));
  }

  @Test
  void testGetElements4() {
    // Arrange and Act
    Vector actualElements = (new AllowHeader(new String[]{"", "foo", "foo"})).getElements();

    // Assert
    assertEquals(2, actualElements.size());
    assertEquals("foo", actualElements.get(0));
    assertEquals("foo", actualElements.get(1));
  }

  @Test
  void testGetElements5() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader(new Header());
    allowHeader.addElement("Elem");

    // Act
    Vector actualElements = allowHeader.getElements();

    // Assert
    assertEquals(1, actualElements.size());
    assertEquals("Elem", actualElements.get(0));
  }

  @Test
  void testGetElements6() {
    // Arrange and Act
    Vector actualElements = (new AllowHeader(new String[]{"foo", "", ""})).getElements();

    // Assert
    assertEquals(1, actualElements.size());
    assertEquals("foo", actualElements.get(0));
  }

  @Test
  void testSetElements() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    // Act
    allowHeader.setElements(new Vector(1));

    // Assert
    assertEquals("", allowHeader.getValue());
  }

  @Test
  void testSetElements2() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    allowHeader.setElements(vector);

    // Assert
    assertEquals("42", allowHeader.getValue());
  }

  @Test
  void testSetElements3() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    allowHeader.setElements(vector);

    // Assert
    assertEquals("42,42", allowHeader.getValue());
  }

  @Test
  void testSetElements4() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    allowHeader.setElements(vector);

    // Assert
    assertEquals("foo", allowHeader.getValue());
  }

  @Test
  void testSetElements5() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    // Act
    allowHeader.setElements(new String[]{"Elements"});

    // Assert
    assertEquals("Elements", allowHeader.getValue());
  }

  @Test
  void testSetElements6() {
    // Arrange
    AllowHeader allowHeader = new AllowHeader("42");

    // Act
    allowHeader.setElements(new String[]{"Elements", "Elements"});

    // Assert
    assertEquals("Elements,Elements", allowHeader.getValue());
  }
}

