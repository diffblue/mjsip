package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class BitStringDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    BitString actualBitString = new BitString(3);

    // Assert
    assertEquals(3, actualBitString.bits.length);
    assertEquals("000", actualBitString.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new BitString(-1));
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    BitString actualBitString = new BitString("Str");

    // Assert
    assertEquals(3, actualBitString.bits.length);
    assertEquals("000", actualBitString.toString());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    BitString actualBitString = new BitString(new BitSet(), 1, 3);

    // Assert
    assertEquals(3, actualBitString.bits.length);
    assertEquals("000", actualBitString.toString());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    BitString actualBitString = new BitString(new BitSet(), 0, 3);

    // Assert
    assertEquals(3, actualBitString.bits.length);
    assertEquals("000", actualBitString.toString());
  }

  @Test
  void testConstructor6() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new BitString(new BitSet(), 1, -1));

  }

  @Test
  void testConstructor7() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new BitString(new BitString("Str"), 1, 3));

  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    BitString actualBitString = new BitString(
        new BitString(new boolean[]{true, true, true, true, true, true, true, true}), 1, 3);

    // Assert
    assertEquals(3, actualBitString.bits.length);
    assertEquals("111", actualBitString.toString());
  }

  @Test
  void testConstructor9() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new BitString(new BitString("Str"), 1, -1));

  }

  @Test
  void testConstructor10() throws UnsupportedEncodingException {
    // Arrange and Act
    BitString actualBitString = new BitString("AAAAAAAA".getBytes("UTF-8"), 1, 3, true);

    // Assert
    assertEquals(24, actualBitString.bits.length);
    assertEquals("100000101000001010000010", actualBitString.toString());
  }

  @Test
  void testConstructor12() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new BitString("AAAAAAAA".getBytes("UTF-8"), 1, -1, true));

  }

  @Test
  void testConstructor13() throws UnsupportedEncodingException {
    // Arrange and Act
    BitString actualBitString = new BitString("AAAAAAAA".getBytes("UTF-8"), 1, 3, false);

    // Assert
    assertEquals(24, actualBitString.bits.length);
    assertEquals("010000010100000101000001", actualBitString.toString());
  }

  @Test
  void testConstructor14() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new BitString(new byte[]{}, 1, 3, false));

  }

  @Test
  void testConstructor15() throws UnsupportedEncodingException {
    // Arrange and Act
    BitString actualBitString = new BitString("AAAAAAAA".getBytes("UTF-8"), true);

    // Assert
    assertEquals(Double.SIZE, actualBitString.bits.length);
    assertEquals("1000001010000010100000101000001010000010100000101000001010000010", actualBitString.toString());
  }

  @Test
  void testConstructor16() throws UnsupportedEncodingException {
    // Arrange and Act
    BitString actualBitString = new BitString("AAAAAAAA".getBytes("UTF-8"), false);

    // Assert
    assertEquals(Double.SIZE, actualBitString.bits.length);
    assertEquals("0100000101000001010000010100000101000001010000010100000101000001", actualBitString.toString());
  }

  @Test
  void testConstructor17() {
    // Arrange and Act
    BitString actualBitString = new BitString(new boolean[]{true, true, true, true});

    // Assert
    assertEquals(4, actualBitString.bits.length);
    assertEquals("1111", actualBitString.toString());
  }

  @Test
  void testConstructor18() {
    // Arrange and Act
    BitString actualBitString = new BitString(new boolean[]{true, true, true, true}, 1, 3);

    // Assert
    assertEquals(3, actualBitString.bits.length);
    assertEquals("111", actualBitString.toString());
  }

  @Test
  void testConstructor19() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new BitString(new boolean[]{}, 1, 3));

  }

  @Test
  void testConstructor20() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new BitString(new boolean[]{true, true, true, true}, 1, -1));

  }

  @Test
  void testLength() {
    // Arrange, Act and Assert
    assertEquals(3, (new BitString("Str")).length());
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new BitString("Str")).equals(null));
    assertFalse((new BitString("Str")).equals("Different type to BitString"));
  }

  @Test
  void testEquals2() {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act and Assert
    assertTrue(bitString.equals(bitString));
    int expectedHashCodeResult = bitString.hashCode();
    assertEquals(expectedHashCodeResult, bitString.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    BitString bitString = new BitString("Str");
    BitString bitString1 = new BitString("Str");

    // Act and Assert
    assertTrue(bitString.equals(bitString1));
    int notExpectedHashCodeResult = bitString.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, bitString1.hashCode()));
  }

  @Test
  void testEquals4() {
    // Arrange
    BitString bitString = new BitString("42");

    // Act and Assert
    assertFalse(bitString.equals(new BitString("Str")));
  }

  @Test
  void testEquals5() {
    // Arrange
    BitString bitString = new BitString(new boolean[]{true, true, true, true, true, true, true, true}, 1, 3);

    // Act and Assert
    assertFalse(bitString.equals(new BitString("Str")));
  }

  @Test
  void testBitAt() {
    // Arrange, Act and Assert
    assertFalse((new BitString("Str")).bitAt(1));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new BitString("")).bitAt(1));
    assertTrue((new BitString(new boolean[]{true, true, true, true, true, true, true, true})).bitAt(1));
  }

  @Test
  void testSubstring() {
    // Arrange and Act
    BitString actualSubstringResult = (new BitString("Str")).substring(1);

    // Assert
    assertEquals(2, actualSubstringResult.bits.length);
    assertEquals("00", actualSubstringResult.toString());
  }

  @Test
  void testSubstring2() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> (new BitString("")).substring(1));
  }

  @Test
  void testSubstring3() {
    // Arrange and Act
    BitString actualSubstringResult = (new BitString(new boolean[]{true, true, true, true, true, true, true, true}))
        .substring(1);

    // Assert
    assertEquals(7, actualSubstringResult.bits.length);
    assertEquals("1111111", actualSubstringResult.toString());
  }

  @Test
  void testSubstring4() {
    // Arrange and Act
    BitString actualSubstringResult = (new BitString("42")).substring(0);

    // Assert
    assertEquals(2, actualSubstringResult.bits.length);
    assertEquals("00", actualSubstringResult.toString());
  }

  @Test
  void testSubstring5() {
    // Arrange and Act
    BitString actualSubstringResult = (new BitString("Str")).substring(1, 3);

    // Assert
    assertEquals(2, actualSubstringResult.bits.length);
    assertEquals("00", actualSubstringResult.toString());
  }

  @Test
  void testSubstring6() {
    // Arrange and Act
    BitString actualSubstringResult = (new BitString(3)).substring(1, 3);

    // Assert
    assertEquals(2, actualSubstringResult.bits.length);
    assertEquals("00", actualSubstringResult.toString());
  }

  @Test
  void testSubstring7() {
    // Arrange and Act
    BitString actualSubstringResult = (new BitString(new boolean[]{true, true, true, true, true, true, true, true}))
        .substring(1, 3);

    // Assert
    assertEquals(2, actualSubstringResult.bits.length);
    assertEquals("11", actualSubstringResult.toString());
  }

  @Test
  void testSubstring8() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> (new BitString("42")).substring(5, 3));
  }

  @Test
  void testStartsWith() {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act and Assert
    assertTrue(bitString.startsWith(new BitString("Str")));
  }

  @Test
  void testStartsWith2() {
    // Arrange
    BitString bitString = new BitString("42");

    // Act and Assert
    assertFalse(bitString.startsWith(new BitString("Str")));
  }

  @Test
  void testStartsWith3() {
    // Arrange
    BitString bitString = new BitString(new boolean[]{true, true, true, true, true, true, true, true});

    // Act and Assert
    assertFalse(bitString.startsWith(new BitString("Str")));
  }

  @Test
  void testStartsWith4() {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act and Assert
    assertFalse(bitString.startsWith(new BitString("Str"), 2));
  }

  @Test
  void testStartsWith5() {
    // Arrange
    BitString bitString = new BitString(new boolean[]{true, true, true, true, true, true, true, true});

    // Act and Assert
    assertFalse(bitString.startsWith(new BitString("Str"), 2));
  }

  @Test
  void testStartsWith6() throws UnsupportedEncodingException {
    // Arrange
    BitString bitString = new BitString("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), true);

    // Act and Assert
    assertTrue(bitString.startsWith(new BitString("Str"), 2));
  }

  @Test
  void testStartsWith7() {
    // Arrange
    BitString bitString = new BitString("");

    // Act and Assert
    assertFalse(bitString.startsWith(new BitString("Str"), -1));
  }

  @Test
  void testSetBit() {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act
    bitString.setBit(true, 1);

    // Assert
    assertEquals("010", bitString.toString());
  }

  @Test
  void testSetBit2() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new BitString("")).setBit(true, 1));
  }

  @Test
  void testSetBits() {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> bitString.setBits(new BitString("Str"), 2));
  }

  @Test
  void testSetBits2() {
    // Arrange
    BitString bitString = new BitString(new boolean[]{true, true, true, true, true, true, true, true});

    // Act
    bitString.setBits(new BitString("Str"), 2);

    // Assert
    assertEquals("11000111", bitString.toString());
  }

  @Test
  void testSetBits3() {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> bitString.setBits(new BitString(new boolean[]{true, true, true, true, true, true, true, true}), 2));
  }

  @Test
  void testGetBytes() {
    // Arrange and Act
    byte[] actualBytes = (new BitString("Str")).getBytes(true);

    // Assert
    assertEquals(1, actualBytes.length);
    assertEquals((byte) 0, actualBytes[0]);
  }

  @Test
  void testGetBytes3() {
    // Arrange and Act
    byte[] actualBytes = (new BitString("Str")).getBytes(false);

    // Assert
    assertEquals(1, actualBytes.length);
    assertEquals((byte) 0, actualBytes[0]);
  }

  @Test
  void testGetBytes5() throws UnsupportedEncodingException {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act and Assert
    assertEquals(1, bitString.getBytes("AAAAAAAA".getBytes("UTF-8"), 1, true));
  }

  @Test
  void testGetBytes6() throws UnsupportedEncodingException {
    // Arrange
    BitString bitString = new BitString(new boolean[]{true, true, true, true, true, true, true, true});

    // Act and Assert
    assertEquals(1, bitString.getBytes("AAAAAAAA".getBytes("UTF-8"), 1, true));
  }

  @Test
  void testGetBytes7() throws UnsupportedEncodingException {
    // Arrange
    BitString bitString = new BitString("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), true);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> bitString.getBytes("AAAAAAAA".getBytes("UTF-8"), 1, true));
  }

  @Test
  void testGetBytes8() throws UnsupportedEncodingException {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act and Assert
    assertEquals(1, bitString.getBytes("AAAAAAAA".getBytes("UTF-8"), 1, false));
  }

  @Test
  void testGetBytes9() throws UnsupportedEncodingException {
    // Arrange
    BitString bitString = new BitString(new boolean[]{true, true, true, true, true, true, true, true});

    // Act and Assert
    assertEquals(1, bitString.getBytes("AAAAAAAA".getBytes("UTF-8"), 1, false));
  }

  @Test
  void testIndexOf() {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act and Assert
    assertEquals(0, bitString.indexOf(new BitString("Str")));
  }

  @Test
  void testIndexOf2() {
    // Arrange
    BitString bitString = new BitString("42");

    // Act and Assert
    assertEquals(-1, bitString.indexOf(new BitString("Str")));
  }

  @Test
  void testIndexOf3() {
    // Arrange
    BitString bitString = new BitString(new boolean[]{true, true, true, true, true, true, true, true});

    // Act and Assert
    assertEquals(-1, bitString.indexOf(new BitString("Str")));
  }

  @Test
  void testIndexOf4() {
    // Arrange
    BitString bitString = new BitString(new boolean[]{true, true, true, true, true, true, true, true});

    // Act and Assert
    assertEquals(0, bitString.indexOf(new BitString(new boolean[]{true, true, true, true, true, true, true, true})));
  }

  @Test
  void testIndexOf5() {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act and Assert
    assertEquals(-1, bitString.indexOf(new BitString("Str"), 2));
  }

  @Test
  void testIndexOf6() {
    // Arrange
    BitString bitString = new BitString(new boolean[]{true, true, true, true, true, true, true, true});

    // Act and Assert
    assertEquals(-1, bitString.indexOf(new BitString("Str"), 2));
  }

  @Test
  void testIndexOf7() throws UnsupportedEncodingException {
    // Arrange
    BitString bitString = new BitString("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), true);

    // Act and Assert
    assertEquals(2, bitString.indexOf(new BitString("Str"), 2));
  }

  @Test
  void testIndexOf8() {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> bitString.indexOf(new BitString("Str"), -1));
  }

  @Test
  void testIndexOf9() throws UnsupportedEncodingException {
    // Arrange
    BitString bitString = new BitString("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), true);

    // Act and Assert
    assertEquals(-1,
        bitString.indexOf(new BitString(new boolean[]{true, true, true, true, true, true, true, true}), 2));
  }

  @Test
  void testInvert() {
    // Arrange
    BitString bitString = new BitString("Str");

    // Act and Assert
    assertSame(bitString, bitString.invert());
  }

  @Test
  void testInvert2() {
    // Arrange
    BitString bitString = new BitString(new boolean[]{true, true, true, true, true, true, true, true});

    // Act and Assert
    assertSame(bitString, bitString.invert());
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("000", (new BitString("Str")).toString());
    assertEquals("11111111", (new BitString(new boolean[]{true, true, true, true, true, true, true, true})).toString());
  }

  @Test
  void testGetBit() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse(BitString.getBit("AAAAAAAA".getBytes("UTF-8"), 1, true));
    assertTrue(BitString.getBit(new byte[]{3, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, true));
    assertTrue(BitString.getBit("AAAAAAAA".getBytes("UTF-8"), 0, true));
    assertTrue(BitString.getBit("AAAAAAAA".getBytes("UTF-8"), 1, false));
    assertFalse(BitString.getBit(new byte[]{3, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, false));
  }
}

