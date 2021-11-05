package org.mjsip.sdp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

class SdpFieldDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    SdpField actualSdpField = new SdpField('A', "42");

    // Assert
    assertEquals('A', actualSdpField.getType());
    assertEquals("42", actualSdpField.getValue());
    assertEquals("A=42\r\n", actualSdpField.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SdpField actualSdpField = new SdpField(new SdpField('A', "42"));

    // Assert
    assertEquals('A', actualSdpField.getType());
    assertEquals("42", actualSdpField.getValue());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    SdpField actualSdpField = new SdpField(new SdpField('\u0000', "42"));

    // Assert
    assertEquals('\u0000', actualSdpField.getType());
    assertEquals("42", actualSdpField.getValue());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    SdpField actualSdpField = new SdpField(new SdpField(new SdpField('A', "42")));

    // Assert
    assertEquals('A', actualSdpField.getType());
    assertEquals("42", actualSdpField.getValue());
  }

  @Test
  void testClone() {
    // Arrange, Act and Assert
    assertEquals('A', ((SdpField) (new SdpField('A', "42")).clone()).getType());
    assertEquals("42", ((SdpField) (new SdpField('A', "42")).clone()).getValue());
    assertEquals("42", ((SdpField) (new SdpField(new SdpField('A', "42"))).clone()).getValue());
    assertEquals('A', ((SdpField) (new SdpField(new SdpField('A', "42"))).clone()).getType());
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new SdpField('A', "42")).equals(1));
  }
}

