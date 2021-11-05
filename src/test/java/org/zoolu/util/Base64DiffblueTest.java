package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class Base64DiffblueTest {
  @Test
  void testEncode() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertArrayEquals(bytes, Base64.decode(Base64.encode(bytes)));
  }

  @Test
  void testEncode2() {
    // Arrange
    byte[] byteArray = new byte[]{3, 'A', 'A', 'A', 'A', 'A', 'A', 'A'};

    // Act and Assert
    assertArrayEquals(byteArray, Base64.decode(Base64.encode(byteArray)));
  }

  @Test
  void testEncode3() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "￿AAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertArrayEquals(bytes, Base64.decode(Base64.encode(bytes)));
  }

  @Test
  void testEncode4() {
    // Arrange
    byte[] byteArray = new byte[]{Byte.MAX_VALUE, 'A', 'A', 'A', 'A', 'A', 'A', 'A'};

    // Act and Assert
    assertArrayEquals(byteArray, Base64.decode(Base64.encode(byteArray)));
  }

  @Test
  void testEncode5() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AAAAAAAAAAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertArrayEquals(bytes, Base64.decode(Base64.encode(bytes)));
  }

  @Test
  void testEncode6() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertArrayEquals(bytes, Base64.decode(Base64.encode(bytes)));
  }

  @Test
  void testJustify() {
    // Arrange, Act and Assert
    assertEquals("Str", Base64.justify("Str", 3));
    assertEquals("f\r\no\r\no", Base64.justify("foo", 1));
  }

  @Test
  void testTrim() {
    // Arrange, Act and Assert
    assertEquals("Str", Base64.trim("Str"));
    assertEquals("42", Base64.trim("42"));
  }
}

