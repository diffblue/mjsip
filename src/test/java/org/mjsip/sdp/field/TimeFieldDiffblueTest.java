package org.mjsip.sdp.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.SdpField;

class TimeFieldDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    TimeField actualTimeField = new TimeField();

    // Assert
    assertEquals("0", actualTimeField.getStartTime());
    assertEquals("t=0 0\r\n", actualTimeField.toString());
    assertEquals("0 0", actualTimeField.getValue());
    assertEquals('t', actualTimeField.getType());
    assertEquals("0", actualTimeField.getStopTime());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    TimeField actualTimeField = new TimeField("Time field");

    // Assert
    assertEquals("Time", actualTimeField.getStartTime());
    assertEquals("t=Time field\r\n", actualTimeField.toString());
    assertEquals("Time field", actualTimeField.getValue());
    assertEquals('t', actualTimeField.getType());
    assertEquals("field", actualTimeField.getStopTime());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    TimeField actualTimeField = new TimeField("Start", "Stop");

    // Assert
    assertEquals("Start Stop", actualTimeField.getValue());
    assertEquals('t', actualTimeField.getType());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    TimeField actualTimeField = new TimeField(new SdpField('A', "42"));

    // Assert
    assertEquals("42", actualTimeField.getValue());
    assertEquals('A', actualTimeField.getType());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    TimeField actualTimeField = new TimeField(new SdpField('\u0000', "42"));

    // Assert
    assertEquals("42", actualTimeField.getValue());
    assertEquals('\u0000', actualTimeField.getType());
  }

  @Test
  void testGetStartTime() {
    // Arrange, Act and Assert
    assertEquals("Time", (new TimeField("Time field")).getStartTime());
    assertEquals("42", (new TimeField("42")).getStartTime());
    assertEquals("", (new TimeField("")).getStartTime());
    assertEquals("Stop", (new TimeField("", "Stop")).getStartTime());
  }

  @Test
  void testGetStopTime() {
    // Arrange, Act and Assert
    assertEquals("field", (new TimeField("Time field")).getStopTime());
    assertEquals("", (new TimeField("42")).getStopTime());
  }
}

