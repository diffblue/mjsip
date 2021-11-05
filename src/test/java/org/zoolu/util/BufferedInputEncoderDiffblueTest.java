package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class BufferedInputEncoderDiffblueTest {
  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(NegativeArraySizeException.class,
        () -> new BufferedInputEncoder(new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")),
            mock(Encoder.class), -1, 3));
    assertThrows(NegativeArraySizeException.class,
        () -> new BufferedInputEncoder(new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")),
            mock(Encoder.class), 3, -1));

  }

  @Test
  void testAvailable() throws IOException {
    // Arrange, Act and Assert
    assertEquals(24, (new BufferedInputEncoder(new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")),
        mock(Encoder.class), 3, 3)).available());
    assertEquals(24, (new BufferedInputEncoder(new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")),
        mock(Encoder.class), 0, 3)).available());
  }

  @Test
  void testRead() throws IOException {
    // Arrange
    Encoder encoder = mock(Encoder.class);
    when(encoder.encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt())).thenReturn(1);
    BufferedInputEncoder bufferedInputEncoder = new BufferedInputEncoder(
        new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), encoder, 3, 3);

    // Act and Assert
    assertEquals(0, bufferedInputEncoder.read());
    verify(encoder).encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt());
    assertEquals(1, bufferedInputEncoder.buffer_index);
    assertEquals(1, bufferedInputEncoder.buffer_size);
  }

  @Test
  void testRead2() throws IOException {
    // Arrange
    Encoder encoder = mock(Encoder.class);
    when(encoder.encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt())).thenReturn(0);
    BufferedInputEncoder bufferedInputEncoder = new BufferedInputEncoder(
        new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), encoder, 3, 3);

    // Act and Assert
    assertEquals(-1, bufferedInputEncoder.read());
    verify(encoder).encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt());
    assertEquals(0, bufferedInputEncoder.buffer_index);
    assertEquals(0, bufferedInputEncoder.buffer_size);
  }

  @Test
  void testRead3() throws IOException {
    // Arrange
    Encoder encoder = mock(Encoder.class);
    when(encoder.encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt())).thenReturn(1);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new BufferedInputEncoder(new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), encoder,
            3, 0)).read());
    verify(encoder).encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt());
  }

  @Test
  void testRead4() throws IOException {
    // Arrange
    Encoder encoder = mock(Encoder.class);
    when(encoder.encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt())).thenReturn(1);
    BufferedInputEncoder bufferedInputEncoder = new BufferedInputEncoder(
        new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), encoder, 3, 3);

    // Act and Assert
    assertEquals(8, bufferedInputEncoder.read("AAAAAAAA".getBytes("UTF-8")));
    verify(encoder, atLeast(1)).encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt());
    assertEquals(1, bufferedInputEncoder.buffer_index);
    assertEquals(1, bufferedInputEncoder.buffer_size);
  }

  @Test
  void testRead5() throws IOException {
    // Arrange
    Encoder encoder = mock(Encoder.class);
    when(encoder.encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt())).thenReturn(1);

    // Act and Assert
    assertEquals(0, (new BufferedInputEncoder(null, encoder, 3, 3)).read(new byte[]{}));
  }

  @Test
  void testRead6() throws IOException {
    // Arrange
    Encoder encoder = mock(Encoder.class);
    when(encoder.encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt())).thenReturn(0);
    BufferedInputEncoder bufferedInputEncoder = new BufferedInputEncoder(
        new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), encoder, 3, 3);

    // Act and Assert
    assertEquals(0, bufferedInputEncoder.read("AAAAAAAA".getBytes("UTF-8")));
    verify(encoder).encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt());
    assertEquals(0, bufferedInputEncoder.buffer_index);
    assertEquals(0, bufferedInputEncoder.buffer_size);
  }

  @Test
  void testRead8() throws IOException {
    // Arrange
    Encoder encoder = mock(Encoder.class);
    when(encoder.encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt())).thenReturn(1);
    BufferedInputEncoder bufferedInputEncoder = new BufferedInputEncoder(
        new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), encoder, 3, 3);

    // Act and Assert
    assertEquals(4, bufferedInputEncoder.read("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    verify(encoder, atLeast(1)).encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt());
    assertEquals(1, bufferedInputEncoder.buffer_index);
    assertEquals(1, bufferedInputEncoder.buffer_size);
  }

  @Test
  void testRead9() throws IOException {
    // Arrange
    Encoder encoder = mock(Encoder.class);
    when(encoder.encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt())).thenReturn(1);

    // Act and Assert
    assertEquals(1, (new BufferedInputEncoder(null, encoder, 3, 3)).read(new byte[]{}, 1, 3));
  }

  @Test
  void testRead10() throws IOException {
    // Arrange
    Encoder encoder = mock(Encoder.class);
    when(encoder.encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt())).thenReturn(0);
    BufferedInputEncoder bufferedInputEncoder = new BufferedInputEncoder(
        new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), encoder, 3, 3);

    // Act and Assert
    assertEquals(1, bufferedInputEncoder.read("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    verify(encoder).encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt());
    assertEquals(0, bufferedInputEncoder.buffer_index);
    assertEquals(0, bufferedInputEncoder.buffer_size);
  }

  @Test
  void testRead11() throws IOException {
    // Arrange
    Encoder encoder = mock(Encoder.class);
    when(encoder.encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt())).thenReturn(4);
    BufferedInputEncoder bufferedInputEncoder = new BufferedInputEncoder(
        new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), encoder, 3, 3);

    // Act and Assert
    assertEquals(4, bufferedInputEncoder.read("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    verify(encoder).encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt());
    assertEquals(4, bufferedInputEncoder.buffer_size);
  }

  @Test
  void testRead12() throws IOException {
    // Arrange
    Encoder encoder = mock(Encoder.class);
    when(encoder.encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt())).thenReturn(1);
    BufferedInputEncoder bufferedInputEncoder = new BufferedInputEncoder(
        new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), encoder, 3, 4);

    // Act and Assert
    assertEquals(4, bufferedInputEncoder.read("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    verify(encoder, atLeast(1)).encode((byte[]) any(), anyInt(), anyInt(), (byte[]) any(), anyInt());
    assertEquals(1, bufferedInputEncoder.buffer_index);
    assertEquals(1, bufferedInputEncoder.buffer_size);
  }

  @Test
  void testSkip() throws IOException {
    // Arrange, Act and Assert
    assertEquals(0L, (new BufferedInputEncoder(new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")),
        mock(Encoder.class), 3, 3)).skip(1L));
  }

  @Test
  void testMarkSupported() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse((new BufferedInputEncoder(new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")),
        mock(Encoder.class), 3, 3)).markSupported());
  }
}

