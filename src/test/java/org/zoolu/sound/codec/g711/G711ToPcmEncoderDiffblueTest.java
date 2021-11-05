package org.zoolu.sound.codec.g711;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioFormat;
import org.junit.jupiter.api.Test;

class G711ToPcmEncoderDiffblueTest {
  @Test
  void testALAWConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    G711ToPcmEncoder.ALAW actualAlaw = new G711ToPcmEncoder.ALAW();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Assert
    assertEquals(6, actualAlaw.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("Name", (new G711ToPcmEncoder(new AudioFormat.Encoding("Name"))).g711_encoding.toString());
  }

  @Test
  void testEncode() throws UnsupportedEncodingException {
    // Arrange
    G711ToPcmEncoder g711ToPcmEncoder = new G711ToPcmEncoder(new AudioFormat.Encoding("Name"));
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(6, g711ToPcmEncoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode2() throws UnsupportedEncodingException {
    // Arrange
    G711ToPcmEncoder g711ToPcmEncoder = new G711ToPcmEncoder(new AudioFormat.Encoding("Name"));

    // Act and Assert
    assertEquals(6, g711ToPcmEncoder.encode(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A'}, 2, 3,
        "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode3() throws UnsupportedEncodingException {
    // Arrange
    G711ToPcmEncoder g711ToPcmEncoder = new G711ToPcmEncoder(new AudioFormat.Encoding("Name"));
    byte[] in_buff = "AAUAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(6, g711ToPcmEncoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode4() throws UnsupportedEncodingException {
    // Arrange
    G711ToPcmEncoder g711ToPcmEncoder = new G711ToPcmEncoder(new AudioFormat.Encoding("Name"));
    byte[] in_buff = "AA￿AAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(6, g711ToPcmEncoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode5() throws UnsupportedEncodingException {
    // Arrange
    G711ToPcmEncoder g711ToPcmEncoder = new G711ToPcmEncoder(new AudioFormat.Encoding("Name"));

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g711ToPcmEncoder.encode(new byte[]{}, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode6() throws UnsupportedEncodingException {
    // Arrange
    G711ToPcmEncoder g711ToPcmEncoder = new G711ToPcmEncoder(new AudioFormat.Encoding("Name"));
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g711ToPcmEncoder.encode(in_buff, 2, 5, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode7() throws UnsupportedEncodingException {
    // Arrange
    G711ToPcmEncoder g711ToPcmEncoder = new G711ToPcmEncoder(new AudioFormat.Encoding("Name"));
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g711ToPcmEncoder.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 5));
  }

  @Test
  void testULAWConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    G711ToPcmEncoder.ULAW actualUlaw = new G711ToPcmEncoder.ULAW();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Assert
    assertEquals(6, actualUlaw.encode(in_buff, 2, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }
}

