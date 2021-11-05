package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Objects;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class MultipleHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    MultipleHeader actualMultipleHeader = new MultipleHeader();
    actualMultipleHeader.setCommaSeparated(true);
    Vector vector = new Vector(1);
    actualMultipleHeader.setValues(vector);

    // Assert
    assertNull(actualMultipleHeader.getName());
    Vector values = actualMultipleHeader.getValues();
    assertSame(vector, values);
    assertEquals(actualMultipleHeader.getHeaders(), values);
    assertTrue(actualMultipleHeader.isCommaSeparated());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    MultipleHeader actualMultipleHeader = new MultipleHeader("Hname");
    actualMultipleHeader.setCommaSeparated(true);
    Vector vector = new Vector(1);
    actualMultipleHeader.setValues(vector);

    // Assert
    assertEquals("Hname", actualMultipleHeader.getName());
    Vector values = actualMultipleHeader.getValues();
    assertSame(vector, values);
    assertEquals(actualMultipleHeader.getHeaders(), values);
    assertTrue(actualMultipleHeader.isCommaSeparated());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Vector vector = new Vector(1);

    // Act
    MultipleHeader actualMultipleHeader = new MultipleHeader("Hname", vector);
    actualMultipleHeader.setCommaSeparated(true);
    Vector vector1 = new Vector(1);
    actualMultipleHeader.setValues(vector1);

    // Assert
    assertEquals("Hname", actualMultipleHeader.getName());
    Vector values = actualMultipleHeader.getValues();
    assertSame(vector1, values);
    assertEquals(vector, values);
    assertEquals(actualMultipleHeader.getHeaders(), values);
    assertTrue(actualMultipleHeader.isCommaSeparated());
  }

  @Test
  void testIsCommaSeparated() {
    // Arrange, Act and Assert
    assertFalse(MultipleHeader.isCommaSeparated(new Header("Hname", "42")));
  }

  @Test
  void testIsCommaSeparated2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse(MultipleHeader.isCommaSeparated(header));
  }

  @Test
  void testIsCommaSeparated3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act and Assert
    assertFalse(MultipleHeader.isCommaSeparated(header));
  }

  @Test
  void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new MultipleHeader("Hname")).size());
  }

  @Test
  void testClone() {
    // Arrange, Act and Assert
    assertTrue(((MultipleHeader) (new MultipleHeader("Hname")).clone()).isCommaSeparated());
    Vector expectedValues = ((MultipleHeader) (new MultipleHeader("Hname")).clone()).getHeaders();
    assertEquals(expectedValues, ((MultipleHeader) (new MultipleHeader("Hname")).clone()).getValues());
    assertEquals("Hname", ((MultipleHeader) (new MultipleHeader("Hname")).clone()).getName());
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertThrows(NullPointerException.class, () -> (new MultipleHeader("Hname")).equals(null));
    assertThrows(ClassCastException.class,
        () -> (new MultipleHeader("Hname")).equals("Different type to MultipleHeader"));
  }

  @Test
  void testEquals2() {
    // Arrange
    MultipleHeader multipleHeader = new MultipleHeader("Hname");

    // Act and Assert
    assertTrue(multipleHeader.equals(multipleHeader));
    int expectedHashCodeResult = multipleHeader.hashCode();
    assertEquals(expectedHashCodeResult, multipleHeader.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    MultipleHeader multipleHeader = new MultipleHeader("Hname");
    MultipleHeader multipleHeader1 = new MultipleHeader("Hname");

    // Act and Assert
    assertTrue(multipleHeader.equals(multipleHeader1));
    int notExpectedHashCodeResult = multipleHeader.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, multipleHeader1.hashCode()));
  }

  @Test
  void testEquals4() {
    // Arrange
    MultipleHeader multipleHeader = new MultipleHeader("java.lang.String");

    // Act and Assert
    assertFalse(multipleHeader.equals(new MultipleHeader("Hname")));
  }

  @Test
  void testEquals5() {
    // Arrange
    MultipleHeader multipleHeader = new MultipleHeader(new Header("Hname", "42"));

    // Act and Assert
    assertFalse(multipleHeader.equals(new MultipleHeader("Hname")));
  }

  @Test
  void testSetHeaders() {
    // Arrange
    MultipleHeader multipleHeader = new MultipleHeader("Hname");
    Vector vector = new Vector(1);

    // Act
    multipleHeader.setHeaders(vector);

    // Assert
    assertEquals(vector, multipleHeader.getValues());
  }

  @Test
  void testRemoveTop() {
    // Arrange
    MultipleHeader multipleHeader = new MultipleHeader("Hname");
    multipleHeader.addTop(new Header("Hname", "42"));

    // Act
    multipleHeader.removeTop();

    // Assert
    Vector expectedHeaders = multipleHeader.values;
    assertEquals(expectedHeaders, multipleHeader.getHeaders());
  }

  @Test
  void testRemoveBottom() {
    // Arrange
    MultipleHeader multipleHeader = new MultipleHeader("Hname");
    multipleHeader.addTop(new Header("Hname", "42"));

    // Act
    multipleHeader.removeBottom();

    // Assert
    Vector expectedHeaders = multipleHeader.values;
    assertEquals(expectedHeaders, multipleHeader.getHeaders());
  }
}

