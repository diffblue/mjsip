package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class ByteUtilsDiffblueTest {
  @Test
  void testCopy() throws UnsupportedEncodingException {
    // Arrange and Act
    byte[] actualCopyResult = ByteUtils.copy("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(8, actualCopyResult.length);
    assertEquals('A', actualCopyResult[0]);
    assertEquals('A', actualCopyResult[1]);
    assertEquals('A', actualCopyResult[2]);
    assertEquals('A', actualCopyResult[3]);
    assertEquals('A', actualCopyResult[4]);
    assertEquals('A', actualCopyResult[5]);
    assertEquals('A', actualCopyResult[6]);
    assertEquals('A', actualCopyResult[7]);
  }

  @Test
  void testCopy2() throws UnsupportedEncodingException {
    // Arrange and Act
    byte[] actualCopyResult = ByteUtils.copy("AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(3, actualCopyResult.length);
    assertEquals('A', actualCopyResult[0]);
    assertEquals('A', actualCopyResult[1]);
    assertEquals('A', actualCopyResult[2]);
  }

  @Test
  void testCopy3() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> ByteUtils.copy(new byte[]{}, 1, -1));
  }

  @Test
  void testMatch() throws UnsupportedEncodingException {
    // Arrange
    byte[] buf1 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertTrue(ByteUtils.match(buf1, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testMatch2() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse(
        ByteUtils.match(new byte[]{'A', 0, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testMatch3() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.match(new byte[]{}, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testMatch4() throws UnsupportedEncodingException {
    // Arrange
    byte[] buf1 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertFalse(ByteUtils.match(buf1, 1, 0, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testMatch5() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.match("AAAAAAAA".getBytes("UTF-8"), 1, 3, new byte[]{}, 1, 3));
  }

  @Test
  void testMatch6() throws UnsupportedEncodingException {
    // Arrange
    byte[] a1 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertTrue(ByteUtils.match(a1, "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testMatch7() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse(ByteUtils.match(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testMatch8() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse(ByteUtils.match(new byte[]{}, "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testCompare() throws UnsupportedEncodingException {
    // Arrange
    byte[] buf1 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, ByteUtils.compare(buf1, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testCompare2() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(-1,
        ByteUtils.compare(new byte[]{'A', 0, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testCompare3() throws UnsupportedEncodingException {
    // Arrange
    byte[] buf1 = "A￿AAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, ByteUtils.compare(buf1, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testCompare4() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.compare(new byte[]{}, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testCompare5() throws UnsupportedEncodingException {
    // Arrange
    byte[] buf1 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, ByteUtils.compare(buf1, 1, 0, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testCompare6() throws UnsupportedEncodingException {
    // Arrange
    byte[] buf1 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, ByteUtils.compare(buf1, 1, 255, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testCompare7() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.compare("AAAAAAAA".getBytes("UTF-8"), 1, 3, new byte[]{}, 1, 3));
  }

  @Test
  void testCompare8() throws UnsupportedEncodingException {
    // Arrange
    byte[] a1 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, ByteUtils.compare(a1, "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testCompare9() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(-1, ByteUtils.compare(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testCompare10() throws UnsupportedEncodingException {
    // Arrange
    byte[] a1 = "￿AAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, ByteUtils.compare(a1, "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testCompare11() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(-1, ByteUtils.compare(new byte[]{}, "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testCompare12() throws UnsupportedEncodingException {
    // Arrange
    byte[] a1 = "AAAAAAAAAAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, ByteUtils.compare(a1, "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testIndexOf() throws UnsupportedEncodingException {
    // Arrange
    byte[] buf1 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, ByteUtils.indexOf(buf1, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testIndexOf2() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(-1,
        ByteUtils.indexOf(new byte[]{'A', 0, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testIndexOf3() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.indexOf(new byte[]{}, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testIndexOf4() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.indexOf("AAAAAAAA".getBytes("UTF-8"), 1, 3, new byte[]{}, 1, 3));
  }

  @Test
  void testIndexOf5() throws UnsupportedEncodingException {
    // Arrange
    byte[] a1 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, ByteUtils.indexOf(a1, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testIndexOf6() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, ByteUtils.indexOf(new byte[]{}, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testIndexOf7() throws UnsupportedEncodingException {
    // Arrange
    byte[] a1 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.indexOf(a1, "AAAAAAAA".getBytes("UTF-8"), 1, Integer.MIN_VALUE));
  }

  @Test
  void testIndexOf8() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils
        .indexOf(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, "AAAAAAAA".getBytes("UTF-8"), 1, Integer.MIN_VALUE));
  }

  @Test
  void testConcat() throws UnsupportedEncodingException {
    // Arrange
    byte[] src1 = "AAAAAAAA".getBytes("UTF-8");

    // Act
    byte[] actualConcatResult = ByteUtils.concat(src1, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(6, actualConcatResult.length);
    assertEquals('A', actualConcatResult[0]);
    assertEquals('A', actualConcatResult[1]);
    assertEquals('A', actualConcatResult[2]);
    assertEquals('A', actualConcatResult[3]);
    assertEquals('A', actualConcatResult[4]);
    assertEquals('A', actualConcatResult[5]);
  }

  @Test
  void testConcat2() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class,
        () -> ByteUtils.concat(new byte[]{}, 1, Integer.MIN_VALUE, "AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testConcat3() throws UnsupportedEncodingException {
    // Arrange
    byte[] src1 = "AAAAAAAA".getBytes("UTF-8");
    byte[] src2 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(6, ByteUtils.concat(src1, 1, 3, src2, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testConcat4() throws UnsupportedEncodingException {
    // Arrange
    byte[] src1 = "AAAAAAAA".getBytes("UTF-8");
    byte[] src2 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(6, ByteUtils.concat(src1, 0, 3, src2, 1, 3, "AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testConcat5() throws UnsupportedEncodingException {
    // Arrange
    byte[] src1 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(Short.SIZE, ByteUtils.concat(src1, "AAAAAAAA".getBytes("UTF-8")).length);
  }

  @Test
  void testConcat6() throws UnsupportedEncodingException {
    // Arrange
    byte[] src2 = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(8, ByteUtils.concat(new byte[]{}, src2, "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testUByte() {
    // Arrange, Act and Assert
    assertEquals((short) 65, ByteUtils.uByte((byte) 'A'));
  }

  @Test
  void testUInt() {
    // Arrange, Act and Assert
    assertEquals(1L, ByteUtils.uInt(1));
  }

  @Test
  void testBytesToHexString() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("4141414141414141", ByteUtils.bytesToHexString("AAAAAAAA".getBytes("UTF-8")));
    assertNull(ByteUtils.bytesToHexString(null));
    assertEquals("414141", ByteUtils.bytesToHexString("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    assertEquals("", ByteUtils.bytesToHexString(new byte[]{}, 1, 0));
    assertThrows(NegativeArraySizeException.class, () -> ByteUtils.bytesToHexString(new byte[]{}, 1, -1));
  }

  @Test
  void testBytesToFormattedHexString() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("4141414141414141",
        ByteUtils.bytesToFormattedHexString("AAAAAAAA".getBytes("UTF-8"), 'A', LogRotationWriter.HOUR));
    assertEquals("41A41A41A41A41A41A41A41",
        ByteUtils.bytesToFormattedHexString(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 'A', 1));
    assertThrows(NegativeArraySizeException.class,
        () -> ByteUtils.bytesToFormattedHexString(new byte[]{}, 'A', LogRotationWriter.HOUR));
    assertThrows(ArithmeticException.class,
        () -> ByteUtils.bytesToFormattedHexString("AAAAAAAA".getBytes("UTF-8"), 'A', 0));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.bytesToFormattedHexString("AAAAAAAA".getBytes("UTF-8"), 'A', -1));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.bytesToFormattedHexString("AAAAAAAA".getBytes("UTF-8"), 'A', Integer.MIN_VALUE));
    assertEquals("414141",
        ByteUtils.bytesToFormattedHexString("AAAAAAAA".getBytes("UTF-8"), 1, 3, 'A', LogRotationWriter.HOUR));
    assertThrows(NegativeArraySizeException.class,
        () -> ByteUtils.bytesToFormattedHexString(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 0, 'A', 1));
    assertEquals("41",
        ByteUtils.bytesToFormattedHexString(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 1, 'A', 1));
    assertEquals("41A41",
        ByteUtils.bytesToFormattedHexString(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 2, 'A', 1));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.bytesToFormattedHexString(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 8, 'A', 1));
    assertThrows(ArithmeticException.class,
        () -> ByteUtils.bytesToFormattedHexString("AAAAAAAA".getBytes("UTF-8"), 1, 3, 'A', 0));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.bytesToFormattedHexString("AAAAAAAA".getBytes("UTF-8"), 1, 3, 'A', -1));
  }

  @Test
  void testTrimHexString() {
    // Arrange, Act and Assert
    assertEquals("0123456789ABCDEF", ByteUtils.trimHexString("0123456789ABCDEF"));
    assertEquals("f", ByteUtils.trimHexString("foo"));
  }

  @Test
  void testHexStringToBytes2() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> ByteUtils.hexStringToBytes("foo"));
  }

  @Test
  void testHexStringToBytes3() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> ByteUtils.hexStringToBytes("Str"));
  }

  @Test
  void testHexStringToBytes4() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.hexStringToBytes("0123456789ABCDEF", "AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testHexStringToBytes5() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class,
        () -> ByteUtils.hexStringToBytes("foo", new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1));
  }

  @Test
  void testHexStringToBytes6() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> ByteUtils.hexStringToBytes("Str", "AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testHexStringToBytes7() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(1, ByteUtils.hexStringToBytes("42", "AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testFormattedHexStringToBytes2() {
    // Arrange and Act
    byte[] actualFormattedHexStringToBytesResult = ByteUtils.formattedHexStringToBytes("42", 'A');

    // Assert
    assertEquals(1, actualFormattedHexStringToBytesResult.length);
    assertEquals('B', actualFormattedHexStringToBytesResult[0]);
  }

  @Test
  void testFormattedHexStringToBytes4() {
    // Arrange and Act
    byte[] actualFormattedHexStringToBytesResult = ByteUtils.formattedHexStringToBytes("42", 'A', 3);

    // Assert
    assertEquals(3, actualFormattedHexStringToBytesResult.length);
    assertEquals('B', actualFormattedHexStringToBytesResult[0]);
    assertEquals((byte) 0, actualFormattedHexStringToBytesResult[1]);
    assertEquals((byte) 0, actualFormattedHexStringToBytesResult[2]);
  }

  @Test
  void testFormattedHexStringToBytes5() {
    // Arrange and Act
    byte[] actualFormattedHexStringToBytesResult = ByteUtils.formattedHexStringToBytes("42", 'A', -1);

    // Assert
    assertEquals(1, actualFormattedHexStringToBytesResult.length);
    assertEquals('B', actualFormattedHexStringToBytesResult[0]);
  }

  @Test
  void testAsHex() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("4141414141414141", ByteUtils.asHex("AAAAAAAA".getBytes("UTF-8")));
    assertNull(ByteUtils.asHex(null));
    assertEquals("414141", ByteUtils.asHex("AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testHexToBytes2() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> ByteUtils.hexToBytes("foo"));
  }

  @Test
  void testHexToBytes3() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> ByteUtils.hexToBytes("StrStr"));
  }

  @Test
  void testHexToBytes4() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> ByteUtils.hexToBytes("foo", 0));
  }

  @Test
  void testHexToBytes5() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> ByteUtils.hexToBytes("Str", 3));
  }

  @Test
  void testHexToBytes6() {
    // Arrange and Act
    byte[] actualHexToBytesResult = ByteUtils.hexToBytes("42", 3);

    // Assert
    assertEquals(3, actualHexToBytesResult.length);
    assertEquals('B', actualHexToBytesResult[0]);
    assertEquals((byte) 0, actualHexToBytesResult[1]);
    assertEquals((byte) 0, actualHexToBytesResult[2]);
  }

  @Test
  void testHexToBytes7() {
    // Arrange, Act and Assert
    assertEquals(58, ByteUtils.hexToBytes("0123456789ABCDEF", 58).length);
  }

  @Test
  void testHexToBytes9() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> ByteUtils.hexToBytes("Str", -1));
  }

  @Test
  void testHexToBytes10() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class,
        () -> ByteUtils.hexToBytes("foo", new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1));
  }

  @Test
  void testHexToBytes11() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> ByteUtils.hexToBytes("Str", "AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testHexToBytes12() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(1, ByteUtils.hexToBytes("42", "AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testBytesToAsciiString() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("AAAAAAAA", ByteUtils.bytesToAsciiString("AAAAAAAA".getBytes("UTF-8")));
    assertEquals(".AAAAAAA", ByteUtils.bytesToAsciiString(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}));
    assertEquals(".AAAAAAA",
        ByteUtils.bytesToAsciiString(new byte[]{Byte.MAX_VALUE, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}));
    assertEquals("AAA", ByteUtils.bytesToAsciiString("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    assertEquals(".AA", ByteUtils.bytesToAsciiString(new byte[]{'A', 0, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
    assertEquals(".AA",
        ByteUtils.bytesToAsciiString(new byte[]{'A', Byte.MAX_VALUE, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.bytesToAsciiString(new byte[]{}, 1, 3));
    assertThrows(NegativeArraySizeException.class,
        () -> ByteUtils.bytesToAsciiString("AAAAAAAA".getBytes("UTF-8"), 1, -1));
  }

  @Test
  void testAsAscii() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("AAAAAAAA", ByteUtils.asAscii("AAAAAAAA".getBytes("UTF-8")));
    assertEquals(".AAAAAAA", ByteUtils.asAscii(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}));
    assertEquals(".AAAAAAA", ByteUtils.asAscii(new byte[]{Byte.MAX_VALUE, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}));
    assertEquals("AAA", ByteUtils.asAscii("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    assertEquals(".AA", ByteUtils.asAscii(new byte[]{'A', 0, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
    assertEquals(".AA", ByteUtils.asAscii(new byte[]{'A', Byte.MAX_VALUE, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.asAscii(new byte[]{}, 1, 3));
    assertThrows(NegativeArraySizeException.class, () -> ByteUtils.asAscii("AAAAAAAA".getBytes("UTF-8"), 1, -1));
  }

  @Test
  void testBytesToBinString() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("0100000101000001010000010100000101000001010000010100000101000001",
        ByteUtils.bytesToBinString("AAAAAAAA".getBytes("UTF-8")));
    assertEquals("010000010100000101000001", ByteUtils.bytesToBinString("AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testAsBinary() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("0100000101000001010000010100000101000001010000010100000101000001",
        ByteUtils.asBinary("AAAAAAAA".getBytes("UTF-8")));
    assertEquals("010000010100000101000001", ByteUtils.asBinary("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.asBinary(new byte[]{}, 1, 3));
  }

  @Test
  void testFourBytesToInt() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(1094795585L, ByteUtils.fourBytesToInt("AAAAAAAA".getBytes("UTF-8")));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.fourBytesToInt(new byte[]{}));
    assertEquals(1094795585L, ByteUtils.fourBytesToInt("AAAAAAAA".getBytes("UTF-8"), 1));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.fourBytesToInt(new byte[]{}, 1));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.fourBytesToInt("AAAAAAAA".getBytes("UTF-8"), 6));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.fourBytesToInt("AAAAAAAA".getBytes("UTF-8"), 7));
  }

  @Test
  void testEightBytesToInt() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(4702111234474983745L, ByteUtils.eightBytesToInt("AAAAAAAA".getBytes("UTF-8")));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.eightBytesToInt(new byte[]{}));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.eightBytesToInt("AAAAAAAA".getBytes("UTF-8"), 1));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.eightBytesToInt(new byte[]{}, 1));
    assertEquals(4702111234474983745L, ByteUtils.eightBytesToInt("AAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.eightBytesToInt("AAAAAAAA".getBytes("UTF-8"), 2));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.eightBytesToInt("AAAAAAAA".getBytes("UTF-8"), 3));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.eightBytesToInt("AAAAAAAA".getBytes("UTF-8"), 4));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.eightBytesToInt("AAAAAAAA".getBytes("UTF-8"), 5));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.eightBytesToInt("AAAAAAAA".getBytes("UTF-8"), 6));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.eightBytesToInt("AAAAAAAA".getBytes("UTF-8"), 7));
  }

  @Test
  void testNBytesToInt() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(4702111234474983745L, ByteUtils.nBytesToInt("AAAAAAAA".getBytes("UTF-8")));
    assertEquals(4276545L, ByteUtils.nBytesToInt("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.nBytesToInt(new byte[]{}, 1, 3));
  }

  @Test
  void testIntToTwoBytes() {
    // Arrange and Act
    byte[] actualIntToTwoBytesResult = ByteUtils.intToTwoBytes(42);

    // Assert
    assertEquals(2, actualIntToTwoBytesResult.length);
    assertEquals((byte) 0, actualIntToTwoBytesResult[0]);
    assertEquals('*', actualIntToTwoBytesResult[1]);
  }

  @Test
  void testIntToTwoBytes2() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.intToTwoBytes(42, new byte[]{}, 1));
  }

  @Test
  void testIntToTwoBytes3() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToTwoBytes(42, "AAAAAAAA".getBytes("UTF-8"), 7));
  }

  @Test
  void testIntToFourBytes() {
    // Arrange and Act
    byte[] actualIntToFourBytesResult = ByteUtils.intToFourBytes(42L);

    // Assert
    assertEquals(4, actualIntToFourBytesResult.length);
    assertEquals((byte) 0, actualIntToFourBytesResult[0]);
    assertEquals((byte) 0, actualIntToFourBytesResult[1]);
    assertEquals((byte) 0, actualIntToFourBytesResult[2]);
    assertEquals('*', actualIntToFourBytesResult[3]);
  }

  @Test
  void testIntToFourBytes2() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.intToFourBytes(42L, new byte[]{}, 1));
  }

  @Test
  void testIntToFourBytes3() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToFourBytes(42L, "AAAAAAAA".getBytes("UTF-8"), 6));
  }

  @Test
  void testIntToFourBytes4() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToFourBytes(42L, "AAAAAAAA".getBytes("UTF-8"), 7));
  }

  @Test
  void testIntToSixBytes() {
    // Arrange and Act
    byte[] actualIntToSixBytesResult = ByteUtils.intToSixBytes(42L);

    // Assert
    assertEquals(6, actualIntToSixBytesResult.length);
    assertEquals((byte) 0, actualIntToSixBytesResult[0]);
    assertEquals((byte) 0, actualIntToSixBytesResult[1]);
    assertEquals((byte) 0, actualIntToSixBytesResult[2]);
    assertEquals((byte) 0, actualIntToSixBytesResult[3]);
    assertEquals((byte) 0, actualIntToSixBytesResult[4]);
    assertEquals('*', actualIntToSixBytesResult[5]);
  }

  @Test
  void testIntToSixBytes2() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.intToSixBytes(42L, new byte[]{}, 1));
  }

  @Test
  void testIntToSixBytes3() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToSixBytes(42L, "AAAAAAAA".getBytes("UTF-8"), 3));
  }

  @Test
  void testIntToSixBytes4() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToSixBytes(42L, "AAAAAAAA".getBytes("UTF-8"), 4));
  }

  @Test
  void testIntToSixBytes5() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToSixBytes(42L, "AAAAAAAA".getBytes("UTF-8"), 6));
  }

  @Test
  void testIntToSixBytes6() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToSixBytes(42L, "AAAAAAAA".getBytes("UTF-8"), 5));
  }

  @Test
  void testIntToSixBytes7() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToSixBytes(1L, new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 7));
  }

  @Test
  void testIntToEightBytes() {
    // Arrange and Act
    byte[] actualIntToEightBytesResult = ByteUtils.intToEightBytes(42L);

    // Assert
    assertEquals(8, actualIntToEightBytesResult.length);
    assertEquals((byte) 0, actualIntToEightBytesResult[0]);
    assertEquals((byte) 0, actualIntToEightBytesResult[1]);
    assertEquals((byte) 0, actualIntToEightBytesResult[2]);
    assertEquals((byte) 0, actualIntToEightBytesResult[3]);
    assertEquals((byte) 0, actualIntToEightBytesResult[4]);
    assertEquals((byte) 0, actualIntToEightBytesResult[5]);
    assertEquals((byte) 0, actualIntToEightBytesResult[6]);
    assertEquals('*', actualIntToEightBytesResult[7]);
  }

  @Test
  void testIntToEightBytes2() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToEightBytes(42L, "AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testIntToEightBytes3() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.intToEightBytes(42L, new byte[]{}, 1));
  }

  @Test
  void testIntToEightBytes4() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToEightBytes(42L, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testIntToEightBytes5() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToEightBytes(42L, "AAAAAAAA".getBytes("UTF-8"), 3));
  }

  @Test
  void testIntToEightBytes6() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToEightBytes(42L, "AAAAAAAA".getBytes("UTF-8"), 4));
  }

  @Test
  void testIntToEightBytes7() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToEightBytes(1L, new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 6));
  }

  @Test
  void testIntToNBytes() {
    // Arrange and Act
    byte[] actualIntToNBytesResult = ByteUtils.intToNBytes(42L, 3);

    // Assert
    assertEquals(3, actualIntToNBytesResult.length);
    assertEquals((byte) 0, actualIntToNBytesResult[0]);
    assertEquals((byte) 0, actualIntToNBytesResult[1]);
    assertEquals('*', actualIntToNBytesResult[2]);
  }

  @Test
  void testIntToNBytes2() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> ByteUtils.intToNBytes(42L, -1));
  }

  @Test
  void testIntToNBytes3() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.intToNBytes(42L, new byte[]{}, 1, 3));
  }

  @Test
  void testFourBytesToIntLittleEndian() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(1094795585L, ByteUtils.fourBytesToIntLittleEndian("AAAAAAAA".getBytes("UTF-8")));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.fourBytesToIntLittleEndian(new byte[]{}));
    assertEquals(1094795585L, ByteUtils.fourBytesToIntLittleEndian("AAAAAAAA".getBytes("UTF-8"), 1));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.fourBytesToIntLittleEndian(new byte[]{}, 1));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.fourBytesToIntLittleEndian("AAAAAAAA".getBytes("UTF-8"), -1));
  }

  @Test
  void testIntToFourBytesLittleEndian() {
    // Arrange and Act
    byte[] actualIntToFourBytesLittleEndianResult = ByteUtils.intToFourBytesLittleEndian(1L);

    // Assert
    assertEquals(4, actualIntToFourBytesLittleEndianResult.length);
    assertEquals((byte) 1, actualIntToFourBytesLittleEndianResult[0]);
    assertEquals((byte) 0, actualIntToFourBytesLittleEndianResult[1]);
    assertEquals((byte) 0, actualIntToFourBytesLittleEndianResult[2]);
    assertEquals((byte) 0, actualIntToFourBytesLittleEndianResult[3]);
  }

  @Test
  void testIntToFourBytesLittleEndian2() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteUtils.intToFourBytesLittleEndian(1L, new byte[]{}, 1));
  }

  @Test
  void testIntToFourBytesLittleEndian3() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteUtils.intToFourBytesLittleEndian(1L, "AAAAAAAA".getBytes("UTF-8"), -1));
  }

  @Test
  void testFourBytesToLong() {
    // Arrange, Act and Assert
    assertEquals(1094795585L, ByteUtils.fourBytesToLong((byte) 'A', (byte) 'A', (byte) 'A', (byte) 'A'));
    assertEquals(4276545L, ByteUtils.fourBytesToLong((byte) 0, (byte) 'A', (byte) 'A', (byte) 'A'));
    assertEquals(406929729L, ByteUtils.fourBytesToLong((byte) 24, (byte) 'A', (byte) 'A', (byte) 'A'));
  }
}

