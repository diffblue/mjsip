package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class HeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    Header actualHeader = new Header();
    actualHeader.setValue("42");

    // Assert
    assertNull(actualHeader.getName());
    assertEquals("42", actualHeader.getValue());
    assertEquals("null: 42\r\n", actualHeader.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    Header actualHeader = new Header("Hname", "42");
    actualHeader.setValue("42");

    // Assert
    assertEquals("Hname", actualHeader.getName());
    assertEquals("42", actualHeader.getValue());
    assertEquals("Hname: 42\r\n", actualHeader.toString());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    Header actualHeader = new Header(new Header("Hname", "42"));

    // Assert
    assertEquals("Hname", actualHeader.getName());
    assertEquals("42", actualHeader.getValue());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    Header actualHeader = new Header(header);

    // Assert
    assertEquals("Hname", actualHeader.getName());
    assertEquals("42", actualHeader.getValue());
  }

  @Test
  void testClone() {
    // Arrange, Act and Assert
    assertEquals("Hname", ((Header) (new Header("Hname", "42")).clone()).getName());
    assertEquals("42", ((Header) (new Header("Hname", "42")).clone()).getValue());
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new Header("Hname", "42")).equals(null));
    assertFalse((new Header("Hname", "42")).equals("Different type to Header"));
  }

  @Test
  void testEquals2() {
    // Arrange
    Header header = new Header("Hname", "42");

    // Act and Assert
    assertTrue(header.equals(header));
    int expectedHashCodeResult = header.hashCode();
    assertEquals(expectedHashCodeResult, header.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    Header header = new Header("Hname", "42");
    Header header1 = new Header("Hname", "42");

    // Act and Assert
    assertTrue(header.equals(header1));
    int notExpectedHashCodeResult = header.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, header1.hashCode()));
  }

  @Test
  void testEquals4() {
    // Arrange
    Header header = new Header("42", "42");

    // Act and Assert
    assertFalse(header.equals(new Header("Hname", "42")));
  }

  @Test
  void testEquals5() {
    // Arrange
    Header header = new Header("Hname", "Hname");

    // Act and Assert
    assertFalse(header.equals(new Header("Hname", "42")));
  }

  @Test
  void testEquals6() {
    // Arrange
    Header header = new Header("Hname", "42");
    AcceptEncodingHeader acceptEncodingHeader = mock(AcceptEncodingHeader.class);
    when(acceptEncodingHeader.getName()).thenReturn("Name");

    // Act and Assert
    assertFalse(header.equals(acceptEncodingHeader));
  }

  @Test
  void testEquals7() {
    // Arrange
    Header header = new Header("Name", "42");
    AcceptEncodingHeader acceptEncodingHeader = mock(AcceptEncodingHeader.class);
    when(acceptEncodingHeader.getValue()).thenReturn("42");
    when(acceptEncodingHeader.getName()).thenReturn("Name");

    // Act and Assert
    assertTrue(header.equals(acceptEncodingHeader));
    int notExpectedHashCodeResult = header.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, acceptEncodingHeader.hashCode()));
  }
}

