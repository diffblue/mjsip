package org.mjsip.ua.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class StringListDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    StringList actualStringList = new StringList("File");

    // Assert
    Vector expectedElements = actualStringList.list;
    assertSame(expectedElements, actualStringList.getElements());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    StringList actualStringList = new StringList("File");

    // Assert
    assertTrue(actualStringList.getElements().isEmpty());
    assertNull(actualStringList.file_url);
    assertEquals("File", actualStringList.file_name);
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    StringList actualStringList = new StringList((String) null);

    // Assert
    assertTrue(actualStringList.getElements().isEmpty());
    assertNull(actualStringList.file_url);
    assertNull(actualStringList.file_name);
  }

  @Test
  void testConstructor4() throws MalformedURLException {
    // Arrange and Act
    StringList actualStringList = new StringList(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Assert
    assertTrue(actualStringList.getElements().isEmpty());
    assertNull(actualStringList.file_name);
  }

  @Test
  void testLoad() {
    // Arrange
    StringList stringList = new StringList("File");

    // Act
    stringList.load();

    // Assert that nothing has changed
    assertTrue(stringList.getElements().isEmpty());
    assertEquals("File", stringList.file_name);
  }

  @Test
  void testLoad2() {
    // Arrange
    StringList stringList = new StringList((String) null);

    // Act
    stringList.load();

    // Assert that nothing has changed
    assertTrue(stringList.getElements().isEmpty());
  }

  @Test
  void testLoad3() throws MalformedURLException {
    // Arrange
    StringList stringList = new StringList(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act
    stringList.load();

    // Assert that nothing has changed
    assertTrue(stringList.getElements().isEmpty());
  }

  @Test
  void testLoad4() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.addElement("Elem");

    // Act
    stringList.load();

    // Assert that nothing has changed
    assertEquals(1, stringList.getElements().size());
    assertEquals("File", stringList.file_name);
  }

  @Test
  void testLoad5() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.insertElementAt("Elem", 0);

    // Act
    stringList.load();

    // Assert that nothing has changed
    assertEquals(1, stringList.getElements().size());
    assertEquals("File", stringList.file_name);
  }

  @Test
  void testSave() {
    // Arrange
    StringList stringList = new StringList((String) null);

    // Act
    stringList.save();

    // Assert that nothing has changed
    assertTrue(stringList.getElements().isEmpty());
  }

  @Test
  void testSave2() throws MalformedURLException {
    // Arrange
    StringList stringList = new StringList(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act
    stringList.save();

    // Assert that nothing has changed
    assertTrue(stringList.getElements().isEmpty());
  }

  @Test
  void testElementAt() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.addElement("Elem");
    stringList.addElement("Elem");

    // Act and Assert
    assertEquals("Elem", stringList.elementAt(1));
  }

  @Test
  void testInsertElementAt() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.addElement("Elem");

    // Act
    stringList.insertElementAt("Elem", 1);

    // Assert
    Vector elements = stringList.getElements();
    assertEquals(2, elements.size());
    assertEquals("Elem", elements.get(0));
    assertEquals("Elem", elements.get(1));
  }

  @Test
  void testInsertElementAt2() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.insertElementAt("Elem", 0);

    // Act
    stringList.insertElementAt("Elem", 1);

    // Assert
    Vector elements = stringList.getElements();
    assertEquals(2, elements.size());
    assertEquals("Elem", elements.get(0));
    assertEquals("Elem", elements.get(1));
  }

  @Test
  void testRemoveElementAt() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.addElement("Elem");
    stringList.addElement("Elem");

    // Act
    stringList.removeElementAt(1);

    // Assert
    Vector elements = stringList.getElements();
    assertEquals(1, elements.size());
    assertEquals("Elem", elements.get(0));
  }

  @Test
  void testAddElement() {
    // Arrange
    StringList stringList = new StringList("File");

    // Act
    stringList.addElement("Elem");

    // Assert
    Vector elements = stringList.getElements();
    assertEquals(1, elements.size());
    assertEquals("Elem", elements.get(0));
  }

  @Test
  void testAddElement2() throws MalformedURLException {
    // Arrange
    StringList stringList = new StringList(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act
    stringList.addElement("Elem");

    // Assert
    Vector elements = stringList.getElements();
    assertEquals(1, elements.size());
    assertEquals("Elem", elements.get(0));
  }

  @Test
  void testAddElement3() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.addElement("Elem");

    // Act
    stringList.addElement("Elem");

    // Assert
    Vector elements = stringList.getElements();
    assertEquals(2, elements.size());
    assertEquals("Elem", elements.get(0));
    assertEquals("Elem", elements.get(1));
  }

  @Test
  void testAddElement4() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.insertElementAt("Elem", 0);

    // Act
    stringList.addElement("Elem");

    // Assert
    Vector elements = stringList.getElements();
    assertEquals(2, elements.size());
    assertEquals("Elem", elements.get(0));
    assertEquals("Elem", elements.get(1));
  }

  @Test
  void testContains2() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.addElement("Elem");

    // Act and Assert
    assertTrue(stringList.contains("Elem"));
  }

  @Test
  void testContains3() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.insertElementAt("Elem", 0);

    // Act and Assert
    assertTrue(stringList.contains("Elem"));
  }

  @Test
  void testIndexOf2() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.addElement("Elem");

    // Act and Assert
    assertEquals(0, stringList.indexOf("Elem"));
  }

  @Test
  void testIndexOf3() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.insertElementAt("Elem", 0);

    // Act and Assert
    assertEquals(0, stringList.indexOf("Elem"));
  }

  @Test
  void testParseLine() {
    // Arrange
    StringList stringList = new StringList("File");

    // Act
    stringList.parseLine("Line");

    // Assert
    Vector elements = stringList.getElements();
    assertEquals(1, elements.size());
    assertEquals("Line", elements.get(0));
  }

  @Test
  void testParseLine2() throws MalformedURLException {
    // Arrange
    StringList stringList = new StringList(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act
    stringList.parseLine("Line");

    // Assert
    Vector elements = stringList.getElements();
    assertEquals(1, elements.size());
    assertEquals("Line", elements.get(0));
  }

  @Test
  void testParseLine3() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.addElement("Elem");

    // Act
    stringList.parseLine("Line");

    // Assert
    Vector elements = stringList.getElements();
    assertEquals(2, elements.size());
    assertEquals("Elem", elements.get(0));
    assertEquals("Line", elements.get(1));
  }

  @Test
  void testParseLine4() {
    // Arrange
    StringList stringList = new StringList("File");
    stringList.insertElementAt("Elem", 0);

    // Act
    stringList.parseLine("Line");

    // Assert
    Vector elements = stringList.getElements();
    assertEquals(2, elements.size());
    assertEquals("Elem", elements.get(0));
    assertEquals("Line", elements.get(1));
  }
}

