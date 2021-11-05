package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class PipeInputStreamDiffblueTest {
  @Test
  void testConstructor() throws IOException {
    // Arrange and Act
    PipeInputStream actualPipeInputStream = new PipeInputStream(new Pipe(3));
    actualPipeInputStream.mark(1);
    actualPipeInputStream.reset();

    // Assert that nothing has changed
    Pipe pipe = actualPipeInputStream.pipe;
    assertEquals(0, pipe.available());
    assertEquals(3, pipe.size());
    assertEquals(3, pipe.freespace());
  }

  @Test
  void testAvailable() throws IOException {
    // Arrange, Act and Assert
    assertEquals(0, (new PipeInputStream(new Pipe(3))).available());
  }

  @Test
  void testRead() throws IOException {
    // Arrange
    Pipe pipe = new Pipe(3);
    pipe.write((byte) 'A');
    PipeInputStream pipeInputStream = new PipeInputStream(pipe);

    // Act and Assert
    assertEquals(65, pipeInputStream.read());
    Pipe pipe1 = pipeInputStream.pipe;
    assertEquals(0, pipe1.available());
    assertEquals(1, pipe1.pipe_top);
  }

  @Test
  void testRead2() throws IOException {
    // Arrange
    Pipe pipe = new Pipe(1);
    pipe.write((byte) 'A');
    PipeInputStream pipeInputStream = new PipeInputStream(pipe);

    // Act and Assert
    assertEquals(65, pipeInputStream.read());
    Pipe pipe1 = pipeInputStream.pipe;
    assertEquals(0, pipe1.available());
    assertEquals(0, pipe1.pipe_top);
  }

  @Test
  void testRead3() throws IOException {
    // Arrange
    PipeInputStream pipeInputStream = new PipeInputStream(new Pipe(3));

    // Act and Assert
    assertEquals(0, pipeInputStream.read(new byte[]{}));
    assertEquals(0, pipeInputStream.pipe.available());
  }

  @Test
  void testRead4() throws IOException {
    // Arrange
    PipeInputStream pipeInputStream = new PipeInputStream(new Pipe(3));

    // Act and Assert
    assertEquals(0, pipeInputStream.read("AAAAAAAA".getBytes("UTF-8"), 1, 0));
    assertEquals(0, pipeInputStream.pipe.available());
  }

  @Test
  void testRead5() throws IOException {
    // Arrange
    PipeInputStream pipeInputStream = new PipeInputStream(new Pipe(0));

    // Act and Assert
    assertEquals(0, pipeInputStream.read("AAAAAAAA".getBytes("UTF-8"), 1, 0));
    assertEquals(0, pipeInputStream.pipe.available());
  }

  @Test
  void testSkip() throws IOException {
    // Arrange
    PipeInputStream pipeInputStream = new PipeInputStream(new Pipe(3));

    // Act and Assert
    assertEquals(0L, pipeInputStream.skip(0L));
    Pipe pipe = pipeInputStream.pipe;
    assertEquals(0, pipe.available());
    assertEquals(0, pipe.pipe_top);
  }

  @Test
  void testSkip2() throws IOException {
    // Arrange
    PipeInputStream pipeInputStream = new PipeInputStream(new Pipe(3));

    // Act and Assert
    assertEquals(0L, pipeInputStream.skip(Long.MIN_VALUE));
    Pipe pipe = pipeInputStream.pipe;
    assertEquals(0, pipe.available());
    assertEquals(0, pipe.pipe_top);
  }

  @Test
  void testSkip3() throws IOException {
    // Arrange
    PipeInputStream pipeInputStream = new PipeInputStream(new Pipe(1));

    // Act and Assert
    assertEquals(0L, pipeInputStream.skip(0L));
    Pipe pipe = pipeInputStream.pipe;
    assertEquals(0, pipe.available());
    assertEquals(0, pipe.pipe_top);
  }

  @Test
  void testMarkSupported() {
    // Arrange, Act and Assert
    assertFalse((new PipeInputStream(new Pipe(3))).markSupported());
  }
}

