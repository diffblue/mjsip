package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class CircularEnumerationDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertTrue((new CircularEnumeration(new Vector(1))).v.isEmpty());
  }

  @Test
  void testNextElement() {
    // Arrange, Act and Assert
    assertNull((new CircularEnumeration(new Vector(1))).nextElement());
  }

  @Test
  void testNextElement2() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    CircularEnumeration circularEnumeration = new CircularEnumeration(vector);

    // Act and Assert
    assertEquals("42", circularEnumeration.nextElement());
    assertEquals(1, circularEnumeration.i);
  }
}

