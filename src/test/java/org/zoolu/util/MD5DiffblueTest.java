package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class MD5DiffblueTest {
  @Test
  void testAsHex() {
    // Arrange
    MD5 md5 = new MD5();

    // Act and Assert
    assertEquals("d41d8cd98f00b204e9800998ecf8427e", md5.asHex());
    assertEquals(Short.SIZE, md5.getDigest().length);
    assertEquals(64L, md5.count);
    assertEquals(0, md5.block_offset);
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    MD5 actualMd5 = new MD5();

    // Assert
    assertEquals(Short.SIZE, actualMd5.getDigest().length);
    assertEquals(4, actualMd5.state.length);
    assertEquals(64L, actualMd5.count);
    assertEquals(0, actualMd5.block_offset);
    assertEquals(Double.SIZE, actualMd5.block.length);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    MD5 actualMd5 = new MD5("Str");

    // Assert
    assertEquals(Short.SIZE, actualMd5.getDigest().length);
    assertEquals(4, actualMd5.state.length);
    assertEquals(64L, actualMd5.count);
    assertEquals(0, actualMd5.block_offset);
    assertEquals(Double.SIZE, actualMd5.block.length);
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    MD5 actualMd5 = new MD5("42");

    // Assert
    assertEquals(Short.SIZE, actualMd5.getDigest().length);
    assertEquals(4, actualMd5.state.length);
    assertEquals(64L, actualMd5.count);
    assertEquals(0, actualMd5.block_offset);
    assertEquals(Double.SIZE, actualMd5.block.length);
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    MD5 actualMd5 = new MD5("27c7cf400229103e00c6d8830029e29b27c7cf400229103e00c6d8830029e29b");

    // Assert
    assertEquals(Short.SIZE, actualMd5.getDigest().length);
    assertEquals(4, actualMd5.state.length);
    assertEquals(128L, actualMd5.count);
    assertEquals(0, actualMd5.block_offset);
    assertEquals(Double.SIZE, actualMd5.block.length);
  }

  @Test
  void testConstructor5() throws UnsupportedEncodingException {
    // Arrange and Act
    MD5 actualMd5 = new MD5("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(Short.SIZE, actualMd5.getDigest().length);
    assertEquals(4, actualMd5.state.length);
    assertEquals(64L, actualMd5.count);
    assertEquals(0, actualMd5.block_offset);
    assertEquals(Double.SIZE, actualMd5.block.length);
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    MD5 actualMd5 = new MD5(new byte[]{});

    // Assert
    assertEquals(Short.SIZE, actualMd5.getDigest().length);
    assertEquals(4, actualMd5.state.length);
    assertEquals(64L, actualMd5.count);
    assertEquals(0, actualMd5.block_offset);
    assertEquals(Double.SIZE, actualMd5.block.length);
  }

  @Test
  void testConstructor7() throws UnsupportedEncodingException {
    // Arrange and Act
    MD5 actualMd5 = new MD5("AAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Assert
    assertEquals(Short.SIZE, actualMd5.getDigest().length);
    assertEquals(4, actualMd5.state.length);
    assertEquals(64L, actualMd5.count);
    assertEquals(0, actualMd5.block_offset);
    assertEquals(Double.SIZE, actualMd5.block.length);
  }

  @Test
  void testConstructor8() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new MD5(new byte[]{}, 2, 3));

  }

  @Test
  void testConstructor9() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new MD5("AAAAAAAA".getBytes("UTF-8"), 2, Double.SIZE));

  }
}

