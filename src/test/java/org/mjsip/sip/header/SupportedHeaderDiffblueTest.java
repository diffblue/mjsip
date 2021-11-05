package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class SupportedHeaderDiffblueTest {
  @Test
  void testAddOptionTag() {
    // Arrange
    SupportedHeader supportedHeader = new SupportedHeader("Option tag");

    // Act
    supportedHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag,Option tag", supportedHeader.getValue());
  }

  @Test
  void testAddOptionTag2() {
    // Arrange
    SupportedHeader supportedHeader = new SupportedHeader("");

    // Act
    supportedHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag", supportedHeader.getValue());
  }

  @Test
  void testAddOptionTag3() {
    // Arrange
    SupportedHeader supportedHeader = new SupportedHeader(new Vector(1));

    // Act
    supportedHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag", supportedHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    SupportedHeader actualSupportedHeader = new SupportedHeader("Option tag");

    // Assert
    Vector allOptionTags = actualSupportedHeader.getAllOptionTags();
    assertEquals(1, allOptionTags.size());
    assertEquals("Option tag", allOptionTags.get(0));
    assertEquals("Supported: Option tag\r\n", actualSupportedHeader.toString());
    assertEquals("Option tag", actualSupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Supported, actualSupportedHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SupportedHeader actualSupportedHeader = new SupportedHeader(new Vector(1));

    // Assert
    assertNull(actualSupportedHeader.getAllOptionTags());
    assertEquals(CoreSipHeaders.Supported, actualSupportedHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    SupportedHeader actualSupportedHeader = new SupportedHeader(vector);

    // Assert
    assertEquals("42", actualSupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Supported, actualSupportedHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    SupportedHeader actualSupportedHeader = new SupportedHeader(vector);

    // Assert
    assertEquals("42,42", actualSupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Supported, actualSupportedHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    SupportedHeader actualSupportedHeader = new SupportedHeader(vector);

    // Assert
    assertEquals("foo", actualSupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Supported, actualSupportedHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    SupportedHeader actualSupportedHeader = new SupportedHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualSupportedHeader.getValue());
    assertEquals("Hname", actualSupportedHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    SupportedHeader actualSupportedHeader = new SupportedHeader(header);

    // Assert
    assertEquals("42", actualSupportedHeader.getValue());
    assertEquals("Hname", actualSupportedHeader.getName());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    SupportedHeader actualSupportedHeader = new SupportedHeader(new String[]{"Option tags"});

    // Assert
    assertEquals("Option tags", actualSupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Supported, actualSupportedHeader.getName());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    SupportedHeader actualSupportedHeader = new SupportedHeader(new String[]{});

    // Assert
    assertNull(actualSupportedHeader.getAllOptionTags());
    assertEquals(CoreSipHeaders.Supported, actualSupportedHeader.getName());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    SupportedHeader actualSupportedHeader = new SupportedHeader(
        new String[]{CoreSipHeaders.Supported, CoreSipHeaders.Supported});

    // Assert
    assertEquals("Supported,Supported", actualSupportedHeader.getValue());
    assertEquals(CoreSipHeaders.Supported, actualSupportedHeader.getName());
  }

  @Test
  void testGetAllOptionTags() {
    // Arrange and Act
    Vector actualAllOptionTags = (new SupportedHeader("Option tag")).getAllOptionTags();

    // Assert
    assertEquals(1, actualAllOptionTags.size());
    assertEquals("Option tag", actualAllOptionTags.get(0));
  }

  @Test
  void testGetAllOptionTags2() {
    // Arrange and Act
    Vector actualAllOptionTags = (new SupportedHeader(new String[]{"foo", "foo", "foo"})).getAllOptionTags();

    // Assert
    assertEquals(3, actualAllOptionTags.size());
    assertEquals("foo", actualAllOptionTags.get(0));
    assertEquals("foo", actualAllOptionTags.get(1));
    assertEquals("foo", actualAllOptionTags.get(2));
  }

  @Test
  void testGetAllOptionTags3() {
    // Arrange, Act and Assert
    assertNull((new SupportedHeader(new Vector(1))).getAllOptionTags());
  }

  @Test
  void testGetAllOptionTags4() {
    // Arrange
    SupportedHeader supportedHeader = new SupportedHeader("Option tag");
    supportedHeader.addOptionTag("");

    // Act
    Vector actualAllOptionTags = supportedHeader.getAllOptionTags();

    // Assert
    assertEquals(1, actualAllOptionTags.size());
    assertEquals("Option tag", actualAllOptionTags.get(0));
  }

  @Test
  void testHasOptionTag() {
    // Arrange, Act and Assert
    assertTrue((new SupportedHeader("Option tag")).hasOptionTag("Option tag"));
    assertFalse((new SupportedHeader(CoreSipHeaders.Supported)).hasOptionTag("Option tag"));
    assertFalse((new SupportedHeader(new String[]{"foo", "foo", "foo"})).hasOptionTag("Option tag"));
    assertFalse((new SupportedHeader(new Vector(1))).hasOptionTag("Option tag"));
  }

  @Test
  void testHasOptionTag2() {
    // Arrange
    SupportedHeader supportedHeader = new SupportedHeader("Option tag");
    supportedHeader.addOptionTag("");

    // Act and Assert
    assertTrue(supportedHeader.hasOptionTag("Option tag"));
  }
}

