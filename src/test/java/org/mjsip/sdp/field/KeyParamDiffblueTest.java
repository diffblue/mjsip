package org.mjsip.sdp.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class KeyParamDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("Key param", (new KeyParam("Key param")).toString());
    assertEquals("Key method:Key info", (new KeyParam("Key method", "Key info")).toString());
    assertEquals("Key param", (new KeyParam(new KeyParam("Key param"))).toString());
    assertEquals("42:Key info", (new KeyParam(new KeyParam("42", "Key info"))).toString());
  }

  @Test
  void testGetKeyMethod() {
    // Arrange, Act and Assert
    assertEquals("Key param", (new KeyParam("Key param")).getKeyMethod());
    assertEquals("", (new KeyParam("")).getKeyMethod());
    assertEquals("Key method", (new KeyParam("Key method", "Key info")).getKeyMethod());
    assertEquals("Key info", (new KeyParam("", "Key info")).getKeyMethod());
  }

  @Test
  void testGetKeyInfo() {
    // Arrange, Act and Assert
    assertEquals("", (new KeyParam("Key param")).getKeyInfo());
    assertEquals("Key", (new KeyParam("Key method", "Key info")).getKeyInfo());
  }
}

