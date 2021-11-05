package org.mjsip.sdp.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.SdpField;

class CryptoAttributeFieldDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    CryptoAttributeField actualCryptoAttributeField = new CryptoAttributeField("42");

    // Assert
    assertEquals("crypto", actualCryptoAttributeField.getAttributeName());
    assertEquals('a', actualCryptoAttributeField.getType());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    CryptoAttributeField actualCryptoAttributeField = new CryptoAttributeField(new SdpField('A', "42"));

    // Assert
    assertEquals("42", actualCryptoAttributeField.getAttributeName());
    assertEquals('A', actualCryptoAttributeField.getType());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    CryptoAttributeField actualCryptoAttributeField = new CryptoAttributeField(new SdpField(new SdpField('A', "42")));

    // Assert
    assertEquals("42", actualCryptoAttributeField.getAttributeName());
    assertEquals('A', actualCryptoAttributeField.getType());
  }
}

