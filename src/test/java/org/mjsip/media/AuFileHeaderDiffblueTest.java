package org.mjsip.media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class AuFileHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    AuFileHeader actualAuFileHeader = new AuFileHeader(1, 1);

    // Assert
    assertEquals(1, actualAuFileHeader.getChannels());
    assertEquals(1, actualAuFileHeader.getEncodingFormat());
    assertEquals(1, actualAuFileHeader.getSampleRate());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AuFileHeader actualAuFileHeader = new AuFileHeader(1, 1, 1, 3L);

    // Assert
    assertEquals(1, actualAuFileHeader.getChannels());
    assertEquals(24, actualAuFileHeader.hdr_len);
    assertEquals(3L, actualAuFileHeader.data_size);
    assertEquals(1, actualAuFileHeader.getSampleRate());
    assertEquals(1, actualAuFileHeader.getEncodingFormat());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    AuFileHeader actualAuFileHeader = new AuFileHeader(1, 1, 1, -1L);

    // Assert
    assertEquals(1, actualAuFileHeader.getChannels());
    assertEquals(24, actualAuFileHeader.hdr_len);
    assertEquals(-1L, actualAuFileHeader.data_size);
    assertEquals(1, actualAuFileHeader.getSampleRate());
    assertEquals(1, actualAuFileHeader.getEncodingFormat());
  }

  @Test
  void testConstructor4() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IOException.class,
        () -> new AuFileHeader(new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))));
  }

  @Test
  void testConstructor5() throws IOException {
    // Arrange
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    byteArrayInputStream.transferTo(new ByteArrayOutputStream(3));

    // Act and Assert
    assertThrows(IOException.class, () -> new AuFileHeader(byteArrayInputStream));
  }

  @Test
  void testWriteTo() throws IOException {
    // Arrange
    AuFileHeader auFileHeader = new AuFileHeader(1, 1);

    // Act
    auFileHeader.writeTo(new ByteArrayOutputStream(3));

    // Assert
    assertEquals(24, auFileHeader.hdr_len);
  }

  @Test
  void testGetDataSize() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new AuFileHeader(1, 1)).getDataSize());
    assertEquals(3L, (new AuFileHeader(1, 1, 1, 3L)).getDataSize());
  }
}

