package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RAckHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    RAckHeader actualRAckHeader = new RAckHeader("42");

    // Assert
    assertEquals("", actualRAckHeader.getCSeqMethod());
    assertEquals("RAck: 42\r\n", actualRAckHeader.toString());
    assertEquals("42", actualRAckHeader.getValue());
    assertEquals(42L, actualRAckHeader.getRAckSequenceNumber());
    assertEquals(SipHeaders.RAck, actualRAckHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    RAckHeader actualRAckHeader = new RAckHeader(1L, 1L, "Method");

    // Assert
    assertEquals("1 1 Method", actualRAckHeader.getValue());
    assertEquals(SipHeaders.RAck, actualRAckHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    RAckHeader actualRAckHeader = new RAckHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualRAckHeader.getValue());
    assertEquals("Hname", actualRAckHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    RAckHeader actualRAckHeader = new RAckHeader(header);

    // Assert
    assertEquals("42", actualRAckHeader.getValue());
    assertEquals("Hname", actualRAckHeader.getName());
  }

  @Test
  void testGetRAckSequenceNumber() {
    // Arrange, Act and Assert
    assertEquals(42L, (new RAckHeader("42")).getRAckSequenceNumber());
    assertEquals(1L, (new RAckHeader(1L, 1L, "Method")).getRAckSequenceNumber());
  }

  @Test
  void testGetRAckSequenceNumber2() {
    // Arrange
    RAckHeader rAckHeader = new RAckHeader("");
    rAckHeader.setValue("42");

    // Act and Assert
    assertEquals(42L, rAckHeader.getRAckSequenceNumber());
  }

  @Test
  void testGetRAckSequenceNumber3() {
    // Arrange
    RAckHeader rAckHeader = new RAckHeader(new Header());
    rAckHeader.setValue("42");

    // Act and Assert
    assertEquals(42L, rAckHeader.getRAckSequenceNumber());
  }

  @Test
  void testGetCSeqSequenceNumber() {
    // Arrange, Act and Assert
    assertEquals(1L, (new RAckHeader(1L, 1L, "Method")).getCSeqSequenceNumber());
  }

  @Test
  void testGetCSeqMethod() {
    // Arrange, Act and Assert
    assertEquals("", (new RAckHeader("42")).getCSeqMethod());
    assertEquals("Method", (new RAckHeader(1L, 1L, "Method")).getCSeqMethod());
  }

  @Test
  void testGetCSeqMethod2() {
    // Arrange
    RAckHeader rAckHeader = new RAckHeader(new Header());
    rAckHeader.setValue("42");

    // Act and Assert
    assertEquals("", rAckHeader.getCSeqMethod());
  }
}

