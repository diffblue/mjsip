package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class ByteAddrUtilsDiffblueTest {
  @Test
  void testBytesToIpv4addr() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("65.65.65.65", ByteAddrUtils.bytesToIpv4addr("AAAAAAAA".getBytes("UTF-8")));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteAddrUtils.bytesToIpv4addr(new byte[]{}));
    assertEquals("65.65.65.65", ByteAddrUtils.bytesToIpv4addr("AAAAAAAA".getBytes("UTF-8"), 2));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteAddrUtils.bytesToIpv4addr(new byte[]{}, 2));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteAddrUtils.bytesToIpv4addr("AAAAAAAA".getBytes("UTF-8"), 6));
  }

  @Test
  void testAsHex4Bytes() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("41414141 41414141 ", ByteAddrUtils.asHex4Bytes("AAAAAAAA".getBytes("UTF-8")));
    assertEquals("414141 ", ByteAddrUtils.asHex4Bytes("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    assertEquals("", ByteAddrUtils.asHex4Bytes(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 0));
    assertEquals("414141 ", ByteAddrUtils.asHex4Bytes(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 3));
  }

  @Test
  void testAsHexEthernetPacket() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("414141414141 414141414141 4141 4141",
        ByteAddrUtils.asHexEthernetPacket("AAAAAAAAAAAAAAAA".getBytes("UTF-8")));
    assertEquals("414141414141 414141414141 4141 41414141 41414141 4141",
        ByteAddrUtils.asHexEthernetPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));
    assertEquals("414141414141 414141414141 4141",
        ByteAddrUtils.asHexEthernetPacket("AAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3));
  }

  @Test
  void testChecksum() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(-33411, ByteAddrUtils.checksum("AAAAAAAA".getBytes("UTF-8"), 1, 3));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteAddrUtils.checksum(new byte[]{}, 1, 3));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> ByteAddrUtils.checksum("AAAAAAAA".getBytes("UTF-8"), 1, 8));
  }

  @Test
  void testUpdateIPv4HeaderChecksum() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> ByteAddrUtils.updateIPv4HeaderChecksum(new byte[]{}, 1));
  }

  @Test
  void testTrimHexString() {
    // Arrange, Act and Assert
    assertEquals("0123456789ABCDEF", ByteAddrUtils.trimHexString("0123456789ABCDEF"));
    assertEquals("fo", ByteAddrUtils.trimHexString("foo"));
    assertEquals("", ByteAddrUtils.trimHexString("0x"));
  }
}

