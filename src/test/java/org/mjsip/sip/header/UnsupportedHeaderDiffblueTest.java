package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class UnsupportedHeaderDiffblueTest {
  @Test
  void testAddOptionTag() {
    // Arrange
    UnsupportedHeader unsupportedHeader = new UnsupportedHeader("Option tag");

    // Act
    unsupportedHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag,Option tag", unsupportedHeader.getValue());
  }

  @Test
  void testAddOptionTag2() {
    // Arrange
    UnsupportedHeader unsupportedHeader = new UnsupportedHeader("");

    // Act
    unsupportedHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag", unsupportedHeader.getValue());
  }

  @Test
  void testAddOptionTag3() {
    // Arrange
    UnsupportedHeader unsupportedHeader = new UnsupportedHeader(new Vector(1));

    // Act
    unsupportedHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag", unsupportedHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    UnsupportedHeader actualUnsupportedHeader = new UnsupportedHeader("Option tag");

    // Assert
    Vector allOptionTags = actualUnsupportedHeader.getAllOptionTags();
    assertEquals(1, allOptionTags.size());
    assertEquals("Option tag", allOptionTags.get(0));
    assertEquals("Unsupported: Option tag\r\n", actualUnsupportedHeader.toString());
    assertEquals("Option tag", actualUnsupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Unsupported, actualUnsupportedHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    UnsupportedHeader actualUnsupportedHeader = new UnsupportedHeader(new Vector(1));

    // Assert
    assertNull(actualUnsupportedHeader.getAllOptionTags());
    assertEquals(CoreSipHeaders.Unsupported, actualUnsupportedHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    UnsupportedHeader actualUnsupportedHeader = new UnsupportedHeader(vector);

    // Assert
    assertEquals("42", actualUnsupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Unsupported, actualUnsupportedHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    UnsupportedHeader actualUnsupportedHeader = new UnsupportedHeader(vector);

    // Assert
    assertEquals("42,42", actualUnsupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Unsupported, actualUnsupportedHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    UnsupportedHeader actualUnsupportedHeader = new UnsupportedHeader(vector);

    // Assert
    assertEquals("foo", actualUnsupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Unsupported, actualUnsupportedHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    UnsupportedHeader actualUnsupportedHeader = new UnsupportedHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualUnsupportedHeader.getValue());
    assertEquals("Hname", actualUnsupportedHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    UnsupportedHeader actualUnsupportedHeader = new UnsupportedHeader(header);

    // Assert
    assertEquals("42", actualUnsupportedHeader.getValue());
    assertEquals("Hname", actualUnsupportedHeader.getName());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    UnsupportedHeader actualUnsupportedHeader = new UnsupportedHeader(new String[]{"Option tags"});

    // Assert
    assertEquals("Option tags", actualUnsupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Unsupported, actualUnsupportedHeader.getName());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    UnsupportedHeader actualUnsupportedHeader = new UnsupportedHeader(new String[]{});

    // Assert
    assertNull(actualUnsupportedHeader.getAllOptionTags());
    assertEquals(CoreSipHeaders.Unsupported, actualUnsupportedHeader.getName());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    UnsupportedHeader actualUnsupportedHeader = new UnsupportedHeader(
        new String[]{CoreSipHeaders.Unsupported, CoreSipHeaders.Unsupported});

    // Assert
    assertEquals("Unsupported,Unsupported", actualUnsupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Unsupported, actualUnsupportedHeader.getName());
  }

  @Test
  void testGetAllOptionTags() {
    // Arrange and Act
    Vector actualAllOptionTags = (new UnsupportedHeader("Option tag")).getAllOptionTags();

    // Assert
    assertEquals(1, actualAllOptionTags.size());
    assertEquals("Option tag", actualAllOptionTags.get(0));
  }

  @Test
  void testGetAllOptionTags2() {
    // Arrange and Act
    Vector actualAllOptionTags = (new UnsupportedHeader(new String[]{"foo", "foo", "foo"})).getAllOptionTags();

    // Assert
    assertEquals(3, actualAllOptionTags.size());
    assertEquals("foo", actualAllOptionTags.get(0));
    assertEquals("foo", actualAllOptionTags.get(1));
    assertEquals("foo", actualAllOptionTags.get(2));
  }

  @Test
  void testGetAllOptionTags3() {
    // Arrange, Act and Assert
    assertNull((new UnsupportedHeader(new Vector(1))).getAllOptionTags());
  }

  @Test
  void testGetAllOptionTags4() {
    // Arrange
    UnsupportedHeader unsupportedHeader = new UnsupportedHeader("Option tag");
    unsupportedHeader.addOptionTag("");

    // Act
    Vector actualAllOptionTags = unsupportedHeader.getAllOptionTags();

    // Assert
    assertEquals(1, actualAllOptionTags.size());
    assertEquals("Option tag", actualAllOptionTags.get(0));
  }

  @Test
  void testHasOptionTag() {
    // Arrange, Act and Assert
    assertTrue((new UnsupportedHeader("Option tag")).hasOptionTag("Option tag"));
    assertFalse((new UnsupportedHeader(CoreSipHeaders.Unsupported)).hasOptionTag("Option tag"));
    assertFalse((new UnsupportedHeader(new String[]{"foo", "foo", "foo"})).hasOptionTag("Option tag"));
    assertFalse((new UnsupportedHeader(new Vector(1))).hasOptionTag("Option tag"));
  }

  @Test
  void testHasOptionTag2() {
    // Arrange
    UnsupportedHeader unsupportedHeader = new UnsupportedHeader("Option tag");
    unsupportedHeader.addOptionTag("");

    // Act and Assert
    assertTrue(unsupportedHeader.hasOptionTag("Option tag"));
  }
}

