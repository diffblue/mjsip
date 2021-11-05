package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class PipeOutputStreamDiffblueTest {
  @Test
  void testConstructor() throws IOException {
    // Arrange and Act
    PipeOutputStream actualPipeOutputStream = new PipeOutputStream(new Pipe(3));
    actualPipeOutputStream.flush();

    // Assert that nothing has changed
    Pipe pipe = actualPipeOutputStream.pipe;
    assertEquals(0, pipe.available());
    assertEquals(3, pipe.size());
    assertEquals(3, pipe.freespace());
  }

  @Test
  void testWrite() throws IOException {
    // Arrange
    PipeOutputStream pipeOutputStream = new PipeOutputStream(new Pipe(3));

    // Act
    pipeOutputStream.write(19088743);

    // Assert
    assertEquals(1, pipeOutputStream.pipe.available());
  }

  @Test
  void testWrite2() throws IOException {
    // Arrange
    PipeOutputStream pipeOutputStream = new PipeOutputStream(new Pipe(1));

    // Act
    pipeOutputStream.write(19088743);

    // Assert
    assertEquals(1, pipeOutputStream.pipe.available());
  }

  @Test
  void testWrite3() throws IOException {
    // Arrange
    PipeOutputStream pipeOutputStream = new PipeOutputStream(new Pipe(8));

    // Act
    pipeOutputStream.write("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(8, pipeOutputStream.pipe.available());
  }

  @Test
  void testWrite4() throws IOException {
    // Arrange
    PipeOutputStream pipeOutputStream = new PipeOutputStream(new Pipe(3));

    // Act
    pipeOutputStream.write("AAAAAAAA".getBytes("UTF-8"), 0, 3);

    // Assert
    assertEquals(3, pipeOutputStream.pipe.available());
  }

  @Test
  void testWrite5() throws IOException {
    // Arrange
    PipeOutputStream pipeOutputStream = new PipeOutputStream(new Pipe(1));

    // Act
    pipeOutputStream.write("AAAAAAAA".getBytes("UTF-8"), 19088743, 0);

    // Assert
    assertEquals(0, pipeOutputStream.pipe.available());
  }
}

