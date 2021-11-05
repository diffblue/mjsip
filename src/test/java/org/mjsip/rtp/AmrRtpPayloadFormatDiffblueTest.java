package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class AmrRtpPayloadFormatDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals((short) 15, AmrRtpPayloadFormat.DEFAULT_CMR);
    assertTrue((new AmrRtpPayloadFormat(true)).bandwidth_efficient_mode);
  }

  @Test
  void testGetSilencePad() throws UnsupportedEncodingException {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(true);

    // Act and Assert
    assertEquals(0, amrRtpPayloadFormat.getSilencePad(42, 10L, "AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testGetSilencePad2() throws UnsupportedEncodingException {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(true);

    // Act and Assert
    assertEquals(0, amrRtpPayloadFormat.getSilencePad(42, 160L, "AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testGetSilencePad3() throws UnsupportedEncodingException {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(false);

    // Act and Assert
    assertEquals(0, amrRtpPayloadFormat.getSilencePad(42, 42L, "AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testRemoveRtpPayloadFormat() throws Exception {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(true);

    // Act and Assert
    assertEquals(3, amrRtpPayloadFormat.removeRtpPayloadFormat("AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat2() throws Exception {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(false);

    // Act and Assert
    assertEquals(6, amrRtpPayloadFormat.removeRtpPayloadFormat("AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat3() throws Exception {
    // Arrange, Act and Assert
    assertEquals(3,
        (new AmrRtpPayloadFormat(true)).removeRtpPayloadFormat(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat4() throws Exception {
    // Arrange, Act and Assert
    assertEquals(3,
        (new AmrRtpPayloadFormat(true)).removeRtpPayloadFormat(new byte[]{'A', 0, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat5() throws Exception {
    // Arrange, Act and Assert
    assertEquals(3,
        (new AmrRtpPayloadFormat(true)).removeRtpPayloadFormat(new byte[]{'A', 4, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat6() throws Exception {
    // Arrange, Act and Assert
    assertEquals(0,
        (new AmrRtpPayloadFormat(true)).removeRtpPayloadFormat(new byte[]{'A', 6, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat7() throws Exception {
    // Arrange, Act and Assert
    assertEquals(3,
        (new AmrRtpPayloadFormat(true)).removeRtpPayloadFormat(new byte[]{'A', 3, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat8() throws Exception {
    // Arrange, Act and Assert
    assertEquals(3,
        (new AmrRtpPayloadFormat(true)).removeRtpPayloadFormat(new byte[]{'A', 2, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat9() throws Exception {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(true);

    // Act and Assert
    assertEquals(3, amrRtpPayloadFormat.removeRtpPayloadFormat("AA￰AAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat10() throws Exception {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new AmrRtpPayloadFormat(false)).removeRtpPayloadFormat(new byte[]{}, 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat11() throws Exception {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new AmrRtpPayloadFormat(true)).removeRtpPayloadFormat(new byte[]{}, 1, 0));
  }

  @Test
  void testRemoveRtpPayloadFormat12() throws Exception {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new AmrRtpPayloadFormat(true)).removeRtpPayloadFormat(new byte[]{}, 1, Integer.MIN_VALUE));
  }

  @Test
  void testRemoveRtpPayloadFormat13() throws Exception {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(true);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> amrRtpPayloadFormat.removeRtpPayloadFormat("AAAAAAAA".getBytes("UTF-8"), Integer.MIN_VALUE, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat14() throws Exception {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new AmrRtpPayloadFormat(false))
        .removeRtpPayloadFormat(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A'}, 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat15() throws Exception {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(false);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> amrRtpPayloadFormat.removeRtpPayloadFormat("AAﾀAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat16() throws Exception {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> (new AmrRtpPayloadFormat(false))
        .removeRtpPayloadFormat(new byte[]{'A', 'A', 15, 'A', 'A', 'A', 'A', 'A'}, 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat17() throws Exception {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(false);

    // Act and Assert
    assertEquals(0, amrRtpPayloadFormat.removeRtpPayloadFormat("AAXAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat18() throws Exception {
    // Arrange, Act and Assert
    assertEquals(1, (new AmrRtpPayloadFormat(false))
        .removeRtpPayloadFormat(new byte[]{'A', 'A', Byte.MAX_VALUE, 'A', 'A', 'A', 'A', 'A'}, 1, 3));
  }

  @Test
  void testRemoveRtpPayloadFormat19() throws Exception {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(false);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> amrRtpPayloadFormat.removeRtpPayloadFormat("AAAAAAAA".getBytes("UTF-8"), -1, 3));
  }

  @Test
  void testSetRtpPayloadFormat() throws UnsupportedEncodingException {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(false);

    // Act and Assert
    assertEquals(4, amrRtpPayloadFormat.setRtpPayloadFormat("AAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testSetRtpPayloadFormat2() throws UnsupportedEncodingException {
    // Arrange
    AmrRtpPayloadFormat amrRtpPayloadFormat = new AmrRtpPayloadFormat(true);

    // Act and Assert
    assertEquals(2, amrRtpPayloadFormat.setRtpPayloadFormat("AXAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testSetRtpPayloadFormat4() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new AmrRtpPayloadFormat(true)).setRtpPayloadFormat(new byte[]{}, 1, 3));
  }

  @Test
  void testSetRtpPayloadFormat5() {
    // Arrange, Act and Assert
    assertEquals(4,
        (new AmrRtpPayloadFormat(false)).setRtpPayloadFormat(new byte[]{'A', 0, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
  }

  @Test
  void testGetRtpPayloadFormatLength() {
    // Arrange, Act and Assert
    assertEquals(4, (new AmrRtpPayloadFormat(true)).getRtpPayloadFormatLength(3));
    assertEquals(4, (new AmrRtpPayloadFormat(false)).getRtpPayloadFormatLength(3));
    assertEquals(2, (new AmrRtpPayloadFormat(true)).getRtpPayloadFormatLength(1));
    assertEquals(7, (new AmrRtpPayloadFormat(true)).getRtpPayloadFormatLength(6));
    assertEquals(14, (new AmrRtpPayloadFormat(true)).getRtpPayloadFormatLength(13));
    assertEquals(15, (new AmrRtpPayloadFormat(true)).getRtpPayloadFormatLength(14));
    assertEquals(Short.SIZE, (new AmrRtpPayloadFormat(true)).getRtpPayloadFormatLength(Short.SIZE));
  }
}

