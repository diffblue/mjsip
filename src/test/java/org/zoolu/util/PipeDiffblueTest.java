package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class PipeDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    Pipe actualPipe = new Pipe(3);

    // Assert
    assertEquals(0, actualPipe.available());
    assertEquals(0, actualPipe.pipe_top);
    assertEquals(3, actualPipe.pipe_buffer.length);
  }

  @Test
  void testConstructor2() {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> new Pipe(-1));
  }

  @Test
  void testSize() {
    // Arrange, Act and Assert
    assertEquals(3, (new Pipe(3)).size());
  }

  @Test
  void testAvailable() {
    // Arrange, Act and Assert
    assertEquals(0, (new Pipe(3)).available());
  }

  @Test
  void testFreespace() {
    // Arrange, Act and Assert
    assertEquals(3, (new Pipe(3)).freespace());
  }

  @Test
  void testWrite() {
    // Arrange
    Pipe pipe = new Pipe(3);

    // Act
    pipe.write((byte) 'A');

    // Assert
    assertEquals(1, pipe.available());
  }

  @Test
  void testWrite2() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new Pipe(0)).write((byte) 'A'));
  }

  @Test
  void testWrite3() throws UnsupportedEncodingException {
    // Arrange
    Pipe pipe = new Pipe(3);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> pipe.write("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testWrite4() throws UnsupportedEncodingException {
    // Arrange
    Pipe pipe = new Pipe(8);

    // Act
    pipe.write("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(8, pipe.available());
  }

  @Test
  void testWrite5() {
    // Arrange, Act and Assert
    assertThrows(ArithmeticException.class, () -> (new Pipe(0)).write(new byte[]{}));
  }

  @Test
  void testWrite6() throws UnsupportedEncodingException {
    // Arrange
    Pipe pipe = new Pipe(3);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> pipe.write("AAAAAAAA".getBytes("UTF-8"), 19088743, 3));
  }

  @Test
  void testWrite7() throws UnsupportedEncodingException {
    // Arrange
    Pipe pipe = new Pipe(0);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> pipe.write("AAAAAAAA".getBytes("UTF-8"), 19088743, 3));
  }

  @Test
  void testWrite8() throws UnsupportedEncodingException {
    // Arrange
    Pipe pipe = new Pipe(3);

    // Act
    pipe.write("AAAAAAAA".getBytes("UTF-8"), 0, 3);

    // Assert
    assertEquals(3, pipe.available());
  }

  @Test
  void testWrite9() throws UnsupportedEncodingException {
    // Arrange
    Pipe pipe = new Pipe(3);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> pipe.write("AAAAAAAA".getBytes("UTF-8"), 19088743, -1));
  }

  @Test
  void testWrite10() throws UnsupportedEncodingException {
    // Arrange
    Pipe pipe = new Pipe(0);

    // Act and Assert
    assertThrows(ArithmeticException.class, () -> pipe.write("AAAAAAAA".getBytes("UTF-8"), 19088743, 0));
  }

  @Test
  void testRead() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new Pipe(3)).read());
  }

  @Test
  void testRead2() {
    // Arrange
    Pipe pipe = new Pipe(3);
    pipe.write((byte) 'A');

    // Act and Assert
    assertEquals('A', pipe.read());
    assertEquals(0, pipe.available());
    assertEquals(1, pipe.pipe_top);
  }

  @Test
  void testRead3() {
    // Arrange
    Pipe pipe = new Pipe(1);
    pipe.write((byte) 'A');

    // Act and Assert
    assertEquals('A', pipe.read());
    assertEquals(0, pipe.available());
    assertEquals(0, pipe.pipe_top);
  }

  @Test
  void testRead4() throws UnsupportedEncodingException {
    // Arrange
    Pipe pipe = new Pipe(3);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> pipe.read("AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testRead5() {
    // Arrange
    Pipe pipe = new Pipe(3);

    // Act and Assert
    assertEquals(0, pipe.read(new byte[]{}));
    assertEquals(0, pipe.available());
  }

  @Test
  void testRead6() throws UnsupportedEncodingException {
    // Arrange
    Pipe pipe = new Pipe(3);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> pipe.read("AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testRead7() throws UnsupportedEncodingException {
    // Arrange
    Pipe pipe = new Pipe(3);

    // Act and Assert
    assertEquals(0, pipe.read("AAAAAAAA".getBytes("UTF-8"), 1, 0));
    assertEquals(0, pipe.available());
  }

  @Test
  void testRead8() throws UnsupportedEncodingException {
    // Arrange
    Pipe pipe = new Pipe(3);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> pipe.read("AAAAAAAA".getBytes("UTF-8"), 1, -1));
  }

  @Test
  void testSkip() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new Pipe(3)).skip(3));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new Pipe(3)).skip(-1));
    assertThrows(ArithmeticException.class, () -> (new Pipe(0)).skip(0));
  }

  @Test
  void testSkip2() {
    // Arrange
    Pipe pipe = new Pipe(3);

    // Act and Assert
    assertEquals(0L, pipe.skip(0));
    assertEquals(0, pipe.available());
    assertEquals(0, pipe.pipe_top);
  }
}

