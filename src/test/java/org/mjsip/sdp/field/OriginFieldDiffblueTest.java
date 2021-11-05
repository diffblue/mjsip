package org.mjsip.sdp.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.SdpField;

class OriginFieldDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("Origin");

    // Assert
    assertEquals("", actualOriginField.getAddress());
    assertEquals("o=Origin\r\n", actualOriginField.toString());
    assertEquals("Origin", actualOriginField.getValue());
    assertEquals("Origin", actualOriginField.getUserName());
    assertEquals('o', actualOriginField.getType());
    assertEquals("", actualOriginField.getSessionVersion());
    assertEquals("", actualOriginField.getSessionId());
    assertEquals("", actualOriginField.getAddressType());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("janedoe", "42 Main St", "42 Main St");

    // Assert
    assertEquals("janedoe 0 0 IN 42 Main St 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField(null, "42 Main St", "42 Main St");

    // Assert
    assertEquals("- 0 0 IN 42 Main St 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("", "42 Main St", "42 Main St");

    // Assert
    assertEquals("- 0 0 IN 42 Main St 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("janedoe", null, "42 Main St");

    // Assert
    assertEquals("janedoe 0 0 IN IP4 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("janedoe", "", "42 Main St");

    // Assert
    assertEquals("janedoe 0 0 IN IP4 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("janedoe", "Sess id", "1.0.2", "42 Main St", "42 Main St");

    // Assert
    assertEquals("janedoe Sess id 1.0.2 IN 42 Main St 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField(null, "Sess id", "1.0.2", "42 Main St", "42 Main St");

    // Assert
    assertEquals("- Sess id 1.0.2 IN 42 Main St 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("", "Sess id", "1.0.2", "42 Main St", "42 Main St");

    // Assert
    assertEquals("- Sess id 1.0.2 IN 42 Main St 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("janedoe", null, "1.0.2", "42 Main St", "42 Main St");

    // Assert
    assertEquals("janedoe 0 1.0.2 IN 42 Main St 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor11() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("janedoe", "", "1.0.2", "42 Main St", "42 Main St");

    // Assert
    assertEquals("janedoe 0 1.0.2 IN 42 Main St 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor12() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("janedoe", "Sess id", null, "42 Main St", "42 Main St");

    // Assert
    assertEquals("janedoe Sess id 0 IN 42 Main St 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor13() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("janedoe", "Sess id", "", "42 Main St", "42 Main St");

    // Assert
    assertEquals("janedoe Sess id 0 IN 42 Main St 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor14() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("janedoe", "Sess id", "1.0.2", null, "42 Main St");

    // Assert
    assertEquals("janedoe Sess id 1.0.2 IN IP4 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor15() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField("janedoe", "Sess id", "1.0.2", "", "42 Main St");

    // Assert
    assertEquals("janedoe Sess id 1.0.2 IN IP4 42 Main St", actualOriginField.getValue());
    assertEquals('o', actualOriginField.getType());
  }

  @Test
  void testConstructor16() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField(new SdpField('A', "42"));

    // Assert
    assertEquals("42", actualOriginField.getValue());
    assertEquals('A', actualOriginField.getType());
  }

  @Test
  void testConstructor17() {
    // Arrange and Act
    OriginField actualOriginField = new OriginField(new SdpField('\u0000', "42"));

    // Assert
    assertEquals("42", actualOriginField.getValue());
    assertEquals('\u0000', actualOriginField.getType());
  }

  @Test
  void testGetUserName() {
    // Arrange, Act and Assert
    assertEquals("Origin", (new OriginField("Origin")).getUserName());
    assertEquals("", (new OriginField("")).getUserName());
    assertEquals("janedoe", (new OriginField("janedoe", "42 Main St", "42 Main St")).getUserName());
  }

  @Test
  void testGetSessionId() {
    // Arrange, Act and Assert
    assertEquals("", (new OriginField("Origin")).getSessionId());
    assertEquals("0", (new OriginField("janedoe", "42 Main St", "42 Main St")).getSessionId());
  }

  @Test
  void testGetSessionVersion() {
    // Arrange, Act and Assert
    assertEquals("", (new OriginField("Origin")).getSessionVersion());
    assertEquals("0", (new OriginField("janedoe", "42 Main St", "42 Main St")).getSessionVersion());
  }

  @Test
  void testGetAddressType() {
    // Arrange, Act and Assert
    assertEquals("", (new OriginField("Origin")).getAddressType());
    assertEquals("42", (new OriginField("janedoe", "42 Main St", "42 Main St")).getAddressType());
  }

  @Test
  void testGetAddress() {
    // Arrange, Act and Assert
    assertEquals("", (new OriginField("Origin")).getAddress());
    assertEquals("Main", (new OriginField("janedoe", "42 Main St", "42 Main St")).getAddress());
  }
}

