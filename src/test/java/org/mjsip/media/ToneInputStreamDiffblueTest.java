package org.mjsip.media;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class ToneInputStreamDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ToneInputStream actualToneInputStream = new ToneInputStream(1, 10.0, 1, 3, 1, true);
    actualToneInputStream.close();
    actualToneInputStream.mark(1);
    actualToneInputStream.reset();

    // Assert that nothing has changed
    assertEquals(65536, actualToneInputStream.available());
    assertEquals(0.0, actualToneInputStream.zero);
    assertEquals(3, actualToneInputStream.size);
    assertEquals(0, actualToneInputStream.s_index);
    byte[] byteArray = actualToneInputStream.s_buff;
    assertEquals(3, byteArray.length);
    assertArrayEquals(new byte[]{0, 0, 0}, byteArray);
    assertEquals((byte) 0, byteArray[0]);
    assertEquals((byte) 0, byteArray[1]);
    assertEquals((byte) 0, byteArray[2]);
    assertEquals(1, actualToneInputStream.fs);
    assertEquals(1, actualToneInputStream.f0);
    assertTrue(actualToneInputStream.big_endian);
    assertEquals(65536, ToneInputStream.MAX_AVAILABLE_BYTES);
    assertEquals(6.283185307179586, actualToneInputStream.B);
    assertEquals(8.388608E7, actualToneInputStream.A);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ToneInputStream actualToneInputStream = new ToneInputStream(1, 10.0, 1, 3, 1, true);

    // Assert
    assertEquals(0.0, actualToneInputStream.zero);
    assertEquals(3, actualToneInputStream.size);
    assertEquals(0, actualToneInputStream.s_index);
    assertEquals(3, actualToneInputStream.s_buff.length);
    assertEquals(1, actualToneInputStream.fs);
    assertEquals(1, actualToneInputStream.f0);
    assertTrue(actualToneInputStream.big_endian);
    assertEquals(6.283185307179586, actualToneInputStream.B);
    assertEquals(8.388608E7, actualToneInputStream.A);
  }

  @Test
  void testConstructor3() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new ToneInputStream(1, 10.0, 1, -1, 1, true));

  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    ToneInputStream actualToneInputStream = new ToneInputStream(1, 10.0, 1, 3, 0, true);

    // Assert
    assertEquals(4194304.0, actualToneInputStream.zero);
    assertEquals(3, actualToneInputStream.size);
    assertEquals(0, actualToneInputStream.s_index);
    assertEquals(3, actualToneInputStream.s_buff.length);
    assertEquals(1, actualToneInputStream.fs);
    assertEquals(1, actualToneInputStream.f0);
    assertTrue(actualToneInputStream.big_endian);
    assertEquals(6.283185307179586, actualToneInputStream.B);
    assertEquals(8.388608E7, actualToneInputStream.A);
  }

  @Test
  void testAvailable() {
    // Arrange, Act and Assert
    assertEquals(65536, (new ToneInputStream(1, 10.0, 1, 3, 1, true)).available());
  }

  @Test
  void testRead() {
    // Arrange, Act and Assert
    assertEquals(0, (new ToneInputStream(1, 10.0, 1, 3, 1, true)).read());
    assertEquals(0, (new ToneInputStream(1, 10.0, 1, 3, 1, false)).read());
    assertEquals(0, (new ToneInputStream(1, 10.0, 1, 0, 1, true)).read(new byte[]{}));
  }

  @Test
  void testRead2() throws UnsupportedEncodingException {
    // Arrange
    ToneInputStream toneInputStream = new ToneInputStream(1, 10.0, 1, 3, 1, true);

    // Act and Assert
    assertEquals(8, toneInputStream.read("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testRead3() throws UnsupportedEncodingException {
    // Arrange
    ToneInputStream toneInputStream = new ToneInputStream(1, 10.0, 1, 8, 1, true);

    // Act and Assert
    assertEquals(8, toneInputStream.read("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testRead4() throws UnsupportedEncodingException {
    // Arrange
    ToneInputStream toneInputStream = new ToneInputStream(1, 10.0, 1, 3, 1, true);

    // Act and Assert
    assertEquals(3, toneInputStream.read("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    assertEquals(0, toneInputStream.s_index);
  }

  @Test
  void testRead5() throws UnsupportedEncodingException {
    // Arrange
    ToneInputStream toneInputStream = new ToneInputStream(1, 10.0, 1, 4, 1, true);

    // Act and Assert
    assertEquals(3, toneInputStream.read("AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testRead6() throws UnsupportedEncodingException {
    // Arrange
    ToneInputStream toneInputStream = new ToneInputStream(1, 10.0, 1, 0, 1, true);

    // Act and Assert
    assertEquals(0, toneInputStream.read("AAAAAAAA".getBytes("UTF-8"), 1, 0));
  }

  @Test
  void testSkip() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ToneInputStream(1, 10.0, 1, 3, 1, true)).skip(1L));
  }

  @Test
  void testMarkSupported() {
    // Arrange, Act and Assert
    assertFalse((new ToneInputStream(1, 10.0, 1, 3, 1, true)).markSupported());
  }
}

