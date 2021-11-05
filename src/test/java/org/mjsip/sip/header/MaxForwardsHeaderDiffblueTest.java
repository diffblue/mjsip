package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class MaxForwardsHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    MaxForwardsHeader actualMaxForwardsHeader = new MaxForwardsHeader("42");

    // Assert
    assertEquals(CoreSipHeaders.Max_Forwards, actualMaxForwardsHeader.getName());
    assertEquals("Max-Forwards: 42\r\n", actualMaxForwardsHeader.toString());
    assertEquals("42", actualMaxForwardsHeader.getValue());
    assertEquals(42, actualMaxForwardsHeader.getNumber());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    MaxForwardsHeader actualMaxForwardsHeader = new MaxForwardsHeader(1);

    // Assert
    assertEquals(CoreSipHeaders.Max_Forwards, actualMaxForwardsHeader.getName());
    assertEquals("1", actualMaxForwardsHeader.getValue());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    MaxForwardsHeader actualMaxForwardsHeader = new MaxForwardsHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("Hname", actualMaxForwardsHeader.getName());
    assertEquals("42", actualMaxForwardsHeader.getValue());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    MaxForwardsHeader actualMaxForwardsHeader = new MaxForwardsHeader(header);

    // Assert
    assertEquals("Hname", actualMaxForwardsHeader.getName());
    assertEquals("42", actualMaxForwardsHeader.getValue());
  }

  @Test
  void testSetNumber() {
    // Arrange
    MaxForwardsHeader maxForwardsHeader = new MaxForwardsHeader(1);

    // Act
    maxForwardsHeader.setNumber(1);

    // Assert
    assertEquals("1", maxForwardsHeader.getValue());
  }

  @Test
  void testGetNumber() {
    // Arrange, Act and Assert
    assertEquals(1, (new MaxForwardsHeader(1)).getNumber());
  }

  @Test
  void testGetNumber2() {
    // Arrange
    MaxForwardsHeader maxForwardsHeader = new MaxForwardsHeader("Hvalue");
    maxForwardsHeader.setNumber(0);

    // Act and Assert
    assertEquals(0, maxForwardsHeader.getNumber());
  }

  @Test
  void testGetNumber3() {
    // Arrange
    MaxForwardsHeader maxForwardsHeader = new MaxForwardsHeader("");
    maxForwardsHeader.setValue("42");

    // Act and Assert
    assertEquals(42, maxForwardsHeader.getNumber());
  }

  @Test
  void testGetNumber4() {
    // Arrange
    MaxForwardsHeader maxForwardsHeader = new MaxForwardsHeader(new Header());
    maxForwardsHeader.setNumber(0);

    // Act and Assert
    assertEquals(0, maxForwardsHeader.getNumber());
  }

  @Test
  void testDecrement() {
    // Arrange
    MaxForwardsHeader maxForwardsHeader = new MaxForwardsHeader(1);

    // Act
    maxForwardsHeader.decrement();

    // Assert
    assertEquals("0", maxForwardsHeader.getValue());
  }

  @Test
  void testDecrement2() {
    // Arrange
    MaxForwardsHeader maxForwardsHeader = new MaxForwardsHeader("Hvalue");
    maxForwardsHeader.setNumber(0);

    // Act
    maxForwardsHeader.decrement();

    // Assert
    assertEquals("-1", maxForwardsHeader.getValue());
  }

  @Test
  void testDecrement3() {
    // Arrange
    MaxForwardsHeader maxForwardsHeader = new MaxForwardsHeader("");
    maxForwardsHeader.setValue("42");

    // Act
    maxForwardsHeader.decrement();

    // Assert
    assertEquals("41", maxForwardsHeader.getValue());
  }

  @Test
  void testDecrement4() {
    // Arrange
    MaxForwardsHeader maxForwardsHeader = new MaxForwardsHeader(new Header());
    maxForwardsHeader.setNumber(0);

    // Act
    maxForwardsHeader.decrement();

    // Assert
    assertEquals("-1", maxForwardsHeader.getValue());
  }
}

