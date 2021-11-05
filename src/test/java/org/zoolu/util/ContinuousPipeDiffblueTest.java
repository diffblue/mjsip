package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class ContinuousPipeDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ContinuousPipe actualContinuousPipe = new ContinuousPipe(3);

    // Assert
    assertEquals(0, actualContinuousPipe.available());
    assertEquals(0, actualContinuousPipe.pipe_top);
    assertEquals(3, actualContinuousPipe.pipe_buffer.length);
    assertEquals(3, actualContinuousPipe.aux.length);
  }

  @Test
  void testWrite() {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(3);

    // Act
    continuousPipe.write((byte) 'A');

    // Assert
    assertEquals(1, continuousPipe.available());
  }

  @Test
  void testWrite2() {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(1);

    // Act
    continuousPipe.write((byte) 'A');

    // Assert
    assertEquals(1, continuousPipe.available());
  }

  @Test
  void testWrite3() throws UnsupportedEncodingException {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(3);

    // Act
    continuousPipe.write("AAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Assert
    assertEquals(3, continuousPipe.available());
  }

  @Test
  void testWrite4() throws UnsupportedEncodingException {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(0);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> continuousPipe.write("AAAAAAAA".getBytes("UTF-8"), 2, 3));
  }

  @Test
  void testWrite5() throws UnsupportedEncodingException {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(1);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> continuousPipe.write("AAAAAAAA".getBytes("UTF-8"), 2, 3));
  }

  @Test
  void testWrite6() throws UnsupportedEncodingException {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(2);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> continuousPipe.write("AAAAAAAA".getBytes("UTF-8"), 2, 19088743));
  }

  @Test
  void testWrite7() {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(3);

    // Act
    continuousPipe.write(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 2, 3);

    // Assert
    assertEquals(3, continuousPipe.available());
  }

  @Test
  void testRead() {
    // Arrange, Act and Assert
    assertEquals((byte) 0, (new ContinuousPipe(3)).read());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new ContinuousPipe(3)).read(new byte[]{}, 2, 3));
  }

  @Test
  void testRead2() throws UnsupportedEncodingException {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(3);
    continuousPipe.write("AAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act and Assert
    assertEquals('A', continuousPipe.read());
    assertEquals(2, continuousPipe.available());
    assertEquals(1, continuousPipe.pipe_top);
  }

  @Test
  void testRead3() {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(1);
    continuousPipe.write((byte) 'A');

    // Act and Assert
    assertEquals('A', continuousPipe.read());
    assertEquals(0, continuousPipe.available());
    assertEquals(0, continuousPipe.pipe_top);
  }

  @Test
  void testRead4() throws UnsupportedEncodingException {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(3);

    // Act and Assert
    assertEquals(3, continuousPipe.read("AAAAAAAA".getBytes("UTF-8"), 2, 3));
    assertEquals(0, continuousPipe.available());
  }

  @Test
  void testRead5() throws UnsupportedEncodingException {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(3);

    // Act and Assert
    assertEquals(0, continuousPipe.read("AAAAAAAA".getBytes("UTF-8"), 2, 0));
    assertEquals(0, continuousPipe.available());
  }

  @Test
  void testRead6() throws UnsupportedEncodingException {
    // Arrange
    ContinuousPipe continuousPipe = new ContinuousPipe(3);
    continuousPipe.write("AAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Act and Assert
    assertEquals(3, continuousPipe.read("AAAAAAAA".getBytes("UTF-8"), 2, 3));
    assertEquals(0, continuousPipe.available());
    assertEquals(0, continuousPipe.pipe_top);
  }
}

