package org.mjsip.sdp.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.SdpField;

class KeyFieldDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    KeyField actualKeyField = new KeyField("Key field");

    // Assert
    assertNull(actualKeyField.getEncryptionKey());
    assertEquals("k=Key field\r\n", actualKeyField.toString());
    assertEquals("Key field", actualKeyField.getValue());
    assertEquals('k', actualKeyField.getType());
    assertEquals("Key field", actualKeyField.getMethod());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    KeyField actualKeyField = new KeyField("Method", "Encryption key");

    // Assert
    assertEquals("Encryption key", actualKeyField.getEncryptionKey());
    assertEquals('k', actualKeyField.getType());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    KeyField actualKeyField = new KeyField(new SdpField('A', "42"));

    // Assert
    assertNull(actualKeyField.getEncryptionKey());
    assertEquals('A', actualKeyField.getType());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    KeyField actualKeyField = new KeyField(new SdpField('\u0000', "42"));

    // Assert
    assertNull(actualKeyField.getEncryptionKey());
    assertEquals('\u0000', actualKeyField.getType());
  }

  @Test
  void testGetMethod() {
    // Arrange, Act and Assert
    assertEquals("Key field", (new KeyField("Key field")).getMethod());
    assertEquals("", (new KeyField(":")).getMethod());
  }

  @Test
  void testGetEncryptionKey() {
    // Arrange, Act and Assert
    assertNull((new KeyField("Key field")).getEncryptionKey());
    assertEquals("", (new KeyField(":")).getEncryptionKey());
  }
}

