package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class RrRtcpPacketDiffblueTest {
  @Test
  void testReceptionReportOffset() {
    // Arrange, Act and Assert
    assertEquals(8, RrRtcpPacket.receptionReportOffset());
  }

  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AAAAAAAA".getBytes("UTF-8");

    // Act
    RrRtcpPacket actualRrRtcpPacket = new RrRtcpPacket(bytes);

    // Assert
    byte[] expectedPacketBuffer = actualRrRtcpPacket.buffer;
    byte[] packetBuffer = actualRrRtcpPacket.getPacketBuffer();
    assertSame(expectedPacketBuffer, packetBuffer);
    assertEquals(8, packetBuffer.length);
    assertEquals(1, actualRrRtcpPacket.getVersion());
    assertEquals(1094795585L, actualRrRtcpPacket.getSsrc());
    assertEquals(1, actualRrRtcpPacket.getReportBlocks().length);
    assertEquals(1, actualRrRtcpPacket.getReceptionReportCount());
    assertEquals(65, actualRrRtcpPacket.getPayloadType());
    assertEquals(0, actualRrRtcpPacket.getPaddingLength());
    assertEquals(0, actualRrRtcpPacket.getPacketOffset());
    assertEquals(66824, actualRrRtcpPacket.getPacketLength());
    assertSame(packetBuffer, bytes);
  }

  @Test
  void testConstructor2() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AAAAAAAA".getBytes("UTF-8");

    // Act
    RrRtcpPacket actualRrRtcpPacket = new RrRtcpPacket(bytes, 2);

    // Assert
    byte[] expectedPacketBuffer = actualRrRtcpPacket.buffer;
    byte[] packetBuffer = actualRrRtcpPacket.getPacketBuffer();
    assertSame(expectedPacketBuffer, packetBuffer);
    assertEquals(8, packetBuffer.length);
    assertEquals(1, actualRrRtcpPacket.getVersion());
    assertEquals(1, actualRrRtcpPacket.getReportBlocks().length);
    assertEquals(1, actualRrRtcpPacket.getReceptionReportCount());
    assertEquals(65, actualRrRtcpPacket.getPayloadType());
    assertEquals(0, actualRrRtcpPacket.getPaddingLength());
    assertEquals(2, actualRrRtcpPacket.getPacketOffset());
    assertEquals(66824, actualRrRtcpPacket.getPacketLength());
    assertSame(packetBuffer, bytes);
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    RrRtcpPacket actualRrRtcpPacket = new RrRtcpPacket(1L,
        new RrRtcpPacket.ReportBlock[]{new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)});

    // Assert
    assertEquals(Integer.SIZE, actualRrRtcpPacket.getPacketBuffer().length);
    assertEquals(2, actualRrRtcpPacket.getVersion());
    assertEquals(1L, actualRrRtcpPacket.getSsrc());
    assertEquals(RtcpPacket.PT_RR, actualRrRtcpPacket.getPayloadType());
    assertEquals(0, actualRrRtcpPacket.getPacketOffset());
    assertEquals(Integer.SIZE, actualRrRtcpPacket.getPacketLength());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    RrRtcpPacket actualRrRtcpPacket = new RrRtcpPacket(0L,
        new RrRtcpPacket.ReportBlock[]{new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)});

    // Assert
    assertEquals(Integer.SIZE, actualRrRtcpPacket.getPacketBuffer().length);
    assertEquals(2, actualRrRtcpPacket.getVersion());
    assertEquals(0L, actualRrRtcpPacket.getSsrc());
    assertEquals(RtcpPacket.PT_RR, actualRrRtcpPacket.getPayloadType());
    assertEquals(0, actualRrRtcpPacket.getPacketOffset());
    assertEquals(Integer.SIZE, actualRrRtcpPacket.getPacketLength());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    RrRtcpPacket actualRrRtcpPacket = new RrRtcpPacket(1L, new RrRtcpPacket.ReportBlock[]{});

    // Assert
    assertEquals(8, actualRrRtcpPacket.getPacketBuffer().length);
    assertEquals(2, actualRrRtcpPacket.getVersion());
    assertEquals(1L, actualRrRtcpPacket.getSsrc());
    assertEquals(RtcpPacket.PT_RR, actualRrRtcpPacket.getPayloadType());
    assertEquals(0, actualRrRtcpPacket.getPacketOffset());
    assertEquals(8, actualRrRtcpPacket.getPacketLength());
  }

  @Test
  void testConstructor6() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> new RrRtcpPacket(1L,
        new RrRtcpPacket.ReportBlock[]{new RrRtcpPacket.ReportBlock("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 1)}));

  }

  @Test
  void testConstructor7() throws UnsupportedEncodingException {
    // Arrange and Act
    RrRtcpPacket actualRrRtcpPacket = new RrRtcpPacket(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

    // Assert
    assertEquals(24, actualRrRtcpPacket.getPacketBuffer().length);
    assertEquals(0, actualRrRtcpPacket.getPacketOffset());
  }

  @Test
  void testGetReceptionReportCount() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(1,
        (new RrRtcpPacket(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")))).getReceptionReportCount());
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new RrRtcpPacket(new RtcpPacket(new byte[]{}))).getReceptionReportCount());
    assertEquals(1, (new RrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))).getReceptionReportCount());
    assertEquals(1, (new RrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2)).getReceptionReportCount());
  }

  @Test
  void testGetReceptionReportCount2() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    RrRtcpPacket.ReportBlock reportBlock1 = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act and Assert
    assertEquals(3, (new RrRtcpPacket(1L, new RrRtcpPacket.ReportBlock[]{reportBlock, reportBlock1,
        new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)})).getReceptionReportCount());
  }

  @Test
  void testReportBlockConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    RrRtcpPacket.ReportBlock actualReportBlock = new RrRtcpPacket.ReportBlock("AAAAAAAA".getBytes("UTF-8"), 1);

    // Assert
    byte[] expectedBuffer = actualReportBlock.buf;
    assertSame(expectedBuffer, actualReportBlock.getBuffer());
    assertEquals(1, actualReportBlock.getBufferOffset());
  }

  @Test
  void testReportBlockConstructor2() {
    // Arrange and Act
    RrRtcpPacket.ReportBlock actualReportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    // Assert
    assertEquals(24, actualReportBlock.getBuffer().length);
    assertEquals(1L, actualReportBlock.getSSRC());
    assertEquals(1L, actualReportBlock.getLSR());
    assertEquals(1L, actualReportBlock.getInterarrivalJitter());
    assertEquals(1L, actualReportBlock.getHighestSqnReceived());
    assertEquals(1, actualReportBlock.getFractionLost());
    assertEquals(1L, actualReportBlock.getDLSR());
    assertEquals(1L, actualReportBlock.getCumulativePacketLost());
    assertEquals(0, actualReportBlock.getBufferOffset());
  }

  @Test
  void testReportBlockSetCumulativePacketLost() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setCumulativePacketLost(1L);

    // Assert
    assertEquals(1L, reportBlock.getCumulativePacketLost());
  }

  @Test
  void testReportBlockSetCumulativePacketLost2() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(0L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setCumulativePacketLost(1L);

    // Assert
    assertEquals(1L, reportBlock.getCumulativePacketLost());
  }

  @Test
  void testReportBlockSetCumulativePacketLost3() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        1);

    // Act
    reportBlock.setCumulativePacketLost(1L);

    // Assert
    assertEquals(1L, reportBlock.getCumulativePacketLost());
  }

  @Test
  void testReportBlockSetDLSR() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setDLSR(1L);

    // Assert
    assertEquals(1L, reportBlock.getDLSR());
  }

  @Test
  void testReportBlockSetDLSR2() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(0L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setDLSR(1L);

    // Assert
    assertEquals(1L, reportBlock.getDLSR());
  }

  @Test
  void testReportBlockSetDLSR3() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        0);

    // Act
    reportBlock.setDLSR(1L);

    // Assert
    assertEquals(1L, reportBlock.getDLSR());
  }

  @Test
  void testReportBlockSetFractionLost() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setFractionLost(1);

    // Assert
    assertEquals(1, reportBlock.getFractionLost());
  }

  @Test
  void testReportBlockSetFractionLost2() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(0L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setFractionLost(1);

    // Assert
    assertEquals(1, reportBlock.getFractionLost());
  }

  @Test
  void testReportBlockSetFractionLost3() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        1);

    // Act
    reportBlock.setFractionLost(1);

    // Assert
    assertEquals(1, reportBlock.getFractionLost());
  }

  @Test
  void testReportBlockSetFractionLost4() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock("AAAAAAAA".getBytes("UTF-8"), 0);

    // Act
    reportBlock.setFractionLost(1);

    // Assert
    assertEquals(1, reportBlock.getFractionLost());
  }

  @Test
  void testReportBlockSetHighestSqnReceived() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setHighestSqnReceived(1L);

    // Assert
    assertEquals(1L, reportBlock.getHighestSqnReceived());
  }

  @Test
  void testReportBlockSetHighestSqnReceived2() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(0L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setHighestSqnReceived(1L);

    // Assert
    assertEquals(1L, reportBlock.getHighestSqnReceived());
  }

  @Test
  void testReportBlockSetHighestSqnReceived3() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        1);

    // Act
    reportBlock.setHighestSqnReceived(1L);

    // Assert
    assertEquals(1L, reportBlock.getHighestSqnReceived());
  }

  @Test
  void testReportBlockSetInterarrivalJitter() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setInterarrivalJitter(1L);

    // Assert
    assertEquals(1L, reportBlock.getInterarrivalJitter());
  }

  @Test
  void testReportBlockSetInterarrivalJitter2() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(0L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setInterarrivalJitter(1L);

    // Assert
    assertEquals(1L, reportBlock.getInterarrivalJitter());
  }

  @Test
  void testReportBlockSetInterarrivalJitter3() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        1);

    // Act
    reportBlock.setInterarrivalJitter(1L);

    // Assert
    assertEquals(1L, reportBlock.getInterarrivalJitter());
  }

  @Test
  void testReportBlockSetLSR() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setLSR(1L);

    // Assert
    assertEquals(1L, reportBlock.getLSR());
  }

  @Test
  void testReportBlockSetLSR2() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(0L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setLSR(1L);

    // Assert
    assertEquals(1L, reportBlock.getLSR());
  }

  @Test
  void testReportBlockSetLSR3() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        1);

    // Act
    reportBlock.setLSR(1L);

    // Assert
    assertEquals(1L, reportBlock.getLSR());
  }

  @Test
  void testReportBlockSetSSRC() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setSSRC(1L);

    // Assert
    assertEquals(1L, reportBlock.getSSRC());
  }

  @Test
  void testReportBlockSetSSRC2() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(0L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act
    reportBlock.setSSRC(1L);

    // Assert
    assertEquals(1L, reportBlock.getSSRC());
  }

  @Test
  void testReportBlockSetSSRC3() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        1);

    // Act
    reportBlock.setSSRC(1L);

    // Assert
    assertEquals(1L, reportBlock.getSSRC());
  }

  @Test
  void testReportBlockSetSSRC4() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock("AAAAAAAA".getBytes("UTF-8"), 0);

    // Act
    reportBlock.setSSRC(1L);

    // Assert
    assertEquals(1L, reportBlock.getSSRC());
  }

  @Test
  void testSetReceptionReportCount() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

    // Act
    rrRtcpPacket.setReceptionReportCount(3);

    // Assert
    assertEquals(1, rrRtcpPacket.getVersion());
  }

  @Test
  void testSetReceptionReportCount2() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new RrRtcpPacket(new RtcpPacket(new byte[]{}))).setReceptionReportCount(3));
  }

  @Test
  void testSetReceptionReportCount3() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    rrRtcpPacket.setReceptionReportCount(3);

    // Assert
    assertEquals(1, rrRtcpPacket.getVersion());
  }

  @Test
  void testSetReceptionReportCount4() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    RrRtcpPacket.ReportBlock reportBlock1 = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket(1L, new RrRtcpPacket.ReportBlock[]{reportBlock, reportBlock1,
        new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)});

    // Act
    rrRtcpPacket.setReceptionReportCount(3);

    // Assert
    assertEquals(2, rrRtcpPacket.getVersion());
  }

  @Test
  void testSetReceptionReportCount5() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2);

    // Act
    rrRtcpPacket.setReceptionReportCount(3);

    // Assert
    assertEquals(1, rrRtcpPacket.getVersion());
  }

  @Test
  void testGetSsrc() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(1094795585L,
        (new RrRtcpPacket(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")))).getSsrc());
    assertEquals(1094795585L,
        (new RrRtcpPacket(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2))).getSsrc());
    assertEquals(1094795585L, (new RrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))).getSsrc());
    assertEquals(1094795585L, (new RrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2)).getSsrc());
  }

  @Test
  void testGetSsrc2() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    RrRtcpPacket.ReportBlock reportBlock1 = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act and Assert
    assertEquals(1L, (new RrRtcpPacket(1L, new RrRtcpPacket.ReportBlock[]{reportBlock, reportBlock1,
        new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)})).getSsrc());
  }

  @Test
  void testSetSsrc() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

    // Act
    rrRtcpPacket.setSsrc(1L);

    // Assert
    assertEquals(1L, rrRtcpPacket.getSsrc());
  }

  @Test
  void testSetSsrc2() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2));

    // Act
    rrRtcpPacket.setSsrc(1L);

    // Assert
    assertEquals(1L, rrRtcpPacket.getSsrc());
  }

  @Test
  void testSetSsrc3() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    rrRtcpPacket.setSsrc(1L);

    // Assert
    assertEquals(1L, rrRtcpPacket.getSsrc());
  }

  @Test
  void testSetSsrc4() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    RrRtcpPacket.ReportBlock reportBlock1 = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket(1L, new RrRtcpPacket.ReportBlock[]{reportBlock, reportBlock1,
        new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)});

    // Act
    rrRtcpPacket.setSsrc(1L);

    // Assert
    assertEquals(1L, rrRtcpPacket.getSsrc());
  }

  @Test
  void testSetSsrc5() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2);

    // Act
    rrRtcpPacket.setSsrc(1L);

    // Assert
    assertEquals(1L, rrRtcpPacket.getSsrc());
  }

  @Test
  void testGetReportBlocks() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(1,
        (new RrRtcpPacket(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")))).getReportBlocks().length);
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> (new RrRtcpPacket(new RtcpPacket(new byte[]{}))).getReportBlocks());
    assertEquals(1, (new RrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))).getReportBlocks().length);
    assertEquals(1, (new RrRtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2)).getReportBlocks().length);
  }

  @Test
  void testGetReportBlocks2() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    RrRtcpPacket.ReportBlock reportBlock1 = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    // Act and Assert
    assertEquals(3, (new RrRtcpPacket(1L, new RrRtcpPacket.ReportBlock[]{reportBlock, reportBlock1,
        new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)})).getReportBlocks().length);
  }

  @Test
  void testSetReportBlocks() {
    // Arrange
    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket(new RtcpPacket(new byte[]{}));

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> rrRtcpPacket
        .setReportBlocks(new RrRtcpPacket.ReportBlock[]{new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)}));
  }

  @Test
  void testSetReportBlocks2() {
    // Arrange
    RrRtcpPacket.ReportBlock reportBlock = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    RrRtcpPacket.ReportBlock reportBlock1 = new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L);

    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket(1L, new RrRtcpPacket.ReportBlock[]{reportBlock, reportBlock1,
        new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)});

    // Act
    rrRtcpPacket
        .setReportBlocks(new RrRtcpPacket.ReportBlock[]{new RrRtcpPacket.ReportBlock(1L, 1, 1L, 1L, 1L, 1L, 1L)});

    // Assert
    assertEquals(2, rrRtcpPacket.getVersion());
  }

  @Test
  void testSetReportBlocks3() throws UnsupportedEncodingException {
    // Arrange
    RrRtcpPacket rrRtcpPacket = new RrRtcpPacket(new RtcpPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

    // Act
    rrRtcpPacket.setReportBlocks(null);

    // Assert
    assertEquals(1, rrRtcpPacket.getVersion());
  }
}

