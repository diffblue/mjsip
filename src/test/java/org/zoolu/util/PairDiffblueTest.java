package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class PairDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    Pair actualPair = new Pair("O1", "O2");
    actualPair.setFirst("O1");
    actualPair.setSecond("O2");

    // Assert
    Object expectedFirst = actualPair.o1;
    assertSame(expectedFirst, actualPair.getFirst());
    Object expectedSecond = actualPair.o2;
    assertSame(expectedSecond, actualPair.getSecond());
    assertEquals("{O1,O2}", actualPair.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    Pair actualPair = new Pair(new Pair(new Pair(new Pair(new Pair(new Pair("O1", "O2"))))));

    // Assert
    assertEquals("O1", actualPair.getFirst());
    assertEquals("O2", actualPair.getSecond());
  }

  @Test
  void testSame() {
    // Arrange, Act and Assert
    assertFalse(Pair.same("Obj1", "Obj2"));
    assertTrue(Pair.same(null, null));
    assertFalse(Pair.same(null, "Obj2"));
  }

  @Test
  void testEquals() {
    // Arrange
    Pair pair = new Pair(new Pair(new Pair(new Pair(new Pair("O1", "O2")))));
    Pair pair1 = new Pair(new Pair(new Pair(new Pair(new Pair("O1", "O2")))));

    // Act and Assert
    assertTrue(pair.equals(pair1));
    int notExpectedHashCodeResult = pair.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, pair1.hashCode()));
  }

  @Test
  void testEquals2() {
    // Arrange
    Pair pair = new Pair(new Pair(new Pair(new Pair(new Pair(null, "O2")))));

    // Act and Assert
    assertFalse(pair.equals(new Pair(new Pair(new Pair(new Pair(new Pair("O1", "O2")))))));
  }

  @Test
  void testEquals3() {
    // Arrange
    Pair pair = new Pair(new Pair(new Pair(new Pair(new Pair(new Pair("O1", "O2"), "O2")))));

    // Act and Assert
    assertFalse(pair.equals(new Pair(new Pair(new Pair(new Pair(new Pair("O1", "O2")))))));
  }

  @Test
  void testEquals4() {
    // Arrange
    Pair pair = new Pair(new Pair(new Pair(new Pair(new Pair("O1", null)))));

    // Act and Assert
    assertFalse(pair.equals(new Pair(new Pair(new Pair(new Pair(new Pair("O1", "O2")))))));
  }

  @Test
  void testEquals5() {
    // Arrange
    Pair pair = new Pair(new Pair(new Pair(new Pair(new Pair(null, "O2")))));
    Pair pair1 = new Pair(new Pair(new Pair(new Pair(new Pair(null, "O2")))));

    // Act and Assert
    assertTrue(pair.equals(pair1));
    int notExpectedHashCodeResult = pair.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, pair1.hashCode()));
  }
}

