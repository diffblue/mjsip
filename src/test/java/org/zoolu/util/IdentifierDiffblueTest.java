package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class IdentifierDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertNull((new Identifier()).toString());
    assertEquals("42", (new Identifier("42")).toString());
    assertEquals("42", (new Identifier(new Identifier("42"))).toString());
    assertEquals("Id", (new Identifier(new Identifier(new Identifier("Id")))).toString());
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new Identifier("42")).equals(null));
    assertFalse((new Identifier("42")).equals("Different type to Identifier"));
  }

  @Test
  void testEquals2() {
    // Arrange
    Identifier identifier = new Identifier("42");

    // Act and Assert
    assertTrue(identifier.equals(identifier));
    int expectedHashCodeResult = identifier.hashCode();
    assertEquals(expectedHashCodeResult, identifier.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    Identifier identifier = new Identifier("42");
    Identifier identifier1 = new Identifier("42");

    // Act and Assert
    assertTrue(identifier.equals(identifier1));
    int expectedHashCodeResult = identifier.hashCode();
    assertEquals(expectedHashCodeResult, identifier1.hashCode());
  }

  @Test
  void testEquals4() {
    // Arrange
    Identifier identifier = new Identifier("Id");

    // Act and Assert
    assertFalse(identifier.equals(new Identifier("42")));
  }

  @Test
  void testEquals5() {
    // Arrange
    Identifier identifier = new Identifier();

    // Act and Assert
    assertFalse(identifier.equals(new Identifier("42")));
  }
}

