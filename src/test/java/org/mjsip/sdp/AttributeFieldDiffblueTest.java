package org.mjsip.sdp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class AttributeFieldDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    AttributeField actualAttributeField = new AttributeField("Attribute");

    // Assert
    assertEquals("Attribute", actualAttributeField.getAttributeName());
    assertEquals("a=Attribute\r\n", actualAttributeField.toString());
    assertEquals("Attribute", actualAttributeField.getValue());
    assertEquals('a', actualAttributeField.getType());
    assertNull(actualAttributeField.getAttributeValue());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AttributeField actualAttributeField = new AttributeField("Attribute", "42");

    // Assert
    assertEquals("Attribute", actualAttributeField.getAttributeName());
    assertEquals('a', actualAttributeField.getType());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    AttributeField actualAttributeField = new AttributeField(new SdpField('A', "42"));

    // Assert
    assertEquals("42", actualAttributeField.getAttributeName());
    assertEquals('A', actualAttributeField.getType());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    AttributeField actualAttributeField = new AttributeField(new SdpField(new SdpField('A', "42")));

    // Assert
    assertEquals("42", actualAttributeField.getAttributeName());
    assertEquals('A', actualAttributeField.getType());
  }

  @Test
  void testGetAttributeName() {
    // Arrange, Act and Assert
    assertEquals("Attribute", (new AttributeField("Attribute")).getAttributeName());
    assertEquals("", (new AttributeField(":")).getAttributeName());
    assertEquals("Attribute", (new AttributeField("Attribute", "42")).getAttributeName());
    assertEquals("42", (new AttributeField(new SdpField('A', "42"))).getAttributeName());
    assertEquals("42", (new AttributeField(new SdpField(new SdpField('A', "42")))).getAttributeName());
  }

  @Test
  void testGetAttributeValue() {
    // Arrange, Act and Assert
    assertNull((new AttributeField("Attribute")).getAttributeValue());
    assertEquals("", (new AttributeField(":")).getAttributeValue());
    assertEquals("42", (new AttributeField("Attribute", "42")).getAttributeValue());
    assertNull((new AttributeField(new SdpField('A', "42"))).getAttributeValue());
    assertNull((new AttributeField(new SdpField(new SdpField('A', "42")))).getAttributeValue());
  }
}

