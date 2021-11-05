package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class SortedVectorDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertTrue((new SortedVector()).v.isEmpty());
    assertTrue((new SortedVector(mock(Comparator.class))).v.isEmpty());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SortedVector actualSortedVector = new SortedVector(new ArrayList<Object>());

    // Assert
    Vector vector = actualSortedVector.v;
    assertTrue(vector.isEmpty());
    assertNull(actualSortedVector.comparator);
    assertEquals(vector, actualSortedVector.toVector());
  }
}

