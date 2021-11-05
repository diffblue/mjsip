package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class SimpleDigestDiffblueTest {
  @Test
  void testAsHex() {
    // Arrange
    SimpleDigest simpleDigest = new SimpleDigest(3);

    // Act and Assert
    assertEquals("030507", simpleDigest.asHex());
    assertEquals(3, simpleDigest.getDigest().length);
    assertEquals(3, simpleDigest.index);
  }

  @Test
  void testAsHex2() {
    // Arrange
    SimpleDigest simpleDigest = new SimpleDigest(240);

    // Act and Assert
    assertEquals("f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f010101010101010101010101010101010303030303030303030303030303030301010"
        + "1010101010101010101010101010707070707070707070707070707070701010101010101010101010101010101030303030"
        + "30303030303030303030303010101010101010101010101010101010f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0101010101010"
        + "1010101010101010101030303030303030303030303030303030101010101010101010101010101010107070707070707070"
        + "70707070707070701010101010101010101010101010101030303030303030303030303030303030", simpleDigest.asHex());
    assertEquals(240, simpleDigest.getDigest().length);
    assertEquals(240, simpleDigest.index);
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    SimpleDigest actualSimpleDigest = new SimpleDigest(3);

    // Assert
    assertEquals(3, actualSimpleDigest.getDigest().length);
    assertFalse(actualSimpleDigest.is_done);
    assertEquals(3, actualSimpleDigest.index);
    assertEquals((byte) 0, actualSimpleDigest.add_term);
  }

  @Test
  void testConstructor2() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new SimpleDigest(-1));
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    SimpleDigest actualSimpleDigest = new SimpleDigest(3, "Str");

    // Assert
    assertEquals(3, actualSimpleDigest.getDigest().length);
    assertFalse(actualSimpleDigest.is_done);
    assertEquals(3, actualSimpleDigest.index);
    assertEquals('9', actualSimpleDigest.add_term);
  }

  @Test
  void testConstructor4() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new SimpleDigest(0, "Str"));

  }

  @Test
  void testConstructor5() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new SimpleDigest(-1, "Str"));

  }

  @Test
  void testConstructor6() throws UnsupportedEncodingException {
    // Arrange and Act
    SimpleDigest actualSimpleDigest = new SimpleDigest(3, "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(3, actualSimpleDigest.getDigest().length);
    assertFalse(actualSimpleDigest.is_done);
    assertEquals(3, actualSimpleDigest.index);
    assertEquals((byte) 8, actualSimpleDigest.add_term);
  }

  @Test
  void testConstructor7() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new SimpleDigest(0, "AAAAAAAA".getBytes("UTF-8")));

  }

  @Test
  void testConstructor8() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new SimpleDigest(-1, "AAAAAAAA".getBytes("UTF-8")));

  }

  @Test
  void testConstructor10() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new SimpleDigest(0, "AAAAAAAA".getBytes("UTF-8"), 2, 3));

  }

  @Test
  void testConstructor11() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new SimpleDigest(-1, "AAAAAAAA".getBytes("UTF-8"), 2, 3));

  }

  @Test
  void testConstructor12() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new SimpleDigest(3, new byte[]{}, 2, 3));

  }

  @Test
  void testUpdate() {
    // Arrange
    SimpleDigest simpleDigest = new SimpleDigest(3);

    // Act
    MessageDigest actualUpdateResult = simpleDigest.update("Str");

    // Assert
    assertSame(simpleDigest, actualUpdateResult);
    assertEquals(3, actualUpdateResult.getDigest().length);
    assertEquals(3, ((SimpleDigest) actualUpdateResult).index);
    assertEquals('9', ((SimpleDigest) actualUpdateResult).add_term);
  }

  @Test
  void testUpdate4() throws UnsupportedEncodingException {
    // Arrange
    SimpleDigest simpleDigest = new SimpleDigest(8);

    // Act
    MessageDigest actualUpdateResult = simpleDigest.update("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertSame(simpleDigest, actualUpdateResult);
    assertEquals(8, actualUpdateResult.getDigest().length);
    assertEquals(8, ((SimpleDigest) actualUpdateResult).index);
    assertEquals((byte) 8, ((SimpleDigest) actualUpdateResult).add_term);
  }

  @Test
  void testUpdate7() throws UnsupportedEncodingException {
    // Arrange
    SimpleDigest simpleDigest = new SimpleDigest(0);

    // Act and Assert
    assertSame(simpleDigest, simpleDigest.update("AAAAAAAA".getBytes("UTF-8"), 2, 0));
  }

  @Test
  void testDoFinal() {
    // Arrange
    SimpleDigest simpleDigest = new SimpleDigest(3);

    // Act
    byte[] actualDoFinalResult = simpleDigest.doFinal();

    // Assert
    assertSame(simpleDigest.message_digest, actualDoFinalResult);
    assertEquals(3, actualDoFinalResult.length);
    assertEquals((byte) 3, actualDoFinalResult[0]);
    assertEquals((byte) 5, actualDoFinalResult[1]);
    assertEquals((byte) 7, actualDoFinalResult[2]);
    assertSame(actualDoFinalResult, simpleDigest.getDigest());
    assertEquals(3, simpleDigest.index);
  }

  @Test
  void testDigest2() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> SimpleDigest.digest(0, "foo"));
  }

  @Test
  void testDigest4() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> SimpleDigest.digest(-1, "Str"));
  }

  @Test
  void testDigest6() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> SimpleDigest.digest(0, new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}));
  }

  @Test
  void testDigest7() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> SimpleDigest.digest(-1, "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testDigest9() {
    // Arrange and Act
    byte[] actualDigestResult = SimpleDigest.digest(1, new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 0);

    // Assert
    assertEquals(1, actualDigestResult.length);
    assertEquals((byte) 1, actualDigestResult[0]);
  }

  @Test
  void testDigest10() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> SimpleDigest.digest(0, new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 1));
  }

  @Test
  void testDigest11() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> SimpleDigest.digest(-1, "AAAAAAAA".getBytes("UTF-8"), 2, 3));
  }

  @Test
  void testDigest12() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> SimpleDigest.digest(3, new byte[]{}, 2, 3));
  }

  @Test
  void testGetDigest() {
    // Arrange
    SimpleDigest simpleDigest = new SimpleDigest(3);

    // Act
    byte[] actualDigest = simpleDigest.getDigest();

    // Assert
    assertSame(simpleDigest.message_digest, actualDigest);
    assertEquals(3, actualDigest.length);
    assertEquals((byte) 3, actualDigest[0]);
    assertEquals((byte) 5, actualDigest[1]);
    assertEquals((byte) 7, actualDigest[2]);
    assertEquals(3, simpleDigest.index);
  }
}

