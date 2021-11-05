package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class RequireHeaderDiffblueTest {
  @Test
  void testAddOptionTag() {
    // Arrange
    RequireHeader requireHeader = new RequireHeader("Option tag");

    // Act
    requireHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag,Option tag", requireHeader.getValue());
  }

  @Test
  void testAddOptionTag2() {
    // Arrange
    RequireHeader requireHeader = new RequireHeader("");

    // Act
    requireHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag", requireHeader.getValue());
  }

  @Test
  void testAddOptionTag3() {
    // Arrange
    RequireHeader requireHeader = new RequireHeader(new Vector(1));

    // Act
    requireHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag", requireHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    RequireHeader actualRequireHeader = new RequireHeader("Option tag");

    // Assert
    Vector allOptionTags = actualRequireHeader.getAllOptionTags();
    assertEquals(1, allOptionTags.size());
    assertEquals("Option tag", allOptionTags.get(0));
    assertEquals("Require: Option tag\r\n", actualRequireHeader.toString());
    assertEquals("Option tag", actualRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Require, actualRequireHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    RequireHeader actualRequireHeader = new RequireHeader(new Vector(1));

    // Assert
    assertNull(actualRequireHeader.getAllOptionTags());
    assertEquals(CoreSipHeaders.Require, actualRequireHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    RequireHeader actualRequireHeader = new RequireHeader(vector);

    // Assert
    assertEquals("42", actualRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Require, actualRequireHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    RequireHeader actualRequireHeader = new RequireHeader(vector);

    // Assert
    assertEquals("42,42", actualRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Require, actualRequireHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    RequireHeader actualRequireHeader = new RequireHeader(vector);

    // Assert
    assertEquals("foo", actualRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Require, actualRequireHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    RequireHeader actualRequireHeader = new RequireHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualRequireHeader.getValue());
    assertEquals("Hname", actualRequireHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    RequireHeader actualRequireHeader = new RequireHeader(header);

    // Assert
    assertEquals("42", actualRequireHeader.getValue());
    assertEquals("Hname", actualRequireHeader.getName());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    RequireHeader actualRequireHeader = new RequireHeader(new String[]{"Option tags"});

    // Assert
    assertEquals("Option tags", actualRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Require, actualRequireHeader.getName());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    RequireHeader actualRequireHeader = new RequireHeader(new String[]{});

    // Assert
    assertNull(actualRequireHeader.getAllOptionTags());
    assertEquals(CoreSipHeaders.Require, actualRequireHeader.getName());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    RequireHeader actualRequireHeader = new RequireHeader(new String[]{CoreSipHeaders.Require, CoreSipHeaders.Require});

    // Assert
    assertEquals("Require,Require", actualRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Require, actualRequireHeader.getName());
  }

  @Test
  void testGetAllOptionTags() {
    // Arrange and Act
    Vector actualAllOptionTags = (new RequireHeader("Option tag")).getAllOptionTags();

    // Assert
    assertEquals(1, actualAllOptionTags.size());
    assertEquals("Option tag", actualAllOptionTags.get(0));
  }

  @Test
  void testGetAllOptionTags2() {
    // Arrange and Act
    Vector actualAllOptionTags = (new RequireHeader(new String[]{"foo", "foo", "foo"})).getAllOptionTags();

    // Assert
    assertEquals(3, actualAllOptionTags.size());
    assertEquals("foo", actualAllOptionTags.get(0));
    assertEquals("foo", actualAllOptionTags.get(1));
    assertEquals("foo", actualAllOptionTags.get(2));
  }

  @Test
  void testGetAllOptionTags3() {
    // Arrange, Act and Assert
    assertNull((new RequireHeader(new Vector(1))).getAllOptionTags());
  }

  @Test
  void testGetAllOptionTags4() {
    // Arrange
    RequireHeader requireHeader = new RequireHeader("Option tag");
    requireHeader.addOptionTag("");

    // Act
    Vector actualAllOptionTags = requireHeader.getAllOptionTags();

    // Assert
    assertEquals(1, actualAllOptionTags.size());
    assertEquals("Option tag", actualAllOptionTags.get(0));
  }

  @Test
  void testHasOptionTag() {
    // Arrange, Act and Assert
    assertTrue((new RequireHeader("Option tag")).hasOptionTag("Option tag"));
    assertFalse((new RequireHeader(CoreSipHeaders.Require)).hasOptionTag("Option tag"));
    assertFalse((new RequireHeader(new String[]{"foo", "foo", "foo"})).hasOptionTag("Option tag"));
    assertFalse((new RequireHeader(new Vector(1))).hasOptionTag("Option tag"));
  }

  @Test
  void testHasOptionTag2() {
    // Arrange
    RequireHeader requireHeader = new RequireHeader("Option tag");
    requireHeader.addOptionTag("");

    // Act and Assert
    assertTrue(requireHeader.hasOptionTag("Option tag"));
  }
}

