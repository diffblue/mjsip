package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class SrRtcpPacketDiffblueTest {
  @Test
  void testReceptionReportOffset() {
    // Arrange, Act and Assert
    assertEquals(28, SrRtcpPacket.receptionReportOffset());
  }

  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AAAAAAAA".getBytes("UTF-8");

    // Act
    SrRtcpPacket actualSrRtcpPacket = new SrRtcpPacket(bytes);

    // Assert
    byte[] expectedPacketBuffer = actualSrRtcpPacket.buffer;
    byte[] packetBuffer = actualSrRtcpPacket.getPacketBuffer();
    assertSame(expectedPacketBuffer, packetBuffer);
    assertEquals(8, packetBuffer.length);
    assertEquals(1, actualSrRtcpPacket.getVersion());
    assertEquals(1094795585L, actualSrRtcpPacket.getSsrc());
    assertEquals(1, actualSrRtcpPacket.getReportBlocks().length);
    assertEquals(66824, actualSrRtcpPacket.getPacketLength());
    assertEquals(0, actualSrRtcpPacket.getPaddingLength());
    assertEquals(1, actualSrRtcpPacket.getReceptionReportCount());
    assertEquals(65, actualSrRtcpPacket.getPayloadType());
    assertEquals(0, actualSrRtcpPacket.getPacketOffset());
    SrRtcpPacket.SenderInfo senderInfo = actualSrRtcpPacket.getSenderInfo();
    assertEquals(8, senderInfo.getBufferOffset());
    assertSame(packetBuffer, senderInfo.getBuffer());
    assertSame(packetBuffer, bytes);
  }

  @Test
  void testConstructor2() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AAAAAAAA".getBytes("UTF-8");

    // Act
    SrRtcpPacket actualSrRtcpPacket = new SrRtcpPacket(bytes, 2);

    // Assert
    byte[] expectedPacketBuffer = actualSrRtcpPacket.buffer;
    byte[] packetBuffer = actualSrRtcpPacket.getPacketBuffer();
    assertSame(expectedPacketBuffer, packetBuffer);
    assertEquals(8, packetBuffer.length);
    assertEquals(1, actualSrRtcpPacket.getVersion());
    assertEquals(1, actualSrRtcpPacket.getReportBlocks().length);
    assertEquals(66824, actualSrRtcpPacket.getPacketLength());
    assertEquals(0, actualSrRtcpPacket.getPaddingLength());
    assertEquals(1, actualSrRtcpPacket.getReceptionReportCount());
    assertEquals(65, actualSrRtcpPacket.getPayloadType());
    assertEquals(2, actualSrRtcpPacket.getPacketOffset());
    SrRtcpPacket.SenderInfo senderInfo = actualSrRtcpPacket.getSenderInfo();
    assertEquals(10, senderInfo.getBufferOffset());
    assertSame(packetBuffer, senderInfo.getBuffer());
    assertSame(packetBuffer, bytes);
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    SrRtcpPacket actualSrRtcpPacket = new SrRtcpPacket(1L, 10L, 10L, 3L, 3L);

    // Assert
    assertEquals(28, actualSrRtcpPacket.getPacketBuffer().length);
    assertEquals(2, actualSrRtcpPacket.getVersion());
    assertEquals(1L, actualSrRtcpPacket.getSsrc());
    assertEquals(28, actualSrRtcpPacket.getPacketLength());
    assertEquals(RtcpPacket.PT_SR, actualSrRtcpPacket.getPayloadType());
    assertEquals(0, actualSrRtcpPacket.getPacketOffset());
    SrRtcpPacket.SenderInfo senderInfo = actualSrRtcpPacket.getSenderInfo();
    assertEquals(3L, senderInfo.getOctectCount());
    assertEquals(10L, senderInfo.getRtpTimestamp());
    assertEquals(42949672L, senderInfo.getNtpTimestamp());
    assertEquals(3L, senderInfo.getPacketCount());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    SrRtcpPacket actualSrRtcpPacket = new SrRtcpPacket(1L, new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L));

    // Assert
    assertEquals(28, actualSrRtcpPacket.getPacketBuffer().length);
    assertEquals(2, actualSrRtcpPacket.getVersion());
    assertEquals(1L, actualSrRtcpPacket.getSsrc());
    assertEquals(28, actualSrRtcpPacket.getPacketLength());
    assertEquals(RtcpPacket.PT_SR, actualSrRtcpPacket.getPayloadType());
    assertEquals(0, actualSrRtcpPacket.getPacketOffset());
    SrRtcpPacket.SenderInfo senderInfo = actualSrRtcpPacket.getSenderInfo();
    assertEquals(3L, senderInfo.getOctectCount());
    assertEquals(10L, senderInfo.getRtpTimestamp());
    assertEquals(42949672L, senderInfo.getNtpTimestamp());
    assertEquals(3L, senderInfo.getPacketCount());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    SrRtcpPacket actualSrRtcpPacket = new SrRtcpPacket(0L, new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L));

    // Assert
    assertEquals(28, actualSrRtcpPacket.getPacketBuffer().length);
    assertEquals(2, actualSrRtcpPacket.getVersion());
    assertEquals(0L, actualSrRtcpPacket.getSsrc());
    assertEquals(28, actualSrRtcpPacket.getPacketLength());
    assertEquals(RtcpPacket.PT_SR, actualSrRtcpPacket.getPayloadType());
    assertEquals(0, actualSrRtcpPacket.getPacketOffset());
    SrRtcpPacket.SenderInfo senderInfo = actualSrRtcpPacket.getSenderInfo();
    assertEquals(3L, senderInfo.getOctectCount());
    assertEquals(10L, senderInfo.getRtpTimestamp());
    assertEquals(42949672L, senderInfo.getNtpTimestamp());
    assertEquals(3L, senderInfo.getPacketCount());
  }

  @Test
  void testConstructor6() throws UnsupportedEncodingException {
    // Arrange and Act
    SrRtcpPacket actualSrRtcpPacket = new SrRtcpPacket(1L,
        new SrRtcpPacket.SenderInfo("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1));

    // Assert
    assertEquals(28, actualSrRtcpPacket.getPacketBuffer().length);
    assertEquals(2, actualSrRtcpPacket.getVersion());
    assertEquals(1L, actualSrRtcpPacket.getSsrc());
    assertEquals(28, actualSrRtcpPacket.getPacketLength());
    assertEquals(RtcpPacket.PT_SR, actualSrRtcpPacket.getPayloadType());
    assertEquals(0, actualSrRtcpPacket.getPacketOffset());
    SrRtcpPacket.SenderInfo senderInfo = actualSrRtcpPacket.getSenderInfo();
    assertEquals(1094795585L, senderInfo.getOctectCount());
    assertEquals(1094795585L, senderInfo.getRtpTimestamp());
    assertEquals(3181868876585L, senderInfo.getNtpTimestamp());
    assertEquals(1094795585L, senderInfo.getPacketCount());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    SrRtcpPacket actualSrRtcpPacket = new SrRtcpPacket(255L, new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L));

    // Assert
    assertEquals(28, actualSrRtcpPacket.getPacketBuffer().length);
    assertEquals(2, actualSrRtcpPacket.getVersion());
    assertEquals(255L, actualSrRtcpPacket.getSsrc());
    assertEquals(28, actualSrRtcpPacket.getPacketLength());
    assertEquals(RtcpPacket.PT_SR, actualSrRtcpPacket.getPayloadType());
    assertEquals(0, actualSrRtcpPacket.getPacketOffset());
    SrRtcpPacket.SenderInfo senderInfo = actualSrRtcpPacket.getSenderInfo();
    assertEquals(3L, senderInfo.getOctectCount());
    assertEquals(10L, senderInfo.getRtpTimestamp());
    assertEquals(42949672L, senderInfo.getNtpTimestamp());
    assertEquals(3L, senderInfo.getPacketCount());
  }

  @Test
  void testConstructor8() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> new SrRtcpPacket(1L, new SrRtcpPacket.SenderInfo("AAAAAAAA".getBytes("UTF-8"), 1)));

  }

  @Test
  void testConstructor9() {
    // Arrange
    SrRtcpPacket.SenderInfo sender_info = new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L);

    // Act
    SrRtcpPacket actualSrRtcpPacket = new SrRtcpPacket(1L, sender_info,
        new RrRtcpPacket.ReportBlock[]{new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)});

    // Assert
    assertEquals(52, actualSrRtcpPacket.getPacketBuffer().length);
    assertEquals(2, actualSrRtcpPacket.getVersion());
    assertEquals(1L, actualSrRtcpPacket.getSsrc());
    assertEquals(52, actualSrRtcpPacket.getPacketLength());
    assertEquals(RtcpPacket.PT_SR, actualSrRtcpPacket.getPayloadType());
    assertEquals(0, actualSrRtcpPacket.getPacketOffset());
    SrRtcpPacket.SenderInfo senderInfo = actualSrRtcpPacket.getSenderInfo();
    assertEquals(1L, senderInfo.getOctectCount());
    assertEquals(1L, senderInfo.getRtpTimestamp());
    assertEquals(2085995274217L, senderInfo.getNtpTimestamp());
    assertEquals(1L, senderInfo.getPacketCount());
  }

  @Test
  void testConstructor10() {
    // Arrange
    SrRtcpPacket.SenderInfo sender_info = new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L);

    // Act
    SrRtcpPacket actualSrRtcpPacket = new SrRtcpPacket(0L, sender_info,
        new RrRtcpPacket.ReportBlock[]{new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)});

    // Assert
    assertEquals(52, actualSrRtcpPacket.getPacketBuffer().length);
    assertEquals(2, actualSrRtcpPacket.getVersion());
    assertEquals(0L, actualSrRtcpPacket.getSsrc());
    assertEquals(52, actualSrRtcpPacket.getPacketLength());
    assertEquals(RtcpPacket.PT_SR, actualSrRtcpPacket.getPayloadType());
    assertEquals(0, actualSrRtcpPacket.getPacketOffset());
    SrRtcpPacket.SenderInfo senderInfo = actualSrRtcpPacket.getSenderInfo();
    assertEquals(1L, senderInfo.getOctectCount());
    assertEquals(1L, senderInfo.getRtpTimestamp());
    assertEquals(2085995274217L, senderInfo.getNtpTimestamp());
    assertEquals(1L, senderInfo.getPacketCount());
  }

  @Test
  void testConstructor11() throws UnsupportedEncodingException {
    // Arrange
    SrRtcpPacket.SenderInfo sender_info = new SrRtcpPacket.SenderInfo("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1);

    // Act
    SrRtcpPacket actualSrRtcpPacket = new SrRtcpPacket(1L, sender_info,
        new RrRtcpPacket.ReportBlock[]{new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)});

    // Assert
    assertEquals(52, actualSrRtcpPacket.getPacketBuffer().length);
    assertEquals(2, actualSrRtcpPacket.getVersion());
    assertEquals(1L, actualSrRtcpPacket.getSsrc());
    assertEquals(52, actualSrRtcpPacket.getPacketLength());
    assertEquals(RtcpPacket.PT_SR, actualSrRtcpPacket.getPayloadType());
    assertEquals(0, actualSrRtcpPacket.getPacketOffset());
    SrRtcpPacket.SenderInfo senderInfo = actualSrRtcpPacket.getSenderInfo();
    assertEquals(1L, senderInfo.getOctectCount());
    assertEquals(1L, senderInfo.getRtpTimestamp());
    assertEquals(2085995274217L, senderInfo.getNtpTimestamp());
    assertEquals(1L, senderInfo.getPacketCount());
  }

  @Test
  void testConstructor12() throws UnsupportedEncodingException {
    // Arrange and Act
    SrRtcpPacket actualSrRtcpPacket = new SrRtcpPacket(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

    // Assert
    assertEquals(24, actualSrRtcpPacket.getPacketBuffer().length);
    assertEquals(0, actualSrRtcpPacket.getPacketOffset());
  }

  @Test
  void testGetSenderInfo() {
    // Arrange and Act
    SrRtcpPacket.SenderInfo actualSenderInfo = (new SrRtcpPacket(1L, 10L, 10L, 3L, 3L)).getSenderInfo();

    // Assert
    assertEquals(28, actualSenderInfo.getBuffer().length);
    assertEquals(8, actualSenderInfo.getBufferOffset());
  }

  @Test
  void testGetSenderInfo2() {
    // Arrange and Act
    SrRtcpPacket.SenderInfo actualSenderInfo = (new SrRtcpPacket(0L, 10L, 10L, 3L, 3L)).getSenderInfo();

    // Assert
    assertEquals(28, actualSenderInfo.getBuffer().length);
    assertEquals(8, actualSenderInfo.getBufferOffset());
  }

  @Test
  void testGetSenderInfo3() throws UnsupportedEncodingException {
    // Arrange and Act
    SrRtcpPacket.SenderInfo actualSenderInfo = (new SrRtcpPacket(
        new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")))).getSenderInfo();

    // Assert
    assertEquals(24, actualSenderInfo.getBuffer().length);
    assertEquals(8, actualSenderInfo.getBufferOffset());
  }

  @Test
  void testGetSenderInfo4() throws UnsupportedEncodingException {
    // Arrange and Act
    SrRtcpPacket.SenderInfo actualSenderInfo = (new SrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")))
        .getSenderInfo();

    // Assert
    assertEquals(24, actualSenderInfo.getBuffer().length);
    assertEquals(8, actualSenderInfo.getBufferOffset());
  }

  @Test
  void testGetSenderInfo5() {
    // Arrange and Act
    SrRtcpPacket.SenderInfo actualSenderInfo = (new SrRtcpPacket(1L, new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L)))
        .getSenderInfo();

    // Assert
    assertEquals(28, actualSenderInfo.getBuffer().length);
    assertEquals(8, actualSenderInfo.getBufferOffset());
  }

  @Test
  void testGetSenderInfo6() throws UnsupportedEncodingException {
    // Arrange and Act
    SrRtcpPacket.SenderInfo actualSenderInfo = (new SrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2))
        .getSenderInfo();

    // Assert
    assertEquals(24, actualSenderInfo.getBuffer().length);
    assertEquals(10, actualSenderInfo.getBufferOffset());
  }

  @Test
  void testSenderInfoConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    SrRtcpPacket.SenderInfo actualSenderInfo = new SrRtcpPacket.SenderInfo("AAAAAAAA".getBytes("UTF-8"), 1);

    // Assert
    byte[] expectedBuffer = actualSenderInfo.buf;
    assertSame(expectedBuffer, actualSenderInfo.getBuffer());
    assertEquals(1, actualSenderInfo.getBufferOffset());
  }

  @Test
  void testSenderInfoConstructor2() {
    // Arrange and Act
    SrRtcpPacket.SenderInfo actualSenderInfo = new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L);

    // Assert
    assertEquals(20, actualSenderInfo.getBuffer().length);
    assertEquals(10L, actualSenderInfo.getRtpTimestamp());
    assertEquals(3L, actualSenderInfo.getPacketCount());
    assertEquals(3L, actualSenderInfo.getOctectCount());
    assertEquals(42949672L, actualSenderInfo.getNtpTimestamp());
    assertEquals(0, actualSenderInfo.getBufferOffset());
  }

  @Test
  void testSenderInfoSetNtpTimestamp() {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L);

    // Act
    senderInfo.setNtpTimestamp(10L);

    // Assert
    assertEquals(42949672L, senderInfo.getNtpTimestamp());
  }

  @Test
  void testSenderInfoSetNtpTimestamp2() {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo(0L, 10L, 3L, 3L);

    // Act
    senderInfo.setNtpTimestamp(10L);

    // Assert
    assertEquals(42949672L, senderInfo.getNtpTimestamp());
  }

  @Test
  void testSenderInfoSetNtpTimestamp3() throws UnsupportedEncodingException {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1);

    // Act
    senderInfo.setNtpTimestamp(10L);

    // Assert
    assertEquals(42949672L, senderInfo.getNtpTimestamp());
  }

  @Test
  void testSenderInfoSetOctectCount() {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L);

    // Act
    senderInfo.setOctectCount(3L);

    // Assert
    assertEquals(3L, senderInfo.getOctectCount());
  }

  @Test
  void testSenderInfoSetOctectCount2() {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo(0L, 10L, 3L, 3L);

    // Act
    senderInfo.setOctectCount(3L);

    // Assert
    assertEquals(3L, senderInfo.getOctectCount());
  }

  @Test
  void testSenderInfoSetOctectCount3() throws UnsupportedEncodingException {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1);

    // Act
    senderInfo.setOctectCount(3L);

    // Assert
    assertEquals(3L, senderInfo.getOctectCount());
  }

  @Test
  void testSenderInfoSetPacketCount() {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L);

    // Act
    senderInfo.setPacketCount(3L);

    // Assert
    assertEquals(3L, senderInfo.getPacketCount());
  }

  @Test
  void testSenderInfoSetPacketCount2() {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo(0L, 10L, 3L, 3L);

    // Act
    senderInfo.setPacketCount(3L);

    // Assert
    assertEquals(3L, senderInfo.getPacketCount());
  }

  @Test
  void testSenderInfoSetPacketCount3() throws UnsupportedEncodingException {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1);

    // Act
    senderInfo.setPacketCount(3L);

    // Assert
    assertEquals(3L, senderInfo.getPacketCount());
  }

  @Test
  void testSenderInfoSetRtpTimestamp() {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L);

    // Act
    senderInfo.setRtpTimestamp(10L);

    // Assert
    assertEquals(10L, senderInfo.getRtpTimestamp());
  }

  @Test
  void testSenderInfoSetRtpTimestamp2() {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo(0L, 10L, 3L, 3L);

    // Act
    senderInfo.setRtpTimestamp(10L);

    // Assert
    assertEquals(10L, senderInfo.getRtpTimestamp());
  }

  @Test
  void testSenderInfoSetRtpTimestamp3() throws UnsupportedEncodingException {
    // Arrange
    SrRtcpPacket.SenderInfo senderInfo = new SrRtcpPacket.SenderInfo("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1);

    // Act
    senderInfo.setRtpTimestamp(10L);

    // Assert
    assertEquals(10L, senderInfo.getRtpTimestamp());
  }

  @Test
  void testSetSenderInfo() {
    // Arrange
    SrRtcpPacket srRtcpPacket = new SrRtcpPacket(1L, 10L, 10L, 3L, 3L);

    // Act
    srRtcpPacket.setSenderInfo(new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L));

    // Assert
    SrRtcpPacket.SenderInfo senderInfo = srRtcpPacket.getSenderInfo();
    assertEquals(3L, senderInfo.getOctectCount());
    assertEquals(10L, senderInfo.getRtpTimestamp());
    assertEquals(42949672L, senderInfo.getNtpTimestamp());
    assertEquals(3L, senderInfo.getPacketCount());
  }

  @Test
  void testSetSenderInfo2() {
    // Arrange
    SrRtcpPacket srRtcpPacket = new SrRtcpPacket(0L, 10L, 10L, 3L, 3L);

    // Act
    srRtcpPacket.setSenderInfo(new SrRtcpPacket.SenderInfo(10L, 10L, 3L, 3L));

    // Assert
    SrRtcpPacket.SenderInfo senderInfo = srRtcpPacket.getSenderInfo();
    assertEquals(3L, senderInfo.getOctectCount());
    assertEquals(10L, senderInfo.getRtpTimestamp());
    assertEquals(42949672L, senderInfo.getNtpTimestamp());
    assertEquals(3L, senderInfo.getPacketCount());
  }
}

