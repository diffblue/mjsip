package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class SdesRtcpPacketDiffblueTest {
  @Test
  void testChunkConstructor() {
    // Arrange and Act
    SdesRtcpPacket.Chunk actualChunk = new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{new SdesRtcpPacket.SdesItem(1, "42")});

    // Assert
    assertEquals(1, actualChunk.getSdesItems().length);
    assertEquals(1L, actualChunk.getSSRC());
  }

  @Test
  void testChunkConstructor2() throws UnsupportedEncodingException {
    // Arrange and Act
    SdesRtcpPacket.Chunk actualChunk = new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))});

    // Assert
    assertEquals(1, actualChunk.getSdesItems().length);
    assertEquals(1L, actualChunk.getSSRC());
  }

  @Test
  void testChunkConstructor3() throws UnsupportedEncodingException {
    // Arrange and Act
    SdesRtcpPacket.Chunk actualChunk = new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{new SdesRtcpPacket.SdesItem("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1)});

    // Assert
    assertEquals(1, actualChunk.getSdesItems().length);
    assertEquals(1L, actualChunk.getSSRC());
  }

  @Test
  void testChunkConstructor4() throws UnsupportedEncodingException {
    // Arrange and Act
    SdesRtcpPacket.Chunk actualChunk = new SdesRtcpPacket.Chunk(1L, new SdesRtcpPacket.SdesItem[]{
        new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3)});

    // Assert
    assertEquals(1, actualChunk.getSdesItems().length);
    assertEquals(1L, actualChunk.getSSRC());
  }

  @Test
  void testChunkConstructor5() {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    // Act
    SdesRtcpPacket.Chunk actualChunk = new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, new SdesRtcpPacket.SdesItem(1, "42")});

    // Assert
    assertEquals(2, actualChunk.getSdesItems().length);
    assertEquals(1L, actualChunk.getSSRC());
  }

  @Test
  void testChunkConstructor6() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new SdesRtcpPacket.Chunk("AAAAAAAA".getBytes("UTF-8"), 1));

  }

  @Test
  void testChunkConstructor7() {
    // Arrange and Act
    SdesRtcpPacket.Chunk actualChunk = new SdesRtcpPacket.Chunk(new byte[]{'A', 'A', 'A', 'A', 'A', 0, 'A', 'A'}, 1);

    // Assert
    assertEquals(0, actualChunk.getSdesItems().length);
    assertEquals(1094795585L, actualChunk.getSSRC());
  }

  @Test
  void testChunkConstructor8() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> new SdesRtcpPacket.Chunk(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 4, 'A'}, 1));

  }

  @Test
  void testChunkConstructor9() {
    // Arrange and Act
    SdesRtcpPacket.Chunk actualChunk = new SdesRtcpPacket.Chunk(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 0, 0}, 1);

    // Assert
    assertEquals(8, actualChunk.getBytes().length);
    assertEquals(1, actualChunk.getSdesItems().length);
    assertEquals(1094795585L, actualChunk.getSSRC());
    assertEquals(8, actualChunk.getLength());
  }

  @Test
  void testChunkConstructor10() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> new SdesRtcpPacket.Chunk("AAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1));

  }

  @Test
  void testChunkGetBytes() {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals(20,
        (new SdesRtcpPacket.Chunk(1L,
            new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")}))
                .getBytes().length);
  }

  @Test
  void testChunkGetBytes2() {
    // Arrange and Act
    byte[] actualBytes = (new SdesRtcpPacket.Chunk(new byte[]{'A', 'A', 'A', 'A', 'A', 0, 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1)).getBytes();

    // Assert
    assertEquals(8, actualBytes.length);
    assertEquals('A', actualBytes[0]);
    assertEquals('A', actualBytes[1]);
    assertEquals('A', actualBytes[2]);
    assertEquals('A', actualBytes[3]);
    assertEquals((byte) 0, actualBytes[4]);
    assertEquals((byte) 0, actualBytes[5]);
    assertEquals((byte) 0, actualBytes[6]);
    assertEquals((byte) 0, actualBytes[7]);
  }

  @Test
  void testChunkGetBytes3() {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals(20,
        (new SdesRtcpPacket.Chunk(0L,
            new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")}))
                .getBytes().length);
  }

  @Test
  void testChunkGetBytes4() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals(40,
        (new SdesRtcpPacket.Chunk(1L,
            new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")}))
                .getBytes().length);
  }

  @Test
  void testChunkGetBytes5() {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1);

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals(Short.SIZE,
        (new SdesRtcpPacket.Chunk(1L,
            new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")}))
                .getBytes().length);
  }

  @Test
  void testChunkGetBytes6() {
    // Arrange, Act and Assert
    assertEquals(12, (new SdesRtcpPacket.Chunk(1L, new SdesRtcpPacket.SdesItem[]{new SdesRtcpPacket.SdesItem(1, "42")}))
        .getBytes().length);
  }

  @Test
  void testChunkGetBytes7() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.Chunk chunk = new SdesRtcpPacket.Chunk(new byte[]{'A', 'A', 'A', 'A', 'A', 0, 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> chunk.getBytes("AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testChunkGetBytes8() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.Chunk chunk = new SdesRtcpPacket.Chunk(1L, new SdesRtcpPacket.SdesItem[]{});

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> chunk.getBytes("AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testChunkGetBytes9() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.Chunk chunk = new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")});

    // Act and Assert
    assertEquals(20, chunk.getBytes("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testChunkGetLength() {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals(20, (new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).getLength());
  }

  @Test
  void testChunkGetLength2() {
    // Arrange, Act and Assert
    assertEquals(8, (new SdesRtcpPacket.Chunk(new byte[]{'A', 'A', 'A', 'A', 'A', 0, 'A', 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1)).getLength());
  }

  @Test
  void testChunkGetLength3() {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals(20, (new SdesRtcpPacket.Chunk(0L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).getLength());
  }

  @Test
  void testChunkGetLength4() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals(40, (new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).getLength());
  }

  @Test
  void testChunkGetLength5() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1);

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals(80, (new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).getLength());
  }

  @Test
  void testChunkGetLength6() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1,
        3);

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals(20, (new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).getLength());
  }

  @Test
  void testChunkGetLength7() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(40, (new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).getLength());
  }

  @Test
  void testChunkGetLength8() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1);

    // Act and Assert
    assertEquals(80, (new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).getLength());
  }

  @Test
  void testChunkGetLength9() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1,
        3);

    // Act and Assert
    assertEquals(20, (new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).getLength());
  }

  @Test
  void testChunkToString() {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals("ssrc=1,items=[{type=1,value=42}{type=1,value=42}{type=1,value=42}]", (new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).toString());
  }

  @Test
  void testChunkToString2() {
    // Arrange, Act and Assert
    assertEquals("ssrc=1094795585,items=[]", (new SdesRtcpPacket.Chunk(new byte[]{'A', 'A', 'A', 'A', 'A', 0, 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1)).toString());
  }

  @Test
  void testChunkToString3() {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals("ssrc=0,items=[{type=1,value=42}{type=1,value=42}{type=1,value=42}]", (new SdesRtcpPacket.Chunk(0L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).toString());
  }

  @Test
  void testChunkToString4() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals("ssrc=1,items=[{type=1,value=AAAAAAAAAAAAAAAAAAAAAAAA}{type=1,value=42}{type=1,value=42}]",
        (new SdesRtcpPacket.Chunk(1L,
            new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).toString());
  }

  @Test
  void testChunkToString5() {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1);

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals("ssrc=1,items=[{type=65,value=}{type=1,value=42}{type=1,value=42}]", (new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).toString());
  }

  @Test
  void testChunkToString6() {
    // Arrange, Act and Assert
    assertEquals("ssrc=1,items=[{type=1,value=42}]",
        (new SdesRtcpPacket.Chunk(1L, new SdesRtcpPacket.SdesItem[]{new SdesRtcpPacket.SdesItem(1, "42")})).toString());
  }

  @Test
  void testChunkToString7() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1,
        3);

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals("ssrc=0,items=[{type=1,value=AAA}{type=1,value=42}{type=1,value=42}]", (new SdesRtcpPacket.Chunk(0L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})).toString());
  }

  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AAAAAAAA".getBytes("UTF-8");

    // Act
    SdesRtcpPacket actualSdesRtcpPacket = new SdesRtcpPacket(bytes);

    // Assert
    byte[] expectedPacketBuffer = actualSdesRtcpPacket.buffer;
    byte[] packetBuffer = actualSdesRtcpPacket.getPacketBuffer();
    assertSame(expectedPacketBuffer, packetBuffer);
    assertEquals(8, packetBuffer.length);
    assertEquals(1, actualSdesRtcpPacket.getVersion());
    assertEquals(1, actualSdesRtcpPacket.getSourceCount());
    assertEquals(65, actualSdesRtcpPacket.getPayloadType());
    assertEquals(0, actualSdesRtcpPacket.getPaddingLength());
    assertEquals(0, actualSdesRtcpPacket.getPacketOffset());
    assertEquals(66824, actualSdesRtcpPacket.getPacketLength());
    assertSame(packetBuffer, bytes);
  }

  @Test
  void testConstructor2() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AAAAAAAA".getBytes("UTF-8");

    // Act
    SdesRtcpPacket actualSdesRtcpPacket = new SdesRtcpPacket(bytes, 2);

    // Assert
    byte[] expectedPacketBuffer = actualSdesRtcpPacket.buffer;
    byte[] packetBuffer = actualSdesRtcpPacket.getPacketBuffer();
    assertSame(expectedPacketBuffer, packetBuffer);
    assertEquals(8, packetBuffer.length);
    assertEquals(1, actualSdesRtcpPacket.getVersion());
    assertEquals(1, actualSdesRtcpPacket.getSourceCount());
    assertEquals(65, actualSdesRtcpPacket.getPayloadType());
    assertEquals(0, actualSdesRtcpPacket.getPaddingLength());
    assertEquals(2, actualSdesRtcpPacket.getPacketOffset());
    assertEquals(66824, actualSdesRtcpPacket.getPacketLength());
    assertSame(packetBuffer, bytes);
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    SdesRtcpPacket actualSdesRtcpPacket = new SdesRtcpPacket(1L, "Cname");

    // Assert
    assertEquals(1, actualSdesRtcpPacket.getChunks().length);
    assertEquals(2, actualSdesRtcpPacket.getVersion());
    assertEquals(RtcpPacket.PT_SDES, actualSdesRtcpPacket.getPayloadType());
    assertEquals(0, actualSdesRtcpPacket.getPacketOffset());
    assertEquals(Short.SIZE, actualSdesRtcpPacket.getPacketLength());
    assertEquals(Short.SIZE, actualSdesRtcpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    SdesRtcpPacket actualSdesRtcpPacket = new SdesRtcpPacket(1L, "byte[]");

    // Assert
    assertEquals(1, actualSdesRtcpPacket.getChunks().length);
    assertEquals(2, actualSdesRtcpPacket.getVersion());
    assertEquals(RtcpPacket.PT_SDES, actualSdesRtcpPacket.getPayloadType());
    assertEquals(0, actualSdesRtcpPacket.getPacketOffset());
    assertEquals(20, actualSdesRtcpPacket.getPacketLength());
    assertEquals(20, actualSdesRtcpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor5() throws UnsupportedEncodingException {
    // Arrange and Act
    SdesRtcpPacket actualSdesRtcpPacket = new SdesRtcpPacket(
        new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

    // Assert
    assertEquals(24, actualSdesRtcpPacket.getPacketBuffer().length);
    assertEquals(0, actualSdesRtcpPacket.getPacketOffset());
  }

  @Test
  void testConstructor6() {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    SdesRtcpPacket.SdesItem sdesItem1 = new SdesRtcpPacket.SdesItem(1, "42");

    // Act
    SdesRtcpPacket actualSdesRtcpPacket = new SdesRtcpPacket(new SdesRtcpPacket.Chunk[]{new SdesRtcpPacket.Chunk(1L,
        new SdesRtcpPacket.SdesItem[]{sdesItem, sdesItem1, new SdesRtcpPacket.SdesItem(1, "42")})});

    // Assert
    assertEquals(1, actualSdesRtcpPacket.getChunks().length);
    assertEquals(2, actualSdesRtcpPacket.getVersion());
    assertEquals(RtcpPacket.PT_SDES, actualSdesRtcpPacket.getPayloadType());
    assertEquals(0, actualSdesRtcpPacket.getPacketOffset());
    assertEquals(24, actualSdesRtcpPacket.getPacketLength());
    assertEquals(24, actualSdesRtcpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    SdesRtcpPacket actualSdesRtcpPacket = new SdesRtcpPacket(new SdesRtcpPacket.Chunk[]{});

    // Assert
    assertEquals(0, actualSdesRtcpPacket.getChunks().length);
    assertEquals(RtcpPacket.PT_SDES, actualSdesRtcpPacket.getPayloadType());
    assertEquals(0, actualSdesRtcpPacket.getPacketOffset());
    assertEquals(4, actualSdesRtcpPacket.getPacketLength());
    assertEquals(4, actualSdesRtcpPacket.getPacketBuffer().length);
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    SdesRtcpPacket actualSdesRtcpPacket = new SdesRtcpPacket(
        new SdesRtcpPacket.Chunk[]{new SdesRtcpPacket.Chunk(new byte[]{'A', 'A', 'A', 'A', 'A', 0, 'A', 'A', 'A', 'A',
            'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1)});

    // Assert
    assertEquals(1, actualSdesRtcpPacket.getChunks().length);
    assertEquals(2, actualSdesRtcpPacket.getVersion());
    assertEquals(RtcpPacket.PT_SDES, actualSdesRtcpPacket.getPayloadType());
    assertEquals(0, actualSdesRtcpPacket.getPacketOffset());
    assertEquals(12, actualSdesRtcpPacket.getPacketLength());
    assertEquals(12, actualSdesRtcpPacket.getPacketBuffer().length);
  }

  @Test
  void testSdesItemConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    SdesRtcpPacket.SdesItem actualSdesItem = new SdesRtcpPacket.SdesItem(1, "AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertEquals(1, actualSdesItem.getType());
    assertEquals("type=1,value=AAA", actualSdesItem.toString());
  }

  @Test
  void testSdesItemConstructor2() {
    // Arrange and Act
    SdesRtcpPacket.SdesItem actualSdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    // Assert
    assertEquals(0, actualSdesItem.off);
    assertEquals(2, actualSdesItem.len);
    assertEquals(2, actualSdesItem.buf.length);
    assertEquals(1, actualSdesItem.getType());
  }

  @Test
  void testSdesItemConstructor3() throws UnsupportedEncodingException {
    // Arrange and Act
    SdesRtcpPacket.SdesItem actualSdesItem = new SdesRtcpPacket.SdesItem(1, "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, actualSdesItem.off);
    assertEquals(8, actualSdesItem.len);
    assertEquals(8, actualSdesItem.buf.length);
    assertEquals(1, actualSdesItem.getType());
  }

  @Test
  void testSdesItemConstructor4() throws UnsupportedEncodingException {
    // Arrange and Act
    SdesRtcpPacket.SdesItem actualSdesItem = new SdesRtcpPacket.SdesItem("AAAAAAAA".getBytes("UTF-8"), 1);

    // Assert
    assertEquals(67, actualSdesItem.getLength());
    assertEquals(3, actualSdesItem.off);
    assertEquals(8, actualSdesItem.buf.length);
    assertEquals(65, actualSdesItem.getType());
  }

  @Test
  void testSdesItemGetBytes() {
    // Arrange and Act
    byte[] actualBytes = (new SdesRtcpPacket.SdesItem(1, "42")).getBytes();

    // Assert
    assertEquals(4, actualBytes.length);
    assertEquals((byte) 1, actualBytes[0]);
    assertEquals((byte) 2, actualBytes[1]);
    assertEquals('4', actualBytes[2]);
    assertEquals('2', actualBytes[3]);
  }

  @Test
  void testSdesItemGetBytes2() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(26, (new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))).getBytes().length);
  }

  @Test
  void testSdesItemGetBytes3() {
    // Arrange and Act
    byte[] actualBytes = (new SdesRtcpPacket.SdesItem(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1)).getBytes();

    // Assert
    assertEquals(2, actualBytes.length);
    assertEquals('A', actualBytes[0]);
    assertEquals((byte) 0, actualBytes[1]);
  }

  @Test
  void testSdesItemGetBytes4() throws UnsupportedEncodingException {
    // Arrange and Act
    byte[] actualBytes = (new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3))
        .getBytes();

    // Assert
    assertEquals(5, actualBytes.length);
    assertEquals((byte) 1, actualBytes[0]);
    assertEquals((byte) 3, actualBytes[1]);
    assertEquals('A', actualBytes[2]);
    assertEquals('A', actualBytes[3]);
    assertEquals('A', actualBytes[4]);
  }

  @Test
  void testSdesItemGetBytes5() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    // Act and Assert
    assertEquals(4, sdesItem.getBytes("AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testSdesItemGetBytes6() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1,
        3);

    // Act and Assert
    assertEquals(5, sdesItem.getBytes("AAAAAAAA".getBytes("UTF-8"), 1));
  }

  @Test
  void testSdesItemGetBytes7() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new SdesRtcpPacket.SdesItem(1, ",value=")).getBytes(new byte[]{}, 1));
  }

  @Test
  void testSdesItemGetValue() {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "42");

    // Act
    byte[] actualValue = sdesItem.getValue();

    // Assert
    assertSame(sdesItem.buf, actualValue);
    assertEquals(2, actualValue.length);
    assertEquals('4', actualValue[0]);
    assertEquals('2', actualValue[1]);
  }

  @Test
  void testSdesItemGetValue2() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket.SdesItem sdesItem = new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    byte[] actualValue = sdesItem.getValue();

    // Assert
    assertSame(sdesItem.buf, actualValue);
    assertEquals(24, actualValue.length);
  }

  @Test
  void testSdesItemGetValue3() {
    // Arrange, Act and Assert
    assertEquals(0, (new SdesRtcpPacket.SdesItem(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1)).getValue().length);
  }

  @Test
  void testSdesItemGetValue4() throws UnsupportedEncodingException {
    // Arrange and Act
    byte[] actualValue = (new SdesRtcpPacket.SdesItem(1, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1, 3))
        .getValue();

    // Assert
    assertEquals(3, actualValue.length);
    assertEquals('A', actualValue[0]);
    assertEquals('A', actualValue[1]);
    assertEquals('A', actualValue[2]);
  }

  @Test
  void testSetSourceCount() {
    // Arrange
    SdesRtcpPacket sdesRtcpPacket = new SdesRtcpPacket(1L, "Cname");

    // Act
    sdesRtcpPacket.setSourceCount(3);

    // Assert
    assertEquals(2, sdesRtcpPacket.getVersion());
  }

  @Test
  void testSetSourceCount2() {
    // Arrange
    SdesRtcpPacket sdesRtcpPacket = new SdesRtcpPacket(0L, "Cname");

    // Act
    sdesRtcpPacket.setSourceCount(3);

    // Assert
    assertEquals(2, sdesRtcpPacket.getVersion());
  }

  @Test
  void testSetSourceCount3() {
    // Arrange
    SdesRtcpPacket sdesRtcpPacket = new SdesRtcpPacket(1L, "byte[]");

    // Act
    sdesRtcpPacket.setSourceCount(3);

    // Assert
    assertEquals(2, sdesRtcpPacket.getVersion());
  }

  @Test
  void testSetSourceCount4() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket sdesRtcpPacket = new SdesRtcpPacket(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

    // Act
    sdesRtcpPacket.setSourceCount(3);

    // Assert
    assertEquals(1, sdesRtcpPacket.getVersion());
  }

  @Test
  void testSetSourceCount5() {
    // Arrange
    SdesRtcpPacket sdesRtcpPacket = new SdesRtcpPacket(new SdesRtcpPacket.Chunk[]{});

    // Act
    sdesRtcpPacket.setSourceCount(3);

    // Assert
    assertEquals(2, sdesRtcpPacket.getVersion());
  }

  @Test
  void testSetSourceCount6() throws UnsupportedEncodingException {
    // Arrange
    SdesRtcpPacket sdesRtcpPacket = new SdesRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    sdesRtcpPacket.setSourceCount(3);

    // Assert
    assertEquals(1, sdesRtcpPacket.getVersion());
  }

  @Test
  void testGetChunks() {
    // Arrange, Act and Assert
    assertEquals(1, (new SdesRtcpPacket(1L, "Cname")).getChunks().length);
    assertEquals(1, (new SdesRtcpPacket(0L, "Cname")).getChunks().length);
    assertEquals(1, (new SdesRtcpPacket(1L, "byte[]")).getChunks().length);
    assertEquals(0, (new SdesRtcpPacket(new RtcpPacket(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}))).getChunks().length);
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new SdesRtcpPacket(new RtcpPacket(new byte[]{}))).getChunks());
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("ssrc=1,items=[{type=1,value=Cname}]", (new SdesRtcpPacket(1L, "Cname")).toString());
    assertEquals("ssrc=0,items=[{type=1,value=Cname}]", (new SdesRtcpPacket(0L, "Cname")).toString());
    assertEquals("ssrc=1,items=[{type=1,value=,items=[}]", (new SdesRtcpPacket(1L, ",items=[")).toString());
    assertEquals("", (new SdesRtcpPacket(new RtcpPacket(new byte[]{0, 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
        'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}))).toString());
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new SdesRtcpPacket(new RtcpPacket(new byte[]{}))).toString());
  }
}

